import elementEvenement.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reunion extends Event {

    protected ParticipantsDescription participants;

    public Reunion(TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement duree,
                   LieuEvenement lieu, Participants participants) {
        super(titre, proprietaire, dateDebut, duree);
        this.lieu = lieu;
        this.participants = participants;
    }
    
    public Reunion(EventId id, TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement duree,
                   LieuEvenement lieu, Participants participants) {
        super(id, titre, proprietaire, dateDebut, duree);
        this.lieu = lieu;
        this.participants = participants;
    }

    @Override
    public String description() {
        return "Réunion : " + getTitle().valeur() + " à " + getLieu().valeur() + " " + participants.description() + " (ID: " + id() + ")";
    }

    @Override
    public List<Event> instancesDansIntervalle(LocalDateTime debut, LocalDateTime fin) {
        List<Event> liste = new ArrayList<>();
        LocalDateTime date = getDateDebut().valeur();

        if (!date.isBefore(debut) && !date.isAfter(fin)) {
            liste.add(this);
        }

        return liste;
    }
    
    @Override
    public boolean conflitAvec(Event autre) {
        // Double dispatch - delegate to the other event's specific method
        return autre.conflitAvecReunion(this);
    }
    
    @Override
    public boolean conflitAvecRendezVous(RendezVous rdv) {
        // Check for time overlap between a reunion and a rendez-vous
        return chevauchementTemporel(rdv);
    }
    
    @Override
    public boolean conflitAvecReunion(Reunion reunion) {
        // For reunions, we consider both time overlap and participant conflicts
        boolean tempsChevauche = chevauchementTemporel(reunion);
        
        // If times don't overlap, there's no conflict
        if (!tempsChevauche) {
            return false;
        }
        
        // If the same location is used, it's a conflict
        if (lieu.valeur().equals(reunion.getLieu().valeur())) {
            return true;
        }
        
        // Check for participant conflicts
        return this.participants.aParticipantsCommuns(reunion.participants);
    }
    
    @Override
    public boolean conflitAvecEvenementPeriodique(EvenementPeriodique periodique) {
        // For simplicity, we'll consider that a reunion conflicts with a periodic event
        // if it conflicts with any of its instances in a reasonable time span (e.g., 1 year)
        LocalDateTime currentDate = getDateDebut().valeur();
        LocalDateTime oneYearLater = currentDate.plusYears(1);
        
        List<Event> instances = periodique.instancesDansIntervalle(
                currentDate.minusDays(periodique.getFrequence().jours()), 
                oneYearLater);
        
        for (Event instance : instances) {
            if (chevauchementTemporel(instance)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Helper method to check for temporal overlap between this event and another
     */
    private boolean chevauchementTemporel(Event autre) {
        // Overlap occurs when:
        // this starts before autre ends AND this ends after autre starts
        return getDateDebut().valeur().isBefore(autre.dateFin()) && 
               dateFin().isAfter(autre.getDateDebut().valeur());
    }
}
