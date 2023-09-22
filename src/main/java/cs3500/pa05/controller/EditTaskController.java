package cs3500.pa05.controller;

import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.TaskQueue;
import cs3500.pa05.model.Week;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;

/**
 * Controls the logic of creating a new task
 */
public class EditTaskController implements Controller {

  private ControllerCompletionListener listener;

  private Task task;

  private TaskQueue tasks;

  @FXML
  private TextField taskTitle;

  @FXML
  private TextField taskDescription;

  @FXML
  private ChoiceBox<String> dayToAddTo;

  @FXML
  private Button addButton;

  @FXML
  private Label dayToAddToLabel;

  /**
   * Constructs a new EditTaskController
   *
   * @param listener completion listener
   * @param task     task to edit
   */
  public EditTaskController(ControllerCompletionListener listener, Task task) {
    this.listener = listener;
    this.task = task;
  }

  /**
   * Run this controller
   */
  @Override
  public void run() {
    activateControls();
    prepareFields();
  }

  /**
   * Activate the button and key-binds
   */
  private void activateControls() {
    this.addButton.setOnAction(event -> editTask());
    for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
      this.dayToAddTo.getItems().add(dayOfWeek.toString());
    }
    this.addButton.getScene().getAccelerators().put(
        new KeyCodeCombination(KeyCode.ENTER),
        this::editTask
    );
  }

  /**
   * Prepares the fields of the form by filling in the current values of the task
   */
  private void prepareFields() {
    this.addButton.setText("Edit");
    this.taskTitle.setText(this.task.getName());
    this.taskDescription.setText(this.task.getDescription());
    this.dayToAddTo.setVisible(false);
    this.dayToAddToLabel.setVisible(false);
  }

  /**
   * Apply the edited values to the task
   */
  private void editTask() {
    String title = this.taskTitle.getText();
    String desc = this.taskDescription.getText();

    if (title.isEmpty()) {
      return;
    }

    task.setName(title);
    task.setDescription(desc);

    listener.onControllerComplete();
  }
}
