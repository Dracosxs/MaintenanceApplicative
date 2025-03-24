package elementEvenement;

public record LieuEvenement(String valeur) {
    public LieuEvenement {
        if (valeur == null || valeur.trim().isEmpty()) {
            throw new IllegalArgumentException("Le lieu ne peut pas être vide.");
        }
    }
}
