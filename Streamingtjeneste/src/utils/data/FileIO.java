package utils.data;

import genre.IGenre;
import genre.SeriesGenre;
import media.IMovie;
import media.ISeries;
import media.Season;
import media.Series;
import user.IUser;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO implements IDataIO {

    public FileIO() {

    }

    @Override
    public ArrayList<IUser> loadUsers() {
        return null;
    }

    @Override
    public ArrayList<IMovie> loadMovies() {
        return null;
    }

    // SKAL SLETTES, VI SKAL BRUGE YUSUF'S FILEIO, BARE TIL
    // AT TESTE SÅ JEG KAN FÅ NOGET DATA
    @Override
    public ArrayList<ISeries> loadSeries() {
        ArrayList<ISeries> series = new ArrayList<>();
        try {
            File file = new File("Data/serier.csv");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String[] values = scanner.nextLine().split(";");

                String title = values[0];

                String[] yearsAsString = values[1].split("-");

                int startYear = Integer.parseInt(yearsAsString[0].trim());
                int endYear = -1;

                if (yearsAsString.length > 1 && !yearsAsString[1].trim().equals("")) {
                    endYear = Integer.parseInt(yearsAsString[1].trim());
                }

                float rating = Float.parseFloat(values[3].replace(",", "."));

                ArrayList<Season> seasons = new ArrayList<>();
                String[] seasonValues = values[4].split(",");

                for (String season : seasonValues) {
                    String[] seasonSplit = season.split("-");

                    int seasonNumber = Integer.parseInt(seasonSplit[0].trim());
                    int episodeCount = Integer.parseInt(seasonSplit[1].trim());

                    Season thisSeason = new Season(seasonNumber, episodeCount);

                    seasons.add(thisSeason);
                }


                ArrayList<IGenre> genres = new ArrayList<>();
                String[] genreValues = values[2].split(",");

                for (String genre : genreValues) {
                    genres.add(SeriesGenre.valueOf(genre.trim().toUpperCase().replaceAll("-", "_")));
                }

                ISeries thisSeries = new Series(title, startYear, endYear, rating, genres, seasons);

                series.add(thisSeries);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return series;
    }
}