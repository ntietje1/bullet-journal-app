package cs3500.pa05.controller;

import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.TimeStamp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;

/**
 * Controls the logic of creating a new event
 */
public class EditEventController implements Controller {

  private final ControllerCompletionListener listener;
  private final Event event;

  @FXML
  private Button addButton;

  @FXML
  private TextField eventTitle;

  @FXML
  private TextField eventDescription;

  @FXML
  private TextField eventStartTime;

  @FXML
  private TextField eventDuration;

  @FXML
  private ChoiceBox<String> dayToAddTo;

  @FXML
  private Label dayToAddToLabel;

  /**
   * Constructs a new EditEventController
   *
   * @param listener completion listener
   * @param event    event being edited
   */
  public EditEventController(ControllerCompletionListener listener, Event event) {
    this.listener = listener;
    this.event = event;
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
    this.addButton.setOnAction(event -> editEvent());
    for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
      this.dayToAddTo.getItems().add(dayOfWeek.toString());
    }
    this.addButton.getScene().getAccelerators().put(
        new KeyCodeCombination(KeyCode.ENTER),
        this::editEvent
    );
  }

  /**
   * Pre-fill the fields of the form with the current values of the event
   */
  private void prepareFields() {
    this.addButton.setText("Edit");
    this.eventTitle.setText(this.event.getName());
    this.eventDescription.setText(this.event.getDescription());
    this.eventStartTime.setText(this.event.getStartTime().toString());
    this.eventDuration.setText(this.event.getDuration().toString());
    this.dayToAddTo.setVisible(false);
    this.dayToAddToLabel.setVisible(false);
  }

  /**
   * Apply the values to the event
   */
  private void editEvent() {
    final String title = this.eventTitle.getText();
    final String desc = this.eventDescription.getText();
    TimeStamp start;
    TimeStamp duration;
    try {
      start = TimeStamp.fromString(this.eventStartTime.getText());
      duration = TimeStamp.fromString(this.eventDuration.getText());
    } catch (IllegalArgumentException e) {
      System.err.println("Couldn't convert input to valid time");
      return;
    }

    if (title.isEmpty()) {
      return;
    }

    event.setName(title);
    event.setDuration(duration);
    event.setStartTime(start);
    event.setDescription(desc);
    listener.onControllerComplete();
  }

}
