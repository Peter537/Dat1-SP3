import org.junit.jupiter.api.Test;
import user.IUser;
import user.User;
import utils.data.FileIO;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class User_Test {
    @Test
    void testNewUser() {
        FileIO fileIO = new FileIO();
        ArrayList<IUser> users = fileIO.loadUsers();
        IUser user = new User(-1, "user1", "testmail", "password1", 21, new ArrayList<>(), new ArrayList<>());

        user.save(users);

        assertEquals("user1", user.getName());
    }

}
