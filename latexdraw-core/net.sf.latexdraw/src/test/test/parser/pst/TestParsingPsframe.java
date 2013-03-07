package test.parser.pst;

import static org.junit.Assert.*;

import java.text.ParseException;

import net.sf.latexdraw.glib.models.interfaces.IRectangle;
import net.sf.latexdraw.glib.models.interfaces.IShape;
import net.sf.latexdraw.glib.views.pst.PSTricksConstants;
import net.sf.latexdraw.parsers.pst.parser.PSTParser;

import org.junit.Test;

public class TestParsingPsframe extends TestParsingShape {
	@Override
	public String getCommandName() {
		return "psframe";
	}


	@Override
	public String getBasicCoordinates() {
		return "(35,20)";
	}


	@Test
	public void testParamAddfillstyle() throws ParseException {
		parser.parsePSTCode("\\"+getCommandName()+"[addfillstyle=hlines]"+getBasicCoordinates());
		assertTrue(PSTParser.errorLogs().isEmpty());
	}


	@Test
	public void testParamBorder() throws ParseException {
		parser.parsePSTCode("\\"+getCommandName()+"[border=2.1in]"+getBasicCoordinates());
		assertTrue(PSTParser.errorLogs().isEmpty());
	}


	@Test
	public void testParamDotsep() throws ParseException {
		parser.parsePSTCode("\\"+getCommandName()+"[dotsep=2.1in]"+getBasicCoordinates());
		assertTrue(PSTParser.errorLogs().isEmpty());
	}


	@Test
	public void testParamDashNumNum() throws ParseException {
		parser.parsePSTCode("\\"+getCommandName()+"[dash=2.1 +0.3]"+getBasicCoordinates());
		assertTrue(PSTParser.errorLogs().isEmpty());
	}


	@Test
	public void testParamDashDimDim() throws ParseException {
		parser.parsePSTCode("\\"+getCommandName()+"[dash=2cm 0.3 pt]"+getBasicCoordinates());
		assertTrue(PSTParser.errorLogs().isEmpty());
	}


	@Test
	public void testParamDashDimNum() throws ParseException {
		parser.parsePSTCode("\\"+getCommandName()+"[dash=2cm 0.3]"+getBasicCoordinates());
		assertTrue(PSTParser.errorLogs().isEmpty());
	}



	@Test
	public void testParamFramearc() throws ParseException {
		IRectangle sh = (IRectangle)parser.parsePSTCode("\\"+getCommandName()+"[framearc=0]"+getBasicCoordinates()).get().getShapeAt(0);
		assertEquals(0., sh.getLineArc(), 0.00001);
		assertTrue(PSTParser.errorLogs().isEmpty());
		sh = (IRectangle)parser.parsePSTCode("\\"+getCommandName()+"[framearc=1]"+getBasicCoordinates()).get().getShapeAt(0);
		assertEquals(1., sh.getLineArc(), 0.00001);
		assertTrue(PSTParser.errorLogs().isEmpty());
		sh = (IRectangle)parser.parsePSTCode("\\"+getCommandName()+"[framearc=0.5]"+getBasicCoordinates()).get().getShapeAt(0);
		assertEquals(0.5, sh.getLineArc(), 0.00001);
		assertTrue(PSTParser.errorLogs().isEmpty());
		sh = (IRectangle)parser.parsePSTCode("\\"+getCommandName()+"[framearc=0.2, framearc=0.3]"+getBasicCoordinates()).get().getShapeAt(0);
		assertEquals(0.3, sh.getLineArc(), 0.00001);
		assertTrue(PSTParser.errorLogs().isEmpty());
		sh = (IRectangle)parser.parsePSTCode("\\"+getCommandName()+"[framearc=-1]"+getBasicCoordinates()).get().getShapeAt(0);
		assertFalse(PSTParser.errorLogs().isEmpty());
		PSTParser.errorLogs().clear();
		sh = (IRectangle)parser.parsePSTCode("\\"+getCommandName()+"[framearc=0.5cm]"+getBasicCoordinates()).get().getShapeAt(0);
		assertFalse(PSTParser.errorLogs().isEmpty());
		PSTParser.errorLogs().clear();
		sh = (IRectangle)parser.parsePSTCode("\\"+getCommandName()+"[framearc=2]"+getBasicCoordinates()).get().getShapeAt(0);
		assertFalse(PSTParser.errorLogs().isEmpty());
	}


	@Test
	public void testCoordinatesPt() throws ParseException {
		IRectangle rec = (IRectangle)parser.parsePSTCode("\\"+getCommandName()+"(0,0)(35pt,20pt)").get().getShapeAt(0);
		assertEquals(0., rec.getPosition().getX(), 0.001);
		assertEquals(0., rec.getPosition().getY(), 0.001);
		assertEquals(35.*IShape.PPC/PSTricksConstants.CM_VAL_PT, rec.getWidth(), 0.001);
		assertEquals(20.*IShape.PPC/PSTricksConstants.CM_VAL_PT, rec.getHeight(), 0.001);
		assertTrue(PSTParser.errorLogs().isEmpty());
	}


	@Test
	public void testCoordinatesMm() throws ParseException {
		IRectangle rec = (IRectangle)parser.parsePSTCode("\\"+getCommandName()+"(0,0)(350mm,200mm)").get().getShapeAt(0);
		assertEquals(0., rec.getPosition().getX(), 0.001);
		assertEquals(0., rec.getPosition().getY(), 0.001);
		assertEquals(35.*IShape.PPC, rec.getWidth(), 0.001);
		assertEquals(20.*IShape.PPC, rec.getHeight(), 0.001);
		assertTrue(PSTParser.errorLogs().isEmpty());
	}


	@Test
	public void testCoordinatesInch() throws ParseException {
		IRectangle rec = (IRectangle)parser.parsePSTCode("\\"+getCommandName()+"(0,0)(35in,20in)").get().getShapeAt(0);
		assertEquals(0., rec.getPosition().getX(), 0.001);
		assertEquals(0., rec.getPosition().getY(), 0.001);
		assertEquals(35.*IShape.PPC/2.54, rec.getWidth(), 0.001);
		assertEquals(20.*IShape.PPC/2.54, rec.getHeight(), 0.001);
		assertTrue(PSTParser.errorLogs().isEmpty());
	}


	@Test
	public void testCoordinatesCm() throws ParseException {
		IRectangle rec = (IRectangle)parser.parsePSTCode("\\"+getCommandName()+"(0,0)(35cm,20cm)").get().getShapeAt(0);
		assertEquals(0., rec.getPosition().getX(), 0.001);
		assertEquals(0., rec.getPosition().getY(), 0.001);
		assertEquals(35.*IShape.PPC, rec.getWidth(), 0.001);
		assertEquals(20.*IShape.PPC, rec.getHeight(), 0.001);
		assertTrue(PSTParser.errorLogs().isEmpty());
	}


	@Test
	public void test1Coordinates() throws ParseException {
		IRectangle rec = (IRectangle)parser.parsePSTCode("\\"+getCommandName()+"(35,20)").get().getShapeAt(0);
		assertEquals(0., rec.getPosition().getX(), 0.001);
		assertEquals(0., rec.getPosition().getY(), 0.001);
		assertEquals(35.*IShape.PPC, rec.getWidth(), 0.001);
		assertEquals(20.*IShape.PPC, rec.getHeight(), 0.001);
		assertTrue(PSTParser.errorLogs().isEmpty());
	}


	@Test
	public void test2CoordinatesIntOppositeAll() throws ParseException {
		IRectangle rec = (IRectangle)parser.parsePSTCode("\\"+getCommandName()+"(35,50)(10,20)").get().getShapeAt(0);
		assertEquals(10.*IShape.PPC, rec.getPosition().getX(), 0.001);
		assertEquals(20.*-IShape.PPC, rec.getPosition().getY(), 0.001);
		assertEquals(25.*IShape.PPC, rec.getWidth(), 0.001);
		assertEquals(30.*IShape.PPC, rec.getHeight(), 0.001);
		assertTrue(PSTParser.errorLogs().isEmpty());
	}


	@Test
	public void test2CoordinatesIntOppositeX() throws ParseException {
		IRectangle rec = (IRectangle)parser.parsePSTCode("\\"+getCommandName()+"(35,20)(10,50)").get().getShapeAt(0);
		assertEquals(10.*IShape.PPC, rec.getPosition().getX(), 0.001);
		assertEquals(20.*-IShape.PPC, rec.getPosition().getY(), 0.001);
		assertEquals(25.*IShape.PPC, rec.getWidth(), 0.001);
		assertEquals(30.*IShape.PPC, rec.getHeight(), 0.001);
		assertTrue(PSTParser.errorLogs().isEmpty());
	}


	@Test
	public void test2CoordinatesIntOppositeY() throws ParseException {
		IRectangle rec = (IRectangle)parser.parsePSTCode("\\"+getCommandName()+"(10,50)(35,20)").get().getShapeAt(0);
		assertEquals(10.*IShape.PPC, rec.getPosition().getX(), 0.001);
		assertEquals(20.*-IShape.PPC, rec.getPosition().getY(), 0.001);
		assertEquals(25.*IShape.PPC, rec.getWidth(), 0.001);
		assertEquals(30.*IShape.PPC, rec.getHeight(), 0.001);
		assertTrue(PSTParser.errorLogs().isEmpty());
	}



	@Test
	public void test2CoordinatesInt() throws ParseException {
		IRectangle rec = (IRectangle)parser.parsePSTCode("\\"+getCommandName()+"(10,20)(35,50)").get().getShapeAt(0);
		assertEquals(10.*IShape.PPC, rec.getPosition().getX(), 0.001);
		assertEquals(20.*-IShape.PPC, rec.getPosition().getY(), 0.001);
		assertEquals(25.*IShape.PPC, rec.getWidth(), 0.001);
		assertEquals(30.*IShape.PPC, rec.getHeight(), 0.001);
		assertTrue(PSTParser.errorLogs().isEmpty());
	}


	@Test
	public void test2CoordinatesFloatSigns2() throws ParseException {
		IRectangle rec = (IRectangle)parser.parsePSTCode("\\"+getCommandName()+"(-+.5,+-5)(+++35.5,--50.5)").get().getShapeAt(0);
		assertEquals(-.5*IShape.PPC, rec.getPosition().getX(), 0.001);
		assertEquals(-5.*-IShape.PPC, rec.getPosition().getY(), 0.001);
		assertEquals(36.*IShape.PPC, rec.getWidth(), 0.001);
		assertEquals(55.5*IShape.PPC, rec.getHeight(), 0.001);
		assertTrue(PSTParser.errorLogs().isEmpty());
	}


	@Test
	public void test2CoordinatesFloatSigns() throws ParseException {
		IRectangle rec = (IRectangle)parser.parsePSTCode("\\"+getCommandName()+"(-+-.5,+--.5)(+++35.5,--50.5)").get().getShapeAt(0);
		assertEquals(.5*IShape.PPC, rec.getPosition().getX(), 0.001);
		assertEquals(.5*-IShape.PPC, rec.getPosition().getY(), 0.001);
		assertEquals(35.*IShape.PPC, rec.getWidth(), 0.001);
		assertEquals(50.*IShape.PPC, rec.getHeight(), 0.001);
		assertTrue(PSTParser.errorLogs().isEmpty());
	}


	@Test
	public void test2CoordinatesFloat2() throws ParseException {
		IRectangle rec = (IRectangle)parser.parsePSTCode("\\"+getCommandName()+"(.5,.5)(35.5,50.5)").get().getShapeAt(0);
		assertEquals(.5*IShape.PPC, rec.getPosition().getX(), 0.001);
		assertEquals(.5*-IShape.PPC, rec.getPosition().getY(), 0.001);
		assertEquals(35.*IShape.PPC, rec.getWidth(), 0.001);
		assertEquals(50.*IShape.PPC, rec.getHeight(), 0.001);
		assertTrue(PSTParser.errorLogs().isEmpty());
	}


	@Test
	public void test2CoordinatesFloat() throws ParseException {
		IRectangle rec = (IRectangle)parser.parsePSTCode("\\"+getCommandName()+"(10.5,20.5)(35.5,50.5)").get().getShapeAt(0);
		assertEquals(10.5*IShape.PPC, rec.getPosition().getX(), 0.001);
		assertEquals(20.5*-IShape.PPC, rec.getPosition().getY(), 0.001);
		assertEquals(25.*IShape.PPC, rec.getWidth(), 0.001);
		assertEquals(30.*IShape.PPC, rec.getHeight(), 0.001);
		assertTrue(PSTParser.errorLogs().isEmpty());
	}


	@Test
	public void test2CoordinatesTwoFirstMissing() throws ParseException {
		IRectangle rec = (IRectangle)parser.parsePSTCode("\\"+getCommandName()+"(,)(35,50)").get().getShapeAt(0);
		assertEquals(PSTricksConstants.DEFAULT_VALUE_MISSING_COORDINATE*IShape.PPC, rec.getPosition().getX(), 0.001);
		assertEquals(PSTricksConstants.DEFAULT_VALUE_MISSING_COORDINATE*-IShape.PPC, rec.getPosition().getY(), 0.001);
		assertEquals(34.*IShape.PPC, rec.getWidth(), 0.001);
		assertEquals(49.*IShape.PPC, rec.getHeight(), 0.001);
		assertTrue(PSTParser.errorLogs().isEmpty());
	}


	@Test
	public void test2CoordinatesTwoLastMissing() throws ParseException {
		IRectangle rec = (IRectangle)parser.parsePSTCode("\\"+getCommandName()+"(0,0)(,)").get().getShapeAt(0);
		assertEquals(0., rec.getPosition().getX(), 0.001);
		assertEquals(0., rec.getPosition().getY(), 0.001);
		assertEquals(IShape.PPC, rec.getWidth(), 0.001);
		assertEquals(IShape.PPC, rec.getHeight(), 0.001);
		assertTrue(PSTParser.errorLogs().isEmpty());
	}
}