package algorithms.mazeGenerators;

public class Position{
	private int X;
	private int Y;
	private int Z;
	
	public Position(int x, int y, int z) {
		super();
		X = x;
		Y = y;
		Z = z;
	}
	public int getX() {
		return X;
	}
	public void setX(int x) {
		X = x;
	}
	public int getY() {
		return Y;
	}
	public void setY(int y) {
		Y = y;
	}
	public int getZ() {
		return Z;
	}
	public void setZ(int z) {
		Z = z;
	}
	public String toString() {
		return "{" + this.X  + "," +  this.Y + "," + this.Z + "}\n" ; 
	}
	
	@Override
	public boolean equals(Object obj) {
		Position p = (Position)obj;
		return p.X == this.X && p.Y == this.Y && p.Z == this.Z;
	}  
}
