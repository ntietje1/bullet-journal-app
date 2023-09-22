package cs3500.pa05.view;

import javafx.scene.Scene;

/**
 * Represents a GUI view
 */
public interface View {

  /**
   * Loads a scene from a FXML file.
   *
   * @return the layout
   * @throws IllegalStateException if error occurs while loading layout
   */
  Scene load() throws IllegalStateException;
}
