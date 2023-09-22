package cs3500.pa05.view;

import cs3500.pa05.controller.Controller;

/**
 * View for editing limits of a week
 */
public class EditLimitsView extends AbstractView {
  /**
   * Instantiate a new view
   *
   * @param controller for this view
   */
  public EditLimitsView(Controller controller) {
    super(controller);
    this.loader.setLocation(getClass().getClassLoader().getResource("edit_limits.fxml"));
  }
}
