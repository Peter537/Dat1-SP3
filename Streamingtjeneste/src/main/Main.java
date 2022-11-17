package main;

import main.utils.TextIO;
import main.utils.data.DataBaseIO;
import main.utils.data.FileIO;
import main.utils.data.IDataIO;

public class Main {

    public static void main(String[] args) {
        IDataIO dataIO = getDataIOType();
        ChillMedia cm = new ChillMedia(dataIO);
        cm.run();
    }

    private static IDataIO getDataIOType() {
        while (true) {
            String dataIOType = TextIO.getUserInput("Vil du bruge filer eller database? (f/d)", new String[]{"Filer", "Database"});
            if (dataIOType.equalsIgnoreCase("f") || dataIOType.equalsIgnoreCase("filer")) {
                return new FileIO();
            } else if (dataIOType.equalsIgnoreCase("d") || dataIOType.equalsIgnoreCase("database")) {
                return new DataBaseIO();
            }
        }
    }
}