package hw1;

/**
 * I HOPE THIS BEING A SEPARATE CLASS IS THE BEST PRACTICE.  My follow up question on Piazza
 * was marked as "resolved" without any helpful comment (Multiple Classes for Homework).
 * It's frustrating to pay good money for a university class where some instructors won't
 * consider my question seriously because the question is outside the scope of the current assignment.
 * 
 * This class is for handling time manipulations.  Specifically designed to deal with 24 hour day transitions.
 * @author Joel
 *
 */
public class MyTime {
	
	/**
	 * Adds two quantities of minutes of the day (maximum value of 24 hours * 60 minutes = 1 day).
	 * @param mins1
	 * The number of minutes of the day to add.
	 * @param mins2
	 * The number of minutes of the day to add.
	 * @return
	 * The minutes of the day (i.e. fewer than 24 * 60).
	 */
	public static int addMinutes(int mins1, int mins2) {
		// Add the minutes
		int totalMins = mins1 + mins2;
		// Bring negative values above 0 by adding 1 day (24 hours * 60 minutes)
		while (totalMins < 0) {
			totalMins += AlarmClock.MINUTES_PER_DAY;
		}
		return totalMins % AlarmClock.MINUTES_PER_DAY;
	}
	
	/**
	 * Combines hours and minutes to minutes of the day (i.e. anything larger than 24 hours * 60 minutes wraps around to the next day).
	 * @param hours
	 * Hours to add.
	 * @param mins
	 * Minutes to add.
	 * @return
	 * The minutes of the day (i.e. fewer than 24 * 60).
	 */
	public static int addHoursAndMinutes(int hours, int mins) {
		return addMinutes(hours * 60, mins);
	}
	
	/**
	 * Determines if compareMins is between startMins and endMins. This function handles day wrapping when endMins < startMins.
	 * startMins is an exclusive compare (<) while endMins is an inclusive compare (<=).
	 * @param startMins
	 * The start of the minutes to compare. Compared exclisively (i.e. <)
	 * @param endMins
	 * The end of the minutes to compare. Compared inculsively (i.e. <=)
	 * @param compareMins
	 * The minutes to check if it's between startMins and endMins.
	 * @return
	 * true if compareMins is between the other values. false if compareMins is not between the other values.
	 */
	public static boolean isBetween(int startMins, int endMins, int compareMins) {
		if (startMins <= endMins) {
			// No day wropping to worry about.
			if (startMins < compareMins && endMins >= compareMins) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			// Day wrapping. endMins is on the next day.
			if (startMins < compareMins || endMins >= compareMins) {
				return true;
			}
			else {
				return false;
			}
		}
	}
}
