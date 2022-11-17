package main;

import main.utils.TextIO;
import main.utils.data.DataBaseIO;
import main.utils.data.FileIO;
import main.utils.data.IDataIO;

public class Main {

    /**
     * Main method
     *
     * @param args default parameter
     */
    public static void main(String[] args) {
        IDataIO dataIO = getDataIOType();
        ChillMedia cm = new ChillMedia(dataIO);
        cm.run();
    }

    /**
     *
     *
     * @return IDataIO
     */
    private static IDataIO getDataIOType() {
        while (true) {
            String dataIOType = TextIO.getUserInput("Vil du bruge filer eller database?", 1, new String[]{"Filer", "Database"});
            if (dataIOType.equalsIgnoreCase("1")) {
                return new FileIO();
            } else if (dataIOType.equalsIgnoreCase("2")) {
                return new DataBaseIO();
            }
        }
    }
}