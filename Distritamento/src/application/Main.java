package application;

import java.io.File;
import java.io.IOException;

import elements.CoordGraph;
import elements.Coordinate;

//import java.io.File;

import metaheuristics.ga.AbstractGA.Chromosome;
import problems.districting.UndirectedGraph;
import problems.districting.solvers.Districting_GA;
import processing.GraphView;

public class Main{
	
	public static UndirectedGraph graphContext;
	public static CoordGraph cGraph;
	public static int districts;

	public static void main(String[] args) {
		
		districts = Integer.parseInt(args[1]);
		
		try {
			cGraph = TSPFileManipulator.createGraphFromFile(args[0]);
			//GraphView.set(cGraph, chromosome);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		UndirectedGraph graph = new UndirectedGraph(cGraph, districts);
		
		//graph.printCostMatrix();
		System.out.println("");
		graphContext = graph;
		
		Districting_GA districting = new Districting_GA(graph, 0, 2, 0.0);
		Chromosome chromosome = districting.getChromosome();
		districting.printChromosome(chromosome);
		
		GraphView.set(cGraph, chromosome, districts);

	}

}
