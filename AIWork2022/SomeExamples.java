import csis4463.*;
import java.util.ArrayList;

public class SomeExamples {
	
	public static void main(String[] args) {
		
		
		// To construct an 8-puzzle first two params should be 3 (a 3 by 3 puzzle). 
		// The 3rd parameter is the length of the shortest path from this state to goal state.
		// E.g., 4 as the 3rd param will generate a random puzzle such that it is possible to
		// find a solution with length 4.
		SlidingTilePuzzle puzzle = new SlidingTilePuzzle(3, 3, 4);
		
		// The SlidingTilePuzzle class overrides the toString method, so you can output
		// a puzzle state with a call to System.out.println as follows.
		System.out.println(puzzle);
		
		// You can solve a puzzle using the implementations of search algorithms provided in the
		// class SlidingTilePuzzleSolver.  See the documentation of that class for the search algorithms
		// included.  You call them all the same way.  Here is an example with A* and Manhattan distance.
		PuzzleSolution solution = SlidingTilePuzzleSolver.AStarSearchManhattanDistance(puzzle);
		
		// After the search is done, you can find the solution as well as statistics about the search
		// via the PuzzleSolution object.  See the documentation for names of the methods.  Here
		// are a couple examples.
		
		// The getSolution method gives you the path as an ArrayList.
		ArrayList<SlidingTilePuzzle> path = solution.getSolution();
		
		System.out.println("Solution path");
		for (SlidingTilePuzzle s : path) {
			System.out.println();
			System.out.println(s);
		}
		
		System.out.println();
		System.out.println("Num states expanded: " + solution.getNumberOfStatesExpanded());
		System.out.println("Num states generated: " + solution.getNumGenerated());
		System.out.println("Num states in memory: " + solution.getNumberOfStatesInMemory());
		System.out.println("Path length: " + solution.getPathLength());
		
	}
}