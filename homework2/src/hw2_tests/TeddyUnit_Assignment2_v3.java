/**
 * @author Teddy Reinert
 * 
 */

package hw2_tests;

import static org.junit.Assert.*;
import hw2.*;

import org.junit.Before;
import org.junit.Test;


public class TeddyUnit_Assignment2_v3 {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void WordPair_SolutionPossible() {
		WordPair wp = new WordPair("test", "test1");
		assertFalse("Is 'test' a scrambled 'test1'?", wp.isSolutionPossible());
		
		wp = new WordPair("Hello", "HelloKitty");
		assertFalse("Is 'Hello' a scrambled 'HelloKitty'?", wp.isSolutionPossible());

		wp = new WordPair("CompSci", "CcIsOmm");
		assertFalse("Is 'CompSci' a scrambled 'CcIsOmm'?", wp.isSolutionPossible());

		wp = new WordPair("TestCar", "RaceCar");
		assertFalse("Is 'TestCar' a scrambled 'Racecar'?", wp.isSolutionPossible());

		wp = new WordPair("test", "TSST");
		assertFalse("Is 'test' a scrambled 'TSST'?", wp.isSolutionPossible());

		wp = new WordPair("Blue", "Green");
		assertFalse("Is 'Blue' a scrambled 'Green'?", wp.isSolutionPossible());
		
		wp = new WordPair("TEST", "test");
		assertTrue("Is 'TEST' a scrabled 'test'?", wp.isSolutionPossible());
		
		wp = new WordPair("BLUE", "blue");
		assertTrue("Is 'BLUE' a scrabled 'blue'?", wp.isSolutionPossible());
		
		wp = new WordPair("BLUE", "beul");
		assertTrue("Is 'BLUE' a scrabled 'beul'?", wp.isSolutionPossible());
		
		wp = new WordPair("PurplE", "Lperup");
		assertTrue("Is 'PurplE' a scrabled 'Lperup'?", wp.isSolutionPossible());
		
		wp = new WordPair("Red", "Der");
		assertTrue("Is 'Red' a scrabled 'Der'?", wp.isSolutionPossible());
		
		wp = new WordPair("Yellow", "WolleY");
		assertTrue("Is 'Yellow' a scrabled 'WolleY'?", wp.isSolutionPossible());
	}
	
	@Test
	public void WordPair_AccessorMethods() {
	WordPair wp = new WordPair("blue", "Bleu");
	
	assertEquals("getScrambledWord() returns uppercase", "BLEU", wp.getScrambledWord());
	assertEquals("getRealWord() returns uppercase", "BLUE", wp.getRealWord());
	
	wp = new WordPair("TENtS", "SEnTT");
	assertEquals("getScrambledWord() returns uppercase", "SENTT", wp.getScrambledWord());
	assertEquals("getRealWord() returns uppercase", "TENTS", wp.getRealWord());
	
	assertEquals("getNumLetterHints() returns 0 to start", 0, wp.getNumLetterHints());
	wp.doLetterHint();
	assertEquals("getNumLetterHints() returns 1 after one hint", 1, wp.getNumLetterHints());
	wp.doLetterHint();
	wp.doLetterHint();
	assertEquals("getNumLetterHints() returns 3 after 3 hints", 3, wp.getNumLetterHints());
	wp.doLetterHint();
	wp.doLetterHint();
	wp.doLetterHint();
	assertEquals("GetNumLetterHints() should not exceed the word length.", 5, wp.getNumLetterHints());
	}
	
	@Test
	public void WordPair_MovementLeft() {
		WordPair wp = new WordPair("Snake", "Ekans");
		assertTrue("Solution should be possible for Real word 'Snake' and scrambled 'Ekans'", wp.isSolutionPossible());
		wp.moveLeft(4,2);
		assertEquals("moveLeft(4,2) on EKANS should result in 'EKSAN'","EKSAN", wp.getScrambledWord());
		wp.moveLeft(2, 2);
		assertEquals("moveLeft(2,2) on EKSAN should result in 'SEKAN'", "SEKAN", wp.getScrambledWord());
		wp.moveLeft(4, 3);
		assertEquals("moveLeft(4,3) on SEKAN should result in 'SNEKA'", "SNEKA", wp.getScrambledWord());
		wp.moveLeft(4, 1);
		assertEquals("moveLeft(4,1) on SNEKA should result in 'SNEAK'", "SNEAK", wp.getScrambledWord());
		wp.moveLeft(3, 1);
		assertEquals("moveLeft(3,1) on SNEAK should result in 'SNAEK'", "SNAEK", wp.getScrambledWord());
		wp.moveLeft(4, 1);
		assertEquals("moveLeft(4,1) on SNAEK should result in 'SNAKE'", "SNAKE", wp.getScrambledWord());
		assertTrue("Scrambled word should be the same as real word", wp.checkSolution());
		assertFalse("checkSlution('ARBOK') should be false, (EKANS has not evolved yet, silly!)", wp.checkSolution("ARBOK"));
		wp.moveLeft(-1, 3);
		assertEquals("moveLeft(-1,3) on SNAKE should result in 'SNAKE' (out of bounds)", "SNAKE", wp.getScrambledWord());
		wp.moveLeft(0,3);
		assertEquals("moveLeft(0,3) on SNAKE should result in 'SNAKE' (out of bounds)", "SNAKE", wp.getScrambledWord());
		wp.moveLeft(0, 0);
		assertEquals("moveLeft(0,3) on SNAKE should result in 'SNAKE' (no movement)", "SNAKE", wp.getScrambledWord());
		wp.moveLeft(7, 2);
		assertEquals("moveLeft(0,3) on SNAKE should result in 'SNAKE' (out of bounds)", "SNAKE", wp.getScrambledWord());
	}
	
	@Test
	public void WordPair_MovementRight() {
		WordPair wp = new WordPair("Snake", "Ekans");
		wp.moveRight(0,2);
		assertEquals("moveRight(0,2) on EKANS should result in 'KAENS'","KAENS", wp.getScrambledWord());
		wp.moveRight(3, 1);
		assertEquals("moveRight(3,1) on KAENS should result in 'KAESN'","KAESN", wp.getScrambledWord());
		wp.moveRight(1, 3);
		assertEquals("moveRight(1,3) on KAESN should result in 'KESNA'","KESNA", wp.getScrambledWord());
		wp.moveRight(0, 4);
		assertEquals("moveRight(0,4) on KESNA should result in 'ESNAK'","ESNAK", wp.getScrambledWord());
		wp.moveRight(0, 4);
		assertEquals("moveRight(0,4) on ESNAK should result in 'SNAKE'","SNAKE", wp.getScrambledWord());
		assertTrue("Scrambled word should be the same as real word", wp.checkSolution());
		wp.moveRight(-1, 3);
		assertEquals("moveRight(-1,3) on SNAKE should result in 'SNAKE' (out of bounds)", "SNAKE", wp.getScrambledWord());
		wp.moveRight(4, 3);
		assertEquals("moveRight(4,3) on SNAKE should result in 'SNAKE' (out of bounds)", "SNAKE", wp.getScrambledWord());
		wp.moveRight(1, 7);
		assertEquals("moveRight(1,7) on SNAKE should result in 'SNAKE' (out of bounds)", "SNAKE", wp.getScrambledWord());
		wp.moveRight(0, 0);
		assertEquals("moveRight(0,0) on SNAKE should result in 'SNAKE' (No Movement)", "SNAKE", wp.getScrambledWord());
	}
	
	/**
	 * This test will test the score, and make sure it does not go below zero, and
	 * test to see if the correct score is given after starting a new game.
	 * 
	 */
	@Test
	public void ScoreCalc(){
		
			// millisPerLetter = 5000 (5 seconds)
			// hint penalty = 2 seconds
			// rescramble penalty = 1/10 of a second
			// incorrect guess penalty = 1 second
		 ScoreCalculator sc = new ScoreCalculator(5000, 2000, 100, 1000);
		 sc.start(10);
		 String msg = "Score after 2 seconds should be 48000";
		 assertEquals(msg, 48000, sc.getPossibleScore(2000));
		 sc.applyHintPenalty(); //-2000
		 
		 msg = "Score after 2 sec, with one Hint Penalty should be 46000";
		 assertEquals(msg, 46000, sc.getPossibleScore(2000));
		 
		 sc.applyHintPenalty(); //-2000
		 sc.applyHintPenalty(); //-2000
		 sc.applyHintPenalty(); //-2000
		 sc.applyHintPenalty(); //-2000
		 sc.applyHintPenalty(); //-2000
		 
		 msg = "Score after 2 sec, with 6 Hint Penalties should be 36000";
		 assertEquals(msg, 36000, sc.getPossibleScore(2000));
		 
		 msg = "Score after 6 sec, with 6 Hint Penalties should be 32000";
		 assertEquals(msg, 32000, sc.getPossibleScore(6000));
		 
		 sc.applyRescramblePenalty(); //-100
		 sc.applyRescramblePenalty(); //-100
		 sc.applyRescramblePenalty(); //-100
		 
		 msg = "Score after 6 sec, with 6 Hint Penalties and 3 rescramble, should be 31700";
		 assertEquals(msg, 31700, sc.getPossibleScore(6000));
		 
		 msg = "Score after 12 sec, with 6 Hint Penalties and 3 rescramble, should be 25700";
		 assertEquals(msg, 25700, sc.getPossibleScore(12000));
		 
		 msg = "Score after 32 sec, with 6 Hint Penalties and 3 rescramble, should be 5700";
		 assertEquals(msg, 5700, sc.getPossibleScore(32000));
		 
		 sc.applyHintPenalty(); //-2000
		 msg = "Score after 32 sec, with 7 Hint Penalties and 3 rescramble, should be 3700";
		 assertEquals(msg, 3700, sc.getPossibleScore(32000));
		 
		 msg = "Score after 35 sec, with 7 Hint Penalties and 3 rescramble, should be 700";
		 assertEquals(msg, 700, sc.getPossibleScore(35000));
		 

		 
		 msg = "Score after 36 sec, with 7 Hint Penalties and 3 rescramble, should be not drop below zero";
		 assertEquals(msg, 0, sc.getPossibleScore(36000));
		 
		 sc.start(6);
		 msg = "After starting a new game, the score should be 30000";
		 assertEquals(msg, 30000, sc.getPossibleScore(0));
		 
		 
		 
	}
}
