package problems.districting.solvers;

import metaheuristics.ga.AbstractGA;
import problems.Evaluator;
import solutions.Solution;

public class Districting_GA extends AbstractGA<Integer, Integer> {

	public Districting_GA(Evaluator<Integer> objFunction, Integer generations, Integer popSize, Double mutationRate) {
		super(objFunction, generations, popSize, mutationRate);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Solution createEmptySol() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Solution decode(Chromosome chromosome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Chromosome generateRandomChromosome() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Double fitness(Chromosome chromosome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void mutateGene(Chromosome chromosome, Integer locus) {
		// TODO Auto-generated method stub

	}

}
