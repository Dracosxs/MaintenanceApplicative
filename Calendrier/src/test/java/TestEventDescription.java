import elementEvenement.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestEventDescription {

    @Test
    public void testDescriptionRendezVous() {
        Event rdv = new RendezVous(
                new TitreEvenement("RDV médical"),
                new Proprietaire("Alice"),
                new DateEvenement(LocalDateTime.of(2024, 4, 15, 14, 0)),
                new DureeEvenement(30)
        );
        assertEquals("RDV : RDV médical à 2024-04-15T14:00", rdv.description());
    }

    @Test
    public void testDescriptionReunion() {
        ParticipantsDescription participants = new Participants(List.of("Alice", "Bob"));
        Event reunion = new Reunion(
                new TitreEvenement("Réunion d'équipe"),
                new Proprietaire("Manager"),
                new DateEvenement(LocalDateTime.of(2024, 4, 16, 10, 0)),
                new DureeEvenement(60),
                new LieuEvenement("Salle A"),
                (Participants) participants
        );
        assertEquals("Réunion : Réunion d'équipe à Salle A avec Alice, Bob", reunion.description());
    }

    @Test
    public void testDescriptionEvenementPeriodique() {
        Event ep = new EvenementPeriodique(
                new TitreEvenement("Daily Sync"),
                new Proprietaire("Team"),
                new DateEvenement(LocalDateTime.of(2024, 4, 10, 9, 0)),
                new DureeEvenement(15),
                new FrequenceEvenement(1)
        );
        assertEquals("Événement périodique : Daily Sync tous les 1 jours", ep.description());
    }

    @Test
    public void testDescriptionCours() {
        Event cours = new Cours(
                new TitreEvenement("CM Java"),
                new Proprietaire("ProfesseurX"),
                new DateEvenement(LocalDateTime.of(2024, 4, 20, 11, 0)),
                new DureeEvenement(120),
                new LieuEvenement("Amphi B"),
                new Matiere("Programmation Java"),
                new Enseignant("Mme Dupont")
        );
        assertEquals("Cours : Programmation Java avec Mme Dupont en salle Amphi B", cours.description());
    }
}