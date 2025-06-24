import javax.swing.*;
import java.awt.*;
import java.awt.event.*; // Listener
import java.awt.image.BufferedImage;  // buffered image to reduce flickering.
/**
 * This is the class that runs Dijkstra's algorithm on the specified graph given.
 *
 * Arcieee
 * 25/06/2025
 */
public class DistanceChecker
{
    //Initialize inheritance objects
    public Nodes current = null; 
    public Algorithm algorithm = null; 
    Graphics2d myGraphic;

    public DistanceChecker(Graphics2d myGraphic){ 
        this.myGraphic=myGraphic;
    }

    public void setAlgorithm(Algorithm algorithm){ //Updates this algorithm object.
        this.algorithm = algorithm;
    }

    public int path(Nodes start, Nodes end){ //When asked to find the shortest Path Between 2 nodes, this implements Dijkstras algorithm to work out the shortest path.
        //Initialize variables/arrays
        current = start; 
        int[][] identityDistance = new int[algorithm.arrayNodes.length][4]; //Creates an array of Nodes with 4 rows, 1 for checking if a node is already visited, 2 for distance, and 1 for the id of the node that updated the distance most recently.
        int currentDistance = 0; int unvisited=0; int visited=1; int searched=2; int status=0; int searchDistance=1; int trueDistance=2;
        
        //Sets Variables to initial state
        for(int i=0; i<algorithm.arrayNodes.length; i++){
            identityDistance[i][status] = 0;
            identityDistance[i][searchDistance] = 2147483647;
            identityDistance[i][trueDistance] = 2147483647;
        }
        for(int i=0; i<myGraphic.linePos.length; i++){
            myGraphic.linePos[i][5] = 0;
        }
        identityDistance[start.identity][searchDistance] = 0; identityDistance[start.identity][trueDistance] = 0; //Exception for the start.
        int i=0; //Counter = 0, at this point we have an array full of 0s and max int, and a start distance 0. 

        if(myGraphic.linePos.length==0&&current!=end){current=end; currentDistance=2147483647;}//If there are no connections from the start, and we are not at the end, current = end and return max value.

        while(current != end){ //Until we reach the end
            while(i<=algorithm.arrayNodes.length-1){  //And while there are nodes left to search
                boolean connection=true;
                while(current.connections[i]==null&&connection==true){ //Iterate through all the potential connections of the current node until a valid connection is found, or until no more potential connections.
                    i++; if (i>current.connections.length-1){i=current.connections.length-1; connection=false;} 
                }

                if(connection==true){ 
                    if(identityDistance[current.identity][searchDistance] + current.distance[i]<identityDistance[current.connections[i].identity][trueDistance]){ //If the Search Distance to the connection Node is less than the Best Known Distance to that node
                        //Sets Connection and last update ID.
                        identityDistance[current.connections[i].identity][status] = visited; 
                        identityDistance[current.connections[i].identity][searchDistance] = identityDistance[current.identity][searchDistance] + current.distance[i]; 
                        identityDistance[current.connections[i].identity][trueDistance] = identityDistance[current.connections[i].identity][searchDistance]; 
                        identityDistance[current.connections[i].identity][3]=current.identity; 
                        myGraphic.linePos[current.connectionsID[i]][5]=1; myGraphic.repaint(); 
                    }
                    if(currentDistance == identityDistance[current.identity][searchDistance])
                        currentDistance = identityDistance[current.connections[i].identity][1];  //If this is the first branch from the node, set current Distance to the new distance.

                    else if (currentDistance > identityDistance[current.connections[i].identity][searchDistance])
                        currentDistance = identityDistance[current.connections[i].identity][1]; //else if the distance travelled is smaller that previously known largest distance, update distance.
                } i++;
            }

            //At this point, we have evaluated all connections of the current Node.
            identityDistance[current.identity][searchDistance]=2147483647; //Sets search distance to current node to max int to prevent it from being selected again unless a shorter path is found to it.
            identityDistance[current.identity][status]=searched; 
            int currentSmallest=0;

            for(i=0; i<=algorithm.arrayNodes.length-1; i++){ //Goes through all nodes of the array to find the current smallest search distance
                if(identityDistance[i][searchDistance]<=currentDistance){
                    currentSmallest=i; currentDistance=identityDistance[i][searchDistance];}} 

            if (currentSmallest!=end.identity&&currentDistance!=2147483647) {current = algorithm.arrayNodes[currentSmallest][0];} //Selects the smallest unsearched node
            else {current = end;} //We have reached the end

            i=0;
        }

        if(currentDistance==2147483647){for(i=0;i<myGraphic.linePos.length;i++){myGraphic.linePos[i][5]=1;}} //If there is no path to the end, colour all connections red and return max int.
        else{ //Else starting from the end node, update each connection that is part of the shortest path to be marked as such.
            while(current!=start){ 
                myGraphic.linePos[current.connectionsID[identityDistance[current.identity][3]]][5]=2;
                current=algorithm.arrayNodes[identityDistance[current.identity][3]][0];
            }
        }

        return currentDistance; //Returns distance
    }
}
