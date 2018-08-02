// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 7 
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/** Three methods that use arrays
 *
 * plotNumbers():
 *      read numbers from file into an array of numbers
 *      then access values.
 *  Uses an array. 
 *
 * readAndReverse():
 *      read tokens from file into an array of Strings
 *      then access values in reverse order
 *  Uses an array. 
 *
 * animalCrowd():
 *      read values from file,
 *      create an array of objects,
 *      call methods on objects
 *  Uses an array. 
 *
 */

public class Exercise{

    /**
     * Constructor to set up an interface with buttons to call all the methods
     */
    public Exercise(){
        UI.addButton("Clear", UI::clearPanes);
        UI.addButton("Show file", this::showFile);
        UI.addButton("Plot Numbers", this::plotNumbers);
        UI.addButton("ReadAndReverse", this::readAndReverse);
        UI.addButton("AnimalCrowd (little)", ()->{ this.animalCrowd("little-crowd.txt"); } );
        UI.addButton("AnimalCrowd (big)", ()->{ this.animalCrowd("big-crowd.txt"); } );
        UI.addButton("Quit", UI::quit);
        UI.setWindowSize(1000,800);
    }

    /** 
     *  Reads a sequence of numbers from the file "numbers.txt" into an array,
     *  keeping track of how many numbers have been read.
     *  The first line contains an integer indicating the number of lines in the file
     *  Finds the number half way through the sequence of numbers
     *  [if there are count numbers, then the middle number is at index (count-1)/2 ]
     *  Then plots the numbers as a sequence of rectangles of width = 5 and
     *  height = the number being plotted,
     *  The color is
     *     green if the number is less than the middle number,
     *     black if it is equal to the middle number, 
     *     red otherwise.
     */
    public void plotNumbers(){
        double base = 450;
        UI.drawLine(0, base, 600, base); // draws the base line
        int count = 0;                   // the number of figures in the array

        try{
            Scanner sc = new Scanner(new File("numbers.txt"));
            int maxCount = sc.nextInt();
            double[] nums = new double[maxCount]; // the array of numbers
            /*# YOUR CODE HERE */

            sc.close();
            // plot the numbers, 
            /*# YOUR CODE HERE */

        } catch(IOException e){UI.println("Fail: " + e);}
    }    

    /** 
     * Asks the user for a file, and and reads the tokens from the file,
     * storing them in an array.
     * The first line contains an integer indicating how many token 
     *    there are in the file.
     * It prints out the number of tokens it read and then
     *   prints the tokens in reverse order.
     */
    public void readAndReverse(){
        int count = 0;                          // the number of tokens read so far.
        try{
            Scanner sc = new Scanner(new File(UIFileChooser.open()));
            /*# YOUR CODE HERE */

        } catch(IOException e){UI.println("File reading failed");}
    }

    /**
     * animalCrowd:
     * Parameter is the name of a file.
     *    Reads a file specifying a collection of Animals 
     * The first line contains an integer indicating how many Animals there are in the file
     * Should create an array of Animals of the correct size
     * Each of the following lines contains two strings (one for the animal type and one for its name) 
     *    and an x and y value.
     * Using a while loop to read through the file
     * it should repeatedly
     *      read the four values on a line
     *      create a new Animal with those values
     *      store the Animal in the next place in the array
     * It should then, in turn,
     *   make each Animal go forward 30 units and jump 10 units, 
     *   then make each Animal introduce itself.
     */
    public void animalCrowd (String filename){
        UI.setColor(Color.black);
        try{
            Scanner sc = new Scanner(new File(filename));
            /*# YOUR CODE HERE */

        } catch(IOException e){UI.println("File reading failed");}
    }

    //=========================================================================
    /**
     * Utility method to list the contents of a file.
     */
    public void showFile(){
        String fname = UIFileChooser.open();
        UI.println("Contents of " + fname+":\n----------------");
        try{
            Scanner sc = new Scanner(new File(fname));
            while (sc.hasNextLine()) {
                UI.println(sc.nextLine());
            }
            sc.close();
            UI.println("--------------");
        } catch(IOException e){System.out.printf("Fail: %s\n", e);}
    }


    public static void main(String[] args){
        new Exercise();
    }        

}
