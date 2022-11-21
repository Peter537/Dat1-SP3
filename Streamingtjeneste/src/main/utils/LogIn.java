package main.utils;

import main.ChillMedia;
import main.user.IUser;
import main.user.User;

import java.util.ArrayList;

public class LogIn {

    private final ChillMedia chillMedia;
    private final ArrayList<IUser> users;
    private IUser user;

    /**
     * Constructor for LogIn
     * <p>
     * This constructor takes an instance of chillmedia which is instantiated by individual users.
     *
     * @param chillMedia is the instance of chillmedia
     */
    public LogIn(ChillMedia chillMedia) {
        this.chillMedia = chillMedia;
        this.users = chillMedia.getDataIO().loadUsers();
        this.user = null;
    }

    /**
     * This method prompts the user whether they want to sign in or sign up
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
            if (getUser() != null) {
                return;
            }
        }
    }

    /**
     * this method takes the user through the sign up flow.
     * This method has a "go back" option so they can exist out of sign In
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

        setUser(new User(-1, name, email, password, age, new ArrayList<>(), new ArrayList<>()));
    }

    /**
     * this method takes the user through the sign in flow.
     * This method has a "go back" option so they can exist out of signup
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
        setUser(user);
        getChillMedia().getDataIO().setCache(user);
    }

    /**
     * this method prompts the user to confirm whether or not they password they entered was what they wanted
     *
     * @param password is the password they initially typed and that they must confirm is the right passowrd
     * @return boolean returns true once the user re-enters the correct password and moves on through the sign up flow
     */
    private boolean confirmPassword(String password) {
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
     * This method prompts the user for their age
     *
     * @return int is the integer value of the user's age
     */
    private int getAge() {
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
     * This method is part of both signIn/SignUp flow and checks if the email used to sign in matches the typed string
     * or it is used to check if the email they are trying to sign up with already is associated with an existing account
     *
     * @param email
     * @return boolean
     */
    private boolean checkEmailInList(String email) {
        for (IUser p : getUsers()) {
            if (p.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method gets the user that matches the email and password the user prompted into the log in flow
     *
     * @param email is the email that matches the user's input.
     * @param password is the password that matches the user's input
     * @return IUser returns the user's account-instance so they can operate they own account through chillmedia flow
     */
    private IUser getUser(String email, String password) {
        for (IUser p : getUsers()) {
            if (p.getEmail().equals(email) && p.getPassword().equals(password)) {
                return p;
            }
        }
        return null;
    }

    private ArrayList<IUser> getUsers() {
        return this.users;
    }

    private ChillMedia getChillMedia() {
        return this.chillMedia;
    }

    private void setUser(IUser user) {
        this.user = user;
    }

    public IUser getUser() {
        return this.user;
    }
}