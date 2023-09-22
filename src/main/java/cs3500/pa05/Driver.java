package cs3500.pa05;

import cs3500.pa05.controller.BulletJournalController;
import cs3500.pa05.controller.WeekViewController;
import cs3500.pa05.model.TaskQueue;
import cs3500.pa05.model.Week;
import cs3500.pa05.view.BulletJournalView;
import cs3500.pa05.view.WeekView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Represents a bullet journal application.
 */
public class Driver extends Application {
  /**
   * Starts the GUI for a bullet journal application
   *
   * @param stage the JavaFX stage to add elements to
   */
  @Override
  public void start(Stage stage) {
    BulletJournalController controller = new BulletJournalController();
    BulletJournalView view = new BulletJournalView(controller);
    try {
      // load and place the weekView's scene onto the stage
      Scene scene = view.load();
      stage.setResizable(false);
      stage.setScene(scene);

      stage.setTitle("Salty Sea Sprouts Bullet Journal App");

      controller.run();

      // render the stage
      stage.show();
    } catch (IllegalStateException exc) {
      System.err.println("Unable to load GUI.");
    }
  }

  /**
   * Entry point for bullet journal
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch();

  }
}
