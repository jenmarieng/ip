package hachi;

import java.util.Scanner;

/**
 * Handles user interface interactions.
 */
public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a welcome message.
     */
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

    /**
     * Prints a goodbye message.
     */
    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints an error message.
     *
     * @param e The exception containing the error message.
     */
    public void printError(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Reads user input from the console.
     *
     * @return The user input string.
     */
    public String readUserInput() {
        return scanner.nextLine();
    }

    /**
     * Prints command output to the console.
     *
     * @param output The output to display.
     */
    public void printCommandOutput(String output) {
        System.out.println(output);
    }
}
