import java.util.ArrayList;
import java.util.Scanner;
public class Tissue {


    private ArrayList<Cell> baseCells;
    public Tissue(int baseCellNum){
        baseCells = new ArrayList<Cell>();
        for(int i=0;i<baseCellNum;i++){
            baseCells.add(new Cell());
        }
    }
    public void execute(int timePassed){
        ArrayList<Integer> idList = new ArrayList<Integer>();
        int parent_size = baseCells.size();

        for(int i=0;i<parent_size;i++){

            Cell currentCell = baseCells.get(i);

            //after the cell executes, if the cell is in mitosis
            if(currentCell.execute(timePassed)){
                //make this the mitosis funtcion until line 29
               if(currentCell.getPhase()==Phases.M){
                   idList.add(currentCell.getTracker().getID());
                   boolean cancer= currentCell.getTracker().getCancerous();
                   //base age is the beginning age of cell, if parent is over 60 when goes through mitosis
                   baseCells.add(new Cell(cancer, currentCell.getMutationStatus(),(currentCell.getTracker().getAge())-50));
                   baseCells.add(new Cell(cancer,currentCell.getMutationStatus(),(currentCell.getTracker().getAge())-50));
               }
               else if(currentCell.getPhase()!=Phases.G0){
                   idList.add(currentCell.getTracker().getID());
               }
            }

        }
        for(Integer ridID: idList){
            baseCells.removeIf(n-> (n.getTracker().getID()==ridID));
        }
    }
    public void display(double cycle){
        System.out.println("CYCLES: "+ cycle);
        System.out.println("Current Cell Arrays:");
        for(int i=0; i<baseCells.size();i++){
            baseCells.get(i).display();
            System.out.print("   ");
            if((i+1)%5==0&&i!=0){
                System.out.println();
            }
        }
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());

        for(int i=0; i<baseCells.size();i++){
            baseCells.get(i).display();
            System.out.println("- Age:"+baseCells.get(i).getTracker().getAge());
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("How many base cells would you like?: ");
        int num = in.nextInt();
        Tissue thisTissue = new Tissue(num);
        System.out.print("How much time has passed?: ");
        int time = in.nextInt();
        //total rounds we will go through per enter
        double rounds = (double)time / 60;
        /*how many full cycles we will pass*/
        int cycle = time / 60;
        /*what cycles the display is on changes as the user has pressed enter*/
        double curCycle=0;


        thisTissue.display(curCycle);
        while (true) {
            System.out.println("‧͙⁺˚*･༓☾ LOOK AT THE MAGIC HAPPEN!!! ☽༓･*˚⁺‧͙");

            for (int i = 0; i < cycle; i++) {
                thisTissue.execute(60);
                curCycle++;
                thisTissue.display(curCycle);

            }
            if(cycle<rounds){
                thisTissue.execute(time%60);
                curCycle += rounds-(double)cycle;
                thisTissue.display(curCycle);
            }

                        System.out.println("To see the cells duplicate again, for the same amount of time, press any key.");
            System.out.print("If you want to exit, click [1] and press [Enter]");
            String close = in.next();

            System.out.println();
            System.out.println();
            if (close.equals("1")) {
                break;
            }
            rounds=rounds+rounds;
            cycle=(int)rounds;
        }
    }
}