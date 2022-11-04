package utils.data;

import genre.IGenre;
import genre.SeriesGenre;
import media.*;
import user.IUser;
import user.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileIO implements IDataIO {

    public FileIO() { }

    @Override
    public ArrayList<IUser> loadUsers() {
        File file = new File("Data/user.csv");
        ArrayList<IUser> users = new ArrayList<>();

        try {
            Scanner readUsers = new Scanner(file);

            while (readUsers.hasNextLine()) {
                String line = readUsers.nextLine();
                String[] values = line.split(";");

                int ID = Integer.parseInt(values[0]);
                String userName = values[1];
                String password = values[2];
                String email = values[3];
                int age = Integer.parseInt(values[4]);

                users.add(new User(ID, userName, password, email, age, new ArrayList<>(), new ArrayList<>()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public ArrayList<IMovie> loadMovies() {
        File file = new File("Data/film.csv");
        ArrayList<IMovie> movieData = new ArrayList<>();

        try {
            Scanner readMovies = new Scanner(file);

            while (readMovies.hasNextLine()) {
                String line = readMovies.nextLine();
                String[] values = line.split(";");

                String movieTitles = values[0];

                int year = Integer.parseInt(values[1]);

                ArrayList<IGenre> movieGenres = new ArrayList<>();
                String[] genreTitles = values[2].trim().split(",");

                for (String genre : genreTitles) {
                    try {
                        String genreName = genre.trim().toUpperCase().replaceAll("-", "_");
                        if (Arrays.stream(SeriesGenre.values()).anyMatch(g -> g.name().equals(genreName))) {
                            movieGenres.add(SeriesGenre.valueOf(genreName));
                        }
                    }
                    catch (Exception e){
                        //TODO Add error to an error list
                    }
                }

                float rating = Float.parseFloat(values[3]);
                movieData.add(new Movie(movieTitles, rating, movieGenres, year));
            }

        } catch (FileNotFoundException e ) {
            System.out.println("No file was found");
        }

        return movieData;
    }

    @Override
    public ArrayList<ISeries> loadSeries() {
        File file = new File("Data/serier.csv");
        ArrayList<ISeries> seriesData = new ArrayList<>();

        try {
            Scanner readSeries = new Scanner(file);

            while (readSeries.hasNextLine()) {
                String line = readSeries.nextLine();
                String [] values = line.split(";");

                String title = values[0];

                String[] yearsString =(values[1].split("-"));
                int startYear = Integer.parseInt(yearsString[0].trim());

                int endYear = -1;

                if ( yearsString.length > 1 && !yearsString[1].trim().equals("")) {
                    endYear = Integer.parseInt(yearsString[1].trim());
                }

                ArrayList<IGenre> seriesGenre = new ArrayList<>();
                String[] genreTitles = values[2].trim().split(",");

                for (String genre : genreTitles) {
                    try {
                        String genreName = genre.trim().toUpperCase().replaceAll("-", "_");
                        if (Arrays.stream(SeriesGenre.values()).anyMatch(g -> g.name().equals(genreName))) {
                            seriesGenre.add(SeriesGenre.valueOf(genreName));
                        }
                    }
                    catch (Exception e){
                        //TODO Add error to an error list
                    }
                }

                float rating = Float.parseFloat(values[3].replaceAll(",", "."));

                ArrayList<Season> seasonsEpisodes = new ArrayList<>();
                String[] seasonValues = values[4].split(",");

                for (String season : seasonValues) {
                    String[] seasonSplit = season.trim().split("-");
                    int seasonCount = Integer.parseInt(seasonSplit[0]);
                    int episodeCount = Integer.parseInt(seasonSplit[1]);

                    Season thisSeason = new Season (seasonCount,episodeCount);
                    seasonsEpisodes.add(thisSeason);
                }
                seriesData.add(new Series(title, startYear, endYear, rating, seriesGenre, seasonsEpisodes));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return seriesData;
    }
}