package org.practice;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

public class Main {


    /**
     * Prints the contents of a file line by line.
     * @param args command line arguments, ignored
     */
    public static void main(String[] args) {

        // https://chatgpt.com/share/670023d6-7fd0-8000-8bd2-0f4bf607ecf0
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\sampada\\IdeaProjects\\Practice\\src\\main\\resources\\test.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }


    }
}


