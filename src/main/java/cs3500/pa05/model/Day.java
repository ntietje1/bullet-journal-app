package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single day in a calendar
 */
public class Day {
  private List<Task> tasks;
  private List<Event> events;
  private DayOfWeek dayOfWeek;

  /**
   * Instantiates a new day object
   *
   * @param dayOfWeek day of week enum to specify which day this is in a week
   */
  public Day(DayOfWeek dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
    this.tasks = new ArrayList<>();
    this.events = new ArrayList<>();
  }

  /**
   * constructor for day object for json format
   *
   * @param dayOfWeek day of week enum to specify which day this is in a week
   * @param tasks     daily tasks
   * @param events    daily events
   */
  public Day(@JsonProperty("week-day") DayOfWeek dayOfWeek,
             @JsonProperty("tasks") ArrayList<Task> tasks,
             @JsonProperty("events") ArrayList<Event> events) {
    this.dayOfWeek = dayOfWeek;
    this.tasks = tasks;
    this.events = events;
  }

  /**
   * gets the day of the week for this day
   *
   * @return the day of the week
   */
  public DayOfWeek getDayOfWeek() {
    return dayOfWeek;
  }

  /**
   * Add a task to this day
   *
   * @param task task to be added
   */
  public void addTask(Task task) {
    this.tasks.add(task);
  }

  /**
   * Add an event to this day
   *
   * @param event event to be added
   */
  public void addEvent(Event event) {
    this.events.add(event);
  }

  /**
   * Remove the given calendar item from this day
   *
   * @param task item to be removed
   */
  public void remove(Task task) {
    this.tasks.remove(task);
  }

  /**
   * Remove the given calendar item from this day
   *
   * @param event item to be removed
   */
  public void remove(Event event) {
    this.events.remove(event);
  }

  /**
   * Get this day's task list
   *
   * @return list of tasks
   */
  @JsonProperty
  public List<Task> getTasks() {
    return this.tasks;
  }

  /**
   * Get this day's event list
   *
   * @return list of events
   */
  @JsonProperty
  public List<Event> getEvents() {
    return this.events;
  }

  /**
   * Set this day's task list
   *
   * @param tasks list to be added
   */
  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }

  /**
   * Get this day's event list
   *
   * @param events list to be added
   */
  public void setEvents(List<Event> events) {
    this.events = events;
  }

  /**
   * Get this day's full name, first letter capitalized
   *
   * @return String containing this day's name
   */
  public String fullName() {
    String name = this.dayOfWeek.name().toLowerCase();
    return Character.toUpperCase(name.charAt(0)) + name.substring(1);
  }

  /**
   * Get this day's abbreviated name, first letter capitalized
   *
   * @return String containing this day's abbreviated name
   */
  public String abbreviatedName() {
    return this.dayOfWeek.abbreviation();
  }

  /**
   * Check if this day is over the max events
   *
   * @param maxEvents max events
   * @return true if over
   */
  public boolean checkOverMaxEvents(int maxEvents) {
    return this.events.size() > maxEvents;
  }

  /**
   * Check if this day is over the max tasks
   *
   * @param maxTasks maxTasks
   * @return true if over
   */
  public boolean checkOverMaxTasks(int maxTasks) {
    return this.tasks.size() > maxTasks;
  }
}
