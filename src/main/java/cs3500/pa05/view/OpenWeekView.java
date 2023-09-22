package cs3500.pa05.view;

import cs3500.pa05.controller.OpenWeekController;

/**
 * View for opening a week
 */
public class OpenWeekView extends AbstractView {
  /**
   * Instantiate a new view
   *
   * @param openWeekController for this view
   */
  public OpenWeekView(OpenWeekController openWeekController) {
    super(openWeekController);
    this.loader.setLocation(getClass().getClassLoader().getResource("open_week.fxml"));
  }
}
