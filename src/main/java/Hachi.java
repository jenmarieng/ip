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

        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(line);
        }
    }
}
