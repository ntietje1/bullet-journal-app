package cs3500.pa05.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;


/**
 * represents a way to read and write files
 */
public class FileManager {

  /**
   * reads a file with the given fileName
   *
   * @param fileName the filename of the file that is read
   * @return a string of all the content in the file
   */
  public static String readFile(String fileName) {
    Path theFile = Path.of(fileName);
    Scanner input = null;
    StringBuilder content = new StringBuilder();

    try {
      input = new Scanner(theFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    while (input.hasNext()) {
      content.append(input.nextLine());
    }

    return content.toString();
  }


  /**
   * Writes the given String to the file with the given filename
   *
   * @param filename name of file to write the contents to
   * @param contents contents to write to the file
   */
  public static void writeFile(String filename, String contents) {
    Path path = Path.of(filename);


    // Convert String to data for writing ("raw" byte data)
    byte[] data = contents.getBytes();


    // The path may not exist, or we may not have permissions to write to it,
    // in which case we need to handle that error (hence try-catch)
    try {
      Files.write(path, data);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


}

