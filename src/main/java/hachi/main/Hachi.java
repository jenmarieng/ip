package hachi.main;

import java.util.Scanner;
import hachi.task.TaskManager;

public class Hachi {
    public static void main(String[] args) throws HachiException {
        System.out.println("""
                Hello! I'm Hachi
                          __
                 \\ ______/ U`-,    (WOOF!)
                  (        /~~
                 /_)^ --,/'
                |_      |_
                What can I do for you?""");

        String userInput;
        Scanner in = new Scanner(System.in);

        TaskManager taskManager = new TaskManager();

        while (true) {
            userInput = in.nextLine();
            String[] inputWords = userInput.split(" ");
            String commandType = inputWords[0];

            try {
                switch (commandType) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    taskManager.listTasks();
                    break;
                case "mark":
                    taskManager.markTaskAsDone(userInput);
                    break;
                case "unmark":
                    taskManager.markTaskAsNotDone(userInput);
                    break;
                case "delete":
                    taskManager.deleteTask(userInput);
                    break;
                default:
                    taskManager.addTask(commandType, userInput);
                }
            } catch (HachiException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
