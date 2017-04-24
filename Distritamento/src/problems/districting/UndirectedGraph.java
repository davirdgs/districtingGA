package problems.districting;

import elements.AbstractGraph;
import solutions.Solution;

public class UndirectedGraph extends AbstractGraph{

	public UndirectedGraph(int nodes) {
		super(nodes);
	}

	
	public void setCost(int i, int j, float cost) {
		///TODO: tratar caso i==j
		if(i==j) return;
		
		this.costMatrix[i][j] = cost;
		this.costMatrix[j][i] = cost;
	}
	
	public void setRandomCosts() {
		for(int i = 0; i < this.costMatrix.length; i++) {
			for(int j = 0; j < this.costMatrix[i].length; j++) {
				if(i==j) continue;
				this.costMatrix[i][j] = AbstractGraph.rnd.nextFloat()*10;
			}
		}
	}


	@Override
	public Integer getDomainSize() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Double evaluate(Solution<Integer> sol) {
		// TODO Auto-generated method stub
		return null;
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