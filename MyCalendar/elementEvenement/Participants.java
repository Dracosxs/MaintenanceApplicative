package elementEvenement;

public record Participants(StringBuilder liste) {
    public Participants {
        if (liste == null || liste.isEmpty()) {
            throw new IllegalArgumentException("Un événement doit avoir au moins un participant.");
        }
    }
}
