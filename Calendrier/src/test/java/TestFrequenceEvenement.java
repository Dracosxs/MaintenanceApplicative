import elementEvenement.FrequenceEvenement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestFrequenceEvenement {

    @Test
    void creerFrequenceValide() {
        FrequenceEvenement frequence = new FrequenceEvenement(7);
        assertEquals(7, frequence.jours());
    }

    @Test
    void creerFrequenceZeroLanceException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new FrequenceEvenement(0);
        });
        assertEquals("La fréquence doit être positive.", exception.getMessage());
    }

    @Test
    void creerFrequenceNegativeLanceException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new FrequenceEvenement(-5);
        });
        assertEquals("La fréquence doit être positive.", exception.getMessage());
    }
}