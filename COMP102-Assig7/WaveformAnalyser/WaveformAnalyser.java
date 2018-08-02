// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 7 
 * Name: Xiaoshi Xie
 * Usercode:
 * ID:300366345
 */

import ecs100.*;
import java.io.*;
import java.util.*;
import java.awt.Color;

/**
 * This is related to your program from assignment 4 which analysed stock levels
 * However, instead of reading data from the user, it reads the data from
 * a file into an array, which means that it can analyse the numbers
 * more easily and in more different ways.
 * It can also cope with much larger sets of numbers.
 * The numbers are guaranteed to be integers but the values can be
 *   negative and the signal swings above and below zero.
 *
 * There are 11 methods you are to complete, all focusing on the array of data.
 * CORE
 *  doRead:              reads numbers into an array
 *  doDisplay:           displays the waveform.
 *  doReportDistortion:  prints out the fraction of time the signal is distorted
 *  doSpread:            displays the spread with two horizontal lines and returns its value.
 * COMPLETION
 *  doDisplayDistortion: shows in red the distorted part of the signal.
 *  doHighlightPeaks:    plots the peaks with small green circles.
 *  doNormalise:         normalises all the values down so there is no distortion
 * CHALLENGE
 *  upperEnvelope:       displays the upper envelope.
 *  lowerEnvelope:       displays the lower envelope.
 *  select and edit:     let the user select a regions of the waveform with the mouse
 *                       to remove them.  Add a save button to save the edited values.
 */

public class WaveformAnalyser{
    // Fields 
    private double[] waveform;   // the field to hold the array of values
    private int count;           // the field to hold the number of values

    // Constant: the threshold for the distortionLevel and showDistortion methods
    public static final double THRESHOLD = 200;

    // Constants: the dimensions of the graph for the displayWaveform method
    public static final int GRAPH_LEFT = 10;
    public static final int ZERO_LINE = 260;

    // Constant fields holding the size of the circles for the highlightPeaks method
    public static final int SIZE_CIRCLE = 10;

    /** Constructor:
     * Set up the ten buttons and mouselistener
     */
    public WaveformAnalyser(){
        //core
        UI.addButton("Read Data", this::doRead);
        UI.addButton("Display Waveform", this::doDisplay);
        UI.addButton("Report Distortion", this::doReportDistortion);
        UI.addButton("Spread", this::doSpread);
        //completion
        UI.addButton("Display Distortion", this::doDisplayDistortion);
        UI.addButton("Peaks", this::doHighlightPeaks);
        UI.addButton("Normalise", this::doNormalise);
        //challenge
        UI.addButton("Envelope", this::doEnvelope);
        UI.addButton("Save", this::doSave);
        UI.addButton("Quit", UI::quit);
        UI.setMouseListener(this::doMouse);   // only for challenge

    }

    /**
     * [CORE]
     * Clears the panes, 
     * Creates an array stored in a field, then
     * Asks user for a waveform file (eg waveform1.txt) 
     * Reads data from the file into the array
     */
    public void doRead(){
        UI.clearPanes();
        String fname = UIFileChooser.open();
        /*# YOUR CODE HERE */
        try{
            Scanner sc=new Scanner(new File(fname));
            this.count=sc.nextInt();
            this.waveform=new double[this.count];
            int i=0;
            while(i<this.waveform.length){
                this.waveform[i]=sc.nextDouble();
                UI.println(this.waveform[i]);
                i++;
            }
        }catch(Exception e){UI.printf("File failurez %s\n",e);}
        UI.println("Read "+ this.count +" data points from " + fname);
    }

    /**
     * [CORE]
     * Displays the waveform as a line graph,
     * The n'th value in waveform is displayed at
     *    x-position is GRAPH_LEFT + n
     *    y-position is ZERO_LINE - the value
     * Plots a line graph of all the points with a blue line between
     *  each pair of adjacent points
     * Draw the horizontal line representing the value zero.
     * Uses GRAPH_LEFT and ZERO_LINE for the dimensions and positions of the graph.
     * Don't worry if the lines go off the window
     */
    public void doDisplay(){
        if (this.waveform == null){ //there is no data to display
            UI.println("No waveform to display");
            return;
        }
        UI.clearGraphics();

        // draw x axis (showing where the value 0 will be)
        UI.setColor(Color.black);
        UI.drawLine(GRAPH_LEFT, ZERO_LINE, GRAPH_LEFT + this.count , ZERO_LINE); 

        // plot points: blue line between each pair of values
        /*# YOUR CODE HERE */
        UI.setColor(Color.blue);
        int i=1;
        while(i<this.waveform.length){
            if(this.waveform!=null){
                UI.drawLine(GRAPH_LEFT+i-1, ZERO_LINE-this.waveform[i-1],GRAPH_LEFT+i, ZERO_LINE-this.waveform[i]);
                i++;
            }
        }
    }

    /**
     * [CORE]
     * Computes and prints out the fraction of time the signal is distorted. 
     * This fraction of time is defined as the number of distorted values, divided by the number of values. 
     * A distorted value is defined as one whose absolute
     * value is greater than the value of THRESHOLD 
     * [Hint] You may find Math.abs() useful for this method - it computes the absolute value
     */
    public void doReportDistortion() {
        if (this.waveform == null){ //there is no data to analyse
            UI.println("No signal to analyse and report on");
            return;
        }
        double fraction = 0;
        /*# YOUR CODE HERE */
        int i=0;
        int distortedNum=0;
        while(i<this.waveform.length){
            if(this.waveform!=null){
                if(Math.abs(this.waveform[i])>THRESHOLD){
                    // UI.println(this.waveform[i]);//test
                    distortedNum++;
                }
                i++;
            }
        }
        //UI.println("distortedNum"+distortedNum);//test
        fraction=(double)distortedNum/this.count;          

        UI.printf("Fraction of time the signal is distorted %4.3f\n", fraction);
    }

    /**
     * [CORE]
     * The spread is the difference between the maximum and minimum values of the waveform.
     * Finds the maximum and minimum values, then
     * Displays the spread by drawing two horizontal lines on top of the waveform: 
     *   one green line for the maximum value, and
     *   one red line for the minimum value.
     */
    public void doSpread() {
        if (this.waveform == null){ //there is no data to display
            UI.println("No waveform to display");
            return;
        }
        this.doDisplay();
        /*# YOUR CODE HERE */
        double min=Double.MAX_VALUE;
        double max=Double.MIN_VALUE;
        int i=0;
        while(i<this.waveform.length){
            if(this.waveform!=null){
                if(this.waveform[i]<min){
                    min=this.waveform[i];
                    //UI.println("min"+min);//test
                }
                if(this.waveform[i]>max){
                    max=this.waveform[i];
                    //UI.println("max"+max);//test
                }
                i++;
            }
        }
        UI.println("max "+max+" min"+min);
        UI.setColor(Color.red);
        UI.drawLine(0+GRAPH_LEFT,ZERO_LINE+Math.abs(min),this.count+GRAPH_LEFT,ZERO_LINE+Math.abs(min));
        UI.setColor(Color.green);
        UI.drawLine(0+GRAPH_LEFT,ZERO_LINE-max,this.count+GRAPH_LEFT,ZERO_LINE-max);
    }

    /**
     * [COMPLETION]  [Fancy version of doDisplay]
     * Display the waveform as a line graph. 
     * Draw a line between each pair of adjacent points
     *   * If neither of the points is distorted, the line is BLUE
     *   * If either of the two end points is distorted, the line is RED
     * Draw the horizontal lines representing the value zero and thresholds values.
     * Uses THRESHOLD to determine distorted values.
     * Uses GRAPH_LEFT and ZERO_LINE for the dimensions and positions of the graph.
     * [Hint] You may find Math.abs(int a) useful for this method.
     * You may assume that all the values are between -250 and +250.
     */
    public void doDisplayDistortion() {
        if (this.waveform == null){ //there is no data to display
            UI.println("No waveform to display");
            return;
        }
        UI.clearGraphics();

        // draw zero axis
        UI.setColor(Color.black);
        UI.drawLine(GRAPH_LEFT, ZERO_LINE, GRAPH_LEFT + this.count , ZERO_LINE); 

        // draw thresholds
        /*# YOUR CODE HERE */
        UI.setColor(Color.green);
        UI.drawLine(GRAPH_LEFT,ZERO_LINE+THRESHOLD,this.count+GRAPH_LEFT, ZERO_LINE+THRESHOLD);//Draw threshold
        UI.drawLine(GRAPH_LEFT,ZERO_LINE-THRESHOLD,this.count+GRAPH_LEFT,ZERO_LINE-THRESHOLD);//Draw threshold
        UI.setColor(Color.blue);
        int i=1;
        while(i<this.waveform.length){
            if(this.waveform!=null){
                if(Math.abs(this.waveform[i-1])>THRESHOLD ||Math.abs(this.waveform[i])>THRESHOLD){
                    UI.setColor(Color.red);
                    UI.drawLine(GRAPH_LEFT+i-1, ZERO_LINE-this.waveform[i-1],GRAPH_LEFT+i, ZERO_LINE-this.waveform[i]);
                }else{
                    UI.setColor(Color.blue);
                    UI.drawLine(GRAPH_LEFT+i-1, ZERO_LINE-this.waveform[i-1],GRAPH_LEFT+i, ZERO_LINE-this.waveform[i]);}
                i++;
            }
        }

    }

    /**
     * [COMPLETION]
     * Plots the peaks with small green circles. 
     *    A peak is defined as a value that is greater or equals to both its
     *    neighbouring values.
     * Note the size of the circle is in the constant SIZE_CIRCLE
     * You may assume that peaks values differ from their neighbouring points.
     */
    public void doHighlightPeaks() {
        this.doDisplayDistortion(); //use doDisplay if doDisplayDistortion isn't complete
        /*# YOUR CODE HERE */
        int i=1;
        while(i<this.waveform.length+1){
            if(this.waveform!=null){
                if(this.waveform[i-1]<this.waveform[i] &&this.waveform[i]>this.waveform[i+1]){
                    UI.setColor(Color.green);
                    UI.drawOval(GRAPH_LEFT+i-SIZE_CIRCLE/2,ZERO_LINE-this.waveform[i]-SIZE_CIRCLE/2,SIZE_CIRCLE,SIZE_CIRCLE);
                }
                i++;
            }
        }
    }

    /**
     * [COMPLETION]
     * Finds the largest value (positive or negative) in the waveform, and
     * normalises all the values down so that the largest value is now equal to
     * the distortion threshold.
     * Then redraws the waveform.
     */
    public void doNormalise() {
        /*# YOUR CODE HERE */
        double max=Double.MIN_VALUE;
        int i=0;
        while(i<this.waveform.length){
            if(this.waveform!=null){
                if(Math.abs(this.waveform[i])>max){
                    max=Math.abs(this.waveform[i]);
                }
                i++;
            }

        }
        //UI.println("max"+max);//test
        double proportion=THRESHOLD/max;
        for(i=0;i<this.waveform.length;i++){
            this.waveform[i]=this.waveform[i]*proportion;

        }
        this.doDisplayDistortion(); //use doDisplay if doDisplayDistortion isn't complete
    }

    public void doEnvelope(){
        if (this.waveform == null){ //there is no data to display
            UI.println("No waveform to display");
            return;
        }
        this.doDisplay();  // display the waveform
        this.upperEnvelope();
        this.lowerEnvelope();
    }

    /**
     * [CHALLENGE]
     * Displays the upper envelope with GREEN lines connecting all the peaks.
     *    A peak is defined as a point that is greater or equal to *both* neighbouring points.
     * DO NOT clear the graphics as we also want to view the waveform.
     */
    public void upperEnvelope() {
        /*# YOUR CODE HERE */
        int i=1;
        int n=1;
       // this.peaksCount;
        
        
      /*  while(i<this.waveform.length+1){
            if(this.waveform!=null){
                if(this.waveform[i-1]<this.waveform[i] &&this.waveform[i]>this.waveform[i+1]){
                 peaks[n]=this.waveform[i];
                 this.peakCount++;
                }
            }
        }
        this.peaks=new double[this.peaksCount];
        while(n<this.peaks.length){
            if(this.peaks!=null){
               // UI.drawLine(GRAPH_LEFT+i-1, ZERO_LINE-this.waveform[i-1],GRAPH_LEFT+i, ZERO_LINE-this.waveform[i]
    }
}*/
}

    /**
     * [CHALLENGE]
     * Displays the lower envelope with RED lines connecting all the "negative" peaks.
     *    A "negative" peak is defined as a point that is smaller or equal to *both* neighbouring points.
     * DO NOT clear the graphics as we also want to view the waveform.
     */
    public void lowerEnvelope() {
        /*# YOUR CODE HERE */

    }

    /**
     * [CHALLENGE]
     * Saves the current waveform values into a file
     */
    public void doSave(){
        /*# YOUR CODE HERE */

    }

    private int index1;
    /**
     * [CHALLENGE]
     * Lets user select a region of the waveform with the mouse
     * and deletes that section of the waveform.
     */
    public void doMouse(String action, double x, double y){
        /*# YOUR CODE HERE */

    }

    // Main
    public static void main(String[] arguments){
        new WaveformAnalyser();
    }   

}
