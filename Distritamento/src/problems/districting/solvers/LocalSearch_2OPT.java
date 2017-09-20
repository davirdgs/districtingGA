/**
 * 
 */
package problems.districting.solvers;

import application.Main;
import problems.Evaluator;
import solutions.Solution;

/**
 * @author davirdgs
 *
 */
public class LocalSearch_2OPT {
	
	public static void optSwap(Solution<Integer> sol, int start, int end) {
		int i = start;
		int j = end;
		
		if(i < 0 || j >= sol.size()) {
			return;
		}
		
		while(i < j) {
			swapSolution(i, j, sol);
			i++;
			j--;
		}
	}
	
	public static void optAllSol(Solution<Integer> sol, int sectionSize, Evaluator<Integer> evaluator) {
		int start = 0;
		int end = sectionSize;
		sol.cost = evaluator.evaluate(sol);
		Double auxCost = sol.cost.doubleValue();
		
		while(sol.size() > end) {
			//sol.printSolution();
			optSwap(sol, start, end);
			sol.cost = evaluator.evaluate(sol);
			//sol.printSolution();
			if(sol.cost >= auxCost) {
				optSwap(sol, start, end);
				sol.cost = evaluator.evaluate(sol);
			} else {
				auxCost = sol.cost.doubleValue();
				sol.printSolution();
			}
			start++;
			end++;
		}
	}
	
	public static void optAllSizes(Solution<Integer> sol, Evaluator<Integer> evaluator) {
		for(int i = 1; i < sol.size(); i++) {
			optAllSol(sol, i, evaluator);
		}
	}
	
	public static void swapSolution(int i, int j, Solution<Integer> sol) {
		int aux = sol.get(i);
		sol.set(i, sol.get(j));
		sol.set(j, aux);
		sol.splitedSolution = null;
	}
	
}
