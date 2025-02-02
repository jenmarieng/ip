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

        String line;
        Scanner in = new Scanner(System.in);

        TaskManager taskManager = new TaskManager();

        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (line.equals("list")) {
                taskManager.listTasks();
            } else if (line.startsWith("mark")) {
                int taskIndex = Integer.parseInt(line.substring(5).trim());
                taskManager.markTaskAsDone(taskIndex);
            } else if (line.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(line.substring(7).trim());
                taskManager.markTaskAsNotDone(taskIndex);
            } else {
                taskManager.addTask(line);
            }
        }
    }
}
