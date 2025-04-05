package elementEvenement;

import java.util.Objects;
import java.util.UUID;

/**
 * Value object representing a unique event identifier.
 */
public final class EventId {
    private final UUID id;
    
    /**
     * Creates a new EventId with a randomly generated UUID.
     */
    public EventId() {
        this.id = UUID.randomUUID();
    }
    
    /**
     * Creates an EventId from an existing UUID.
     *
     * @param id The UUID
     */
    public EventId(UUID id) {
        this.id = Objects.requireNonNull(id, "ID cannot be null");
    }
    
    /**
     * Creates an EventId from a string representation of a UUID.
     *
     * @param idString The string representation of a UUID
     * @throws IllegalArgumentException if the string is not a valid UUID
     */
    public EventId(String idString) {
        try {
            this.id = UUID.fromString(idString);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid UUID format: " + idString, e);
        }
    }
    
    /**
     * Returns the string representation of this EventId.
     *
     * @return The string representation of the UUID
     */
    @Override
    public String toString() {
        return id.toString();
    }
    
    /**
     * Checks if this EventId is equal to another object.
     *
     * @param o The object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventId eventId = (EventId) o;
        return Objects.equals(id, eventId.id);
    }
    
    /**
     * Returns the hash code of this EventId.
     *
     * @return The hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}