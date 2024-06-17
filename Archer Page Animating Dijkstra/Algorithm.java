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
    public Nodes Start;
    public Nodes End;
    public Nodes[] ArrayNodes; //Creates an array of nodes.
    public int number;
    Scanner keyboard;
    /**
     * Constructor for objects of class Algorithm
     */
    public Algorithm()
    {
        
    
    }
    
    public void DetermineConnections(int number){
      for(int i=1; i<number-1; i++){Start.ExtendedAddConnections(ArrayNodes[i]);
        End.ExtendedAddConnections(ArrayNodes[i]);
      }
    }
    
    public Nodes ReturnNode(int NodeIdentity){
        System.out.println(ArrayNodes[NodeIdentity].Identity);
        return ArrayNodes[NodeIdentity];
    }
    
    public void RunAlgorithm(){
       DistanceChecker DistanceChecker = new DistanceChecker(); //Initialize a distance checker
       DistanceChecker.setAlgorithm(this); //inform the distance checker that this is the algorithm
       System.out.println(DistanceChecker.Path(Start, End)); //prints out the distance value
    }
    
    public void SetNodeNumber(int number){
        // initialise instance variables
       // System.out.println("Number of Nodes? (integer)"); //Prompts user for number of nodes
       // keyboard=new Scanner(System.in);
       // number=keyboard.nextInt(); //retrieves value
        
        ArrayNodes = new Nodes[number]; //Sets ArrayNodes to an array containing Nodes of length requested
        
        for (int i=0; i<number; i++) //For However many times equal to the number of nodes;
        {ArrayNodes[i]=new Nodes(i); //Let each element in the Array equal a new node with the corrosponding identity.
         ArrayNodes[i].InitialiseConnections(number);} //This Initializes connections of each node. Using my automatic connection scheme
       
        Start = ArrayNodes[0]; //Sets Start Node equal to Node with identity 0
        End = ArrayNodes[number-1]; //Sets Start Node equal to Node with identity (Nodes requested - 1)
          
        for (int i=0; i<number; i++){System.out.println(ArrayNodes[i].Identity);}
        DetermineConnections(number);
        this.number=number;
        System.out.println("Done!");
    }
    
    public void ImportGraph(String[] nodes, String[] connections){
        int x = nodes.length;
        ArrayNodes = new Nodes[number];
        for (int i=0; i<number; i++)
        {ArrayNodes[i]=new Nodes(i); ArrayNodes[i].Name=nodes[i];
        }

        Start = ArrayNodes[0]; //Sets Start Node equal to Node with identity 0
        End = ArrayNodes[number-1]; //Sets Start Node equal to Node with identity (Nodes requested - 1)
        
        for (int i=0; i<number; i++){System.out.println(ArrayNodes[i].Identity);}
        
    }
    
}
