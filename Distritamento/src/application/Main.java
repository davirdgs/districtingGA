package application;

import java.io.IOException;
import java.util.ArrayList;

import elements.CoordGraph;
import metaheuristics.ga.AbstractGA;

//import java.io.File;

import metaheuristics.ga.AbstractGA.Chromosome;
import metaheuristics.ga.AbstractGA.Population;
import problems.districting.UndirectedGraph;
import problems.districting.solvers.Districting_GA;
import solutions.Solution;

public class Main{
	
	public static UndirectedGraph graphContext;
	public static CoordGraph cGraph;
	public static int districts;

	public static void main(String[] args) {
		
		districts = Integer.parseInt(args[1]);
		
		try {
			cGraph = TSPFileManipulator.createGraphFromFile(args[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		UndirectedGraph graph = new UndirectedGraph(cGraph, districts);
		
		graph.printCostMatrix();
		System.out.println("");
		graphContext = graph;
		
		//Districting_GA(Evaluator<Integer> objFunction, Integer generations, Integer popSize, Double mutationRate)
		Districting_GA districting = new Districting_GA(graph, 5, 10, 0.1);
		Chromosome chromosome = districting.getChromosome();
		districting.printChromosome(chromosome);
		Solution sol = districting.decodeTest(chromosome);
		sol.split(districts);
		sol.cost = graph.evaluate(sol);
		System.out.print("Custo da solução: ");
		System.out.println(sol.cost);
		
		Population pop = districting.initializePopulationTest();
		districting.printPopulation(pop);
		System.out.println("\n\n Melhor cromossomo: \n");
		Chromosome best = districting.getBestChromosomeTest(pop);
		districting.printChromosome(best);
		Solution bestS = districting.decodeTest(best);
		bestS.cost = graph.evaluate(bestS);
		System.out.print("Custo da solução: ");
		System.out.println(bestS.cost);
		
		System.out.println("\n\n Pior cromossomo: \n");
		Chromosome worse = districting.getWorseChromosomeTest(pop);
		districting.printChromosome(worse);
		Solution worseS = districting.decodeTest(worse);
		worseS.cost = graph.evaluate(worseS);
		System.out.print("Custo da solução: ");
		System.out.println(worseS.cost);
		
		System.out.println("\n\n CrossOver: \n");
		ArrayList<AbstractGA<Integer, Integer>.Chromosome> cross = districting.c1Operator(best, worse);
		districting.printChromosome(cross.get(0));
		districting.printChromosome(cross.get(1));
		
		System.out.println("\n\n Offsprings: \n");
		Population offs = districting.crossoverTest(pop);
		districting.printPopulation(offs);
		
		System.out.println("\n\n Mutants: \n");
		Population mut = districting.mutateTest(offs);
		districting.printPopulation(mut);
	}

}
