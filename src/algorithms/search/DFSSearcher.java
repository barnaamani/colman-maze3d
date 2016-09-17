package algorithms.search;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * This class is concrete class of Searcher interface
 * This class represents DFS Searcher that search Solution in a searchable problem according to DFS Algorithm
 * @param <T>
 */
public class DFSSearcher<T> extends CommonSearcher<T> {

	
	/**
	 * This method finds a solution to a Searchable problem
	 * @param s the searchable problem
	 * @return solution
	 */
	@Override
	public Solution<T> search(Searchable s) {
		
		ArrayList<State<T>> discovered = new ArrayList<State<T>>();
		List<State<T>> neighbors  = null;
		State<T> state = null;
		
		openList.add(s.getStartState());
		while(!openList.isEmpty()) {
			//state = popOpenList();
			state = openList.poll();
			this.evaluatedNodes++;
			
			if(!state.equals(s.getGoalState())) {
				discovered.add(state);
				neighbors = s.getAllPossibleStates(state);
				for(State<T> st: neighbors) {
					st.setCameFrom(state);
					st.setCost(state.getCost() + s.getMoveCost(state, st));

					if(st.equals(s.getGoalState())) {
						return backTrace(st);
					}
					else if(!openList.contains(st)){
						openList.add(st);
						discovered.add(st);
					}
				}
				
			}
			else {
				return backTrace(state);
			}
		}
		return null;
	}

}


