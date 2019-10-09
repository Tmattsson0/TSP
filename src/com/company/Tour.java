package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static com.company.TourManager.getCity;
import static com.company.TourManager.getStartingCity;

public class Tour {

    ArrayList<City> tourRoute = new ArrayList<City>();

    private double fitness;
    private double distance;

    //Make tour with predefined route.
    public Tour(ArrayList<City> tour){
        this.tourRoute = tour;
    }

    //Make random tourRoute with defined starting city starting city.
    public Tour(Boolean random) {
        if(random) {
            tourRoute.addAll(TourManager.getCities());

            Collections.shuffle(tourRoute);

            //Puts starting city back to index 0
            Collections.swap(tourRoute, 0, TourManager.findStartingCityIndex(tourRoute));
        }
    }

    public double getFitness() {
        if (fitness == 0) {
            fitness = 1/getDistance();
        }
        return fitness;
    }

    public double getDistance() {
        if (distance == 0.0) {
            double tourDistance = 0.0;

            for (int i = 0; i < tourRoute.size(); i++) {

                if (i + 1 < tourRoute.size())
                    tourDistance += tourRoute.get(i).distance(tourRoute.get(i+1));
                else {
                    tourDistance += tourRoute.get(i).distance(tourRoute.get(0));
                }
            }
            distance = tourDistance;
        }
            return distance;
    }

    public boolean isCityInTour(City city){
        for (City c : this.tourRoute) {
            if (c != null)
                if(c.isSame(city)){
                return true;
                }
        }
        return false;
    }
}
