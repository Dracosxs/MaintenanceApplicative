package elementEvenement;

public record DureeEvenement(int minutes) {
    public DureeEvenement {
        if (minutes <= 0) {
            throw new IllegalArgumentException("La durée doit être positive.");
        }
    }
}
