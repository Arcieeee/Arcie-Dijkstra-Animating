import java.lang.Math; //Maths for Math.random()
/**
 * The Nodes class. This models a node in the graph.
 *
 * Arcieee
 * 25/06/2025
 */
public class Nodes
{
    //Initializes: Distance array for connection weights, Connections array, Connections ID array, Node Identity array, Name, xPos & yPos, and random nums.
    public int[] distance; 
    public Nodes[] connections; 
    public int[] connectionsID; 
    public int identity; 
    public String name;
    public int xPos; 
    public int yPos; 
    public int xran; public int yran;

    //Constructor
    public Nodes(int identity) //Requires an Identity value as a reference.
    { 
        double num=Math.random()*10+0.5;
        num = (int)Math.round(num); //Generates a random integer number between 1 and 10, used in random graph generation  

        this.identity=identity;
        name=this.identity+""; //Default for name is id.
    }

    public void randomNumOld() //old RNG integer generator (legacy)
    {
        double x = Math.random()*10+1;
        x = x - x%1;
        System.out.println(x);
    }

    public void addConnections(Nodes node, int weight){ //Method updates connections array
        connections[node.identity] = node; distance[node.identity] = weight; //Updates connections, respective weights updated aswell.

        //Legacy Console output that could give the user information about their graph.
        /**if(node.identity<identity) //Going from Earlier to Later when printing out Connection message
         *System.out.println("Connection "+node.name+" - "+name+" Initialized with weight "+distance[node.identity]); //Connection from node1 to node2 Initialized with weight weight1
         *else //If not, reverse Position of Elements in the message
         *System.out.println("Connection "+name+" - "+node.name+" Initialized with weight "+node.distance[this.identity]); //Connection from node1 to node2 Initialized with weight weight1
         */
    }

    public void initialiseConnections(int x){ //Initialize Connections array & Distance array.
        connections = new Nodes[x]; connectionsID = new int[x]; 
        distance = new int[x]; 
        for(int i=0; i<x; i++){distance[i]=2147483647;} //Fills array with Max Int since no distances are recorded yet
    }

    public void extendedAddConnections(Nodes node, int weight){ //If this method is called, it will update the connections of the node this node is connecting to.
        connections[node.identity] = node; distance[node.identity] = weight; 
        connections[node.identity].addConnections(this, weight); //Triggers Update for Self, and Connected node
    }

    public void generateRandomNumber(){ //Generates Random Numbers for use
        double num=Math.random()*800+0.5;
        xran = (int)Math.round(num); //Generates a random integer number between 1 and 800
        num=Math.random()*800+0.5;    yran = (int)Math.round(num); //Generates a random integer number between 1 and 800
    }

    public void setPosition(int x, int y){ //Updates This Nodes' position.
        xPos = x; yPos = y;
    }
}
