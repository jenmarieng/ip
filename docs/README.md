# Hachi User Guide

Hachi is a **desktop program for managing to-do tasks, optimised for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). These tasks can be categorised into 3 types: todo, deadline (includes a deadline date), event (includes an event start date and end date).

## Features
> [!NOTE]
> Words in UPPER_CASE are the parameters to be supplied by the user.
> e.g. in `todo TASK_NAME`, `TASK_NAME` is a parameter which can be used as `todo midterms revision`.
> Extraneous parameters for commands that do not take in parameters (such as list and bye) will be ignored.
> e.g. if the command specifies `list 123`, it will be interpreted as `list`.

### Adding todo : `todo`

Adds a todo task.

Format: `todo TASK_NAME`

Example: `todo watch lecture`

A confirmation message will be printed, indicating that the task has been successfully added, if no errors occur.
```
Arf! I've added this task:
  [T][ ] watch lecture
Now you have 1 tasks in the list.
```

### Adding deadline : `deadline`

Adds a deadline task.

Format: `deadline TASK_NAME /by DATE`

Example: `deadline watch lecture /by tmr`

A confirmation message will be printed, indicating that the task has been successfully added, if no errors occur.
```
Arf! I've added this task:
  [D][ ] watch lecture (by: tmr)
Now you have 1 tasks in the list.
```

### Adding event : `event`

Adds an event task.

Format: `event TASK_NAME /from START_DATE /to END_DATE`

Example: `event attend lecture /from fri 4pm /to 6pm`

A confirmation message will be printed, indicating that the task has been successfully added, if no errors occur.
```
Arf! I've added this task:
  [E][ ] attend lecture (from: fri 4pm to: 6pm)
Now you have 1 tasks in the list.
```

### Listing tasks : `list`

Lists all tasks added.

Format: `list`

All the existing tasks will be listed, if no errors occur.
```
Woof! I've fetched the tasks in your list:
1. [D][ ] watch lecture (by: tmr)
2. [T][ ] cs2113 ip
3. [E][ ] formal dinner (from: 6pm to: 9pm)
```

### Deleting task : `delete`

Deletes a task.

Format: `delete TASK_INDEX`

Example: `delete 2`

A confirmation message will be printed, indicating that the task has been successfully deleted, if no errors occur.
```
Ruff. Task deleted:
  [T][ ] cs2113 ip
Now you have 2 tasks in the list.
```

### Marking task : `mark`

Marks a task as done.

Format: `mark TASK_INDEX`

Example: `mark 1`

A confirmation message will be printed, indicating that the task has been successfully marked as done, if no errors occur.
```
Proud of you! I've marked this task as done:
  [D][X] watch lecture (by: tmr)
```

### Unmarking task : `unmark`

Unmarks a task as done.

Format: `unmark TASK_INDEX`

Example: `unmark 1`

A confirmation message will be printed, indicating that the task has been successfully marked as done, if no errors occur.
```
Okay, I've marked this task as not done yet:
  [D][ ] watch lecture (by: tmr)
```

### Finding task : `find`

Finds tasks that contain the keyword in their task description.

Format: `find KEYWORD`

Example: `find ip`

A confirmation message will be printed, indicating that the task has been successfully marked as done, if no errors occur.
```
Woof! I've fetched the tasks that match:
1. [T][ ] cs2113 ip
```

### Exiting the program : `bye`

Exits the program

Format: `exit`

A goodbye message will be printed.
```
Bye. Hope to see you again soon!
```

### Saving the data
Hachi data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
