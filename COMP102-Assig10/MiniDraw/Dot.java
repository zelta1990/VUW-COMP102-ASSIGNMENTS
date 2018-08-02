// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 10
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.awt.Color;
import java.io.*;

/** Dot represents a small circle shape of a fixed size (5 pixels)
 *   Implements the Shape interface.
 *   Needs fields to record the position, and colour
 */

/*# YOUR CODE HERE */
public class Dot implements Shape {
    private double x;  // one end
    private double y;
    private final int pixelSize=5;
    private Color col;
    public Dot(double x, double y,Color col) {
        this.x=x;
        this.y=y;
        this.col=col;

    }
     public Dot(String description) {
        /*# YOUR CODE HERE */
        Scanner data = new Scanner(description);
        int red = data.nextInt();
        int green = data.nextInt();
        int blue = data.nextInt();
        this.col = new Color(red, green, blue);
        this.x = data.nextDouble();
        this.y = data.nextDouble();
        
    }
     public boolean on(double u, double v){
        // an easy approximation is to pretend it is the enclosing rectangle.
        // It is nicer to do a little bit of geometry and get it right
        /*# YOUR CODE HERE */
        return false;
    }
    public void moveBy(double dx, double dy){
        /*# YOUR CODE HERE */

    }
    public void redraw(){
        /*# YOUR CODE HERE */

    }
       public void resize(double changeWd, double changeHt){
        /*# YOUR CODE HERE */

    }
     public String toString(){
        /*# YOUR CODE HERE */
       return "";
    }
}
