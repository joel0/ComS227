package hw2;

import java.util.ArrayList;
import util.PermutationGenerator;

/**
 * A WordPair encapsulates two representations of a given word - its "real" form, which does not
 * change, and a "scrambled" form in which the letters are rearranged. The scrambled form of the
 * word may be updated by calls to the methods <code>moveLeft</code>, <code>moveRight</code>,
 * <code>doLetterHint</code>, and <code>rescramble</code>.
 * 
 * @author Joel May
 */
public class WordPair {
	/**
	 * The correct word.
	 */
	private String realWord;
	/**
	 * The current scramble of the word.
	 */
	private String scrambledWord;
	/**
	 * The number of hints that have been used.
	 */
	private int numHints = 0;

	/**
	 * Constructs an instance of WordPair with the given strings. This constructor does not check
	 * whether the given scrambled word is a valid rearrangement of the given real word, but this
	 * can be checked by a subsequent call to the method <code>isSolutionPossible</code>.
	 * 
	 * @param givenRealWord
	 *            the real word
	 * @param givenScrambledWord
	 *            the word with characters rearranged
	 */
	public WordPair(String givenRealWord, String givenScrambledWord) {
		realWord = givenRealWord;
		scrambledWord = givenScrambledWord;
	}

	/**
	 * Returns the current scrambled form of the word in upper case.
	 * 
	 * @return scrambled form of the word.
	 */
	public String getScrambledWord() {
		return scrambledWord.toUpperCase();
	}

	/**
	 * Returns the real word in upper case.
	 * 
	 * @return the real word
	 */
	public String getRealWord() {
		return realWord.toUpperCase();
	}

	/**
	 * Modifies the scrambled word by moving the character at position <code>index</code> to the
	 * right the given number of spaces. Intervening characters are shifted left. For example, if
	 * the scrambled word is "ABCEDFG", then after moveRight(2, 3) the scrambled word should be
	 * "ABDEFCG". This method should do nothing if the index is out of range, if <code>howFar</code>
	 * is negative, if the index plus <code>howFar</code> is out of range, or if the index or index
	 * minus <code>howFar</code> is less than <code>getNumLetterHints()</code>.
	 * 
	 * @param index
	 *            zero-based index of the character to be moved
	 * @param howFar
	 *            number of spaces to the right to move the character
	 */
	public void moveRight(int index, int howFar) {
		// Do nothing if howFar is negative. (Other bounds are checked in move().)
		if (howFar < 0) {
			return; // Do nothing
		}
		move(index, howFar);
	}

	/**
	 * Modifies the scrambled word by moving the character at position <code>index</code> to the
	 * left the given number of spaces. Intervening characters are shifted right. For example, if
	 * the scrambled word is "ABCEDFG", then after moveLeft(5, 3) the scrambled word should be
	 * "ABFCDEG". This method should do nothing if the index is out of range, if <code>howFar</code>
	 * is negative, if the index minus <code>howFar</code> is out of range, or if the index or index
	 * minus <code>howFar</code> is less than <code>getNumLetterHints()</code>.
	 * 
	 * @param index
	 *            zero-based index of the character to be moved
	 * @param howFar
	 *            number of spaces to the left to move the character
	 */
	public void moveLeft(int index, int howFar) {
		// Do nothing if howFar is negative. (Other bounds are checked in move().)
		if (howFar < 0) {
			return; // Do nothing
		}
		move(index, -howFar);
	}

	/**
	 * Moves the character at <code>index</code> the specified places.
	 * 
	 * @param index
	 *            The desired character to move
	 * @param howFar
	 *            How many places to move the character.
	 */
	private void move(int index, int howFar) {
		// Check bounds
		if (index >= scrambledWord.length() || index < 0 || index + howFar >= scrambledWord.length() || index + howFar < 0) {
			return;
		}
		// Check hint bounds
		if (index < numHints || index + howFar < numHints) {
			return;
		}

		// Convert the scrambledWord to an ArrayList for easy manipulations. This would be better as
		// a
		// StringBuilder, but I didn't read the instruction PDF all the way through before writing
		// the code.
		ArrayList<Byte> scrambledChars = new ArrayList<>();
		for (byte currentByte : scrambledWord.getBytes()) {
			scrambledChars.add(currentByte);
		}

		// Temporarily store the character to move
		byte movingChar = scrambledChars.get(index);
		// Remove the char
		scrambledChars.remove(index);
		// Add the char
		scrambledChars.add(index + howFar, movingChar);

		// Convert the ArrayList back to a string
		scrambledWord = "";
		for (byte currentByte : scrambledChars) {
			scrambledWord += (char) currentByte;
		}
	}

	/**
	 * Returns the number of letters at the beginning of the scrambled word whose positions are
	 * currently fixed by previous calls to <code>letterHint()</code>.
	 * 
	 * @return number of fixed characters at the beginning of the scrambled word
	 */
	public int getNumLetterHints() {
		return numHints;
	}

	/**
	 * Applies a hint by placing a correct character at the position <code>p</code>, where
	 * <code>p = getNumLetterHints()</code> is the number of characters that have already been
	 * placed in the correct position by previous calls to this method. Specifically, this method
	 * searches to the right starting at index <code>p</code> until it finds the first occurrence of
	 * the correct character, and then swaps that character into position <code>p</code>. After this
	 * method has been called n times, the first n letters of the scrambled word are correct and are
	 * not moved again by any subsequent method calls.
	 * <p>
	 * For example: if the real word is "AARDVARK" and the scrambled word is "DVRAKARA", then after
	 * one call to <code>doLetterHint</code>, the scrambled word is "AVRDKARA" (D is swapped with
	 * first A). After two calls to <code>doLetterHint</code>, the scrambled word is "AARDKVRA" (V
	 * is swapped with the second A). After three calls to <code>doLetterHint</code>, the scrambled
	 * word is still "AARDKVRA" (R is swapped with itself). However <code>getNumLetterHints</code>
	 * will now return 3, and the characters at indices 0, 1, and 2 will be not be moved.
	 */
	public void doLetterHint() {
		// Only give a hint if there is a logical hint to give.
		if (!isSolutionPossible() || numHints >= realWord.length()) {
			return;
		}

		// Store the character that is at index numHints, so it can be swapped with the correct
		// character.
		char wrongChar = scrambledWord.toUpperCase().charAt(numHints);
		char rightChar = realWord.toUpperCase().charAt(numHints);
		// Find the first occurrence of the correct character to the right of numHints inclusive.
		// Calling .indexOf() here should be safe (i.e. always nonnegative) without a sanity check.
		// The only time it would be a problem is if the scambled word does not have the hints in
		// place where it should, which should never happen.
		int sourceIndex = scrambledWord.toUpperCase().indexOf(rightChar, numHints);

		// Change the immutable String to a StringBuilder to swap the characters.
		StringBuilder sb = new StringBuilder(scrambledWord);
		// Swap the characters
		sb.setCharAt(numHints, rightChar);
		sb.setCharAt(sourceIndex, wrongChar);
		scrambledWord = sb.toString();

		numHints++;
	}

	/**
	 * Rescrambles the non-fixed letters in the scrambled using the given permutation generator.
	 * Letters to the left of the index <code>getNumLetterHints()</code> are not moved. This method
	 * should update the scrambled word with the value of
	 * 
	 * <pre>
	 * WordScrambler.scramble(getScrambledWord(), getNumLetterHints(), gen);
	 * </pre>
	 * 
	 * @param gen
	 *            permutation generator for scrambling the non-fixed letters
	 */
	public void rescramble(PermutationGenerator gen) {
		scrambledWord = WordScrambler.scramble(scrambledWord, numHints, gen);
	}

	/**
	 * Determines whether the scrambled word is a valid rearrangement of the real word (disregarding
	 * case).
	 * 
	 * @return true if it is possible to rearrange the characters in the scrambled word to obtain
	 *         the real word
	 */
	public boolean isSolutionPossible() {
		// Convert the scrambledWord string to an ArrayList for easy element deletion.
		ArrayList<Byte> scrambledChars = new ArrayList<>();
		for (byte currentByte : scrambledWord.toUpperCase().getBytes()) {
			scrambledChars.add(currentByte);
		}

		// For each character in the realWord, remove it from the scrambledChars.
		// If the character from the realWord does not exist, then the solution is impossible.
		for (byte currentChar : realWord.toUpperCase().getBytes()) {
			if (!scrambledChars.remove((Byte) currentChar)) {
				return false;
			}
		}

		// If there are characters left in scrambledChars, then scrambledChars contained too many
		// characters.
		if (scrambledChars.size() != 0) {
			return false;
		}

		return true;
	}

	/**
	 * Determines whether the current scrambled word is equal to the real word (disregarding case).
	 * 
	 * @return true if scrambled word and real word are the same, false otherwise
	 */
	public boolean checkSolution() {
		return checkSolution(scrambledWord);
	}

	/**
	 * Determines whether the given string is equal to the real word (disregarding case). Does not
	 * modify the scrambled word.
	 * 
	 * @return true if given word and real word are the same, false otherwise
	 */
	public boolean checkSolution(String solution) {
		return realWord.equalsIgnoreCase(solution);
	}
}
