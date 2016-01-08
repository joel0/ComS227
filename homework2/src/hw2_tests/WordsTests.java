package hw2_tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.junit.*;

import static org.junit.Assert.*;
import hw2.Words;

public class WordsTests {
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
