import elementEvenement.DureeEvenement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestDureeEvenement {

    @Test
    void creerDureeValide() {
        DureeEvenement duree = new DureeEvenement(45);
        assertEquals(45, duree.minutes());
    }

    @Test
    void creerDureeZeroLanceException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DureeEvenement(0);
        });
        assertEquals("La durée doit être positive.", exception.getMessage());
    }

    @Test
    void creerDureeNegativeLanceException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DureeEvenement(-10);
        });
        assertEquals("La durée doit être positive.", exception.getMessage());
    }
}