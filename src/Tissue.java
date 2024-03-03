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
               if(currentCell.getPhase()==Phases.M){
                   idList.add(currentCell.getTracker().getID());
                   boolean cancer= currentCell.getTracker().getCancerous();
                   baseCells.add(new Cell(cancer, currentCell.getMutationStatus()));
                   baseCells.add(new Cell(cancer,currentCell.getMutationStatus()));
               }
            }
        }
        for(Integer ridID: idList){
            baseCells.removeIf(n-> (n.getTracker().getID()==ridID));
        }
    }
    public void display(int roundNum){
        System.out.println("ROUND: "+ roundNum);
        System.out.println("Current Cell Arrays:");
        for(int i=0; i<baseCells.size();i++){
            baseCells.get(i).display();
            System.out.print("   ");
            if(i%4==0&&i!=0){
                System.out.println();
            }
        }
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("How many base cells would you like?: ");
        int num = in.nextInt();
        Tissue thisTissue = new Tissue(num);
        System.out.print("How much time has passed?: ");
        int time = in.nextInt();
        int rounds = time/60;

        System.out.println("‧͙⁺˚*･༓☾ LOOK AT THE MAGIC HAPPEN!!! ☽༓･*˚⁺‧͙");
        thisTissue.display(0);
        for(int i=0;i<rounds;i++) {
            thisTissue.execute(time);
            thisTissue.display(rounds);
        }


    }
}
