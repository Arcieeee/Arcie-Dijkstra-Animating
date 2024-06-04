
/**
 * Write a description of class DistanceChecker here.
 *
 * @author (your name)
 * @version (a version End.Identity or a date)
 */
public class DistanceChecker
{
    public Nodes Current = null;
    public Algorithm Algorithm = null;
    
    public DistanceChecker(){
        
    }
    
    public void setAlgorithm(Algorithm Algorithm){
        this.Algorithm = Algorithm;
    }
    
    public int Path(Nodes Start, Nodes End){
        Current = Start; //Sets the current searched node to the start
        int CurrentDistance = 0; //initialize Distance
        int[][] IdentityDistance = new int[End.Identity+1][3]; //Creates an array as many nodes long with 2 rows, one for checking if a node is already visited, the other for distance
        for(int h=0; h<End.Identity; h++){
            IdentityDistance[h][0] = 0;
            IdentityDistance[h][1] = 2147483647; //sets everything in row 1 to 0; row 2 to max int.
            IdentityDistance[h][2] = 2147483647;
        }
        IdentityDistance[0][1] = 0; //except for the start
        IdentityDistance[0][2] = 0; //except for the start
        int i=0;
        
        //at this point we have an array full of 0s and max int, and a start distance 0. 
        
          while(Current != End){ //while we are not at the end
           while(i<End.Identity){  //While we haven't searched every connection 
               while(Current.Connections[i]==null){ //Goes through until finds valid connection.
               i++;
           }
           if(IdentityDistance[Current.Connections[i].Identity][0]==0) { //if the node isn't visited, set the distance to that point equal to distance to current node + distance to the next node
               IdentityDistance[Current.Connections[i].Identity][0] = 1;
               IdentityDistance[Current.Connections[i].Identity][1] = IdentityDistance[Current.Identity][1] + Current.Connections[i].distance;
               IdentityDistance[Current.Connections[i].Identity][2] = IdentityDistance[Current.Connections[i].Identity][1];
            } else if (IdentityDistance[Current.Connections[i].Identity][1]>IdentityDistance[Current.Identity][1]+Current.Connections[i].distance){ //same, but first check to see whether the already known distance value is larger than the new one
               IdentityDistance[Current.Connections[i].Identity][0] = 1;
               IdentityDistance[Current.Connections[i].Identity][1] = IdentityDistance[Current.Identity][1] + Current.Connections[i].distance;
               IdentityDistance[Current.Connections[i].Identity][2] = IdentityDistance[Current.Connections[i].Identity][1];
            }
           if(CurrentDistance == IdentityDistance[Current.Identity][1]){CurrentDistance = IdentityDistance[Current.Connections[i].Identity][1]; } //If this is the first branch from the node, set Current Distance to the new distance.
           else if (CurrentDistance > IdentityDistance[Current.Connections[i].Identity][1]){CurrentDistance = IdentityDistance[Current.Connections[i].Identity][1];} //else if the distance travelled is larger that previously known largest distance, update distance.
           i++;
          }
          
          //At this point, we have evaluated all connections of the current Node.
          
          IdentityDistance[Current.Identity][1]=2147483647; //sets distance to current node to max int to indicate it has been searched.
          i=0;
          while(IdentityDistance[i][1]>CurrentDistance){i++;} //Goes through the array until finds a distance that is equal or less than the current distance (guarenteed)
          if (i!=End.Identity) //if we are not at the end
          {Current = Algorithm.ArrayNodes[i];} else {Current = End;} //Current = Node with that identity.
          
         }
        return CurrentDistance;
    }
}
