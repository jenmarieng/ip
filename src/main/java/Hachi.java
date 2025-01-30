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
        do {
            line = in.nextLine();
            System.out.println(line);
        } while (!line.equals("bye"));
        System.out.println("Bye. Hope to see you again soon!");
    }
}
