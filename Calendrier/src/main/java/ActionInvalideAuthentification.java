import java.util.Scanner;

/**
 * Action class for handling invalid authentication choices.
 */
public class ActionInvalideAuthentification implements AuthentificationAction {
    @Override
    public String executer(Scanner scanner, AuthService authService) {
        System.out.println("Option invalide. Veuillez choisir 1 pour vous connecter ou 2 pour créer un compte.");
        return null;
    }
}