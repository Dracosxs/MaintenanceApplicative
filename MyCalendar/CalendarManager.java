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
        List<Event> resultats = new ArrayList<>();
        for (Event event : events) {
            resultats.addAll(event.instancesDansIntervalle(debut, fin));
        }
        return resultats;
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