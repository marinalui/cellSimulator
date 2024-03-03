import java.util.*;
public class Cell{
    private Tracker tracker;
    private Phases phase;
    private HashMap<Checkpoint.Mutations, Boolean> mutationStatus = new HashMap<Checkpoint.Mutations, Boolean>();

    public Cell(){
        tracker = new Tracker();
        determinePhase(tracker.getAge());
    }
    public Cell(boolean cancerous){
        tracker = new Tracker(cancerous);
        determinePhase(tracker.getAge());
    }
    public void determinePhase(int age){
        if (0<=age && age<=Phases.G1.getTime()){
            phase = Phases.G1;
        } else if (Phases.G1.getTime()<age && age <=Phases.S.getTime()){
            phase = Phases.S;
        } else if (Phases.S.getTime()<age && age <=Phases.G2.getTime()){
            phase = Phases.G2;
        } else if (Phases.G2.getTime()<age) {
            phase = Phases.M;
        }
    }
    public Tracker getTracker(){
        return tracker;
    }
    public Phases getPhase(){
        return phase;
    }
    public HashMap<Checkpoint.Mutations, Boolean> getMutationStatus(){
        return mutationStatus;
    }
    public void updateMutationStatus(){
        //for()
    }
    /**
     * updates the cells phase
     * @param time
     * @return if the cell passed the checkpoint, if false it should get deleted later
     */
    public boolean execute(int time){
        boolean result=true;
        tracker.updateAge(time);
        Phases previousPhase = phase;
        determinePhase(tracker.getAge());
        Checkpoint checkpoint = new Checkpoint();
        if(previousPhase!= phase&& phase!=Phases.S){
           if (!checkpoint.test(this)){
               result = false;
           }
        }
        return result;
    }
}
