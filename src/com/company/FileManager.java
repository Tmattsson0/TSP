package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FileManager {

    public void readFile(String file) throws IOException {

        try(BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
            String line = br.readLine();

            while (line != null) {
                if(line.matches("^[0-9 ].*$")){
//                    System.out.println(line);
                    String[] tempArr = line.trim().split("\\s+");

                    TourManager.addCity(new City(Double.parseDouble(tempArr[1]), Double.parseDouble(tempArr[2]), Integer.parseInt(tempArr[0])));
                }
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException();
        }
    }

    public void csvOut(Tour finalTour) throws IOException {
        FileWriter csvWriter = new FileWriter("bestRoute.csv");
        csvWriter.append("Index");
        csvWriter.append(", ");
        csvWriter.append("x");
        csvWriter.append(", ");
        csvWriter.append("y");
        csvWriter.append("\n");

        for (City rowData : finalTour.tourRoute) {
            csvWriter.append(String.join(", ",
                    Integer.toString(rowData.getName()),
                    Double.toString(rowData.getX()),
                    Double.toString(rowData.getY())));
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }
}
