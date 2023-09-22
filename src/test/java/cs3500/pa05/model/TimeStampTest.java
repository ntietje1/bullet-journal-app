package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * represents tests for the TimeStamp class
 */
class TimeStampTest {

  private TimeStamp time;
  private TimeStamp timeTwo;

  /**
   * Setup TimeStamp objects before each test
   */
  @BeforeEach
  void setup() {
    time = new TimeStamp(5, 10);
    timeTwo = new TimeStamp(10, 5);
  }

  /**
   * tests the TimeStamp constructor
   */
  @Test
  void testConstructor() {
    assertThrows(IllegalArgumentException.class, () -> new TimeStamp(-1, 10));
    assertThrows(IllegalArgumentException.class, () -> new TimeStamp(2, -10));
    assertThrows(IllegalArgumentException.class, () -> new TimeStamp(100, 5));
    assertThrows(IllegalArgumentException.class, () -> new TimeStamp(1, 100));
  }

  /**
   * tests the getHours method
   */
  @Test
  void testGetHours() {
    assertEquals(5, time.getHours());
  }

  /**
   * tests the getMinutes method
   */
  @Test
  void testGetMinutes() {
    assertEquals(10, time.getMinutes());
  }

  /**
   * tests the add method
   */
  @Test
  void testAdd() {
    assertEquals(15, time.add(timeTwo).getHours());
    assertEquals(15, time.add(timeTwo).getMinutes());
  }

  /**
   * tests the toString method
   */
  @Test
  void testToString() {
    assertEquals("05:10", time.toString());
    assertEquals("10:05", timeTwo.toString());
  }

  /**
   * tests the fromString method
   */
  @Test
  void testFromString() {
    assertEquals(time.getHours(), TimeStamp.fromString("5:10").getHours());
    assertEquals(time.getMinutes(), TimeStamp.fromString("5:10").getMinutes());
    assertThrows(IllegalArgumentException.class, () -> TimeStamp.fromString("a:bc"));
  }
}