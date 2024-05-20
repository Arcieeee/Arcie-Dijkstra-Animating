
/**
 * Write a description of class Variables here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Variables
{
    // instance variables - replace the example below with your own
    private int x;
    private char letter = 'x';
    private int pi = 8734;
    private long tau = 878754554545435843l;
    private double decimal = 3.14159265358979323846264;
    private float smalldecimal = 3.142857f;
    private byte sixetynine = 69;
    private short nice = 420;
    

    /**
     * Constructor for objects of class Variables
     */
    public Variables()
    {
        // initialise instance variables
        x = 0;
    
    }
    
    public int integer()
    {
        // initialise instance variables
        pi++;
        return pi;
    
    }
    
    public float Float()
    {
        // initialise instance variables
        smalldecimal++;
        return smalldecimal;
    
    }

    public long Long()
    {
        // initialise instance variables
        tau++;
        return tau;
    
    }

    public double Double()
    {
        // initialise instance variables
        decimal++;
        return decimal;
    
    }

    public byte Byte()
    {
        // initialise instance variables
        sixetynine++;
        return sixetynine;
    
    }

    public short Short()
    {
        // initialise instance variables
        nice++;
        return nice;
    
    }

    public char character()
    {
        // initialise instance variables
        letter++;
        return letter;
    
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
