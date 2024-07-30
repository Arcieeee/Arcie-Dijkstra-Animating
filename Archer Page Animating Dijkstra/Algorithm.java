import javax.swing.*;
import java.awt.*;
import java.awt.event.*; // Listener
import java.awt.image.BufferedImage;  // buffered image to reduce flickering.
import java.util.Scanner; // Keyboard input.
/**
 * The Algorithm responsible for most of the Behind the scenes processing of user input and requests
 *
 * Archer Page
 * 22/07/2024
 */
public class Algorithm
{
    public Nodes Start; //Creates Start Node
    public Nodes End; //Creates End Node
    public Nodes[][] ArrayNodes; //Creates an array of nodes.
    public int number; //Integer when requested to do things a certain number of times
    Scanner keyboard; //Keyboard
    public String[] Names; //Array to hold Nodes' Names
    public DistanceChecker DistanceChecker; //The actual Dijkstra's Algorithm part
    Graphics2d myGraphic; //Graphic so Algorithm can Interact with Graphic
    public int[][] linePos; //Int array to hold Line Positions for future use

    /**
     * Constructor for objects of class Algorithm
     */
    public Algorithm(Graphics2d myGraphic) //Constuctor requires graphic so Algorithm can interact with it
    {
        this.myGraphic=myGraphic; //Update Algorithm instance variable
    }

    public void DetermineConnections(int number){ //When Asked to generate random connections
        int j=0;
        for(int i=1; i<number-1; i++){ //For the number of times equal to 2 minus the number of nodes
            double num=Math.random()*10+0.5;
            int numInt = (int)Math.round(num); //Generates a random integer number between 1 and 10 for weight

            Start.ExtendedAddConnections(ArrayNodes[i][0], numInt); //Adds start to Specified node that is not the start or end with specified weight
            linePos[j][0]=Start.xPos; linePos[j][1]=Start.yPos; linePos[j][2]=ArrayNodes[i][0].xPos; linePos[j][3]=ArrayNodes[i][0].yPos; linePos[j][4]=numInt; linePos[j][5]=0; j++;

            num=Math.random()*10+0.5;
            numInt = (int)Math.round(num); //Generates a random integer number between 1 and 10 for weight  

            End.ExtendedAddConnections(ArrayNodes[i][0], numInt); //Adds end to Specified node that is not the start or end with specified weight
            linePos[j][0]=End.xPos; linePos[j][1]=End.yPos; linePos[j][2]=ArrayNodes[i][0].xPos; linePos[j][3]=ArrayNodes[i][0].yPos; linePos[j][4]=numInt; linePos[j][5]=0; j++;
        }
    }

    public Nodes ReturnNode(int NodeIdentity){ //Return Identity of a specified node
        System.out.println(ArrayNodes[NodeIdentity][0].Identity); //Print to console
        return ArrayNodes[NodeIdentity][0]; //Return
    }

    public void RunAlgorithm(){ //When asked to Run Dijkstra's algorithm
        if(myGraphic==null){myGraphic = new Graphics2d(ArrayNodes, linePos, this);} else {myGraphic.startup(ArrayNodes, linePos);}//Update MyGraphic so it can draw the output of the algorithm
        DistanceChecker = new DistanceChecker(myGraphic); //Initialize a distance checker to run Dijkstra's algorithm
        DistanceChecker.setAlgorithm(this); //Inform the distance checker that this is the algorithm
        System.out.println("The shortest path is "+DistanceChecker.Path(Start, End)+" long"); //prints out the distance value to the console
    }

    public void SetNodeNumber(int number){ //When asked to create a specified number of nodes
        //Legacy Text-Based Input
        
        // initialise instance variables
        // System.out.println("Number of Nodes? (integer)"); //Prompts user for number of nodes
        // keyboard=new Scanner(System.in);
        // number=keyboard.nextInt(); //retrieves value

        ArrayNodes = new Nodes[number][1]; //Sets ArrayNodes to an array containing Nodes of length requested
        linePos = new int[(number-2)*2][6]; //And LinePos array to length requested
        

        for (int i=0; i<number; i++) //For However many times equal to the number of nodes;
           {ArrayNodes[i][0]=new Nodes(i); //Let each element in the Array equal a new node with the corrosponding identity.
            ArrayNodes[i][0].InitialiseConnections(number); //This Initializes connections of each node. Using my automatic connection scheme
            ArrayNodes[i][0].Name =i+""; ArrayNodes[i][0].generateRandomNumber(); ArrayNodes[i][0].SetPosition(ArrayNodes[i][0].xran, ArrayNodes[i][0].yran);
        }

        Start = ArrayNodes[0][0]; //Sets Start Node equal to Node with identity 0
        End = ArrayNodes[number-1][0]; //Sets Start Node equal to Node with identity (Nodes requested - 1)

        for (int i=0; i<number; i++){System.out.println(ArrayNodes[i][0].Identity);} //Print out each node's Indentity
        
        DetermineConnections(number); //Determine connections between each node
        this.number=number; //Save for later use
        System.out.println("Done!"); //Done!
    }

    public void ImportGraph(String[][] nodes, String[][] connections){ //Responsible for processing imports of a graph
        ArrayNodes = new Nodes[nodes.length][1]; //Sets ArrayNodes to an array containing Nodes of length requested
        Names = new String[nodes.length]; //And Name array to length requested
        linePos = new int[connections.length][6]; //And LinePos array to length requested
        try{ //If the Node Information is done correctly, this should succeed
            for(int i=0;i<nodes.length;i++){
                //Create Node, Initialize Nodes's connection array, and update name and set position of node according to info given.
                ArrayNodes[i][0]=new Nodes(i); ArrayNodes[i][0].InitialiseConnections(nodes.length); ArrayNodes[i][0].Name = nodes[i][0]; ArrayNodes[i][0].SetPosition(Integer.parseInt(nodes[i][1]), Integer.parseInt(nodes[i][2]));
                Names[i] = nodes[i][0];
            }
        } catch (Exception e) {System.out.println("Problem with Node Declaration");} //If try fails, Print Error message
        
        int h=0; int i=0; int j=0; int k=0; //Variables for use below
        
        for(j=0; j<connections.length; j++){ //For however many connections we have
            while(!connections[j][0].equals(Names[i])){ //While our connections element doesn't equal the name of our selected node
                System.out.println("String /"+connections[j][0]+"/ and String /"+Names[i]+"/ is different"); //Print out they are different
                i++; //Increase counter by 1
            }
            k=i; i=0;
            while(!connections[j][1].equals(Names[i])){ //While our connections element doesn't equal the name of our selected node
                i++; //Increase counter by 1
            }
            //Updates LinePosition to corrosponding values, and connects the specified 2 nodes and updates each nodes connection array, aswell as this Algorithms Node array.
            ArrayNodes[k][0].ExtendedAddConnections(ArrayNodes[i][0], Integer.parseInt(connections[j][2])); ArrayNodes[k][0].ConnectionsID[ArrayNodes[i][0].Identity]=j; ArrayNodes[i][0].ConnectionsID[ArrayNodes[k][0].Identity]=j;
            linePos[j][0]=ArrayNodes[k][0].xPos; linePos[j][1]=ArrayNodes[k][0].yPos; linePos[j][2]=ArrayNodes[i][0].xPos; linePos[j][3]=ArrayNodes[i][0].yPos; linePos[j][4]=Integer.parseInt(connections[j][2]); linePos[j][5]=0;
            i=0; 
        }

        System.out.println("Done"); //Done!

        Start = ArrayNodes[0][0]; //Sets Start Node equal to Node with identity 0
        End = ArrayNodes[nodes.length-1][0]; //Sets Start Node equal to Node with identity (Nodes requested - 1)

    }
    public void SetDistanceChecker(DistanceChecker a){ //Updates DistanceChecker variable so Algorithm can talk to DistanceChecker
        DistanceChecker = a;
    }
}
