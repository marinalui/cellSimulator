import java.util.HashSet;

public class Checkpoint {
    public enum Mutations{
        NO_RESOURCES, DNA_ERROR,CHROMOSONE_MISALIGNED;

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
            //generate random number

            return true;
        } else if(phase==Phases.G2){
            return true;
        } else if(phase == Phases.M){
            return true;
        }
        else {return false;}

    }

}
