package mini2;

import java.util.Scanner;

/**
 * 
 * @author Joel
 *
 */
public class TallyString {

	/**
	 * Character used for a line in the string representation.
	 */
	public static final char LINE = '|';
	/**
	 * Character used for a star in the string representation.
	 */
	public static final char STAR = '*';
	/**
	 * Value of a STAR character.
	 */
	public static final int STAR_VALUE = 5;

	/**
	 * Returns the value of the given tally group, or -1 if the group is not
	 * valid according to the method isValidGroup.
	 * 
	 * @param group
	 *            the given tally group
	 * @return value of the tally group, or -1 if the group is invalid
	 */
	public static int evaluateGroup(String group) {
		if (!isValidGroup(group)) {
			return -1;
		}

		if (group.equals("0")) {
			return 0;
		}

		int out = 0;
		for (int i = 0; i < group.length(); i++) {
			switch (group.charAt(i)) {
			case LINE:
				out += 1;
				break;
			case STAR:
				out += STAR_VALUE;
				break;
			default:
				System.out.println("Something went wrong with evaluateGroup(String)!");
				break;
			}
		}
		return out;
	}

	/**
	 * Returns the value of the given tally string. The string need not be in
	 * normalized form, but every group must be valid (according to
	 * <code>isValidGroup</code>) and the string must contain at least one
	 * group. If any group is invalid, the method returns -1. For example:
	 * <ul>
	 * <li>For string <code>"*|** || |****"</code>, the method returns 1641.</li>
	 * <li>For string <code>" 0 0  |  0  ************|||| |     "</code>, the
	 * method returns 1641.</li>
	 * <li>For string <code>"     "</code>, the method returns -1 (contains no
	 * tally group).</li>
	 * </ul>
	 * 
	 * @param s
	 *            the given tally string
	 * @return value of the given tally string, or -1 if invalid
	 */
	public static int evaluateString(String s) {
		int out = 0;
		Scanner scanner = new Scanner(s);

		// If the scanner does not have a first group, then the input is
		// invalid.
		if (!scanner.hasNext()) {
			scanner.close();
			return -1;
		}

		while (scanner.hasNext()) {
			out *= 10; // Each new group means we must shift the current value
						// to the left one digit.
			String currentGroup = scanner.next();
			if (!isValidGroup(currentGroup)) {
				scanner.close();
				return -1;
			}

			out += evaluateGroup(currentGroup);
		}
		scanner.close();

		return out;
	}

	/**
	 * Determines whether the given string is a valid tally group. A tally group
	 * is valid if it is either the string "0" or if it contains only the
	 * characters LINE and STAR, in any order. Spaces are not allowed. The group
	 * does <em>not</em> have satisfy the definition for normalized form of a
	 * tally string. For example:
	 * <ul>
	 * <li><code>"||**|*|"</code> is a valid tally group (with value 19).</li>
	 * <li><code>"||* *|*|"</code> is not a valid tally group, because it
	 * contains a space (though it is a valid <em>tally string</em> with value
	 * 82).</li>
	 * <li><code>"**0"</code> is not a valid tally group, because a '0'
	 * character can only occur in a string by itself.</li>
	 * <li><code>"||234"</code> is not a valid tally group, because it contains
	 * characters other than LINE and STAR.</li>
	 * </ul>
	 * 
	 * @param s
	 *            given string
	 * @return true if the string is a valid tally group, false otherwise
	 */
	public static boolean isValidGroup(String s) {
		if (s.equals("0")) {
			return true;
		}

		// The group must not be empty.
		if (s.equals("")) {
			return false;
		}

		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case LINE:
			case STAR:
				// do nothing. These characters are fine.
				break;
			default:
				return false;
			}
		}

		return true;
	}

	/**
	 * Creates a tally group from a given nonnegative integer. Returns a string
	 * with at most four LINE characters, and all LINE characters are to the
	 * right of all STAR characters, if any. (This is similar to the
	 * requirements for a tally group within the normalized form of a tally
	 * string, except that the value is not restricted to the range 0 through
	 * 9.) For example, given the number 37, this method returns the string
	 * "*******||".
	 * 
	 * @param givenValue
	 *            the number to be represented as a tally group
	 * @return group whose value is the given number, or null if the number is
	 *         negative
	 */
	public static String makeGroup(int givenValue) {
		int stars = givenValue / STAR_VALUE;
		int lines = givenValue % STAR_VALUE;

		if (givenValue == 0) {
			return "0";
		}
		if (givenValue < 0) {
			return null;
		}

		return repeatChar(STAR, stars) + repeatChar(LINE, lines);
	}

	/**
	 * Creates a normalized tally string for the given number. For example,
	 * given the number 37, this method returns the string "||| *||". Given the
	 * number 1641, this method returns the string "| *| |||| |".
	 * 
	 * @param givenValue
	 *            the number to be represented as a string
	 * @return tally string in normalized form that has the given value, or null
	 *         if the given value is negative
	 */
	public static String makeNormalizedString(int givenValue) {
		if (givenValue < 0) {
			return null;
		}
		
		int remainingValue = givenValue;
		String out = "";
		do {
			out = makeGroup(remainingValue % 10) + " " + out; // remainingValue % 10 is the
																// rightmost
																// digit.
			remainingValue /= 10; // The rightmost digit has been processed,
									// remove it.
		} while (remainingValue > 0); // While is at the end so a givenValue of
										// 0 returns 0.

		return out.trim(); // There is one extra space character on the end of
							// the string. This removes it.
	}

	/**
	 * Repeats the specified character the specified number of times.
	 * 
	 * @param c
	 *            The character to repeat.
	 * @param times
	 *            The number of times to repeat.
	 * @return A String of the repeated character.
	 */
	private static String repeatChar(char c, int times) {
		String out = "";
		for (int i = 0; i < times; i++) {
			out += c;
		}
		return out;
	}

}
