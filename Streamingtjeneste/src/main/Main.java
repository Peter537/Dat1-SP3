package main;

import main.utils.TextIO;
import main.utils.data.DataBaseIO;
import main.utils.data.FileIO;
import main.utils.data.IDataIO;

public class Main {

    /**
     * Main method that initialises the program
     *
     * @param args default parameter
     */
    public static void main(String[] args) {
        IDataIO dataIO = getDataIOType();
        ChillMedia cm = new ChillMedia(dataIO);
        cm.run();
    }

    /**
     * prompts the user whether they want to use the textfiles (could be a local offline mode) or database (online) for the program-instance
     *
     * @return IDataIO returns the type of DataIO the user would like to use
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