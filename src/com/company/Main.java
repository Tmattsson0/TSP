package com.company;

public class Main {

    public static void main(String[] args) {
    Singleton st = Singleton.getInstance();
    FileRead fr = new FileRead();
    fr.readFile("/Users/thomasmattsson/Documents/GitHub/TSP/src/com/company/burma14.tsp");

//    for (City c : st.cities) {
//        System.out.println(c.toString());
//    }

    }
}
