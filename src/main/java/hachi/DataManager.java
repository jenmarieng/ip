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

public class DataManager {
    private static final String DIRECTORY_PATH = "./data";
    public static final int TASK_TYPE_INDEX = 1;
    public static final int TASK_STATUS_INDEX = 4;
    public static final int TASK_INFO_INDEX = 7;
    public static final int BY_PREFIX_INDEX = "(by :".length();
    public static final int FROM_PREFIX_INDEX = "(from: ".length();
    public static final int TO_PREFIX_INDEX = " to: ".length();

    private final String filePath;

    public DataManager(String filePath) {
        this.filePath = filePath;
    }

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
        case 'T':
            task = new Todo(taskInfo);
            break;
        case 'D': {
            int byIndex = taskInfo.indexOf("(by: ");
            String description = taskInfo.substring(0, byIndex).trim();
            String by = taskInfo.substring(byIndex + BY_PREFIX_INDEX, taskInfo.length() - 1).trim();
            task = new Deadline(description, by);
            break;
        }
        case 'E': {
            int fromIndex = taskInfo.indexOf("(from: ");
            int toIndex = taskInfo.indexOf(" to: ");
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