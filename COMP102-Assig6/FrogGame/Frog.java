// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.awt.Color;
import java.util.*;
import java.io.*;

/** Frog
 * A new frog starts at the starting line, in lane 0.
 * It can then hop up to the next lane, or hop back to the previous lane.
 * 
 * The frog must keep track of what lane it is in.
 * It cannot hop back from lane 0.
 *
 * If the frog goes splat, it isn't alive any more, and can't hop.
 * It must keep track of whether it is alive or not.
 *
 * Its x position is given the constant FrogGame.FROG_X
 * Its y position is determined by the lane it is in and constants
 * from the FrogGame class:
 *    y =  FrogGame.START_LINE - lane * FrogGame.LANE_WIDTH
 *
 */

public class Frog {
    // Constants
    public static final int SIZE = 40;

    // Fields

    /*# YOUR CODE HERE */
    private int lane;
    private boolean isAlive;
    private double x=FrogGame.FROG_X;//currext x position of frog
    private double y;//current y position of frog

    /**
     * Make a new frog and draw it.
     */
    public Frog(){
        /*# YOUR CODE HERE */

        this.isAlive=true;
        this.lane=0;
        this.y=FrogGame.START_LINE - lane * FrogGame.LANE_WIDTH;
        this.draw();

    }

    /**
     * Make the frog hop forward: erase, change to the next lane, redraw
     * If the frog is dead it can't move
     * For a nicer version, make the hop in three stages:
     * make the frog spread out its legs, then move, then crouch down again.
     */
    public void hop(){
        /*# YOUR CODE HERE */ 
        this.erase();
        if(isAlive){
            this.lane=this.lane+1;
            this.y=FrogGame.START_LINE - lane * FrogGame.LANE_WIDTH;
            this.draw();
        }

    }

    /**
     * Make the frog hop backward: erase, change position, redraw
     * If the frog is dead it can't move.
     * It cannot hop back from lane 0
     * For a nicer version, make the hop in three stages:
     * make the frog spread out its legs, then move, then crouch down again.
     */
    public void hopBack(){
        /*# YOUR CODE HERE */

        this.erase();
        if(isAlive){
            if(lane>0){
                this.lane=this.lane-1;
                this.y=FrogGame.START_LINE - lane * FrogGame.LANE_WIDTH;
                this.draw();
            }else{
                this.draw();
            }

        }
    }

    /**
     * The Frog has just been hit by a car,
     * Makes the frog be in the dead state.
     * Redraws the frog.
     */
    public void splat(){
        /*# YOUR CODE HERE */
        this.isAlive=false;
        UI.drawImage("splat.jpg",x,y,SIZE,SIZE);

    }

    /**
     * Return the lane the frog is currently in
     */
    public int getLane(){
        /*# YOUR CODE HERE */    
        return this.lane;  //remove this line (it is just to allow the template to compile)
    }

    /**
     * Return true if the frog is alive, and false if it is not
     */
    public boolean isAlive(){
        /*# YOUR CODE HERE */
        if(isAlive){
            return true;
        }else{
            return false;
        }//remove this line (it is just to allow the template to compile)
    }

    /**
     * Draws the frog at the current position.
     * Works out x and y as described in the comment at the top of the file
     * If it is not alive, draws a splat.
     */
    public void draw(){
        /*# YOUR CODE HERE */
        if(isAlive){
            UI.drawImage("littlefrog.jpg",this.x,this.y,SIZE,SIZE);
        }
        else{
            UI.drawImage("splat.jpg",this.x,this.y,SIZE,SIZE);
        }

    }

    /**
     * Erase the frog
     * Works out x and y as described in the comment at the top of the file
     */

    public void erase(){
        /*# YOUR CODE HERE */
        UI.eraseImage("littlefrog.jpg",this.x,this.y);

    }
}
