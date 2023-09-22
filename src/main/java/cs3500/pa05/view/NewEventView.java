package cs3500.pa05.view;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.controller.NewEventController;
import javafx.scene.Scene;

/**
 * View for creating a new event
 */
public class NewEventView extends AbstractView {

  /**
   * Instantiate a new event view
   *
   * @param controller weekViewController for this view
   */
  public NewEventView(Controller controller) {
    super(controller);
    this.loader.setLocation(getClass().getClassLoader().getResource("new_event.fxml"));
  }
}
