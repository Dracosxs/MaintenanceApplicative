import elementEvenement.EventId;

import java.util.Scanner;

/**
 * Action class for deleting an event by its ID.
 */
public class SupprimerEventAction implements Action {
    @Override
    public boolean executer(Scanner scanner, CalendarManager manager, String utilisateur) {
        System.out.println("\n=== Suppression d'un événement ===");
        
        // First show all events to help user identify which to delete
        System.out.println("Événements disponibles :");
        manager.afficherEvenements();
        
        System.out.print("\nEntrez l'UUID de l'événement à supprimer : ");
        String uuidString = scanner.nextLine();
        
        try {
            // Parse the UUID string into an EventId
            EventId eventId = new EventId(uuidString);
            
            // Try to delete the event
            boolean deleted = manager.supprimerEvent(eventId);
            
            // Use a ternary operator for the message since it's a user-facing output
            String message = deleted 
                ? "Événement supprimé avec succès."
                : "Aucun événement trouvé avec cet identifiant.";
            
            System.out.println(message);
        } catch (IllegalArgumentException e) {
            // Handle invalid UUID format
            System.out.println("Format d'UUID invalide : " + e.getMessage());
        }
        
        return true;
    }
}