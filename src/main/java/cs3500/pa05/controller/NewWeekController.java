package cs3500.pa05.controller;

import cs3500.pa05.model.Week;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;

/**
 * Controller to handle creation of new weeks
 */
public class NewWeekController implements Controller {

  private WeekCompletionListener listener;

  @FXML
  private TextField weekName;

  @FXML
  private TextField weekPath;

  @FXML
  private TextField weekMaxEvents;

  @FXML
  private TextField weekMaxTasks;

  @FXML
  private Button weekCreate;

  /**
   * Constructs a new instance of a NewWeekController
   *
   * @param listener listener object
   */
  public NewWeekController(WeekCompletionListener listener) {
    this.listener = listener;
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
    this.weekCreate.setOnAction(e -> this.createWeek());
    this.weekCreate.getScene().getAccelerators().put(
        new KeyCodeCombination(KeyCode.ENTER),
        this::createWeek
    );
  }

  /**
   * creates a new week
   */
  private void createWeek() {
    String name = this.weekName.getText();
    String path = this.weekPath.getText();
    String maxEvents = this.weekMaxEvents.getText();
    String maxTasks = this.weekMaxTasks.getText();

    if (name.isEmpty() || path.isEmpty() || maxEvents.isEmpty() || maxTasks.isEmpty()) {
      return;
    }

    Week week = new Week();
    week.setName(this.weekName.getText());
    week.setPath(path);
    week.setMaxEvents(Integer.parseInt(maxEvents));
    week.setMaxTasks(Integer.parseInt(maxTasks));
    listener.onWeekComplete(week);
  }
}
