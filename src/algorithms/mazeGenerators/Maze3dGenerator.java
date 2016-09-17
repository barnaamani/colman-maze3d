package algorithms.mazeGenerators;

// Generates a Maze
public interface Maze3dGenerator {
	Maze3d generate(int x_axis, int y_axis, int z_axis, IPickCellStrategy pickStrategy);
	String measureAlgorithmTime(int x_axis, int y_axis, int z_axis, IPickCellStrategy pickStrategy);
}
