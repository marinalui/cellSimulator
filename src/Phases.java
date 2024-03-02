public enum Phases {
    G1(20), S(35),G2(50), M(60);
    public final int timePeriod;
    private Phases( int timePeriod){
        this.timePeriod= timePeriod;
    }
}
