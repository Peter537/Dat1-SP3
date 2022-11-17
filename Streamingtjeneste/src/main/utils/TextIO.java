package main.utils;

import main.media.IMedia;

import java.util.ArrayList;
import java.util.Scanner;

public class TextIO {

    private static final Scanner scanner = new Scanner(System.in);

    /*
     * This method receives a message and displays it to the user and returns the input.
     *
     * @param msg The message to print to the user
     * @return String The input from the user
     */
    public static String getUserInput(String msg){
        print(msg);
        return getScanner().nextLine();
    }

    /*
     * This method receives a message and displays it to the user and displays some options to the user.
     *
     * @param msg The message to print to the user
     * @param options The options to display to the user
     * @return String The input from the user
     */
    public static String getUserInput(String msg, String[] options) {
        println(msg);
        for (int i = 0; i < options.length; i++) {
            println((i) + ". " + options[i]);
        }
        return getScanner().nextLine();
    }

    /*
     * This method receives a message and displays it to the user and displays some options to the user.
     *
     * @param msg The message to print to the user
     * @param startIndex The index to start at
     * @param options The options to display to the user
     * @return String The input from the user
     */
    public static String getUserInput(String msg, int startIndex, String[] options) {
        println(msg);
        for (int i = 0; i < options.length; i++) {
            println((i + startIndex) + ". " + options[i]);
        }
        return getScanner().nextLine();
    }

    /*
     * This method receives a message and displays it to the user and displays some options to the user.
     *
     * @param msg The message to print to the user
     * @param page The page that the user is on
     * @param pageSize The size of the page
     * @param media The list of media to display to the user
     * @return String The input from the user
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

    /*
     * This method receives a message and displays it on a new line
     *
     * @param msg The message to print to the user
     * @return Nothing.
     */
    public static void println(String msg) {
        System.out.println(msg);
    }

    /*
     * This method receives a message and displays it on the same line
     *
     * @param msg The message to print to the user
     * @return Nothing.
     */
    public static void print(String msg) {
        System.out.print(msg);
    }

    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static Scanner getScanner() {
        return scanner;
    }
}
