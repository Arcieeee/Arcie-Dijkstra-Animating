import java.lang.Math; //Maths for Math.random()
/**
 * The Nodes class. Responsible for being the Graph.
 *
 * Archer
 * 30/07/2024
 */
public class Nodes
{
  public int[] distance; //Distance array to hold weight values for each connection
  public Nodes[] Connections; //Array to hold Connections
  public int[] ConnectionsID; //Array to hold what ID each connection is
  public int Identity; //Identity of Node
  public String Name; //Name given to Node
  public int xPos; //xPos
  public int yPos; //yPos
  public int xran; public int yran; //random num
  
  //Constuctor
    public Nodes(int Identity) //Gets Identity
  { 
      double num=Math.random()*10+0.5;
      num = (int)Math.round(num); //Generates a random integer number between 1 and 10, used in random graph generation  
      
      
      this.Identity=Identity; //Instance variable Identity = Local Variable Identity 
      Name=this.Identity+""; //Default for Name
  }
  
  public void RandomNumOld() //old RNG integer generator (legacy)
  {
      double x = Math.random()*10+1;
      x = x - x%1;
      System.out.println(x);
  }
  
  public void AddConnections(Nodes Node, int weight){ //Method that tells Node to update Connections array
      Connections[Node.Identity] = Node; distance[Node.Identity] = weight; //Connection [Node ID] updated to connected node, corrosponsing distance array element updated to hold weight
      if(Node.Identity<Identity) //Going from Earlier to Later when printing out Connection message
      System.out.println("Connection "+Node.Name+" - "+Name+" Initialized with weight "+distance[Node.Identity]); //Connection from Node1 to Node2 Initialized with weight weight1
      else //If not, reverse Position of Elements in the message
      System.out.println("Connection "+Name+" - "+Node.Name+" Initialized with weight "+Node.distance[this.Identity]); //Connection from Node1 to Node2 Initialized with weight weight1
  }

  public void InitialiseConnections(int x){ //Initialize Connections array
      Connections = new Nodes[x]; ConnectionsID = new int[x]; //Size of array
      distance = new int[x]; //Distance array too
      for(int i=0; i<x; i++){distance[i]=2147483647;} //Fill Distance array with Max Int since no distances recorded yet
  }

  public void ExtendedAddConnections(Nodes Node, int weight){ //If this node is told to add a connection via this method, it will tell the node it is connecting to to update it's connections aswell.
      Connections[Node.Identity] = Node; distance[Node.Identity] = weight; //Updates Self
      Connections[Node.Identity].AddConnections(this, weight); //Triggers Update for connected Node
  }
  
  public void generateRandomNumber(){ //Generates Random Numbers for use
     double num=Math.random()*800+0.5;
     xran = (int)Math.round(num); //Generates a random integer number between 1 and 800
     num=Math.random()*800+0.5;    yran = (int)Math.round(num); //Generates a random integer number between 1 and 800
  }
  
  public void SetPosition(int x, int y){ //Updates This Nodes' position to given values
      xPos = x; yPos = y;
  }
}
