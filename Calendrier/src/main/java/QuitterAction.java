import java.util.Scanner;

/**
 * Action class for logging out from the calendar application.
 */
public class QuitterAction implements Action {
    @Override
    public boolean executer(Scanner scanner, CalendarManager manager, String utilisateur) {
        System.out.println("Déconnexion ! Voulez-vous continuer ? (oui/non)");
        boolean continuer = scanner.nextLine().trim().equalsIgnoreCase("oui");
        return false; // Return false to exit the authenticated user loop
    }
}