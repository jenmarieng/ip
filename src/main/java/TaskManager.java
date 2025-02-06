public class TaskManager {
    public static final int MAX_NUMBER_OF_TASKS = 100;
    private Task[] tasks = new Task[MAX_NUMBER_OF_TASKS];
    private int tasksCount = 0;

    public void addTask(String taskType, String taskInfo) {
        Task task;

        switch (taskType) {
        case "todo":
            task = addTodo(taskInfo);
            break;
        case "deadline":
            task = addDeadline(taskInfo);
            break;
        case "event":
            task = addEvent(taskInfo);
            break;
        default:
            System.out.println("Woof? I don't understand. Try starting with todo, deadline or event!");
            return;
        }

        if (task == null) {
            return;
        }
        tasks[tasksCount] = task;
        tasksCount++;
        System.out.println("Got it! I've added this task:\n  " + task + "\nNow you have " + tasksCount + " tasks in the list.");
    }

    public Task addTodo(String taskInfo) {
        String description = taskInfo.substring(5).trim();
        return new Todo(description);
    }

    public Task addDeadline(String taskInfo) {
        int byIndex = taskInfo.indexOf("/by");
        if (byIndex == -1) {
            System.out.println("Invalid deadline format :(");
            return null;
        }
        String description = taskInfo.substring(9, byIndex).trim();
        String by = taskInfo.substring(byIndex + 4).trim();
        return new Deadline(description, by);
    }

    public Task addEvent(String taskInfo) {
        int fromIndex = taskInfo.indexOf("/from");
        int toIndex = taskInfo.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1) {
            System.out.println("Invalid event format :(");
            return null;
        }
        String description = taskInfo.substring(6, fromIndex).trim();
        String from = taskInfo.substring(fromIndex + 6, toIndex).trim();
        String to = taskInfo.substring(toIndex + 4).trim();
        return new Event(description, from, to);
    }

    public void listTasks() {
        if (tasksCount == 0) {
            System.out.println("Woof? No tasks added yet.");
        } else {
            System.out.println("Woof! Here are the tasks in your list:");
            for (int i = 0; i < tasksCount; i++) {
                System.out.println((i + 1) + "." + tasks[i].toString());
            }
        }
    }

    public boolean isValidTaskIndex(int taskIndex) {
        return taskIndex > 0 && taskIndex <= tasksCount;
    }

    public void markTaskAsDone(int taskIndex) {
        if (isValidTaskIndex(taskIndex)) {
            if (!tasks[taskIndex - 1].isTaskDone()) {
                tasks[taskIndex - 1].markAsDone();
                System.out.println("Proud of you! I've marked this task as done:\n  " + tasks[taskIndex - 1].toString());
            } else {
                System.out.println("You've already done this task!");
            }
        } else {
            System.out.println("Woof... there is no such task...");
        }
    }

    public void markTaskAsNotDone(int taskIndex) {
        if (isValidTaskIndex(taskIndex)) {
            if (tasks[taskIndex - 1].isTaskDone()) {
                tasks[taskIndex - 1].markAsNotDone();
                System.out.println("Okay, I've marked this task as not done yet:\n  " + tasks[taskIndex - 1].toString());
            } else {
                System.out.println("The task is already marked as not done!");
            }
        } else {
            System.out.println("Woof... there is no such task...");
        }
    }
}
