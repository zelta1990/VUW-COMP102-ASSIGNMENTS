// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for Assignment 4, COMP 102 
 * Name:Xiaoshi Xie
 * Usercode:
 * ID:300366345
 */

import ecs100.*;
import java.awt.Color;
/**
 * A program that plays a guessing game in which the computer  "thinks of"
 * a number between 1 and 40 (or two numbers for the more advanced version),
 * and then gives the player a limited number of chances to guess the number(s). 
 * If the player guesses the number within the maximum number of guesses, they win points.
 * The program should prompt the player for each guess, and then display each guess
 * as a mark on a "number line" in the graphics pane,
 * It should also tell the player whether the guess is correct, or too low or too high.
 * 
 * For the simple (core) version, 
 *  The program will play rounds of the game until the player says they have had enough.
 *  For each round, the player has 5 chances to guess the number.
 *  After each guess, the program says whether the guess is correct, too low, or too high.
 *  If the player guesses the number within the 5 chances, the player gains a point
 *   otherwise, the player loses a point (but the score never goes below 0).
 *  At the end, the program reports the player's score
 *  To get full marks for core,  the program should also show each guess
 *   on a display on the graphics pane.
 *
 * For the completion version,
 *  - The program should think of two (different) numbers each round and must
 *    say whether a wrong guess is less than the smaller number, greater
 *    than the larger number, or between the numbers.
 *    The player only wins the round if they guess both numbers (not if they
 *     guess one number twice!)
 *    The program should still display the guesses on the graphics pane.
 *    The program should disallow any guesses outside the range 1 to 40, and make the
 *      player enter the guess again.
 *  - The player gains 1 point for the round if they guess both numbers, plus
 *    an extra point for each remaining guess that they didn't use up.  The player
 *    loses a point if they didn't guess either number (though the score still
 *    never goes below 0).
 * 
 * For the challenge version,
 *  - The program should display the regions of the number line that are now
 *    impossible. For example, if the player guesses 6 and the program says that
 *    this is less than both numbers, then the numbers couldn't be between 1 and 6,
 *    so the program should display that region of the line as "impossible".
 *  - Even better (though this requires more features of Java than the lectures have
 *    covered so far) the program should not allow the player to make a guess that
 *    is in any of the impossible regions (ie, it should tell the user that it is
 *    impossible and make them enter a valid guess).
 *  - Rig the game so that the player will always win at the very last possible
 *    guess. 
 */
public class GuessingGame{

    public static final int maxValue = 40;
    public static final int maxGuesses = 5;

    // used for displaying the result in the Core
    public static final int barLeft = 50;
    public static final int barTop = 100;
    public static final int size = 10;   // width of each number on the bar.

    /** CORE
     * Plays one round of the one-number version of the guessing game
     * Computer randomly chooses an integer between 1 and maxValue (40)
     * Draws a horizontal line in the graphics pane labeled with 1 at one end
     *  and maxValue at the other end.
     * Player is offered up to maxGuesses chances to guess this number
     *    Whenever they guess outside the range 1 to maxValue, it asks them to try again
     *    Displays their guess on the line by drawing a bar at the appropriate place
     *     and putting the number above it.
     *    Tells Player if their guess is higher or lower than the answer
     *    Tells the player when they have guessed correctly and stops if they win
     * Returns true if the player guessed correctly, else returns false.
     */
    public boolean playRound(){
        /*# YOUR CODE HERE */

        int number=(int)(Math.random()*40+1);
        int counter=1;
        String mark="";
        boolean answer=false;
        UI.drawString("1",15,70);
        UI.drawString("40",415,70);
        UI.fillRect(15,75,400,5);

        while(counter<=5 && !answer ){      
            int guess=UI.askInt("Please guess a number between 1 and 40");
            String b=Integer.toString(guess);
            answer=(guess==number);
            //UI.println(number);//to test whether the program runs correct

            if(guess<1 ||guess>40){
                UI.println("Out of range.Please try again");          

            }
            else if(guess<number){
                UI.println("Your guess is too low.");
                UI.setColor(Color.blue);
                UI.drawString(guess+"",15+guess*10,65);
                UI.fillRect(15+guess*10,70,5,10);        

            }
            else if(guess>number) {
                UI.println("Your guess is too high.");
                UI.setColor(Color.red);
                UI.drawString(guess+"",15+guess*10,65);
                UI.fillRect(15+guess*10,70,5,10);        

            }
            else{
                UI.println(guess);
                UI.println("Your guess is correct.");
                UI.setColor(Color.green);
                UI.drawString(guess+"",15+guess*10,65);
                UI.fillRect(15+guess*10,70,5,10);        

            }
            counter=counter+1;

        }

        UI.println("That's all your guesses.The number is "+number);

        return answer;
    }

    /** CORE
     * Plays the guessing game.
     * Prints an introduction, then
     * repeatedly calls playRound to play a single round of the game,
     * keeping track of the score.
     * After each round, it asks if they want to play again.
     * When finished it prints the score (how many times they guessed
     * correctly and how many rounds they tried).
     */
    public void playGame(){
        UI.println("-----CORE GAME-----------");
        UI.println("Hello, let's play the guessing game");
        UI.println("Each round, you must guess my number;");
        /*# YOUR CODE HERE */
        int score;
        score=0;

        boolean c=true;  
        boolean b=(score>=0);
        while(b && c ){
            UI.clearGraphics();
            boolean answer=this.playRound();
            if(answer){
                score=score+1;

            }
            else{
                score=score-1;
            }
            String reflection=UI.askString("Do you wanna play again?(Enter y for yes)"); 
            c=(reflection.equals("y"));
        }
        if (score<0){
            score=0;
        }
        UI.println("Your final score is "+score);

    }

    /**
     * Displays the bar.
     * Clears the graphics pane then draws line labeled with 1 and maxValue at the ends
     */
    public void displayBar(){
        /*# YOUR CODE HERE */        

        int number=(int)(Math.random()*40+1);
        int counter=1;
        String mark="";
        boolean answer=false;
        UI.drawString("1",15,70);
        UI.drawString("40",415,70);
        UI.fillRect(15,75,400,5);

        while(counter<=5 && !answer ){      
            int guess=UI.askInt("Please guess a number between 1 and 40");
            String b=Integer.toString(guess);
            answer=(guess==number);
            //UI.println(number);//to test whether the program runs correct

            if(guess<1 ||guess>40){
                UI.println("Out of range.Please try again");          

            }
            else if(guess<number){
                UI.println("Your guess is too low.");
                UI.drawString(guess+"",15+guess*10,65);
                UI.fillRect(15+guess*10,70,5,10);        

            }
            else if(guess>number) {
                UI.println("Your guess is too high.");
                UI.drawString(guess+"",15+guess*10,65);
                UI.fillRect(15+guess*10,70,5,10);        

            }
            else{
                UI.println(guess);
                UI.println("Your guess is correct.");
                UI.drawString(guess+"",15+guess*10,65);
                UI.fillRect(15+guess*10,70,5,10);        

            }
            counter=counter+1;

        }

        UI.println("That's all your guesses.The number is "+number);

    }
                        
    /**
     * Displays a guess on the bar, using the specified color.
     * Clears the graphics pane then draws line labeled with 1 and maxValue at the ends
     */
    public void displayGuess(){
        /*# YOUR CODE HERE */    

        int number=(int)(Math.random()*40+1);
        int counter=1;
        String mark="";
        boolean answer=false;
        UI.drawString("1",15,70);
        UI.drawString("40",415,70);
        UI.fillRect(15,75,400,5);

        while(counter<=5 && !answer ){      
            int guess=UI.askInt("Please guess a number between 1 and 40");
            String b=Integer.toString(guess);
            answer=(guess==number);
            //UI.println(number);//to test whether the program runs correct

            if(guess<1 ||guess>40){
                UI.println("Out of range.Please try again");          

            }
            else if(guess<number){
                UI.println("Your guess is too low.");
                UI.setColor(Color.blue);
                UI.drawString(guess+"",15+guess*10,65);
                UI.fillRect(15+guess*10,70,5,10);        

            }
            else if(guess>number) {
                UI.println("Your guess is too high.");
                UI.setColor(Color.red);
                UI.drawString(guess+"",15+guess*10,65);
                UI.fillRect(15+guess*10,70,5,10);        

            }
            else{
                UI.println(guess);
                UI.println("Your guess is correct.");
                UI.setColor(Color.green);
                UI.drawString(guess+"",15+guess*10,65);
                UI.fillRect(15+guess*10,70,5,10);        

            }
            counter=counter+1;

        }

        UI.println("That's all your guesses.The number is "+number);

    }

    
    /** playRound: COMPLETION
     * Computer randomly chooses two different numbers between 1 and maxValue
     * Draws a horizontal line in the graphics pane labeled with 1 at one end
     *  and maxValue at the other end.
     * User is offered up to maxGuesses chances to guess these two numbers 
     *  Whenever they guess outside the range 1 to maxValue, it asks them to try again
     *  Displays their guess on the line by drawing a bar at the appropriate place
     *  After each guess, it tells the user whether  their guess is:
     *  - correct,
     *  - higher than both numbers,
     *  - lower than both numbers, or
     *  - between the two numbers.
     * Stops when they have used up all their guesses, or have guessed both numbers correctly
     * It returns a score which is
     *  -1, if they guessed neither number,
     *  0, if they guessed just one number
     *  1 plus the number of remaining guesses that they didn't
     *    use, if they guessed both numbers
     */
    public int playRoundCompletion(){
        int number1=(int)(Math.random()*40+1);
        int number2=(int)(Math.random()*40+1);
        if(number1>number2){  //to make number1 always less than number2;
            int a=number1;
            int b=number2;
            number1=b;
            number2=a;
        }
        int counter=1;        
        boolean answer1=false;
        boolean answer2=false;
        UI.drawString("1",15,70);
        UI.drawString("40",415,70);
        UI.fillRect(15,75,400,5);

        while(counter<=6 && !(answer1&&answer2)){      
            int guess=UI.askInt("Please guess a number between 1 and 40");
            answer1=(guess==number1);
            answer2=(guess==number2);

            if(guess<1||guess>40){
                UI.println("Out of range.Retry");

                counter=counter-1;
            }
            else if(answer1){
                UI.println("Your guess of number1 is correct");
                UI.setColor(Color.green);
                UI.drawString(guess+"",15+guess*10,65);
                UI.fillRect(15+guess*10,70,5,10); 
                UI.setColor(Color.black);

            }  
            else if(answer2){
                UI.println("Your guess of number2 is correct");
                UI.setColor(Color.green);
                UI.drawString(guess+"",15+guess*10,65);
                UI.fillRect(15+guess*10,70,5,10); 
                UI.setColor(Color.black);
            }
            else if(guess<number1){
                UI.println("Your guess is too low.");
                UI.setColor(Color.blue);
                UI.drawString(guess+"",15+guess*10,65);
                UI.fillRect(15+guess*10,70,5,10); 
                UI.setColor(Color.black);
            }
            else if(guess>number1 && guess<number2){
                UI.println("Your guess is inbetween");
                UI.setColor(Color.black);
                UI.drawString(guess+"",15+guess*10,65);
                UI.fillRect(15+guess*10,70,5,10); 
            }
            else if(guess>number2){
                UI.println("Your guess is too high.");
                UI.setColor(Color.red);
                UI.drawString(guess+"",15+guess*10,65);
                UI.fillRect(15+guess*10,70,5,10); 
            }
             counter=counter+1;
        }
       if(answer1&&answer2){
           UI.println("You got the two numbers right.");
        }

    UI.println("That's all your guesses.The number are "+number1+" and "+number2);
    int value;
    if(answer1&&answer2){
        value=1;
    }
    else if(answer1||answer2){
        value=0;
    }
    else if((answer1&&answer2) &&(6-counter>0)){
        value=2;
    }
    else{
        value=-1;
    }
    return value;
    

    //return answer;
    //return 0; // To make the template compile
}

/** playGame:  COMPLETION  
 * Plays a better version of the guessing game.
 * Prints an introduction, then
 * repeatedly calls playRound to play a single round of the game,
 * keeping track of the score.
 * After each round, it asks if they want to play again.
 * When finished it prints the score and how many rounds they went through
 */
public void playGameCompletion(){
UI.println("Hello, let's play the guessing game");
UI.println("Each round, you must guess my two numbers.They are between 1 and 40");
int score=0;
boolean c=true;  
boolean b=(score>=0);
while(b && c ){
UI.clearGraphics();
int value=this.playRoundCompletion();
if(value==-1){
score=score-1;                
}
else if(value==0){
score=score;
}
else if (value==1){
score=score+1;
}
else if(value==2){
score=score+2;
}
String reflection=UI.askString("Do you wanna play again?(Enter y for yes)"); 
c=(reflection.equals("y"));

if (score<0){
score=0;
}

UI.println("Your final score is "+score);

}
}


/** Constructor: set up user interface */
public GuessingGame(){
UI.initialise();
UI.addButton("Clear", UI::clearPanes );
UI.addButton("One round core",  this::playRound );
UI.addButton("Core", this::playGame ); 
}        

}

