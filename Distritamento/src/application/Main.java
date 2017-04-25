package application;

//import java.io.File;

import metaheuristics.ga.AbstractGA.Chromosome;
import problems.districting.UndirectedGraph;
import problems.districting.solvers.Districting_GA;

public class Main {
	
	public static UndirectedGraph graphContext;

	public static void main(String[] args) {
		
		//File[] filelist = TSPFileManipulator.finder("./Instancias");
		
		UndirectedGraph graph = new UndirectedGraph(4, 2);
		graph.setRandomCosts();
		
		graph.printCostMatrix();
		System.out.println("");
		graphContext = graph;
		
		Districting_GA districting = new Districting_GA(graph, 0, 2, 0.0);
		Chromosome chromosome = districting.getChromosome();
		districting.printChromosome(chromosome);
	}

}
