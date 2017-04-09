/**
 * 
 */
package metaheuristics.ga;

import problems.Evaluator;
import solutions.Solution;

/**
 * @author davirdgs
 *
 */
public class GeneticAlgorithm extends AbstractGA {

	/**
	 * @param objFunction
	 * @param generations
	 * @param popSize
	 * @param mutationRate
	 */
	public GeneticAlgorithm(Evaluator objFunction, Integer generations, Integer popSize, Double mutationRate) {
		super(objFunction, generations, popSize, mutationRate);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see metaheuristics.ga.AbstractGA#createEmptySol()
	 */
	@Override
	public Solution createEmptySol() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see metaheuristics.ga.AbstractGA#decode(metaheuristics.ga.AbstractGA.Chromosome)
	 */
	@Override
	protected Solution decode(Chromosome chromosome) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see metaheuristics.ga.AbstractGA#generateRandomChromosome()
	 */
	@Override
	protected Chromosome generateRandomChromosome() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see metaheuristics.ga.AbstractGA#fitness(metaheuristics.ga.AbstractGA.Chromosome)
	 */
	@Override
	protected Double fitness(Chromosome chromosome) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see metaheuristics.ga.AbstractGA#mutateGene(metaheuristics.ga.AbstractGA.Chromosome, java.lang.Integer)
	 */
	@Override
	protected void mutateGene(Chromosome chromosome, Integer locus) {
		// TODO Auto-generated method stub

	}

}
