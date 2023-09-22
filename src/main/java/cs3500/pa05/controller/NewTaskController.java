package cs3500.pa05.controller;

import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.TaskQueue;
import cs3500.pa05.model.Week;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;

/**
 * Represents a controller for a new task dialog box
 */
public class NewTaskController implements Controller {

  private ControllerCompletionListener listener;

  private Week week;

  private TaskQueue tasks;

  @FXML
  private TextField taskTitle;

  @FXML
  private TextField taskDescription;

  @FXML
  private ChoiceBox<String> dayToAddTo;

  @FXML
  private Button addButton;


  /**
   * Constructs a new instance of a NewTaskController
   *
   * @param listener Listener object
   * @param week     week to add new task to
   * @param tasks    task queue to add task to
   */
  public NewTaskController(ControllerCompletionListener listener, Week week, TaskQueue tasks) {
    this.week = week;
    this.tasks = tasks;
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
    this.addButton.setOnAction(event -> createTask());
    for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
      this.dayToAddTo.getItems().add(dayOfWeek.toString());
    }
    this.addButton.getScene().getAccelerators().put(
        new KeyCodeCombination(KeyCode.ENTER),
        this::createTask
    );
  }

  /**
   * creates a task
   */
  private void createTask() {
    String title = this.taskTitle.getText();
    String desc = this.taskDescription.getText();
    String currentChoice = this.dayToAddTo.getValue();

    if (title.isEmpty() || currentChoice == null) {
      return;
    }

    Task task = new Task(title);
    task.setDescription(desc);
    this.week.getDay(DayOfWeek.valueOf(currentChoice.toUpperCase())).addTask(task);
    this.tasks.addTask(task);
    listener.onControllerComplete();
  }
}

