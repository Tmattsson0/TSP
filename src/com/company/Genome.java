package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Genome implements Comparable {
    private Singleton st = Singleton.getInstance();


    private List<City> genome;
    private City startingCity;
    private int numberOfCities;
    private double fitness;

    // Generates a random salesman
    public Genome(int numberOfCities, City startingCity) {
        this.startingCity = startingCity;
        this.numberOfCities = numberOfCities;

        this.genome = randomSalesman();
        this.fitness = this.calculateFitness();
    }

    // Generates a salesman with a user-defined genome
    public Genome(List<City> permutationOfCities, int numberOfCities, City startingCity) {
        this.genome = permutationOfCities;
        this.startingCity = startingCity;
        this.numberOfCities = numberOfCities;

        this.fitness = this.calculateFitness();
    }

    // Generates a random genome
    // Genomes are permutations of the list of cities, except the starting city
    // so we add them all to a list and shuffle
    public List<City> randomSalesman() {
        List<City> result = new ArrayList<City>();
        for (int i = 0; i < numberOfCities; i++) {
            if(st.cities.get(i).getX() != startingCity.getX() && st.cities.get(i).getY() != startingCity.getY()) {
                result.add(st.cities.get(i));
            }
        }
        Collections.shuffle(result);
        return result;
    }

    double calculateFitness() {
        double fitness = 0;
        City currentCity = startingCity;

        // Calculating path cost
        for (City gene : genome) {
            fitness += currentCity.distance(gene);
            currentCity = gene;
        }

        // We have to add going back to the starting city to complete the circle
        // the genome is missing the starting city, and indexing starts at 0, which is why we subtract 2
        fitness += currentCity.distance(startingCity);

        return fitness;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public List<City> getGenome() {
        return genome;
    }
}
