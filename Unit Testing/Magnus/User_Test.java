package Magnus;

import main.media.IMovie;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import main.user.IUser;
import main.utils.data.FileIO;

import java.io.IOException;
import java.util.ArrayList;

class User_Test {
    @Test
    void testNewUser() throws IOException, ParseException {
        FileIO fileIO = new FileIO();
        ArrayList<IUser> users = fileIO.loadUserFromJson();
        ArrayList<IMovie> movies = fileIO.loadMovies();

        IUser user = users.get(0);
//        for (int i = 0; i < 8; i++) {
//            IUser user = new User(-1, "user" + i, "testmail", "password1", 21, new ArrayList<>(), new ArrayList<>());
//
//            //fileIO.save(users, user);
//        }

    }

}
