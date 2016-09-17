package algorithms.search;
import java.io.Serializable;

import algorithms.mazeGenerators.Position;

/**
 * This class represents State in Searchable problem
 */

public class State<T> implements Comparable<State<T>> {
		private State<T> cameFrom;
		private double cost;
		private T value;
		private String key;
		private boolean visited;
		
		public State(T value) {
			this.value =  value;
		}
		
		/**
		 * Instantiates a new state.
		 */
		public State() {
			this.value = null;
			this.cost = 0;
			this.cameFrom = null;
		}
		
		/**
		 * Instantiates a new state.
		 *
		 * @param s the s
		 */
		public State(State<T> s) {
			this.value = s.value;
			this.cost = s.cost;
			this.cameFrom = s.cameFrom;
		}
		
		/**
		 * This method get the came from state
		 * @return the cameFromState state
		 */
		public State<T> getCameFrom() {
			return cameFrom;
		}
		
		/**
		 * This method set the came from state
		 * @param cameFromState - came from state to set
		 */
		public void setCameFrom(State<T> cameFrom) {
			this.cameFrom = cameFrom;
		}

		/**
		 * Gets the state.
		 *
		 * @return the state
		 */
		public T getState() {
			return this.value;
		}
		
		/**
		 * This method get the state cost
		 * @return State cost
		 */
		public double getCost() {
			return cost;
		}
		
		/**
		 * This method set the state cost
		 * @param cost - cost to set in state
		 */
		public void setCost(double cost) {
			this.cost = cost;
		}
		
		
		public T getValue() {
			return value;
		}
		public void setValue(T value) {
			this.value = value;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		
	
		
		@Override
		public String toString() {
			String str = "State Value: " + value.toString().replaceAll("(\\r|\\n)", ""); ;
			if (this.cost!=0.0)
				str+= ",State Cost: " + this.cost ;
			str +=  "\n";
			return str;
		}
		
		/**
		 * This method check if state isVisited
		 * @return true or false
		 */
		public boolean isVisited() 
		{
			return this.visited;
		}
		
		/**
		 * This method set true or false to visited flag of state
		 * @param visited flag
		 */
		public void setVisited(boolean visited) 
		{
			this.visited = visited;
		}

		
		@Override
		public boolean equals(Object obj) {
			State<T> s = (State<T>)obj;
			return s.value.equals(this.value);
		}
		
		@Override
		public int compareTo(State<T> s) {
			return (int)(this.getCost() - s.getCost());	
			// return > 0 if this > s
			//        < 0 if this < s
			//        = 0 if this == s
		}

		public void setState (T startPosition) {
			this.value = startPosition;
			
		}
		
	}

