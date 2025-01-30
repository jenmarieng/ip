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
                taskManager.markTaskAsDone(line);
            } else if (line.startsWith("unmark")) {
                taskManager.markTaskAsNotDone(line);
            } else {
                taskManager.addTask(line);
            }
        }
    }
}
