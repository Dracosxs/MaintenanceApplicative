package elementEvenement;

public record TitreEvenement(String valeur) {
    public TitreEvenement {
        if (valeur == null || valeur.trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre ne peut pas être vide.");
        }
    }
}
