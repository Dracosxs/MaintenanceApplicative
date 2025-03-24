import elementEvenement.*;

import java.time.LocalDateTime;
import java.util.List;


public abstract class Event {
    protected TitreEvenement title;
    protected DateEvenement dateDebut;
    protected DureeEvenement dureeMinutes;
    protected LieuEvenement lieu; // Utilisé uniquement pour les réunions
    protected Participants participants; // Utilisé uniquement pour les réunions

    protected Proprietaire proprietaire;
    protected FrequenceEvenement frequence;



    public Event(TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement dureeMinutes) {
        this.title = titre;
        this.proprietaire =  proprietaire;
        this.dateDebut = dateDebut;
        this.dureeMinutes = dureeMinutes;
    }

    public abstract List<Event> instancesDansIntervalle(LocalDateTime debut, LocalDateTime fin);

    public abstract String description();


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

