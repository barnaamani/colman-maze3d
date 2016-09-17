package algorithms.demo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.PickRandomCell;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;
import algorithms.search.BFS;
import algorithms.search.DFSSearcher;
//import algorithms.search.DFSSearcher;
import algorithms.search.Solution;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

/**
 * This class demonstrate used of Solution Searchers Algorithms
 */
public class Demo 
{
	/**
	 * This method generate maze and solve it with 2 different algorithms
	 * @param height - Maze height required to generate
	 * @param length - Maze length required to generate
	 * @param width  - Maze width required to generate
	 */
	public void run(int height,int length,int width) 
	{
		try{
			// Maze Generation
			PickRandomCell pick = new PickRandomCell();
			//SimpleMaze3dGenerator generator = new SimpleMaze3dGenerator();
			GrowingTreeGenerator generator = new GrowingTreeGenerator();
			Maze3d maze = generator.generate(4,4,4,pick);
			System.out.println(maze);
			
			// Maze Positions
			Position entrance = maze.getStartPosition();
			System.out.println("Maze Entrance \tPosition: " + entrance);
			Position goal = maze.getGoalPosition();
			System.out.println("Maze Exit \tPosition: " + goal);
			
			Maze3dAdapter adapter = new Maze3dAdapter(maze);
			
			// BFS Search
			BFS<Position> bfs = new BFS<Position>();
			Solution<Position> solution = bfs.search(adapter);
			System.out.println("BFS Solution");
			System.out.println("------------");
			System.out.println(solution);
			System.out.println("The Number of evaluated nodes with BFS Algorithm = " + bfs.getNumberOfNodesEvaluated());
			System.out.println();
			
			// DFS Search
			DFSSearcher<Position> dfs = new DFSSearcher<Position>();
			Solution<Position> solutionDfs = dfs.search(adapter);
			System.out.println("DFS Solution");
			System.out.println("------------");
			System.out.println(solutionDfs);
			System.out.println("The Number of evaluated nodes with DFS Algorithm = " + dfs.getNumberOfNodesEvaluated());
			
			/*// save it to a file
			OutputStream out=new MyCompressorOutputStream(
			new FileOutputStream("1.maz"));
			out.write(maze.toByteArray());
			out.flush();
			out.close();
			InputStream in = new MyDecompressorInputStream(
					new FileInputStream("1.maz"));

			byte b[]=new byte[maze.toByteArray().length];
			in.read(b);
			in.close();
			Maze3d loaded=new Maze3d(b);
			System.out.println(loaded.equals(maze));*/
		}
			
			
			
			
			
			
			
			/*// save it to a file
			OutputStream out;
			try {
				out = new MyCompressorOutputStream(
						new FileOutputStream("1.maz"));
				byte[] arr = maze.toByteArray();
				out.write(arr.length);
				out.write(arr);
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
			InputStream in;
			try {
				in = new MyDecompressorInputStream(
					new FileInputStream("1.maz"));
				int size = in.read();			
				byte b[]=new byte[size];
				in.read(b);
				in.close();	
				
				Maze3d loaded = new Maze3d(b);
				System.out.println("maze loaded from file:");
				System.out.println(loaded);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		

	}

}