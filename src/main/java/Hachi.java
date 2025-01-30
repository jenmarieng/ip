import java.util.Scanner;

public class Hachi {
    private static String[] tasks = new String[100];
    private static int tasksCount = 0;

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

        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (line.equals("list")) {
                if (tasksCount == 0) {
                    System.out.println("Woof? No tasks added yet.");
                }
                else {
                    for (int i = 0; i < tasksCount; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                }
            }
            else {
                tasks[tasksCount] = line;
                tasksCount++;
                System.out.println("added: " + line);
            }
        }
    }
}
