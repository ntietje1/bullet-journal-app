package cs3500.pa05.view;

import cs3500.pa05.controller.BulletJournalController;
import cs3500.pa05.controller.NewWeekController;

/**
 * View for bullet journal app
 */
public class BulletJournalView extends AbstractView {
  /**
   * Instantiate a new view
   *
   * @param bulletJournalController for this view
   */
  public BulletJournalView(BulletJournalController bulletJournalController) {
    super(bulletJournalController);
    this.loader.setLocation(getClass().getClassLoader().getResource("bullet_journal.fxml"));
  }
}
