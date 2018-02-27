package net.sf.latexdraw.view.jfx;

import java.util.concurrent.TimeoutException;
import javafx.geometry.Bounds;
import javafx.scene.shape.Path;
import net.sf.latexdraw.data.ShapeSupplier;
import net.sf.latexdraw.models.interfaces.shape.IRhombus;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxToolkit;

import static org.junit.Assert.assertEquals;

public class TestViewRhombus extends TestViewBorderedShape<ViewRhombus, IRhombus, Path> {
	@BeforeClass
	public static void beforeClass() throws TimeoutException {
		FxToolkit.registerPrimaryStage();
	}

	@Override
	protected IRhombus createModel() {
		return ShapeSupplier.createRhombus();
	}

	@Test
	@Override
	public void testOnTranslateX() {
		final Bounds before = view.getBorder().getBoundsInParent();
		model.translate(17d, 0d);
		view.getBorder().setTranslateX(-17d);
		assertEquals(before, view.getBorder().getBoundsInParent());
	}

	@Test
	@Override
	public void testOnTranslateY() {
		final Bounds before = view.getBorder().getBoundsInParent();
		model.translate(0d, -19d);
		view.getBorder().setTranslateY(19d);
		assertEquals(before, view.getBorder().getBoundsInParent());
	}

	@Test
	@Override
	public void testShadowPositionSameThanBorder() {
		assertEquals(view.getBorder().getElements(), view.getShadow().get().getElements());
	}
}
