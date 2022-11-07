package utils;

import media.IMedia;
import media.IMovie;
import media.ISeries;

import java.util.ArrayList;
import java.util.Scanner;

public class TextIO {

    // Comments will be changed later. These were the comments from our "Matador" project.

    private static final Scanner scanner = new Scanner(System.in);

    public TextIO() { }
    
    /*
        receives a message and displays it to the user
        prompts the user for one input value
        returns the input
    */
    public String getUserInput(String msg){
        print(msg);
        return scanner.nextLine();
    }

    /*
        receives a message and displays it to the user
        and displays some options to the user
    */
    public String getUserInput(String message, String[] optionsList) {
        println(message);
        for (int i = 0; i < optionsList.length; i++) {
            println((i) + ". " + optionsList[i]);
        }
        return scanner.nextLine();
    }

    public String getUserInput(String message, int startIndex, String[] optionsList) {
        println(message);
        for (int i = 0; i < optionsList.length; i++) {
            println((startIndex + i) + ". " + optionsList[i]);
        }
        return scanner.nextLine();
    }

    public String getUserInputFromMedia(String message, int page, ArrayList<IMedia> media) {
        println(message);
        println("-2 - Previous page");
        println("-1 - Next page");
        println("0 - Exit");
        for (int i = 0; i < media.size(); i++) {
            println((i + 1 + ((page - 1) * 10)) + " - " + media.get(i).getTitle());
        }
        return scanner.nextLine();
    }

    /*
        receives a message and displays it to the user
        displays a list of options
        prompts the user for one input value
        returns the input
    */
    public int getUserInput(String msg, ArrayList<String> arr){
        println(msg);
        for (int i = 0; i < arr.size(); i++) {
            println((i + 1) + ". " + arr.get(i));
        }
        return scanner.nextInt();
    }

    public void println(String msg) {
        System.out.println(msg);
    }

    public void print(String msg) {
        System.out.print(msg);
    }
}
