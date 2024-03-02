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
        for(Cell currentCell: baseCells){

        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("How many base cells would you like?: ");
        int num = in.nextInt();
        Tissue thisTissue = new Tissue(num);
        System.out.print("How much time has passed?: ");
        int time = in.nextInt();
        thisTissue.execute(time);

    }
}
