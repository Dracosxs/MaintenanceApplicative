package elementEvenement;

public class AucunParticipant implements ParticipantsDescription {
    @Override
    public String description() {
        return "sans participants";
    }
    
    @Override
    public boolean aParticipantsCommuns(ParticipantsDescription autres) {
        // An event with no participants cannot have common participants with any other event
        return false;
    }
}
