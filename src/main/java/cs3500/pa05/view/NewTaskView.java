package cs3500.pa05.view;

import cs3500.pa05.controller.Controller;

/**
 * View for creating a new task
 */
public class NewTaskView extends AbstractView {

  /**
   * Instantiate a new event view
   *
   * @param controller weekViewController for this view
   */
  public NewTaskView(Controller controller) {
    super(controller);
    this.loader.setLocation(getClass().getClassLoader().getResource("new_task.fxml"));
  }
}
