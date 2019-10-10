package com.company;

import java.io.IOException;

public class Main {
    static int populationArg = 100;
    private static int generationsArg = 1000;

    public static void main(String[] args) throws IOException, InterruptedException {
        FileManager fm = new FileManager();

        //If not running from commandline put FULL filepath to tsp here.
        String filePath = "/Users/thomasmattsson/Documents/GitHub/TSP/src/com/company/rl1304.tsp";

        //Scanner and argument handling
        if (args.length > 0) {
            try {
                //Set the filepath
                filePath = args[0];

                if (args.length > 1) {
                    populationArg = Integer.parseInt(args[1]);
                }
                if (args.length > 2) {
                    generationsArg = Integer.parseInt(args[2]);
                }

            } catch (NumberFormatException er) {
                System.err.println("Arguments must be integers! Shutting down...");
                er.printStackTrace();
                System.exit(2);
            }

        } else {
            System.err.println("No arguments. Shutting down...");
            System.exit(3);
        }

        //Calls readFile() that will parse the .tsp into an Arraylist of cities and save it in the TourManager
        fm.readFile(filePath);

        // Initialize population print to user
        long startTime = System.currentTimeMillis();
        Population pop = new Population(populationArg);
        double initialDistance = pop.getFittestTour().getDistance();
        System.out.printf("\n\nInitial distance: %.2f\n", initialDistance);
        Thread.sleep(1000);
        System.out.printf("Size or population = %d. Number of generations = %d\n", populationArg, generationsArg);
        Thread.sleep(500);
        System.out.println("Running... Please wait.");

        // Evolve population for x generations
        pop = GeneticAlgorithm.evolvePopulation(pop);
        for (int i = 0; i < generationsArg ; i++) {
            pop = GeneticAlgorithm.evolvePopulation(pop);
        }

        // Print final results
        System.out.println("Finished");
        System.out.printf("Final distance: %.2f\n", pop.getFittestTour().getDistance());
        System.out.printf("Route is %.2f%% shorter than before.\n", (initialDistance - pop.getFittestTour().getDistance())/initialDistance*100);
        fm.csvOut(pop.getFittestTour());

        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in seconds: " + timeElapsed/1000);
    }
}
