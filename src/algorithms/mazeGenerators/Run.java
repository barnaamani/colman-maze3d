package algorithms.mazeGenerators;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import algorithms.demo.Demo;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;

/**
 * This class demonstrate Maze functionality
 */
public class Run 
{
	private static void testMazeGenerator(Maze3dGenerator mg) throws Exception{
		
		PickRandomCell pick = new PickRandomCell();
		
		// prints the time it takes the algorithm to run
		//System.out.println(mg.measureAlgorithmTime(5,5,5,pick));
		
		// generate another 3d maze
		Maze3d maze=mg.generate(5,5,5,pick);
		
		// get the maze entrance
		Position p = maze.getStartPosition();
		
		// print the position
		System.out.println(p); // format "{x,y,z}"
		
		// get all the possible moves from a position
		String[] moves = maze.getPossibleMoves(p);
		
		// print the moves
		for(String move : moves)
				System.out.println(move);
		
		// prints the maze exit position
		System.out.println(maze.getGoalPosition());
		
		try {
			// get 2d cross sections of the 3d maze
			//System.out.println();
			//int[][] maze2dx=maze.getCrossSectionByX(5);
			//printMaze2dBySection(maze2dx);
			//int[][] maze2dy=maze.getCrossSectionByY(5);
			//printMaze2dBySection(maze2dy);
			//int[][] maze2dz=maze.getCrossSectionByZ(0);
			//printMaze2dBySection(maze2dz);
			System.out.println(maze);
			//maze2dz=maze.getCrossSectionByZ(5);
			//printMaze2dBySection(maze2dz);
			
			// this should throw an exception!
			maze.getCrossSectionByX(-1);
		} 
		
		catch (IndexOutOfBoundsException e){
			System.out.println("good!");
		}
	}
	
	public static void main(String[] args) throws Exception 
	{
			
			//testMazeGenerator(new SimpleMaze3dGenerator());
			//testMazeGenerator(new GrowingTreeGenerator());
		    Demo d = new Demo();
			d.run(5,5,5);
	}
	
	public static void printMaze2dBySection(int [][] matrix)
	{
		System.out.println("Matrix Section is: ");
		for (int i=0;i<matrix.length;i++)
		{
			for (int j=0;j<matrix[0].length;j++)
			{
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
}
