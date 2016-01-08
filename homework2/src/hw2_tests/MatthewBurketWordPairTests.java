package hw2_tests;

import hw2.WordPair;

import java.util.Random;

import org.junit.*;

import static org.junit.Assert.*;
import util.PermutationGenerator;

public class MatthewBurketWordPairTests {
	private WordPair wp;

	@Before
	public void setup() {
		wp = new WordPair("hello", "oelHl");
	}

	@Test
	public void getReal() {
		String msg = "Real word should equal hello";
		assertEquals(msg, "HELLO", wp.getRealWord());
	}

	@Test
	public void getScrambled() {
		String msg = "Scrambled word should equal hello";
		assertEquals(msg, "OELHL", wp.getScrambledWord());
	}

	@Test
	public void checkSolution() {
		String msg = "Scrambled word should equal hello";
		assertEquals(msg, false, wp.checkSolution());
	}

	@Test
	public void checkifSolution() {
		String msg = "The scramled word should be";
		assertEquals(msg, true, wp.isSolutionPossible());
	}

	@Test
	public void checkifSolution2() {
		String msg = "If a word has some extra letters that are part of word it should be valid";
		wp = new WordPair("hello", "oelllhl");
		assertEquals(msg, false, wp.isSolutionPossible());
	}

	@Test
	public void checkifSolution3() {
		String msg = "If a double length String should not be solutuion.";
		wp = new WordPair("hello", "helloHELLO");
		assertEquals(msg, false, wp.isSolutionPossible());
	}
	
	@Test
	public void checkifSolution4() {
		String msg = "Caps shouldn't have any effect on the validilty of a solution";
		wp = new WordPair("HELLO", "hello");
		assertEquals(msg, true, wp.isSolutionPossible());
	}
	
	@Test
	public void checkifSolution5() {
		String msg = "If a double length String should not be solutuion.";
		wp = new WordPair("hello", "qurufru hjfruj");
		assertEquals(msg, false, wp.isSolutionPossible());
	}
	
	@Test
	public void checkifSolution6() {
		String msg = "If a double length String should not be solutuion.";
		wp = new WordPair("hello", "hello ");
		assertEquals(msg, false, wp.isSolutionPossible());
	}

	@Test
	public void moveRight() {
		String msg = "Moveing index 0 one space in hello should result in ehllo";
		wp = new WordPair("HELLO", "HELLO");
		wp.moveRight(0, 1);
		assertEquals(msg, "ehllo".toUpperCase(), wp.getScrambledWord());
	}

	@Test
	public void moveRightOutOfBounds() {
		String msg = "Moving index 0 one space in hello should result in hello";
		wp = new WordPair("HELLO", "HELLO");
		wp.moveRight(0, 10);
		assertEquals(msg, "hello".toUpperCase(), wp.getScrambledWord());
	}

	@Test
	public void moveRightOutOfBounds2() {
		String msg = "Moving index 10 one space in hello should result in hello";
		wp = new WordPair("HELLO", "HELLO");
		wp.moveRight(10, 1);
		assertEquals(msg, "hello".toUpperCase(), wp.getScrambledWord());
	}

	@Test
	public void moveRightOutOfBounds3() {
		String msg = "Moving index 10 -8 spaces hello should result in hello";
		wp = new WordPair("HELLO", "HELLO");
		wp.moveRight(10, -8);
		assertEquals(msg, "hello".toUpperCase(), wp.getScrambledWord());
	}

	@Test
	public void moveRightOutOfBounds4() {
		String msg = "Moving index 10 -8 spaces hello should result in hello";
		wp = new WordPair("HELLO", "HELLO");
		wp.moveRight(-10, 12);
		assertEquals(msg, "hello".toUpperCase(), wp.getScrambledWord());
	}

	@Test
	public void moveLeft() {
		String msg = "Moving index 2, 2 spaces in hello should result in lehlo";
		wp = new WordPair("hello", "HELLO");
		wp.moveLeft(2, 2);
		assertEquals(msg, "lhelo".toUpperCase(), wp.getScrambledWord());
	}

	public void moveLeftOutOfBounds() {
		String msg = "Moving index 0 10 spaces in hello should result in hello";
		wp = new WordPair("hello", "HELLO");
		wp.moveLeft(1, 10);
		assertEquals(msg, "hello".toUpperCase(), wp.getScrambledWord());
	}

	@Test
	public void moveLeftOutOfBounds2() {
		String msg = "Moving index 10 1 space in hello should result in hello";
		wp = new WordPair("hello", "HELLO");
		wp.moveLeft(10, 1);
		assertEquals(msg, "hello".toUpperCase(), wp.getScrambledWord());
	}

	@Test
	public void moveLeftOutOfBounds3() {
		String msg = "Moving index 10 -8 spaces in hello should result in hello";
		wp = new WordPair("hello", "HELLO");
		wp.moveLeft(10, -8);
		assertEquals(msg, "hello".toUpperCase(), wp.getScrambledWord());
	}

	@Test
	public void moveLeftOutOfBounds4() {
		String msg = "Moving index -10 8 spaces in hello should result in hello";
		wp = new WordPair("hello", "HELLO");
		wp.moveLeft(-8, 12);
		assertEquals(msg, "hello".toUpperCase(), wp.getScrambledWord());
	}

	@Test
	public void getNumHits() {
		String msg = "Should be zero on creation of a Word Pair";
		assertEquals(msg, 0, wp.getNumLetterHints());
	}

	@Test
	public void getNumHits2() {
		String msg = "Should be zero on creation of a Word Pair";
		wp.doLetterHint();
		assertEquals(msg, 1, wp.getNumLetterHints());
	}

	@Test
	public void getNumHits3() {
		String msg = "Should be zero on creation of a Word Pair";
		for (int i = -1; i < wp.getRealWord().length(); i++) {
			wp.doLetterHint();
		}
		assertEquals(msg, wp.getRealWord().length(), wp.getNumLetterHints());
	}

	@Test
	public void doLetterHint() {
		String msg = "Letter hint should repalce the first letter";
		wp = new WordPair("steve", "tseev");
		wp.doLetterHint();
		assertEquals(msg, "steev".toUpperCase(), wp.getScrambledWord());
	}

	@Test
	public void doLetterHint2() {
		String msg = "After doing legnth word + 1 hints should be the full word";
		for (int i = -1; i < wp.getRealWord().length(); i++) {
			wp.doLetterHint();
		}
		assertEquals(msg, wp.getRealWord(), wp.getScrambledWord());
	}

	@Test
	public void doLetterHint3() {
		String msg = "Letter hint should repalce the first letter";
		wp = new WordPair("steve", "tSeev");
		wp.doLetterHint();
		assertEquals(msg, "steev".toUpperCase(), wp.getScrambledWord());
	}

	@Test
	public void rescramble() {
		String msg = "Rescramble makes word scrmaled in a new way";
		Random rand = new Random(1337);
		PermutationGenerator gen = new PermutationGenerator(rand);
		wp = new WordPair("steve", "tseve");
		wp.rescramble(gen);
		assertEquals(msg, "VEETS", wp.getScrambledWord());
	}

	@Test
	public void rescrambleWithHint() {
		PermutationGenerator pg = new PermutationGenerator(new Random(1337));
		wp = new WordPair("aardvark", "dvrakara");
		wp.rescramble(pg);
		wp.doLetterHint();
		wp.rescramble(pg);
		assertEquals("Rescramble with 1 hint", "AAADRRVK", wp.getScrambledWord());
	}

	@Test
	public void rescrambleWithTooManyHint() {
		PermutationGenerator pg = new PermutationGenerator(new Random(1337));
		wp = new WordPair("aardvark", "dvrakara");
		wp.rescramble(pg);
		for (int i = -1; i < wp.getRealWord().length(); i++) {
			wp.doLetterHint();
		}
		wp.rescramble(pg);
		assertEquals("Rescramble with all the hints plus one",
				wp.getRealWord(), wp.getScrambledWord());
	}

	@Test
	public void checkSoluionWithGivenStringTrue() {
		assertTrue("Correct solution should return true",
				wp.checkSolution(wp.getRealWord().toLowerCase()));
	}

	@Test
	public void checkSoluionWithGivenStringFalse() {
		assertFalse("Incorrect solution should return false",
				wp.checkSolution(wp.getScrambledWord()));
	}

	@Test
	public void checkSoluionWithGivenStringFalse2() {
		wp = new WordPair("hello", "zzzzzz");
		assertFalse("Incorrect solution should return false",
				wp.checkSolution(wp.getScrambledWord()));
	}

	@Test
	public void checkSoluionWithGivenStringFalse3() {
		wp = new WordPair("hello", "hellop");
		assertFalse("Incorrect solution should return false",
				wp.checkSolution(wp.getScrambledWord()));
	}

	@Test
	public void checkSoluionWithGivenStringFalse4() {
		wp = new WordPair("hello", "hel");
		assertFalse("Incorrect solution should return false",
				wp.checkSolution(wp.getScrambledWord()));
	}
	
	@Test
	public void checkSoluionWithGivenStringFalse5() {
		wp = new WordPair("hello", "hello ");
		assertFalse("Incorrect solution should return false",
				wp.checkSolution(wp.getScrambledWord()));
	}

}
