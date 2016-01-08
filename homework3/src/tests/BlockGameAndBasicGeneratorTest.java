package tests;

import hw3.*;
import static org.junit.Assert.assertEquals;
import hw3.api.IPolyomino;
import hw3.api.Position;
import hw3.example.SampleGenerator;
import hw3.impl.AbstractBlockGame;
import hw3.impl.Block;
import hw3.impl.GridCell;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
/**
 * A set of JUnit tests for BlockGame and BasicGenerator
 * @author Peter Thedens
 *
 */
public class BlockGameAndBasicGeneratorTest {
	private BlockGame bg;
	private BlockGame bg2;
	private GridCell[][] gc;
	private GridCell[][] gc2;
	private ArrayList<Position> cells;
	//private static final String myColors = "YMGYRGYROBOOGOMGMRYBBMGOYCRMBOYCOOBOBBYOMGYCBBOR";
	/*
	 * Change to true to print tests to console
	 */
	private static final boolean PRINT_GRID = false;
	
	@Before
	public void setUp() throws Exception {
		bg = new BlockGame(new SampleGenerator());
		gc = bg.getGrid();
		cells = new ArrayList<Position>();
		bg2 = new BlockGame(new SampleGenerator(), new Random (23));
		gc2 = bg2.getGrid();
	}

	@Test
	public void emptyBoard() {
		printGrid(gc, "Empty Board Test");
		for (int i = 0; i < gc.length; i++)
			for (int j = 0; j < gc[0].length; j++)
				assertEquals(null, gc[i][j]);
		assertEquals("score should be 0",0, bg.determineScore());
	}

	@Test
	public void Board() {
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < gc[0].length; j++) {
				gc[i][j] = new GridCell(AbstractBlockGame.COLORS[0]);
				cells.add(new Position(i, j));
			}
		}
		printGrid(gc, "Board Test 1");
		ArrayList<Position> yourList = bg.determineCellsToCollapse();
		for (Position x : yourList) {
			assertEquals(x.toString() + " should not be collapsed", true,
					cells.contains(x));
		}
		for (Position x : cells) {
			assertEquals(x.toString() + " should be collapsed", true,
					yourList.contains(x));
		}
		assertEquals("score should be 12",12, bg.determineScore());
	}

	@Test
	public void Board2() {
		for (int i = 0; i < 2; i++) {
			for (int j = 5; j < gc[0].length; j++) {
				gc[i][j] = new GridCell(AbstractBlockGame.COLORS[0]);
				cells.add(new Position(i, j));
			}
		}
		printGrid(gc, "Board Test 2");
		ArrayList<Position> yourList = bg.determineCellsToCollapse();
		for (Position x : yourList) {
			assertEquals(x.toString() + " should not be collapsed", true,
					cells.contains(x));
		}
		for (Position x : cells) {
			assertEquals(x.toString() + " should be collapsed", true,
					yourList.contains(x));
		}
		assertEquals("score should be 14",14, bg.determineScore());
	}

	@Test
	public void Board3() {
		gc[4][4] = new GridCell(AbstractBlockGame.COLORS[0]);
		gc[5][4] = new GridCell(AbstractBlockGame.COLORS[0]);
		gc[6][4] = new GridCell(AbstractBlockGame.COLORS[1]);
		gc[7][4] = new GridCell(AbstractBlockGame.COLORS[0]);
		gc[4][3] = new GridCell(AbstractBlockGame.COLORS[3]);
		printGrid(gc, "Board Test 4");
		ArrayList<Position> yourList = bg.determineCellsToCollapse();
		for (Position x : yourList) {
			assertEquals(x.toString() + " should not be collapsed", true,
					cells.contains(x));
		}
		for (Position x : cells) {
			assertEquals(x.toString() + " should be collapsed", true,
					yourList.contains(x));
		}
		assertEquals("score should be 0",0, bg.determineScore());
	}

	@Test
	public void Board4() {
		gc[0][0] = new GridCell(AbstractBlockGame.COLORS[1]);
		gc[0][1] = new GridCell(AbstractBlockGame.COLORS[0]);
		gc[0][2] = new GridCell(AbstractBlockGame.COLORS[0]);
		gc[1][0] = new GridCell(AbstractBlockGame.COLORS[0]);
		gc[1][1] = new GridCell(AbstractBlockGame.COLORS[1]);
		gc[1][2] = new GridCell(AbstractBlockGame.COLORS[1]);
		gc[2][0] = new GridCell(AbstractBlockGame.COLORS[0]);
		gc[2][3] = new GridCell(AbstractBlockGame.COLORS[1]);
		printGrid(gc, "Board Test 5");
		ArrayList<Position> yourList = bg.determineCellsToCollapse();
		for (Position x : yourList) {
			assertEquals(x.toString() + " should not be collapsed", true,
					cells.contains(x));
		}
		for (Position x : cells) {
			assertEquals(x.toString() + " should be collapsed", true,
					yourList.contains(x));
		}
		assertEquals("score should be 0", 0, bg.determineScore());
	}

	@Test
	public void Board5() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				if ((j == 0) || (i != 1 && j == 1) || (i == 2 && j == 2)) {
					gc[i][j] = new GridCell(AbstractBlockGame.COLORS[2]);
					cells.add(new Position(i, j));
				} else if ((i == 0 && j > 1) || (i == 1 && j == 3)) {
					gc[i][j] = new GridCell(AbstractBlockGame.COLORS[4]);
					cells.add(new Position(i, j));
				} else
					gc[i][j] = new GridCell(AbstractBlockGame.COLORS[3]);
			}
		}
		printGrid(gc, "Board Test 5");
		ArrayList<Position> yourList = bg.determineCellsToCollapse();
		for (Position x : yourList) {
			assertEquals(x.toString() + " should not be collapsed", true,
					cells.contains(x));

		}
		for (Position x : cells) {
			assertEquals(x.toString() + " should be collapsed", true,
					yourList.contains(x));
		}
		assertEquals("score should be 9", 9, bg.determineScore());
	}

	@Test
	public void Board6() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				if ((j == 1 && i < 3) || (i == 2 && j == 2)) {
					gc[i][j] = new GridCell(AbstractBlockGame.COLORS[0]);
					cells.add(new Position(i, j));
					}
				else if ((i == 1 && j == 0) || (j == 0 && i == 2) || (j == 1 && i == 3))
					gc[i][j] = new GridCell(AbstractBlockGame.COLORS[1]);
				else
					gc[i][j] = new GridCell(AbstractBlockGame.COLORS[2]);
			}
		}
		printGrid(gc, "Board Test 6");
		ArrayList<Position> yourList = bg.determineCellsToCollapse();
		for (Position x : yourList) {
			assertEquals(x.toString() + " should not be collapsed", true,
					cells.contains(x));
		}
		for (Position x : cells) {
			assertEquals(x.toString() + " should be collapsed", true,
					yourList.contains(x));
		}
		yourList.removeAll(cells);
		assertEquals(
				"list should only record positions of red (collapsing) blocks",
				true, yourList.isEmpty());
		assertEquals("score should be 4", 4, bg.determineScore());
	}
	
	@Test
	public void checkerBoard() {
		printGrid(gc2, "Checkerboard test");
		GridCell[] saved = new GridCell[48];
		int k=0;
		for(int i=0;i<gc2.length;i++) {
			for(int j=0;j<gc2[0].length;j++) {
				if(i<16||(j+i)%2==1)
					assertEquals(null, gc2[i][j]);
				else {
					assertEquals(false,gc2[i][j].equals(null));
					saved[k] = gc2[i][j];
					k++;
				}
					
			}
		}
		BlockGame bg3 = new BlockGame(new SampleGenerator(), new Random(23));
		GridCell[][] test = bg3.getGrid();
		for(int i=0;i<gc2.length;i++) {
			for(int j=0;j<gc2[0].length;j++) {
				if(i<16||(j+i)%2==1)
					assertEquals(null, test[i][j]);
				else 
					assertEquals(true, gc2[i][j].matches(test[i][j]));
			}
		}
		//String colors = "";
//		for(int i=0;i<saved.length;i++) {
//			colors += findShortColorName(saved[i]);
//		}
		//assertEquals("Colors should be"+myColors,myColors, colors); // potentially volatile test, may disregard		
		
	}
	
	@Test
	public void BasicGeneratorTest () {
		BasicGenerator gen = new BasicGenerator(new Random(13));
		ArrayList<IPolyomino> list = new ArrayList<IPolyomino>();
		for(int i=0;i<10000; i++) {
			list.add(gen.getNext());
		}
		int Is=0,Ls=0,Ds = 0;
		for(IPolyomino z: list) {
			if(z.getBlocks().length==4) { //L-piece
				assertEquals("(-2, 5)", z.getPosition().toString());
				Ls++;
			}
			else if(z.getBlocks().length==3) { //I-piece
				assertEquals("(-2, 5)", z.getPosition().toString());
				Is++;
			}
			else if(z.getBlocks().length==2){ //Diagonal piece
				assertEquals("(-1, 5)", z.getPosition().toString());
				Ds++;
			}
		}
		assertEquals(Is+" I-pieces generated, should be about 6000",6000, Is, 6000*.05);
		assertEquals(Ls+" L-pieces generated, should be about 2000",2000, Ls, 2000*.05);
		assertEquals(Ds+" Diagonal pieces generated, should be about 2000",2000, Ds, 2000*.05);
		
		BasicGenerator gen2 = new BasicGenerator(new Random(13));
		ArrayList<IPolyomino> list2 = new ArrayList<IPolyomino>();
		for(int i=0;i<10000; i++) {
			list2.add(gen2.getNext());
		}
		int i=0,l=0,d=0;
		for(IPolyomino a:list2) {
			if(a.getBlocks().length==4) l++;
			if(a.getBlocks().length==3) i++;
			if(a.getBlocks().length==2) d++;
		}
		assertEquals("using same random seed should give same number of diamond pieces",Ds,d);
		assertEquals("using same random seed should give same number of L pieces",Ls,l);
		assertEquals("using same random seed should give same number of I pieces",Is,i);
		int a=0;
		while (a<5000) {
			int c=0, s=0;
			IPolyomino x = list.get(c);
			IPolyomino z = list2.get(s);
			int n=0, m=0;
			while(true) {
				Block r = x.getBlocks()[n++];
				Block t = z.getBlocks()[m++];
				assertEquals("using same random seed should give same color",t.getColorHint(),r.getColorHint());
				a++;
				if(m==z.getBlocks().length) {s++; break;}
				if(n==x.getBlocks().length) {c++; break;}
			}
		}
	}

	public static void printGrid(GridCell[][] grid, String testName) {
		if(!PRINT_GRID) return;
		System.out.printf("%s\n", testName);
		for (int row = 0; row < grid.length; row += 1) {
			for (int col = 0; col < grid[0].length; col += 1) {
				String s = findShortColorName(grid[row][col]);
				System.out.print(s + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static String findShortColorName(GridCell c) {
		if (c == null)
			return "-";
		Color color = c.getColorHint();
		String colorName = "?";

		// look for a color name that is used in abstract block game
		Color[] knownColors = AbstractBlockGame.COLORS;
		String[] knownColorNames = AbstractBlockGame.COLOR_NAMES;

		for (int i = 0; i < knownColors.length; i += 1) {
			if (knownColors[i].equals(color)) {
				colorName = knownColorNames[i];
				break;
			}
		}
		return colorName;
	}
}