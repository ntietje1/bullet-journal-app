package cs3500.pa05.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.FileManager;
import cs3500.pa05.model.Week;
import cs3500.pa05.model.json.WeekJson;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;

/**
 * Controls the logic of opening a week
 */
public class OpenWeekController implements Controller {

  @FXML
  private TextField weekPath;

  @FXML
  private Button weekOpen;

  private WeekCompletionListener listener;

  ObjectMapper mapper;

  /**
   * Constructs a new instance of a OpenWeekController
   *
   * @param listener listener object
   */
  public OpenWeekController(WeekCompletionListener listener) {
    this.listener = listener;
    this.mapper = new ObjectMapper();
  }

  /**
   * Run this controller
   */
  @Override
  public void run() {
    this.activateControls();
  }

  /**
   * activates controls
   */
  private void activateControls() {
    this.weekOpen.setOnAction(e -> this.openWeek());
    this.weekOpen.getScene().getAccelerators().put(
        new KeyCodeCombination(KeyCode.ENTER),
        this::openWeek
    );
  }

  /**
   * opens this week by reading the .bujo file
   */
  private void openWeek() {
    String contents = FileManager.readFile(this.weekPath.getText());
    WeekJson openedWeek = null;
    try {
      openedWeek = this.mapper.readValue(contents, WeekJson.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

    Week result = openedWeek.week();

    this.listener.onWeekComplete(result);
  }
}
