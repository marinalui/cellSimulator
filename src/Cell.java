import java.util.*;
/**
 * Creates one cell. A private tracker hold relevant the variables.
 * Mutations get stored in a hashmap.
 *
 * @author Marina Lui
 * @author Ashley Miller
 */
public class Cell{
    private Tracker tracker;
    private Phases phase;
    /**When a cell gets a certain mutation:NO_RESOURCES, DNA_ERROR, CHROMOSOME_MISALIGNED
     * it will update to a true value in the hashmap.*/
    private HashMap<Checkpoint.Mutations, Boolean> mutationStatus = new HashMap<Checkpoint.Mutations, Boolean>();

    /**
     * A constructor for original base cells that creates a tracker, determines the phase, fills the hashmap
     * with mutation keys and false values
     */
    public Cell(){
        tracker = new Tracker();
        determinePhase(tracker.getAge());
        mutationStatus.put(Checkpoint.Mutations.NO_RESOURCES,false);
        mutationStatus.put(Checkpoint.Mutations.DNA_ERROR,false);
        mutationStatus.put(Checkpoint.Mutations.CHROMOSOME_MISALIGNED,false);
    }

    /**
     * a constructor for daughter cells
     * @param cancerous boolean on whether the parent cell was cancerous
     * @param mutationStatus hashmap of which mutations the parent had
     * @param baseAge age calculated based on the parent cell age minus the mitosis cycle
     */
    public Cell(boolean cancerous, HashMap<Checkpoint.Mutations, Boolean> mutationStatus,int baseAge){
        //base age is the beginning age of cell, if parent is over 60 when goes through mitosis
        tracker = new Tracker(cancerous,baseAge);
        determinePhase(tracker.getAge());
        this.mutationStatus = mutationStatus;
    }

    /**
     * determines the phase based on the age and assigns the correct one to the phase variable
     * @param age how many seconds old out of 60 seconds the cell is.
     */
    public void determinePhase(int age){
        if (0<=age && age<=Phases.G1.getTime()){
            phase = Phases.G1;
        } else if (Phases.G1.getTime()<age && age <=Phases.S.getTime()){
            phase = Phases.S;
        } else if (Phases.S.getTime()<age && age <=Phases.G2.getTime()){
            phase = Phases.G2;
        } else if (Phases.G2.getTime()<age) {
            phase = Phases.M;
        } else if (Phases.G0.getTime() <=age){
            phase = Phases.G0;
        }
    }

    /**
     * tracker getter
     * @return tracker
     */
    public Tracker getTracker(){
        return tracker;
    }
    /**
     * phase getter
     * @return phase
     */
    public Phases getPhase(){
        return phase;
    }
    /**
     * mutationStatus getter
     * @return mutationStatus
     */
    public HashMap<Checkpoint.Mutations, Boolean> getMutationStatus(){
        return mutationStatus;
    }

    /**
     * generates the odds for if a cell gets a mutation and updates the mutationStatus hashmap values
     */
    public void updateMutationStatus(){
       for(Map.Entry<Checkpoint.Mutations, Boolean> set: mutationStatus.entrySet()){
           Random rand = new Random();
           //can update with relevant chances of mutation in future development
           int chance1 = rand.nextInt(100);
           int chance2 = rand.nextInt(100);
           int chance3 = rand.nextInt(100);
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
     * 1.updates the cells age
     * 2.determines if it is in a new phase
     * 3. goes through the correct checkpoints
     * 4. runs the odds and applies results for if a cell mutates
     * @param time how much time got added to the age of a cell
     * @return if the cell passes the checkpoint or not
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

    /**
     * displays each cell to the console.
     * Non-cancerous cell: []
     * Cancerous cell: {}
     * Includes phase and ID in the [] or {}
     * <p>
     * example:
     * [G1-1] [G2-2] {S-3}
     */
    public void display(){
        if (this.getTracker().getCancerous()) {
            System.out.print("{C - "+ this.getTracker().getID()+'}');
        }
        else{
            System.out.print("["+this.getPhase()+" - "+this.getTracker().getID()+"]");
        }
    }
}
