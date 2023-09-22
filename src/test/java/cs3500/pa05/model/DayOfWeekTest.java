package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * represents tests for the DayOfWeek enum
 */
class DayOfWeekTest {

  /**
   * tests the toString method
   */
  @Test
  void testToString() {
    assertEquals("Monday", DayOfWeek.MONDAY.toString());
    assertEquals("Tuesday", DayOfWeek.TUESDAY.toString());
    assertEquals("Wednesday", DayOfWeek.WEDNESDAY.toString());
    assertEquals("Thursday", DayOfWeek.THURSDAY.toString());
    assertEquals("Friday", DayOfWeek.FRIDAY.toString());
    assertEquals("Saturday", DayOfWeek.SATURDAY.toString());
    assertEquals("Sunday", DayOfWeek.SUNDAY.toString());
  }

  /**
   * tests the abbreviation method
   */
  @Test
  void testAbbreviation() {
    assertEquals("Mon", DayOfWeek.MONDAY.abbreviation());
    assertEquals("Tue", DayOfWeek.TUESDAY.abbreviation());
    assertEquals("Wed", DayOfWeek.WEDNESDAY.abbreviation());
    assertEquals("Thur", DayOfWeek.THURSDAY.abbreviation());
    assertEquals("Fri", DayOfWeek.FRIDAY.abbreviation());
    assertEquals("Sat", DayOfWeek.SATURDAY.abbreviation());
    assertEquals("Sun", DayOfWeek.SUNDAY.abbreviation());
  }
}