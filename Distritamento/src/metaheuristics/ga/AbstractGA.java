/**
 * 
 */
package metaheuristics.ga;

import java.util.ArrayList;
import java.util.Random;

import problems.Evaluator;
import solutions.Solution;

/**
 * Abstract class for metaheuristic GRASP (Greedy Randomized Adaptive Search
 * Procedure). It consider a minimization problem.
 * 
 * @author ccavellucci, fusberti
 * @param <F>
 *            Generic type of the candidate to enter the solution.
 */
public abstract class AbstractGA<G extends Number, F> {

	@SuppressWarnings("serial")
	public class Chromosome extends ArrayList<G> {	}
	
	@SuppressWarnings("serial")
	public class Population extends ArrayList<Chromosome> {}
	
	/**
	 * flag that indicates whether the code should print more information on
	 * screen
	 */
	public static boolean verbose = true;

	/**
	 * a random number generator
	 */
	public static final Random rng = new Random(0);

	/**
	 * the objective function being optimized
	 */
	protected Evaluator<F> ObjFunction;

	protected int generations;
	protected int popSize;
	protected int chromosomeSize;
	protected double mutationRate;

	/**
	 * the best solution cost
	 */
	protected Double bestCost;

	/**
	 * the best solution
	 */
	protected Solution<F> bestSol;
	
	protected Chromosome bestChromosome;

	/**
	 * Creates a new solution which is empty, i.e., does not contain any
	 * candidate solution element.
	 * 
	 * @return An empty solution.
	 */
	public abstract Solution<F> createEmptySol();
	
	protected abstract Solution<F> decode(Chromosome chromosome);
	
	protected abstract Chromosome generateRandomChromosome();

	protected abstract Double fitness(Chromosome chromosome);
	
	protected abstract void mutateGene(Chromosome chromosome, Integer locus);

	/**
	 * The GRASP local search phase is responsible for repeatedly applying a
	 * neighborhood operation while the solution is getting improved, i.e.,
	 * until a local optimum is attained.
	 * 
	 * @return An local optimum solution.
	 */

	public AbstractGA(Evaluator<F> objFunction, Integer generations, Integer popSize, Double mutationRate) {
		this.ObjFunction = objFunction;
		this.generations = generations;
		this.popSize = popSize;
		this.chromosomeSize = this.ObjFunction.getDomainSize();
		this.mutationRate = mutationRate;
	}

	
	/**
	 * The GRASP mainframe. It consists of a loop, in which each iteration goes
	 * through the constructive heuristic and local search. The best solution is
	 * returned as result.
	 * 
	 * @return The best feasible solution obtained throughout all iterations.
	 */
	public Solution<F> solve() {
		
		Population population = initializePopulation();
		
		bestChromosome = getBestChromosome(population);
		bestSol = decode(bestChromosome);
		System.out.println("(Gen. " + 0 + ") BestSol = " + bestSol);
		
		for (int g=1;g<=generations;g++) {
			
			Population parents = selectParents(population);
			
			Population offsprings = crossover(parents);
			
			Population mutants = mutate(offsprings);
			
			Population newpopulation = selectPopulation(mutants);

			population = newpopulation;
			
			bestChromosome = getBestChromosome(population);
			
			if (fitness(bestChromosome) > bestSol.cost) {
				bestSol = decode(bestChromosome);
				if (verbose)
					System.out.println("(Gen. " + g + ") BestSol = " + bestSol);
			}
					
		}
		
		return bestSol;
	}
	

	protected Population initializePopulation() {
		
		Population population = new Population();
		
		while (population.size() < popSize) {
			population.add(generateRandomChromosome());
		}
		
		return population;
		
	}
	


	protected Chromosome getBestChromosome(Population population) {

		double bestFitness = Double.NEGATIVE_INFINITY;
		Chromosome bestChromosome = null;
		for (Chromosome c : population) {
			double fitness = fitness(c);
			if (fitness > bestFitness) { 
				bestFitness = fitness;
				bestChromosome = c;
			}
		}
		
		return bestChromosome;
	}
	
	protected Chromosome getWorseChromosome(Population population) {

		double worseFitness = Double.POSITIVE_INFINITY;
		Chromosome worseChromosome = null;
		for (Chromosome c : population) {
			double fitness = fitness(c);
			if (fitness < worseFitness) { 
				worseFitness = fitness;
				worseChromosome = c;
			}
		}
		
		return worseChromosome;
	}

	protected Population selectParents(Population population) {
		
		Population parents = new Population();
		
		while (parents.size() < popSize) {
			int index1 = rng.nextInt(popSize);
			Chromosome parent1 = population.get(index1);
			int index2 = rng.nextInt(popSize);
			Chromosome parent2 = population.get(index2);
			if (fitness(parent1) > fitness(parent2)) {
				parents.add(parent1);
			} else {
				parents.add(parent2);
			}
		}
		
		return parents;
		
	}

	protected Population mutate(Population offsprings) {
		
		for (Chromosome c : offsprings) {
			for (int locus=0;locus<chromosomeSize;locus++) {
				if (rng.nextDouble() < mutationRate) {
					mutateGene(c,locus);
				}
			}
		}		
		
		return offsprings;
	}

	protected Population crossover(Population parents) {
		
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

	protected Population selectPopulation(Population offsprings) {
		
		Chromosome worse = getWorseChromosome(offsprings);
		if (fitness(worse) < fitness(bestChromosome)) {
			offsprings.remove(worse);
			offsprings.add(bestChromosome);
		}
		
		return offsprings;
	}


}
