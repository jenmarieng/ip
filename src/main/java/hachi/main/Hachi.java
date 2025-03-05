package hachi.main;

import java.util.Scanner;

import hachi.Ui;
import hachi.DataManager;
import hachi.command.Command;
import hachi.task.TaskManager;
import hachi.Parser;

/**
 * The main class for the Hachi program, responsible for initialising
 * and running the task management system.
 */
public class Hachi {

    private final Ui ui;
    private final TaskManager taskManager;
    private final DataManager dataManager;

    /**
     * Constructs a Hachi instance.
     *
     * @param filePath The file path where task data is stored.
     */
    public Hachi(String filePath) {
        this.ui = new Ui();
        this.dataManager = new DataManager(filePath);
        this.taskManager = new TaskManager(dataManager.loadTasksData());
    }

    /**
     * Runs the main loop of the application, processing user input
     * and executing commands until the user decides to exit.
     */
    public void run() {
        ui.printHello();
        String userInput;

        boolean isExit = false;
        while (!isExit) {
            try {
                userInput = ui.readUserInput();
                Command command = Parser.parse(userInput);
                command.execute(taskManager, ui, dataManager);
                isExit = command.isExit();
            } catch (HachiException e) {
                ui.printError(e);
            }
        }
        ui.printBye();
    }

    /**
     * The main entry point of the program.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Hachi("./data/hachi.txt").run();
    }
}
