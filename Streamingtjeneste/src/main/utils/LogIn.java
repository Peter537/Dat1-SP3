package main.utils;

import main.ChillMedia;
import main.user.IUser;
import main.user.User;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class LogIn {

    private IUser currentUser;

    private final ArrayList<IUser> users;


    public LogIn(ChillMedia cm) {
        this.currentUser = null;
        this.users = cm.getDataIO().loadUsers();
    }

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


    private void signUp() {
        // promts user to write email, and checks if the email is unique. If the email is not unique, the user has to write a different email.
        String email = TextIO.getUserInput("You are signing up. Write email address, press 0 to go back: ");
        if (email.equals("0")) {
            logIn();
            return;
        }

        if (checkEmailInList(email)) {
            TextIO.println("Email already in use, please try again");
            signUp();
            return;
        }

        String name = TextIO.getUserInput("You are signing up. Write name, press 0 to go back: ");
        if (name.equals("0")) {
            logIn();
            return;
        }

        // promts user for a password, this does not have to be unique;
        String password = TextIO.getUserInput("You are signing up. Write password, press 0 to go back: ");
        if (password.equals("0")) {
            logIn();
            return;
        }

        confirmPassword (password);

        int age = getAge ();

        createUser(name, password, email, age);

    }


    private void signIn() {

        // prompts user for email and checks if it exists in the list
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

    public void confirmPassword(String password) {
        String confirmPW;
        //below msg string is there because it needs to change statement if given prompt is not valid
        String msg = "You are signing up. Confirm password, press 0 to go back: ";
        boolean notConfirmed = true;
        while (notConfirmed) {
            confirmPW = TextIO.getUserInput(msg);

            if (confirmPW.equals("0")) {
                logIn();
                return;
            }

            if (confirmPW.equals(password)) {
                notConfirmed = false;
            } else {
                msg = "Passwords do not match, please try again or press 0 to go back: ";
            }
        }
    }

    public int getAge() {
        String userAge;
        while (true) {
            try {
                userAge = TextIO.getUserInput("You are signing up. Write your age, press 0 to go back: ");
                if (userAge.equals("0")) {
                    logIn();
                    break;
                }
                Integer.parseInt(userAge);
                return Integer.parseInt(userAge);

            } catch (NumberFormatException e) {
                TextIO.println("Please write a number.");
            }
        }
        return Integer.parseInt(userAge);
    }

    private void createUser(String name, String email, String password, int age) {
        currentUser = new User(-1, name, email, password, age, new ArrayList<>(), new ArrayList<>());
    }

    private boolean checkEmailInList(String email) {
        for (IUser p : users) {
            if (p.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

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