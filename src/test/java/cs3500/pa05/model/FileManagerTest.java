package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * represents tests for the FileManager class
 */
class FileManagerTest {

  /**
   * tests the readFile method
   */
  @Test
  void testReadFile() {
    assertEquals("info", FileManager.readFile("exampleFiles/readFileTester.bujo"));

    assertThrows(RuntimeException.class, () -> FileManager.readFile("hello"));
  }

  /**
   * tests the writeFile method by making a file and
   * checking that the file contains the correct contents that were given
   */
  @Test
  void testWriteFile() {
    String fileName = "exampleFiles/writeFileTester.bujo";
    String fileContents = "testing";

    FileManager.writeFile(fileName, fileContents);

    assertEquals(fileContents, FileManager.readFile(fileName));

    assertThrows(RuntimeException.class, () -> FileManager.writeFile("/Downloads/hello", "info"));
  }


}