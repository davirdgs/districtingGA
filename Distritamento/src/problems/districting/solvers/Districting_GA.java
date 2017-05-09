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
		Solution sol = new Solution();
		return sol;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Solution decode(Chromosome chromosome) {
		
		Solution sol = new Solution();
		for(int i = 0; i < chromosome.size(); i++) {
			sol.add(chromosome.get(i));
		}
		sol.cost = this.ObjFunction.evaluate(sol);
		return sol;
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
		Solution sol = decode(chromosome);
		return 1/sol.cost;
	}

	@Override
	protected void mutateGene(Chromosome chromosome, Integer locus) {
		chromosomeSwap(locus, rng.nextInt(chromosome.size()), chromosome);
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
	
	public Chromosome c1Operator(Chromosome chrA, Chromosome chrB) {
		Chromosome chromosome = new Chromosome();
		boolean check[] = new boolean[chrA.size()];
		
		int crossPoint = rng.nextInt(chrA.size());
		System.out.println("CrossPoint: " + crossPoint);
		for(int i = 0; i < crossPoint; i++) {
			chromosome.add(chrA.get(i));
			check[chrA.get(i)] = true;
		}
		for(int i = 0; i < chrB.size(); i++) {
			if(!check[chrB.get(i)]) {
				chromosome.add(chrB.get(i));
				check[chrB.get(i)] = true;
			}
		}
		return chromosome;
	}

}
