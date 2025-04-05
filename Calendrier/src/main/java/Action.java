import java.util.Scanner;

/**
 * Interface representing an action that can be executed in the calendar application.
 * Each action in the menu will implement this interface.
 */
public interface Action {
    /**
     * Executes the action.
     *
     * @param scanner The scanner for user input
     * @param manager The calendar manager containing events
     * @param utilisateur The current user
     * @return true if the application should continue, false otherwise
     */
    boolean executer(Scanner scanner, CalendarManager manager, String utilisateur);
}