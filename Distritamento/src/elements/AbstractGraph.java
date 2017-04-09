/**
 * 
 */
package elements;

import java.util.Random;

/**
 * Abstract Class for generic graphs
 * 
 * @author davirdgs
 * 
 */
public abstract class AbstractGraph {
	
	protected float[][] costMatrix;
	private final int nodesNumber;
	
	public static final Random rnd = new Random(0);

	/**
	 * 
	 */
	public AbstractGraph(int nodes) {
		this.nodesNumber = nodes;
		this.costMatrix = new float[nodes][nodes];
	}
	
	public int getNodesNumber() {
		return nodesNumber;
	}
	
	protected abstract void setCost(int i, int j, float cost);
	
	protected abstract void setRandomCosts();

}
