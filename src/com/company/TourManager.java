package com.company;

import java.util.ArrayList;

//Class for holding the route as read by TSPFileReader along with helpermethods

public class TourManager {

    private static ArrayList<City> cities = new ArrayList<>();
    public static City startingCity;

    public static void addCity(City city) {
        if (cities.isEmpty()){
            cities.add(city);
            setStartingCity(city);
        } else {
            cities.add(city);
        }
    }

    // Get a city
    public static City getCity(int index){
        return cities.get(index);
    }

    // Get the number of cities
    public static int numberOfCities(){
        return cities.size();
    }

    public static int findStartingCityIndex(ArrayList<City> cities){
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getX() == startingCity.getX() && cities.get(i).getY() == startingCity.getY()){
                return i;
            }
        }
        return 0;
    }

    //Getter and setter
    public static ArrayList<City> getCities() {
        return cities;
    }

    public static void setCities(ArrayList<City> cities) {
        TourManager.cities = cities;
    }

    public static City getStartingCity() {
        return startingCity;
    }

    public static void setStartingCity(City startingCity) {
        TourManager.startingCity = startingCity;
    }
}
