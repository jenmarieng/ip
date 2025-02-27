package hachi.main;

import java.util.Scanner;

import hachi.Ui;
import hachi.DataManager;
import hachi.command.Command;
import hachi.task.TaskManager;
import hachi.Parser;

public class Hachi {

    private final Ui ui;
    private final TaskManager taskManager;
    private final DataManager dataManager;

    public Hachi(String filePath) {
        this.ui = new Ui();
        this.dataManager = new DataManager(filePath);
        this.taskManager = new TaskManager(dataManager.loadTasksData());
    }

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

    public static void main(String[] args) {
        new Hachi("./data/hachi.txt").run();
    }
}
