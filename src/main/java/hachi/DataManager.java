package hachi;

import hachi.task.Deadline;
import hachi.task.Event;
import hachi.task.Task;
import hachi.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles saving and loading of task data.
 */
public class DataManager {
    private static final String DIRECTORY_PATH = "./data";
    private static final int TASK_TYPE_INDEX = 1;
    private static final int TASK_STATUS_INDEX = 4;
    private static final int TASK_INFO_INDEX = 7;
    private static final char TODO = 'T';
    private static final char DEADLINE = 'D';
    private static final char EVENT = 'E';
    private static final String BY_PREFIX = "(by: ";
    private static final String FROM_PREFIX = "(from: ";
    private static final String TO_PREFIX = " to: ";
    private static final int BY_PREFIX_INDEX = BY_PREFIX.length();
    private static final int FROM_PREFIX_INDEX = FROM_PREFIX.length();
    private static final int TO_PREFIX_INDEX = TO_PREFIX.length();

    private final String filePath;

    /**
     * Constructs a DataManager with the specified file path.
     *
     * @param filePath The file path where task data is stored.
     */
    public DataManager(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves tasks to a file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasksData(ArrayList<Task> tasks) {
        try {
            File directory = new File(DIRECTORY_PATH);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(task + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Woooof! Failed to save tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from a file.
     *
     * @return The list of loaded tasks.
     */
    public ArrayList<Task> loadTasksData() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = formatTasksData(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Woooof! Failed to load tasks: " + e.getMessage());
        }
        return tasks;
    }

    private static Task formatTasksData(String line) {
        char taskType = line.charAt(TASK_TYPE_INDEX);
        boolean isDone = line.charAt(TASK_STATUS_INDEX) == 'X';
        String taskInfo = line.substring(TASK_INFO_INDEX);

        Task task;
        switch (taskType) {
        case TODO:
            task = new Todo(taskInfo);
            break;
        case DEADLINE: {
            int byIndex = taskInfo.indexOf(BY_PREFIX);
            String description = taskInfo.substring(0, byIndex).trim();
            String by = taskInfo.substring(byIndex + BY_PREFIX_INDEX, taskInfo.length() - 1).trim();
            task = new Deadline(description, by);
            break;
        }
        case EVENT: {
            int fromIndex = taskInfo.indexOf(FROM_PREFIX);
            int toIndex = taskInfo.indexOf(TO_PREFIX);
            String description = taskInfo.substring(0, fromIndex).trim();
            String from = taskInfo.substring(fromIndex + FROM_PREFIX_INDEX, toIndex).trim();
            String to = taskInfo.substring(toIndex + TO_PREFIX_INDEX, taskInfo.length() - 1).trim();
            task = new Event(description, from, to);
            break;
        }
        default:
            System.out.println("Woof? Invalid task format in saved file: " + line);
            return null;
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}