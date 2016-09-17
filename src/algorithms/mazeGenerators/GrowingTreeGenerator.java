package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// A maze Generator that uses GrowingTree algorithm to generate a Maze
public class GrowingTreeGenerator extends CommonMaze3dGenerator{
	
	// A strategy for picking a cell from a list, for the algorithm
	IPickCellStrategy PickStrategy;
	
	// Generates a maze with Growing Tree algorithm.
	// Made of 4 steps, as described in the chore.
	@Override
	public Maze3d generate(int x_axis, int y_axis, int z_axis, IPickCellStrategy pickStrategy) {
		Maze3d maze = new Maze3d(x_axis, y_axis, z_axis);
		PickStrategy = pickStrategy;
		//step 1
		//Let listOfCells be a list of cells, initially empty
		System.out.println("Starting Growing Tree Maze3d Generator. TotalX = " + maze.getX_axis() + "TotalY = " + maze.getY_axis()  + "TotalZ = " + maze.getZ_axis());
		List<Position> listOfCells = new ArrayList<Position>();
		
		//step 2
		//Add one cell to C, at random
		Random rand = new Random(); 
		int x = randOddNumber(maze.getX_axis());
		int y = randOddNumber(maze.getY_axis()); 
		int z = randOddNumber(maze.getZ_axis()); 		
		listOfCells.add(new Position(x,y,z));
		System.out.println("Random start = " + listOfCells.get(0).toString());
		System.out.println();
		
		//Repeat steps 3-4 until C is empty
		while(listOfCells.isEmpty() == false){
			//step 3
			//Choose a cell c from C
			Position chosenCell = PickStrategy.Pick(listOfCells);
			
			//step 4
			//If c has unvisited neighbors:
			//Choose an unvisited neighbor of cell c. Call it neighborCell
			List<Position> listOfUnvisitedNeighbors = maze.getUnvisitedNeighbors(chosenCell);
			if(listOfUnvisitedNeighbors.isEmpty() == false){
				Position unvisitedNeighbor = listOfUnvisitedNeighbors.get(rand.nextInt(listOfUnvisitedNeighbors.size()));
				maze.RemoveWallBetweenCells(chosenCell, unvisitedNeighbor);
				listOfCells.add(unvisitedNeighbor);
			}
			else
				listOfCells.remove(chosenCell);
		}
		
		//Generate entrance and exit
		boolean foundStart= false;
		boolean foundEnd= false;
		List<Position> temp = null;
		
		for (int m = 1; m < maze.getX_axis()-1; m=m+2) {
			for (int n = 1; n < maze.getY_axis()-1; n=n+2) {
				if (maze.getValueAt(m,n,1) == 0 && !foundStart)
				{
					maze.setStartPosition(new Position(m,n,1));
					foundStart = true;
					break;					
				}
			}
		}
		
		for (int m = 1; m < maze.getX_axis()-1; m=m+2) {
			for (int n = 1; n < maze.getY_axis()-1; n=n+2) {
				if (maze.getValueAt(m,n,maze.getZ_axis()-2) == 0 && !foundEnd)
				{

					maze.setGoalPosition(new Position(m,n,maze.getZ_axis()-2));
					foundEnd = true;
					break;
								
				}
			}
		}
		
		return maze;
	}
}
