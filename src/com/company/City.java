package com.company;

import static java.lang.Math.abs;
import static java.lang.Math.tan;

class City {
    private double x;
    private double y;

    City(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public double distance(City city){
        double xDis = abs(city.x - getX());
        double yDis = abs(city.y - getY());

        return Math.sqrt((xDis*xDis) + (yDis*yDis));
    }

    public boolean isSame(City city){
        if(city != null) {
            return getX() == city.x && getY() == city.y;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return ("X: " + getX() + ",\t" + "Y: " + getY());
    }
}
