package elements;


/**
 * Class for undirected graph element.
 * 
 * @author davirdgs
 */
public class Graph extends AbstractGraph{

	public Graph(int nodes) {
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
				this.costMatrix[i][j] = AbstractGraph.rng.nextFloat()*10;
			}
		}
	}

}
