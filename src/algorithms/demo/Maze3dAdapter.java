package algorithms.demo;
import java.util.HashMap;
import java.util.List;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Searchable;
import algorithms.search.State;

import java.util.ArrayList;



/**
 * This class adapting between Searchable Object to maze3d object
 * based on Object Adapter Design Pattern
 * @param <T>
 */
public class Maze3dAdapter implements Searchable<Position> {
	
	private Maze3d maze3d ;

	private static final double MOVEMENT_COST = 1.0;
	
	/**
	 * Maze3dAdapter Constructor
	 * @param maze3d - Maze3d the adapter wrapped
	 */
	public Maze3dAdapter(Maze3d maze3d) 
	{
		this.maze3d=maze3d;
	}
	
	/**
	 * get the wrapped maze3d object
	 * @return - the wrapped maze3d object
	 */
	public Maze3d getMaze3d() {
		return this.maze3d;
	}
	
	/**
	 * set and wrap maze3d object in the adapter
	 * @param maze3d - the maze3d object to wrap
	 */
	public void setMaze3d(Maze3d maze3d) {
		this.maze3d = maze3d;
	}
	
	/**
	 * Get the Start state of any searchable problem
	 * @return - state represent the start state of the problem
	 */
	@Override
	public State<Position> getStartState() 
	{
		Position startPos = this.maze3d.getStartPosition();
		State<Position> startState = new State<Position>(startPos);
		return startState;
	}
	
	/**
	 * Get the Goal state of any searchable problem
	 * @return - state represent the start state of the problem
	 */
	@Override
	public State<Position> getGoalState() {
		Position goalPos = this.maze3d.getGoalPosition();
		State<Position> goalState = new State<Position>(goalPos);
		return goalState;	
	}

	@Override
	public String toString() {
		return "Maze3dAdapter:" + '\n' +'\t'+ "maze3d:"  + '\n' + maze3d.toString() + "]";
	}
	
	@Override
	public List<State<Position>> getAllPossibleStates(State<Position> s) {
		Position currPos = s.getValue();
		
		List<Position> moves = maze3d.getAllPossibleMoves(currPos);
		List<State<Position>> states = new ArrayList<State<Position>>();
		
		for (Position pos: moves) {
			states.add(new State<Position>(pos));
		}
		return states;		
	}
	
	@Override
	public double getMoveCost(State<Position> currState, State<Position> neighbor) {		
		return 1; // in the maze all moves have the same cost
	}

}
