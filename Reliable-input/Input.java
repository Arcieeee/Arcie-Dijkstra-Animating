import java.util.*;


/**
 * Write a description of class Input here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Input
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Input
     */
    public Input()
    {
        // initialise instance variables
        x = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int readNum(){
        // put your code here
        Scanner keyboard=new Scanner(System.in);
        if (keyboard.hasNextInt())
        return keyboard.nextInt();
        else{System.out.println("Nope"); return -1;}
    }
}
