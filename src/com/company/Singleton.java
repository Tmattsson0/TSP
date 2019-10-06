package com.company;

import java.util.ArrayList;

public class Singleton {
    // Variables
    ArrayList<City> cities;
    //Commandline arguments
    String filePath;
    int populationArg;
    int fitnessArg;



    // static variable single_instance of type Singleton
    private static Singleton single_instance = null;

    // private constructor restricted to this class itself
    private Singleton() {
        cities = new ArrayList<City>();
    }

    // static method to create instance of Singleton class
    public static Singleton getInstance()
    {
        if (single_instance == null)
            single_instance = new Singleton();

        return single_instance;
    }


}

