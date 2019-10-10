package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GeneticAlgorithm {
    //GA parameters
    private static final double mutationRate = 0.015;
    private static final double crossoverRate = 0.99;
    private static final boolean elitism = true;
    private static final int tournamentSize = Main.populationArg/2;

    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.populationSize());

        // Keep our best tour if elitism is enabled
        int elitismOffset = 0;
        if (elitism) {
            newPopulation.saveTour(0, pop.getFittestTour());
            elitismOffset = 1;
        }

        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            //Choose best parents
            Tour parent1 = tournamentSelection(pop);
            Tour parent2 = tournamentSelection(pop);

                if (Math.random() < crossoverRate) {
                    Tour child1 = crossover(parent1, parent2);
                    //Add child to new pop
                    newPopulation.saveTour(i, child1);
                } else {
                    newPopulation.saveTour(i ,parent1);
                }
        }

        //Mutate
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getTours()[i]);
        }

        return newPopulation;
    }

    //PMX crossover
    private static Tour crossover(Tour parent1, Tour parent2) {
        //Create empty child tour
        Tour child1 = new Tour(false);
//        Tour child2 = new Tour(false);

        // Get start and end sub tour positions for parent1's tour
        Random r = new Random();

        int startPos = r.nextInt(parent1.tourRoute.size());
        int endPos = r.nextInt(parent1.tourRoute.size() - startPos) + startPos;


        //Add subsection of parent2 to child1. Add null everywhere else.
        for (int i = 0; i < parent2.tourRoute.size(); i++) {
            if(i >= startPos && i <= endPos){
                child1.tourRoute.add(parent2.tourRoute.get(i));
            } else {
                child1.tourRoute.add(null);
            }
        }

        //Add cities from parent1 to child1 where if there is no conflict.
        for (int i = 0; i < parent1.tourRoute.size(); i++) {
            if (!child1.isCityInTour(parent1.tourRoute.get(i)) && (child1.tourRoute.get(i) == null)){
                child1.tourRoute.set(i, parent1.tourRoute.get(i));
            }
        }

        //Add remaining cities from opposite parent.
        for (int i = 0; i < child1.tourRoute.size(); i++) {
            if (child1.tourRoute.get(i) == null){
                for (int j = 0; j < parent1.tourRoute.size(); j++) {
                    if (!child1.isCityInTour(parent1.tourRoute.get(j))){
                        child1.tourRoute.set(i, parent1.tourRoute.get(j));
                        break;
                    }
                }
            }
        }
            return child1;
    }

    private static Tour tournamentSelection(Population pop) {
        // Create a random tournament population
        Population tournament = new Population(tournamentSize);

        for (int i = 0; i < tournamentSize; i++) {
            // Get a second random position in the tour
            Random r = new Random();
            int randomInt = r.nextInt(pop.populationSize());

            //Compares new tournament population to old population and puts the fittest individual in the tournament.
            if (tournament.tours[i].getFitness() < pop.getTours()[randomInt].getFitness()){
                tournament.saveTour(i, pop.getTours()[randomInt]);
            }
        }
        return tournament.getFittestTour();
    }

    private static void mutate(Tour tour) {
        for(int tourPos1 = 0; tourPos1 < tour.tourRoute.size(); tourPos1++){
            //Mutation rate
            if(Math.random() < mutationRate){
                // Get a second random position in the tour
                Random r = new Random();
                int tourPos2 = r.nextInt((tour.tourRoute.size() - 1) + 1);

                // Get the cities at target position in tour
                City city1 = tour.tourRoute.get(tourPos1);
                City city2 = tour.tourRoute.get(tourPos2);

                // Swap cities
                tour.tourRoute.set(tourPos2, city1);
                tour.tourRoute.set(tourPos1, city2);
            }
        }
    }
}
