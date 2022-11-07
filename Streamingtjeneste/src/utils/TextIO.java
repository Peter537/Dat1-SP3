package utils;

import media.IMedia;

import java.util.ArrayList;
import java.util.Scanner;

public class TextIO {

    private static final Scanner scanner = new Scanner(System.in);

    public TextIO() { }
    
    /*
     * This method receives a message and displays it to the user and returns the input.
     *
     * @param msg The message to print to the user
     * @return String The input from the user
     */
    public String getUserInput(String msg){
        print(msg);
        return scanner.nextLine();
    }

    /*
     * This method receives a message and displays it to the user and displays some options to the user.
     *
     * @param msg The message to print to the user
     * @param options The options to display to the user
     * @return String The input from the user
     */
    public String getUserInput(String msg, String[] options) {
        println(msg);
        for (int i = 0; i < options.length; i++) {
            println((i) + ". " + options[i]);
        }
        return scanner.nextLine();
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
    public String getUserInputFromMedia(String msg, int page, int pageSize, ArrayList<IMedia> media) {
        println(msg);
        println("-2 - Previous page");
        println("-1 - Next page");
        println("0 - Exit");
        for (int i = 0; i < media.size(); i++) {
            println((i + 1 + ((page - 1) * pageSize)) + " - " + media.get(i).getTitle());
        }
        return scanner.nextLine();
    }

    /*
     * This method receives a message and displays it on a new line
     *
     * @param msg The message to print to the user
     * @return Nothing.
     */
    public void println(String msg) {
        System.out.println(msg);
    }

    /*
     * This method receives a message and displays it on the same line
     *
     * @param msg The message to print to the user
     * @return Nothing.
     */
    public void print(String msg) {
        System.out.print(msg);
    }
}
