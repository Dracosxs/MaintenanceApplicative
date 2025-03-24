package elementEvenement;

public record Enseignant(String nom) {
    public Enseignant {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'enseignant ne peut pas être vide.");
        }
    }
}