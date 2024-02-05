package AIWork;
//Nicholas Stephani, A.I. Homework 4
import java.util.ArrayList;
import csis4463.*;

public class Homework4 {
    public static void main(String[] args){

        PuzzleAnalyzer solution = new PuzzleAnalyzer(10);

        //This prints out the number of states expanded, formatting the output to be 2 deciaml places. 
        System.out.println("Num States Expanded:");
        System.out.println("L\t\tUCS\t\tA*1\t\tA*2\t\tID\t\tIDA*1\t\tIDA*2");
        for(int x = 2; x <= 12; x += 2){
            System.out.print(x);
            for(int y = 0; y < 6; y++){
                System.out.printf("\t\t%.2f", solution.getAverage(y,x));
            }
            System.out.println();
        }
        System.out.println();

        //This prints out the number of states generated, formatting the output to be 2 deciaml places. 
        System.out.println("Num States Generated:");
        System.out.println("L\t\tUCS\t\tA*1\t\tA*2\t\tID\t\tIDA*1\t\tIDA*2");
        for(int x = 2; x <= 12; x += 2){
            System.out.print(x);
            for(int y = 6; y < 12; y++){
                System.out.printf("\t\t%.2f", solution.getAverage(y,x));
            }
            System.out.println();
        }
        System.out.println();
        //This prints out the number of states in memory used, formatting the output to be 2 deciaml places. 
        System.out.println("Max States in Memory:");
        System.out.println("L\t\tUCS\t\tA*1\t\tA*2\t\tID\t\tIDA*1\t\tIDA*2");
        for(int x = 2; x <= 12; x += 2){
            System.out.print(x);
            for(int y = 12; y < 18; y++){
                System.out.printf("\t\t%.2f", solution.getAverage(y,x));
            }
            System.out.println();
        }
    }
    
}

class PuzzleAnalyzer{

    private ArrayList<SlidingTilePuzzle> listOfPuzzles = new ArrayList();
    private ArrayList<PuzzleSolution> listOfSolutions = new ArrayList();

    //This list stores averges with the x being the average solutions, and y being the position of the average
    private double[][] averageList = new double[20][20];

    //This constructor goes though and makes puzzles, storing their averages as it goes
    public PuzzleAnalyzer(int numberOfPuzzles){

        //This loop makes puzzles with solutions 2, 4, 6, 8, 10, 12. Then it saves their averages in an array
        for(int x = 2; x <= 12; x+= 2){

            for(int y = 0; y < numberOfPuzzles; y++){
                SlidingTilePuzzle puzzle = new SlidingTilePuzzle(3, 3, x);
                listOfPuzzles.add(puzzle);
            }
            setUCSValues(x);
            setA1Values(x);
            setA2Values(x);
            setIDValues(x);
            setIDA1Values(x);
            setIDA2Values(x);
        }
    }

    //This loops thorough the list of puzzles, solving with UCS, and then saves their averages
    public void setUCSValues(int y){
        for(int x = 0; x < listOfPuzzles.size(); x++){
            PuzzleSolution solution = SlidingTilePuzzleSolver.uniformCostSearch(listOfPuzzles.get(x));
            listOfSolutions.add(solution);
        }
        averageList[0][y] = getAverageExpandedStates();
        averageList[6][y] = getAverageGeneratedStates();
        averageList[12][y] = getAverageMaxMemory();

        listOfSolutions.clear();
    }

    //This loops thorough the list of puzzles, solving with A* with Misplaced Tiles as the heuristic, and then saves their averages
    public void setA1Values(int y){
        for(int x = 0; x < listOfPuzzles.size(); x++){
            PuzzleSolution solution = SlidingTilePuzzleSolver.AStarSearchMisplacedTiles(listOfPuzzles.get(x));
            listOfSolutions.add(solution);
        }
        averageList[1][y] = getAverageExpandedStates();
        averageList[7][y] = getAverageGeneratedStates();
        averageList[13][y] = getAverageMaxMemory();

        listOfSolutions.clear();
    }

    //This loops thorough the list of puzzles, solving with A* with Manhattan Distance as the heuristic, and then saves their averages
    public void setA2Values(int y){
        for(int x = 0; x < listOfPuzzles.size(); x++){
            PuzzleSolution solution = SlidingTilePuzzleSolver.AStarSearchManhattanDistance(listOfPuzzles.get(x));
            listOfSolutions.add(solution);
        }
        averageList[2][y] = getAverageExpandedStates();
        averageList[8][y] = getAverageGeneratedStates();
        averageList[14][y] = getAverageMaxMemory();

        listOfSolutions.clear();
    }

    //This loops thorough the list of puzzles, solving with iterative deepening, and then saves their averages
    public void setIDValues(int y){
        for(int x = 0; x < listOfPuzzles.size(); x++){
            PuzzleSolution solution = SlidingTilePuzzleSolver.iterativeDeepening(listOfPuzzles.get(x));
            listOfSolutions.add(solution);
        }
        averageList[3][y] = getAverageExpandedStates();
        averageList[9][y] = getAverageGeneratedStates();
        averageList[15][y] = getAverageMaxMemory();

        listOfSolutions.clear();
    }

    //This loops thorough the list of puzzles, solving with IDA* with Misplaced Tiles as the heuristic, and then saves their averages
    public void setIDA1Values(int y){
        for(int x = 0; x < listOfPuzzles.size(); x++){
            PuzzleSolution solution = SlidingTilePuzzleSolver.idaStarMisplacedTiles(listOfPuzzles.get(x));
            listOfSolutions.add(solution);
        }
        averageList[4][y] = getAverageExpandedStates();
        averageList[10][y] = getAverageGeneratedStates();
        averageList[16][y] = getAverageMaxMemory();

        listOfSolutions.clear();
    }

    //This loops thorough the list of puzzles, solving with IDA* with Manhattan Distance as the heuristic, and then saves their averages
    public void setIDA2Values(int y){
        for(int x = 0; x < listOfPuzzles.size(); x++){
            PuzzleSolution solution = SlidingTilePuzzleSolver.idaStarManhattanDistance(listOfPuzzles.get(x));
            listOfSolutions.add(solution);
        }
        averageList[5][y] = getAverageExpandedStates();
        averageList[11][y] = getAverageGeneratedStates();
        averageList[17][y] = getAverageMaxMemory();

        listOfSolutions.clear();
    }

    //This takes the list of solutions, and computes the average number of expanded states
    public double getAverageExpandedStates(){
        double sum = 0;
        for(int x = 0; x < listOfSolutions.size(); x++){
            sum += listOfSolutions.get(x).getNumberOfStatesExpanded();
        }
        return sum/listOfSolutions.size();
    }

    //This takes the list of solutions, and computes the average number of Generated States
    public double getAverageGeneratedStates(){
        double sum = 0;
        for(int x = 0; x < listOfSolutions.size(); x++){
            sum += listOfSolutions.get(x).getNumGenerated();
        }
        return sum/listOfSolutions.size();
    }

    //This takes the list of solutions, and computes the average max memory used by the search
    public double getAverageMaxMemory(){
        double sum = 0;
        for(int x = 0; x < listOfSolutions.size(); x++){
            sum += listOfSolutions.get(x).getNumberOfStatesInMemory();
        }
        return sum/listOfSolutions.size();
    }

    //Simply returns the average at a given point
    public double getAverage(int x, int y) {return averageList[x][y];}

}
