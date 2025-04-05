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

    @Override
    public String description() {
        return "Réunion : " + getTitle().valeur() + " à " + getLieu().valeur() + " " + participants.description();
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
