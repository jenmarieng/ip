package hachi;

import hachi.command.AddCommand;
import hachi.command.ByeCommand;
import hachi.command.Command;
import hachi.command.DeleteCommand;
import hachi.command.FindCommand;
import hachi.command.ListCommand;
import hachi.command.MarkCommand;
import hachi.command.UnmarkCommand;

/**
 * Parses user input and returns the corresponding command.
 */
public class Parser {

    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String DELETE = "delete";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String FIND = "find";

    /**
     * Parses user input and returns the corresponding command.
     *
     * @param userInput The input string.
     * @return The corresponding command object.
     * @throws HachiException If the input is invalid.
     */
    public static Command parse(String userInput) throws HachiException {
        String[] fullInput = userInput.split(" ");
        String commandType = fullInput[0].toLowerCase();

        try {
            switch (commandType) {
            case BYE:
                return new ByeCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                return new MarkCommand(userInput);
            case UNMARK:
                return new UnmarkCommand(userInput);
            case DELETE:
                return new DeleteCommand(userInput);
            case TODO, DEADLINE, EVENT:
                return new AddCommand(commandType, userInput);
            case FIND:
                return new FindCommand(userInput);
            default:
                throw new HachiException("Woof? I don't understand. Try starting with list, find, todo, deadline, event, delete, mark or unmark!");
            }
        } catch (HachiException e) {
            throw new HachiException(e.getMessage());
        }
    }
}
