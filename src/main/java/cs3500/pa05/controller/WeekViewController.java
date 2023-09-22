package cs3500.pa05.controller;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.FileManager;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.TaskQueue;
import cs3500.pa05.model.Week;
import cs3500.pa05.model.json.JsonUtils;
import cs3500.pa05.model.json.WeekJson;
import cs3500.pa05.view.EditLimitsView;
import cs3500.pa05.view.NewEventView;
import cs3500.pa05.view.NewTaskView;
import cs3500.pa05.view.NewWeekView;
import cs3500.pa05.view.OpenWeekView;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * Represents a controller for a bullet journal GUI app
 */
public class WeekViewController implements Controller, ControllerCompletionListener {
  Week week;
  TaskQueue tasks;
  Stage popupStage;
  BulletJournalController bulletJournalController;

  @FXML
  private Label taskLimit;

  @FXML
  private Label eventLimit;

  @FXML
  private Label weekTitle;

  @FXML
  private Label totalEvents;

  @FXML
  private Label totalTasks;

  @FXML
  private Label tasksCompletes;

  @FXML
  private VBox monday;

  @FXML
  private VBox tuesday;

  @FXML
  private VBox wednesday;

  @FXML
  private VBox thursday;

  @FXML
  private VBox friday;

  @FXML
  private VBox saturday;

  @FXML
  private VBox sunday;

  private Map<VBox, DayOfWeek> dayMap;

  @FXML
  private VBox taskQueue;

  @FXML
  private Button newEventButton;

  @FXML
  private Button newTaskButton;

  @FXML
  private Button saveWeekButton;

  @FXML
  private Button openWeekButton;

  @FXML
  private Button newWeekButton;

  @FXML
  private HBox mainPane;

  /**
   * Instantiate a new controller
   */
  public WeekViewController(BulletJournalController bulletJournalController, Stage popupStage,
                            Week week) {
    this.week = week;
    this.tasks = new TaskQueue();
    this.tasks.addAll(week.findAllTasks());
    this.bulletJournalController = bulletJournalController;
    this.popupStage = popupStage;
  }

  /**
   * Initialize a map of vboxes to their respective days of the week
   */
  private void initializeDayMap() {
    if (this.dayMap == null) {
      this.dayMap = new HashMap<>();
      this.dayMap.put(this.monday, DayOfWeek.MONDAY);
      this.dayMap.put(this.tuesday, DayOfWeek.TUESDAY);
      this.dayMap.put(this.wednesday, DayOfWeek.WEDNESDAY);
      this.dayMap.put(this.thursday, DayOfWeek.THURSDAY);
      this.dayMap.put(this.friday, DayOfWeek.FRIDAY);
      this.dayMap.put(this.saturday, DayOfWeek.SATURDAY);
      this.dayMap.put(this.sunday, DayOfWeek.SUNDAY);
    }
  }

  /**
   * Do this when the controller is completed
   */
  @Override
  public void onControllerComplete() {
    updateDisplay();
    if (this.popupStage != null) {
      popupStage.close();
    }
  }

  /**
   * Initializes a bullet journal app
   */
  @FXML
  public void run() {
    this.activateButtons();
    this.setupHotkeys();
    this.initializeDayMap();
    this.updateDisplay();
  }

  /**
   * Bind all buttons to their respective methods
   */
  private void activateButtons() {
    this.newTaskButton.setOnAction(e -> createTask());
    this.newEventButton.setOnAction(e -> createEvent());
    this.saveWeekButton.setOnAction(e -> saveWeek());
    this.openWeekButton.setOnAction(e -> openWeek());
    this.newWeekButton.setOnAction(e -> createWeek());
  }

  /**
   * sets up the shortcuts
   */
  private void setupHotkeys() {
    addNewEventAccelerator();
    addNewTaskAccelerator();
    addOpenWeekAccelerator();
    addNewWeekAccelerator();
    addSaveWeekAccelerator();
    addEditLimitsAccelerator();
  }

  /**
   * creates the keyboard shortcut for adding a new task
   * CTRL/CMD + T
   */
  private void addNewTaskAccelerator() {
    this.newTaskButton.getScene().getAccelerators().put(
        new KeyCodeCombination(KeyCode.T, KeyCombination.SHORTCUT_DOWN),
        this::createTask
    );
  }

  /**
   * Adds keyboard shortcut for creating a new event
   * CTRL/CMD + E
   */
  private void addNewEventAccelerator() {
    this.newEventButton.getScene().getAccelerators().put(
        new KeyCodeCombination(KeyCode.E, KeyCombination.SHORTCUT_DOWN),
        this::createEvent
    );
  }


  /**
   * Adds keyboard shortcut for saving the week
   * CTRL/CMD + S
   */
  private void addSaveWeekAccelerator() {
    this.saveWeekButton.getScene().getAccelerators().put(
        new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN),
        this::saveWeek
    );
  }


  /**
   * Adds keyboard shortcut for creating a new week
   * CTRL/CMD + N
   */
  private void addNewWeekAccelerator() {
    this.newEventButton.getScene().getAccelerators().put(
        new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN),
        this::createWeek
    );
  }

  /**
   * Adds keyboard shortcut for opening a week
   * CTRL/CMD + O
   */
  private void addOpenWeekAccelerator() {
    this.newEventButton.getScene().getAccelerators().put(
        new KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN),
        this::openWeek
    );
  }

  /**
   * Adds keyboard shortcut for editing task/events limits
   * CTRL/CMD + L
   */
  private void addEditLimitsAccelerator() {
    this.newEventButton.getScene().getAccelerators().put(
        new KeyCodeCombination(KeyCode.L, KeyCombination.SHORTCUT_DOWN),
        this::editLimits
    );
  }

  /**
   * Update the display to reflect recent changes in the data
   */
  private void updateDisplay() {
    this.taskQueue.getChildren().clear();
    for (VBox dayVbox : Arrays.asList(monday, tuesday, wednesday, thursday, friday, saturday,
        sunday)) {
      updateSingleDay(dayVbox);
    }
    updateTaskQueue();
    updateWeeklyOverview();
    this.weekTitle.setText(this.week.getName());
  }

  /**
   * updates the task queue
   */
  private void updateTaskQueue() {
    BackgroundFill backgroundFill =
        new BackgroundFill(Color.color(0.50, 0.50, 0.50, 0.1), new CornerRadii(10), Insets.EMPTY);
    Background background = new Background(backgroundFill);
    taskQueue.setBackground(background);
    Region region = new Region();
    taskQueue.getChildren().add(region);
    VBox.setVgrow(region, Priority.ALWAYS);
    progressBar(this.tasks.getTasks(), taskQueue);
  }

  /**
   * Update a single day to reflect changes in the data
   *
   * @param dayVbox vbox to update
   */
  private void updateSingleDay(VBox dayVbox) {
    Objects.requireNonNull(dayVbox);
    DayOfWeek dayOfWeek = this.dayMap.get(dayVbox);
    Objects.requireNonNull(dayOfWeek);
    Day day = this.week.getDay(dayOfWeek);

    BackgroundFill backgroundFill;
    if (day.checkOverMaxEvents(week.getMaxEventsPerDay())
        || day.checkOverMaxTasks(week.getMaxTasksPerDay())) {
      backgroundFill =
          new BackgroundFill(Color.color(1.00, 0.25, 0.25, 0.2), new CornerRadii(10), Insets.EMPTY);
    } else {
      backgroundFill =
          new BackgroundFill(Color.color(0.50, 0.50, 0.50, 0.1), new CornerRadii(10), Insets.EMPTY);
    }
    Background background = new Background(backgroundFill);
    dayVbox.setBackground(background);

    dayVbox.getChildren().clear();

    for (Task task : day.getTasks()) {
      dayVbox.getChildren().add(this.assembleTaskControl(task));
      taskQueue.getChildren().add(this.assembleTaskControl(task));
    }
    for (Event event : day.getEvents()) {
      dayVbox.getChildren().add(this.assembleEventControl(event));
    }
    Region region = new Region();
    dayVbox.getChildren().add(region);
    VBox.setVgrow(region, Priority.ALWAYS);
    displayWarning(day, dayVbox);
    progressBar(day.getTasks(), dayVbox);
  }

  /**
   * Add progress bar with labels
   *
   * @param tasks tasks to show progress of
   * @param vbox  vbox to add to
   */
  private void progressBar(List<Task> tasks, VBox vbox) {
    int tasksCompleted = 0;
    ProgressBar progressBar = new ProgressBar(0);
    progressBar.setMaxWidth(Integer.MAX_VALUE);
    double progress = 0;

    for (Task task : tasks) {
      if (task.getCompleted()) {
        progress += 1.0 / tasks.size();
        tasksCompleted++;
      }
      progressBar.setProgress(progress);
    }
    Label progressLabel = new Label("Progress: "
        + tasksCompleted + "/" + tasks.size());
    progressLabel.setPadding(new Insets(0, 2, 0, 2));
    vbox.getChildren().add(progressLabel);
    vbox.getChildren().add(progressBar);
  }

  /**
   * Update the weekly overview data
   */
  private void updateWeeklyOverview() {
    int numEvents = 0;
    for (Day d : this.week.getDays().values()) {
      numEvents += d.getEvents().size();
    }
    int tasksCompleted = 0;
    for (Task t : this.tasks.getTasks()) {
      if (t.getCompleted()) {
        tasksCompleted++;
      }
    }
    totalEvents.setText("Total Events = " + numEvents);

    int numTasks = this.tasks.getTasks().size();
    totalTasks.setText("Total tasks = " + numTasks);

    if (numTasks == 0) {
      tasksCompletes.setText("Tasks completed = 100%");
    } else {
      double percentComplete = (tasksCompleted * 100.0) / numTasks;
      tasksCompletes.setText("Tasks completed = " + (int) percentComplete + "%");
    }


    int maxTasks = this.week.getMaxTasksPerDay();
    int maxEvents = this.week.getMaxEventsPerDay();
    if (maxTasks == Integer.MAX_VALUE) {
      taskLimit.setText("No Task Limit");
    } else {
      taskLimit.setText("Task Limit = " + maxTasks);
    }

    if (maxEvents == Integer.MAX_VALUE) {
      eventLimit.setText("No Event Limit");
    } else {
      eventLimit.setText("Event Limit = " + maxEvents);
    }
  }

  private void displayWarning(Day day, VBox dayVbox) {
    VBox messageBox = new VBox();
    messageBox.setBackground(new Background(new BackgroundFill(
        Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));

    if (day.checkOverMaxEvents(this.week.getMaxEventsPerDay())) {
      Label warning = new Label("over event limit");
      messageBox.getChildren().add(warning);
    }
    if (day.checkOverMaxTasks(this.week.getMaxTasksPerDay())) {
      Label warningTwo = new Label("over task limit");
      messageBox.getChildren().add(warningTwo);
    }
    dayVbox.getChildren().add(messageBox);
  }


  /**
   * Assemble a control for modifying a task
   *
   * @param task task to be modified
   * @return Pane containing interactable elements
   */
  private Pane assembleTaskControl(Task task) {
    VBox outputBox = new VBox();
    HBox nameBox = new HBox();
    HBox descBox = new HBox();
    outputBox.getChildren().addAll(nameBox, descBox);

    CheckBox toggleCompletedCheckBox = new CheckBox();
    toggleCompletedCheckBox.setSelected(task.getCompleted());
    toggleCompletedCheckBox.setOnAction(e -> toggleTaskCompleted(task));

    Text taskLabel = new Text(task.getName());
    if (task.getCompleted()) {
      taskLabel.setStrikethrough(true);
    }
    final TextFlow textFlow = this.makeTextFlow(taskLabel);

    final Text textDesc = new Text(task.getDescription());
    final TextFlow textFlow2 = this.makeTextFlow(textDesc);

    descBox.getChildren().add(textFlow2);

    Button deleteButton = this.makeDeleteButton();
    deleteButton.setOnAction(e -> deleteTask(task));
    Button editButton = this.makeEditButton();
    editButton.setOnAction(e -> editTask(task));

    HBox subHbox = new HBox();
    HBox.setHgrow(subHbox, Priority.ALWAYS);
    subHbox.setAlignment(Pos.CENTER_RIGHT);
    subHbox.getChildren().add(editButton);
    subHbox.getChildren().add(deleteButton);

    nameBox.getChildren().addAll(toggleCompletedCheckBox, textFlow, subHbox);
    nameBox.setAlignment(Pos.CENTER_LEFT);
    HBox.setHgrow(nameBox, Priority.ALWAYS);

    giveBackground(outputBox, task.getCompleted());
    return outputBox;
  }

  /**
   * Assemble a control for modifying an event
   *
   * @param event event to be modified
   * @return Pane containing interactable elements
   */
  private Pane assembleEventControl(Event event) {

    VBox outputBox = new VBox();
    HBox nameBox = new HBox();
    HBox descBox = new HBox();
    outputBox.getChildren().addAll(nameBox, descBox);

    Text eventLabel = new Text(event.getName());
    final TextFlow textFlow = this.makeTextFlow(eventLabel);

    Text eventDesc = new Text("Start " + event.getStartTime() + System.lineSeparator()
        + "Duration " + event.getDuration() + System.lineSeparator() + event.getDescription());
    final TextFlow textFlow2 = this.makeTextFlow(eventDesc);

    descBox.getChildren().add(textFlow2);

    Button deleteButton = this.makeDeleteButton();
    deleteButton.setOnAction(e -> deleteEvent(event));
    Button editButton = this.makeEditButton();
    editButton.setOnAction(e -> editEvent(event));

    HBox subHbox = new HBox();
    HBox.setHgrow(subHbox, Priority.ALWAYS);
    subHbox.setAlignment(Pos.CENTER_RIGHT);
    subHbox.getChildren().add(editButton);
    subHbox.getChildren().add(deleteButton);


    nameBox.getChildren().addAll(textFlow, subHbox);
    nameBox.setAlignment(Pos.CENTER_LEFT);
    HBox.setHgrow(nameBox, Priority.ALWAYS);

    giveBackground(outputBox, false);
    return outputBox;
  }

  private void giveBackground(Pane pane, boolean completed) {
    if (!completed) {
      pane.setStyle("-fx-background-color: rgba(100, 50, 100, 0.5); -fx-background-radius: 10;");
    } else {
      pane.setStyle("-fx-background-color: rgba(80, 80, 80, 0.5); -fx-background-radius: 10;");
    }
  }

  /**
   * Make a pre-formatted text flow with the given text
   *
   * @param text text to be put inside the text flow
   * @return generated text flow
   */
  private TextFlow makeTextFlow(Text text) {
    text.setFill(Color.WHITE);
    text.setFont(Font.font("System", 12));
    TextFlow textFlow = new TextFlow();
    textFlow.getChildren().add(text);
    textFlow.setTextAlignment(TextAlignment.LEFT);
    textFlow.setPadding(new Insets(6, 0, 4, 4));
    return textFlow;
  }

  /**
   * Make a delete button with a red icon
   *
   * @return button
   */
  private Button makeDeleteButton() {
    return makeImageButton("/minus-icon.png", 16);
  }

  /**
   * Make an edit button with a pencil icon
   *
   * @return button
   */
  private Button makeEditButton() {
    return makeImageButton("/edit-icon-3.png", 16);
  }

  /**
   * Make an image button with the given image path
   *
   * @param imagePath path to the image
   * @return Square button of given size
   */
  private Button makeImageButton(String imagePath, double size) {
    Button button = new Button();
    button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
    Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
    ImageView editImageView = new ImageView(image);
    editImageView.setFitWidth(size);
    editImageView.setFitHeight(size);
    button.setPadding(new Insets(1, 1, 1, 1));
    button.setGraphic(editImageView);
    return button;
  }

  /**
   * Create a task
   * creates a popup window to retrieve data from user
   */
  private void createTask() {
    NewTaskController newTaskController = new NewTaskController(this, this.week, this.tasks);
    NewTaskView newTaskView = new NewTaskView(newTaskController);
    this.popupStage.setTitle("Create Task");
    this.popupStage.setScene(newTaskView.load());
    this.popupStage.show();
    newTaskController.run();
  }

  /**
   * Creates a pop-up window to edit a task
   *
   * @param task task to edit
   */
  private void editTask(Task task) {
    EditTaskController editTaskController = new EditTaskController(this, task);
    NewTaskView editTaskView = new NewTaskView(editTaskController);
    this.popupStage.setTitle("Edit Task");
    this.popupStage.setScene(editTaskView.load());
    this.popupStage.show();
    editTaskController.run();
  }

  /**
   * Create an event
   * creates a popup window to retrieve data from user
   */
  private void createEvent() {
    NewEventController newEventController = new NewEventController(this, this.week);
    NewEventView newEventView = new NewEventView(newEventController);
    this.popupStage.setTitle("Create Event");
    this.popupStage.setScene(newEventView.load());
    this.popupStage.show();
    newEventController.run();
  }

  /**
   * Creates a pop-up window to edit an event
   *
   * @param event event to edit
   */
  private void editEvent(Event event) {
    EditEventController editEventController = new EditEventController(this, event);
    NewEventView editEventView = new NewEventView(editEventController);
    this.popupStage.setTitle("Edit Event");
    this.popupStage.setScene(editEventView.load());
    this.popupStage.show();
    editEventController.run();
  }

  /**
   * Toggle completed status for task
   *
   * @param task task to toggle
   */
  private void toggleTaskCompleted(Task task) {
    task.toggleCompleted();
    this.updateDisplay();
  }

  /**
   * Delete a task
   *
   * @param task task for deletion
   */
  private void deleteTask(Task task) {
    this.week.remove(task);
    this.tasks.removeTask(task);
    this.updateDisplay();
  }

  /**
   * Delete an event
   *
   * @param event event for deletion
   */
  private void deleteEvent(Event event) {
    this.week.remove(event);
    this.updateDisplay();
  }

  /**
   * creates a new week
   */
  private void createWeek() {
    this.saveWeek();
    NewWeekController newWeekController = new NewWeekController(this.bulletJournalController);
    NewWeekView newWeekView = new NewWeekView(newWeekController);
    this.popupStage.setTitle("New Week");
    this.popupStage.setScene(newWeekView.load());
    this.popupStage.show();
    newWeekController.run();
  }

  /**
   * turns this week into json format then saves to file
   * and the file name corresponds to week name
   */
  private void saveWeek() {
    if (this.week.getPath().isEmpty()) {
      return;
    }
    WeekJson saveWeek = new WeekJson(this.week);
    JsonNode save = JsonUtils.serializeRecord(saveWeek);
    FileManager.writeFile(this.week.getPath(), save.toString());
  }

  /**
   * opens the file and converts its contents to json format
   * then turns the json into a week
   */
  private void openWeek() {
    OpenWeekController openWeekController = new OpenWeekController(this.bulletJournalController);
    OpenWeekView openWeekView = new OpenWeekView(openWeekController);
    this.popupStage.setTitle("Open Week");
    this.popupStage.setScene(openWeekView.load());
    this.popupStage.show();
    openWeekController.run();
  }

  private void editLimits() {
    EditLimitsController editLimitsController = new EditLimitsController(this.week, this);
    EditLimitsView editLimitsView = new EditLimitsView(editLimitsController);
    this.popupStage.setTitle("Edit Limits");
    this.popupStage.setScene(editLimitsView.load());
    this.popupStage.show();
    editLimitsController.run();
  }

}
