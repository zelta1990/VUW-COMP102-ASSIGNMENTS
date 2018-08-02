// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 2
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.awt.Color;

/**
 * Draws flags of various countries.
 * The correct dimensions of the official flags varies from country to country,
 * But you may assume that they are 2/3 as high as they are wide.
 * The exact colours of the flags will also be difficult to match;
 * It is fine to use the standard colours: red, green, blue, orange, etc
 * You can find lots of flag details (including the correct dimensions and colours)
 * from   http://www.crwflags.com/fotw/flags/    
 */
public class FlagDrawer{

    public static final double top = 100;
    public static final double left = 50;

    /** Constructor, sets up the user interface */
    public FlagDrawer(){
        UI.initialise();
        UI.addButton("Clear", UI::clearGraphics);

        UI.addButton("Core: Uganda", this::ugandaFlag);
        UI.addButton("Core: Norway", this::norwayFlag);
        UI.addButton("Core: Maldives", this::maldivesFlag);

        //COMPLETION
        UI.addButton("Completion: Greenland", this::greenlandFlag);
        UI.addButton("Completion: Pacman", this::pacmanFlag);

        //CHALLENGE
        UI.addButton("Challenge: Basque", this::basqueFlag);
        UI.addButton("Challenge: Jamaica", this::jamaicaFlag);
        UI.addButton("Quit", UI::quit);
    }

    /** The flag for Uganda consists of 6 strips (black, yellow, red, black, yellow, red)
     *  with a white circle in the center.
     *  CORE
     */
    public void ugandaFlag() {
        /*# YOUR CODE HERE */
        double w=UI.askDouble("What's the width of the flag:");
        UI.setColor(Color.black);     //set the color for the first rect;
        UI.fillRect(left,top,w,w/9);   //draw the first rect;
        UI.setColor(Color.yellow);//set the color for the second rect;
        UI.fillRect(left,top+w/9,w,w/9);//draw the second rect;
        UI.setColor(Color.red);//set the color for the third rect;
        UI.fillRect(left,top+w*2/9,w,w/9);//draw the third rect;
        UI.setColor(Color.black);//set the color for the fourth rect;
        UI.fillRect(left,top+w*3/9,w,w/9);//draw the fourth rect;
        UI.setColor(Color.yellow);//set the color for the fifth rect;
        UI.fillRect(left,top+w*4/9,w,w/9);//draw the fifth rect;
        UI.setColor(Color.red);//set the color for the sixth rect;
        UI.fillRect(left,top+w*5/9,w,w/9);//draw the sixth rect;
        UI.setColor(Color.white);//set the color for the circle;
        UI.fillOval(left+(w/2-w/9),top+w*2/9,w*2/9,w*2/9);//draw the circle;
        
        
        
        
        
        

    } 

    /** The flag for Norway is a red rectangle with
     *  a blue cross with a white border, slightly off-set to the left-hand side;
     *  CORE
     */
    public void norwayFlag() {
        /*# YOUR CODE HERE */
        double w=UI.askDouble("What's the width of the flag:");
        UI.setColor(Color.red);//set the color for the largest rect;
        UI.fillRect(left,top,w,2*w/3);//draw the largest rect;
        UI.setColor(Color.white);//set color for the vertical white stripe;
        UI.fillRect(left+w/4,top,w/4-w/20,2*w/3);//draw the vertical white stripe;
        UI.fillRect(left,top+w/3-w/10,w,w/5);//draw the horizontal white stripe;
        UI.setColor(Color.blue);//set color for the smaller vertical stripe;
        UI.fillRect(left+3*w/10,top,w/10,2*w/3);//draw the smaller vertical stripe;
        UI.fillRect(left,top+w/3-w/20,w,w/10);//draw the smaller horizontal stripe;
    }

    /** The flag for Maldives is a red rectangle with
     *  a smaller green rectangle in the middle and a white crescent in the center;
     *  CORE
     */
    public void maldivesFlag() {
        /*# YOUR CODE HERE */
        double w=UI.askDouble("What's the width of the flag:");
        UI.setColor(Color.red.darker());//set color for the largest rect;
        UI.fillRect(left,top,w,2*w/3);//draw the largest rect;
        UI.setColor(Color.green.darker());//set color for the central rect;
        UI.fillRect(left+w/6,top+w/6,w-w/3,w/3);//draw the central rect;
        UI.setColor(Color.white);//set color for the crescent;
        UI.fillOval(left+7*w/18,top+2*w/9,2*w/9,2*w/9);//draw the larger oval;
        UI.setColor(Color.green.darker());//set color for the smaller oval;
        UI.fillOval(left+4*w/9,top+2*w/9,2*w/9,2*w/9);//draw the smaller oval;
      

    }       

    /** The flag for Greenland is a rectangle whose top half is white
     *  and bottom half is red. There is a circle in the middle (off-set to left) 
     *  which is also half white/red but on the opposite sides.
     *  COMPLETION
     */
    public void greenlandFlag() {
        // involves drawing quite a lot of rectangles!
        /*# YOUR CODE HERE */
        double w=UI.askDouble("What's the width of the flag:");
        UI.setColor(Color.red);//set color for the lower rect;
        UI.fillRect(left,top+w/3,w,w/3);//draw the lower rect;
        UI.fillArc(left+w/5,top+2*w/15,2*w/5,2*w/5,0,180);
        UI.setColor(Color.white);//set color for the lower arc
        UI.fillArc(left+w/5,top+2*w/15,2*w/5,2*w/5,0,-180);//draw the lower arc;
        UI.setColor(Color.black);//set color for the upper rect;
        UI.drawRect(left,top,w,2*w/3);//draw the upper rect;
        

    }

    /** Pacman
     *  A red pacman facing left on a black background chasing green, yellow and blue dots.
     *  From left to right the dots are blue, yellow, and green
     *  COMPLETION
     */
    public  void pacmanFlag() {
        /*# YOUR CODE HERE */
        double w=UI.askDouble("What's the width of the flag:");
        UI.setColor(Color.black);//set color for the large rect;
        UI.fillRect(left,top,w,w/2);//draw the large rect;
        UI.setColor(Color.red);//set color for the pacman;
        UI.fillArc(left+3*w/5,top+w/8,w/4,w/4,198,318);//draw the pacman;
        UI.setColor(Color.green);//set color for the third pac-dot;
        UI.fillOval(left+w/2,top+w/4-w/40,w/20,w/20);//draw the third pac-dot;
        UI.setColor(Color.yellow);//set color for the second pac-dot;
        UI.fillOval(left+w/2-w/6,top+w/4-w/40,w/20,w/20);//draw the second pac-dot;
        UI.setColor(Color.blue);//set color for the first pac-dot;
        UI.fillOval(left+w/2-w/3,top+w/4-w/40,w/20,w/20);//draw the first pac-dot;

    }

    /** The flag for the Basque Country is a red rectangle with
     *  a green diagonal cross and a white cross.
     *  CHALLENGE
     */
    public void basqueFlag() {
        /*# YOUR CODE HERE */
        double w=UI.askDouble("What's the width of the flag:");
        UI.setColor(Color.red);//set color for the largest rect;
        UI.fillRect(left,top,w,2*w/3);//draw the largest rect;
        UI.setColor(Color.green);//set color for the polygons;
        //double p1=[
        //UI.fillPolygon(double x.points[]={left,left,left+2*w/21,left+w-2*w/21,left+w,left+w},double y.points[]={top+2*w/21,top,top,top+2*w/3,top+2*w/3,top+2*w/3-2*w/21},6);

    }

    /** The Jamaican flag has a yellow diagonal cross with 
     *  green triangles top and bottom, and black triangles left and right.
     *  There is no fillTriangle method in the UI class!
     *  CHALLENGE
     */
    public void jamaicaFlag(){
        /*# YOUR CODE HERE */

    }


}
