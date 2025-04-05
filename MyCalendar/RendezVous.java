import elementEvenement.DateEvenement;
import elementEvenement.DureeEvenement;
import elementEvenement.Proprietaire;
import elementEvenement.TitreEvenement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RendezVous extends Event {
    public RendezVous(TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement duree) {
        super(titre, proprietaire, dateDebut, duree);
    }

    @Override
    public String description() {
        return "RDV : " + getTitle().valeur() + " à " + getDateDebut().valeur();
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
}
