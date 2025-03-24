import elementEvenement.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CoursTest {

    @Test
    public void testDescriptionCours() {
        Cours cours = new Cours(
                new TitreEvenement("CM POO"),
                new Proprietaire("Etudiant1"),
                new DateEvenement(LocalDateTime.of(2024, 4, 10, 9, 0)),
                new DureeEvenement(120),
                new LieuEvenement("Amphi A"),
                new Matiere("Programmation Orientée Objet"),
                new Enseignant("Mme Durand")
        );

        String attendu = "Cours : Programmation Orientée Objet avec Mme Durand en salle Amphi A";
        assertEquals(attendu, cours.description());
    }

    @Test
    public void testInstanceDansIntervalle() {
        Cours cours = new Cours(
                new TitreEvenement("CM BD"),
                new Proprietaire("Etudiant2"),
                new DateEvenement(LocalDateTime.of(2024, 5, 3, 14, 0)),
                new DureeEvenement(90),
                new LieuEvenement("Salle B"),
                new Matiere("Bases de Données"),
                new Enseignant("M. Martin")
        );

        LocalDateTime debut = LocalDateTime.of(2024, 5, 1, 0, 0);
        LocalDateTime fin = LocalDateTime.of(2024, 5, 5, 23, 59);

        List<Event> result = cours.instancesDansIntervalle(debut, fin);
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof Cours);
    }

    @Test
    public void testInstanceHorsIntervalle() {
        Cours cours = new Cours(
                new TitreEvenement("TD Réseaux"),
                new Proprietaire("Etudiant3"),
                new DateEvenement(LocalDateTime.of(2024, 6, 20, 10, 0)),
                new DureeEvenement(60),
                new LieuEvenement("Salle C"),
                new Matiere("Réseaux"),
                new Enseignant("Mme Lopez")
        );

        LocalDateTime debut = LocalDateTime.of(2024, 6, 1, 0, 0);
        LocalDateTime fin = LocalDateTime.of(2024, 6, 10, 23, 59);

        List<Event> result = cours.instancesDansIntervalle(debut, fin);
        assertTrue(result.isEmpty());
    }
}