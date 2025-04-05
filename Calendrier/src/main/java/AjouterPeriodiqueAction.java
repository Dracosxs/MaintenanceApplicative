import elementEvenement.*;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Action class for adding a periodic event to the calendar.
 */
public class AjouterPeriodiqueAction implements Action {
    @Override
    public boolean executer(Scanner scanner, CalendarManager manager, String utilisateur) {
        System.out.print("Titre de l'événement : ");
        String titre = scanner.nextLine();
        System.out.print("Année (AAAA) : ");
        int annee = Integer.parseInt(scanner.nextLine());
        System.out.print("Mois (1-12) : ");
        int moisRdv = Integer.parseInt(scanner.nextLine());
        System.out.print("Jour (1-31) : ");
        int jourRdv = Integer.parseInt(scanner.nextLine());
        System.out.print("Heure début (0-23) : ");
        int heure = Integer.parseInt(scanner.nextLine());
        System.out.print("Minute début (0-59) : ");
        int minute = Integer.parseInt(scanner.nextLine());
        System.out.print("Durée (en minutes) : ");
        int duree = Integer.parseInt(scanner.nextLine());
        System.out.print("Frequence (en jours) : ");
        int frequence = Integer.parseInt(scanner.nextLine());

        manager.ajouterEvent(new EvenementPeriodique(
                new TitreEvenement(titre),
                new Proprietaire(utilisateur),
                new DateEvenement(LocalDateTime.of(annee, moisRdv, jourRdv, heure, minute)),
                new DureeEvenement(duree),
                new FrequenceEvenement(frequence)
        ));

        System.out.println("Événement ajouté.");
        return true;
    }
}