// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 8
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.io.*;

/**
 *  Creates an array that holds Person objects, with data read from the
 *   file "family.txt".
 *  Then allows the user to ask four questions about the people:
 *    What are their names
 *    Who is the mother of Catherine
 *    Who is the mother of Michael
 *    Who are the children of Michael
 *   Note: The family tree represented in the file has five people:
 *    Carole and Michael are the parents of Catherine, Philippa and James.
 *    Carole and Michael's parents are unknown.
 */

public class PersonExercise{

    ArrayList<Person> data = new ArrayList<Person>();

    /**
     * Add Buttons to the UI
     */
    public PersonExercise(){
        UI.addButton("Load DB", this::doLoad);
        UI.addButton("All Names", this::allNames);
        UI.addButton("Mother of Catherine", this::doMother1);
        UI.addButton("Mother of Michael", this::doMother2);
        UI.addButton("Children of Michael", this::doChildren);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(1); // expand Text pane
    }

    public void doMother1(){
        this.findMotherOf("Catherine");
    }

    public void doMother2(){
        this.findMotherOf("Michael");
    }

    public void doChildren(){
        this.findChildrenOfFather("Michael");
    }

    /**
     * Read each line of the file, (name, gender, dob, mother's name, father's name)
     *  constructing a Person object for the line
     *  and putting it in the ArrayList
     * Close the file and print "Loaded" when finished.
     */
    public void doLoad(){
        try{
            Scanner scan = new Scanner(new File("family.txt"));
            this.data.clear();
            while (scan.hasNext()){
                /*# YOUR CODE HERE */

            }
            scan.close();
            UI.println("Loaded");
        }
        catch(IOException e){UI.println("Error while reading database file");}


    }

    /**
     * Print out the name, gender, and year of birth of each person in the array.
     * Note, the Person class has a toString method that is useful.
     */
    public void allNames(){
        /*# YOUR CODE HERE */

    }
    /**
     * Steps through the list to find a person with the specified name
     * Then prints out that person's mother, if known
     * otherwise print out "Mother unknown"
     */
    public void findMotherOf(String name){
        /*# YOUR CODE HERE */

    }

    /**
     * Steps through the list to find each person whose father is the specified name
     * Prints out the name of each such person
     */
    public void findChildrenOfFather(String name){
        /*# YOUR CODE HERE */

    }

    public static void main(String[] args){
        new PersonExercise();
    }

}
