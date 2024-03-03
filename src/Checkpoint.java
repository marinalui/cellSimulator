import java.util.HashSet;

public class Checkpoint {
    public enum Mutations{
        NO_RESOURCES, DNA_ERROR,CHROMOSOME_MISALIGNED;

    }
    private Phases phase;
    private Cell currentCell;
    private String mutation;
    public Checkpoint(){
    }
    /**
     *
     * @param currentCell
     * @return true if it passes the checkpoint, false if it fails the checkpoint
     */
    public boolean test(Cell currentCell){
        boolean result = true;
        this.currentCell = currentCell;
        phase = this.currentCell.getPhase();
        if (phase==Phases.G1){
            if(currentCell.getMutationStatus().get(Mutations.NO_RESOURCES)){
                phase = Phases.G0;
                result = false;
            }
        } else if(phase==Phases.G2){
            if(currentCell.getMutationStatus().get(Mutations.DNA_ERROR)){
                result = false;
            }
        } else if(phase == Phases.M){
            if(currentCell.getMutationStatus().get(Mutations.CHROMOSOME_MISALIGNED)){
                result = false;
            }
        } else if(phase == Phases.G0){
            int age = currentCell.getTracker().getAge();
            if(age >=90){
                phase = Phases.G1;
            }
        }
        return result;
    }

}
