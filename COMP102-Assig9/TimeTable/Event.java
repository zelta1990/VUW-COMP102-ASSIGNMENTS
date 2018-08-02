// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 102 Assignment 9
 * Name:
 * Usercode:
 * ID:
 */

/** Represents a event.
 * This is a very simple class.  It needs
 * - two fields (Strings) for storing the description and location
 * - a constructor (which is passed a description and a location
 * - two methods (getDesc() and getLoc()) to return the description and location
 */
public class Event{

    /*# YOUR CODE HERE */
    private String desc;
    private String loc;

    public Event(String d,String l){
        this.desc=d;
        this.loc=l;
    }

    public String getDesc(){
        return this.desc;
    }

    public String getLoc(){
        return this.loc;
    }
}