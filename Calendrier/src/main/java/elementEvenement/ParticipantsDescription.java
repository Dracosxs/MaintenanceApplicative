package elementEvenement;

public interface ParticipantsDescription {
    /**
     * Returns a description of the participants.
     *
     * @return The description
     */
    String description();
    
    /**
     * Checks if there are common participants between this and another participants description.
     *
     * @param autres The other participants description
     * @return true if there are common participants, false otherwise
     */
    boolean aParticipantsCommuns(ParticipantsDescription autres);
}
