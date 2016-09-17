package algorithms.search;
import java.util.ArrayList;
import java.util.List;

public class Solution<T> {
	private List<State<T>> states = new ArrayList<State<T>>();
	
	/**
	 * This method return ArrayList of states 
	 * @return states arrayList represents the solution
	 */
	public List<State<T>> getStates() {
		return states;
	}

	/**
	 * This method set ArrayList of states
	 * @param states - arrayList represents the solution to set
	 */
	public void setStates(List<State<T>> states) {
		this.states = states;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (State<T> s : states) {
			sb.append(s.toString()).append(" ");
		}
		return sb.toString();
	}
	
	/**
	 * @param state - state whose presence in this solution is to be tested
	 * @return - true if this Solution contains the specified state
	 */
	public boolean contains(State<T> state)
	{
		if (states.contains(state))
			return true;
		return false;
	}
	
	/**
	 * Removes the first occurrence of the specified element from this list, if it is present.
	 * If the Solution does not contain the state, it is unchanged. More formally, removes the state with the lowest index in the solution states list.
	 * @param state - state to remove from the solution states list
	 * @return - Returns true if this solution contained the specified state 
	 */
	public boolean remove(State<T> state)
	{
		return states.remove(state);
	}

}
