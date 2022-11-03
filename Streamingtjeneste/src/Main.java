import genre.IGenre;
import genre.MovieGenre;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ArrayList<IGenre> genres = new ArrayList<>(new ArrayList<>(List.of(MovieGenre.values())));

        for (IGenre genre : genres) {
                System.out.print(genre);
        }

    }


}