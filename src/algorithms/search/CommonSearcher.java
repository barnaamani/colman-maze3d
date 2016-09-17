package algorithms.search;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public abstract class CommonSearcher<T>  implements Searcher {
	
	protected int evaluatedNodes = 0;
	protected PriorityQueue<State<T>> openList = new PriorityQueue<State<T>>();
	
	/** The close set. */
	protected HashSet<State<T>> closeSet;


	@Override
	public int getNumberOfNodesEvaluated() {		
		return evaluatedNodes;
	}
	
	/**
	 * Back trace.
	 *
	 * Return the solution of the problem by save the goal state
	 * and run from the end of solution to the start using 'cameFrom' values.
	 *
	 * @param goalState the goal state
	 * @return the solution
	 */
	protected Solution<T> backTrace(State<T> goalState) {
		Solution<T> sol = new Solution<T>();
		
		State<T> currState = goalState;
		List<State<T>> states = sol.getStates();
		while (currState != null) {		
			states.add(0, currState);
			currState = currState.getCameFrom();
		}
		return sol;
	}
	
	// Removes an element from openList for evaluation
		protected State<T> popOpenList(){
			evaluatedNodes++;
			return openList.poll();
		}

}
