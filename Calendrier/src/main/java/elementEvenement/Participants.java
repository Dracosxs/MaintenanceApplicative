package elementEvenement;

import java.util.List;
import java.util.stream.Collectors;

public class Participants implements ParticipantsDescription {
    private final List<String> noms;

    public Participants(List<String> noms) {
        if (noms == null || noms.isEmpty()) {
            throw new IllegalArgumentException("Un événement doit avoir au moins un participant.");
        }
        this.noms = noms;
    }

    public List<String> liste() {
        return noms;
    }

    @Override
    public String description() {
        return "avec " + String.join(", ", noms);
    }
    
    @Override
    public boolean aParticipantsCommuns(ParticipantsDescription autres) {
        if (autres instanceof Participants) {
            Participants autresParticipants = (Participants) autres;
            return noms.stream().anyMatch(nom -> autresParticipants.liste().contains(nom));
        } else if (autres instanceof AucunParticipant) {
            return false; // No common participants with an empty set
        } else {
            // Generic case for future extensions of ParticipantsDescription
            return false;
        }
    }
}
