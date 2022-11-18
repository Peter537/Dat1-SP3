package main.utils;

import main.ChillMedia;
import main.user.IUser;
import main.user.User;

import java.util.ArrayList;

public class LogIn {

    private IUser currentUser;
    private final ArrayList<IUser> users;

    /**
     * Constructor for LogIn
     * <p>
     * KOMMENTAR_TIL_KONSTRUKTØREN_HER
     *
     * @param chillmedia
     */
    public LogIn(ChillMedia chillmedia) {
        this.currentUser = null;
        this.users = chillmedia.getDataIO().loadUsers();
    }

    /**
     *
     */
    public void logIn() {
        String[] options = new String[]{
                "Sign in",
                "Sign up"
        };
        while (true) {
            String input = TextIO.getUserInput("Do you want to sign in, or sign up?", 1, options);
            if (input.equals("1")) {
                signIn();
            } else if (input.equals("2")) {
                signUp();
            }
            if (currentUser != null) {
                return;
            }
        }
    }

    /**
     *
     */
    private void signUp() {
        String email = TextIO.getUserInput("You are signing up. Write your email address, or press 0 to go back: ");
        if (email.equals("0")) {
            logIn();
            return;
        }

        if (checkEmailInList(email)) {
            TextIO.println("Email already in use...");
            while (true) {
                String input = TextIO.getUserInput("Press 1 to go back to signing up, or press 2 to switch to sign in: ");
                if (input.equals("1")) {
                    signUp();
                    return;
                } else if (input.equals("2")) {
                    signIn();
                    return;
                }
            }
        }

        String name = TextIO.getUserInput("You are signing up. Write name, press 0 to go back: ");
        if (name.equals("0")) {
            logIn();
            return;
        }

        String password = TextIO.getUserInput("You are signing up. Write password, press 0 to go back: ");
        if (password.equals("0")) {
            logIn();
            return;
        }

        boolean confirmed = confirmPassword(password);
        if (!confirmed) {
            logIn();
            return;
        }

        int age = getAge();
        if (age == -1) {
            logIn();
            return;
        }

        this.currentUser = new User(-1, name, email, password, age, new ArrayList<>(), new ArrayList<>());
    }

    /**
     *
     */
    private void signIn() {
        String email = TextIO.getUserInput("You are signing in. Write your email address, or press 0 to go back: ");
        if (email.equals("0")) {
            logIn();
            return;
        }

        if (!checkEmailInList(email)) {
            TextIO.println("Email not found, please try again");
            String[] options = new String[]{
                    "Yes",
                    "No"
            };
            String input = TextIO.getUserInput("Do you want to sign up?", 1, options);
            if (input.equals("1")) {
                signUp();
                return;
            } else if (input.equals("2")) {
                signIn();
                return;
            }
        }

        String msg = "Write your password, press 0 to go back: ";
        IUser user;
        while (true) {
            String password = TextIO.getUserInput(msg);
            if (password.equals("0")) {
                logIn();
                return;
            }

            user = getUser(email, password);
            if (user == null) {
                msg = "Password does not match, please try again: ";
            } else {
                break;
            }
        }
        this.currentUser = user;
    }

    /**
     *
     *
     * @param password
     * @return boolean
     */
    public boolean confirmPassword(String password) {
        String msg = "You are signing up. Confirm password, press 0 to go back: ";
        while (true) {
            String confirmPW = TextIO.getUserInput(msg);
            if (confirmPW.equals("0")) {
                return false;
            }
            if (confirmPW.equals(password)) {
                return true;
            } else {
                msg = "Passwords do not match, please try again or press 0 to go back: ";
            }
        }
    }

    /**
     *
     *
     * @return int
     */
    public int getAge() {
        while (true) {
            try {
                String userAge = TextIO.getUserInput("You are signing up. Write your age, press 0 to go back: ");
                if (userAge.equals("0")) {
                    return -1;
                }
                return Integer.parseInt(userAge);
            } catch (NumberFormatException e) {
                TextIO.println("Please write a number.");
            }
        }
    }

    /**
     *
     *
     * @param email
     * @return boolean
     */
    private boolean checkEmailInList(String email) {
        for (IUser p : users) {
            if (p.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     *
     * @param email
     * @param password
     * @return IUser
     */
    private IUser getUser(String email, String password) {
        for (IUser p : users) {
            if (p.getEmail().equals(email) && p.getPassword().equals(password)) {
                return p;
            }
        }
        return null;
    }

    public IUser getCurrentUser() {
        return currentUser;
    }
}