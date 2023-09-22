package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * represents test for the Task class
 */
class TaskTest {

  /**
   * tests the getName method
   */
  @Test
  void testGetName() {
    Task task = new Task("name", false);
    assertEquals("name", task.getName());
    CalendarItem taskOther = new Task("other");
    assertEquals("other", taskOther.getName());
  }

  /**
   * Tests the set name method
   */
  @Test
  void testSetName() {
    Task task = new Task("before");
    assertEquals("before", task.getName());
    task.setName("after");
    assertEquals("after", task.getName());
  }

  /**
   * tests the getDescription method
   */
  @Test
  void testGetDescription() {
    CalendarItem task = new Task("other");
    task.setDescription("info");
    assertEquals("info", task.getDescription());
  }

  /**
   * tests the getCompleted and setCompleted method
   */
  @Test
  void getCompleted() {
    Task task = new Task("name");
    assertFalse(task.getCompleted());
    task.toggleCompleted();
    assertTrue(task.getCompleted());
    task.toggleCompleted();
    assertFalse(task.getCompleted());
  }

}