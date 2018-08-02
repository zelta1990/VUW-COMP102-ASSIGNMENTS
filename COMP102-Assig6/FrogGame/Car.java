// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

import java.awt.Color;

import ecs100.UI;

/**
 * Car:
 * A car drives along one lane of the road, (lane 1, 2, 3, ... )
 * It must keep track of its x position. 
 * Its y position is determined by the lane it is in and constants
 * from the FrogGame class:
 *    y is  FrogGame.START_LINE - lane * FrogGame.LANE_WIDTH
 * 
 * Each car has a direction - going to the left or going to the right.
 * When a car gets to the end of the road it disappears and immediately
 * reappears at the other end, still moving in the same direction.
 * 
 * The ends of the road are defined by the constants:  FrogGame.ROAD_LEFT or FrogGame.ROAD_RIGHT.
 * All cars move at the same speed.
 */

public class Car {

    // Constants  (size of the car)
    public static final int WIDTH = 50;
    public static final int HEIGHT = 30;
    public static final int SPEED = 3;     // pixels to move each time move() is called

    // Fields
    /*# YOUR CODE HERE */
    private int lane;
    private double x;
    private double y;
    private String direction;
    private double leftEnd=FrogGame.ROAD_LEFT;
    private double rightEnd=FrogGame.ROAD_RIGHT;

    // Constructor
    /**
     * Make a new car, specifying the direction, the lane,
     * and the x position in the lane
     */
    public Car(String dir, int lane,  double x ){
        /*# YOUR CODE HERE */
        this.direction=dir;
        this.lane=lane;
        this.x=x;
        this.draw();
    }

    /**
     * Move the car according to its speed and direction:  erase, change position, redraw
     * If it goes past the end of the road, move it to the other end.
     * (if moving to the right, the end of the road is FrogGame.ROAD_RIGHT,
     *  if moving to the left, the end of the road is FrogGame.ROAD_LEFT.
     */
    public void move(){
        /*# YOUR CODE HERE */
        this.erase();
        if(direction.equals("right")){
            if (this.x<this.rightEnd){
                this.x=this.x+SPEED;
            }else if(this.x>=rightEnd){
                this.x=this.leftEnd;
            }
        }
        if(direction.equals("left")){
            if(this.x>this.leftEnd){
                this.x=this.x-SPEED;
            }else if(this.x<=leftEnd){
                this.x=this.rightEnd;
            }
        }
        this.draw();

    }
    /**
     * Return the lane the car is in
     */
    public int getLane(){
        /*# YOUR CODE HERE */

        //Note, this method needs to return an int in order to compile
        return this.lane;  // this is WRONG - it is just to make Car compile.
    }

    /**
     * Return the x position of the car
     */
    public double getX(){
        /*# YOUR CODE HERE */

        //Note, this method needs to return an int in order to compile
        return this.x;  // THIS IS WRONG, it is just here to make it compile
    }

    /**
     * Draw the car at its current position.
     *  The roof portion should be a darker version of the colour of the body.
     */
    public void draw(){
        /*# YOUR CODE HERE */
        this.y=FrogGame.START_LINE - lane * FrogGame.LANE_WIDTH;
        Color c=new Color(0,180,255);
        UI.setColor(c);
        UI.fillRect(this.x,this.y,WIDTH,HEIGHT);
        Color d=new Color(0,136,200);
        UI.setColor(d);
        UI.fillRect(this.x+10,this.y+5,WIDTH-20,HEIGHT-10);
    }

    /**
     * Erase the car from its current position
     */
    public void erase(){
        /*# YOUR CODE HERE */
        UI.eraseRect(this.x,this.y,WIDTH,HEIGHT);
    }

}
