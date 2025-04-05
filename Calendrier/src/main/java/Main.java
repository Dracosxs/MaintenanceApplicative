import elementEvenement.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        CalendarManager calendar = new CalendarManager();
        Scanner scanner = new Scanner(System.in);
        String utilisateur = null;
        boolean continuer = true;

        // Initialize authentication service
        AuthService authService = new AuthServiceImpl();

        // Initialize the action map for the main menu
        Map<String, Action> actions = new HashMap<>();
        actions.put("1", new AfficherEvenementsAction());
        actions.put("2", new AjouterRdvAction());
        actions.put("3", new AjouterReunionAction());
        actions.put("4", new AjouterPeriodiqueAction());
        actions.put("5", new SupprimerEventAction()); // New action for deleting events
        actions.put("6", new QuitterAction());        // Moved to option 6

        // Initialize the authentication action map
        Map<String, AuthentificationAction> authActions = new HashMap<>();
        authActions.put("1", new ConnexionExistanteAction());
        authActions.put("2", new CreationCompteAction());
        
        // Create a supplier for the default authentication action
        Supplier<AuthentificationAction> defaultAuthAction = ActionInvalideAuthentification::new;

        while (true) {
            // Authentication section - now refactored without conditionals
            while (utilisateur == null) {
                displayAuthenticationBanner();
                System.out.println("1 - Se connecter");
                System.out.println("2 - Créer un compte");
                System.out.println("Choix : ");

                String choix = scanner.nextLine();
                
                // Get the appropriate authentication action
                AuthentificationAction authAction = authActions.getOrDefault(choix, defaultAuthAction.get());
                
                // Execute the authentication action
                utilisateur = authAction.executer(scanner, authService);
            }

            // Main application loop - already refactored to use Action pattern
            continuer = true;
            while (continuer && utilisateur != null) {
                // Display menu
                System.out.println("\nBonjour, " + utilisateur);
                System.out.println("=== Menu Gestionnaire d'Événements ===");
                System.out.println("1 - Voir les événements");
                System.out.println("2 - Ajouter un rendez-vous perso");
                System.out.println("3 - Ajouter une réunion");
                System.out.println("4 - Ajouter un évènement périodique");
                System.out.println("5 - Supprimer un événement"); // New menu option
                System.out.println("6 - Se déconnecter");         // Moved to option 6
                System.out.print("Votre choix : ");
                
                // Get user choice
                String choix = scanner.nextLine();
                
                // Get corresponding action from map
                Action action = actions.getOrDefault(choix, actions.get("6")); // Default is now 6 for QuitterAction
                
                // Execute the action
                continuer = action.executer(scanner, calendar, utilisateur);
                
                // If action returned false, user wants to log out
                if (!continuer) {
                    utilisateur = null;
                }
            }
        }
    }

    /**
     * Displays the authentication banner.
     */
    private static void displayAuthenticationBanner() {
        System.out.println("  _____         _                   _                __  __");
        System.out.println(" / ____|       | |                 | |              |  \\/  |");
        System.out.println(
                "| |       __ _ | |  ___  _ __    __| |  __ _  _ __  | \\  / |  __ _  _ __    __ _   __ _   ___  _ __");
        System.out.println(
                "| |      / _` || | / _ \\| '_ \\  / _` | / _` || '__| | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '__|");
        System.out.println(
                "| |____ | (_| || ||  __/| | | || (_| || (_| || |    | |  | || (_| || | | || (_| || (_| ||  __/| |");
        System.out.println(
                " \\_____| \\__,_||_| \\___||_| |_| \\__,_| \\__,_||_|    |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_|");
        System.out.println(
                "                                                                                   __/ |");
        System.out.println(
                "                                                                                  |___/");
    }
}
