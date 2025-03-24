import elementEvenement.DateEvenement;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TestDateEvenement {

    @Test
    void creerDateValide() {
        LocalDateTime now = LocalDateTime.now();
        DateEvenement date = new DateEvenement(now);
        assertEquals(now, date.valeur());
    }

    @Test
    void creerDateNullLanceException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DateEvenement(null);
        });
        assertEquals("La date ne peut pas être nulle.", exception.getMessage());
    }
}
