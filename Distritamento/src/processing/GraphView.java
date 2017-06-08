package processing;

import java.util.ArrayList;

import elements.CoordGraph;
import processing.core.PApplet;
import solutions.Solution;
import metaheuristics.ga.AbstractGA.Chromosome;

public class GraphView extends PApplet {
	
	public static CoordGraph cGraph = new CoordGraph();
	public static Solution solution;
	public static int districts;
	
	//x' = 1000*(x - min) / (max - min)
	//fac = 1000/(max - min)
	private static float facx;
	private static float facy;
	
	public void set(CoordGraph graph, Solution sol, int dist) {
		districts = dist;
		cGraph = graph;
		solution = sol;
		
		facx = (float) (650 / (graph.maxX - graph.minX));
		facy = (float) (650 / (graph.maxY - graph.minY));
		
		PApplet.main("processing.GraphView");
	}

	// Processing
    public void settings(){
    	size(660, 660);
    }

    public void setup(){
    	//fill(120,50,240);
    	noLoop();
    }

    public void draw(){
    	
    	String cost = "Cost: " + solution.cost.toString();
    	
    	fill(0,0,0);
    	textSize(16);
    	text(cost,20,30);
    	
    	ArrayList<ArrayList<Integer>> splitSol = solution.split(districts);
    	
    	//draw graph
    	for(int i = 0; i < cGraph.size(); i++) {
    		strokeWeight(7);
    		stroke(250, 0, 0);
    		point(facx*(float)(cGraph.get(i).x - cGraph.minX), facy*(float)(cGraph.get(i).y - cGraph.minY));
    	}
    	
    	stroke(0, 0, 250);
    	for(int i = 0; i < splitSol.size(); i++) {
    		if(splitSol.get(i).size() == 0) {
    			continue;
    		}
    		float x = facx*(float)(cGraph.get(splitSol.get(i).get(0)).x - cGraph.minX);
    		float y = facy*(float)(cGraph.get(splitSol.get(i).get(0)).y - cGraph.minY);
    		point(x,y);
    	}
    	//point(facx*(float)(cGraph.get(i).x - cGraph.minX), facy*(float)(cGraph.get(i).y - cGraph.minY));
    	strokeWeight(1);
    	stroke(0, 0, 0);
    	for(int i = 0; i < splitSol.size(); i++) {
    		for(int j = 0; j < splitSol.get(i).size() - 1; j++) {
    			float xi = facx*(float)(cGraph.get(splitSol.get(i).get(j)).x - cGraph.minX);
        		float yi = facy*(float)(cGraph.get(splitSol.get(i).get(j)).y - cGraph.minY);
        		float xo = facx*(float)(cGraph.get(splitSol.get(i).get(j+1)).x - cGraph.minX);
        		float yo = facy*(float)(cGraph.get(splitSol.get(i).get(j+1)).y - cGraph.minY);
        		line(xi, yi, xo, yo);
    		}
    		
    		if(splitSol.get(i).size() == 0) {
    			continue;
    		}
    		
    		float xi = facx*(float)(cGraph.get(splitSol.get(i).get(0)).x - cGraph.minX);
    		float yi = facy*(float)(cGraph.get(splitSol.get(i).get(0)).y - cGraph.minY);
    		float xo = facx*(float)(cGraph.get(splitSol.get(i).get(splitSol.get(i).size() - 1)).x - cGraph.minX);
    		float yo = facy*(float)(cGraph.get(splitSol.get(i).get(splitSol.get(i).size() - 1)).y - cGraph.minY);
    		line(xi, yi, xo, yo);
    	}
    	
    }
}
