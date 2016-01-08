/**
 * @author Joel May and Matthew Burket
 * 
 * Notes:
 * - You will need the provided words.txt in the root of the project
 * - This test class will create a file called WordTestFile.txt in the root of your project
 * 		This file is need to test some "special" cases ;)
 */
package hw2_tests;

import static org.junit.Assert.*;
import hw2.*;

import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import util.PermutationGenerator;

@RunWith(Suite.class)
@Suite.SuiteClasses({ JMUnitV2.ScoreCalculatorTests.class, JMUnitV2.WordPairTests.class, JMUnitV2.WordScramblerTests.class, JMUnitV2.WordsTests.class })
public class JMUnitV2 {
	public static class ScoreCalculatorTests {
		private ScoreCalculator sc;

		@Before
		public void setup() {
			sc = new ScoreCalculator(5000, 2000, 200, 100);
		}

		@Test
		public void starting() {
			sc.start(10);
			String msg = "Given a 10 letter word and no time passed score should be zero";
			assertEquals(msg, 50000, sc.getPossibleScore(0));

		}

		@Test
		public void oneSecondPass() {
			sc.start(10);
			String msg = "Given a 10 letter word and one second passed score should be 49000";
			assertEquals(msg, 50000 - 1000, sc.getPossibleScore(1000));

		}

		@Test
		public void hintPenalty() {
			sc.start(10);
			String msg = "Given a 10 letter word and zero seconds passed score should be 48000 after a hint";
			sc.applyHintPenalty();
			assertEquals(msg, 50000 - 2000, sc.getPossibleScore(0));

		}

		@Test
		public void rescramblePenalty() {
			sc.start(10);
			String msg = "Given a 10 letter word and zero seconds passed score should be 49900 after a rescramble";
			sc.applyRescramblePenalty();
			assertEquals(msg, 50000 - 200, sc.getPossibleScore(0));

		}

		@Test
		public void inncorectGuess() {
			sc.start(10);
			String msg = "Given a 10 letter word and zero seconds passed score should be 49000 after a incorrect guess";
			sc.applyIncorrectGuessPenalty();
			assertEquals(msg, 50000 - 100, sc.getPossibleScore(0));

		}

		@Test
		public void timeOutZeroTest() {
			sc.start(10);
			String msg = "Given a 10 letter word and 30 seconds passed score should be 0.";
			assertEquals(msg, 0, sc.getPossibleScore(300000));

		}

		@Test
		public void timeOutHintZeroTest() {
			sc.start(10);
			String msg = "Given a 10 letter word and 30 seconds passed score should be 0. After a hint.";
			sc.applyHintPenalty();
			assertEquals(msg, 0, sc.getPossibleScore(300000));

		}

		@Test
		public void timeOutGuessZeroTest() {
			sc.start(10);
			String msg = "Given a 10 letter word and 30 seconds passed score should be 0. After an inncorrect guess.";
			sc.applyIncorrectGuessPenalty();
			assertEquals(msg, 0, sc.getPossibleScore(300000));

		}

		@Test
		public void timeOutReScmableZeroTest() {
			sc.start(10);
			String msg = "Given a 10 letter word and 30 seconds passed score should be 0. After an inncorrect guess.";
			sc.applyRescramblePenalty();
			assertEquals(msg, 0, sc.getPossibleScore(300000));

		}

		@Test
		public void hintZero() {
			sc.start(10);
			String msg = "After a lot of hints the score should be zero.";
			int prev;
			int current;
			do {
				prev = sc.getPossibleScore(0);
				sc.applyHintPenalty();
				current = sc.getPossibleScore(0);

			} while (prev != current && current > -10);
			assertEquals(msg, 0, sc.getPossibleScore(0));

		}

		@Test
		public void invalidGuessZero() {
			sc.start(10);
			String msg = "After a lot of hints the score should be zero.";
			int prev;
			int current;
			do {
				prev = sc.getPossibleScore(0);
				sc.applyIncorrectGuessPenalty();
				current = sc.getPossibleScore(0);

			} while (prev != current && current > -10);
			assertEquals(msg, 0, sc.getPossibleScore(0));

		}

		@Test
		public void rescramableZero() {
			sc.start(10);
			String msg = "After a lot of hints the score should be zero.";
			int prev;
			int current;
			do {
				prev = sc.getPossibleScore(0);
				sc.applyRescramblePenalty();
				current = sc.getPossibleScore(0);

			} while (prev != current && current > -10);
			assertEquals(msg, 0, sc.getPossibleScore(0));

		}

	}

	public static class WordPairTests {
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
	        public void moveRightFourLettersEnd() {
	            String msg = "Moveing index 1 one space in java should result in jvaa";
	            wp = new WordPair("java", "java");
	            wp.moveRight(1, 1);
	            assertEquals(msg, "jvaa".toUpperCase(), wp.getScrambledWord());
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
		public void moveRightOutOfBounds5() {
			String msg = "Moving index 1 1 space hello should result in hello after two hints";
			wp = new WordPair("HELLO", "HELLO");
			wp.doLetterHint();
			wp.doLetterHint();
			wp.moveRight(1, 1);
			assertEquals(msg, "hello".toUpperCase(), wp.getScrambledWord());
		}

		@Test
		public void moveLeft() {
			String msg = "Moving index 2, 2 spaces in hello should result in lehlo";
			wp = new WordPair("hello", "HELLO");
			wp.moveLeft(2, 2);
			assertEquals(msg, "lhelo".toUpperCase(), wp.getScrambledWord());
		}

		@Test
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
		public void moveLeftOutOfBounds5() {
			String msg = "Moving index 1 1 space in hello should result in hello after two hints";
			wp = new WordPair("hello", "HELLO");
			wp.doLetterHint();
			wp.doLetterHint();
			wp.moveLeft(1, 1);
			assertEquals(msg, "hello".toUpperCase(), wp.getScrambledWord());
		}

		@Test
		public void moveLeftOutOfBounds6() {
			String msg = "Moving index 1 1 space in hello should result in hello after one hint";
			wp = new WordPair("hello", "HELLO");
			wp.doLetterHint();
			wp.moveLeft(1, 1);
			assertEquals(msg, "hello".toUpperCase(), wp.getScrambledWord());
		}

		@Test
		public void getNumHits() {
			String msg = "Should be zero on creation of a Word Pair";
			assertEquals(msg, 0, wp.getNumLetterHints());
		}

		@Test
		public void getNumHits2() {
			String msg = "Should be one on creation of a Word Pair and after a hint";
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
			assertEquals("Rescramble with all the hints plus one", wp.getRealWord(), wp.getScrambledWord());
		}

		@Test
		public void checkSoluionWithGivenStringTrue() {
			assertTrue("Correct solution should return true", wp.checkSolution(wp.getRealWord().toLowerCase()));
		}

		@Test
		public void checkSoluionWithGivenStringFalse() {
			assertFalse("Incorrect solution should return false", wp.checkSolution(wp.getScrambledWord()));
		}

		@Test
		public void checkSoluionWithGivenStringFalse2() {
			wp = new WordPair("hello", "zzzzzz");
			assertFalse("Incorrect solution should return false", wp.checkSolution(wp.getScrambledWord()));
		}

		@Test
		public void checkSoluionWithGivenStringFalse3() {
			wp = new WordPair("hello", "hellop");
			assertFalse("Incorrect solution should return false", wp.checkSolution(wp.getScrambledWord()));
		}

		@Test
		public void checkSoluionWithGivenStringFalse4() {
			wp = new WordPair("hello", "hel");
			assertFalse("Incorrect solution should return false", wp.checkSolution(wp.getScrambledWord()));
		}

		@Test
		public void checkSoluionWithGivenStringFalse5() {
			wp = new WordPair("hello", "hello ");
			assertFalse("Incorrect solution should return false", wp.checkSolution(wp.getScrambledWord()));
		}

	}

	public static class WordScramblerTests {
		private PermutationGenerator gen;
		private Random rand;

		@Before
		public void setup() {
			rand = new Random(1337);
			gen = new PermutationGenerator(rand);
		}

		@Test
		public void scramble() {
			String msg = "Hello when scrabled should equal lolHe";
			assertEquals(msg, "lolHe", WordScrambler.scramble("Hello", gen));
		}

		@Test
		public void scramble2() {
			String msg = "Hello when scrabled should equal lolHe";
			assertEquals(msg, "Heoll", WordScrambler.scramble("Hello", 2, gen));
		}

		@Test
		public void length() {
			PermutationGenerator pg = new PermutationGenerator();
			String result = WordScrambler.scramble("Hello", pg);
			String msg = "The given word should be length as the scrambled after scamble";
			assertEquals(msg, result.length(), "Hello".length());
		}
	}

	public static class WordsTests {
		private Words w;
		private Random rand;

		@Before
		public void setup() throws FileNotFoundException {
			w = new Words("words.txt");
			rand = new Random(42);
		}

		@Test
		public void WordsWordCoverage() throws FileNotFoundException {
			// Load the words from the file
			File f = new File("words.txt");
			Scanner s = new Scanner(f);
			ArrayList<String> words = new ArrayList<>();
			while (s.hasNextLine()) {
				words.add(s.nextLine());
			}
			s.close();

			// Since we are desiring a random number generator to give us every value, we will
			// simply
			// repeat it as many times as an int will allow. It's not guaranteed to produce every
			// value,
			// but very likely.
			for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++) {
				words.remove(w.getWord(rand));
				if (words.isEmpty()) {
					// All the words in the file have occurred from getWord
					break;
				}
			}

			assertTrue("getWord should return every word from words.txt", words.isEmpty());
		}

		@Test
		public void WordsWithSpaces() throws FileNotFoundException {
			String msg = "Words with spaces should not be split up.";
			// Write the words
			String[] newWords = { "Steve likes 42", "word", "test" };
			File f = new File("WordsTestFile.txt");
			PrintWriter pw = new PrintWriter(f);
			for (String oneWord : newWords) {
				pw.println(oneWord);
			}
			pw.close();

			rand = new Random(1023);
			w = new Words(f.getAbsolutePath());
			assertEquals(msg, "Steve likes 42", w.getWord(rand));

			f.delete();
		}

		@Test
		public void WordsWithWeirdCharacters() throws FileNotFoundException {
			String msg = "Words with funky characters should not be changed.";
			// Write the words
			String[] newWords = { "!\0~@\u0007#$%^&*(){}[]/?-_\\|=+;:`", "word", "test" };
			File f = new File("WordsTestFile.txt");
			PrintWriter pw = new PrintWriter(f);
			for (String oneWord : newWords) {
				pw.println(oneWord);
			}
			pw.close();

			rand = new Random(1023);
			w = new Words(f.getAbsolutePath());
			assertEquals(msg, "!\0~@\u0007#$%^&*(){}[]/?-_\\|=+;:`", w.getWord(rand));

			f.delete();
		}

	}
}
