# Java FX fxids

## week_view.fxml
- Top of page:
	- `weekTitle` label at top of page for title of the week
- Controls:
	- `newEvent` button to create new event
	- `newTask` button to create new task
	- `newWeek` button to create new week
	- `saveWeek` button to save week to file
	- `openWeek` button to open week from file
- Week Overview:
	- `totalEvents` label showing total events. default value: `Total Events = `
	- `totalTasks` label showing total tasks. default value: `Total Tasks = `
	- `tasksCompleted` label showing task completion %. default value: `Tasks Completed = %`
- Limits:
	- `taskLimit` label showing task limit. default value: `Task Limit = `
	- `eventLimit` label showing event limit. default value: `Event Limit = `
- Days:
	- Each enum value for day of the week has a VBox associated with the enum name
		- e.g. `MONDAY`, `TUESDAY` etc.
- Task Queue:
	- `taskQueue` A VBox for listing all the tasks for the week


## new_week.fxml
- `weekName` text field for name of the week
- `weekPath` text field for path to the file
- `weekMaxEvents` text field for max number of events per day
- `weekMaxTasks` text field for max number of tasks per day
- `weekCreate` button to create the week

## open_week.fxml
- `weekPath` text field for path to week file
- `weekOpen` button to open week

## new_event.fxml
- `eventTitle` text field for event title
- `eventDescription` text field for event description
- `eventStartTime` text field for event start time
- `eventDuration` text field for event duration
- `dayToAddTo` choice box for selecting day to add to
- `buttonAdd` button to add the event

## new_event.fxml
- `taskTitle` text field for event title
- `taskDescription` text field for event description
- `dayToAddTo` choice box for selecting day to add to
- `buttonAdd` button to add the event
