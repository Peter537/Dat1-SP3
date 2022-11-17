package main.utils;

import main.media.IMedia;

import java.util.ArrayList;
import java.util.Scanner;

public class TextIO {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     *
     *
     * @param msg
     * @return String
     */
    public static String getUserInput(String msg){
        print(msg);
        return getScanner().nextLine();
    }

    /**
     *
     *
     * @param msg
     * @param options
     * @return String
     */
    public static String getUserInput(String msg, String[] options) {
        println(msg);
        for (int i = 0; i < options.length; i++) {
            println((i) + ". " + options[i]);
        }
        return getScanner().nextLine();
    }

    /**
     *
     *
     * @param msg
     * @param startIndex
     * @param options
     * @return String
     */
    public static String getUserInput(String msg, int startIndex, String[] options) {
        println(msg);
        for (int i = 0; i < options.length; i++) {
            println((i + startIndex) + ". " + options[i]);
        }
        return getScanner().nextLine();
    }

    /**
     *
     *
     * @param msg
     * @param page
     * @param pageSize
     * @param media
     * @return String
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
     *
     *
     * @param msg
     */
    public static void println(String msg) {
        System.out.println(msg);
    }

    /**
     *
     *
     * @param msg
     */
    public static void print(String msg) {
        System.out.print(msg);
    }

    /**
     *
     */
    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static Scanner getScanner() {
        return scanner;
    }
}
