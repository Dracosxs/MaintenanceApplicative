import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Implementation of the AuthService interface using a Map to store credentials.
 */
public class AuthServiceImpl implements AuthService {
    private final Map<String, String> credentials;
    
    /**
     * Creates a new AuthServiceImpl with pre-defined users.
     */
    public AuthServiceImpl() {
        this.credentials = new HashMap<>();
        // Add predefined users
        credentials.put("Roger", "Chat");
        credentials.put("Pierre", "KiRouhl");
    }
    
    @Override
    public String authenticate(String username, String password) {
        // Guard clause for null inputs
        if (username == null || password == null) {
            return null;
        }
        
        // Check if the provided credentials match
        String storedPassword = credentials.get(username);
        return Objects.equals(storedPassword, password) ? username : null;
    }
    
    @Override
    public boolean createAccount(String username, String password) {
        // Guard clauses
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            return false;
        }
        
        // Check if user already exists
        if (credentials.containsKey(username)) {
            return false;
        }
        
        // Store new credentials
        credentials.put(username, password);
        return true;
    }
}