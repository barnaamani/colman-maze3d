package algorithms.mazeGenerators;

import java.time.Duration;
import java.time.Instant;

public abstract class CommonMaze3dGenerator implements Maze3dGenerator {
	
	//An implementation for all Maze Generators for measuring the alorithm's calculation time.
	
	public String measureAlgorithmTime(int x_axis, int y_axis, int z_axis, IPickCellStrategy pickStrategy)
	{
		Instant start = Instant.now();
		generate(x_axis, y_axis, z_axis, pickStrategy);
		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		return "Time taken to generate Maze: "+ timeElapsed.toMillis() +" milliseconds\n";
	}
	
	public int randOddNumber(int range){
		if (range % 2 == 0)
			range = range - 1;
		else
			range = range - 2;
		int Random_No = 1 + 2*(int)(Math.random()*((range-1)/2+1));
		
		return Random_No;
	}
}
