package processing;

import elements.CoordGraph;
import processing.core.PApplet;
import elements.Coordinate;
import metaheuristics.ga.AbstractGA.Chromosome;

public class GraphView extends PApplet {
	
	public static CoordGraph cGraph = new CoordGraph();
	public static Chromosome chromosome;
	
	static public void set(CoordGraph graph, Chromosome chr) {
		cGraph = graph;
		chromosome = chr;
		PApplet.main("processing.GraphView");
	}

	// Processing
    public void settings(){
    	size(500, 500);
    }

    public void setup(){
    	//fill(120,50,240);

    	noLoop();
    }

    public void draw(){
    	
    	//draw axis
    	line(250, 0, 250, 500);
    	line(0, 250, 500, 250);
    	
    	//draw graph
    	for(int i = 0; i < cGraph.size(); i++) {
    		strokeWeight(7);
    		stroke(250, 0, 0);
    		point((float)(cGraph.get(i).x) + 250, (float) (cGraph.get(i).y + 250));
    		for(int j = i; j < cGraph.size(); j++) {
    			strokeWeight(1);
    			stroke(0, 0, 0);
    			//line((float)cGraph.get(i).x + 250, (float)cGraph.get(i).y + 250, (float)cGraph.get(j).x + 250, (float)cGraph.get(j).y + 250);
    		}
    	}
    	
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
    			point((float)(cGraph.get(i).x) + 250, (float) (cGraph.get(i).y + 250));
    			start = false;
    			//districtIndex = i;
    		}
    		
    		stroke(0, 0, 250);
    		strokeWeight(2);
    		int j = i+1;
    		if(j < cGraph.size()) {
    			line((float)cGraph.get(i).x + 250, (float)cGraph.get(i).y + 250, (float)cGraph.get(j).x + 250, (float)cGraph.get(j).y + 250);
    		} else {
    			//line((float)cGraph.get(i).x + 250, (float)cGraph.get(i).y + 250, (float)cGraph.get(districtIndex).x + 250, (float)cGraph.get(districtIndex).y + 250);
    		}
    		
    	}
    	
    }
}
