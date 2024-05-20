
/**
 * Write a description of class Scanner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner; // Keyboard input.
public class Scanners
{
    // instance variables - replace the example below with your own
    private int x;
    Scanner keyboard;

    /**
     * Constructor for objects of class Scanner
     */
    public Scanners()
    {
        // initialise instance variables
        x = 0;
        keyboard=new Scanner(System.in);
        int number=keyboard.nextInt();
        System.out.println(number);
    }

    public void print()
    {
        keyboard=new Scanner(System.in);
        int number=keyboard.nextInt();
        System.out.println(number);
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}
