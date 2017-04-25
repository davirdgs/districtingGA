package problems.qbf.solvers;

import java.io.IOException;

import metaheuristics.ga.AbstractGA;
import problems.qbf.QBF;
import solutions.Solution;



/**
 * Metaheuristic GRASP (Greedy Randomized Adaptive Search Procedure) for
 * obtaining an optimal solution to a QBF (Quadractive Binary Function --
 * {@link #QuadracticBinaryFunction}). Since by default this GRASP considers
 * minimization problems, an inverse QBF function is adopted.
 * 
 * @author ccavellucci, fusberti
 */
public class GA_QBF extends AbstractGA<Integer,Integer> {

	/**
	 * Constructor for the GRASP_QBF class. An inverse QBF objective function is
	 * passed as argument for the superclass constructor.
	 * 
	 * @param alpha
	 *            The GRASP greediness-randomness parameter (within the range
	 *            [0,1])
	 * @param iterations
	 *            The number of iterations which the GRASP will be executed.
	 * @param filename
	 *            Name of the file for which the objective function parameters
	 *            should be read.
	 * @throws IOException
	 *             necessary for I/O operations.
	 */
	public GA_QBF(Integer generations, Integer popSize, Double mutationRate, String filename) throws IOException {
		super(new QBF(filename), generations, popSize, mutationRate);
	}
	

	/**
	 * {@inheritDoc}
	 * 
	 * The local search operator developed for the QBF objective function is
	 * composed by the neighborhood moves Insertion, Removal and 2-Exchange.
	 */
//	@Override
//	public Solution<Element_QBF> localSearch() {
//
//		Double minDeltaCost;
//		Element_QBF bestCandIn = null, bestCandOut = null;
//
//		do {
//			minDeltaCost = Double.POSITIVE_INFINITY;
//			updateCL();
//				
//			// Evaluate insertions
//			for (Element_QBF candIn : CL) {
//				double deltaCost = candIn.evaluateInsertionCost(incumbentSol, ObjFunction);
//				if (deltaCost < minDeltaCost) {
//					minDeltaCost = deltaCost;
//					bestCandIn = candIn;
//					bestCandOut = null;
//				}
//			}
//			// Evaluate removals
//			for (Element_QBF candOut : incumbentSol.elements) {
//				double deltaCost = candOut.evaluateRemovalCost(incumbentSol, ObjFunction);
//				if (deltaCost < minDeltaCost) {
//					minDeltaCost = deltaCost;
//					bestCandIn = null;
//					bestCandOut = candOut;
//				}
//			}
//			// Evaluate exchanges
//			for (Element_QBF candIn : CL) {
//				for (Element_QBF candOut : incumbentSol.elements) {
//					double deltaCost = candOut.evaluateExchangeCost(candIn, incumbentSol, ObjFunction);
//					if (deltaCost < minDeltaCost) {
//						minDeltaCost = deltaCost;
//						bestCandIn = candIn;
//						bestCandOut = candOut;
//					}
//				}
//			}
//			// Implement the best move, if it reduces the solution cost.
//			if (minDeltaCost < -Double.MIN_VALUE) {
//				if (bestCandOut != null) {
//					incumbentSol.elements.remove(bestCandOut);
//					CL.add(bestCandOut);
//				}
//				if (bestCandIn != null) {
//					incumbentSol.elements.add(bestCandIn);
//					CL.remove(bestCandIn);
//				}
//				evaluateSolution(incumbentSol);
//			}
//		} while (minDeltaCost < -Double.MIN_VALUE);
//
//		return null;
//	}

	/**
	 * {@inheritDoc}
	 * 
	 * This createEmptySol instantiates an empty solution and it attributes a
	 * zero cost, since it is known that a QBF solution with all variables set
	 * to zero has also zero cost.
	 */
	@Override
	public Solution<Integer> createEmptySol() {
		Solution<Integer> sol = new Solution<Integer>();
		sol.cost = 0.0;
		return sol;
	}

	@Override
	protected Solution<Integer> decode(Chromosome chromosome) {
		
		Solution<Integer> solution = createEmptySol();
		for (int locus=0;locus<chromosome.size();locus++) {
			if (chromosome.get(locus) == 1) {
				solution.add(new Integer(locus));
			}
		}
		
		ObjFunction.evaluate(solution);
		return solution;
	}

	@Override
	protected Chromosome generateRandomChromosome() {
		
		Chromosome chromosome = new Chromosome();
		for (int i=0;i<chromosomeSize;i++) {
			chromosome.add(rng.nextInt(2));
		}
		
		return chromosome;
	}

	@Override
	protected Double fitness(Chromosome chromosome) {
		
		return decode(chromosome).cost;
		
	}

	@Override
	protected void mutateGene(Chromosome chromosome, Integer locus) {
		
		chromosome.set(locus, 1-chromosome.get(locus));
		
	}
	
	/**
	 * A main method used for testing the GRASP metaheuristic.
	 * 
	 */
	public static void main(String[] args) throws IOException {

		long startTime = System.currentTimeMillis();
		GA_QBF ga = new GA_QBF(1000,100,1.0/100.0, "instances/qbf100");
		Solution<Integer> bestSol = ga.solve();
		System.out.println("maxVal = " + bestSol);
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Time = "+(double)totalTime/(double)1000+" seg");

	}


	@Override
	protected AbstractGA<Integer, Integer>.Population crossover(AbstractGA<Integer, Integer>.Population parents) {
	
	Population offsprings = new Population();
	
	for (int i=0;i<popSize;i=i+2) {
		
		Chromosome parent1 = parents.get(i);
		Chromosome parent2 = parents.get(i+1);
		
		int crosspoint1 = rng.nextInt(chromosomeSize+1);
		int crosspoint2 = crosspoint1+rng.nextInt((chromosomeSize+1)-crosspoint1);
		
		Chromosome offspring1 = new Chromosome();
		Chromosome offspring2 = new Chromosome();
		
		for (int j=0;j<chromosomeSize;j++) {
			if (j >= crosspoint1 && j < crosspoint2) {
				offspring1.add(parent2.get(j));
				offspring2.add(parent1.get(j));	
			} else {
				offspring1.add(parent1.get(j));
				offspring2.add(parent2.get(j));		
			}
		}
		
		offsprings.add(offspring1);
		offsprings.add(offspring2);
		
	}
	
	return offsprings;
	
}

}
