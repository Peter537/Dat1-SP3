package Magnus;

import org.junit.jupiter.api.Test;
import user.IUser;
import user.User;
import utils.data.FileIO;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class User_Test {
    @Test
    void testNewUser() {
        TEST_FileIO fileIO = new TEST_FileIO();
        ArrayList<IUser> users = fileIO.loadUsers();
        for (int i = 0; i < 8; i++) {
            IUser user = new User(-1, "user" + i, "testmail", "password1", 21, new ArrayList<>(), new ArrayList<>());

            fileIO.save(users, user);
        }
    }

}
