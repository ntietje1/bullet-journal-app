package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * represents tests for the TaskQueue class
 */
class TaskQueueTest {

  /**
   * tests the getTasks method
   */
  @Test
  void testGetTasks() {
    TaskQueue testQueue = new TaskQueue();
    assertEquals(0, testQueue.getTasks().size());
  }

  /**
   * tests addAll method
   */
  @Test
  void testAddAll() {
    List<Task> tasks = new ArrayList<Task>();
    tasks.add(new Task("task"));
    TaskQueue testQueue = new TaskQueue();
    testQueue.addAll(tasks);
    assertEquals(1, testQueue.getTasks().size());
  }

  /**
   * tests the addTask method
   */
  @Test
  void testAddTask() {
    TaskQueue testQueue = new TaskQueue();
    testQueue.addTask(new Task("name"));
    assertEquals(1, testQueue.getTasks().size());
  }

  /**
   * tests the removeTask method
   */
  @Test
  void testRemoveTask() {
    TaskQueue testQueue = new TaskQueue();
    Task task = new Task("name");
    testQueue.addTask(task);
    assertEquals(1, testQueue.getTasks().size());
    testQueue.removeTask(task);
    assertEquals(0, testQueue.getTasks().size());
  }
}