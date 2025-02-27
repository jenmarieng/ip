package hachi.task;

import hachi.main.HachiException;

import java.util.ArrayList;


public class TaskManager {
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final int TODO_PREFIX_LENGTH = "todo ".length();
    public static final int DEADLINE_PREFIX_LENGTH = "deadline ".length();
    public static final int EVENT_PREFIX_LENGTH = "event ".length();
    public static final int BY_PREFIX_LENGTH = "/by".length();
    public static final int FROM_PREFIX_LENGTH = "/from".length();
    public static final int TO_PREFIX_LENGTH = "/to".length();
    public static final int MARK_PREFIX_LENGTH = "mark".length();
    public static final int UNMARK_PREFIX_LENGTH = "unmark".length();
    public static final int DELETE_PREFIX_LENGTH = "delete".length();

    private final ArrayList<Task> tasks;

    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

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

    public String markTaskAsDone(String userInput) {
        try {
            int taskIndex = Integer.parseInt(userInput.substring(MARK_PREFIX_LENGTH).trim()) - 1;
            if (!tasks.get(taskIndex).isTaskDone()) {
                tasks.get(taskIndex).markAsDone();
                return ("Proud of you! I've marked this task as done:\n  " + tasks.get(taskIndex).toString());
            } else {
                return ("You've already done this task!");
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return ("That's im-paw-sible... there is no such task...");
        } catch (NumberFormatException e) {
            return ("It's im-paw-sible for that to be a number! It should be 'mark (task index)'.");
        }
    }

    public String markTaskAsNotDone(String userInput) {
        try {
            int taskIndex = Integer.parseInt(userInput.substring(UNMARK_PREFIX_LENGTH).trim()) - 1;
            if (tasks.get(taskIndex).isTaskDone()) {
                tasks.get(taskIndex).markAsNotDone();
                return ("Okay, I've marked this task as not done yet:\n  " + tasks.get(taskIndex).toString());
            } else {
                return ("The task is already marked as not done!");
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return ("That's im-paw-sible... there is no such task...");
        } catch (NumberFormatException e) {
            return ("It's im-paw-sible for that to be a number! It should be 'unmark (task index)'.");
        }
    }
}
