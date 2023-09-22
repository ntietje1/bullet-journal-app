package cs3500.pa05.view;

import cs3500.pa05.controller.NewWeekController;

/**
 * View for creating a new week
 */
public class NewWeekView extends AbstractView {
  /**
   * Instantiate a new view
   *
   * @param newWeekController for this view
   */
  public NewWeekView(NewWeekController newWeekController) {
    super(newWeekController);
    this.loader.setLocation(getClass().getClassLoader().getResource("new_week.fxml"));
  }
}
