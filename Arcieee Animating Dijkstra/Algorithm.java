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
    public Nodes start; //Creates start Node
    public Nodes end; //Creates end Node
    public Nodes[][] arrayNodes; //Creates an array of nodes.
    public int number; //Integer when requested to do things a certain number of times
    Scanner keyboard; //Keyboard
    public String[] names; //Array to hold Nodes' names
    public DistanceChecker distanceChecker; //The actual Dijkstra's Algorithm part
    Graphics2d myGraphic; //Graphic so Algorithm can Interact with Graphic
    public int[][] linePos = null; //Int array to hold Line Positions for future use

    /**
     * Constructor for objects of class Algorithm
     */
    public Algorithm(Graphics2d myGraphic) //Constuctor requires graphic so Algorithm can interact with it
    {
        this.myGraphic=myGraphic; //Update Algorithm instance variable
    }

    public void determineConnections(int number){ //When Asked to generate random connections
        int j=0;
        for(int i=1; i<number-1; i++){ //For the number of times equal to 2 minus the number of nodes
            double num=Math.random()*10+0.5;
            int numInt = (int)Math.round(num); //Generates a random integer number between 1 and 10 for weight

            start.extendedAddConnections(arrayNodes[i][0], numInt); //Adds start to Specified node that is not the start or end with specified weight
            linePos[j][0]=start.xPos; linePos[j][1]=start.yPos; linePos[j][2]=arrayNodes[i][0].xPos; linePos[j][3]=arrayNodes[i][0].yPos; linePos[j][4]=numInt; linePos[j][5]=0; 
            start.connectionsID[arrayNodes[i][0].identity]=j; arrayNodes[i][0].connectionsID[start.identity]=j; j++; //Updates Arrays needed for Graphic

            num=Math.random()*10+0.5;
            numInt = (int)Math.round(num); //Generates a random integer number between 1 and 10 for weight  

            end.extendedAddConnections(arrayNodes[i][0], numInt); //Adds end to Specified node that is not the start or end with specified weight
            linePos[j][0]=end.xPos; linePos[j][1]=end.yPos; linePos[j][2]=arrayNodes[i][0].xPos; linePos[j][3]=arrayNodes[i][0].yPos; linePos[j][4]=numInt; linePos[j][5]=0; 
            end.connectionsID[arrayNodes[i][0].identity]=j; arrayNodes[i][0].connectionsID[end.identity]=j; j++; //Updates Arrays needed for Graphic
        }
    }

    public Nodes returnNode(int nodeIdentity){ //Return identity of a specified node (testing thing)
        System.out.println(arrayNodes[nodeIdentity][0].identity); //Print to console
        return arrayNodes[nodeIdentity][0]; //Return
    }

    public void runAlgorithm(){ //When asked to Run Dijkstra's algorithm
        if(arrayNodes!=null&&arrayNodes.length!=0){ //If there is a graph to draw
            if(myGraphic==null){myGraphic = new Graphics2d(arrayNodes, linePos, this);} else {myGraphic.startup(arrayNodes, linePos);}//Update MyGraphic so it can draw the output of the algorithm
            distanceChecker = new DistanceChecker(myGraphic); //Initialize a distance checker to run Dijkstra's algorithm
            distanceChecker.setAlgorithm(this); //Inform the distance checker that this is the algorithm
            System.out.println("The shortest path is "+distanceChecker.path(start, end)+" long"); //prints out the Distance value to the console
        } else{System.out.println("No graph to run algorithm on, please either import a graph, generate a random graph, or pick a sample graph from the menu!");} //Else, informs the user of the bad news.
    }

    public void setNodeNumber(int number){ //When asked to create a specified number of nodes
        //Legacy Text-Based Input

        // initialise instance variables
        // System.out.println("Number of Nodes? (integer)"); //Prompts user for number of nodes
        // keyboard=new Scanner(System.in);
        // number=keyboard.nextInt(); //retrieves value

        arrayNodes = new Nodes[number][1]; //Sets arrayNodes to an array containing Nodes of length requested
        linePos = new int[(number-2)*2][6]; //And LinePos array to length requested

        for (int i=0; i<number; i++) //For However many times equal to the number of nodes;
        {arrayNodes[i][0]=new Nodes(i); //Let each element in the Array equal a new node with the corrosponding identity.
            arrayNodes[i][0].initialiseConnections(number); //This Initializes connections of each node. Using my automatic connection scheme
            arrayNodes[i][0].name =i+""; arrayNodes[i][0].generateRandomNumber(); arrayNodes[i][0].setPosition(arrayNodes[i][0].xran, arrayNodes[i][0].yran); //Sets names and random position for Nodes.
        }

        start = arrayNodes[0][0]; //Sets start Node equal to Node with identity 0
        end = arrayNodes[number-1][0]; //Sets start Node equal to Node with identity (Nodes requested - 1)

        determineConnections(number); //Determine connections between each node
        this.number=number; //Save for later use
        System.out.println("Done! Select Run Algorithm to view!"); //Done!
    }

    public void importGraph(String[][] nodes, String[][] connections){ //Responsible for processing imports of a graph
        arrayNodes = new Nodes[nodes.length][1]; //Sets arrayNodes to an array containing Nodes of length requested
        names = new String[nodes.length]; //And name array to length requested
        linePos = new int[connections.length][6]; //And LinePos array to length requested
        try{ //If the Node Information is done correctly, this should succeed
            for(int i=0;i<nodes.length;i++){
                //Create Node, Initialize Nodes's connection array, and update name and set position of node according to info given.
                arrayNodes[i][0]=new Nodes(i); arrayNodes[i][0].initialiseConnections(nodes.length); arrayNodes[i][0].name = nodes[i][0]; arrayNodes[i][0].setPosition(Integer.parseInt(nodes[i][1]), Integer.parseInt(nodes[i][2]));
                names[i] = nodes[i][0];
            }
        } catch (Exception e) {System.out.println("Problem with Node Declaration");} //If try fails, Print Error message

        int h=0; int i=0; int j=0; int k=0; //Variables for use below

        try{
            for(j=0; j<connections.length; j++){ //For however many connections we have
                while(!connections[j][0].equals(names[i])){ //While our connections element doesn't equal the name of our selected node
                    i++; //Increase counter by 1
                }
                k=i; i=0;
                while(!connections[j][1].equals(names[i])){ //While our connections element doesn't equal the name of our selected node
                    i++; //Increase counter by 1
                }
                //Updates LinePosition to corrosponding values, and connects the specified 2 nodes and updates each nodes connection array, aswell as this Algorithms Node array.
                arrayNodes[k][0].extendedAddConnections(arrayNodes[i][0], Integer.parseInt(connections[j][2])); arrayNodes[k][0].connectionsID[arrayNodes[i][0].identity]=j; arrayNodes[i][0].connectionsID[arrayNodes[k][0].identity]=j;
                linePos[j][0]=arrayNodes[k][0].xPos; linePos[j][1]=arrayNodes[k][0].yPos; linePos[j][2]=arrayNodes[i][0].xPos; linePos[j][3]=arrayNodes[i][0].yPos; linePos[j][4]=Integer.parseInt(connections[j][2]); linePos[j][5]=0;
                i=0; //reset counter
            }
        } catch (Exception e){System.out.println("A Connection does not exist, any subsequent connections present have been ignored");} //If there is a connection that does not exist, or wrong indexes, this error message is printed

        try{start = arrayNodes[0][0]; //Sets start Node equal to Node with identity 0
            end = arrayNodes[nodes.length-1][0]; //Sets start Node equal to Node with identity (Nodes requested - 1)
            System.out.println("Done"); //Done!
        } catch (Exception e) {System.out.println("Graph contains no nodes!!!");} //Oh no! There is no element at [0][0]!

    }

    public void setDistanceChecker(DistanceChecker a){ //Updates DistanceChecker variable so Algorithm can talk to DistanceChecker
        distanceChecker = a;
    }

    public void setStart(int startID){ //Changes start to node specified with id
        start=arrayNodes[startID][0];
    }

    public void setEnd(int endID){ //Changes end to node specified with id
        end=arrayNodes[endID][0];
    }
}
