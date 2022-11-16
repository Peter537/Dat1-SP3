package main;

import main.utils.data.DataBaseIO;

public class Main {

    public static void main(String[] args) {
        DataBaseIO dbio = new DataBaseIO();

        ChillMedia cm = new ChillMedia();
        cm.run();
    }
}