package cs3500.pa05.controller;

import cs3500.pa05.model.Week;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;

/**
 * Controls editing limits of a week
 */
public class EditLimitsController implements Controller {

  private final Week week;
  private final ControllerCompletionListener listener;

  @FXML
  private TextField taskLimit;

  @FXML
  private TextField eventLimit;

  @FXML
  private Button editButton;

  /**
   * Constructs a new EditLimitsController
   *
   * @param week     week to edit limits of
   * @param listener listener to close controller
   */
  public EditLimitsController(Week week, ControllerCompletionListener listener) {
    this.week = week;
    this.listener = listener;
  }

  /**
   * Run this controller
   */
  @Override
  public void run() {
    activateControls();
  }

  /**
   * activates the controls
   */
  private void activateControls() {
    this.editButton.setOnAction(event -> editLimits());
    this.editButton.getScene().getAccelerators().put(
        new KeyCodeCombination(KeyCode.ENTER),
        this::editLimits
    );
  }

  /**
   * Edits the limits of the given event to the given values
   */
  private void editLimits() {
    int taskLimitInt = validateInput(this.taskLimit);
    int eventLimitInt = validateInput(this.eventLimit);
    if (taskLimitInt >= 0 && eventLimitInt >= 0) {
      this.week.setMaxTasks(taskLimitInt);
      this.week.setMaxEvents(eventLimitInt);
      this.listener.onControllerComplete();
    }
  }

  /**
   * Verifies field contains a valid int that is >= 0
   * prints an error if not
   *
   * @param field field to validate
   * @return value of field
   */
  private int validateInput(TextField field) {
    int fieldValue = -1;
    try {
      fieldValue = Integer.parseInt(field.getText());
    } catch (NumberFormatException e) {
      this.taskLimit.setText("must be a number");
    }
    if (fieldValue < 0) {
      field.setText("Must be > 0");
    }
    return fieldValue;
  }
}
