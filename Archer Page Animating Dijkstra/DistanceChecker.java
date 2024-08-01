import javax.swing.*;
import java.awt.*;
import java.awt.event.*; // Listener
import java.awt.image.BufferedImage;  // buffered image to reduce flickering.
/**
 * This is the class that runs Dijkstra's algorithm on the specified graph given.
 *
 * Archer Page
 * 30/07/2024
 */
public class DistanceChecker
{
    public Nodes current = null; //current Node
    public Algorithm algorithm = null; //Algorithm so they can talk
    Graphics2d myGraphic; //Graphic so they can talk

    public DistanceChecker(Graphics2d myGraphic){ //Upon construction update Graphic
        this.myGraphic=myGraphic;
    }

    public void setAlgorithm(Algorithm algorithm){ //Update Algorithm
        this.algorithm = algorithm;
    }

    public int path(Nodes start, Nodes end){ //When asked to find the shortest Path Between 2 nodes, this method works out the shortest distance

        current = start; //Sets the current searched node to the start
        int currentDistance = 0; //initialize Distance
        int[][] identityDistance = new int[end.identity+1][4]; //Creates an array as many nodes long with 2 rows, one for checking if a node is already visited, the other for distance
        int unvisited=0; int visited=1; int searched=2; int status=0; int searchDistance=1; int trueDistance=2;

        for(int i=1; i<end.identity+1; i++){
            identityDistance[i][status] = 0;
            identityDistance[i][searchDistance] = 2147483647; //sets everything in row 1 to 0; row 2 and 3 to max int.
            identityDistance[i][trueDistance] = 2147483647;
        }

        identityDistance[0][searchDistance] = 0; identityDistance[0][trueDistance] = 0; //except for the start

        int i=0; //Counter = 0
        //at this point we have an array full of 0s and max int, and a start distance 0. 

        while(current != end){ //While we are not at the end
            while(i<=end.identity){  

                int x=0;    
                while(current.connections[i]==null&&x==0){ //Goes all the connections of the current node until it finds one, or until it runs out of connections to search
                    i++; if (i>current.connections.length-1){i=current.connections.length-1; x=1;}
                }

                if(x==0){ //if we have a connection
                    if(identityDistance[current.identity][searchDistance] + current.distance[i]<identityDistance[current.connections[i].identity][trueDistance]){ //if the Search Distance to the node is less than the True Distance of the node
                        identityDistance[current.connections[i].identity][status] = visited; //Set node status to visited
                        identityDistance[current.connections[i].identity][searchDistance] = identityDistance[current.identity][searchDistance] + current.distance[i]; //Update Search Distance
                        identityDistance[current.connections[i].identity][trueDistance] = identityDistance[current.connections[i].identity][searchDistance]; //Set True Distance to Search Distance
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
            identityDistance[current.identity][searchDistance]=2147483647; //sets search distance to current node to max int to prevent it from being selected again unless a shorter path is found to it.
            identityDistance[current.identity][status]=searched;
            int x=0;

            for(i=0; i<=end.identity; i++){
                if(identityDistance[i][searchDistance]<=currentDistance){
                    x=i; currentDistance=identityDistance[i][searchDistance];}}

            if (x!=end.identity) //if we are not at the end
            {current = algorithm.arrayNodes[x][0];} 
            else {current = end;} //current = Node with that identity.

            i=0;
        }
        
        while(current!=start){
            myGraphic.linePos[current.connectionsID[identityDistance[current.identity][3]]][5]=2;
            current=algorithm.arrayNodes[identityDistance[current.identity][3]][0];
        }
        return currentDistance;
    }
}
