
/**
 * Write a description of class Switch here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Switch
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Switch
     */
    public Switch()
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
    public void OddSwitch(int Number)
    {
        // put your code here
        switch(Number){
        case 1: case 3: case 5: case 7: System.out.println("odd");
        break;
        case 0: case 2: case 4: case 6: case 8: System.out.println("even");
        break;
        default: System.out.println("Please pick a number between 0 & 8");
        }
    }
}
