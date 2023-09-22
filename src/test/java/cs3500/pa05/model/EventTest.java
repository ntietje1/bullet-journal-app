package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * represents tests for the Event class
 */
class EventTest {

  private final TimeStamp time = new TimeStamp(5, 10);
  private final TimeStamp timeTwo = new TimeStamp(2, 30);

  /**
   * tests the getName method
   */
  @Test
  void getName() {
    Event event = new Event("eventName", time, time);
    assertEquals("eventName", event.getName());
  }

  /**
   * tests the getStartTime method
   */
  @Test
  void getStartTime() {
    Event event = new Event("eventName", time, timeTwo);
    assertEquals(time, event.getStartTime());
  }

  /**
   * tests the getDuration method
   */
  @Test
  void getDuration() {
    Event event = new Event("eventName", time, timeTwo);
    assertEquals(timeTwo, event.getDuration());
  }

  /**
   * tests the setDuration method
   */
  @Test
  void setDuration() {
    Event event = new Event("eventName", time, time);
    assertEquals(time, event.getDuration());
    event.setDuration(timeTwo);
    assertEquals(timeTwo, event.getDuration());
  }

  /**
   * tests the setStartTime method
   */
  @Test
  void setStartTime() {
    Event event = new Event("eventName", time, time);
    assertEquals(time, event.getStartTime());
    event.setStartTime(timeTwo);
    assertEquals(timeTwo, event.getStartTime());
  }
}