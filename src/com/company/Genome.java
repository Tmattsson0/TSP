package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Genome implements Comparable {
    Singleton st = Singleton.getInstance();

    List<City> genome;
    double[][] travelCost;
    City startingCity = st.cities.get(0);
    int numberOfCities;
    double fitness;

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
    private List<City> randomSalesman() {
        List<City> result = new ArrayList<City>();
        for (int i = 0; i < numberOfCities; i++) {
//            if (i != startingCity)
//                result.add(i);
        }
        Collections.shuffle(result);
        return result;
    }





    public double calculateFitness() {
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
}
