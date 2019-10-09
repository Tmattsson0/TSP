package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class FileRead {
    private Singleton singleton = Singleton.getInstance();

    void readFile(String file) throws IOException {

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                if(line.matches("^[0-9 ].*$")){
//                    System.out.println(line);
                    String[] tempArr = line.split("\\s+");

                    singleton.cities.add(new City(Double.parseDouble(tempArr[2]), Double.parseDouble(tempArr[3])));
                }
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException();
        }
    }
}
