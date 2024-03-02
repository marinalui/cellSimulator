public class Checkpoint {
    private Phases phase;
    private Cell currentCell;
    public Checkpoint(){

    }
    public boolean test(Cell currentCell){
        this.currentCell = currentCell;
        phase = this.currentCell.getPhase();
        if (phase==Phases.G1){
            return true;
        } else if(phase==Phases.G2){
            return true;
        } else if(phase == Phases.M){
            return true;
        }
        else {return false;}

    }

}
