package com.company;

import static java.lang.Math.abs;

class City {
    private String name;
    private double x;
    private double y;

    City(String name, double xCoord, double yCoord) {
        this.name = name;
        this.x = xCoord;
        this.y = yCoord;
    }

    double distance(City b){
        double xDis = abs(this.x - b.x);
        double yDis = abs(this.y - b.y);

        return Math.sqrt((xDis * 2) + (yDis * 2));
    }

    @Override
    public String toString() {
        return ("Name: " + this.name + "\t" + "X: " + this.x + "\t" + "Y: " + this.y);
    }
}
