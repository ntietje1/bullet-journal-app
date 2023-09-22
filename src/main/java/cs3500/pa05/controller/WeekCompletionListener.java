package cs3500.pa05.controller;

import cs3500.pa05.model.Week;

/**
 * Represents an object that can listen for the completion of a controller
 */
public interface WeekCompletionListener {

  /**
   * Do this when the controller is completed
   *
   * @param week week to pass along to parent controller
   */
  void onWeekComplete(Week week);
}