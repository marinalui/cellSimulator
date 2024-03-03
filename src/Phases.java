/**
 * Enum that holds the different phases of the cell cycle
 *
 * Each value corresponds to how many seconds a phase takes. A full cycle has 60 seconds or 1 minute.
 * G0: is set to -90 because it should age 1.5 cycles before re-entering the cell cycle with a G1 phase.
 *
 * @author Marina Lui
 * @author Ashley Miller
 */

public enum Phases {
    G1(20), S(35),G2(50), M(60),G0(-90);
    private final int timePeriod;
    private Phases( int timePeriod){
        this.timePeriod= timePeriod;
    }
    public int getTime(){
        return timePeriod;
    }
}
