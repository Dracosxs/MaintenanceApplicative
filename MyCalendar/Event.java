import elementEvenement.*;


public abstract class Event {
    protected TitreEvenement title;
    protected String proprietaire;
    protected DateEvenement dateDebut;
    protected DureeEvenement dureeMinutes;
    protected LieuEvenement lieu; // Utilisé uniquement pour les réunions
    protected Participants participants; // Utilisé uniquement pour les réunions
    protected int frequenceJours; // Utilisé uniquement pour les événements périodiques

    public Event(TitreEvenement titre, String proprietaire, DateEvenement dateDebut, DureeEvenement dureeMinutes) {
        this.title = titre;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.dureeMinutes = dureeMinutes;
    }

    public abstract String description();

    public TitreEvenement getTitle() {
        return title;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public DateEvenement getDateDebut() {
        return dateDebut;
    }

    public DureeEvenement getDureeMinutes() {
        return dureeMinutes;
    }

    public LieuEvenement getLieu() {
        return lieu;
    }

    public Participants getParticipants() {
        return participants;
    }

    protected int frequenceJours() {
        return frequenceJours;
    }
}

