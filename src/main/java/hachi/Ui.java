package hachi;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void printHello() {
        System.out.println("""
                Hello! I'm Hachi
                          __
                 \\ ______/ U`-,    (WOOF!)
                  (        /~~
                 /_)^ --,/'
                |_      |_
                What can I do for you?""");
    }

    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printError(Exception e) {
        System.out.println(e.getMessage());
    }

    public String readUserInput() {
        return scanner.nextLine();
    }

    public void printCommandOutput(String output) {
        System.out.println(output);
    }
}
