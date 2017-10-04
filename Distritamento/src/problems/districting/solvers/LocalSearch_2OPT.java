/**
 * 
 */
package problems.districting.solvers;

import application.Main;
import metaheuristics.ga.AbstractGA.Chromosome;
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
	
	public static void optSwap(Chromosome chr, int start, int end) {
		int i = start;
		int j = end;
		
		if(i < 0 || j >= chr.size()) {
			return;
		}
		
		while(i < j) {
			swapChromosome(i, j, chr);
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
	
	public static void optAllSol(Chromosome chr, int sectionSize, Evaluator<Integer> evaluator) {
		int start = 0;
		int end = sectionSize;
		Double cost = evaluator.evaluate(chr);
		Double auxCost = cost;
		
		while(chr.size() > end) {
			//sol.printSolution();
			optSwap(chr, start, end);
			cost = evaluator.evaluate(chr);
			//sol.printSolution();
			if(cost >= auxCost) {
				optSwap(chr, start, end);
				cost = evaluator.evaluate(chr);
			} else {
				auxCost = cost;
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
	
	public static void optAllSizes(Chromosome chr, Evaluator<Integer> evaluator) {
		for(int i = 1; i < chr.size(); i++) {
			optAllSol(chr, i, evaluator);
		}
	}
	
	public static void localSearch(Solution<Integer> sol, Evaluator<Integer> evaluator) {
		Double obj = Double.MAX_VALUE;
		int i = 0;
		while(i < 2) {
			System.out.println("Local search" + i);
			optAllSizes(sol, evaluator);
			if(sol.cost >= obj) {
				i++;
			} else {
				i = 0;
			}
			obj = sol.cost;
		}
	}
	
	public static void localSearch(Chromosome chr, Evaluator<Integer> evaluator) {
		Double obj = Double.MAX_VALUE;
		Double cost = evaluator.evaluate(chr);
		int i = 0;
		while(i <= 2) {
			System.out.println("Local search" + i);
			optAllSizes(chr, evaluator);
			cost = evaluator.evaluate(chr);
			if(cost >= obj) {
				i++;
			} else {
				i = 0;
			}
			obj = cost;
		}
	}
	
	public static void swapSolution(int i, int j, Solution<Integer> sol) {
		int aux = sol.get(i);
		sol.set(i, sol.get(j));
		sol.set(j, aux);
		sol.splitedSolution = null;
	}
	
	public static void swapChromosome(int i, int j, Chromosome chr) {
		Object aux = chr.get(i);
		chr.set(i, chr.get(j));
		chr.set(j, aux);
	}
	
}
