package tests;

import static org.junit.Assert.*;
import hw3.IPiece;
import hw3.api.Position;
import hw3.impl.Block;

import java.awt.Color;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class IPieceTest {
	private IPiece d;
	private Block[] blocks;
	private Color[] colors;

	@Before
	public void setUp() throws Exception {
		colors = new Color[3];
		colors[0] = Color.RED;
		colors[1] = Color.BLUE;
		colors[2] = Color.ORANGE;
		d = new IPiece(new Position(2, 5), colors);
	}
	
	
	// Test state of the piece after construction

	@Test
	public void testConstruction() {
		blocks = d.getBlocks();
		String expected = "[[R, (2, 5)], [B, (3, 5)], [O, (4, 5)]]";
		assertEquals(expected, Arrays.toString(blocks));
		expected = "(2, 5)";
		assertEquals(expected, d.getPosition().toString());
	}
	
	// Test IllegalArgumentException for bad Color[]
		@Test
		public void testIllegalArgumentException() {
			colors = new Color[2];
			colors[0] = Color.RED;
			colors[1] = Color.BLUE;
			d = new IPiece(new Position(2, 5), colors);
		}
	
	
	// Test transform()
	
	@Test
	public void testTransform() {
		d.transform();
		String expected = "(2, 5)";
		assertEquals(expected, d.getPosition().toString());
		blocks = d.getBlocks();
		expected = "[[R, (2, 5)], [B, (3, 5)], [O, (4, 5)]]";
		assertEquals(expected, Arrays.toString(blocks));
		
		d.transform();
		expected = "(2, 5)";
		assertEquals(expected, d.getPosition().toString());
		blocks = d.getBlocks();
		expected = "[[R, (2, 5)], [B, (3, 5)], [O, (4, 5)]]";
		assertEquals(expected, Arrays.toString(blocks));
	}
	
	
	// Test shifting methods
	
	@Test
	public void testShiftDown() {
		d.shiftDown();
		d.shiftDown();
		String expected = "(4, 5)";
		assertEquals(expected, d.getPosition().toString());
	}
	
	@Test
	public void testShiftLeft() {
		d.shiftLeft();
		String expected = "(2, 4)";
		assertEquals(expected, d.getPosition().toString());
		d.shiftLeft();
		expected = "(2, 3)";
		assertEquals(expected, d.getPosition().toString());
	}
	
	@Test
	public void testShiftRight() {
		d.shiftRight();
		String expected = "(2, 6)";
		assertEquals(expected, d.getPosition().toString());
		d.shiftRight();
		d.shiftRight();
		d.shiftRight();
		expected = "(2, 9)";
		assertEquals(expected, d.getPosition().toString());
	}
	
	@Test
	public void testShiftingBlocks() {
		d.shiftDown();
		d.shiftLeft();
		d.shiftRight();
		d.shiftRight();
		d.shiftRight();
		blocks = d.getBlocks();
		String expected = "[[R, (3, 7)], [B, (4, 7)], [O, (5, 7)]]";
		assertEquals(expected, Arrays.toString(blocks));
	}
	
	
	// Test Initializing Position
	
	@Test
	public void testInitializingPosition() {
		d.initializePosition(new Position(5, 5));
		String expected = "(5, 5)";
		assertEquals(expected, d.getPosition().toString());
		blocks = d.getBlocks();
		expected = "[[R, (5, 5)], [B, (6, 5)], [O, (7, 5)]]";
		assertEquals(expected, Arrays.toString(blocks));
	}
	
	
	// Test Cycling
	
	@Test
	public void testCycling() {
		d.cycle();
		blocks = d.getBlocks();
		String expected = "[[O, (2, 5)], [R, (3, 5)], [B, (4, 5)]]";
		assertEquals(expected, Arrays.toString(blocks));
	}
	
	@Test
	public void testCycling2() {
		d.cycle();
		d.cycle();
		d.cycle();
		d.cycle();
		d.cycle();
		blocks = d.getBlocks();
		String expected = "[[B, (2, 5)], [O, (3, 5)], [R, (4, 5)]]";
		assertEquals(expected, Arrays.toString(blocks));
	}
}