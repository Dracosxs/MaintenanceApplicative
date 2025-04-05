package elementEvenement;

public record Proprietaire(String nom) {
    public Proprietaire {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du propriétaire ne peut pas être vide.");
        }
    }
}
