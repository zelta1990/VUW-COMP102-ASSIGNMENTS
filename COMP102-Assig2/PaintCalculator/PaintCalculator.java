// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 102 Assignment 2 
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;

/** Program for calculating amount of paint required to paint a room */

public class PaintCalculator{

    public static final double doorHeight = 2.1;        // Height of the doors
    public static final double doorWidth = 0.8;         // Width of the doors
    public static final double SqMetersPerLitre = 15;   // Area covered by 1 litres of paint


    /** Constructor, sets up the user interface */
    public PaintCalculator(){
        UI.initialise();
        UI.addButton("Core", this::calculatePaintCore ); 
        UI.addButton("Completion", this::calculatePaintCompletion );
        UI.addButton("Quit", UI::quit);
        UI.setDivider(1);    // Expand the Text pane
    }

    /** Calculates and prints litres of paint needed to paint a room
        with four walls (excluding the doors, floor, and ceiling)
     */
    public void calculatePaintCore(){
        double l=UI.askDouble("What's the length of your room(in meters):");
        double w=UI.askDouble("What's the width of your room(in meters):");
        double h=UI.askDouble("What's the height of your room(in meters):");
        int n=UI.askInt("How many doors do you have in your room(in meters):");
        double v=(l*h*2+w*h*2-0.8*2.1*n)/15;
        UI.println("You need"+v+"litres of paint for your room.");
       
    }

    /** Calculates and prints litres of paint needed to paint a room with four walls
        (excluding the doors and windows), and windows and ceiling separately.
     */
    public void calculatePaintCompletion(){
        /*# YOUR CODE HERE */
        double l=UI.askDouble("What's the length of your room(in meters):");
        double w=UI.askDouble("What's the width of your room(in meters):");
        double h=UI.askDouble("What's the height of your room(in meters):");
        int n=UI.askInt("How many doors do you have in your room(in meters):");
        int m=UI.askInt("How many windows do you have in your room:");
        double a=UI.askDouble("What's the width of your windows(in meters):"); 
        double b=UI.askDouble("What's the height of your windows(in meters):");
        double v1=(l*h*2+w*h*2-0.8*2.1*n-m*a*b)/15;
        double v2=l*w/15;
        UI.println("You need"+v1+"litres of paint for the walls of your room.");
        UI.println("You need"+v2+"litres of paint for the ceiling of your room.");

    }



}
