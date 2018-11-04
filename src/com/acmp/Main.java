package com.acmp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Integer> people = new ArrayList<>();
    private static int humans;
    private static int steps;

    public static void main(String[] args) throws FileNotFoundException, CustomException {

        readFile();
        getSurvivor();
        writeFile(people.get(0));

    }

    private static void readFile() throws FileNotFoundException, CustomException {
        FileInputStream file = new FileInputStream("INPUT.txt");
        Scanner inputFile = new Scanner(file);
        String line = inputFile.nextLine();
        final String[] array = line.split(" ");
        humans = Integer.parseInt(array[0]);
        steps = Integer.parseInt(array[1]);
        if (!(humans > 0 && humans <= 500 && steps > 0 && steps <= 100)) {
            throw new CustomException("Please, check INPUT.txt file. N <= 500, K <= 100");
        }
    }

    private static void getSurvivor(){
        int counter = 1;
        int index = 0;

        for (int i = 0; i < humans; i++) {
            people.add(i + 1);
        }

        while (people.size() != 1) {
            if (counter < steps) {
                counter++;
                index = index + 1 < people.size() ? ++index : 0;
            } else {
                people.remove(index);
                counter = 1;
                if (index == people.size()){
                    index = 0;
                }
            }
        }
    }

    private static void writeFile(int survivor) {
        File file = new File("OUTPUT.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
            writer.println(survivor);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }
    }

}

class CustomException extends Exception {

    public CustomException(String message) {
        super(message);
    }

}