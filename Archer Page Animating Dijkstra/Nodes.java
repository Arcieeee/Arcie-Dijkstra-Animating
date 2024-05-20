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
  public int distance;
  public Nodes[] Connections;
  public int Identity;
    /**
     * Constructor for objects of class Nodes
     */
    public Nodes(int Identity)
  { 
         double num=Math.random()*10+1;
         distance = (int)Math.round(num); //Generates a random integer number between 1 and 10
         this.Identity=Identity;
  }
  
  public void RandomNumOld()
  {
      double x = Math.random()*10+1;
      x = x - x%1;
      System.out.println(x);
  }
  
  public void Connections(int x){
      Nodes[] Connections = new Nodes[x]; //Creates an array of Nodes called connections  
  }
}
