package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Represents a week
 */
public class Week {
  private String name;
  private String path;
  private LinkedHashMap<DayOfWeek, Day> days;
  private int maxEventsPerDay;
  private int maxTasksPerDay;

  /**
   * Instantiates a week object
   */
  public Week() {
    this.name = "";
    this.path = "";
    this.maxEventsPerDay = Integer.MAX_VALUE;
    this.maxTasksPerDay = Integer.MAX_VALUE;
    this.days = new LinkedHashMap<>();
    for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
      this.days.put(dayOfWeek, new Day(dayOfWeek));
    }
  }

  /**
   * Constructor for a week object for json format
   *
   * @param name            week name
   * @param path            week path
   * @param maxEventsPerDay max events per day
   * @param maxTasksPerDay  max tasks per day
   * @param days            days with their day of the week
   */
  public Week(@JsonProperty("week-name") String name,
              @JsonProperty("path-name") String path,
              @JsonProperty("max-events") int maxEventsPerDay,
              @JsonProperty("max-tasks") int maxTasksPerDay,
              @JsonProperty("days") LinkedHashMap<DayOfWeek, Day> days) {
    this.name = name;
    this.path = path;
    this.maxEventsPerDay = maxEventsPerDay;
    this.maxTasksPerDay = maxTasksPerDay;
    this.days = days;
  }

  /**
   * Clear out everything scheduled for this week
   */
  public void clear() {
    this.days = new LinkedHashMap<>();
    for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
      this.days.put(dayOfWeek, new Day(dayOfWeek));
    }
  }

  /**
   * gets the name of the week
   *
   * @return week name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Get this week's path
   *
   * @return path
   */
  public String getPath() {
    return this.path;
  }

  /**
   * Set this week's path
   *
   * @param path path to set
   */
  public void setPath(String path) {
    this.path = path;
  }

  /**
   * gets the max events per day
   *
   * @return max events per day
   */
  public int getMaxEventsPerDay() {
    return this.maxEventsPerDay;
  }

  /**
   * gets the max tasks per day
   *
   * @return max tasks per day
   */
  public int getMaxTasksPerDay() {
    return this.maxTasksPerDay;
  }

  /**
   * gets the days in this week and their associated week day
   *
   * @return week days
   */
  public LinkedHashMap<DayOfWeek, Day> getDays() {
    return this.days;
  }

  /**
   * set the days in this week and their associated week day
   *
   * @param days days
   */
  public void setDays(LinkedHashMap<DayOfWeek, Day> days) {
    this.days = days;
  }

  /**
   * Get a specific day object from this week
   *
   * @param dayOfWeek day of the week to retrieve
   * @return day object from this week
   */
  public Day getDay(DayOfWeek dayOfWeek) {
    return this.days.get(dayOfWeek);
  }

  /**
   * Sets the name for this week
   *
   * @param name name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Set the maximum events for any given day this week
   *
   * @param maxEventsPerDay numebr of events to set as max
   */
  public void setMaxEvents(int maxEventsPerDay) {
    this.maxEventsPerDay = maxEventsPerDay;
  }

  /**
   * Set the maximum tasks for any given day this week
   *
   * @param maxTasksPerDay number of tasks to set as max
   */
  public void setMaxTasks(int maxTasksPerDay) {
    this.maxTasksPerDay = maxTasksPerDay;
  }



  /**
   * Returns all tasks scheduled for this week
   * Shallow copies of task lists, so changes to task status can be made elsewhere
   *
   * @return list of tasks scheduled for this week
   */
  public List<Task> findAllTasks() {
    List<Task> tasks = new ArrayList<>();
    for (Day day : this.days.values()) {
      tasks.addAll(day.getTasks());
    }
    return tasks;
  }

  /**
   * Removes the given calendar item from this week
   *
   * @param task item to remove
   */
  public void remove(Task task) {
    for (Day day : this.days.values()) {
      day.remove(task);
    }
  }

  /**
   * Removes the given calendar item from this week
   *
   * @param event item to remove
   */
  public void remove(Event event) {
    for (Day day : this.days.values()) {
      day.remove(event);
    }
  }
}
