package cs3500.pa05.controller;

/**
 * Controller completion listener interface
 * listens for a sub-controller to finish its task
 */
public interface ControllerCompletionListener {

  /**
   * What to do when sub-controller finishes
   */
  void onControllerComplete();
}
