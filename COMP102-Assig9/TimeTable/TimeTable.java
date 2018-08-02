// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 102 Assignment 9
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/** This TimeTable program will allow the user to record and modify events
 *  for a weekly timetable
 *  The program will first display an empty grid of events.
 *  The user can then:
 *  - create a new timetable of events 
 *  - load a week of events from a file,
 *  - save events to a file,
 *  - use the mouse to select a cell (day/hour) 
 *  - add an event to the selected cell (if not currently full)
 *  - delete the event in the selected cell
 *  - ask the program to find a sequence of hours on the same day that are free.
 *
 * The timetable is represented by a (2D) array of Event objects, indexed
 *   by the day (0 .. DAYS-1) and the hour (MIN_HOUR .. MAX_HOUR) 
 *
 * A slot that has no event is represented by a null value in
 *   the corresponding element of the array.
 */

public class TimeTable {

    // Times:  the hour, between 8 and 17 (5pm)
    private static final int MIN_HOUR = 8;   // the earliest hour in the timetable
    private static final int MAX_HOUR = 17;  // the latest hour in the timetable

    private static final int DAYS = 7;
    private Event[][] events = new Event[DAYS][MAX_HOUR + 1];

    // Fields for the interface
    private int selectedHour = MIN_HOUR;
    private int selectedDay = 0;
    private String currentDesc;
    private String currentLoc;

    // layout of the grid
    private final int gridLeft = 80;    // left edge of the grid
    private final int gridTop = 40;     // top edge of the grid
    private final int cellWidth = 90;   // width of cells in the grid
    private final int cellHeight = 35;  // height of cells in the grid

    /** Construct a new TimeTable object
     *  set up the GUI (mouse, buttons)
     *  displays the timetable grid
     */
    public TimeTable(){
        UI.initialise();
        UI.setImmediateRepaint(false);
        UI.setMouseListener(this::doMouse );

        UI.addButton("New", this::doNew );
        UI.addButton("Load", this::doLoad );
        UI.addButton("Save", this::doSave );
        UI.addTextField("Description", (String v) -> this.currentDesc = v );
        UI.addTextField("Location", (String v) -> this.currentLoc = v );
        UI.addButton("Add", this::doAdd );
        UI.addButton("Delete", this::doDelete );
        UI.addButton("Search", this::doSearch );
        UI.addButton("Quit", UI::quit );
        this.redisplay();
    }

    /**
     * If the user clicks on a cell, it makes this cell be the selected cell:
     * Works out the day from the x position
     * Works out the hour from the y position
     * Checks that the day and hour are within bounds
     * Stores the day and hour in the appropriate fields
     * Redisplays the timetable
     */

    public void doMouse(String action, double x, double y) {
        if (action.equals("released")){
            int day =  (int)((x-this.gridLeft) / this.cellWidth);
            int hour = MIN_HOUR + (int)((y-this.gridTop) / this.cellHeight);
            if (day>=0 && day <DAYS && hour>=MIN_HOUR && hour <= MAX_HOUR){ 
                this.selectedHour = hour;
                this.selectedDay = day;
                this.redisplay();
            }
        }
    }

    /**
     * Create a new (empty) timetable
     */
    public void doNew(){
        this.events = new Event[DAYS][MAX_HOUR + 1];
        this.redisplay();
    }

    /**
     * Display the Event chart on the graphics pane
     *  Displays the days and hours in the margins
     *  Displays all the events
     *  Hilights the current day and hour
     *  (by displaying it with a different background colour)
     * NOTE: should use the displayCell method to draw each cell.
     */
    private void redisplay(){
        // display all the cells with the activities
        // redisplay (and highlight) the current cell
        UI.clearGraphics();
        this.displayTitles();
        /*# YOUR CODE HERE */
        for(int day=0;day<DAYS;day++){
            for(int hour=8;hour<MAX_HOUR+1;hour++){
                this.displayCell(day,hour,Color.white);                
            }
        }
        this.displayCell(selectedDay,selectedHour,Color.yellow);
        UI.repaintGraphics();
    }

    /** [Already completed for you]
     * Displays one cell (rectangular region of the grid) on the
     * graphics pane, along with any Event in it.
     * The first two arguments specify the day and the hour 
     * The third argument is the colour of the background. 
     */
    private void displayCell(int day, int hour, Color c){
        int x = this.gridLeft + day*this.cellWidth;   // left of cell
        int y = this.gridTop + (hour-MIN_HOUR)*this.cellHeight;  // top of cell
        UI.setColor(c);
        UI.fillRect(x, y, this.cellWidth, this.cellHeight);
        UI.setColor(Color.black);
        UI.drawRect(x, y, this.cellWidth, this.cellHeight);   // outline
        if ( this.events[day][hour] != null){
            UI.drawString(this.events[day][hour].getDesc(), x+2, y+this.cellHeight/2);
            UI.drawString(this.events[day][hour].getLoc(), x+2, y+this.cellHeight-2);
        }
    }

    /** Delete the event in the currently selected cell, then redisplay */
    public void doDelete() {
        /*# YOUR CODE HERE */
        this.events[selectedDay][selectedHour]=null;
        this.displayCell(selectedDay,selectedHour,Color.white);
        this.redisplay();
    }

    /**
     * Add an event in the selected cell, unless
     * - The cell already has an event or
     * - The user has not entered a description or location in the text fields
     *   in which case, it should display an appropriate message in the text pane
     * Redisplays the timetable.
     */
    public void doAdd() {
        /*# YOUR CODE HERE */
        if(this.events[this.selectedDay][this.selectedHour]!=null){
            UI.println("There is already an event");
        }
        if(currentDesc==null){
            UI.println("Please enter a description");
        }
        if(currentLoc==null){
            UI.println("Please enter a location");
        }
        Event addE=new Event(this.currentDesc,this.currentLoc);
        this.events[this.selectedDay][this.selectedHour]=addE; 
        // this.displayCell(selectedDay,selectedHour,Color.blue);
        this.redisplay();
    }

    /** Load a TimeTable from a file and redisplay.
     * Saved files are of the form:
     *          day hour
     *          description
     *          location
     *          day hour
     *          description
     *          location
     *          .....
     *  Asks the user to select a file.
     *  For each entry, 
     *  It should read the day and the hour as integers, and then the event details.
     *  Be careful:  after reading the day and hour, the scanner will be pointing at the end of the
     *  line, not at the beginning of the line with the name!
     */
    public void doLoad() {
        /*# YOUR CODE HERE */
        File file=new File(UIFileChooser.open("Choose database file"));
        try{
            Scanner sc=new Scanner(file);
            while(sc.hasNext()){     
                int day=sc.nextInt();
                int hour =sc.nextInt();
                sc.nextLine();
                String desc=sc.nextLine();
                String loc=sc.nextLine();
                this.events[day][hour]=new Event(desc,loc);
            }
            sc.close();
            UI.println("Timetable Loaded");
        }
        catch(IOException e){ UI.println("Fail: "+e);}
        this.redisplay();
    }

    /**
     * Save a TimeTable to a file in a form that can be loaded again later.
     * (ie, the same form as used in doLoad above).
     * Asks the user to choose a file.
     */
    public void doSave() {
        /*# YOUR CODE HERE */
        try{
            PrintStream out=new PrintStream(new File("timetable"));
            for(int day=0;day<DAYS;day++){
                for(int hour=8;hour<MAX_HOUR+1;hour++){
                    if(this.events[day][hour]!=null){
                        out.println(day+" "+hour);
                        out.println(this.events[day][hour].getDesc());
                        out.println(this.events[day][hour].getLoc());
                    }
                }
            }
        }catch(IOException e){ UI.println("Fail: "+e);}
        UI.println("The reading of file has completed.");
    }

    /** [COMPLETION ONLY]
     * Ask for the number of hours required, and then
     *  search for a day that has a block of that many hours free.
     *  Highlight the cells in red. (Don't forget to repaint the graphics)
     */
    public void doSearch() {
         this.redisplay();
        int hoursWanted = UI.askInt("Number of hours required"); 
        /*# YOUR CODE HERE */
       // int count=0;
        for(int day=0;day<DAYS;day++){
            for(int hour=8;hour<MAX_HOUR+1;hour++){
                for(int i=hour;i<hour+hoursWanted &&hour+hoursWanted<MAX_HOUR+1;i++)
                    if(this.events[day][i]==null){
                    this.displayCell(day,i,Color.red);
                }
            }
        }	    
        UI.repaintGraphics();
        this.redisplay();
    }
    // ==========================================================
    //                   HELPER METHOD
    //        These are already completed for you.
    // ==========================================================

    private String[] dayNames = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    /**
     * Display days and hours on the margins of the grid
     */
    private void displayTitles(){
        // display the titles on the margins
        for (int day = 0; day < DAYS; day++) {
            int x = this.gridLeft + day*this.cellWidth;
            UI.drawString(this.dayNames[day], x+10, this.gridTop-5);
        }
        for (int hour=MIN_HOUR; hour<=MAX_HOUR; hour++) {
            int y = this.gridTop + (hour-MIN_HOUR)*this.cellHeight;
            UI.drawString(hour+":00-"+(hour+1)+":00", this.gridLeft-80, y+this.cellHeight*2/3);
        }
    }

    // Main
    public static void main(String[] arguments){
        new TimeTable();
    }   

}
