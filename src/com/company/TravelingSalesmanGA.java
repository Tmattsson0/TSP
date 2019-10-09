package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TravelingSalesmanGA {
    private final int reproductionSize;
    private Singleton st = Singleton.getInstance();

    private int tournamentSize;
    private int genomeSize;
    private float mutationRate;
    private int generationSize;

    private double targetFitness;

    //Total number of fitness evaluations
    private int maxIterations = st.fitnessArg;

    public TravelingSalesmanGA(int targetFitness){
        this.targetFitness = targetFitness;
        this.genomeSize = st.numberOfCities-1;


        generationSize = 5000;
        reproductionSize = 200;
        maxIterations = 1000;
        mutationRate = 0.1f;
        tournamentSize = 40;
    }

    public List<Genome> initialPopulation(){
        List<Genome> population = new ArrayList<>();
        for(int i=0; i<generationSize; i++){
            population.add(new Genome(st.numberOfCities, st.startingCity));
        }
        return population;
    }

    //Method that selects genomes with a specified selection method
    public List<Genome> selection(List<Genome> population){
        List<Genome> selectedGenomes = new ArrayList<>();

        for (int i = 0; i < reproductionSize; i++) {
        //TournamentSelection
        selectedGenomes.add(tournamentSelection(population));
        }

        return selectedGenomes;
    }

    //Method for picking n random elements from a list.
    public static <E> List<E> nRandomElements(List<E> list, int n){
        Random r = new Random();
        int randomNumb = r.nextInt((list.size() - n));

        Collections.shuffle(list);

        //Picking a random section of the shuffled list
        return list.subList(randomNumb, randomNumb + n);
    }

    //TournamentSelection
    public Genome tournamentSelection(List<Genome> population) {
        List<Genome> tournamentSelectedGenomes = nRandomElements(population, tournamentSize);
        return Collections.min(tournamentSelectedGenomes);
    }


    //PMX Crossover
    public List<Genome> crossover(List<Genome> parents){
        //Setup
        Random r = new Random();
        int breakpoint = r.nextInt(genomeSize);
        List<Genome> children = new ArrayList<>();

        List<City> parentGenome1 = new ArrayList<>(parents.get(0).getGenome());
        List<City> parentGenome2 = new ArrayList<>(parents.get(1).getGenome());

        //Child 1
        for (int i = 0; i < breakpoint; i++) {
            City newValue;
            newValue = parentGenome2.get(i);
            Collections.swap(parentGenome1, parentGenome1.indexOf(newValue), i);
        }
        children.add(new Genome(parentGenome1, st.cities.size(), st.startingCity));
        parentGenome1 = parents.get(0).getGenome();

        //Child 2
        for (int i = breakpoint; i <genomeSize; i++) {
            City newValue = parentGenome1.get(i);
            Collections.swap(parentGenome2, parentGenome2.indexOf(newValue), i);
        }
        children.add(new Genome(parentGenome2, st.cities.size(), st.startingCity));

        return children;
    }

    public Genome mutate(Genome salesman) {
        Random r = new Random();
        float mutate = r.nextFloat();
        if (mutate < mutationRate) {
            List<City> genome = salesman.getGenome();
            Collections.swap(genome, r.nextInt(genomeSize), r.nextInt(genomeSize));
            return new Genome(genome, st.cities.size(), st.startingCity);
        }
        return salesman;
    }

    public List<Genome> createGeneration(List<Genome> population) {
        List<Genome> generation = new ArrayList<>();
        int currentGenerationSize = 0;
        while (currentGenerationSize < generationSize) {
            List<Genome> parents = nRandomElements(population, 2);
            List<Genome> children = crossover(parents);
            children.set(0, mutate(children.get(0)));
            children.set(1, mutate(children.get(1)));
            generation.addAll(children);
            currentGenerationSize += 2;
        }
        return generation;
    }

    public Genome optimize() {
        List<Genome> population = initialPopulation();
        Genome globalBestGenome = population.get(0);
        for (int i = 0; i < maxIterations; i++) {
            List<Genome> selected = selection(population);
            population = createGeneration(selected);
            globalBestGenome = Collections.min(population);
            if (globalBestGenome.getFitness() < targetFitness)
                break;
        }
        return globalBestGenome;
    }
}

