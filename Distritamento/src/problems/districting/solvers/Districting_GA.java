package problems.districting.solvers;

import java.util.ArrayList;
import java.util.Random;
import metaheuristics.ga.AbstractGA;
import metaheuristics.ga.AbstractGA.Population;
import problems.Evaluator;
import solutions.Solution;

public class Districting_GA extends AbstractGA<Integer, Integer> {

	public Districting_GA(Evaluator<Integer> objFunction, Integer generations, Integer popSize, Double mutationRate) {
		super(objFunction, generations, popSize, mutationRate);
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
		sol.cost = ObjFunction.evaluate(sol);
		return 1/sol.cost;
	}

	@Override
	protected void mutateGene(Chromosome chromosome, Integer locus) {
		chromosomeSwap(locus, rng.nextInt(chromosome.size()), chromosome);
	}
	
	public Chromosome getChromosome() {
		return generateRandomChromosome();
	}
	
	public static void printChromosome(Chromosome chromosome) {
		for(int i = 0; i < chromosome.size(); i++) {
			System.out.print(chromosome.get(i) + " ");
		}
		System.out.println("");
	}

	@Override
	protected AbstractGA<Integer, Integer>.Population crossover(AbstractGA<Integer, Integer>.Population parents) {
		
		Population offsprings = new Population();
		
		for(int i = 0; i < popSize; i = i+2) {
			ArrayList<Chromosome> cross = this.c1Operator(parents.get(i), parents.get(i+1));
			offsprings.add(cross.get(0));
			offsprings.add(cross.get(1));
		}
		
		Chromosome best = this.getBestChromosome(parents);
		offsprings.add(best);
		Chromosome worse = this.getWorseChromosome(offsprings);
		offsprings.remove(worse);
		
		return offsprings;
	}
	
	public ArrayList<Chromosome> c1Operator(Chromosome chrA, Chromosome chrB) {
		Chromosome chromosomeA = new Chromosome();
		Chromosome chromosomeB = new Chromosome();
		boolean checkA[] = new boolean[chrA.size()];
		boolean checkB[] = new boolean[chrB.size()];
		
		int crossPoint = rng.nextInt(chrA.size());
		System.out.println("CrossPoint: " + crossPoint);
		for(int i = 0; i < crossPoint; i++) {
			chromosomeA.add(chrA.get(i));
			chromosomeB.add(chrB.get(i));
			checkA[chrA.get(i)] = true;
			checkB[chrB.get(i)] = true;
		}
		for(int i = crossPoint; i < chrB.size(); i++) {
			if(!checkA[chrB.get(i)]) {
				chromosomeA.add(chrB.get(i));
				checkA[chrB.get(i)] = true;
			}
		}
		
		for(int i = crossPoint; i < chrB.size(); i++) {
			if(!checkB[chrA.get(i)]) {
				chromosomeB.add(chrA.get(i));
				checkB[chrA.get(i)] = true;
			}
		}
		
		for(int i = 0; i < chrB.size(); i++) {
			if(!checkA[chrB.get(i)]) {
				chromosomeA.add(chrB.get(i));
				checkA[chrB.get(i)] = true;
			}
		}
		
		for(int i = 0; i < chrB.size(); i++) {
			if(!checkB[chrA.get(i)]) {
				chromosomeB.add(chrA.get(i));
				checkB[chrA.get(i)] = true;
			}
		}
		
		ArrayList<Chromosome> children = new ArrayList<Chromosome>();
		children.add(chromosomeA);
		children.add(chromosomeB);
		return children;
	}
	
	public void printPopulation(Population pop) {
		for(int i =  0; i < pop.size(); i++) {
			Districting_GA.printChromosome(pop.get(i));
		}
	}
	
	//
	public Solution decodeTest(Chromosome c) {
		return decode(c);
	}
	
	public Population initializePopulationTest() {
		return this.initializePopulation();
	}
	
	public Chromosome getBestChromosomeTest(Population pop) {
		return this.getBestChromosome(pop);
	}
	
	public Chromosome getWorseChromosomeTest(Population pop) {
		return this.getWorseChromosome(pop);
	}
	
	public AbstractGA<Integer, Integer>.Population crossoverTest(AbstractGA<Integer, Integer>.Population parents) {
		return this.crossover(parents);
	}
	
	public Population mutateTest(Population offsprings)  {
		return this.mutate(offsprings);
	}
}
