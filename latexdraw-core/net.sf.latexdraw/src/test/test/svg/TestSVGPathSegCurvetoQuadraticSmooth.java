package test.svg;

import java.text.ParseException;

import junit.framework.TestCase;

import net.sf.latexdraw.parsers.svg.parsers.SVGPathParser;
import net.sf.latexdraw.parsers.svg.path.SVGPathHandler;
import net.sf.latexdraw.parsers.svg.path.SVGPathSeg;
import net.sf.latexdraw.parsers.svg.path.SVGPathSegCurvetoQuadraticSmooth;
import net.sf.latexdraw.parsers.svg.path.SVGPathSegMoveto;
import net.sf.latexdraw.parsers.svg.path.SVGPathSeg.PathSeg;

import org.junit.Test;

public class TestSVGPathSegCurvetoQuadraticSmooth extends TestCase implements SVGPathHandler {
	protected final SVGPathSegCurvetoQuadraticSmooth seg = new SVGPathSegCurvetoQuadraticSmooth(-5.23e-10, 6.5, false);
	protected int cpt = 0;

	@Test
	public void testGetters() {
		assertEquals(seg.getX(), -5.23e-10);
		assertEquals(seg.getY(), 6.5);
		assertFalse(seg.isRelative());
		assertEquals(seg.getType(), PathSeg.CURVETO_QUADRATIC_SMOOTH_ABS);
	}


	@Test
	public void testToString() throws ParseException {
		SVGPathSegMoveto m = new SVGPathSegMoveto(0, 0, false);
		SVGPathParser parser = new SVGPathParser(m.toString() + " " + seg.toString(), this);

		parser.parse();
	}



	@Override
	public void onPathSeg(SVGPathSeg pathSeg) {
		if((pathSeg instanceof SVGPathSegMoveto) && cpt==0) {
			cpt++;
			return ;
		}

		assertTrue(pathSeg instanceof SVGPathSegCurvetoQuadraticSmooth);

		SVGPathSegCurvetoQuadraticSmooth seg2 = (SVGPathSegCurvetoQuadraticSmooth)pathSeg;

		assertEquals(seg.getX(), seg2.getX());
		assertEquals(seg.getY(), seg2.getY());
		assertEquals(seg.isRelative(), seg2.isRelative());
	}
}