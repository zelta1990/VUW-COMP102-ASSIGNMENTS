// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 102 Assignment 8 
 * Name:Xiaoshi Xie
 * Usercode:
 * ID:300366345
 */

import ecs100.*;
import java.util.*;
import java.io.*;

/** Reads a genealogy database from a file, and allows the user
to display information about people in the database.
Each line of the file (except the first) contains information
about one person:
- their name
- the year of their birth
- the name of their mother (or ? if the mother is unknown)
- the name of their father (or ? if the father is unknown)

The program will read the data into an list of Person objects.

The program then allows the user to print out
- the names of all the people in the database
(note, names are just the first name - no spaces)
- date of birth of a given person
- parents of a person (if known) and their dates of birth
- the number of (known) children of a person and all their names
and dates of birth.

 */

public class Genealogy{
    // Fields
    private ArrayList<Person> db;  // list of Person objects recording the parents of a person
    private Person currentPerson;  // The person currently specified by the user.

    /** Construct a new Genealogy object
     * and set up the GUI
     */
    public Genealogy(){
        UI.addButton("Load DB", this::doLoad);
        UI.addButton("All Names", this::printAllNames);
        UI.addTextField("Name", this::doName);
        UI.addButton("Birth", this::printPerson);
        UI.addButton("Parents", this::printParents);
        UI.addButton("Children", this::printChildren);
        UI.addButton("GrandChildren", this::grandChildren);
        UI.addButton("Clear", UI::clearText);
        UI.addButton("Quit", UI::quit);

        //this.loadDatabase("database.txt");
    }

    /**
     * Looks up the name in the database, and stores the Person
     * in the currentPerson field, if there is such a person.
     */
    public void doName(String value){
        Person p = this.getPerson(value);
        if (p==null){
            UI.println(value + " not in database");
        }
        else {
            currentPerson = p;
            UI.printf("found %s in database\n", value);
            UI.println("----------------------");
        }
    }

    /** Load the database */
    public void doLoad(){
        this.loadDatabase(UIFileChooser.open("Choose database file"));
    }

    /**
     * Reads the data from the database file into an ArrayList
     * Reads the data on each line andconstructs a Person object,
     *  and puts the Person object into list
     * The method may assume that the database is correctly formatted,
     *  and does not need to do any checking of the input.
     */
    public void loadDatabase(String filename){
        UI.printf("Reading Database from %s ....\n", filename);
        /*# YOUR CODE HERE */
        try{
            this.db = new ArrayList<Person>();
            File file=new File(filename);
            Scanner sc= new Scanner(file);
            while(sc.hasNext()){

                String n=sc.next();
                String g=sc.next();
                int dob=sc.nextInt();
                String m=sc.next();
                String f=sc.next();
                Person p= new Person(n,g,dob,m,f);
                this.db.add(p);             
            }
            sc.close();
            UI.println("Database Loaded");
        }
        catch(IOException e){ UI.println("Fail: "+e);}
    }

    /**
     * Print out names of all the people in the database
     */
    public void printAllNames(){
        UI.println("All names:");
        /*# YOUR CODE HERE */
        if(!db.isEmpty()){
            for(int i=0;i<db.size();i++){
                UI.println(db.get(i).getName());
            }
        }
        UI.println("----------------------");
    }

    /**
     * Looks for and returns the Person with the given name in the database.
     * returns null if not found
     */
    public Person getPerson(String name){
        /*# YOUR CODE HERE */
        Person value=null;
        if(!db.isEmpty()){
            for(int i=0;i<db.size();i++){
                if(db.get(i).getName().equals(name)){
                    value=db.get(i);
                }
            }
        }return value;
    }

    /**
     * Prints the name and year of birth of the currently selected person.
     * If no current person, prints a message.
     *  [Note, the toString() method of the Person class returns a string
     * containing the name and year of birth of the person.]
     */
    public void printPerson(){
        /*# YOUR CODE HERE */
        if(currentPerson!=null){
            UI.println(this.currentPerson.toString());
        }else{
            currentPerson=null;
            UI.println("Not found in the database");
        }
        UI.println("----------------------");
    }

    /**
     * Prints the names of the mother and the father if they are known
     * (or appropriate messages if they are unknown).
     */
    public void printParents(){
        /*# YOUR CODE HERE */
        if(currentPerson!=null){
            String m=currentPerson.getMotherName();
            String f=currentPerson.getFatherName();
            if(m!=null){
                if(f!=null){
                    m=m;
                    f=f;
                }else{
                    m=m;
                    f="unknown";
                }
            }
            else{
                if(f!=null){
                    m="unknown";
                    f=f;
                }else{
                    m="unknown";
                    f="unknown";
                }
            }

            UI.println("Parents of "+currentPerson.getName());
            UI.println("mother "+m);
            UI.println("father "+f);
        }
        else{
            UI.println("Not found in database");
        }
        UI.println("----------------------");
    }

    /**
     * Prints the number of children of the current person,
     * followed by the names and years of birth all the children.
     * Searches the array for Persons who have the currently specified
     *  person as one of their parents.
     * Any such person is added to an ArrayList.
     * It then prints out the information from the array of children.
     */
    public void printChildren(){
        /*# YOUR CODE HERE */
        Person k=null;
        ArrayList<Person>kid = new ArrayList<Person>();
        String p=currentPerson.getName();
        int count=0;        
        if(currentPerson!=null){
            for(int i=0;i<db.size();i++){
                String m=db.get(i).getMotherName();
                String f=db.get(i).getFatherName();
                if((m!=null && m.equals(p))||(f!=null && f.equals(p))){
                    k=db.get(i);
                    kid.add(k);
                    count++;
                }

            }UI.println(currentPerson.getName()+" has "+count+" children");
            for(int n=0;n<kid.size();n++){
                UI.println(kid.get(n).toString());
            }
        }else{
            UI.println(currentPerson+" has no children");
        }
    }

    /** COMPLETION:
     * Prints (to textArea) names of all grandchildren (if any) 
     *  of the currently specified person
     */
    public void grandChildren(){
        /*# YOUR CODE HERE */
        Person gk=null;
        Person k=null;
        ArrayList<Person>gkid=new ArrayList<Person>();
        ArrayList<Person>kid = new ArrayList<Person>();
        String p=currentPerson.getName();
        int count=0;
        if(currentPerson!=null){
            for(int i=0;i<db.size();i++){
                String m=db.get(i).getMotherName();
                String f=db.get(i).getFatherName();
                
                if((m!=null && m.equals(p))||(f!=null && f.equals(p))){
                    k=db.get(i);
                    kid.add(k);
                }
            }
            for(int i=0;i<db.size();i++){
                String gm=db.get(i).getMotherName();
                String gf=db.get(i).getFatherName();
                if((gm!=null &&gm.equals(k.getName())||(gf!=null&&gf.equals(k.getName())))){
                    gk=db.get(i);
                    gkid.add(gk);
                    count++;
                }

            }
        }
        UI.println(currentPerson.getName()+" has "+count+" grandchildren");
        for(int n=0;n<gkid.size();n++){
            UI.println(gkid.get(n).toString());
        }
        UI.println("----------------------");
    }
    


    // Main
    public static void main(String[] arguments){
        Genealogy g = new Genealogy();
    }   

}
