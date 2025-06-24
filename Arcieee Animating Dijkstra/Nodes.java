import java.lang.Math; //Maths for Math.random()
/**
 * The Nodes class. Responsible for being the Graph.
 *
 * Arcieee
 * 30/07/2024
 */
public class Nodes
{
  public int[] distance; //Distance array to hold weight values for each connection
  public Nodes[] connections; //Array to hold connections
  public int[] connectionsID; //Array to hold what ID each connection is
  public int identity; //identity of node
  public String name; //name given to node
  public int xPos; //xPos
  public int yPos; //yPos
  public int xran; public int yran; //random num
  
  //Constuctor
    public Nodes(int identity) //Gets identity
  { 
      double num=Math.random()*10+0.5;
      num = (int)Math.round(num); //Generates a random integer number between 1 and 10, used in random graph generation  
      
      
      this.identity=identity; //Instance variable identity = Local Variable identity 
      name=this.identity+""; //Default for name
  }
  
  public void randomNumOld() //old RNG integer generator (legacy)
  {
      double x = Math.random()*10+1;
      x = x - x%1;
      System.out.println(x);
  }
  
  public void addConnections(Nodes node, int weight){ //Method that tells node to update connections array
      connections[node.identity] = node; distance[node.identity] = weight; //Connection [node ID] updated to connected node, corrosponsing distance array element updated to hold weight
      
      //Legacy Console output that could give the user information about their graph.
      /**if(node.identity<identity) //Going from Earlier to Later when printing out Connection message
      *System.out.println("Connection "+node.name+" - "+name+" Initialized with weight "+distance[node.identity]); //Connection from node1 to node2 Initialized with weight weight1
      *else //If not, reverse Position of Elements in the message
      *System.out.println("Connection "+name+" - "+node.name+" Initialized with weight "+node.distance[this.identity]); //Connection from node1 to node2 Initialized with weight weight1
      */
    }

  public void initialiseConnections(int x){ //Initialize connections array
      connections = new Nodes[x]; connectionsID = new int[x]; //Size of array
      distance = new int[x]; //Distance array too
      for(int i=0; i<x; i++){distance[i]=2147483647;} //Fill Distance array with Max Int since no distances recorded yet
  }

  public void extendedAddConnections(Nodes node, int weight){ //If this node is told to add a connection via this method, it will tell the node it is connecting to to update it's connections aswell.
      connections[node.identity] = node; distance[node.identity] = weight; //Updates Self
      connections[node.identity].addConnections(this, weight); //Triggers Update for connected node
  }
  
  public void generateRandomNumber(){ //Generates Random Numbers for use
     double num=Math.random()*800+0.5;
     xran = (int)Math.round(num); //Generates a random integer number between 1 and 800
     num=Math.random()*800+0.5;    yran = (int)Math.round(num); //Generates a random integer number between 1 and 800
  }
  
  public void setPosition(int x, int y){ //Updates This Nodes' position to given values
      xPos = x; yPos = y;
  }
}
