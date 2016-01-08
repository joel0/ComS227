package tests;

import java.awt.Color;
import java.security.InvalidParameterException;
import java.util.Random;

import hw3.*;
import hw3.api.IPolyomino;
import hw3.api.Position;
import hw3.impl.AbstractBlockGame;
import hw3.impl.Block;

import org.junit.Test;

import static org.junit.Assert.*;

public class JoelUnit {
	
	@Test
	public void InitialGrid() {
		BlockGame game = new BlockGame(new BasicGenerator(new Random(1337)), new Random(1337));
		for (int col = 0; col < AbstractBlockGame.HEIGHT - 8; col++) {
			for (int row = 0; row < AbstractBlockGame.WIDTH; row++) {
				assertNull("The top portion of the grid should be empty with the two parameter contsructor for BlockGame.", game.getGrid()[col][row]);
			}
		}
		for (int col = AbstractBlockGame.HEIGHT - 8; col < AbstractBlockGame.HEIGHT; col++) {
			for (int row = 0; row < AbstractBlockGame.WIDTH; row++) {
				if (((col - (AbstractBlockGame.HEIGHT - 8)) + row) % 2 == 1) {
					assertNull("The lower portion of the grid should have a checkerboard pattern with an empty cell at col:" + col + ", row:" + row, game.getGrid()[col][row]);
				} else {
					assertNotNull("The lower portion of the grid should have a checkerboard pattern with a colored cell at col:" + col + ", row:" + row, game.getGrid()[col][row]);
				}
			}
		}
	}
	
	@Test
	public void EmptyGrid() {
		BlockGame game = new BlockGame(new BasicGenerator(new Random(1337)));
		for (int col = 0; col < AbstractBlockGame.HEIGHT; col++) {
			for (int row = 0; row < AbstractBlockGame.WIDTH; row++) {
				assertNull("The BlockGame constructor with one parameter should create and empty grid.", game.getGrid()[col][row]);
			}
		}
	}
	
	////////////////////////////////

	@Test
	public void CheckColorIShape() {
		IPolyomino poly = new IPiece(new Position(0, 0), new Color[] { Color.CYAN, Color.RED, Color.YELLOW });
		assertEquals("The color on IPiece is wrong.", Color.CYAN, poly.getBlocks()[0].getColorHint());
		assertEquals("The color on IPiece is wrong.", Color.RED, poly.getBlocks()[1].getColorHint());
		assertEquals("The color on IPiece is wrong.", Color.YELLOW, poly.getBlocks()[2].getColorHint());
	}

	@Test
	public void CheckColorDiagonalShape() {
		IPolyomino poly = new DiagonalPiece(new Position(0, 0), new Color[] { Color.CYAN, Color.RED });
		assertEquals("The color on DiagonalPiece is wrong.", Color.CYAN, poly.getBlocks()[0].getColorHint());
		assertEquals("The color on DiagonalPiece is wrong.", Color.RED, poly.getBlocks()[1].getColorHint());
	}

	@Test
	public void CheckColorLShape() {
		IPolyomino poly = new LPiece(new Position(0, 0), new Color[] { Color.CYAN, Color.RED, Color.YELLOW, Color.GREEN });
		assertEquals("The color on LPiece is wrong.", Color.CYAN, poly.getBlocks()[0].getColorHint());
		assertEquals("The color on LPiece is wrong.", Color.RED, poly.getBlocks()[1].getColorHint());
		assertEquals("The color on LPiece is wrong.", Color.YELLOW, poly.getBlocks()[2].getColorHint());
		assertEquals("The color on LPiece is wrong.", Color.GREEN, poly.getBlocks()[3].getColorHint());
	}
	
	///////////////////////////////
	
	@Test
	public void CheckAbsIShape() {
		IPolyomino poly = new IPiece(new Position(5, 10), new Color[] { Color.CYAN, Color.CYAN, Color.CYAN });
		assertEquals("The IShape's absolute shape or position is wrong (or the order of blocks is wrong).", new Position(5, 11), poly.getBlocks()[0].getPosition());
		assertEquals("The IShape's absolute shape or position is wrong (or the order of blocks is wrong).", new Position(6, 11), poly.getBlocks()[1].getPosition());
		assertEquals("The IShape's absolute shape or position is wrong (or the order of blocks is wrong).", new Position(7, 11), poly.getBlocks()[2].getPosition());
	}

	@Test
	public void CheckAbsDiagonalShape() {
		IPolyomino poly = new DiagonalPiece(new Position(5, 10), new Color[] { Color.CYAN, Color.CYAN });
		assertEquals("The DiagonalPiece's absolute shape or position is wrong (or the order of blocks is wrong).", new Position(5, 10), poly.getBlocks()[0].getPosition());
		assertEquals("The DiagonalPiece's absolute shape or position is wrong (or the order of blocks is wrong).", new Position(6, 11), poly.getBlocks()[1].getPosition());
	}

	@Test
	public void CheckAbsLShape() {
		IPolyomino poly = new LPiece(new Position(5, 10), new Color[] { Color.CYAN, Color.CYAN, Color.CYAN, Color.CYAN });
		assertEquals("The LPiece's absolute shape or position is wrong (or the order of blocks is wrong).", new Position(5, 10), poly.getBlocks()[0].getPosition());
		assertEquals("The LPiece's absolute shape or position is wrong (or the order of blocks is wrong).", new Position(5, 11), poly.getBlocks()[1].getPosition());
		assertEquals("The LPiece's absolute shape or position is wrong (or the order of blocks is wrong).", new Position(6, 11), poly.getBlocks()[2].getPosition());
		assertEquals("The LPiece's absolute shape or position is wrong (or the order of blocks is wrong).", new Position(7, 11), poly.getBlocks()[3].getPosition());
	}
	
	////////////////////////////////
	
	@Test
	public void CheckIShape() {
		IPolyomino poly = new IPiece(new Position(0, 0), new Color[] { Color.CYAN, Color.CYAN, Color.CYAN });
		assertEquals("The number of blocks in the IPiece is wrong.", 3, poly.getBlocks().length);
		assertEquals("The IShape's shape is wrong (or the order of blocks is wrong).", new Position(0, 1), poly.getBlocks()[0].getPosition());
		assertEquals("The IShape's shape is wrong (or the order of blocks is wrong).", new Position(1, 1), poly.getBlocks()[1].getPosition());
		assertEquals("The IShape's shape is wrong (or the order of blocks is wrong).", new Position(2, 1), poly.getBlocks()[2].getPosition());
	}

	@Test
	public void CheckDiagonalShape() {
		IPolyomino poly = new DiagonalPiece(new Position(0, 0), new Color[] { Color.CYAN, Color.CYAN });
		assertEquals("The number of blocks in the DiagnoalPiece is wrong.", 2, poly.getBlocks().length);
		assertEquals("The DiagonalPiece's shape is wrong (or the order of blocks is wrong).", new Position(0, 0), poly.getBlocks()[0].getPosition());
		assertEquals("The DiagonalPiece's shape is wrong (or the order of blocks is wrong).", new Position(1, 1), poly.getBlocks()[1].getPosition());
	}

	@Test
	public void CheckLShape() {
		IPolyomino poly = new LPiece(new Position(0, 0), new Color[] { Color.CYAN, Color.CYAN, Color.CYAN, Color.CYAN });
		assertEquals("The number of blocks in the DiagnoalPiece is wrong.", 4, poly.getBlocks().length);
		assertEquals("The LPiece's shape is wrong (or the order of blocks is wrong).", new Position(0, 0), poly.getBlocks()[0].getPosition());
		assertEquals("The LPiece's shape is wrong (or the order of blocks is wrong).", new Position(0, 1), poly.getBlocks()[1].getPosition());
		assertEquals("The LPiece's shape is wrong (or the order of blocks is wrong).", new Position(1, 1), poly.getBlocks()[2].getPosition());
		assertEquals("The LPiece's shape is wrong (or the order of blocks is wrong).", new Position(2, 1), poly.getBlocks()[3].getPosition());
	}
	
	////////////////////////////////

	@Test
	public void BlockStartPositions() throws Exception {
		boolean iFound = false;
		boolean dFound = false;
		boolean lFound = false;
		Random rand = new Random(1337);
		BasicGenerator gen = new BasicGenerator(rand);
		for (int x = 0; x < 100000 && !(iFound && dFound && lFound); x++) {
			IPolyomino poly = gen.getNext();
			if (poly instanceof IPiece) {
				iFound = true;
				assertEquals("The IPiece should have an initial position of (-2, 5).", new Position(-2, 5), poly.getPosition());
			} else if (poly instanceof DiagonalPiece) {
				dFound = true;
				assertEquals("The DiagonalPiece should have an initial position of (-1, 5).", new Position(-1, 5), poly.getPosition());
			} else if (poly instanceof LPiece) {
				lFound = true;
				assertEquals("The LPiece should have an initial position of (-2, 5).", new Position(-2, 5), poly.getPosition());
			} else {
				throw new Exception("Unexpected piece type!");
			}
		}
	}

	@Test
	public void PieceProbability() throws Exception {
		int iCount = 0;
		int dCount = 0;
		int lCount = 0;
		Random rand = new Random(1337);
		BasicGenerator gen = new BasicGenerator(rand);
		for (int x = 0; x < 100000; x++) {
			IPolyomino poly = gen.getNext();
			if (poly instanceof IPiece) {
				iCount++;
			} else if (poly instanceof DiagonalPiece) {
				dCount++;
			} else if (poly instanceof LPiece) {
				lCount++;
			} else {
				throw new Exception("Unexpected piece type!");
			}
		}
		int totalCount = iCount + dCount + lCount;
		double iPercent = (double) iCount / totalCount;
		double dPercent = (double) dCount / totalCount;
		double lPercent = (double) lCount / totalCount;

		assertEquals("Probability for IPiece should be 60%.", 0.6, iPercent, 0.01);
		assertEquals("Probability for DiagonalPiece should be 20%.", 0.2, dPercent, 0.01);
		assertEquals("Probability for LPiece should be 20%.", 0.2, lPercent, 0.01);
	}

	@Test
	public void ColorProbability() {
		int[] colorCounts = new int[AbstractBlockGame.COLORS.length];
		int totalColors = 0;
		Random rand = new Random(1337);
		BasicGenerator gen = new BasicGenerator(rand);
		for (int x = 0; x < 100000; x++) {
			IPolyomino poly = gen.getNext();
			for (Block block : poly.getBlocks()) {
				Color color = block.getColorHint();
				int index = findColorIndex(color);
				colorCounts[index]++;
				totalColors++;
			}
		}

		double desiredProbability = 1.0 / colorCounts.length;
		for (int x = 0; x < colorCounts.length; x++) {
			double probability = (double) colorCounts[x] / totalColors;
			assertEquals("Color " + AbstractBlockGame.COLOR_NAMES[x] + " should have an equal probability as other colors.", desiredProbability, probability, 0.01);
		}
	}

	private int findColorIndex(Color c) {
		for (int x = 0; x < AbstractBlockGame.COLORS.length; x++) {
			if (AbstractBlockGame.COLORS[x].equals(c)) {
				return x;
			}
		}
		throw new InvalidParameterException("The color is not in the array.");
	}
}
