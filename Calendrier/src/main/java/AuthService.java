/**
 * Interface for authentication services in the calendar application.
 */
public interface AuthService {
    /**
     * Authenticates a user with the given username and password.
     *
     * @param username The username
     * @param password The password
     * @return The authenticated username or null if authentication fails
     */
    String authenticate(String username, String password);
    
    /**
     * Creates a new user account.
     *
     * @param username The username
     * @param password The password
     * @return true if the account was created successfully, false otherwise
     */
    boolean createAccount(String username, String password);
}