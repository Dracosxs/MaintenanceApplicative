import elementEvenement.DateEvenement;
import elementEvenement.DureeEvenement;
import elementEvenement.TitreEvenement;

public class RendezVous extends Event {
    public RendezVous(TitreEvenement titre, String proprietaire, DateEvenement dateDebut, DureeEvenement duree) {
        super(titre, proprietaire, dateDebut, duree);
    }

    @Override
    public String description() {
        return "RDV : " + getTitle().valeur() + " à " + getDateDebut().valeur();
    }
}
