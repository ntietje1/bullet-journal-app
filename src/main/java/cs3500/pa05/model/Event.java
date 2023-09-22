package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a calendar event
 */
public class Event extends CalendarItem {
  private TimeStamp startTime;
  private TimeStamp duration;

  /**
   * Instantiates a new event object
   *
   * @param name      name of the event
   * @param startTime start time of the event
   * @param duration  duration of the event
   */
  public Event(
      @JsonProperty("event-name") String name,
      @JsonProperty("start-time") TimeStamp startTime,
      @JsonProperty("event-duration") TimeStamp duration) {
    super(name);
    this.startTime = startTime;
    this.duration = duration;
  }

  /**
   * gets the start time of the event
   *
   * @return event start time
   */
  public TimeStamp getStartTime() {
    return this.startTime;
  }

  /**
   * Sets the start time of this event to the given value
   *
   * @param startTime desired start time
   */
  public void setStartTime(TimeStamp startTime) {
    this.startTime = startTime;
  }

  /**
   * gets the duration of the event
   *
   * @return event duration
   */
  public TimeStamp getDuration() {
    return this.duration;
  }

  /**
   * Sets the duration of this event to the given value
   *
   * @param duration the desired duration
   */
  public void setDuration(TimeStamp duration) {
    this.duration = duration;
  }
}
