import elementEvenement.TitreEvenement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestTitreEvenement {

    @Test
    void creerTitreValide() {
        TitreEvenement titre = new TitreEvenement("Réunion Importante");
        assertEquals("Réunion Importante", titre.valeur());
    }

    @Test
    void creerTitreVideLanceException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TitreEvenement("");
        });
        assertEquals("Le titre ne peut pas être vide.", exception.getMessage());
    }

    @Test
    void creerTitreNullLanceException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TitreEvenement(null);
        });
        assertEquals("Le titre ne peut pas être vide.", exception.getMessage());
    }
}
