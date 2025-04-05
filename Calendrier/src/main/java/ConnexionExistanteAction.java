import java.util.Scanner;

/**
 * Action class for logging in with existing credentials.
 */
public class ConnexionExistanteAction implements AuthentificationAction {
    @Override
    public String executer(Scanner scanner, AuthService authService) {
        System.out.print("Nom d'utilisateur: ");
        String username = scanner.nextLine();
        
        System.out.print("Mot de passe: ");
        String password = scanner.nextLine();
        
        String authenticatedUser = authService.authenticate(username, password);
        
        if (authenticatedUser == null) {
            System.out.println("Authentification échouée. Nom d'utilisateur ou mot de passe incorrect.");
        }
        
        return authenticatedUser;
    }
}