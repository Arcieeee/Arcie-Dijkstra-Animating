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
    public Nodes[] ArrayNodes;
    Scanner keyboard;
    /**
     * Constructor for objects of class Algorithm
     */
    public Algorithm()
    {
        
        
        // initialise instance variables
        System.out.println("Number of Nodes? (integer)"); //Prompts user for number of nodes
        keyboard=new Scanner(System.in);
        int number=keyboard.nextInt(); //retrieves value
        
        Nodes[] ArrayNodes = new Nodes[number]; //possibly one too many
        for (int i=0; i<number; i++){ArrayNodes[i]=new Nodes(i); 
            ArrayNodes[i].InitialiseConnections(number);}
        Start = ArrayNodes[0]; //creates starting node
        End = ArrayNodes[number-1];
    }
    
    public int CreateNodes(int number){
        return number;
    }
    
    public void CreateConnections(int number){
        
    }
    
    public void DetermineConnections(int number){
      for(int i=1; i<number; i++){Start.AddConnections(ArrayNodes[i]);}
    }
}
