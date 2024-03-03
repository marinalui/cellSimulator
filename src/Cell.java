import java.util.*;
public class Cell{
    private Tracker tracker;
    private Phases phase;
    private HashMap<Checkpoint.Mutations, Boolean> mutationStatus = new HashMap<Checkpoint.Mutations, Boolean>();

    public Cell(){
        tracker = new Tracker();
        determinePhase(tracker.getAge());
        mutationStatus.put(Checkpoint.Mutations.NO_RESOURCES,false);
        mutationStatus.put(Checkpoint.Mutations.DNA_ERROR,false);
        mutationStatus.put(Checkpoint.Mutations.CHROMOSOME_MISALIGNED,false);
    }
    public Cell(boolean cancerous, HashMap<Checkpoint.Mutations, Boolean> mutationStatus,int baseAge){
        //base age is the beginning age of cell, if parent is over 60 when goes through mitosis
        tracker = new Tracker(cancerous,baseAge);
        determinePhase(tracker.getAge());
        this.mutationStatus = mutationStatus;
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
        } else if (Phases.G0.getTime() == age){
            phase = Phases.G0;
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
       for(Map.Entry<Checkpoint.Mutations, Boolean> set: mutationStatus.entrySet()){
           Random rand = new Random();
           //can update with relevant chances of mutation in future development
           int chance1 = rand.nextInt(50);
           int chance2 = rand.nextInt(50);
           int chance3 = rand.nextInt(50);
           if(chance1 == 1){
               mutationStatus.put(Checkpoint.Mutations.NO_RESOURCES, true);
           }
           if(chance2 == 1){
               mutationStatus.put(Checkpoint.Mutations.DNA_ERROR, true);
           }
           if(chance3 == 1){
               mutationStatus.put(Checkpoint.Mutations.CHROMOSOME_MISALIGNED, true);
           }
       }
    }
    /**
     * updates the cells phase, age, if it passes checkpoint
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
           } else if (checkpoint.test(this)&& mutationStatus.get(Checkpoint.Mutations.DNA_ERROR)){
               getTracker().updateCancerous();
           } else if(checkpoint.test(this)&& mutationStatus.get(Checkpoint.Mutations.CHROMOSOME_MISALIGNED)){
               getTracker().updateCancerous();
           }
        }
        updateMutationStatus();
        return result;
    }
    public void display(){

        if (this.getTracker().getCancerous()) {
            System.out.print("{C - "+ this.getTracker().getID()+'}');

        }
        else{

            System.out.print("["+this.getPhase()+" - "+this.getTracker().getID()+"]");
        }

    }
}
