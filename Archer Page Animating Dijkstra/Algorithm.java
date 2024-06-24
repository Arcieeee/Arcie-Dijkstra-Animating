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
    public Nodes[][] ArrayNodes; //Creates an array of nodes.
    public int number;
    Scanner keyboard;
    public String[] Names;
    /**
     * Constructor for objects of class Algorithm
     */
    public Algorithm()
    {
        
    
    }
    
    public void DetermineConnections(int number){
      for(int i=1; i<number-1; i++){Start.ExtendedAddConnections(ArrayNodes[i][0]);
        End.ExtendedAddConnections(ArrayNodes[i][0]);
      }
    }
    
    public Nodes ReturnNode(int NodeIdentity){
        System.out.println(ArrayNodes[NodeIdentity][0].Identity);
        return ArrayNodes[NodeIdentity][0];
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
        
        ArrayNodes = new Nodes[number][2]; //Sets ArrayNodes to an array containing Nodes of length requested
        
        for (int i=0; i<number; i++) //For However many times equal to the number of nodes;
        {ArrayNodes[i][0]=new Nodes(i); //Let each element in the Array equal a new node with the corrosponding identity.
         ArrayNodes[i][0].InitialiseConnections(number);} //This Initializes connections of each node. Using my automatic connection scheme
       
        Start = ArrayNodes[0][0]; //Sets Start Node equal to Node with identity 0
        End = ArrayNodes[number-1][0]; //Sets Start Node equal to Node with identity (Nodes requested - 1)
          
        for (int i=0; i<number; i++){System.out.println(ArrayNodes[i][0].Identity);}
        DetermineConnections(number);
        this.number=number;
        System.out.println("Done!");
    }
    
    public void ImportGraph(String[][] nodes, String[][] connections){
       ArrayNodes = new Nodes[nodes.length][2]; //Sets ArrayNodes to an array containing Nodes of length requested
        for(int i=0;i<nodes.length;i++){
           ArrayNodes[i][0]=new Nodes(i);
           ArrayNodes[i][0].Name = nodes[i][0];
           Names[i] = nodes[i][0];
       }
       int h=0;
       
    int i=0; int j=0;
       for(j=0; j<connections.length; j++){
        while(connections[j][0]!=Names[i]){
           i++;
        }
        int k = i; i=0;
        while(connections[j][1]!=Names[i]){
           i++;
        }
        ArrayNodes[k][0].ExtendedAddConnections(ArrayNodes[i][0]);
        j++;
        }
    }
}
