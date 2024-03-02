public class Checkpoint {
    private Phases phase;
    private Cell currentCell;
    public Checkpoint(){

    }
    public boolean test(Cell currentCell){
        this.currentCell = currentCell;
        phase = this.currentCell.getPhase();
        if (phase==phase.G1){
            return true;
        }
        else if(phase==phase.G2){return true;}
        else {return false;}

    }
}
