public class TaskManager {
    public static final int MAX_NUMBER_OF_TASKS = 100;
    public static final int TODO_PREFIX_LENGTH = "todo ".length();
    public static final int DEADLINE_PREFIX_LENGTH = "deadline ".length();
    public static final int EVENT_PREFIX_LENGTH = "event ".length();
    public static final int BY_PREFIX_LENGTH = "/by".length();
    public static final int FROM_PREFIX_LENGTH = "/from".length();
    public static final int TO_PREFIX_LENGTH = "/to".length();
    public static final int MARK_PREFIX_LENGTH = "mark".length();
    public static final int UNMARK_PREFIX_LENGTH = "unmark".length();

    private Task[] tasks = new Task[MAX_NUMBER_OF_TASKS];
    private int tasksCount = 0;

    public void addTask(String taskType, String taskInfo) throws HachiException {
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
            throw new HachiException("Woof? I don't understand. Try starting with todo, deadline or event!");
        }

        if (task == null) {
            return;
        }
        tasks[tasksCount] = task;
        tasksCount++;
        System.out.println("Arf! I've added this task:\n  " + task + "\nNow you have " + tasksCount + " tasks in the list.");
    }

    public Task addTodo(String taskInfo) throws HachiException {
        if (taskInfo.length() <= TODO_PREFIX_LENGTH) {
            throw new HachiException("Woof? So, what do you want to do?");
        }

        String description = taskInfo.substring(TODO_PREFIX_LENGTH).trim();
        return new Todo(description);
    }

    public Task addDeadline(String taskInfo) throws HachiException {
        if (taskInfo.length() <= DEADLINE_PREFIX_LENGTH) {
            throw new HachiException("Woof? So, what do you want to do?");
        }

        int byIndex = taskInfo.indexOf("/by");
        if (byIndex == -1 || byIndex == taskInfo.length() - BY_PREFIX_LENGTH) {
            throw new HachiException("Ruff! Invalid deadline format :( it should be 'deadline (task name) /by (deadline)'.");
        }
        String description = taskInfo.substring(DEADLINE_PREFIX_LENGTH, byIndex).trim();
        String by = taskInfo.substring(byIndex + BY_PREFIX_LENGTH).trim();
        if (by.isEmpty() || description.isEmpty()) {
            throw new HachiException("Woof woof... Deadline needs a description and date.");
        }
        return new Deadline(description, by);
    }

    public Task addEvent(String taskInfo) throws HachiException {
        if (taskInfo.length() <= EVENT_PREFIX_LENGTH) {
            throw new HachiException("Woof? So, what do you want to do?");
        }

        int fromIndex = taskInfo.indexOf("/from");
        int toIndex = taskInfo.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1 || fromIndex == taskInfo.length() - FROM_PREFIX_LENGTH
                || toIndex == taskInfo.length() - TO_PREFIX_LENGTH) {
            throw new HachiException("Ruff! Invalid event format :( it should be 'event (task name) /from (start date) /to (end date)'.");
        }
        String description = taskInfo.substring(EVENT_PREFIX_LENGTH, fromIndex).trim();
        String from = taskInfo.substring(fromIndex + FROM_PREFIX_LENGTH, toIndex).trim();
        String to = taskInfo.substring(toIndex + TO_PREFIX_LENGTH).trim();
        if (from.isEmpty() || to.isEmpty() || description.isEmpty()) {
            throw new HachiException("Woof woof... Event needs a description, start and end dates.");
        }
        return new Event(description, from, to);
    }

    public void listTasks() {
        if (tasksCount == 0) {
            System.out.println("Woof? No tasks added yet.");
        } else {
            System.out.println("Woof! I've fetched the tasks in your list:");
            for (int i = 0; i < tasksCount; i++) {
                System.out.println((i + 1) + "." + tasks[i].toString());
            }
        }
    }

    public void markTaskAsDone(String userInput) {
        try {
            int taskIndex = Integer.parseInt(userInput.substring(MARK_PREFIX_LENGTH).trim());
            if (!tasks[taskIndex - 1].isTaskDone()) {
                tasks[taskIndex - 1].markAsDone();
                System.out.println("Proud of you! I've marked this task as done:\n  " + tasks[taskIndex - 1].toString());
            } else {
                System.out.println("You've already done this task!");
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("That's im-paw-sible... there is no such task...");
        } catch (NumberFormatException e) {
            System.out.println("It's im-paw-sible for that to be a number! It should be 'mark (task index)'.");
        }
    }

    public void markTaskAsNotDone(String userInput) {
        try {
            int taskIndex = Integer.parseInt(userInput.substring(UNMARK_PREFIX_LENGTH).trim());
            if (tasks[taskIndex - 1].isTaskDone()) {
                tasks[taskIndex - 1].markAsNotDone();
                System.out.println("Okay, I've marked this task as not done yet:\n  " + tasks[taskIndex - 1].toString());
            } else {
                System.out.println("The task is already marked as not done!");
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("That's im-paw-sible... there is no such task...");
        } catch (NumberFormatException e) {
            System.out.println("It's im-paw-sible for that to be a number! It should be 'unmark (task index)'.");
        }
    }
}
