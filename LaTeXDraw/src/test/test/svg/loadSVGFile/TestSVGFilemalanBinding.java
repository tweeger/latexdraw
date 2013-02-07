package test.svg.loadSVGFile;

import java.awt.Color;

import net.sf.latexdraw.glib.models.interfaces.IArrow.ArrowStyle;
import net.sf.latexdraw.glib.models.interfaces.IBezierCurve;
import net.sf.latexdraw.glib.models.interfaces.IPolygon;
import net.sf.latexdraw.glib.models.interfaces.IPolyline;
import net.sf.latexdraw.glib.models.interfaces.IText;
import net.sf.latexdraw.glib.models.interfaces.IShape.FillingStyle;
import net.sf.latexdraw.glib.models.interfaces.IShape.LineStyle;
import net.sf.latexdraw.glib.models.interfaces.IText.TextPosition;

public class TestSVGFilemalanBinding extends TestLoadSVGFile {

	@Override
	public String getPathSVGFile() {
		return "src/resources/test/res/testLoad/malanBinding.svg";
	}


	public void testShape26() {
		assertTrue(group.getShapeAt(26) instanceof IBezierCurve);
		IBezierCurve pol = (IBezierCurve) group.getShapeAt(26);

		assertEquals(Color.BLACK, pol.getLineColour());
		assertEquals(2, pol.getNbPoints());
		assertFalse(pol.hasDbleBord());
		assertEquals(2., pol.getThickness());
		assertEquals(111., pol.getPtAt(0).getX(), 0.00001);
		assertEquals(166.7561896, pol.getPtAt(0).getY(), 0.00001);
		assertEquals(115., pol.getPtAt(1).getX(), 0.00001);
		assertEquals(40., pol.getPtAt(1).getY(), 0.00001);
		assertEquals(89., pol.getFirstCtrlPtAt(0).getX(), 0.00001);
		assertEquals(138.688747, pol.getFirstCtrlPtAt(0).getY(), 0.00001);
		assertEquals(85., pol.getFirstCtrlPtAt(1).getX(), 0.00001);
		assertEquals(59.013428, pol.getFirstCtrlPtAt(1).getY(), 0.00001);
		assertEquals(0., pol.getRotationAngle());
		assertEquals(LineStyle.SOLID, pol.getLineStyle());
		assertFalse(pol.hasShadow());
		assertEquals(FillingStyle.NONE, pol.getFillingStyle());
		assertEquals(ArrowStyle.NONE, pol.getArrowAt(0).getArrowStyle());
		assertEquals(ArrowStyle.RIGHT_ARROW, pol.getArrowAt(1).getArrowStyle());
		assertEquals(0., pol.getArrowAt(1).getArrowInset(), 0.00001);
		assertEquals(2.65, pol.getArrowAt(1).getArrowSizeDim(), 0.01);
		assertEquals(2.0, pol.getArrowAt(1).getArrowSizeNum(), 0.00001);
		assertEquals(1.4, pol.getArrowAt(1).getArrowLength(), 0.00001);
	}


	public void testShape4() {
		assertTrue(group.getShapeAt(4) instanceof IPolyline);
		IPolyline pol = (IPolyline) group.getShapeAt(4);

		assertEquals(new Color(177, 177, 177), pol.getLineColour());
		assertEquals(2, pol.getNbPoints());
		assertFalse(pol.hasDbleBord());
		assertEquals(4., pol.getThickness());
		assertEquals(294., pol.getPtAt(0).getX(), 0.00001);
		assertEquals(102., pol.getPtAt(0).getY(), 0.00001);
		assertEquals(294.0, pol.getPtAt(1).getX(), 0.00001);
		assertEquals(167.0, pol.getPtAt(1).getY(), 0.00001);
		assertEquals(0., pol.getRotationAngle());
		assertEquals(LineStyle.DASHED, pol.getLineStyle());
		assertFalse(pol.hasShadow());
		assertEquals(FillingStyle.NONE, pol.getFillingStyle());
	}


	public void testShape3() {
		assertTrue(group.getShapeAt(3) instanceof IText);
		IText txt = (IText) group.getShapeAt(3);
		assertEquals(Color.BLACK, txt.getLineColour());
		assertEquals("\\emph{\\textbf{\\scriptsize{Correspondance de schémas}}}", txt.getText());
		assertEquals(210.585929, txt.getPosition().getX(), 0.00001);
		assertEquals(61.859375, txt.getPosition().getY(), 0.00001);
		assertEquals(TextPosition.BOT_LEFT, txt.getTextPosition());
	}


	public void testShape1() {
		assertTrue(group.getShapeAt(1) instanceof IPolygon);
		IPolygon pol = (IPolygon) group.getShapeAt(1);

		assertEquals(Color.BLACK, pol.getLineColour());
		assertEquals(5, pol.getNbPoints());
		assertFalse(pol.hasDbleBord());
		assertEquals(1., pol.getThickness());
		assertEquals(108.499829, pol.getPtAt(0).getX(), 0.00001);
		assertEquals(23.96374, pol.getPtAt(0).getY(), 0.00001);
		assertEquals(108.499829, pol.getPtAt(1).getX(), 0.00001);
		assertEquals(120.05512, pol.getPtAt(1).getY(), 0.00001);
		assertEquals(200.82894, pol.getPtAt(2).getX(), 0.00001);
		assertEquals(120.05512, pol.getPtAt(2).getY(), 0.00001);
		assertEquals(200.82894, pol.getPtAt(3).getX(), 0.00001);
		assertEquals(62.400297, pol.getPtAt(3).getY(), 0.00001);
		assertEquals(174.44919, pol.getPtAt(4).getX(), 0.00001);
		assertEquals(23.963746, pol.getPtAt(4).getY(), 0.00001);
		assertEquals(0., pol.getRotationAngle());
		assertEquals(LineStyle.SOLID, pol.getLineStyle());
		assertFalse(pol.hasShadow());
		assertEquals(FillingStyle.PLAIN, pol.getFillingStyle());
		assertEquals(new Color(210, 230, 254), pol.getFillingCol());
	}


	public void testShape0() {
		assertTrue(group.getShapeAt(0) instanceof IPolyline);
		IPolyline pol = (IPolyline) group.getShapeAt(0);

		assertEquals(Color.BLACK, pol.getLineColour());
		assertEquals(2, pol.getNbPoints());
		assertTrue(pol.hasDbleBord());
		assertEquals(1., pol.getThickness());
		assertEquals(204.37871, pol.getPtAt(0).getX(), 0.00001);
		assertEquals(401.13132, pol.getPtAt(1).getX(), 0.00001);
		assertEquals(193.85937, pol.getPtAt(0).getY(), 0.00001);
		assertEquals(193.0, pol.getPtAt(1).getY(), 0.00001);
		assertEquals(0., pol.getRotationAngle());
		assertEquals(LineStyle.SOLID, pol.getLineStyle());
		assertEquals(6., pol.getDbleBordSep());
		assertEquals(Color.WHITE, pol.getDbleBordCol());
		assertFalse(pol.hasShadow());
		assertEquals(ArrowStyle.NONE, pol.getArrowAt(0).getArrowStyle());
		assertEquals(ArrowStyle.RIGHT_ARROW, pol.getArrowAt(1).getArrowStyle());
		assertEquals(0.4, pol.getArrowAt(1).getArrowInset(), 0.00001);
		assertEquals(2.65, pol.getArrowAt(1).getArrowSizeDim(), 0.01);
		assertEquals(2.0, pol.getArrowAt(1).getArrowSizeNum(), 0.00001);
		assertEquals(1.4, pol.getArrowAt(1).getArrowLength(), 0.00001);
		assertEquals(FillingStyle.NONE, pol.getFillingStyle());
	}


	@Override
	public int getNbShapesExpected() {
		return 39;
	}
}