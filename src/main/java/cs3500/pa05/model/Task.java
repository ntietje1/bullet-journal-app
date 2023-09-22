package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a task to be done
 */
public class Task extends CalendarItem {
  private boolean completed;

  /**
   * constructor for task
   *
   * @param name name of the task
   */
  public Task(String name) {
    super(name);
    this.completed = false;
  }

  /**
   * constructor for task for json format
   *
   * @param name      name of the task
   * @param completed task status
   */
  public Task(
      @JsonProperty("task-name") String name,
      @JsonProperty("completed") boolean completed) {
    super(name);
    this.completed = completed;
  }

  /**
   * get completed status of task
   *
   * @return task status
   */
  public boolean getCompleted() {
    return this.completed;
  }


  /**
   * Sets the completed state of this task
   *
   */
  public void toggleCompleted() {
    this.completed = !this.completed;
  }
}
