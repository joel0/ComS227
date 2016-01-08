/**
 * @author Joel May
 * Copyright 2015
 */

package hw2_tests;

import hw2.ScoreCalculator;
import hw2.WordPair;
import hw2.WordScrambler;
import hw2.Words;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.junit.Test;

import util.PermutationGenerator;
import static org.junit.Assert.*;

public class JoelUnit_Assignment2_v3 {

	@Test
	public void WordScrambler() {
		Random rand = new Random(1337);
		PermutationGenerator perm = new PermutationGenerator(rand);
		assertEquals("ABC with seed 1337", "CAB", WordScrambler.scramble("ABC", perm));

		rand = new Random(1338);
		perm = new PermutationGenerator(rand);
		assertEquals("ABC with seed 1338", "BAC", WordScrambler.scramble("ABC", perm));

		rand = new Random(1338);
		perm = new PermutationGenerator(rand);
		assertEquals("testthis with first two constant and seed of 1338", "TEtissht", WordScrambler.scramble("TEstthis", 2, perm));
	}

	@Test
	public void Words() throws FileNotFoundException {
		Random rand = new Random(1337);
		Words w = new Words("words.txt");
		assertEquals("words.txt random", "bereft", w.getWord(rand));
	}

	@Test
	public void WordPair() {
		WordPair wp = new WordPair("test", "nope");
		assertFalse("Is 'nope' a scrambled 'test'?", wp.isSolutionPossible());

		wp = new WordPair("test", "testtest");
		assertFalse("Is 'testtest' a scrambled 'test'?", wp.isSolutionPossible());

		wp = new WordPair("testtest", "test");
		assertFalse("Is 'test' a scrambled 'test'?", wp.isSolutionPossible());

		wp = new WordPair("test", "tset");
		assertTrue("Is 'test' a scrambled 'test'?", wp.isSolutionPossible());

		wp = new WordPair("test", "TEst");
		assertTrue("Is 'TEst' a scrambled 'test'?", wp.isSolutionPossible());

		wp = new WordPair("TEst", "test");
		assertTrue("Is 'test' a scrabled 'TEst'?", wp.isSolutionPossible());

		wp = new WordPair("test", "tset");
		assertEquals("getScrambledWord() returns uppercase", "TSET", wp.getScrambledWord());
		assertEquals("getRealWord() returns uppercase", "TEST", wp.getRealWord());

		wp.moveRight(0, -1);
		assertEquals("moveRight(0, -1)", "TSET", wp.getScrambledWord());
		wp.moveRight(0, 0);
		assertEquals("moveRight(0, 0)", "TSET", wp.getScrambledWord());
		wp.moveRight(0, 1);
		assertEquals("moveRight(0, 1)", "STET", wp.getScrambledWord());
		wp.moveRight(0, 2);
		assertEquals("moveRight(0, 2)", "TEST", wp.getScrambledWord());
		wp.moveRight(0, 3);
		assertEquals("moveRight(0, 3)", "ESTT", wp.getScrambledWord());
		wp.moveRight(0, 4);
		assertEquals("moveRight(0, 4) (too far)", "ESTT", wp.getScrambledWord());
		wp.moveRight(10, 2);
		assertEquals("moveRight(10, 2) (index out of bounds)", "ESTT", wp.getScrambledWord());
		wp.moveRight(10, -8);
		assertEquals("moveRight(10, -8) (index out of bounds)", "ESTT", wp.getScrambledWord());
		wp.moveRight(-1, 2);
		assertEquals("moveRight(-1, 2) (index out of bounds)", "ESTT", wp.getScrambledWord());

		assertFalse("Wrong answer", wp.checkSolution());
		assertTrue("Correct answer 'test'", wp.checkSolution("test"));
		assertTrue("Correct answer 'teST' (ignoring case)", wp.checkSolution("teST"));

		wp.moveLeft(2, -1);
		assertEquals("moveLeft(2, -1)", "ESTT", wp.getScrambledWord());
		wp.moveLeft(2, 0);
		assertEquals("moveLeft(2, 0)", "ESTT", wp.getScrambledWord());
		wp.moveLeft(2, 1);
		assertEquals("moveLeft(2, 1)", "ETST", wp.getScrambledWord());
		wp.moveLeft(2, 2);
		assertEquals("moveLeft(2, 2)", "SETT", wp.getScrambledWord());
		wp.moveLeft(2, 3);
		assertEquals("moveLeft(2, 3) (too far)", "SETT", wp.getScrambledWord());
		wp.moveLeft(10, 2);
		assertEquals("moveLeft(10, 2) (index out of bounds)", "SETT", wp.getScrambledWord());
		wp.moveLeft(10, -8);
		assertEquals("moveLeft(10, -8) (index out of bounds)", "SETT", wp.getScrambledWord());
		wp.moveLeft(-1, 2);
		assertEquals("moveLeft(-1, 2) (index out of bounds)", "SETT", wp.getScrambledWord());

		assertEquals("No hints should be 0", 0, wp.getNumLetterHints());
		wp.doLetterHint();
		assertEquals("One hint", 1, wp.getNumLetterHints());
		assertEquals("Hint 1", "TEST", wp.getScrambledWord());
		wp.moveRight(0, 1);
		assertEquals("Move character revealed by hint", "TEST", wp.getScrambledWord());
		wp.moveLeft(2, 2);
		assertEquals("Move character to location revealed by hint", "TEST", wp.getScrambledWord());
		wp.moveRight(1, 2);
		assertEquals("Move character from next to one revealed by hint", "TSTE", wp.getScrambledWord());
		wp.moveLeft(3, 2);
		assertEquals("Move character to next to one revealed by hint", "TEST", wp.getScrambledWord());
		assertTrue("Correct answer", wp.checkSolution());
		wp.moveRight(2, 1); // "TETS"

		assertEquals("True word should not have been changed", "TEST", wp.getRealWord());

		wp.doLetterHint();
		assertEquals("2 hints", 2, wp.getNumLetterHints());
		assertEquals("Hint 2 (already in place)", "TETS", wp.getScrambledWord());
		wp.doLetterHint();
		wp.doLetterHint();
		wp.doLetterHint();
		assertEquals("One too many hints should be ignored", "TEST", wp.getScrambledWord());
		assertEquals("One too many hints should be ignored", 4, wp.getNumLetterHints());

		wp = new WordPair("aardvark", "dvrakara");
		wp.doLetterHint();
		assertEquals("Example from JavaDoc on doLetterHint()", "AVRDKARA", wp.getScrambledWord());
		wp.doLetterHint();
		assertEquals("Example from JavaDoc on doLetterHint()", "AARDKVRA", wp.getScrambledWord());
		wp.doLetterHint();
		assertEquals("Example from JavaDoc on doLetterHint()", "AARDKVRA", wp.getScrambledWord());

		PermutationGenerator pg = new PermutationGenerator(new Random(1337));
		wp = new WordPair("aardvark", "dvrakara");
		wp.rescramble(pg);
		assertEquals("Rescramble with no hints", "RKVARADA", wp.getScrambledWord());
		wp.doLetterHint();
		wp.rescramble(pg);
		assertEquals("Rescramble with 1 hint", "AAADRRVK", wp.getScrambledWord());
		wp.doLetterHint();
		wp.doLetterHint();
		wp.doLetterHint();
		wp.doLetterHint();
		wp.doLetterHint();
		wp.doLetterHint();
		wp.doLetterHint();
		wp.rescramble(pg);
		assertEquals("Rescrable with all hints", "AARDVARK", wp.getScrambledWord());
	}

	@Test
	public void WordPairMoveWithNegatives1() {
		WordPair wp = new WordPair("test", "nope");
		wp.moveLeft(0, -1);
		assertEquals("Negative howFar in moveLeft should do nothing", "NOPE", wp.getScrambledWord());
	}

	@Test
	public void WordPairMoveWithNegatives2() {
		WordPair wp = new WordPair("test", "nope");
		wp.moveLeft(1, -1);
		assertEquals("Negative howFar in moveLeft should do nothing", "NOPE", wp.getScrambledWord());
	}

	@Test
	public void WordPairMoveWithNegatives3() {
		WordPair wp = new WordPair("test", "nope");
		wp.moveLeft(-1, 2);
		assertEquals("Negative index in moveLeft should do nothing", "NOPE", wp.getScrambledWord());
	}

	@Test
	public void WordPairMoveWithNegatives4() {
		WordPair wp = new WordPair("test", "nope");
		wp.moveRight(3, -1);
		assertEquals("Negative howFar in moveRight should do nothing", "NOPE", wp.getScrambledWord());
	}

	@Test
	public void WordPairMoveWithNegatives5() {
		WordPair wp = new WordPair("test", "nope");
		wp.moveRight(1, -1);
		assertEquals("Negative howFar in moveRight should do nothing", "NOPE", wp.getScrambledWord());
	}

	@Test
	public void WordPairMoveWithNegatives6() {
		WordPair wp = new WordPair("test", "nope");
		wp.moveRight(-1, 0);
		assertEquals("Negative index in moveRight should do nothing", "NOPE", wp.getScrambledWord());
	}

	@Test
	public void ScoreCalculator() {
		ScoreCalculator sc = new ScoreCalculator(100000, 200, 400, 300);

		sc.start(5);
		assertEquals("5 letter word with 100000 points per letter", 500000, sc.getPossibleScore(0));
		assertEquals("5 letter word at 100000 points per letter after 10 seconds", 500000 - 10000, sc.getPossibleScore(10000));
		sc.applyHintPenalty();
		assertEquals("5 letter word at 100000 points per letter with 1 hint at 200 points after 0 seconds", 500000 - 200, sc.getPossibleScore(0));
		assertEquals("5 letter word at 100000 points per letter with 1 hint at 200 points after 10 seconds", 500000 - 200 - 10000, sc.getPossibleScore(10000));
		sc.applyHintPenalty();
		assertEquals("5 letter word at 100000 points per letter with 2 hints at 200 points after 0 seconds", 500000 - 400, sc.getPossibleScore(0));
		assertEquals("5 letter word at 100000 points per letter with 2 hints at 200 points after 10 seconds", 500000 - 400 - 10000, sc.getPossibleScore(10000));

		sc.applyIncorrectGuessPenalty();
		assertEquals("5 letter word at 100000 points per letter with 2 hints at 200 points and 1 incorrect guess at 300 points after 0 seconds", 500000 - 400 - 300, sc.getPossibleScore(0));
		assertEquals("5 letter word at 100000 points per letter with 2 hints at 200 points and 1 incorrect guess at 300 points after 10 seconds", 500000 - 400 - 300 - 10000,
				sc.getPossibleScore(10000));
		sc.applyIncorrectGuessPenalty();
		assertEquals("5 letter word at 100000 points per letter with 2 hints at 200 points and 2 incorrect guesses at 300 points after 0 seconds", 500000 - 400 - 600, sc.getPossibleScore(0));
		assertEquals("5 letter word at 100000 points per letter with 2 hints at 200 points and 2 incorrect guesses at 300 points after 10 seconds", 500000 - 400 - 600 - 10000,
				sc.getPossibleScore(10000));

		sc.applyRescramblePenalty();
		assertEquals("5 letter word at 100000 points per letter with 2 hints at 200 points, 2 incorrect guesses at 300 points, and 1 rescramble at 400 points after 0 seconds",
				500000 - 400 - 600 - 400, sc.getPossibleScore(0));
		assertEquals("5 letter word at 100000 points per letter with 2 hints at 200 points, 2 incorrect guesses at 300 points, and 1 rescramble at 400 points after 10 seconds", 500000 - 400 - 600
				- 400 - 10000, sc.getPossibleScore(10000));
	}

	@Test
	public void ScoreCalculatorBelowZero() {
		ScoreCalculator sc = new ScoreCalculator(100, 200, 400, 300);

		sc.start(5);
		assertEquals("5 letter word at 100 points per letter after 10 seconds", 0, sc.getPossibleScore(10000));
		assertEquals("5 letter word at 100 points per letter after 0.499 seconds", 1, sc.getPossibleScore(499));
		assertEquals("5 letter word at 100 points per letter after 0.5 seconds", 0, sc.getPossibleScore(500));
		assertEquals("5 letter word at 100 points per letter after 0.501 seconds", 0, sc.getPossibleScore(501));
		sc.applyHintPenalty();
		assertEquals("5 letter word at 100 points per letter with 1 hint at 200 points after 10 seconds", 0, sc.getPossibleScore(10000));
		sc.applyHintPenalty();
		assertEquals("5 letter word at 100 points per letter with 2 hints at 200 points after 10 seconds", 0, sc.getPossibleScore(10000));

		sc.applyIncorrectGuessPenalty();
		assertEquals("5 letter word at 100 points per letter with 2 hints at 200 points and 1 incorrect guess at 300 points after 0 seconds", 0, sc.getPossibleScore(0));
		assertEquals("5 letter word at 100 points per letter with 2 hints at 200 points and 1 incorrect guess at 300 points after 10 seconds", 0, sc.getPossibleScore(10000));
		sc.applyIncorrectGuessPenalty();
		assertEquals("5 letter word at 100 points per letter with 2 hints at 200 points and 2 incorrect guesses at 300 points after 0 seconds", 0, sc.getPossibleScore(0));
		assertEquals("5 letter word at 100 points per letter with 2 hints at 200 points and 2 incorrect guesses at 300 points after 10 seconds", 0, sc.getPossibleScore(10000));

		sc.applyRescramblePenalty();
		assertEquals("5 letter word at 100 points per letter with 2 hints at 200 points, 2 incorrect guesses at 300 points, and 1 rescramble at 400 points after 0 seconds", 0, sc.getPossibleScore(0));
		assertEquals("5 letter word at 100 points per letter with 2 hints at 200 points, 2 incorrect guesses at 300 points, and 1 rescramble at 400 points after 10 seconds", 0,
				sc.getPossibleScore(10000));
	}

	@Test
	public void WordsIndexBounds() throws FileNotFoundException {
		Random rand = new Random(42);
		Words w = new Words("words2.txt");
		File f = new File("words2.txt");
		Scanner s = new Scanner(f);
		ArrayList<String> words = new ArrayList<>();
		while (s.hasNextLine()) {
			words.add(s.nextLine());
		}
		s.close();

		// Since we are desiring a random number generator to give us every value, we will simply
		// repeat it as many times as an int will allow. It's not guaranteed to produce every value,
		// but very likely.
		for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++) {
			words.remove(w.getWord(rand));
			if (words.isEmpty()) {
				// All the words in the file have occurred from getWord
				break;
			}
		}

		assertTrue("getWord should return every word from words2.txt", words.isEmpty());
	}
}
