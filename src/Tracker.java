/**
 * Keeps tabs on the variables for a cell. Gets created with each Cell.java constructor call.
 * @author Marina Lui
 * @author Ashley Miller
 */

import java.util.Random;
public class Tracker {
    private int age;
    public static int IDNum=0;
    private int ID;
    private boolean cancerous;
    //base cells constructor
    public Tracker(){
        Random rand = new Random();
        age = rand.nextInt(-1,50);//comment  for test, set this age = 60
        ID = IDNum;
        IDNum += 1;
        cancerous = false;
        if (age == -1){
            age = -90;
        }
    }
    //daughter cells constructor
    public Tracker(boolean cancerous,int baseAge){
        //base age is the beginning age of cell, if parent is over 60 when goes through mitosis
        age = baseAge;
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

