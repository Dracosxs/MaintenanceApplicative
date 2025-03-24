import elementEvenement.LieuEvenement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestLieuEvenement {

    @Test
    void creerLieuValide() {
        LieuEvenement lieu = new LieuEvenement("Salle B302");
        assertEquals("Salle B302", lieu.valeur());
    }

    @Test
    void creerLieuVideLanceException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LieuEvenement("");
        });
        assertEquals("Le lieu ne peut pas être vide.", exception.getMessage());
    }

    @Test
    void creerLieuNullLanceException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LieuEvenement(null);
        });
        assertEquals("Le lieu ne peut pas être vide.", exception.getMessage());
    }
}
