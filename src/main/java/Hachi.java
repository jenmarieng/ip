import java.util.Scanner;

public class Hachi {
    public static void main(String[] args) {
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
            int taskIndex;

            switch (commandType) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                return;
            case "list":
                taskManager.listTasks();
                break;
            case "mark":
                taskIndex = Integer.parseInt(userInput.substring(5).trim());
                taskManager.markTaskAsDone(taskIndex);
                break;
            case "unmark":
                taskIndex = Integer.parseInt(userInput.substring(7).trim());
                taskManager.markTaskAsNotDone(taskIndex);
                break;
            default:
                taskManager.addTask(commandType, userInput);
            }
        }
    }
}
