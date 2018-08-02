// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 3
 * Name:Xiaoshi Xie (coworked with Niharika Konduri)
 * Usercode:
 * ID:300366345
 */

import ecs100.*;
import java.awt.Color;
import java.util.*;
import java.io.*;

/** BusinessReminders: Writes a series of reminders, each one tailored to the recipient  */
public class BusinessReminders{

    /** Constructor: set up user interface */
    public BusinessReminders(){
        UI.initialise();
        UI.addButton("Clear", UI::clearText );
        UI.addButton("Core: Vet", this::vetReminder );
        UI.addButton("Completion: WOF", this::wofReminder );
        UI.addButton("Quit", UI::quit );
        UI.setDivider(1);
    }

    /**
     * Write out a series of at least five reminders to pet owners.
     * Each reminder should 
     * - be addressed to a different person
     * - refer to their pet's name and the type of animal several time
     * - indicate the price of the visit.
     * vetReminder should repeatedly call a method which sends a reminder to a single pet owner,
     * passing different arguments to the method each time.
     */
    public void vetReminder(){
        /*# YOUR CODE HERE */
        sendToPetOwner("John","dog","Charlie",95.25);
        sendToPetOwner("Angela","cat","Tiger",55.55);
        sendToPetOwner("Christina","dog","Bella",80.70);
        sendToPetOwner("Olivia","cat","Sammy",55.55);
        sendToPetOwner("Tom","cat","Toby",55.55);
    }
    public void sendToPetOwner(String ownerName,String petType,String petName,double price){
        UI.println("Dear "+ownerName+",");
        UI.println();
        UI.println("   Our records show that your "+petType+" "+petName+" is due for an annual check up and booster vaccination.");
        UI.println("This annual booster vaccination is necessary to ensure "+petName+" is protected against a range of potentially fatal diseases.");
        UI.printf("The fees for an annual checkup for your "+petType+" is "+"$%.2f.",price);
        UI.println();
        UI.println("To encourage you to bring "+petName+" in for vaccination, we are offering FREE worm tablets with a vaccination.");
        UI.println("We look forward to seeing you soon,");
        UI.println();
        UI.println("from the team at the Happy Pets Vet Clinics");
        UI.println();
        

    }

    /**
     * Write out a series of at least five Warrant of fitness reminders.
     * Each reminder should
     * - be addressed to a different person using both their full name and their first name
     * - refer to the car type (several times), registration, and the year the vehicle was
     *   first registered
     * - remind the owner the number of months the warrant of fitness will be issued for
     *   (6 or 12) and the warrant due date
     * - recommend another service indicating its price
     * - offer a 10% discount of the total cost if the owner decides to book for both
     *   the warrant of fitness and the extra service
     * - indicate the total cost after discount (with 2 decimal places).
     */
    public void wofReminder(){
        /*# YOUR CODE HERE */
        writeWofReminder("Mark Andrews","2010 SINNAN COUPE(PPL 123)",12,"1 August","Sinnan Coupe","super premium",284.00,287.10,"Mark");
        writeWofReminder("Sarah Ford","2012 KIZUSU SPEEDY(CAR 345)",12,"5 August","Kizusu Speedy","standard services",172.00,186.30,"Sarah");
        writeWofReminder("John Evans","1995 BISHI RATERA(MRD 666)",6,"6 August","Bishi Ratera","Cam Belts",755.00,711.00,"John");
        writeWofReminder("Rebecca Jaynes","1999 TATATA BOROLLA(ETT 987)",6,"7 August","Tatata Borolla","Air Conditioning regas",215.00,225.00,"Rebecca");
        writeWofReminder("Daniel Wang","2011 HUNGWAI ARRAS(ZAS 135)",12,"7 August","Hungwai Arras","new tyres",50.00,76.50,"Daniel");
    }
    public void writeWofReminder(String ownerName,String carType,int month,String regDate,String carName,String service,double price, double cost,String firstName){
        UI.println("Dear "+ownerName+",");
        UI.println();
        UI.println("  According to our records,your "+carType+" is due for a "+month+" monthly warrant of fitness on "+regDate+".");
        UI.println();
        UI.printf("Our records also show that your "+carName+" needs "+service+" costing "+"$%.2f.",price);
        UI.println();
        UI.println();
        UI.println("But, "+firstName+", if you decide to do both on the same day,");
        UI.printf(" your "+service+" and the warrant of fitness for your Tatata Borolla will only cost "+"$%.2f.",cost);
        UI.println();
        UI.println();
        UI.println("Please phone 1234567 to book your car in.");
        UI.println();
        UI.println();
        
    }


}
