public class Cell{
    private Tracker tracker;
    private Phases phase;

    public Cell(){
        tracker = new Tracker();
        determinePhase(tracker.getAge());
    }
    public void determinePhase(int age){
        if (0<=age && age<=Phases.G1.getTime()){
            phase = Phases.G1;
        } else if (Phases.G1.getTime()<age && age <=Phases.S.getTime()){
            phase = Phases.S;
        } else if (Phases.S.getTime()<age && age <=Phases.G2.getTime()){
            phase = Phases.G2;
        } else if (Phases.G2.getTime()<age && age <=Phases.M.getTime()) {
            phase = Phases.M;
        }else{
            System.out.println("Age is out of bounds");
        }
    }
    public Tracker getTracker(){
        return tracker;
    }
    public Phases getPhase(){
        return phase;
    }
    //TODO
    public void execute(int time){
       tracker.updateAge(time);
       Phases previousPhase = phase;
       determinePhase(tracker.getAge());
       if(phase==Phases.G1&&previousPhase!= Phases.G1){

       }
    }
}
