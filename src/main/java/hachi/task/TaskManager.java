package hachi.task;

import hachi.HachiException;

import java.util.ArrayList;

/**
 * Manages tasks by adding, deleting, listing, searching, and updating their status.
 */
public class TaskManager {
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final int TODO_PREFIX_LENGTH = "todo ".length();
    private static final int DEADLINE_PREFIX_LENGTH = "deadline ".length();
    private static final int EVENT_PREFIX_LENGTH = "event ".length();
    private static final String BY_PREFIX = "/by ";
    private static final String FROM_PREFIX = "/from ";
    private static final String TO_PREFIX = "/to ";
    private static final int BY_PREFIX_LENGTH = "/by ".length();
    private static final int FROM_PREFIX_LENGTH = "/from ".length();
    private static final int TO_PREFIX_LENGTH = "/to ".length();
    private static final int MARK_PREFIX_LENGTH = "mark".length();
    private static final int UNMARK_PREFIX_LENGTH = "unmark".length();
    private static final int DELETE_PREFIX_LENGTH = "delete".length();
    private static final int FIND_PREFIX_LENGTH = "find ".length();

    private final ArrayList<Task> tasks;

    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task based on the task type and description provided.
     *
     * @param taskType The type of task (todo, deadline, event).
     * @param taskInfo The task details.
     * @return Confirmation message that a task has been added or an error has occurred.
     */
    public String addTask(String taskType, String taskInfo) {
        Task task;

        try {
            switch (taskType) {
            case TODO:
                task = addTodo(taskInfo);
                break;
            case DEADLINE:
                task = addDeadline(taskInfo);
                break;
            case EVENT:
                task = addEvent(taskInfo);
                break;
            default:
                return ("Woof? I don't understand. Try starting with todo, deadline or event!");
            }
            tasks.add(task);
            return ("Arf! I've added this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
        } catch (HachiException e) {
            return e.getMessage();
        }
    }

    /**
     * Adds a Todo task based on user input.
     *
     * @param taskInfo The full command input from the user.
     * @return A Todo task with the specified description.
     * @throws HachiException If the description is missing.
     */
    public Task addTodo(String taskInfo) throws HachiException {
        if (taskInfo.length() <= TODO_PREFIX_LENGTH) {
            throw new HachiException("Woof? So, what do you want to do?");
        }

        String description = taskInfo.substring(TODO_PREFIX_LENGTH).trim();
        if (description.isEmpty()) {
            throw new HachiException("Woof woof... Todo needs a task description.");
        }
        return new Todo(description);
    }

    /**
     * Adds a Deadline task based on user input.
     *
     * @param taskInfo The full command input from the user.
     * @return A Deadline task with the specified description and due date.
     * @throws HachiException If the description or due date is missing or incorrectly formatted.
     */
    public Task addDeadline(String taskInfo) throws HachiException {
        if (taskInfo.length() <= DEADLINE_PREFIX_LENGTH) {
            throw new HachiException("Woof? So, what do you want to do?");
        }

        int byIndex = taskInfo.indexOf(BY_PREFIX);
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

    /**
     * Adds an Event task based on user input.
     *
     * @param taskInfo The full command input from the user.
     * @return An Event task with the specified description, start time, and end time.
     * @throws HachiException If any required details are missing or incorrectly formatted.
     */
    public Task addEvent(String taskInfo) throws HachiException {
        if (taskInfo.length() <= EVENT_PREFIX_LENGTH) {
            throw new HachiException("Woof? So, what do you want to do?");
        }

        int fromIndex = taskInfo.indexOf(FROM_PREFIX);
        int toIndex = taskInfo.indexOf(TO_PREFIX);
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

    /**
     * Deletes a task from task list.
     *
     * @param userInput The index of the task to delete.
     * @return Confirmation message that a task has been deleted or an error has occurred.
     */
    public String deleteTask(String userInput) {
        try {
            int taskIndex = Integer.parseInt(userInput.substring(DELETE_PREFIX_LENGTH).trim()) - 1;
            Task removedTask = tasks.get(taskIndex);
            tasks.remove(taskIndex);
            return ("Ruff. Task deleted:\n  " + removedTask.toString() + "\nNow you have " + tasks.size() + " tasks in the list.");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return ("That's im-paw-sible... there is no such task...");
        } catch (NumberFormatException e) {
            return ("It's im-paw-sible for that to be a number! It should be 'delete (task index)'.");
        }
    }

    /**
     * Lists all tasks.
     *
     * @return A formatted string of all tasks.
     */
    public String listTasks() {
        if (tasks.isEmpty()) {
            return ("Woof? No tasks added yet.");
        } else {
            StringBuilder output = new StringBuilder("Woof! I've fetched the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                output.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
            }
            return output.toString();
        }
    }

    /**
     * Marks a task as done.
     *
     * @param userInput The index of the task to mark as done.
     * @return Confirmation message that the task has been marked done or an error has occurred.
     */
    public String markTaskAsDone(String userInput) {
        try {
            int taskIndex = Integer.parseInt(userInput.substring(MARK_PREFIX_LENGTH).trim()) - 1;
            Task task = tasks.get(taskIndex);
            if (!task.isTaskDone()) {
                task.markAsDone();
                return ("Proud of you! I've marked this task as done:\n  " + task.toString());
            } else {
                return ("You've already done this task!");
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return ("That's im-paw-sible... there is no such task...");
        } catch (NumberFormatException e) {
            return ("It's im-paw-sible for that to be a number! It should be 'mark (task index)'.");
        }
    }

    /**
     * Marks a task as not done.
     *
     * @param userInput The index of the task to mark as not done.
     * @return Confirmation message that the task has been marked not done or an error has occurred.
     */
    public String markTaskAsNotDone(String userInput) {
        try {
            int taskIndex = Integer.parseInt(userInput.substring(UNMARK_PREFIX_LENGTH).trim()) - 1;
            Task task = tasks.get(taskIndex);
            if (task.isTaskDone()) {
                task.markAsNotDone();
                return ("Okay, I've marked this task as not done yet:\n  " + task.toString());
            } else {
                return ("The task is already marked as not done!");
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return ("That's im-paw-sible... there is no such task...");
        } catch (NumberFormatException e) {
            return ("It's im-paw-sible for that to be a number! It should be 'unmark (task index)'.");
        }
    }

    public String findTask(String userInput) {
        try {
            String keyword = userInput.substring(FIND_PREFIX_LENGTH).trim();
            if (keyword.isEmpty()) {
                return ("Ruff? So, what do you want me to fetch?");
            }
            ArrayList<Task> matchedTasks = new ArrayList<>();
            for (Task task : tasks) {
                String taskDescription = task.getDescription();
                if (taskDescription.contains(keyword)) {
                    matchedTasks.add(task);
                }
            }

            if (matchedTasks.isEmpty()) {
                return ("Woof? No such tasks.");
            } else {
                StringBuilder output = new StringBuilder("Woof! I've fetched the tasks that match:\n");
                for (int i = 0; i < matchedTasks.size(); i++) {
                    output.append(i + 1).append(". ").append(matchedTasks.get(i).toString()).append("\n");
                }
                return output.toString();
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return ("Arf... Try again with 'find (keyword)', eg. 'find book'.");
        }
    }
}
