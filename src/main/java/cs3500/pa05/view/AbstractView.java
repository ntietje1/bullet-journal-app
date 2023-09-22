package cs3500.pa05.view;

import cs3500.pa05.controller.Controller;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * Represents a generic view
 */
public abstract class AbstractView implements View {
  protected FXMLLoader loader;

  /**
   * Instantiate a new view
   *
   * @param controller for this view
   */
  public AbstractView(Controller controller) {
    this.loader = new FXMLLoader();
    this.loader.setController(controller);
  }

  /**
   * Loads a scene from a FXML file.
   *
   * @return the layout
   * @throws IllegalStateException if error occurs while loading layout
   */
  @Override
  public Scene load() throws IllegalStateException {
    // load the layout
    try {
      Scene scene = this.loader.load();
      scene.getRoot().setStyle("-fx-base:black");
      return scene;
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}
