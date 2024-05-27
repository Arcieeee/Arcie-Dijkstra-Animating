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
    public Nodes[] ArrayNodes; //Creates an array of nodes.
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
        
        ArrayNodes = new Nodes[number]; //possibly one too many
        
        for (int i=0; i<number; i++) //For However many times equal to the number of nodes;
        {ArrayNodes[i]=new Nodes(i); //Let each element in the Array equal a new node with the corrosponding identity.
         ArrayNodes[i].InitialiseConnections(number);} //This hopefully Initializes connections of each node.
       
        Start = ArrayNodes[0]; //creates starting node
        End = ArrayNodes[number-1]; //creates ending node.
        
        
        for (int i=0; i<number; i++){System.out.println(ArrayNodes[i].Identity);}
        DetermineConnections(number);
    }
    
    public int CreateNodes(int number){
        return number;
    }
    
    public void CreateConnections(int number){
        
    }
    
    public void DetermineConnections(int number){
      for(int i=1; i<number-1; i++){Start.AddConnections(ArrayNodes[i]);
        ArrayNodes[i].AddConnections(Start); ArrayNodes[i].AddConnections(End); End.AddConnections(ArrayNodes[i]);
      }
    }
    
    public Nodes ReturnNode(int NodeIdentity){
        System.out.println(ArrayNodes[NodeIdentity].Identity);
        return ArrayNodes[NodeIdentity];
    }
}
