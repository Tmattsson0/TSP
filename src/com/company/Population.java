package com.company;

public class Population {


    Tour[] tours;

    //Generates population of random tours
    public Population(int populationSize) {
        tours = new Tour[populationSize];
        // Loop and create individuals
        for (int i = 0; i < populationSize; i++) {
            tours[i] = new Tour(true);
        }
    }

    // Gets the best tour in the population
    public Tour getFittestTour() {
        Tour fittestTour = tours[0];
        // Loop through individuals to find fittest
        for (int i = 1; i < populationSize(); i++) {
            if (fittestTour.getFitness() <= tours[i].getFitness()) {
                fittestTour = tours[i];
            }
        }
        return fittestTour;
    }

    public Tour[] getTours() {
        return tours;
    }

    public void saveTour(int i, Tour tour) {
        tours[i] = tour;
    }

    // Gets population size
    public int populationSize() {
        return tours.length;
    }
}
