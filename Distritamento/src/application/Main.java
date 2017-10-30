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
import processing.GraphView;
import solutions.Solution;
import problems.districting.solvers.LocalSearch_2OPT;

public class Main{
	
	//public static UndirectedGraph graphContext;
	public static CoordGraph cGraph;
	public static int districts;

	public static void main(String[] args) {
		
		districts = Integer.parseInt(args[1]);
		
		try {
			cGraph = TSPFileManipulator.createGraphFromFile(args[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		TSPFileManipulator.LKH();
		
		UndirectedGraph graph = new UndirectedGraph(cGraph, districts);
		//graphContext = graph;
		
		//Districting_GA(Evaluator<Integer> objFunction, Integer generations, Integer popSize, Double mutationRate)
//		int popSize = graph.getDistrictNumber() + graph.getNodesNumber();
//		Districting_GA districting = new Districting_GA(graph, 1000, 90, (double) (1/(graph.getDistrictNumber()+graph.getNodesNumber())));
//		
//		districting.solve();
//		System.out.print("Terminando... ");
//		
//		System.out.println(districting.history.get(districting.history.size()-1).cost);
//		GraphView windown = new GraphView();
//		windown.set(cGraph, districting.getBestSolution(), districts);
//		
//		LocalSearch_2OPT.localSearch(districting.getBestSolution(), graph);
//		windown.set(cGraph, districting.getBestSolution(), districts);
		
	}
	

}
