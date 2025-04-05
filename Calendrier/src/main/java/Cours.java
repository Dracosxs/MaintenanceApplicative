import elementEvenement.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cours extends Event {
    private final LieuEvenement salle;
    private final Matiere matiere;
    private final Enseignant enseignant;

    public Cours(TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut,
                 DureeEvenement duree, LieuEvenement salle, Matiere matiere, Enseignant enseignant) {
        super(titre, proprietaire, dateDebut, duree);
        this.salle = salle;
        this.matiere = matiere;
        this.enseignant = enseignant;
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
    public String description() {
        return "Cours : " + matiere.valeur() + " avec " + enseignant.nom() + " en salle " + salle.valeur();
    }

    @Override
    public boolean conflitAvec(Event autre) {
        return false;
    }

    @Override
    public boolean conflitAvecRendezVous(RendezVous rdv) {
        return false;
    }

    @Override
    public boolean conflitAvecReunion(Reunion reunion) {
        return false;
    }

    @Override
    public boolean conflitAvecEvenementPeriodique(EvenementPeriodique periodique) {
        return false;
    }

}
