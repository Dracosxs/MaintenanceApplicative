import elementEvenement.*;

import java.time.LocalDateTime;
import java.util.List;


public abstract class Event {
    private final EventId id;
    protected TitreEvenement title;
    protected DateEvenement dateDebut;
    protected DureeEvenement dureeMinutes;
    protected LieuEvenement lieu; // Utilisé uniquement pour les réunions
    protected Participants participants; // Utilisé uniquement pour les réunions

    protected Proprietaire proprietaire;
    protected FrequenceEvenement frequence;


    public Event(TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement dureeMinutes) {
        this(new EventId(), titre, proprietaire, dateDebut, dureeMinutes);
    }
    
    public Event(EventId id, TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement dureeMinutes) {
        this.id = id;
        this.title = titre;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.dureeMinutes = dureeMinutes;
    }

    public abstract List<Event> instancesDansIntervalle(LocalDateTime debut, LocalDateTime fin);

    public abstract String description();
    
    /**
     * Returns the unique identifier of this event.
     *
     * @return The event ID
     */
    public EventId id() {
        return id;
    }

    /**
     * Vérifie si cet événement entre en conflit avec un autre événement.
     * Utilise le mécanisme de double dispatch pour déterminer le type concret de l'autre événement.
     *
     * @param autre L'autre événement à vérifier
     * @return true si les événements sont en conflit, false sinon
     */
    public abstract boolean conflitAvec(Event autre);
    
    /**
     * Vérifie si cet événement entre en conflit avec un rendez-vous.
     *
     * @param rdv Le rendez-vous à vérifier
     * @return true si les événements sont en conflit, false sinon
     */
    public abstract boolean conflitAvecRendezVous(RendezVous rdv);
    
    /**
     * Vérifie si cet événement entre en conflit avec une réunion.
     *
     * @param reunion La réunion à vérifier
     * @return true si les événements sont en conflit, false sinon
     */
    public abstract boolean conflitAvecReunion(Reunion reunion);
    
    /**
     * Vérifie si cet événement entre en conflit avec un événement périodique.
     *
     * @param periodique L'événement périodique à vérifier
     * @return true si les événements sont en conflit, false sinon
     */
    public abstract boolean conflitAvecEvenementPeriodique(EvenementPeriodique periodique);
    
    /**
     * Calcule la date de fin de l'événement.
     *
     * @return La date de fin de l'événement
     */
    public LocalDateTime dateFin() {
        return dateDebut.valeur().plusMinutes(dureeMinutes.minutes());
    }

    public TitreEvenement getTitle() {
        return title;
    }

    public Proprietaire getProprietaire() {
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

    protected FrequenceEvenement getFrequence() {
        return frequence;
    }
}
