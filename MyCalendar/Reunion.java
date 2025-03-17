import elementEvenement.*;

public class Reunion extends Event {
    public Reunion(TitreEvenement titre, String proprietaire, DateEvenement dateDebut, DureeEvenement duree,
                   LieuEvenement lieu, Participants participants) {
        super(titre, proprietaire, dateDebut, duree);
        this.lieu = lieu;
        this.participants = participants;
    }

    @Override
    public String description() {
        String participantsStr = (getParticipants() == null || getParticipants().liste().isEmpty())
                ? "sans participants"
                : "avec " + String.join(", ", getParticipants().liste());

        return "Réunion : " + getTitle().valeur() + " à " + getLieu().valeur() + " " + participantsStr;
    }

}
