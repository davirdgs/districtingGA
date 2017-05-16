package problems.districting;

import elements.AbstractGraph;
import elements.CoordGraph;
import elements.Coordinate;
import problems.Evaluator;
import solutions.Solution;
import java.lang.Math;

public class UndirectedGraph extends AbstractGraph implements Evaluator<Integer>{

	public UndirectedGraph(int nodes, int districts) {
		super(nodes, districts);
	}
	
	public UndirectedGraph(CoordGraph cGraph, int districts) {
		super(cGraph.size(), districts);
		
		for(int i = 0; i < this.costMatrix.length; i++) {
			for(int j = 0; j < this.costMatrix[i].length; j++) {
				if(i==j) continue;
				this.costMatrix[i][j] = euclidDistance(cGraph.get(i), cGraph.get(j));
				this.costMatrix[j][i] = this.costMatrix[i][j];
			}
		}
	}

	public static int euclidDistance(Coordinate i, Coordinate j) {
		Double doubleValue = Math.sqrt((i.x - j.x)*(i.x - j.x) + (i.y - j.y)*(i.y - j.y));
		return doubleValue.intValue();
	}
	
	public void setCost(int i, int j, int cost) {
		///TODO: tratar caso i==j
		if(i==j) return;
		
		this.costMatrix[i][j] = cost;
		this.costMatrix[j][i] = cost;
	}
	
	public void setRandomCosts() {
		
		for(int i = 0; i < this.costMatrix.length; i++) {
			for(int j = 0; j < this.costMatrix[i].length; j++) {
				if(i==j) continue;
				this.costMatrix[i][j] = AbstractGraph.rnd.nextInt(100);
				if(i > j) this.costMatrix[i][j] = this.costMatrix[j][i];
			}
		}
	}


	@Override
	public Integer getDomainSize() {
		return (this.getNodesNumber() + this.getDistrictNumber());
	}


	@Override
	public Double evaluate(Solution<Integer> sol) {
		
		double cost = 0;
		int deposit = 0;
		int position = 0;
		
		for(int i = 0; i < sol.size(); i++) {
			if((int)sol.get(i) == 0) {
				if(deposit == 0 || position == 0) continue;
				cost = cost + costMatrix[deposit][position];
				deposit = 0;
				position = 0;
				continue;
			}
			
		}
		
		
		return (double) cost;
		
	}


	@Override
	public Double evaluateInsertionCost(Integer elem, Solution<Integer> sol) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Double evaluateRemovalCost(Integer elem, Solution<Integer> sol) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Double evaluateExchangeCost(Integer elemIn, Integer elemOut, Solution<Integer> sol) {
		// TODO Auto-generated method stub
		return null;
	}
}