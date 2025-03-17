import elementEvenement.DateEvenement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CalendarManager {
    public List<Event> events;

    public CalendarManager() {
        this.events = new ArrayList<>();
    }

    public void ajouterEvent(Event event) {
        events.add(event);
    }



    public List<Event> eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        List<Event> result = new ArrayList<>();
        for (Event e : events) {
            if (e instanceof EvenementPeriodique eventPeriodique) {
                LocalDateTime temp = eventPeriodique.getDateDebut().valeur();
                while (temp.isBefore(fin)) {
                    if (!temp.isBefore(debut)) {
                        // On ajoute une **nouvelle instance** de l'événement avec la date ajustée
                        result.add(new EvenementPeriodique(
                                eventPeriodique.getTitle(),
                                eventPeriodique.getProprietaire(),
                                new DateEvenement(temp),
                                eventPeriodique.getDureeMinutes(),
                                eventPeriodique.frequenceJours()
                        ));
                    }
                    temp = temp.plusDays(eventPeriodique.frequenceJours());
                }
            } else if (!e.getDateDebut().valeur().isBefore(debut) && !e.getDateDebut().valeur().isAfter(fin)) {
                result.add(e);
            }
        }
        return result;
    }

//
//
//    public boolean conflit(Event e1, Event e2) {
//        LocalDateTime fin1 = e1.dateDebut.plusMinutes(e1.dureeMinutes);
//        LocalDateTime fin2 = e2.dateDebut.plusMinutes(e2.dureeMinutes);
//
//        if (e1.type.equals("PERIODIQUE") || e2.type.equals("PERIODIQUE")) {
//            return false; // Simplification abusive
//        }
//
//        if (e1.dateDebut.isBefore(fin2) && fin1.isAfter(e2.dateDebut)) {
//            return true;
//        }
//        return false;
//    }

    public void afficherEvenements() {
        for (Event e : events) {
            System.out.println(e.description());
        }
    }
}