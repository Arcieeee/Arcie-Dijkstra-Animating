import java.lang.Math;
/**
 * Write a description of class Nodes here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Nodes
{
    // instance variables - replace the example below with your own
  public int[] distance;
  public Nodes[] Connections;
  public int[] ConnectionsID;
  public int Identity;
  public String Name;
  public int xPos;
  public int yPos;
    /**
     * Constructor for objects of class Nodes
     */
    public Nodes(int Identity)
  { 
      double num=Math.random()*10+0.5;
      num = (int)Math.round(num); //Generates a random integer number between 1 and 10   
      
      
      this.Identity=Identity;
      Name=this.Identity+"";
  }
  
  public void RandomNumOld() //old RNG integer generator
  {
      double x = Math.random()*10+1;
      x = x - x%1;
      System.out.println(x);
  }
  
  public void AddConnections(Nodes Node, int weight){
      Connections[Node.Identity] = Node; distance[Node.Identity] = weight;
      if(Node.Identity<Identity)
      System.out.println("Connection "+Node.Name+" - "+Name+" Initialized with weight "+distance[Node.Identity]);
      else
      System.out.println("Connection "+Name+" - "+Node.Name+" Initialized with weight "+Node.distance[this.Identity]);
  }

  public void InitialiseConnections(int x){
      Connections = new Nodes[x]; ConnectionsID = new int[x];
      distance = new int[x]; 
      for(int i=0; i<x; i++){distance[i]=2147483647;}
  }

  public void ExtendedAddConnections(Nodes Node, int weight){
      Connections[Node.Identity] = Node; distance[Node.Identity] = weight;
      Connections[Node.Identity].AddConnections(this, weight);
  }
  
  public void GenerateRandomNumber(int times){
      int j=0;
      while(j<times){
      double num=Math.random()*10+0.5;
      int numInt = (int)Math.round(num); //Generates a random integer number between 1 and 10
      System.out.println(numInt);
      j++;
    }
  }
  
  public void SetPosition(int x, int y){
      xPos = x; yPos = y;
  }
}
