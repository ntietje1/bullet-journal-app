package cs3500.pa05.controller;

import cs3500.pa05.model.Week;
import cs3500.pa05.view.WeekView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 * Represents a controller for a bullet journal GUI app
 */
public class BulletJournalController implements Controller, WeekCompletionListener {

  Stage popupStage;

  @FXML
  private TabPane tabpane;

  @FXML
  private Tab defaultWeek;


  /**
   * Instantiates a new bullet journal controller
   */
  public BulletJournalController() {
  }

  /**
   * Run this controller
   */
  @Override
  public void run() {
    this.popupStage = new Stage();
    this.popupStage.hide();
    tabpane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
    this.setUpDefaultWeek();
  }

  /**
   * Add the default week to the tab pane
   */
  private void setUpDefaultWeek() {
    WeekViewController weekViewController =
        new WeekViewController(this, this.popupStage, new Week());
    WeekView weekView = new WeekView(weekViewController);
    Scene scene = weekView.load();
    this.defaultWeek.setContent(scene.getRoot());
    weekViewController.run();
  }

  /**
   * Create a new tab and select it
   *
   * @return the new tab
   */
  private Tab createAndSelectTab() {
    Tab tab = new Tab("New Week");
    final ObservableList<Tab> tabs = tabpane.getTabs();
    tab.setClosable(true);
    tabs.add(tab);
    tabpane.getSelectionModel().select(tab);
    return tab;
  }

  /**
   * Do this when a week has been opened/created
   */
  @Override
  public void onWeekComplete(Week week) {
    this.popupStage.close();
    Tab currentTab = this.createAndSelectTab();
    currentTab.setText(week.getName());
    WeekViewController weekViewController = new WeekViewController(this, this.popupStage, week);
    WeekView weekView = new WeekView(weekViewController);
    Scene scene = weekView.load();
    currentTab.setContent(scene.getRoot());
    weekViewController.run();
  }
}
