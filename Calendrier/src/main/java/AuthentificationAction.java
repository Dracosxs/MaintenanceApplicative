import java.util.Scanner;

/**
 * Interface for authentication actions in the calendar application.
 */
public interface AuthentificationAction {
    /**
     * Executes the authentication action.
     *
     * @param scanner The scanner for user input
     * @param authService The authentication service
     * @return The authenticated username or null if authentication failed
     */
    String executer(Scanner scanner, AuthService authService);
}