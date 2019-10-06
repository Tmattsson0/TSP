package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
    Singleton st = Singleton.getInstance();
    FileRead fr = new FileRead();
    fr.readFile("/Users/thomasmattsson/Documents/GitHub/TSP/src/com/company/burma14.tsp");

    //Calculate travel costs to and from all cities
    double[][] travelCosts = new double[st.cities.size()][st.cities.size()];
        for(int i = 0; i<st.cities.size(); i++){
            for(int j=0; j<=i; j++){
                if(i==j) {
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
