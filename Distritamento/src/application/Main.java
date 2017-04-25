package application;

import java.io.File;
import java.io.IOException;

import problems.districting.UndirectedGraph;

public class Main {
	
	public static UndirectedGraph graphContext;

	public static void main(String[] args) {
		
		File[] filelist = TSPFileManipulator.finder("./Instancias");
		
		UndirectedGraph graph = new UndirectedGraph(4, 2);
		graph.setRandomCosts();
		
		
		
	}
	
	

}
