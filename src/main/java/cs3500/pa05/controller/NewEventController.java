package cs3500.pa05.controller;

import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.TimeStamp;
import cs3500.pa05.model.Week;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;

/**
 * Represents a controller for a new event dialog box
 */
public class NewEventController implements Controller {

  private ControllerCompletionListener listener;

  private Week week;

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

  /**
   * constructor for a NewEventController
   *
   * @param listener listener for the event
   * @param week     week that events are added to
   */
  public NewEventController(ControllerCompletionListener listener, Week week) {
    this.listener = listener;
    this.week = week;
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
    this.addButton.setOnAction(event -> createEvent());
    for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
      this.dayToAddTo.getItems().add(dayOfWeek.toString());
    }
    this.addButton.getScene().getAccelerators().put(
        new KeyCodeCombination(KeyCode.ENTER),
        this::createEvent
    );
  }

  /**
   * creates an event
   */
  private void createEvent() {
    String title = this.eventTitle.getText();
    String desc = this.eventDescription.getText();
    String currentChoice = this.dayToAddTo.getValue();
    TimeStamp start;
    TimeStamp duration;
    try {
      start = TimeStamp.fromString(this.eventStartTime.getText());
      duration = TimeStamp.fromString(this.eventDuration.getText());
    } catch (IllegalArgumentException e) {
      System.err.println("Couldn't convert input to valid time");
      return;
    }

    if (title.isEmpty() || currentChoice == null) {
      return;
    }

    Event event = new Event(title, start, duration);
    event.setDescription(desc);
    this.week.getDay(DayOfWeek.valueOf(currentChoice.toUpperCase())).addEvent(event);
    listener.onControllerComplete();
  }

}
