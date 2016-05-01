package net.sf.latexdraw.instruments

import java.io.File

import net.sf.latexdraw.actions.ExportTemplate
import net.sf.latexdraw.actions.LoadTemplate
import net.sf.latexdraw.actions.UpdateTemplates
import net.sf.latexdraw.badaboom.BadaboomCollector
import net.sf.latexdraw.generators.svg.SVGDocumentGenerator
import net.sf.latexdraw.glib.models.interfaces.shape.IDrawing
import net.sf.latexdraw.lang.LangTool
import net.sf.latexdraw.ui.LFrame
import net.sf.latexdraw.util.LResources
import org.malai.swing.widget.MMenu
import org.malai.swing.instrument.WidgetInstrument
import org.malai.swing.widget.MMenuItem
import org.malai.swing.interaction.library.MenuItemPressed
import org.malai.swing.ui.SwingUIComposer
import org.malai.instrument.Interactor

/**
 * This instrument manages the templates.<br>
 * <br>
 * This file is part of LaTeXDraw.<br>
 * Copyright (c) 2005-2014 Arnaud BLOUIN<br>
 * <br>
 * LaTeXDraw is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later version.
 * <br>
 * LaTeXDraw is distributed without any warranty; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.<br>
 * <br>
 * 2012-04-19<br>
 * @author Arnaud BLOUIN
 * @since 3.0
 */
class TemplateManager(composer : SwingUIComposer[_], val ui : LFrame, val drawing:IDrawing) extends WidgetInstrument(composer) {
	/** The main menu that contains the template menu items. */
	val _templateMenu : MMenu = new MMenu(LangTool.INSTANCE.getStringLaTeXDrawFrame("LaTeXDrawFrame.103"), true)

	/** The menu item that permits to update the templates. */
	val _updateTemplatesMenu : MMenuItem = new MMenuItem(LangTool.INSTANCE.getStringLaTeXDrawFrame("LaTeXDrawFrame.41"), LResources.RELOAD_ICON)

	/** The menu item used to export the selection as templates. */
	val _exportTemplateMenu : MMenuItem = new MMenuItem(LangTool.INSTANCE.getStringLaTeXDrawFrame("LaTeXDrawFrame.42"))


	initialiseWidgets


	override def setActivated(activated : Boolean) {
		super.setActivated(activated)
		templateMenu.setEnabled(isActivated)
	}


	override protected def initialiseInteractors() {
		try	{
			addInteractor(new MenuItem2UpdateTemplates(this))
			addInteractor(new MenuItem2LoadTemplate(this))
			addInteractor(new MenuItem2ExportTemplate(this))
		}
		catch{case ex: Throwable => BadaboomCollector.INSTANCE.add(ex)}
	}


	override protected def initialiseWidgets() {
		_templateMenu.addSeparator
		_templateMenu.add(_updateTemplatesMenu)

		val action = new UpdateTemplates()
		action.templatesMenu = _templateMenu
		action.updateThumbnails = false
		action.doIt
		action.flush
	}

	/**
	 * @return The main menu that contains the template menu items.
	 */
	def templateMenu = _templateMenu

	/**
	 * @return The menu item that permits to update the templates.
	 */
	def updateTemplatesMenu() = _updateTemplatesMenu

	/**
	 * @return The menu item used to export the selection as templates.
	 */
	def exportTemplateMenu = _exportTemplateMenu
}


private sealed class MenuItem2ExportTemplate(ins : TemplateManager) extends
				Interactor[ExportTemplate, MenuItemPressed, TemplateManager](ins, false, classOf[ExportTemplate], classOf[MenuItemPressed]) {
	override def initAction() {
		action.setUi(instrument.ui)
		action.templatesMenu = instrument.templateMenu
	}

	override def isConditionRespected = interaction.getMenuItem==instrument.exportTemplateMenu
}


private sealed class MenuItem2LoadTemplate(ins : TemplateManager) extends
				Interactor[LoadTemplate, MenuItemPressed, TemplateManager](ins, false, classOf[LoadTemplate], classOf[MenuItemPressed]) {
	override def initAction() {
		action.setFile(new File(interaction.getMenuItem.getName))
		action.setOpenSaveManager(SVGDocumentGenerator.INSTANCE)
		action.setUi(instrument.ui)
    action.setDrawing(instrument.drawing)
	}

	override def isConditionRespected = interaction.getMenuItem!=instrument.updateTemplatesMenu && instrument.templateMenu.contains(interaction.getMenuItem)
}


/** Maps a menu item interaction to an action that updates the templates. */
private sealed class MenuItem2UpdateTemplates(ins : TemplateManager) extends
				Interactor[UpdateTemplates, MenuItemPressed, TemplateManager](ins, false, classOf[UpdateTemplates], classOf[MenuItemPressed]) {
	override def initAction() {
		action.updateThumbnails = true
		action.templatesMenu = instrument.templateMenu
	}

	override def isConditionRespected = interaction.getMenuItem==instrument.updateTemplatesMenu
}
