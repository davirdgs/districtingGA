package application;

import elements.CoordGraph;
import elements.Coordinate;

//import java.io.File;

import metaheuristics.ga.AbstractGA.Chromosome;
import problems.districting.UndirectedGraph;
import problems.districting.solvers.Districting_GA;
import processing.GraphView;

public class Main{
	
	public static UndirectedGraph graphContext;

	public static void main(String[] args) {
		
		CoordGraph cGraph = new CoordGraph();
		
		cGraph.add(Coordinate.newCoordinate(36.49,7.49));
		cGraph.add(Coordinate.newCoordinate(57.06, 9.51));
		cGraph.add(Coordinate.newCoordinate(30.22,48.14));
		cGraph.add(Coordinate.newCoordinate(5.15,-3.56));
		cGraph.add(Coordinate.newCoordinate(34.59,-106.37));
		cGraph.add(Coordinate.newCoordinate(57.12,-2.12));
		cGraph.add(Coordinate.newCoordinate(16.45,-99.45));
		cGraph.add(Coordinate.newCoordinate(5.36, -0.10));
		cGraph.add(Coordinate.newCoordinate(28.56,-13.36));
		cGraph.add(Coordinate.newCoordinate(8.59,38.48));
		cGraph.add(Coordinate.newCoordinate(12.50,45.02));
		cGraph.add(Coordinate.newCoordinate(-34.48,138.38));
		
		UndirectedGraph graph = new UndirectedGraph(12, 3);
		graph.setRandomCosts();
		
		graph.printCostMatrix();
		System.out.println("");
		graphContext = graph;
		
		Districting_GA districting = new Districting_GA(graph, 0, 2, 0.0);
		Chromosome chromosome = districting.getChromosome();
		Chromosome chromosome2 = districting.getChromosome();
		districting.printChromosome(chromosome);
		districting.printChromosome(chromosome2);
		districting.printChromosome(districting.c1Operator(chromosome, chromosome2));
		
		GraphView.set(cGraph, chromosome);
		
		
		//File[] filelist = TSPFileManipulator.finder("./Instancias");


	}

}
