package hw1;

/**
 * JoelUnit (JUnit) Tests for Assignment 1 CS 227 Iowa State University Spring 2015
 * @author Joel May and Matthew Burket
 *
 */
public class AlarmClockTest {
	static int totalTests = 0;
	static int failedTests = 0;

	public static void main(String[] args) {
		AlarmClock aa = new AlarmClock(5, 30);
		aa.setAlarmTime(5, 45);
		aa.alarmOn();
		aa.advanceTime(15);
		aa.advanceTime(23, 45);
		aa.alarmOff();
		aa.advanceTime(15);
		System.out.println(aa.isRinging());
		
		AlarmClock a = new AlarmClock(5, 0);
		// New clock has an alarm time of 01:00
		evaluate("new clock alarm is 01:00", a.getAlarmTimeAsString(), "01:00");
		evaluate("effective alarm is also 01:00", a.getEffectiveAlarmTimeAsString(), "01:00");
		// Alarm = 5:15
		a.setAlarmTime(5, 15);
		evaluate("alarm set to 05:15", a.getAlarmTimeAsString(), "05:15");
		// Alarm on
		a.alarmOn();
		// Advance 30 mins
		a.advanceTime(30);
		// 5:30?
		evaluate("time advanced 30 mins", a.getClockTimeAsString(), "05:30");
		evaluate("time in minutes", a.getClockTime(), 330);
		// Ringing? (yes)
		evaluate("alarm time passed", a.isRinging(), true);
		// Advance 5 mins
		a.advanceTime(5);
		// Snooze
		a.snooze();
		// Ringing? (no)
		evaluate("snoozed", a.isRinging(), false);
		// Advance 5 mins
		a.advanceTime(5);
		// 5:40?
		evaluate("advanced 5 mins", a.getClockTimeAsString(), "05:40");
		evaluate("time in mins", a.getClockTime(), 340);
		// Ringing? (no)
		evaluate("alarm not passed", a.isRinging(), false);
		// Advance 4 mins
		a.advanceTime(4);
		// 5:44?
		evaluate("advanced 4 mins", a.getClockTimeAsString(), "05:44");
		evaluate("time in mins", a.getClockTime(), 344);
		// Ringing? (yes)
		evaluate("alarm passed", a.isRinging(), true);
		// Advance 10 mins
		a.advanceTime(10);
		// Snooze
		a.snooze();
		// 5:54?
		evaluate("advanced 10 mins and snoozed", a.getClockTimeAsString(), "05:54");
		evaluate("time in mins", a.getClockTime(), 354);
		// Advance 30 mins
		a.advanceTime(30);
		// 6:24?
		evaluate("advanced 30 mins", a.getClockTimeAsString(), "06:24");
		evaluate("time in mins", a.getClockTime(), 384);
		// Ringing? (yes)
		evaluate("alarm passed", a.isRinging(), true);
		// Turn off alarm
		a.alarmOff();
		// Ringing? (no)
		evaluate("alarm turned off", a.isRinging(), false);
		// Turn alarm on
		a.alarmOn();
		// Advance 22 hours and 51 minutes
		a.advanceTime(22, 51);
		// 5:15?
		evaluate("alarm on, 22hr 51min advanced", a.getClockTimeAsString(), "05:15");
		evaluate("time in mins", a.getClockTime(), 315);
		// Ringing? (yes)
		evaluate("alarm passed", a.isRinging(), true);
		// Advance Time 24-hours should be zero minutes
		a.setTime(0, 0);
		a.advanceTime(24, 0);
		evaluate("Advance Time 24-hours should be zero minutes", a.getClockTime(), 0);
		displayResults();
	}
	
	/**
	 * This Method evaluates two strings. If they are the same then test pass, if not test fails.
	 * @param description
	 * 	Plain-text description
	 * @param value
	 * 	The value to bested
	 * @param expected
	 * 	The expected false
	 * @return
	 * 	true if the test passes
	 */
	private static boolean evaluate(String description, String value, String expected) {
		boolean result;
		if (!value.equals(expected)) {
			System.out.println("Test failed: " + description);
			System.out.println(value);
			System.out.println(expected + " expected");
			result = false;
		} else {
			System.out.println(String.format("Test passed: %s - %s - %s expected", description, value, expected));
			result = true;
		}
		System.out.println();
		resultAdd(result);
		return result;
	}
	
	/**
	 * This Method evaluates two ints. If they are the same then test pass, if not test fails.
	 * @param description
	 * 	Plain-text description
	 * @param value
	 * 	The value to bested
	 * @param expected
	 * 	The expected false
	 * @return
	 * 	true if the test passes
	 */
	private static boolean evaluate(String description, int value, int expected) {
		boolean result;
		if (value != expected) {
			System.out.println("Test failed: " + description);
			System.out.println(value);
			System.out.println(expected + " expected");
			result = false;
		} else {
			System.out.println(String.format("Test passed: %s - %d - %d expected", description, value, expected));
			result = true;
		}
		System.out.println();
		resultAdd(result);
		return result;
	}

	/**
	 * This Method evaluates two booleans. If they are the same then test pass, if not test fails.
	 * @param description
	 * 	Plain-text description
	 * @param value
	 * 	The value to bested
	 * @param expected
	 * 	The expected false
	 * @return
	 * 	true if the test passes
	 */
	private static boolean evaluate(String description, boolean value, boolean expected) {
		boolean result;
		if (value != expected) {
			System.out.println("Test failed: " + description);
			System.out.println(value);
			System.out.println(expected + " expected");
			result = false;
		} else {
			System.out.println(String.format("Test passed: %s - %b - %b expected", description, value, expected));
			result = true;
		}
		System.out.println();
		resultAdd(result);
		return result;
	}
	
	/**
	 * This method adds up the total tests and failures
	 * @param passed
	 * 	A boolean value stating if the test passed
	 */
	private static void resultAdd(boolean passed) {
		totalTests += 1;
		if (!passed) {
			failedTests += 1;
		}
	}
	
	/**
	 * The method used for displaying results at the end of the tests
	 */
	private static void displayResults() { 
		System.out.println(totalTests + " " 
							+ pluralize(totalTests, "assertion") + ", " 
							+ failedTests + " " +  
							pluralize(failedTests, "failure") 
							+ ".");
	}
	
	/**
	 * This is a simple pluralize method. Given a count and the singular form of the word adds an s to end based on count.
	 * Doesn't take speical cases into account.
	 * @param count
	 * 	Number of objects associated with the word
	 * @param singularForm
	 * 	The singular form of the word
	 * @return
	 * 	The proper form of the word
	 */
	private static String pluralize(int count, String singularForm) {
		if (count == 1) {
			return singularForm;
		}
		else {
			return singularForm + "s";
		}
	}
}