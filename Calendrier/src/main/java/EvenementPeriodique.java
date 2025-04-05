import elementEvenement.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EvenementPeriodique extends Event {
    public EvenementPeriodique(TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement duree, FrequenceEvenement frequenceJours) {
        super(titre, proprietaire, dateDebut, duree);
        this.frequence = frequenceJours;
    }
    
    public EvenementPeriodique(EventId id, TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement duree, FrequenceEvenement frequenceJours) {
        super(id, titre, proprietaire, dateDebut, duree);
        this.frequence = frequenceJours;
    }

    @Override
    public String description() {
        return "Événement périodique : " + getTitle().valeur() + " tous les " + getFrequence().jours() + " jours (ID: " + id() + ")";
    }

    @Override
    public List<Event> instancesDansIntervalle(LocalDateTime debut, LocalDateTime fin) {
        List<Event> instances = new ArrayList<>();
        LocalDateTime prochaineDate = getDateDebut().valeur();

        while (!prochaineDate.isAfter(fin)) {
            if (!prochaineDate.isBefore(debut)) {
                // Create a new instance with a new EventId for each occurrence
                instances.add(new EvenementPeriodique(
                        new EventId(), // Generate a new ID for each instance
                        getTitle(),
                        getProprietaire(),
                        new DateEvenement(prochaineDate),
                        getDureeMinutes(),
                        getFrequence()
                ));
            }
            prochaineDate = prochaineDate.plusDays(getFrequence().jours());
        }

        return instances;
    }
    
    @Override
    public boolean conflitAvec(Event autre) {
        // Double dispatch - delegate to the other event's specific method
        return autre.conflitAvecEvenementPeriodique(this);
    }
    
    @Override
    public boolean conflitAvecRendezVous(RendezVous rdv) {
        // Check if any instance of this periodic event conflicts with the rendez-vous
        return instanceConflitAvec(rdv.getDateDebut().valeur(), rdv.dateFin());
    }
    
    @Override
    public boolean conflitAvecReunion(Reunion reunion) {
        // Check if any instance of this periodic event conflicts with the reunion
        return instanceConflitAvec(reunion.getDateDebut().valeur(), reunion.dateFin());
    }
    
    @Override
    public boolean conflitAvecEvenementPeriodique(EvenementPeriodique autre) {
        // This is more complex - we need to check if any instances of both periodic events conflict
        // For simplicity, we'll check instances over a reasonable period (e.g., 1 year)
        LocalDateTime startCheck = getDateDebut().valeur().isBefore(autre.getDateDebut().valeur()) 
                ? getDateDebut().valeur() 
                : autre.getDateDebut().valeur();
        
        LocalDateTime endCheck = startCheck.plusYears(1);
        
        // Get instances of both events in the checking period
        List<Event> thisInstances = instancesDansIntervalle(startCheck, endCheck);
        List<Event> autreInstances = autre.instancesDansIntervalle(startCheck, endCheck);
        
        // Check for conflicts between any pair of instances
        for (Event thisInstance : thisInstances) {
            for (Event autreInstance : autreInstances) {
                if (chevauchementTemporel(thisInstance, autreInstance)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Helper method to check if any instance of this periodic event conflicts with the given time slot
     */
    private boolean instanceConflitAvec(LocalDateTime debut, LocalDateTime fin) {
        // Calculate a reasonable period to check for conflicts
        LocalDateTime checkStart = debut.minusMonths(6);
        LocalDateTime checkEnd = fin.plusMonths(6);
        
        // Get all instances in this period
        List<Event> instances = instancesDansIntervalle(checkStart, checkEnd);
        
        // Check if any instance conflicts with the given time slot
        for (Event instance : instances) {
            if (chevauchementTemporel(instance, debut, fin)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Helper method to check for temporal overlap between two events
     */
    private boolean chevauchementTemporel(Event event1, Event event2) {
        // Overlap occurs when:
        // event1 starts before event2 ends AND event1 ends after event2 starts
        return event1.getDateDebut().valeur().isBefore(event2.dateFin()) && 
               event1.dateFin().isAfter(event2.getDateDebut().valeur());
    }
    
    /**
     * Helper method to check for temporal overlap between an event and a time slot
     */
    private boolean chevauchementTemporel(Event event, LocalDateTime debut, LocalDateTime fin) {
        // Overlap occurs when:
        // event starts before fin AND event ends after debut
        return event.getDateDebut().valeur().isBefore(fin) && 
               event.dateFin().isAfter(debut);
    }
}
