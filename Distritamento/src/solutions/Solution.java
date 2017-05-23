package solutions;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Solution<E> extends ArrayList<E> {
	
	public Double cost = Double.POSITIVE_INFINITY;
	
	public ArrayList<ArrayList<E>> splitedSolution = null;
	
	public Solution() {
		super();
	}
	
	public Solution(Solution<E> sol) {
		super(sol);
		cost = sol.cost;
	}
	
	public static Double fitness(Solution sol) {
		return 1/sol.cost;
	}
	
	public ArrayList<ArrayList<E>> split(int districts) {
		
		ArrayList<ArrayList<E>> split = new ArrayList<ArrayList<E>>(districts);
		
		int index = 0;
		
		ArrayList<E> list = new ArrayList<E>();
		split.add(list);
		
		for(int i = 0; i < this.size(); i++) {
			if((int)this.get(i) < (this.size() - districts)) {
				split.get(index).add(this.get(i));
			} else {
				index++;
				ArrayList<E> lt = new ArrayList<E>();
				split.add(lt);
			}
		}
		
		this.splitedSolution = split;
		return split;
	}


	@Override
	public String toString() {
		return "Solution: cost=[" + cost + "], size=[" + this.size() + "], elements=" + super.toString();
	}
	
	public void printSplited() {
		for(int i = 0; i < this.splitedSolution.size(); i++) {
			for(int j = 0; j < this.splitedSolution.get(i).size(); j++) {
				System.out.print(this.splitedSolution.get(i).get(j));
				System.out.print(" ");
			}
			System.out.println("");
		}
	}

}

