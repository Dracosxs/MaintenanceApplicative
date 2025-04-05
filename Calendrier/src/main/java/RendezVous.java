import elementEvenement.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RendezVous extends Event {
    public RendezVous(TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement duree) {
        super(titre, proprietaire, dateDebut, duree);
    }
    
    public RendezVous(EventId id, TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement duree) {
        super(id, titre, proprietaire, dateDebut, duree);
    }

    @Override
    public String description() {
        return "RDV : " + getTitle().valeur() + " à " + getDateDebut().valeur() + " (ID: " + id() + ")";
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
        // Double dispatch - deleguate to the other event's specific method
        return autre.conflitAvecRendezVous(this);
    }
    
    @Override
    public boolean conflitAvecRendezVous(RendezVous rdv) {
        // Check for time overlap between two rendez-vous
        return chevauchementTemporel(rdv);
    }
    
    @Override
    public boolean conflitAvecReunion(Reunion reunion) {
        // Check for time overlap between a rendez-vous and a reunion
        return chevauchementTemporel(reunion);
    }
    
    @Override
    public boolean conflitAvecEvenementPeriodique(EvenementPeriodique periodique) {
        // For simplicity, we'll consider that a rendez-vous conflicts with a periodic event
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
