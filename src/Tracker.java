import java.util.Random;
public class Tracker {
    private int age;
    public static int IDNum=0;
    private int ID;
    private boolean cancerous;
    public Tracker(){
        Random rand = new Random();
        age = rand.nextInt(0,61);
        ID = IDNum;
        IDNum += 1;
    }
    public void updateAge(int amount){
        age += amount;
    }

}
