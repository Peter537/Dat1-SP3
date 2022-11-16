package main.utils;

import main.ChillMedia;
import main.user.IUser;
import main.user.User;

import java.util.ArrayList;

public class LogIn {

    private IUser currentUser;
    private final ArrayList<IUser> users;

    public LogIn(ChillMedia cm) {
        this.currentUser = null;
        this.users = cm.getSessionCache().getUsers();
    }

    public void logIn() {
        // Create message for TextIO
        String msg = "Do you want to sign in, or create a new user?";
        // Create options for TextIO
        String[] options = new String[]{
                "Sign in",
                "Sign up"
        };
        // direct user to signIn() or signUp() depending on their answer
        while (true) {
            String input = TextIO.getUserInput(msg, 1, options);
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
        String msg = "You are signing up. Write email address, press 0 to go back: ";
        String email = TextIO.getUserInput(msg);
        if (email.equals("0")) {
            logIn();
            return;
        }

        if (checkEmailInList(email)) {
            TextIO.println("Email already in use, please try again");
            signUp();
            return;
        }

        // promts user for a name, this does not have to be unique
        msg = "You are signing up. Write name, press 0 to go back: ";
        String name = TextIO.getUserInput(msg);
        if (name.equals("0")) {
            logIn();
            return;
        }

        // promts user for a password, this does not have to be unique
        msg = "You are signing up. Write password, press 0 to go back: ";
        String password = TextIO.getUserInput(msg);
        if (password.equals("0")) {
            logIn();
            return;
        }

        //will check if you can confirm the same password
        msg = "You are signing up. Confirm password, press 0 to go back: ";
        String confirmPassword;
        boolean notConfirmed = true;
        while (notConfirmed) {
            confirmPassword = TextIO.getUserInput(msg);
            if (confirmPassword.equals("0")) {
                logIn();
                return;
            }
            if (confirmPassword.equals(password)) {
                notConfirmed = false;
            } else {
                msg = "Passwords do not match, please try again or press 0 to go back: ";
            }
        }

        // promts user for their age, this does not have to be unique. The age is converted into an integer.
        int age;
        while (true) {
            try {
                msg = "You are signing up. Write your age, press 0 to go back: ";
                String sAge = TextIO.getUserInput(msg);
                if (sAge.equals("0")) {
                    logIn();
                }
                age = Integer.parseInt(sAge);
                break;
            } catch (NumberFormatException e) {
                TextIO.println("Please write a number");
            }
        }

        // A new userprofile is created with the field values the user has given.
        createUser(name, password, email, age);
    }


    private void signIn() {
        // promts user for email and checks if it exists in the list
        String msg = "You are signing in. Write email address, press 0 to go back: ";
        String email = TextIO.getUserInput(msg);
        if (email.equals("0")) {
            logIn();
            return;
        }

        if (!checkEmailInList(email)) {
            TextIO.println("Email not found, please try again");
            msg = "Do you want to sign up?";
            String[] options = new String[]{
                    "Yes",
                    "No"
            };
            String input = TextIO.getUserInput(msg, 1, options);
            if (input.equals("1")) {
                signUp();
                return;
            } else if (input.equals("2")) {
                signIn();
                return;
            }
        }

        // NOT DONE, needs to check if password matches ( Or does it? That
        // should probably only be done in sign UP (which is already made)
        msg = "Write your password, press 0 to go back: ";
        String password = TextIO.getUserInput(msg);
        if (password.equals("0")) {
            logIn();
            return;
        }


        // checks if the password matches the password in the userprofile
        IUser user = getUser(email, password);
        if (user == null) {
            TextIO.println("Password does not match, please try again");
            signIn();
            return;
        }

        currentUser = user;
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