package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a calendar item
 */
public abstract class CalendarItem {
  String name;
  String description;

  /**
   * Instantiates a new Calendar Item
   *
   * @param name name of the item
   */
  public CalendarItem(String name) {
    this.name = name;
    this.description = "";
  }

  /**
   * constructor for the calendar item for json format
   *
   * @param name        name of the item
   * @param description description of the item
   */
  public CalendarItem(
      @JsonProperty("name") String name,
      @JsonProperty("description") String description) {
    this.name = name;
    this.description = description;
  }

  /**
   * gets name of calendar item
   *
   * @return calendar item name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Sets the name of this calendar item to the given value
   *
   * @param name desired name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * gets description of calendar item
   *
   * @return calendar item description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Set the description of this item
   *
   * @param description String to be set as description
   */
  public void setDescription(String description) {
    this.description = description;
  }
}
