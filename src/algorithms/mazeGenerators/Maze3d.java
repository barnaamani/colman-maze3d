package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;

// A Maze is a data structure, made of cells and separated by walls (Just like the example of a MazeGenerator).
// In order to be able to pass between two cells, there must not be a wall between them. 
public class Maze3d {
	
	//Maze, size of maze and walls
	int[][][] Maze;
	private int X_axis;
	private int Y_axis;
	private int Z_axis;
	private Position startPosition;
	private Position goalPosition;
	public final static int WALL = 1;
	public final static int FREE = 0;
	
	// A Maze is initially full of walls and cells are empty
	public Maze3d(int x_axis, int y_axis, int z_axis) {
		X_axis = 2*x_axis+1;
		Y_axis = 2*y_axis+1;
		Z_axis = 2*z_axis+1;
		Maze = new int[X_axis][Y_axis][Z_axis];
		for (int x = 0; x < X_axis; x++) {
			for (int y = 0; y < Y_axis; y++) {
				for (int z = 0; z < Z_axis; z++) {
					Maze[x][y][z] = 1;
				}
			}
		}
	}
	
	public Maze3d(byte[] arr) {
		int k = 0;
		this.X_axis = arr[k++];
		this.Y_axis = arr[k++];
		this.Z_axis = arr[k++];
		Maze = new int[X_axis][Y_axis][Z_axis];

		Position startPos = new Position(arr[k++], arr[k++], arr[k++]);
		this.setStartPosition(startPos);
		Position goalPos = new Position(arr[k++], arr[k++], arr[k++]);
		this.setGoalPosition(goalPos);
		
		for (int x = 0; x < X_axis; x++) {
			for (int y = 0; y < Y_axis; y++) {
				for (int z = 0; z < Z_axis; z++) {
					Maze[x][y][z] =  arr[k++];
				}
			}
		}
	}
	
	public byte[] toByteArray() {
		ArrayList<Byte> arr = new ArrayList<Byte>();
		arr.add((byte)this.X_axis);
		arr.add((byte)this.Y_axis);
		arr.add((byte)this.Z_axis);
		arr.add((byte)startPosition.getX());
		arr.add((byte)startPosition.getY());
		arr.add((byte)startPosition.getZ());
		arr.add((byte)goalPosition.getX());
		arr.add((byte)goalPosition.getY());
		arr.add((byte)goalPosition.getZ());
		
		for (int x = 0; x < X_axis; x++) {
			for (int y = 0; y < Y_axis; y++) {
				for (int z = 0; z < Z_axis; z++) {
					arr.add((byte)Maze[x][y][z]);

				}
			}
		}
		
		byte[] bytes = new byte[arr.size()];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte)arr.get(i);
		}
		return bytes;
	}

	public void setStartPosition(Position startPosition) {
		this.startPosition = startPosition;
	}
	public void setGoalPosition(Position goalPosition) {
		this.goalPosition = goalPosition;
	}
	public void setFreeCell(Position pos) {
		this.Maze[pos.getX()][pos.getY()][pos.getZ()] = FREE;
	}
	
	public List<Position> getAllPossibleMoves(Position c) {
		List<Position> moves = new ArrayList<Position>();
		//moves.clear();
		Position temp= null;
		for (Position neighbor : getNeighbors(c)) {
			if(isPassable(c, neighbor)){
				if(c.getX() < neighbor.getX() &&  isPassable(c, neighbor)){
					temp = new Position(c.getX()+2, c.getY(), c.getZ());
					if (checkValidCell(temp)) 
						moves.add(temp);
				}
				if(c.getX() > neighbor.getX() &&  isPassable(c, neighbor)){
					temp = new Position(c.getX()-2, c.getY(), c.getZ());
					if (checkValidCell(temp)) 
						moves.add(temp);
				}
				if(c.getY() < neighbor.getY() &&  isPassable(c, neighbor)){
					temp = new Position(c.getX(), c.getY()+2, c.getZ());
					if (checkValidCell(temp)) 
						moves.add(temp);
				}
				if(c.getY() < neighbor.getY() &&  isPassable(c, neighbor)){
					temp = new Position(c.getX(), c.getY()-2, c.getZ());
					if (checkValidCell(temp)) 
						moves.add(temp);
				}
				if(c.getZ() < neighbor.getZ() &&  isPassable(c, neighbor)){
					temp = new Position(c.getX(), c.getY(), c.getZ()+2);
					if (checkValidCell(temp)) 
						moves.add(temp);
				}
				if(c.getZ() < neighbor.getZ() &&  isPassable(c, neighbor)){
					temp = new Position(c.getX(), c.getY(), c.getZ()-2);
					if (checkValidCell(temp)) 
						moves.add(temp);
				}
			}
		}
		
		return moves;
	}
	
	public void setWall(Position pos) {
		this.Maze[pos.getX()][pos.getY()][pos.getZ()] = WALL;
	}
	
	// Removes a wall between 2 cells, to forge a Maze.
	public void RemoveWallBetweenCells(Position c1, Position c2){
		if(checkValidCell(c1) && checkValidCell(c2) && isNeighbor(c1, c2)){
			Maze[(c1.getX()+c2.getX())/2][(c1.getY()+c2.getY())/2][(c1.getZ()+c2.getZ())/2] = FREE;
			setCell(c1, 0);
			setCell(c2, 0);
		}
	}
	public void generateEnterance(){
		Maze[1][0][0] = 0;
	}
	public void generatedExit(){
		Maze[X_axis-2][Y_axis-1][Z_axis-1] = FREE;
	}
	
	// getters & setters
	public int[][][] getMaze() {
		return Maze;
	}
	public int getX_axis() {
		return X_axis;
	}
	public int getY_axis() {
		return Y_axis;
	}
	public int getZ_axis() {
		return Z_axis;
	}	
	public void setCell(Position position, int value){
		if (checkValidCell(position))
			Maze[position.getX()][position.getY()][position.getZ()] = value;
	}
	public int getCell(Position position){
		if (checkValidCell(position))
			return Maze[position.getX()][position.getY()][position.getZ()];
		return -1;
	}
	
	// Checks if a cell is within the Maze limits
	public Boolean checkValidCell(Position position){
		if (position.getX() >= 0 && position.getX() < X_axis)
			if (position.getY() >= 0 && position.getY() < Y_axis)
				if (position.getZ() >= 0 && position.getZ() < Z_axis)
					return true;
		return false;
			
	}
	
	// Checks if two given cells are valid and neighbors.
	private Boolean isNeighbor(Position c1, Position c2){
		if (!(checkValidCell(c1) && checkValidCell(c2)))
			return false;
		if (Math.abs(c1.getX()-c2.getX())==2 && c1.getY()==c2.getY() && c1.getZ()==c2.getZ())
			return true;
		if (c1.getX()==c2.getX() && Math.abs(c1.getY()-c2.getY())==2 && c1.getZ()==c2.getZ())
			return true;
		if (c1.getX()==c2.getX() && c1.getY()==c2.getY() && Math.abs(c1.getZ()-c2.getZ())==2)
			return true;
		return false;
	}
	
	public Boolean isPassable(Position c1, Position c2){
		if (isNeighbor(c1, c2)){
			if(Maze[(c1.getX()+c2.getX())/2][(c1.getY()+c2.getY())/2][(c1.getZ()+c2.getZ())/2] == FREE){
				return true;
			}
		}
		return false;
	}
	
	// Get list of neighbors of a cell
	public List<Position> getNeighbors(Position c){
		List<Position> listOfCells = new ArrayList<Position>();
		
		if(c.getX()+2 < X_axis)
			listOfCells.add(new Position(c.getX()+2, c.getY(), c.getZ()));
		if(c.getX()-2 >= 0)
			listOfCells.add(new Position(c.getX()-2, c.getY(), c.getZ()));
		if(c.getY()+2 < Y_axis)
			listOfCells.add(new Position(c.getX(), c.getY()+2, c.getZ()));
		if(c.getY()-2 >= 0)
			listOfCells.add(new Position(c.getX(), c.getY()-2, c.getZ()));
		if(c.getZ()+2 < Z_axis)
			listOfCells.add(new Position(c.getX(), c.getY(), c.getZ()+2));
		if(c.getZ()-2 >= 0)
			listOfCells.add(new Position(c.getX(), c.getY(), c.getZ()-2));
			
		return listOfCells;
	}
	
	public List<Position> getUnvisitedNeighbors(Position c){
		List<Position> positions = getNeighbors(c);
		List<Position> unvisitedNeighbors = new ArrayList<Position>();
		for (Position position : positions) {
			if(this.getCell(position) == 1)
				unvisitedNeighbors.add(position);
		}
		return unvisitedNeighbors;
	}

	public Position getStartPosition() {
		/*for (int x = 1; x < X_axis-1; x=x+2) {
			for (int y = 1; y < Y_axis-1; y=y+2) {
				for (int z = 1; z < Z_axis-1; z=z+2) {
					if(Maze[x][y][z] == 0){
						if(x==1){
							Maze[0][y][z] = 0;
							this.startPosition = new Position(0,y,z);
						}
						if(x == X_axis-2){
							Maze[x+1][y][z] = 0;
							this.startPosition = new Position(x+1,y,z);
						}
						if(y==1){
							Maze[x][0][z] = 0;
							this.startPosition = new Position(x,0,z);
						}
						if(y == Y_axis-2){
							Maze[x][y+1][z] = 0;
							this.startPosition = new Position(z,y+1,z);
						}
						if(z==1){
							Maze[x][y][0] = 0;
							this.startPosition = new Position(x,y,0);
						}
						if(z == Z_axis-2){
							Maze[x][y][z+1] = 0;
							this.startPosition = new Position(x,y,z+1);
						}
					}
				}
			}
		}*/
		return startPosition;
	}
	
	public Position getGoalPosition(){
		/*for (int x = X_axis-2; x > 1; x=x-2) {
			for (int y = Y_axis-2; y > 1; y=y-2) {
				for (int z = Z_axis-2; z > 1 ; z=z-2) {
					if(Maze[x][y][z] == 0){
						if(x==1){
							Maze[0][y][z] = 0;
							this.goalPosition = new Position(0,y,z);
						}
						if(x == X_axis-2){
							Maze[x+1][y][z] = 0;
							this.goalPosition = new Position(x+1,y,z);
						}
						if(y==1){
							Maze[x][0][z] = 0;
							this.goalPosition = new Position(x,0,z);
						}
						if(y == Y_axis-2){
							Maze[x][y+1][z] = 0;
							this.goalPosition = new Position(x,y+1,z);
						}
						if(z==1){
							Maze[x][y][0] = 0;
							this.goalPosition = new Position(x,y,0);
						}
						if(z == Z_axis-2){
							Maze[x][y][z+1] = 0;
							this.goalPosition = new Position(x,y,z+1);
						}
					}
				}
			}
		}*/
		return this.goalPosition;
	}

	public String[] getPossibleMoves(Position c) {
		List<String> possibleWays = new ArrayList<String>();
		for (Position neighbor : getNeighbors(c)) {
			if(isPassable(c, neighbor)){
				if(c.getX() < neighbor.getX()){
					possibleWays.add("Right");
				}
				if(c.getX() > neighbor.getX()){
					possibleWays.add("Left");
				}
				if(c.getY() < neighbor.getY()){
					possibleWays.add("Upwards");
				}
				if(c.getY() < neighbor.getY()){
					possibleWays.add("Downwards");
				}
				if(c.getZ() < neighbor.getZ()){
					possibleWays.add("Forward");
				}
				if(c.getZ() < neighbor.getZ()){
					possibleWays.add("Backwards");
				}
			}
		}
		String[] possibleWaysArr = new String[possibleWays.size()];
		return possibleWays.toArray(possibleWaysArr);
	}

	public int[][] getCrossSectionByX(int i) {
		return Maze[i];
	}

	public int[][] getCrossSectionByY(int i) {
		int[][] crossSectionByY = new int[X_axis][Z_axis];
		for (int x = 0; x < X_axis; x++) {
			crossSectionByY[x] = Maze[x][i];
		}
		return crossSectionByY;
	}

	public int[][] getCrossSectionByZ(int i) {
		int[][] crossSectionByZ = new int[X_axis][Y_axis];
		for (int x = 0; x < X_axis; x++) {
			for (int y = 0; y < Y_axis; y++) {
				crossSectionByZ[x][y] = Maze[x][y][i];				
			}
		}
		return crossSectionByZ;
	}
	
	
	@Override
	public String toString() {		
		StringBuilder sb = new StringBuilder();
		System.out.println("Matrix :");
		for (int i = 0; i < Z_axis; i++) {
			int[][] maze2dz=this.getCrossSectionByZ(i);
			sb.append("Cross ["+ i + "] :\n");
			sb.append(printMaze2dBySection(maze2dz));
		}
		
		return sb.toString();
	}
	private static String printMaze2dBySection(int [][] matrix)
	{
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<matrix.length;i++)
		{
			for (int j=0;j<matrix[0].length;j++)
			{
				sb.append(matrix[i][j]+" ");
			}
			sb.append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}
	public int getValueAt(int x, int y, int z)
	{
		if (x >= 0 && x < X_axis)
			if (y >= 0 && y < Y_axis)
				if (z >= 0 && z< Z_axis){
			return this.Maze[x][y][z]; 
		}
		return -1;
	}
	

	
	
	
	
	

}