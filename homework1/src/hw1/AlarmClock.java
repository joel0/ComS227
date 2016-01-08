package hw1;

/**
 * An 24-hour alarm clock with one alarm and a 9-minute snooze feature.
 * @author jmay
 *
 */
public class AlarmClock {
	/**
	 * The number of minutes that snooze will do. Measured from the current time, not the time of the alarm.
	 */
	public static final int SNOOZE_MINUTES = 9;
	/**
	 * The number of minutes in a single day. This number is used so the clock will not obtain values greater than 23:59.
	 */
	public static final int MINUTES_PER_DAY = 1440;
	
	/**
	 * The number of minutes past midnight.
	 */
	private int currentMinutes;
	/**
	 * The number of minutes past midnight for the alarm.
	 */
	private int alarmMinutes;
	/**
	 * The number of new minutes past midnight for an alarm that has been snoozed.
	 */
	private int effectiveAlarmMinutes;
	/**
	 * This keeps track of whether the effectiveAlarmMinutes will turn the ringer on when the current time passes it.
	 */
	private boolean alarmOn;
	/**
	 * This keeps track of whether the ringer is currently ringing.
	 */
	private boolean alarmRinging;
	
	/**
	 * Constructs an alarm clock with an initial time at 0:00 and an alarm at 1:00. The alarm is set to off.
	 */
	public AlarmClock() {
		this(0, 0);
	}
	
	/**
	 * Constructs an alarm clock with an initial time as specified and an alarm at 1:00. The alarm is set to off.
	 * @param hours
	 * Hours for the time
	 * @param minutes
	 * Minutes for the time
	 */
	public AlarmClock(int hours, int minutes) {
		setTime(hours, minutes);
		setAlarmTime(1, 0);
		//currentMinutes = MyTime.addHoursAndMinutes(hours, minutes);
		//alarmMinutes = 60;
		//effectiveAlarmMinutes = alarmMinutes;
		alarmOn = false;
		alarmRinging = false;
	}
	
	
	/**
	 * Advances the clock time by the specified number of minutes. If the updated clock time passes or equals
	 * the effective alarm time, and the alarm is set, then the clock should go into ringing mode. If snooze is
	 * not in effect, "effective alarm time" is just the alarm time as currently set. If snooze is in effect,
	 * the "effective alarm time" is 9 minutes after the clock time at which the snooze() method was last called.
	 * If the clock goes into ringing mode, the snooze timer is canceled. The argument must be a non-negative
	 * number but may be arbitrarily large.
	 * @param minutes
	 * Number of minutes to advance time
	 */
	public void advanceTime(int minutes) {
		int newMinutes = MyTime.addMinutes(currentMinutes, minutes);
		if (alarmOn) {
			// If the minutes advanced is equal to or greater than a day, then the alarm time has passed.
			// MyTime.isBetween does not deal with times longer than one day.
			if (minutes >= MINUTES_PER_DAY) {
				alarmRinging = true;
			} 
			else {
				if (MyTime.isBetween(currentMinutes, newMinutes, effectiveAlarmMinutes)) {
					alarmRinging = true;
				}
			}
		}
		currentMinutes = newMinutes;
	}
	
	/**
	 * Advances the clock time by the given hours and minutes. If the updated clock time passes or equals the
	 * effective alarm time, and the alarm is set, then the clock should go into ringing mode. If snooze is not
	 * in effect, "effective alarm time" is just the alarm time as currently set. If snooze is in effect, the
	 * "effective alarm time" is 9 minutes after the clock time at which the snooze() method was called. The
	 * arguments must be non-negative numbers but may be arbitrarily large.
	 */
	public void advanceTime(int hours, int minutes) {
		advanceTime(hours * 60 + minutes);
	}
	
	/**
	 * Turns off the alarm, stops it from ringing (if it is ringing) and cancels snooze if it is in effect.
	 */
	public void alarmOff() {
		alarmOn = false;
		alarmRinging = false;
		effectiveAlarmMinutes = alarmMinutes;
	}
	
	/**
	 * Turns the alarm on. This method does not cause the alarm to start ringing regardless of current clock
	 * time and alarm time. (If clock time and alarm time are equal, it will start ringing after time is advanced by 24 hours.)
	 */
	public void alarmOn() {
		alarmOn = true;
	}
	
	/**
	 * Returns the current alarm time as the number of minutes past midnight. This value is always between 0 and 1439, inclusive.
	 * @return
	 * Number of minutes past midnight for the alarm time
	 */
	public int getAlarmTime() {
		return alarmMinutes;
	}
	
	/**
	 * Returns the current alarm time as a string of the form hh:mm. The hours value hh is between 0 and 23,
	 * inclusive, and the minutes value mm is between 0 and 59, inclusive.
	 * @return
	 */
	public String getAlarmTimeAsString() {
		return String.format("%02d:%02d", alarmMinutes / 60, alarmMinutes % 60);
	}
	
	/**
	 * Returns the current clock time as the number of minutes past midnight. This value is always between 0 and 1439, inclusive.
	 * @return
	 * Number of minutes past midnight for the clock time
	 */
	public int getClockTime() {
		return currentMinutes;
	}
	
	/**
	 * Returns the current clock time as a string of the form hh:mm. The hours value hh is between 0 and 23,
	 * inclusive, and the minutes value mm is between 0 and 59, inclusive.
	 * @return
	 * Clock time in string form
	 */
	public String getClockTimeAsString() {
		return String.format("%02d:%02d", currentMinutes / 60, currentMinutes % 60);
	}
	
	/**
	 * Returns the effective alarm time as the number of minutes past midnight. If snooze is not in effect,
	 * this value is the same as the current alarm time. This value is always between 0 and 1439, inclusive.
	 * @return
	 * Number of minutes past midnight for the effective alarm time
	 */
	public int getEffectiveAlarmTime() {
		return effectiveAlarmMinutes;
	}
	
	/**
	 * Returns the effective alarm time as a string of the form hh:mm. The hours value hh is between 0 and 23,
	 * inclusive, and the minutes value mm is between 0 and 59, inclusive.
	 * @return
	 * Effective alarm time in string form
	 */
	public String getEffectiveAlarmTimeAsString() {
		return String.format("%02d:%02d", effectiveAlarmMinutes / 60, effectiveAlarmMinutes % 60);
	}
	
	/**
	 * Determines whether the clock is currently ringing.
	 * @return
	 * true if the clock is currently ringing.
	 */
	public boolean isRinging() {
		return alarmRinging;
	}
	
	/**
	 * Sets the alarm time to the given hours and minutes. The hours must be in the range [0, 23] and minutes
	 * must be in the range [0, 59]. This method will not cause the clock to start ringing, and will not cause
	 * it to stop ringing if it is already in the ringing state. If snooze is in effect, it is canceled by this method.
	 * @param hours
	 * Hour for alarm time
	 * @param minutes
	 * Minutes for alarm time
	 */
	public void setAlarmTime(int hours, int minutes) {
		alarmMinutes = MyTime.addHoursAndMinutes(hours, minutes);
		effectiveAlarmMinutes = alarmMinutes;
	}
	
	/**
	 * Sets the current clock time to the given hours and minutes. The hours must be in the range [0, 23] and
	 * minutes must be in the range [0, 59]. This method will not cause the clock to start ringing, even if the
	 * alarm is currently set, and will not cause it to stop ringing if it is already in the ringing state. If
	 * snooze is in effect, it is canceled by this method.
	 * @param hours
	 * Hour for current time
	 * @param minutes
	 * Minutes for current time
	 */
	public void setTime(int hours, int minutes) {
		currentMinutes = MyTime.addHoursAndMinutes(hours, minutes);
		effectiveAlarmMinutes = alarmMinutes;
	}
	
	/**
	 * Stops the clock from ringing and sets the effective alarm time for SNOOZE_MINUTES minutes after the
	 * current clock time. Does not turn off the alarm or change the alarm time. Does nothing if the alarm is not currently ringing.
	 */
	public void snooze() {
		if (alarmRinging) {
			alarmRinging = false;
			effectiveAlarmMinutes = MyTime.addMinutes(currentMinutes, SNOOZE_MINUTES);
		}
	}
}
