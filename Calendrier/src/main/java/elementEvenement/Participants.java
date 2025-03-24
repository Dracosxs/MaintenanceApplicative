package elementEvenement;

import java.util.List;

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
}
