import java.util.Scanner;

/**
 * Action class for creating a new user account.
 */
public class CreationCompteAction implements AuthentificationAction {
    @Override
    public String executer(Scanner scanner, AuthService authService) {
        System.out.print("Nom d'utilisateur: ");
        String username = scanner.nextLine();
        
        System.out.print("Mot de passe: ");
        String password = scanner.nextLine();
        
        System.out.print("Répéter mot de passe: ");
        String confirmPassword = scanner.nextLine();
        
        // Verify passwords match
        if (!password.equals(confirmPassword)) {
            System.out.println("Les mots de passes ne correspondent pas...");
            return null;
        }
        
        // Create the account
        boolean created = authService.createAccount(username, password);
        
        if (created) {
            System.out.println("Compte créé avec succès.");
            return username;
        } else {
            System.out.println("Échec de la création du compte.");
            return null;
        }
    }
}