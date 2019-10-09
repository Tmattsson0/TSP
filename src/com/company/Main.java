package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        //Scanner and argument handling
        if (true) {
            try {
                //Set the filepath
//                String filePath = args[0];
//                String filePath = "/Users/thomasmattsson/Documents/GitHub/TSP/src/com/company/burma14.tsp";
                String filePath = "/Users/thomasmattsson/Documents/GitHub/TSP/src/com/company/rl1304.tsp";

                //Calls readFile that will parse the .tsp into an Arraylist of cities and save it in the TourManager
                TSPFileReader fr = new TSPFileReader();
                fr.readFile(filePath);

                if (args.length > 1) {
                    int populationArg = Integer.parseInt(args[1]);
                }
                if (args.length > 2) {
                    double fitnessArg = Integer.parseInt(args[2]);
                }

            } catch (IOException e) {
                System.err.println("File not found! Shutting down");
                System.exit(1);

            } catch (NumberFormatException er) {
                System.err.println("Argument '" + args[1] + "' must be an integer.");
                System.exit(2);
            }

        } else {
            System.err.println("No arguments shutting down...");
            System.exit(3);
        }

        // Initialize population
        Population pop = new Population(50);
        System.out.println("Initial distance: " + pop.getFittestTour().getDistance());

        // Evolve population for 100 generations
        pop = GeneticAlgorithm.evolvePopulation(pop);
        for (int i = 0; i < 100  ; i++) {
            pop = GeneticAlgorithm.evolvePopulation(pop);
        }

        // Print final results
        System.out.println("Finished");
        System.out.println("Final distance: " + pop.getFittestTour().getDistance());
        System.out.println("Solution:");
//        System.out.println(pop.getFittestTour());
    }
}
