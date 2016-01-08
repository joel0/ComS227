/**
 * @author Teddy Reinert
 * 
 */

package hw2_tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hw2.WordPair;

import java.util.Random;


public class TeddyUnit_Assignment2 {

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
	
	wp = new WordPair("TENtS", "SEnTS");
	assertEquals("getScrambledWord() returns uppercase", "SENTS", wp.getScrambledWord());
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
	public void WordPair_Movement() {
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
	}
}
