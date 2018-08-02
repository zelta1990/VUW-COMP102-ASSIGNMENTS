// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 5
 * Name:Xiaoshi Xie
 * Usercode:
 * ID:300366345
 */

import ecs100.*;
import java.util.*;
import java.io.*;

public class ClassTimes {

    /* classdata.txt is changed to newClassdata.txt in 2014T2.
     * The line format is : course, day, start time, end time, room, type.
     * the type is now the last token in each line. */

    /** Core 1
     * Reads the class timetable file, printing out each line to the UI.
     * This method is very straightforward, and there are very similar
     * examples in the lecture notes.
     */
    public void printAll() {
        try {
            /*# YOUR CODE HERE */
            Scanner scan=new Scanner(new File("newClassdata.txt"));
            while(scan.hasNext()){
                String courseCode=scan.next();
                String day=scan.next();
                String startTime=scan.next();
                String endTime=scan.next();
                String roomCode=scan.next();
                String classType=scan.next();
                UI.println(courseCode+"  "+day+"  "+startTime+"  "+endTime+"  "+roomCode+"  "+classType);

            }
        }

        catch(IOException e) {UI.printf("File Failure %s \n", e);}
    }

    /** Core 2
     * Reads the class timetable file, printing out (to the UI window)
     * the class type, day, start time, end time, and room
     * for each class with the target course.
     * Prints a message if there are no classes for the course.
     */
    public void printCourse(String targetCourse){
        /*# YOUR CODE HERE */
        try {
            Scanner scan=new Scanner(new File("newClassdata.txt"));
            targetCourse=UI.askString("Please enter the course code");
            int falseCount=0;
            int trueCount=0;
            int totalCount=0;
            while(scan.hasNext()){
                String courseCode=scan.next();
                String day=scan.next();
                int startTime=scan.nextInt();
                String endTime=scan.next();
                String roomCode=scan.next();
                String classType=scan.next();
                String out=courseCode+"  "+day+"  "+startTime+"  "+endTime+"  "+roomCode+"  "+classType;                
                if(courseCode.equals(targetCourse)){
                    UI.println(out);
                }else{
                    falseCount+=1;//to count when the coursecode doesn't match

                }
                totalCount+=1;//to count the available classes
            } 
            scan.close();
            if (falseCount==totalCount){   
                UI.println("There are no classes for the course.");
            }
        }
        catch(IOException e) {UI.printf("File Failure %s \n", e);}
    }           

    /** Core 3
     * Prints out the start time and underlines it, then
     * Reads the class timetable file, printing out (to the UI window)
     * the course code, room, type, day and end time for
     * each class that starts at the target time.
     * It will be best to read the six tokens on each line individually.
     */
    public void printAtStartTime(int startTime) {
        UI.println("Classes starting at " + startTime);
        UI.println("============================");
        /*# YOUR CODE HERE */
        try {
            Scanner scan=new Scanner(new File("newClassdata.txt"));
            startTime=UI.askInt("Please enter the startTime");
            int falseCount=0;
            int totalCount=0;
            while(scan.hasNext()){
                String courseCode=scan.next();
                String day=scan.next();
                int time=scan.nextInt();
                String endTime=scan.next();
                String roomCode=scan.next();
                String classType=scan.next();
                String out=courseCode+"  "+day+"  "+startTime+"  "+endTime+"  "+roomCode+"  "+classType;
                if(time==startTime){
                    UI.println(out);
                }else{
                    falseCount+=1;
                }
                totalCount+=1;
            }        
            scan.close();
            if (falseCount==totalCount){
                UI.println("There are no classes for this starttime.");
            }
        }
        catch(IOException e) {UI.printf("File Failure %s \n", e);}
    }           

    /** Core 4
     * Writes a new file listing all the class bookings that are in a given room.
     *  The name of the new file should be the room, followed by "_Bookings.txt"
     *  The first line of the file should specify what room the bookings are for:
     *  "Bookings for room <room name>"
     *  
     *  Each class booking should be formatted in three lines, with a blank line after.
     *  Course: <Course Code>
     *  Time: <Day> <Start Time>-<End Time>
     *  Session: <Type>
     *
     *  For example, if the targetRoom is VZ515, then the start of the file would be as follows
     * 
     *  Bookings for room VZ515
     *  --------------------------------------------------
     *  Course: ACCY111
     *  Time: Tue 1000-1050
     *  Session: Tutorial
     *  
     *  Course: ACCY130
     *  Time: Thu 1310-1400
     *  Session: Tutorial
     *  
     *  Course: ACCY130
     *  Time: Tue 1310-1400
     *  Session: Tutorial
     *  
     */
    public void bookingsFileForRoom(String targetRoom) {
        /*# YOUR CODE HERE */
        try {
            Scanner scan=new Scanner(new File("newClassdata.txt"));
            targetRoom=UI.askString("Enter room code");
            PrintStream out=new PrintStream(new File(targetRoom+"_Bookings.txt"));
            int falseCount=0;
            int totalCount=0;
            out.println("Bookings for room "+targetRoom);
            out.println("-----------------------------------");
            while(scan.hasNext()){
                String courseCode=scan.next();
                String day=scan.next();
                String startTime=scan.next();
                String endTime=scan.next();
                String roomCode=scan.next();
                String classType=scan.next();
                if(roomCode.equals(targetRoom)){        
                    out.println("Course: "+courseCode);
                    out.println("Time: "+day+" "+startTime+" "+endTime);
                    out.println("Session: "+classType);
                    out.println();
                }else{
                    falseCount+=1;
                }
                totalCount+=1;
            } 

            scan.close();
            UI.println("The reading of file has completed.");
            if (falseCount==totalCount){
                out.println("There are no classes for this room.");
            }
            out.close();

        }
        catch(IOException e) {UI.printf("File Failure %s \n", e);}
    }   

    /** Completion 1
     * Prints a title containing its arguments, and then
     * Reads the class timetable file, printing out (to the UI window)
     * the course code, class type, day, start and end time
     * for each class that is in targetRoom1 or targetRoom2 and is on targetDay
     * It will be best to read the six tokens on each line individually.
     */
    public void printRoomsOnDay(String targetRoom1, String targetRoom2, String targetDay) {
        UI.printf("Classes in %s or %s on %s%n", targetRoom1, targetRoom2, targetDay);
        UI.println("=======================");
        /*# YOUR CODE HERE */
        try {
            Scanner scan=new Scanner(new File("newClassdata.txt"));
            targetRoom1=UI.askString("Please enter target room1");
            targetRoom2=UI.askString("Please enter target room2");
            targetDay=UI.askString("Please enter target day(case sensative)");
            int falseCount=0;
            int totalCount=0;
            int count=0;
            UI.println("Classes in "+targetRoom1+" or "+targetRoom2+" on "+targetDay);
            UI.println("----------------------------------------------");
            while(scan.hasNext()){
                String courseCode=scan.next();
                String day=scan.next();
                String startTime=scan.next();
                String endTime=scan.next();
                String roomCode=scan.next();
                String classType=scan.next();
                if(targetDay.equals(day)){
                    if(roomCode.equals(targetRoom1)||roomCode.equals(targetRoom2)){    
                        UI.println(courseCode+" "+startTime);
                    }else{
                        falseCount+=1;
                       // UI.println("wrongroom");//test
                    }
                    totalCount+=1;
                }       
                else{
                    count+=1;
                    //UI.println("wrongday");//test
                }
            }
            if (falseCount==totalCount){
                UI.println("There are no classes in either room on that day.");
            }

            scan.close();
        }

        catch(IOException e) {UI.printf("File Failure %s \n", e);}
    }           

    /** Completion 2
     * Returns the average (mean) duration in minutes of all classes scheduled
     * in a specified room.
     * Hint: be careful with the times
     * Hint: if there are no classes in the room, do not cause an error.
     */
    public double meanClassLength(String targetRoom) {
        /*# YOUR CODE HERE */
        double meanClassLength;
        try {
            Scanner scan=new Scanner(new File("newClassdata.txt"));
            targetRoom=UI.askString("Enter room code");
            int falseCount=0;
            int totalCount=0;
            int trueCount=0;
            int totalLength=0;
            int hourDiff;
            int minDiff;
            int length;
            
            while(scan.hasNext()){
                String courseCode=scan.next();
                String day=scan.next();
                String startTime=scan.next();
                String endTime=scan.next();
                String roomCode=scan.next();
                String classType=scan.next();
                String startHour="";
                String startMin="";
                String endHour="";
                String endMin="";
                if(startTime.length()==3){
                    startHour=startTime.substring(0,1);
                    startMin=startTime.substring(1);
                }else if(startTime.length()==4){
                    startHour=startTime.substring(0,2);
                    startMin=startTime.substring(2);
                }
                if(endTime.length()==3){
                    endHour=endTime.substring(0,1);
                    endMin=endTime.substring(1);
                }else if(endTime.length()==4){
                    endHour=endTime.substring(0,2);
                    endMin=endTime.substring(2);
                }
                Integer startH=Integer.valueOf(startHour);
                Integer endH=Integer.valueOf(endHour);
                Integer startM=Integer.valueOf(startMin);
                Integer endM=Integer.valueOf(endMin);
                if(roomCode.equals(targetRoom)){    
                    //UI.println("starthour "+startH+" endHour "+endH+" startMin "+startM+" endMin "+endM);//test
                    if(endM>=startM){           
                        hourDiff=endH-startH;
                        minDiff=endM-startM;
                        length=minDiff+hourDiff*60;
                        //UI.println("hourDiff "+hourDiff+" minDiff "+minDiff+" Length "+length); //test
                    }else {
                        minDiff=endM+60-startM;
                        hourDiff=endH-1-startH;
                        length=minDiff+hourDiff*60;
                        //UI.println("hourDiff "+hourDiff+" minDiff "+minDiff+" Length "+length); //test
                    }
                    totalLength=totalLength+length;
                    trueCount+=1;                
                }else{
                    falseCount+=1;
                }
                totalCount+=1;
            } 

            scan.close();
            meanClassLength=(double)totalLength/trueCount;
            //UI.println("trueCount"+trueCount);//test
            UI.printf("The mean class length for this room is %4.2f minutes",meanClassLength);
            if (falseCount==totalCount){
                UI.println("There are no classes for this room.");
            }
            return meanClassLength;
        }catch(IOException e) {UI.printf("File Failure %s \n", e);}

        return -1;
    }


    // to make it compile 

    /** Challenge 1
     * Lists all the courses (just the course code) that have classes in a given building
     * on a given day such that any part of the class is between the given times.
     * Each course involved should only be listed once, even if it has several classes
     * in the building in the time period.  Note, the data file is ordered by the course code.
     * Note that this is similar, but not the same as, one of the completion questions.
     */
    public void potentialDisruptions(String building, String targetDay, int targetStart, int targetEnd){
        /*# YOUR CODE HERE */

    }

    /** Challenge 2
     * Lists all available time for a giving room on a giving day from 900 to 1700
     */
    public void availableTimes(String targetRoom, String targetDay){
        /*# YOUR CODE HERE */

    }

    // ======================================================================
    // A "Command line" interface for running the program.
    /** Repeatedly asks the user for a command (one letter) and
    calls the appropriate method */
    public void commandLoop(){
        UI.setDivider(1.0);
        this.printHelp();
        while(true){
            String cmd = UI.askString("Command: ").substring(0,1).toUpperCase();
            if (cmd.equals("A")) {
                this.printAll();
            }
            else if (cmd.equals("C") ){
                String code = UI.askString("Enter course code (eg COMP102):").toUpperCase();
                this.printCourse(code);
            }
            else if (cmd.equals("S") ){
                int time = UI.askInt("Enter start time (eg 900):");
                this.printAtStartTime(time);
            }
            else if (cmd.equals("F")) {
                String room = UI.askString("Enter room code (eg AM102):").toUpperCase();
                this.bookingsFileForRoom(room);
            }
            else if (cmd.equals("D")) {
                String room1 = UI.askString("Enter first room code (eg AM102):").toUpperCase();
                String room2 = UI.askString("Enter second room code (eg AM104):").toUpperCase();
                String day = UI.askString("Enter first 3 letters of day (eg Mon):");
                this.printRoomsOnDay(room1, room2, day);
            }
            else if (cmd.equals("M")) {
                String room = UI.askString("Enter room (eg HMLT205):");
                UI.printf("Average duration in %s = %4.2f mins%n",
                    room, this.meanClassLength(room));
            }
            else if (cmd.equals("P")) {
                String bldg = UI.askString("Enter building (eg HM):");
                String day = UI.askString("Enter first 3 letters of day (eg Mon):");
                int time1 = UI.askInt("Enter start time (eg 1115):");
                int time2 = UI.askInt("Enter end time (eg 1430):");
                this.potentialDisruptions(bldg, day, time1, time2);
            }
            else if (cmd.equals("T")) {
                String room = UI.askString("Enter room (eg AM102):");
                String day = UI.askString("Enter first 3 letters of day (eg Mon):");
                this.availableTimes(room, day);
            }
            else if (cmd.equals("H") ){
                this.printHelp();
            }
            else if (cmd.equals("Q") ){
                UI.quit();
            }
            else {
                UI.println("Command '" +cmd+"' not recognised");
                this.printHelp();
            }
        }
    }

    /** Prints a list of the possible commands */
    public void printHelp(){
        UI.println("Commands are:");
        UI.println("\tA for print all    (core)"); //Core
        UI.println("\tC for print course (core)"); //Core
        UI.println("\tS for start time   (core)"); //Core

        UI.println("\tF for file of bookings for room (core)"); //core
        UI.println("\tD for rooms on day (completion)"); //Completion

        UI.println("\tM for mean class length (Completion)"); //Completion
        UI.println("\tP for courses in building during time period (challenge)");
        UI.println("\tT for available times (challenge)");//Challenge
        UI.println("\tH for this help");
        UI.println("\tQ for quit");
    }

    public static void main(String[] args){
        ClassTimes ct = new ClassTimes();
        //ct.commandLoop();

    }
}

