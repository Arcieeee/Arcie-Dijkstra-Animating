import java.util.Scanner; // Keyboard input.
/**
 * The Implementation of the algorithm
 *
 * Archer Page
 * 13/05/2024
 */
public class Algorithm
{
    // instance variables - replace the example below with your own
    private int x;
    public Nodes Start;
    public Nodes End;
    Scanner keyboard;
    /**
     * Constructor for objects of class Algorithm
     */
    public Algorithm()
    {
        Start = new Nodes(1);
        
        // initialise instance variables
        x = 0;
        System.out.println("Number of Nodes? (integer)");
        keyboard=new Scanner(System.in);
        int number=keyboard.nextInt();
        End = new Nodes(number);
        System.out.print(number);
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
    
    public int CreateConnections(int number){
        return number;
    }
}
