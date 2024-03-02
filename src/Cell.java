public class Cell{
    private Tracker tracker;
    private Phases phase;

    public Cell(){
        tracker = new Tracker();
        determinePhase(tracker.getAge());
    }
    public void determinePhase(int age){
        if (0<=age && age<=20){
            phase = Phases.G1;
        } else if (20<age && age <=35){
            phase = Phases.S;
        } else if (35<age && age <=50){
            phase = Phases.G2;
        } else if (50<age && age <=60) {
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
    public void execute(int time){
       tracker.updateAge(time);
    }
}
