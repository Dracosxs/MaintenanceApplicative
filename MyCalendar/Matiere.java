package elementEvenement;

public record Matiere(String valeur) {
    public Matiere {
        if (valeur == null || valeur.trim().isEmpty()) {
            throw new IllegalArgumentException("La matière ne peut pas être vide.");
        }
    }
}