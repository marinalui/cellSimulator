import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 *
 * @author Ashley
 * @author Marina
 */
public class Tissue {

    /**The collection of cells that make up the tissue **/
    private ArrayList<Cell> baseCells;

    /**
     * This is the constructor of the cells. This function enters a loop in which it creates the number of cells as the
     * user specified.
     * @param baseCellNum number of base cells to start off with
     *
     */
    public Tissue(int baseCellNum){
        baseCells = new ArrayList<Cell>();
        for(int i=0;i<baseCellNum;i++){
            baseCells.add(new Cell());
        }
    }

    /**
     * This function is one cycle of cells, it goes through each cell in the tissue and adds the time passed to the age
     * of the cell, and runs the cell's execute function:
     *      - Returns whether or not it passed the checkpoint
     *      - Updates age of cell
     *      - Updates phase of cell based cell
     *      - Runs through the checkpoint
     *      - Then it either mutates the cell or it doesn't
     *
     * If the cell's execute does pass, and the cell is not in G0 phase, remove it
     * If the cell's execute does pass, and the cell is in M phase, duplicate it
     *      add the current cell's id to the remove list and add 2 daughter cells to the base cells
     *
     *then go through each id in the remove list, find the cell with the id and remove it from the baseCells list
     * @param timePassed - the amount of time passed in the cycle.
     */
    public void execute(int timePassed){
        ArrayList<Integer> idList = new ArrayList<Integer>();
        int parent_size = baseCells.size();

        for(int i=0;i<parent_size;i++){

            Cell currentCell = baseCells.get(i);

            //after the cell executes, if the cell is in mitosis
            if(currentCell.execute(timePassed)){
                //make this the mitosis funtcion until line 29
               if((currentCell.getPhase()==Phases.M)){
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

    /**
     * This function displays the cell cycle.
     * It goes through each cell in the baseCells list and calls the cell's display.
     * Every fifth cell, click enter.
     * Then print the ages of the cells
     *
     * @param cycle the cycle the function is currently on
     */
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

    /**
     * This function serves as the main function in our program.
     * 1) Asks the user for the number of base cells it would like to begin with
     * 2) Asks the amount of time that has passed the cycle.
     *
     * 3) Then computes the total number of rounds the program with go through is computed
     * 4) Then computes the total number of completed rounds is calculated as an integer
     * 5) Displays the base cells
     * 6) Then goes into a loop where the program does the following everytime the user enter input other than [Enter]:
     *      6A) goes into another for loop that runs until i is equal to the number of whole cycles it should run through.
     * 7) If the number of rounds the user requested is a decimal, the program will run through that after it goes
     *      through the whole number of cycles
     *        7A)executes with the leftover cycle time
     *        7B) recalculates the currentCycle, add the decimal to it.
     *        7C displays the current cycle
     * 8) Prints a statement asking the user if they want to continue with the same time increments
     * 9)If the user states that they do, the number of rounds gets updated, if the user doesn't, the program immediately exits
     * 10)the cycles then also updates
     *
     *
     * @param args
     */
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