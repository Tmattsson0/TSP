package com.company;

import com.company.*;

import java.io.IOException;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Singleton st = Singleton.getInstance();

        //Scanner and argument handling

        if (args.length > 0) {
            try {
                st.filePath = args[0];

                FileRead fr = new FileRead();
                fr.readFile(st.filePath);

                if (args[1] != null) {
                    st.populationArg = Integer.parseInt(args[1]);
                }
                if (args[2] != null) {
                    st.fitnessArg = Integer.parseInt(args[2]);
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


        //Calculate travel costs to and from all cities
        double[][] travelCosts = new double[st.cities.size()][st.cities.size()];
        for (int i = 0; i < st.cities.size(); i++) {
            for (int j = 0; j <= i; j++) {
                if (i == j) {
                    travelCosts[i][j] = 0.0;
                } else {
                    travelCosts[i][j] = st.cities.get(i).distance(st.cities.get(j));
                    travelCosts[j][i] = travelCosts[i][j];
                }
            }
        }

        System.out.println(Arrays.deepToString(travelCosts));

//    for (City c : st.cities) {
//        System.out.println(c.toString());
//    }

    }
}
