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

    double getX(){
        return this.x;
    }

    double getY(){
        return this.y;
    }

    double distance(City city){
        double xDis = abs(getX() - city.x);
        double yDis = abs(getY() - city.y);

        return Math.sqrt((xDis * 2) + (yDis * 2));
    }

    @Override
    public String toString() {
        return ("X: " + getX() + ",\t" + "Y: " + getY());
    }
}
