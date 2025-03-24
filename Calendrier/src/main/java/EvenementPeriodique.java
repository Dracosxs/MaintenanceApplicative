import elementEvenement.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EvenementPeriodique extends Event {
    public EvenementPeriodique(TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement duree, FrequenceEvenement frequenceJours) {
        super(titre, proprietaire, dateDebut, duree);
        this.frequence = frequenceJours;
    }

    @Override
    public String description() {
        return "Événement périodique : " + getTitle().valeur() + " tous les " + getFrequence().jours() + " jours";
    }

    @Override
    public List<Event> instancesDansIntervalle(LocalDateTime debut, LocalDateTime fin) {
        List<Event> instances = new ArrayList<>();
        LocalDateTime prochaineDate = getDateDebut().valeur();

        while (!prochaineDate.isAfter(fin)) {
            if (!prochaineDate.isBefore(debut)) {
                instances.add(new EvenementPeriodique(
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



}
