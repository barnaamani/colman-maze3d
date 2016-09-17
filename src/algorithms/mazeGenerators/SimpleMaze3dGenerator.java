package algorithms.mazeGenerators;

//Generates a random Maze.
public class SimpleMaze3dGenerator extends CommonMaze3dGenerator {
	@Override
	public Maze3d generate(int x_axis, int y_axis, int z_axis, IPickCellStrategy pickStrategy) {
		
		Maze3d maze = new Maze3d(x_axis, y_axis, z_axis);
		System.out.println("Starting Simple Maze3d Generator. TotalX = " + maze.getX_axis() + " TotalY = " + maze.getY_axis()  + " TotalZ = " + maze.getZ_axis());
		int randomXStrat = randOddNumber(maze.getX_axis());
		int randomYStart = randOddNumber(maze.getY_axis());
		System.out.println("randomXStrat = " + randomXStrat + " randomYStart = " + randomYStart);
		maze.setStartPosition(new Position(randomXStrat, randomYStart, 0));
		System.out.println();
		
		int z =0;
		for (z = 0; z < maze.getZ_axis(); z=z+2) {
			maze.RemoveWallBetweenCells(new Position(randomXStrat,randomYStart,z), new Position(randomXStrat,randomYStart,z+2));
		}
		maze.setGoalPosition(new Position(randomXStrat, randomYStart, maze.getZ_axis()-1));
		//System.out.println(maze.getCell(maze.getGoalPosition()));
		return maze;
	}

}
