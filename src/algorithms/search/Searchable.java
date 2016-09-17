package algorithms.search;
import java.util.List;

import algorithms.mazeGenerators.Position;

/**
* This class represents a searchable object.
* An object can implements this interface in order to be serachable
*/
public interface Searchable <T>
{
	/**
	 * Get the Start state of any searchable problem
	 * @return - state represent the start state of the problem
	 */
	<T> State<T> getStartState();
	
	/**
	 * Get the Goal state of any searchable problem
	 * @return - state represent the start state of the problem
	 */
	<T> State<T> getGoalState();
	
	/**
	 * Get all possible action can be reach from known state of any searchable problem
	 * @param state -  represent known state in searchable problem
	 * @return - Hash map that maps between any possible action to its desirable state 
	 */
	List<State<T>> getAllPossibleStates(State<T> s);

	
	double getMoveCost(State<T> currState, State<T> neighbor);

}

