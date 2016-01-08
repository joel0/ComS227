package hw1;

public class CopyOfAlarmClockTest {

	public static void main(String[] args) {
		

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
	}

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
		return result;
	}

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
		return result;
	}

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
		return result;
	}
}
