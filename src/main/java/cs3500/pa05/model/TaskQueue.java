package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks to be completed
 */
public class TaskQueue {
  private List<Task> tasks;

  /**
   * Creates a new TaskQueue
   */
  public TaskQueue() {
    this.tasks = new ArrayList<>();
  }

  /**
   * get all tasks in this queue
   *
   * @return task list
   */
  public List<Task> getTasks() {
    return this.tasks;
  }

  /**
   * Add another task list to be shown in this queue
   *
   * @param taskList list to be added
   */
  public void addAll(List<Task> taskList) {
    this.tasks.addAll(taskList);
  }

  /**
   * Add a task to this queue
   *
   * @param task task to be added
   */
  public void addTask(Task task) {
    this.tasks.add(task);
  }

  /**
   * Remove a task from this queue
   *
   * @param task task to be removed
   */
  public void removeTask(Task task) {
    this.tasks.remove(task);
  }
}
