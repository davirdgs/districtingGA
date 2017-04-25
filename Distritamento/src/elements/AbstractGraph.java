/**
 * 
 */
package elements;

import java.util.Random;
//import problems.Evaluator;

/**
 * Abstract Class for generic graphs
 * 
 * @author davirdgs
 * 
 */
public abstract class AbstractGraph {
	
	protected int[][] costMatrix;
	private final int nodesNumber;
	private final int districtNumber;
	
	public static final Random rnd = new Random();

	/**
	 * 
	 */
	public AbstractGraph(int nodes, int district) {
		this.districtNumber = district;
		this.nodesNumber = nodes;
		this.costMatrix = new int[nodes][nodes];
	}
	
	public int getNodesNumber() {
		return nodesNumber;
	}
	
	protected abstract void setCost(int i, int j, int cost);
	
	protected abstract void setRandomCosts();

	public int getDistrictNumber() {
		return districtNumber;
	}
	
	public void printCostMatrix() {
		for(int i = 0; i < costMatrix.length; i++) {
			for(int j = 0; j < costMatrix[i].length; j++) {
				System.out.print(costMatrix[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
	public int[][] getCostMatrix() {
		return this.costMatrix;
	}

}
