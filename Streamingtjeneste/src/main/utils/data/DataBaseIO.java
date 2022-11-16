package main.utils.data;

import main.media.IMovie;
import main.media.ISeries;
import main.user.IUser;
import main.utils.data.dbutil.MySQL;
import main.utils.data.dbutil.SQLStatements;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class DataBaseIO implements IDataIO {

    private final MySQL mySQL;

    public DataBaseIO() {
        mySQL = new MySQL();
        mySQL.openConnection("localhost", "chillmedia", "root", getPassword());
    }

    public ArrayList<IUser> loadUsers() {
        ResultSet userdata = mySQL.executeQuery(SQLStatements.getAllUsers());
        return null;
    }

    public ArrayList<IMovie> loadMovies() {
        ResultSet movieData = mySQL.executeQuery(SQLStatements.getAllMovies());
        return null;
    }

    public ArrayList<ISeries> loadSeries() {
        ResultSet seriesData = mySQL.executeQuery(SQLStatements.getAllSeries());
        return null;
    }

    public void save(ArrayList<IUser> users) {

    }

    public MySQL getMySQL() {
        return mySQL;
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