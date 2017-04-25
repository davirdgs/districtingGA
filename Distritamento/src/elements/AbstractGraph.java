/**
 * 
 */
package elements;

import java.util.Random;
import problems.Evaluator;

/**
 * Abstract Class for generic graphs
 * 
 * @author davirdgs
 * 
 */
public abstract class AbstractGraph implements Evaluator<Integer>{
	
	protected float[][] costMatrix;
	private final int nodesNumber;
	private final int districtNumber;
	
	public static final Random rnd = new Random(0);

	/**
	 * 
	 */
	public AbstractGraph(int nodes, int district) {
		this.districtNumber = district;
		this.nodesNumber = nodes;
		this.costMatrix = new float[nodes][nodes];
	}
	
	public int getNodesNumber() {
		return nodesNumber;
	}
	
	protected abstract void setCost(int i, int j, float cost);
	
	protected abstract void setRandomCosts();

	public int getDistrictNumber() {
		return districtNumber;
	}

}
