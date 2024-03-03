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
    public boolean test(Cell currentCell){
        this.currentCell = currentCell;
        phase = this.currentCell.getPhase();
        if (phase==Phases.G1){
            if(currentCell.getMutationStatus().get(Mutations.NO_RESOURCES)){
                return false;
            }

            return true;
        } else if(phase==Phases.G2){
            if(currentCell.getMutationStatus().get(Mutations.DNA_ERROR)){
                return false;
            }
            return true;
        } else if(phase == Phases.M){
            if(currentCell.getMutationStatus().get(Mutations.CHROMOSOME_MISALIGNED)){
                return false;
            }
            return true;
        }
        else {return false;}

    }

}
