import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

/**
 * Action class for displaying events with various filtering options.
 */
public class AfficherEvenementsAction implements Action {
    @Override
    public boolean executer(Scanner scanner, CalendarManager manager, String utilisateur) {
        System.out.println("\n=== Menu de visualisation d'Événements ===");
        System.out.println("1 - Afficher TOUS les événements");
        System.out.println("2 - Afficher les événements d'un MOIS précis");
        System.out.println("3 - Afficher les événements d'une SEMAINE précise");
        System.out.println("4 - Afficher les événements d'un JOUR précis");
        System.out.println("5 - Retour");
        System.out.print("Votre choix : ");

        String choix = scanner.nextLine();
        
        Map<String, DisplayStrategy> strategies = new HashMap<>();
        strategies.put("1", new DisplayAllStrategy());
        strategies.put("2", new DisplayMonthStrategy());
        strategies.put("3", new DisplayWeekStrategy());
        strategies.put("4", new DisplayDayStrategy());
        strategies.put("5", new ReturnStrategy());
        
        DisplayStrategy strategy = strategies.getOrDefault(choix, new ReturnStrategy());
        strategy.display(scanner, manager);
        
        return true;
    }
    
    // Interface for display strategies
    private interface DisplayStrategy {
        void display(Scanner scanner, CalendarManager manager);
    }
    
    // Concrete strategies
    private class DisplayAllStrategy implements DisplayStrategy {
        @Override
        public void display(Scanner scanner, CalendarManager manager) {
            manager.afficherEvenements();
            System.out.println("Fin de la liste.");
        }
    }
    
    private class DisplayMonthStrategy implements DisplayStrategy {
        @Override
        public void display(Scanner scanner, CalendarManager manager) {
            System.out.print("Entrez l'année (AAAA) : ");
            int anneeMois = Integer.parseInt(scanner.nextLine());
            System.out.print("Entrez le mois (1-12) : ");
            int mois = Integer.parseInt(scanner.nextLine());

            LocalDateTime debutMois = LocalDateTime.of(anneeMois, mois, 1, 0, 0);
            LocalDateTime finMois = debutMois.plusMonths(1).minusSeconds(1);

            afficherListe(manager.eventsDansPeriode(debutMois, finMois));
        }
    }
    
    private class DisplayWeekStrategy implements DisplayStrategy {
        @Override
        public void display(Scanner scanner, CalendarManager manager) {
            System.out.print("Entrez l'année (AAAA) : ");
            int anneeSemaine = Integer.parseInt(scanner.nextLine());
            System.out.print("Entrez le numéro de semaine (1-52) : ");
            int semaine = Integer.parseInt(scanner.nextLine());

            LocalDateTime debutSemaine = LocalDateTime.now()
                    .withYear(anneeSemaine)
                    .with(WeekFields.of(Locale.FRANCE).weekOfYear(), semaine)
                    .with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1)
                    .withHour(0).withMinute(0);
            LocalDateTime finSemaine = debutSemaine.plusDays(7).minusSeconds(1);

            afficherListe(manager.eventsDansPeriode(debutSemaine, finSemaine));
        }
    }
    
    private class DisplayDayStrategy implements DisplayStrategy {
        @Override
        public void display(Scanner scanner, CalendarManager manager) {
            System.out.print("Entrez l'année (AAAA) : ");
            int anneeJour = Integer.parseInt(scanner.nextLine());
            System.out.print("Entrez le mois (1-12) : ");
            int moisJour = Integer.parseInt(scanner.nextLine());
            System.out.print("Entrez le jour (1-31) : ");
            int jour = Integer.parseInt(scanner.nextLine());

            LocalDateTime debutJour = LocalDateTime.of(anneeJour, moisJour, jour, 0, 0);
            LocalDateTime finJour = debutJour.plusDays(1).minusSeconds(1);

            afficherListe(manager.eventsDansPeriode(debutJour, finJour));
        }
    }
    
    private class ReturnStrategy implements DisplayStrategy {
        @Override
        public void display(Scanner scanner, CalendarManager manager) {
            // Do nothing, return to main menu
        }
    }
    
    private void afficherListe(List<Event> evenements) {
        if (evenements.isEmpty()) {
            System.out.println("Aucun événement trouvé pour cette période.");
        } else {
            System.out.println("Événements trouvés : ");
            for (Event e : evenements) {
                System.out.println("- " + e.description());
            }
        }
    }
}