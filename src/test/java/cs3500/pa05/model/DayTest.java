package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * represents tests for the Day class
 */
class DayTest {

  private Day day;
  private TimeStamp time;
  private Task task;
  private Event event;

  /**
   * setup day for tests
   */
  @BeforeEach
  void setup() {
    day = new Day(DayOfWeek.FRIDAY);
    time = new TimeStamp(5, 10);
    task = new Task("taskOne");
    event = new Event("eventName", time, time);
  }

  /**
   * Test constructors
   */
  @Test
  void testConstructors() {
    assertDoesNotThrow(() -> day = new Day(DayOfWeek.FRIDAY));
    assertDoesNotThrow(() -> day = new Day(DayOfWeek.MONDAY,
        new ArrayList<Task>(), new ArrayList<Event>()));
  }

  /**
   * tests the getDayOfWeek method
   */
  @Test
  void testGetDayOfWeek() {
    assertEquals(DayOfWeek.FRIDAY, day.getDayOfWeek());
  }

  /**
   * tests the addTask method
   */
  @Test
  void testAddTask() {
    assertEquals(0, day.getTasks().size());
    day.addTask(task);
    assertEquals(1, day.getTasks().size());
  }

  /**
   * tests the addEvent method
   */
  @Test
  void testAddEvent() {
    assertEquals(0, day.getEvents().size());
    day.addEvent(event);
    assertEquals(1, day.getEvents().size());
  }

  /**
   * tests the fullName method
   */
  @Test
  void testFullName() {
    assertEquals("Friday", day.fullName());
  }

  /**
   * tests the abbreviatedName method
   */
  @Test
  void testAbbreviatedName() {
    assertEquals("Fri", day.abbreviatedName());
  }

  /**
   * tests the remove method
   */
  @Test
  void testRemove() {
    day.addEvent(event);
    day.addTask(task);
    assertEquals(1, day.getTasks().size());
    assertEquals(1, day.getEvents().size());

    day.remove(event);
    assertEquals(1, day.getTasks().size());
    assertEquals(0, day.getEvents().size());

    day.remove(task);
    assertEquals(0, day.getTasks().size());
    assertEquals(0, day.getEvents().size());
  }

  /**
   * tests the checkOverMaxEvents method
   */
  @Test
  void testCheckOverMaxEvents() {
    day.addEvent(event);
    assertFalse(day.checkOverMaxEvents(2));
    assertTrue(day.checkOverMaxEvents(0));
  }

  /**
   * tests the checkOverMaxTasks method
   */
  @Test
  void testCheckOverMaxTasks() {
    day.addTask(task);
    assertFalse(day.checkOverMaxTasks(2));
    assertTrue(day.checkOverMaxTasks(0));
  }

  /**
   * Test set tasks method
   */
  @Test
  void testSetTasks() {
    assertTrue(day.getTasks().isEmpty());
    List<Task> tasks = new ArrayList<>();
    tasks.add(task);
    day.setTasks(tasks);
    assertEquals(task, day.getTasks().get(0));
  }

  /**
   * Test set events method
   */
  @Test
  void testSetEvents() {
    assertTrue(day.getEvents().isEmpty());
    List<Event> events = new ArrayList<>();
    events.add(event);
    day.setEvents(events);
    assertEquals(event, day.getEvents().get(0));
  }
}