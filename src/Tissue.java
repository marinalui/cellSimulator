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
                   baseCells.add(new Cell(cancer));
                   baseCells.add(new Cell(cancer));
               }
            }
            else{
                if(currentCell.getPhase()==Phases.G0) {
                    idList.add(currentCell.getTracker().getID());
                }
            }
        }
        for(Integer ridID: idList){
            baseCells.removeIf(n-> (n.getTracker().getID()==ridID));
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("How many base cells would you like?: ");
        int num = in.nextInt();
        Tissue thisTissue = new Tissue(num);
        System.out.print("How much time has passed?: ");
        int time = in.nextInt();
        int rounds = time/60;
        for(int i=0;i<rounds;i++) {
            thisTissue.execute(time);
        }

    }

}
