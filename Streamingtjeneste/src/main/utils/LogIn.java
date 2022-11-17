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
            String input = TextIO.getUserInput("Do you want to sign in, or create a new user?", 1, options);
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
        String email = TextIO.getUserInput("You are signing up. Write email address, press 0 to go back: ");
        if (email.equals("0")) {
            logIn();
            return;
        }

        /* HVAD SKER DER HVIS MAN SKRIVER "3" */
        if (checkEmailInList(email)) {
            TextIO.println("Email already in use...");
            String input = TextIO.getUserInput("Press 1 to continue signing up or press 2 to switch to sign in: ");
            if (input.equals("1")) {
                signUp();
                return;
            } else if (input.equals("2")) {
                signIn();
                return;
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

        createUser(name, password, email, age);
    }

    /**
     *
     */
    private void signIn() {
        String email = TextIO.getUserInput("You are signing in. Write email address, press 0 to go back: ");
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

        String password = TextIO.getUserInput("Write your password, press 0 to go back: ");
        if (password.equals("0")) {
            logIn();
            return;
        }

        IUser user = getUser(email, password);
        if (user == null) {
            TextIO.println("Password does not match, please try again");
            signIn();
            return;
        }
        currentUser = user;
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
     * Eventuelt fjerne denne metode? ret irrelevant ift. det kun er 1 linje
     * <p>
     * Vi har et ID som er '-1', men det skal det ikke være.
     * Vi skal lige have kigget det igennem og fundet en anden løsning,
     * når vi skal oprette en user herinde i login.
     *
     * @param name
     * @param email
     * @param password
     * @param age
     */
    private void createUser(String name, String email, String password, int age) {
        currentUser = new User(-1, name, email, password, age, new ArrayList<>(), new ArrayList<>());
    }

    /**
     *
     *
     * @param email
     * @return boolean
     */
    private boolean checkEmailInList(String email) {
        return users.stream().anyMatch(user -> user.getEmail().equals(email));
        /*
        for (IUser p : users) {
            if (p.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
         */
    }

    /**
     *
     *
     * @param email
     * @param password
     * @return IUser
     */
    private IUser getUser(String email, String password) {
        return users.stream().filter(user -> user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)).findFirst().orElse(null);
        /*
        for (IUser p : users) {
            if (p.getEmail().equals(email) && p.getPassword().equals(password)) {
                return p;
            }
        }
        return null;
         */
    }

    public IUser getCurrentUser() {
        return currentUser;
    }
}