import java.util.Random;
public class Tracker {
    private int age;
    public static int IDNum=0;
    private int ID;
    private boolean cancerous;
    //base cells constructor
    public Tracker(){
        Random rand = new Random();
        age = rand.nextInt(0,61);
        ID = IDNum;
        IDNum += 1;
        cancerous = false;
    }
    //daughter cells constructor
    public Tracker(boolean cancerous){
        age = 0;
        ID = IDNum;
        IDNum += 1;
        this.cancerous = cancerous;
    }
    public int getAge(){
        return age;
    }
    public int getID(){
        return ID;
    }
    public boolean getCancerous(){
        return cancerous;
    }
    public void updateAge(int amount){
        age += amount;
    }
    public void updateCancerous(){
        cancerous = true;
    }


}

