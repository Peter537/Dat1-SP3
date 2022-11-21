package main.utils;

import main.media.IMedia;

import java.util.ArrayList;
import java.util.Scanner;

public class TextIO {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * This method displays a message to the user and then prompts them for a response
     *
     * @param msg is the message the user will be shown
     * @return String is the string value the user inputs afterwards
     */
    public static String getUserInput(String msg){
        print(msg);
        return getScanner().nextLine();
    }

    /**
     * This method is an overload that not only displays a message and lets the user response, but it also shows a
     * list of options before the user responds
     *
     * @param msg is the message the user will be shown
     * @param options is the list of options the user can choose from
     * @return String is the string value the user has inputted
     */
    public static String getUserInput(String msg, String[] options) {
        println(msg);
        for (int i = 0; i < options.length; i++) {
            println((i) + ". " + options[i]);
        }
        return getScanner().nextLine();
    }

    /**
     * This method is similar to the above message in letting the user choose from a displayed list of options.
     * The difference is that this method has a startindex as index '0' is reserved for the "go back" function
     *
     * @param msg is the message the user will be shown
     * @param startIndex Is the startindex for the options (usually starts at 1, as 0 is reserved for the "go back" function)
     * @param options is the list of options the user can choose from
     * @return String is the string value the user has inputted
     */
    public static String getUserInput(String msg, int startIndex, String[] options) {
        println(msg);
        for (int i = 0; i < options.length; i++) {
            println((i + startIndex) + ". " + options[i]);
        }
        return getScanner().nextLine();
    }

    /**
     * This is a helper method that displays the page to the user and shows the user how to navigate the page of
     * media the user queried for. It then also lets the user choose from the displayed media based on the query
     *
     * @param msg is a message the user will be shown
     * @param page is the page number that the user is on
     * @param pageSize is the page size (here we have chosen it should be 10 movies shown per page)
     * @param media is the list of media that was loaded onto that page
     * @return String is the user's input for their choice of media
     */
    public static String getUserInputFromMedia(String msg, int page, int pageSize, ArrayList<IMedia> media) {
        println(msg);
        println("-2 - Previous page");
        println("-1 - Next page");
        println("0 - Exit");
        for (int i = 0; i < media.size(); i++) {
            println((i + 1 + ((page - 1) * pageSize)) + " - " + media.get(i).getTitle());
        }
        return getScanner().nextLine();
    }

    /**
     * We made a method for println so we can rid the program of cluttering "System.out.println"-lines
     *
     * @param msg is the message to be displayed
     */
    public static void println(String msg) {
        System.out.println(msg);
    }

    /**
     * We made a method for println so we can rid the program of cluttering "System.out.print"-lines
     *
     * @param msg is the message to be displayed
     */
    public static void print(String msg) {
        System.out.print(msg);
    }

    public static Scanner getScanner() {
        return scanner;
    }
}
