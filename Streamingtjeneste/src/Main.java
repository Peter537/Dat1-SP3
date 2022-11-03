import genre.IGenre;
import genre.MovieGenre;
import genre.SeriesGenre;
import media.ISeason;
import media.ISeries;
import media.Season;
import media.Series;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("Data/serier.csv");
        Scanner scanner = new Scanner(file);

        ArrayList<ISeries> series = new ArrayList<>();

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

            ArrayList<ISeason> seasons = new ArrayList<>();
            String[] seasonValues = values[4].split(",");

            for (String season : seasonValues) {
                String[] seasonSplit = season.split("-");

                int seasonNumber = Integer.parseInt(seasonSplit[0].trim());
                int episodeCount = Integer.parseInt(seasonSplit[1].trim());

                ISeason thisSeason = new Season(seasonNumber, episodeCount);

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

        System.out.println(series);
    }
}