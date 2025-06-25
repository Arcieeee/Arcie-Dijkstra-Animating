import javax.swing.*;
import java.awt.*;
import java.awt.event.*; // Listener
import java.awt.image.BufferedImage;  // buffered image to reduce flickering.
import java.util.Scanner; // Keyboard input.
/**
 * The Algorithm responsible for most of the Behind the scenes processing of user input and requests
 *
 * Arcieee
 * 25/06/2025
 */
public class Algorithm
{
    //Creates an array of Nodes, and a Start node and End node.
    public Nodes start; 
    public Nodes end;
    public Nodes[][] arrayNodes;

    //Creates a number variable, Names array, DistanceChecker object, and Line Position array.
    public int number;
    public String[] names; 
    public DistanceChecker distanceChecker; 
    public int[][] linePos = null;

    Scanner keyboard; //Keyboard
    Graphics2d myGraphic; //Graphic 

    /**
     * Constructor for objects of class Algorithm
     */
    public Algorithm(Graphics2d myGraphic) 
    {
        this.myGraphic=myGraphic; 
    }

    public void determineConnections(int number){ //Pseudo-Randomly generates connections between random nodes.
        int j=0;
        for(int i=1; i<number-1; i++){ //For all the "Middle" Nodes.
            //Generate a random integer number between 1 and 10 for weight, then initialize a connection between "Middle" node and the start.
            double num=Math.random()*10+0.5;
            int numInt = (int)Math.round(num); 

            start.extendedAddConnections(arrayNodes[i][0], numInt); 
            linePos[j][0]=start.xPos; linePos[j][1]=start.yPos; linePos[j][2]=arrayNodes[i][0].xPos; linePos[j][3]=arrayNodes[i][0].yPos; linePos[j][4]=numInt; linePos[j][5]=0; 
            start.connectionsID[arrayNodes[i][0].identity]=j; arrayNodes[i][0].connectionsID[start.identity]=j; j++; 

            //Same as above, but for the End Node.
            num=Math.random()*10+0.5;
            numInt = (int)Math.round(num); 

            end.extendedAddConnections(arrayNodes[i][0], numInt); 
            linePos[j][0]=end.xPos; linePos[j][1]=end.yPos; linePos[j][2]=arrayNodes[i][0].xPos; linePos[j][3]=arrayNodes[i][0].yPos; linePos[j][4]=numInt; linePos[j][5]=0; 
            end.connectionsID[arrayNodes[i][0].identity]=j; arrayNodes[i][0].connectionsID[end.identity]=j; j++;
        }
    }

    public Nodes returnNode(int nodeIdentity){ //Returns identity of a specified node.
        System.out.println(arrayNodes[nodeIdentity][0].identity); 
        return arrayNodes[nodeIdentity][0]; 
    }

    public void runAlgorithm(){ //Initializes the running of Dijkstra's algorithm.
        try{if(arrayNodes!=null&&arrayNodes.length!=0){ 
                //Update MyGraphic.
                if(myGraphic==null){myGraphic = new Graphics2d(arrayNodes, linePos, this);} else {myGraphic.startup(arrayNodes, linePos);}

                //Initialize a distance checker to run Dijkstra's algorithm
                distanceChecker = new DistanceChecker(myGraphic);
                distanceChecker.setAlgorithm(this);

                //Runs Dijkstras algorithm, and prints the result.
                System.out.println("The shortest path is "+distanceChecker.path(start, end)+" long");
            } else{System.out.println("No graph to run algorithm on, please either import a graph, generate a random graph, or pick a sample graph from the menu!");}
        } catch (Exception e){System.out.println("Invalid Graph given, likely due to an Import that contained error(s)");}
    }

    public void setNodeNumber(int number){ //Creates n nodes.
        //Legacy Text-Based Input

        // initialise instance variables
        // System.out.println("Number of Nodes? (integer)"); //Prompts user for number of nodes
        // keyboard=new Scanner(System.in);
        // number=keyboard.nextInt(); //retrieves value

        //Sets array sizes
        arrayNodes = new Nodes[number][1]; 
        linePos = new int[(number-2)*2][6];

        for (int i=0; i<number; i++) 
        {   arrayNodes[i][0]=new Nodes(i); //Sets each element in the Array equal a new node with the corrosponding identity.
            arrayNodes[i][0].initialiseConnections(number); //This Initializes connections of each node. Using my automatic connection scheme
            arrayNodes[i][0].name =i+""; arrayNodes[i][0].generateRandomNumber(); arrayNodes[i][0].setPosition(arrayNodes[i][0].xran, arrayNodes[i][0].yran); //Sets names and random position for Nodes.
        }

        //Set start and end nodes.
        start = arrayNodes[0][0]; 
        end = arrayNodes[number-1][0]; 

        determineConnections(number); 
        this.number=number; 
        
        System.out.println("Done! Select Run Algorithm to view!"); //Done!
    }

    public void importGraph(String[][] nodes, String[][] connections){ //Processes User input into a graph.
        //Initialize Nodes, Names, and LinePos arrays
        arrayNodes = new Nodes[nodes.length][1]; 
        names = new String[nodes.length]; 
        linePos = new int[connections.length][6]; 
        
        try{ 
            for(int i=0;i<nodes.length;i++){
                //Create Node, Initialize Nodes's connection array, and update name and set position of node according to info given.
                arrayNodes[i][0]=new Nodes(i); arrayNodes[i][0].initialiseConnections(nodes.length); arrayNodes[i][0].name = nodes[i][0]; arrayNodes[i][0].setPosition(Integer.parseInt(nodes[i][1]), Integer.parseInt(nodes[i][2]));
                names[i] = nodes[i][0];
            }
        } catch (Exception e) {System.out.println("Problem with Node Declaration");} //Error message

        int nodeID=0; int firstNodeID=0; int connectionID=0; //Variables for use below

        try{
            for(connectionID=0; connectionID<connections.length; connectionID++){ //Iterates through all connections
                //Iterates through all Node names until a match is found.
                while(!connections[connectionID][0].equals(names[nodeID])){ 
                    nodeID++;
                }
                firstNodeID=nodeID; nodeID=0; //And Stores the result.
                while(!connections[connectionID][1].equals(names[nodeID])){ 
                    nodeID++;
                }
                
                //Updates LinePosition to corrosponding values, and connects the specified 2 nodes and updates each nodes connection array, aswell as this Algorithms Node array.
                arrayNodes[firstNodeID][0].extendedAddConnections(arrayNodes[nodeID][0], Integer.parseInt(connections[connectionID][2])); arrayNodes[firstNodeID][0].connectionsID[arrayNodes[nodeID][0].identity]=connectionID; arrayNodes[nodeID][0].connectionsID[arrayNodes[firstNodeID][0].identity]=connectionID;
                linePos[connectionID][0]=arrayNodes[firstNodeID][0].xPos; linePos[connectionID][1]=arrayNodes[firstNodeID][0].yPos; 
                linePos[connectionID][2]=arrayNodes[nodeID][0].xPos; linePos[connectionID][3]=arrayNodes[nodeID][0].yPos; 
                linePos[connectionID][4]=Integer.parseInt(connections[connectionID][2]); linePos[connectionID][5]=0; nodeID=0;
            }
        } catch (Exception e){System.out.println("A Connection does not exist, any subsequent connections present have been ignored");} //If there is a connection that does not exist, or wrong indexes, this error message is printed

        try{start = arrayNodes[0][0]; end = arrayNodes[nodes.length-1][0]; //Set start and end Nodes.
            System.out.println("Done"); //Done!
        } catch (Exception e) {System.out.println("Graph contains no nodes!!!");} //Error Message.

    }

    public void setDistanceChecker(DistanceChecker a){ //Updates DistanceChecker variable.
        distanceChecker = a;
    }

    public void setStart(int startID){ //Changes start to node specified.
        start=arrayNodes[startID][0];
    }

    public void setEnd(int endID){ //Changes end to node specified.
        end=arrayNodes[endID][0];
    }
}