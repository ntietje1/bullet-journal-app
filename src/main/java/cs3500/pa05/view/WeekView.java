package cs3500.pa05.view;

import cs3500.pa05.controller.WeekViewController;

/**
 * Represents a bullet journal GUI view
 */
public class WeekView extends AbstractView {

  /**
   * Instantiate a new week view
   *
   * @param weekViewController weekViewController for this view
   */
  public WeekView(WeekViewController weekViewController) {
    super(weekViewController);
    this.loader.setLocation(getClass().getClassLoader().getResource("week_view.fxml"));
  }
}
