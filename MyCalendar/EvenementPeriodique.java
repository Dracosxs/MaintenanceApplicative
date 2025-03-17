import elementEvenement.DateEvenement;
import elementEvenement.DureeEvenement;
import elementEvenement.TitreEvenement;

public class EvenementPeriodique extends Event {
    public EvenementPeriodique(TitreEvenement titre, String proprietaire, DateEvenement dateDebut, DureeEvenement duree, int frequenceJours) {
        super(titre, proprietaire, dateDebut, duree);
        this.frequenceJours = frequenceJours;
    }

    @Override
    public String description() {
        return "Événement périodique : " + getTitle().valeur() + " tous les " + frequenceJours + " jours";
    }



}
