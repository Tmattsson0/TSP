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
        if(distance == 0){
            int tourDistance = 0;

            for (int i = 0; i < tourRoute.size(); i++) {

                City fromCity = getCity(i);
                City toCity;

                //Make sure we dont go out of bounds
                if(i + 1 < tourRoute.size()){
                    toCity = getCity(i + 1);
                }
                //If last city is reached then toCity is starting city.
                else{
                    toCity = getStartingCity();
                }
                    tourDistance += fromCity.distance(toCity);
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
