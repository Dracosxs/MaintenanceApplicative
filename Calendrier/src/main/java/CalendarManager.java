import elementEvenement.EventId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CalendarManager {
    public List<Event> events;

    public CalendarManager() {
        this.events = new ArrayList<>();
    }

    public void ajouterEvent(Event event) {
        events.add(event);
    }

    /**
     * Supprime l'événement avec l'identifiant spécifié.
     *
     * @param id L'identifiant de l'événement à supprimer
     * @return true si un événement a été supprimé, false sinon
     */
    public boolean supprimerEvent(EventId id) {
        // Use iterator to avoid conditionals
        Iterator<Event> iterator = events.iterator();
        while (iterator.hasNext()) {
            Event event = iterator.next();
            if (Objects.equals(event.id(), id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public List<Event> eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        List<Event> resultats = new ArrayList<>();
        for (Event event : events) {
            resultats.addAll(event.instancesDansIntervalle(debut, fin));
        }
        return resultats;
    }

    public void afficherEvenements() {
        for (Event e : events) {
            System.out.println(e.description());
        }
    }
    
    /**
     * Retourne l'événement avec l'identifiant spécifié.
     *
     * @param id L'identifiant de l'événement à trouver
     * @return L'événement correspondant ou null si aucun événement ne correspond
     */
    public Event trouverEventParId(EventId id) {
        return events.stream()
                .filter(event -> Objects.equals(event.id(), id))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Retourne tous les événements qui sont en conflit avec l'événement donné.
     *
     * @param e L'événement pour lequel chercher des conflits
     * @return Une liste d'événements en conflit
     */
    public List<Event> conflitsAvec(Event e) {
        return events.stream()
                .filter(event -> !event.id().equals(e.id())) // Exclude self
                .filter(event -> event.conflitAvec(e))       // Check for conflict
                .collect(Collectors.toList());
    }
}