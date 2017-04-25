package problems.districting.solvers;

import application.Main;
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
		
		int district = Main.graphContext.getDistrictNumber();
		int nodes = Main.graphContext.getNodesNumber();
		Chromosome chromosome = new Chromosome();
		
		for(int i = 0; i < district; i++) {
			chromosome.add(0);
		}
		for(int i = 1; i <= nodes; i++) {
			chromosome.add(i);
		}
		
		return null;
	}
	
	protected void chromosomeSwap(int i, int j, Chromosome chromosome) {
		int aux = chromosome.get(i);
		chromosome.set(i, chromosome.get(j));
		chromosome.set(j, aux);
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
