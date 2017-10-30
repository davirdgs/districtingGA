package problems.districting.solvers;

import java.util.ArrayList;
import java.util.Random;
import metaheuristics.ga.AbstractGA;
import metaheuristics.ga.AbstractGA.Chromosome;
import metaheuristics.ga.AbstractGA.Population;
import problems.Evaluator;
import problems.districting.UndirectedGraph;
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
	public Chromosome generateRandomChromosome() {
		
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
	public Double fitness(Chromosome chromosome) {
		Solution sol = decode(chromosome);
		sol.cost = ObjFunction.evaluate(sol);
		return -sol.cost;
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
			ArrayList<Chromosome> cross = this.c11Operator(parents.get(i), parents.get(i+1));
			
			//
			
			//Solução mista com busca local
			LocalSearch_2OPT.localSearch(cross.get(0), ObjFunction);
			LocalSearch_2OPT.localSearch(cross.get(1), ObjFunction);
			
			//
			
			offsprings.add(cross.get(0));
			offsprings.add(cross.get(1));
		}
		
		Chromosome best = this.getBestChromosome(parents);
		offsprings.add(best);
		Chromosome worse = this.getWorseChromosome(offsprings);
		offsprings.remove(worse);
		
		
		offsprings.addAll(parents);
		
		return offsprings;
	}
	
	public void populationColumnSwap(Population pop, int i, int j) {
		for(int k = 0; k < pop.size(); k++) {
			chromosomeSwap(i, j, pop.get(k));
		}
	}
	
	protected Population initializePopulation() {
		
		Population pop = new Population();
		for(int i = 0; i < this.popSize; i++) {
			Chromosome chr = chromosomeGuloso();
			pop.add(chr);
		}
		
		return pop;
		//return latinPopulation();
		
	}
	
	public Chromosome chromosomeGuloso() {
		
		Random rng = new Random();
		
		Chromosome chr = new Chromosome();
		int[][] costMatrix = ObjFunction.getCostMatrix();
		boolean[] check = new boolean[costMatrix.length];
		int distritos = ObjFunction.getDomainSize() - costMatrix.length + 1;
		
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>(distritos);
		
		//init
		for(int i = 0; i < distritos; i++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			int despot = rng.nextInt(distritos);
			while(check[despot]) {
				despot = rng.nextInt(costMatrix.length);
			}
			list.add(despot);
			check[despot] = true;
			result.add(list);
		}
		
		int districtSize = costMatrix.length / distritos;
		
		for(int i = 0; i < result.size(); i++) {
			for(int j = 0; j <= districtSize; j++) {
				int last = result.get(i).get(result.get(i).size()-1);
				int next = maisProximo(last,check);
				if(next == -1) { break; }
				result.get(i).add(next);
				check[next] = true;
			}
		}
		
		//Concatena listas
		for(int i = 0; i < result.size(); i++) {
			chr.addAll(result.get(i));
			chr.add(costMatrix.length + i);
		}
		chr.remove(chr.size()-1);
		
		return chr;
	}
	
	///Ok
	public int maisProximo(int no) {
		int[][] costMatrix = ObjFunction.getCostMatrix();
		int bestNext = 0;
		if(no == bestNext) {
			bestNext++;
		}
		for(int i = 0; i < costMatrix.length; i++) {
			if(i==no || no==bestNext) { continue; }
			if(costMatrix[no][bestNext] > costMatrix[no][i]) {
				bestNext = i;
			}
		}
		return bestNext;
	}
	
	public int maisProximo(int no, boolean[] check) {
		int[][] costMatrix = ObjFunction.getCostMatrix();
		int bestNext = 0;
		
		while(true) {
			if(bestNext >= costMatrix.length) { return -1; }
			if(bestNext != no && !check[bestNext]) { break; }
			bestNext++;
		}
		
		boolean flag = true;
		for(int i = 0; i < check.length; i++) {
			if(!check[i]) { flag = false; }
		}
		if(flag) { return -1; }
		
		for(int i = 0; i < costMatrix.length; i++) {
			if(i==no || no==bestNext || check[i]) { continue; }
			if(costMatrix[no][bestNext] > costMatrix[no][i]) {
				bestNext = i;
			}
		}
		
		return bestNext;
	}
	
	public Population latinPopulation () {
		
		Population pop = new Population();
		
		int k = ObjFunction.getDomainSize();//popSize;
		for(int i = 0; i < ObjFunction.getDomainSize(); i++) {
			Chromosome chr = new Chromosome();
			int temp = k;
			while(temp < ObjFunction.getDomainSize()) {
				chr.add(temp);
				temp++;
			}
			for(int j = 0; j < k; j++) {
				chr.add(j);
			}
			k--;
			pop.add(chr);
		}
		
		Random rnd = new Random();
		for(int i = 0; i < pop.size(); i++) {
			populationColumnSwap(pop, rnd.nextInt(pop.size()), rnd.nextInt(pop.size()));
		}
		if(pop.size() > popSize) {
			while(pop.size() > popSize) {
				pop.remove(0);
			}
		} else {
			while(pop.size() != popSize) {
				Chromosome ch = this.generateRandomChromosome();
				pop.add(ch);
			}
		}
		
		return pop;
	}
	
	public ArrayList<Chromosome> c11Operator(Chromosome chrA, Chromosome chrB) {
		Chromosome chromosomeA = new Chromosome();
		Chromosome chromosomeB = new Chromosome();
		boolean checkA[] = new boolean[chrA.size()];
		boolean checkB[] = new boolean[chrB.size()];
		
		int crossPoint = rng.nextInt(chrA.size());
		
		ArrayList<Chromosome> children = new ArrayList<Chromosome>();
		children.add(chromosomeA);
		children.add(chromosomeB);
		
		for(int i = 0; i < crossPoint; i++) {
			chromosomeA.add(chrA.get(i));
			chromosomeB.add(chrB.get(i));
			checkA[chrA.get(i)] = true;
			checkB[chrB.get(i)] = true;
		}
		
		for(int i = crossPoint; i < chrB.size(); i++) {
			if(!checkB[chrA.get(i)]) {
				chromosomeB.add(chrA.get(i));
				checkB[chrA.get(i)] = true;
			} else {
				for(int j = 0; j < crossPoint; j++) {
					if(!checkB[chrA.get(j)]) {
						chromosomeB.add(chrA.get(j));
						checkB[chrA.get(j)] = true;
						break;
					}
				}
			}
			if(!checkA[chrB.get(i)]) {
				chromosomeA.add(chrB.get(i));
				checkA[chrB.get(i)] = true;
			} else {
				for(int j = 0; j < crossPoint; j++) {
					if(!checkA[chrB.get(j)]) {
						chromosomeA.add(chrB.get(j));
						checkA[chrB.get(j)] = true;
						break;
					}
				}
			}
		}
		
		return children;
	}
	
	public ArrayList<Chromosome> c1Operator(Chromosome chrA, Chromosome chrB) {
		Chromosome chromosomeA = new Chromosome();
		Chromosome chromosomeB = new Chromosome();
		boolean checkA[] = new boolean[chrA.size()];
		boolean checkB[] = new boolean[chrB.size()];
		
		int crossPoint = rng.nextInt(chrA.size());
		
		for(int i = 0; i < crossPoint; i++) {
			chromosomeA.add(chrA.get(i));
			chromosomeB.add(chrB.get(i));
			checkA[chrA.get(i)] = true;
			checkB[chrB.get(i)] = true;
		}
		
		for(int i = crossPoint; i < chrB.size(); i++) {
			if(!checkB[chrA.get(i)]) {
				chromosomeB.add(chrA.get(i));
				checkB[chrA.get(i)] = true;
			} 
			if(!checkA[chrB.get(i)]) {
				chromosomeA.add(chrB.get(i));
				checkA[chrB.get(i)] = true;
			}
		}
		
		for(int i = 0; i < chrB.size(); i++) {
			if(!checkA[chrB.get(i)]) {
				chromosomeA.add(chrB.get(i));
				checkA[chrB.get(i)] = true;
			}
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
	
	static public void printPopulation(Population pop) {
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
	
	public Population selectParentsTest(Population population) {
		return this.selectParents(population);
	}
	
	public Solution getBestSolution() {
		return this.bestSol;
	}
}
