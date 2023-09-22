package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a time of the day or a time duration
 */
public class TimeStamp {
  private int hours;
  private int minutes;

  /**
   * Constructs a TimeStamp
   *
   * @param hours   hour value in range [0 ,24]
   * @param minutes minute value in range [0, 60]
   */
  public TimeStamp(
      @JsonProperty("hours") int hours,
      @JsonProperty("minutes") int minutes) {
    if (hours < 0 || hours > 24) {
      throw new IllegalArgumentException("hours must be between 0 and 24");
    } else if (minutes < 0 || minutes > 60) {
      throw new IllegalArgumentException("minutes must be between 0 and 60");
    } else {
      this.hours = hours;
      this.minutes = minutes;
    }
  }

  /**
   * converts TimeStamp to string
   *
   * @return string representation of the time stamp
   */
  @Override
  public String toString() {
    String hour = (hours < 10) ? "0" + hours : String.valueOf(hours);
    String minute = (minutes < 10) ? "0" + minutes : String.valueOf(minutes);
    return hour + ":" + minute;
  }

  /**
   * Converts the given string into a TimeStamp
   *
   * @param timeString String to convert
   *                   Must be in the format %d:%d
   * @return A new TimeStamp object from the string
   */
  public static TimeStamp fromString(String timeString) {
    int colonIndex = timeString.indexOf(":");
    try {
      int hours = Integer.parseInt(timeString.substring(0, colonIndex));
      int minutes = Integer.parseInt(timeString.substring(colonIndex + 1));
      return new TimeStamp(hours, minutes);
    } catch (NumberFormatException | IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Invalid time string: " + timeString);
    }

  }

  /**
   * Get the hour value for this timeStamp
   *
   * @return hour value
   */
  public int getHours() {
    return hours;
  }

  /**
   * Get the minute value for this timeStamp
   *
   * @return minute value
   */
  public int getMinutes() {
    return minutes;
  }

  /**
   * Add another TimeStamp to this one
   *
   * @param duration other TimeStamp to add
   * @return new TimeStamp with the added values
   * @throws IllegalArgumentException if output TimeStamp would overflow into new day
   */
  public TimeStamp add(TimeStamp duration) throws IllegalArgumentException {
    return new TimeStamp(hours + duration.getHours(), minutes + duration.getMinutes());
  }
}
