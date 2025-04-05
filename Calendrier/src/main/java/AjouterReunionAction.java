import elementEvenement.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Action class for adding a meeting to the calendar.
 */
public class AjouterReunionAction implements Action {
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
        System.out.println("Lieu :");
        String lieu = scanner.nextLine();

        List<String> nomsParticipants = new ArrayList<>();
        nomsParticipants.add(utilisateur);

        System.out.println("Ajouter un participant ? (oui / non)");
        while (scanner.nextLine().trim().equalsIgnoreCase("oui")) {
            System.out.print("Nom du participant : ");
            String nom = scanner.nextLine().trim();
            if (!nom.isEmpty()) {
                nomsParticipants.add(nom);
            }
        }

        ParticipantsDescription participants;

        if (nomsParticipants.isEmpty()) {
            participants = new AucunParticipant();
        } else {
            participants = new Participants(nomsParticipants);
        }

        manager.ajouterEvent(new Reunion(
                new TitreEvenement(titre),
                new Proprietaire(utilisateur),
                new DateEvenement(LocalDateTime.of(annee, moisRdv, jourRdv, heure, minute)),
                new DureeEvenement(duree),
                new LieuEvenement(lieu),
                (Participants) participants
            )
        );

        System.out.println("Événement ajouté.");
        return true;
    }
}