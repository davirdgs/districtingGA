package problems.districting.solvers;

import java.util.Random;
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
		
		//TODO
		
		return null;
	}

	@Override
	protected Chromosome generateRandomChromosome() {
		
		Random rng = new Random();
		
		Chromosome chromosome = new Chromosome();
		
		for(int i = 0; i < ObjFunction.getDomainSize(); i++) {
			chromosome.add(i);
		}
		
		for(int i = 0; i < ObjFunction.getDomainSize(); i++) {
			chromosomeSwap(rng.nextInt(chromosome.size()), rng.nextInt(chromosome.size()), chromosome);
		}
		
		return chromosome;
	}
	
	protected void chromosomeSwap(int i, int j, Chromosome chromosome) {
		int aux = chromosome.get(i);
		chromosome.set(i, chromosome.get(j));
		chromosome.set(j, aux);
	}

	@Override
	protected Double fitness(Chromosome chromosome) {
		
		return null;
	}

	@Override
	protected void mutateGene(Chromosome chromosome, Integer locus) {
		// TODO Auto-generated method stub

	}
	
	public Chromosome getChromosome() {
		return generateRandomChromosome();
	}
	
	public void printChromosome(Chromosome chromosome) {
		for(int i = 0; i < chromosome.size(); i++) {
			System.out.print(chromosome.get(i) + " ");
		}
		System.out.println("");
	}

	@Override
	protected AbstractGA<Integer, Integer>.Population crossover(AbstractGA<Integer, Integer>.Population parents) {
		// TODO Auto-generated method stub
		return null;
	}

}
