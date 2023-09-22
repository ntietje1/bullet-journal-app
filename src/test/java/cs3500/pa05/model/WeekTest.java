package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashMap;
import org.junit.jupiter.api.Test;

/**
 * represents tests for the week class
 */
class WeekTest {

  /**
   * tests the getName method
   */
  @Test
  void testGetName() {
    Week week = new Week();
    assertEquals("", week.getName());
    week.setName("name");
    assertEquals("name", week.getName());
  }

  /**
   * tests the getMaxEventsPerDay method
   */
  @Test
  void testGetMaxEventsPerDay() {
    Week week = new Week();
    week.setMaxEvents(10);
    assertEquals(10, week.getMaxEventsPerDay());
  }

  /**
   * tests the getMaxTasksPerDay method
   */
  @Test
  void testGetMaxTasksPerDay() {
    Week week = new Week();
    week.setMaxTasks(10);
    assertEquals(10, week.getMaxTasksPerDay());
  }

  /**
   * tests the getDays method
   */
  @Test
  void testGetDays() {
    Week week = new Week();
    for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
      assertTrue(week.getDays().containsKey(dayOfWeek));
    }
  }

  /**
   * test the getDay method
   */
  @Test
  void testGetDay() {
    Week week = new Week();
    assertEquals(DayOfWeek.FRIDAY, week.getDay(DayOfWeek.FRIDAY).getDayOfWeek());
  }

  /**
   * tests the setName method
   */
  @Test
  void testSetName() {
    Week week = new Week();
    assertEquals("", week.getName());
    week.setName("week");
    assertEquals("week", week.getName());
  }

  /**
   * tests the setMaxEvents method
   */
  @Test
  void testSetMaxEvents() {
    Week week = new Week();
    week.setMaxEvents(10);
    assertEquals(10, week.getMaxEventsPerDay());
    week.setMaxEvents(20);
    assertEquals(20, week.getMaxEventsPerDay());
  }

  /**
   * tests the setMaxTasks method
   */
  @Test
  void testSetMaxTasks() {
    Week week = new Week();
    week.setMaxTasks(10);
    assertEquals(10, week.getMaxTasksPerDay());
    week.setMaxTasks(20);
    assertEquals(20, week.getMaxTasksPerDay());
  }

  /**
   * tests the getTasks method
   */
  @Test
  void testGetTasks() {
    Week week = new Week();
    assertEquals(0, week.findAllTasks().size());
    Week weekTwo = new Week("name", "path", 5,
        5, new LinkedHashMap<DayOfWeek, Day>());
    assertEquals(0, weekTwo.findAllTasks().size());
  }

  /**
   * tests the getPath and setPath method
   */
  @Test
  void testGetPathAndSetPath() {
    Week week = new Week();
    assertEquals("", week.getPath());
    week.setPath("file.bujo");
    assertEquals("file.bujo", week.getPath());
  }

  /**
   * tests the remove method
   */
  @Test
  void testRemove() {
    Week week = new Week();
    TimeStamp t = new TimeStamp(5, 10);
    Event e = new Event("eventName", t, t);
    Task task = new Task("taskName");
    week.getDay(DayOfWeek.FRIDAY).addEvent(e);
    week.getDay(DayOfWeek.FRIDAY).addTask(task);
    assertEquals(1, week.getDay(DayOfWeek.FRIDAY).getEvents().size());
    assertEquals(1, week.getDay(DayOfWeek.FRIDAY).getTasks().size());
    week.remove(e);
    assertEquals(0, week.getDay(DayOfWeek.FRIDAY).getEvents().size());
    week.remove(task);
    assertEquals(0, week.getDay(DayOfWeek.FRIDAY).getTasks().size());
  }

  /**
   * tests the clear method
   */
  @Test
  void testClear() {
    Week week = new Week();
    TimeStamp t = new TimeStamp(5, 10);
    Event e = new Event("eventName", t, t);
    week.getDay(DayOfWeek.FRIDAY).addEvent(e);
    Task task = new Task("taskOne");
    week.getDay(DayOfWeek.MONDAY).addTask(task);
    assertEquals(1, week.getDay(DayOfWeek.FRIDAY).getEvents().size());
    assertEquals(1, week.getDay(DayOfWeek.MONDAY).getTasks().size());

    week.clear();
    assertEquals(0, week.getDay(DayOfWeek.FRIDAY).getEvents().size());
    assertEquals(0, week.getDay(DayOfWeek.MONDAY).getTasks().size());
  }
}