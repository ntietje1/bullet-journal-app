package cs3500.pa05.model;

/**
 * Enumeration representing the days of the week
 */
public enum DayOfWeek {
  SUNDAY("Sun"), MONDAY("Mon"), TUESDAY("Tue"), WEDNESDAY("Wed"),
  THURSDAY("Thur"), FRIDAY("Fri"), SATURDAY("Sat");

  private final String abbreviation;

  /**
   * Create a Day of week enum with abbreviation
   *
   * @param abbreviation the abbreviation
   */
  DayOfWeek(String abbreviation) {
    this.abbreviation = abbreviation;
  }

  @Override
  public String toString() {
    return this.name().charAt(0) + this.name().substring(1).toLowerCase();
  }


  /**
   * Get this day's abbreviation
   *
   * @return String containing abbreviation
   */
  public String abbreviation() {
    return abbreviation;
  }
}
