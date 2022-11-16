package main.utils.data;

import main.media.IMovie;
import main.media.ISeries;
import main.user.IUser;
import main.utils.data.dbutil.MySQL;

import java.util.ArrayList;

public class DataBaseIO implements IDataIO {
    MySQL mySQL;

    public DataBaseIO() {
        mySQL = new MySQL();
        mySQL.openConnection("localhost", "ChillMedia", "root", "12321");
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
}
