import elementEvenement.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EvenementPeriodiqueTest {

    @Test
    public void testAucuneOccurrenceHorsIntervalle() {
        EvenementPeriodique ep = new EvenementPeriodique(
                new TitreEvenement("Standup Dev"),
                new Proprietaire("Alice"),
                new DateEvenement(LocalDateTime.of(2024, 3, 10, 9, 0)),
                new DureeEvenement(30),
                new FrequenceEvenement(7)
        );

        LocalDateTime debut = LocalDateTime.of(2024, 3, 1, 0, 0);
        LocalDateTime fin = LocalDateTime.of(2024, 3, 5, 23, 59);

        List<Event> instances = ep.instancesDansIntervalle(debut, fin);
        assertTrue(instances.isEmpty(), "Aucune occurrence ne devrait être dans la période");
    }

    @Test
    public void testUneOccurrenceDansIntervalle() {
        EvenementPeriodique ep = new EvenementPeriodique(
                new TitreEvenement("Réunion hebdo"),
                new Proprietaire("Bob"),
                new DateEvenement(LocalDateTime.of(2024, 4, 1, 10, 0)),
                new DureeEvenement(60),
                new FrequenceEvenement(7)
        );

        LocalDateTime debut = LocalDateTime.of(2024, 4, 1, 0, 0);
        LocalDateTime fin = LocalDateTime.of(2024, 4, 7, 23, 59);

        List<Event> instances = ep.instancesDansIntervalle(debut, fin);
        assertEquals(1, instances.size());
        assertEquals("Événement périodique : Réunion hebdo tous les 7 jours", instances.get(0).description());
    }

    @Test
    public void testPlusieursOccurrences() {
        EvenementPeriodique ep = new EvenementPeriodique(
                new TitreEvenement("Checkpoint projet"),
                new Proprietaire("Claire"),
                new DateEvenement(LocalDateTime.of(2024, 5, 1, 15, 0)),
                new DureeEvenement(90),
                new FrequenceEvenement(2)
        );

        LocalDateTime debut = LocalDateTime.of(2024, 5, 1, 0, 0);
        LocalDateTime fin = LocalDateTime.of(2024, 5, 7, 23, 59);

        List<Event> instances = ep.instancesDansIntervalle(debut, fin);
        assertEquals(4, instances.size()); // 1er, 3, 5, 7 mai
    }
}
