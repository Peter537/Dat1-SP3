import org.junit.jupiter.api.Test;
import user.IUser;
import utils.data.FileIO;

import java.util.ArrayList;

public class User_Test {
    @Test
    public void testNewUser() {
        FileIO fileIO = new FileIO();
        ArrayList<IUser> users = fileIO.loadUsers();
    }

}
