package main.utils.data;

import main.media.IMovie;
import main.media.ISeries;
import main.user.IUser;
import main.utils.data.dbutil.MySQL;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DataBaseIO implements IDataIO {
    MySQL mySQL;

    public DataBaseIO() {
        mySQL = new MySQL();
        mySQL.openConnection("localhost", "chillmedia", "root", getPassword());
    }

    public ArrayList<IUser> loadUsers() {
        return null;
    }

    public ArrayList<IMovie> loadMovies() {
        return null;
    }

    public ArrayList<ISeries> loadSeries() {
        return null;
    }

    public void save(ArrayList<IUser> users) {

    }

    private String getPassword() {
        String password = "";

        File file = new File("Data/dbpass.csv");

        try {
            Scanner scanner = new Scanner(file);
            password = scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return password;
    }
}
