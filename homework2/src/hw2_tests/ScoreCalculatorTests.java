package hw2_tests;

import org.junit.*;

import static org.junit.Assert.*;
import hw2.ScoreCalculator;

public class ScoreCalculatorTests {
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
		String msg = "After a lot of stupid guesses the score should be zero.";
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
		String msg = "After a lot of rescrambles the score should be zero.";
		int prev;
		int current;
		do {
			prev = sc.getPossibleScore(0);
			sc.applyRescramblePenalty();
			current = sc.getPossibleScore(0);
		} while (prev != current && current > -10);
		assertEquals(msg, 0, sc.getPossibleScore(0));
	}

	@Test
	public void zeroLengthWord() {
		String msg = "Max score should be 0 when the word length is zero and one second has passed.";
		sc.start(0);
		assertEquals(msg, 0, sc.getPossibleScore(1000));
	}

	@Test
	public void zeroMillisPerLetter() {
		String msg = "The max score should be zero after one second when the millisPerLetter is zero.";
		sc = new ScoreCalculator(0, 2000, 200, 100);
		sc.start(10);
		assertEquals(msg, 0, sc.getPossibleScore(1000));
	}

	@Test
	public void zeroHintPenalty() {
		String msg = "The score should be 50,000 when the hintPenalty is zero after one hint.";
		sc = new ScoreCalculator(5000, 0, 200, 100);
		sc.start(10);
		sc.applyHintPenalty();
		assertEquals(msg, 0, sc.getPossibleScore(50000));
	}

	@Test
	public void zeroRescramblePenalty() {
		String msg = "The score should be 50,000 when the rescramblePenalty is zero after one rescramble.";
		sc = new ScoreCalculator(5000, 2000, 0, 100);
		sc.start(10);
		sc.applyRescramblePenalty();
		assertEquals(msg, 0, sc.getPossibleScore(50000));
	}

	@Test
	public void zeroIncorrectGuessPenalty() {
		String msg = "The score should be 50,000 when the incorrectGuessPenalty is zero after one incorrect guess.";
		sc = new ScoreCalculator(5000, 2000, 200, 100);
		sc.start(10);
		sc.applyIncorrectGuessPenalty();
		assertEquals(msg, 0, sc.getPossibleScore(50000));
	}
	
	@Test
	public void startTwice() {
		String msg = "Calling start() a second time should reset penalties.";
		sc.start(10);
		sc.applyIncorrectGuessPenalty();
		sc.start(5);
		assertEquals(msg, 25000, sc.getPossibleScore(0));
	}

}