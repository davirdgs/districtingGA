package processing;

import java.util.ArrayList;

import elements.CoordGraph;
import processing.core.PApplet;
import metaheuristics.ga.AbstractGA.Chromosome;

public class GraphView extends PApplet {
	
	public static CoordGraph cGraph = new CoordGraph();
	public static Chromosome chromosome;
	public static int districts;
	
	public void set(CoordGraph graph, Chromosome chr, int dist) {
		districts = dist;
		cGraph = graph;
		chromosome = chr;
		PApplet.main("processing.GraphView");
	}

	// Processing
    public void settings(){
    	size((int)cGraph.maxX, (int)cGraph.maxY);
    }

    public void setup(){
    	//fill(120,50,240);
    	noLoop();
    }

    public void draw(){
    	
    	//draw axis
    	//line(250, 0, 250, 500);
    	//line(0, 250, 500, 250);
    	
    	ArrayList<Integer> starts = new ArrayList<Integer>();
    	
    	//draw graph
    	for(int i = 0; i < cGraph.size(); i++) {
    		strokeWeight(7);
    		stroke(250, 0, 0);
    		//point((float)(cGraph.get(i).x) + 250, (float) (cGraph.get(i).y + 250));
    		point((float)(cGraph.get(i).x), (float) (cGraph.get(i).y));
    	}
    	
    	
    	//get and draw deposits
    	if((int)chromosome.get(0) < cGraph.size()) {
    		starts.add((int)chromosome.get(0));
    	}
    	
    	for(int i = 0; i < chromosome.size(); i++) {
    		if((int)chromosome.get(i) >= cGraph.size()) {
    			int j = i+1;
    			starts.add((int)chromosome.get(j));
    		}
    	}
    	
    	/*
    	//draw path
    	boolean start = true;
    	int districts = 0;
    	int districtIndex = 0;
    	for(int i = 0; i < (chromosome.size() - districts); i++) {
    		if((int)chromosome.get(i) >= cGraph.size()) {
    			start = true;
    			districts++;
    			continue;
    		}
    		if(start) {
    			stroke(0, 250, 0);
    			strokeWeight(7);
    			point((float)(cGraph.get(i).x), (float) (cGraph.get(i).y));
    			//point((float)(cGraph.get(i).x) + 250, (float) (cGraph.get(i).y + 250));
    			start = false;
    			districtIndex = i;
    		}
    		
    		stroke(0, 0, 250);
    		strokeWeight(2);
    		
    		int j = i + 1;
    		//Fecha o laço
    		if((int)chromosome.get(j) >= cGraph.size() - districts) {
    			line((float)cGraph.get(i).x, (float)cGraph.get(i).y, (float)cGraph.get(districtIndex).x, (float)cGraph.get(districtIndex).y);
    			continue;
    		}
    		
    		
    		
    		int j = i+1;
    		if(j < cGraph.size()) {
    			line((float)cGraph.get(i).x, (float)cGraph.get(i).y, (float)cGraph.get(j).x, (float)cGraph.get(j).y);
    			//line((float)cGraph.get(i).x + 250, (float)cGraph.get(i).y + 250, (float)cGraph.get(j).x + 250, (float)cGraph.get(j).y + 250);
    		} else {
    			//line((float)cGraph.get(i).x + 250, (float)cGraph.get(i).y + 250, (float)cGraph.get(districtIndex).x + 250, (float)cGraph.get(districtIndex).y + 250);
    		}
    		
    	}*/
    	
    }
}
