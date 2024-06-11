
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
        Current = Start; //Sets the Current searched node to the start
        int CurrentDistance = 0; //initialize Distance
        int[][] IdentityDistance = new int[End.Identity+1][3]; //Creates an array as many nodes long with 2 rows, one for checking if a node is already visited, the other for distance
        int unvisited=0; int visited=1; int searched=2; int Status=0; int SearchDistance=1; int TrueDistance=2;
        
        for(int i=1; i<End.Identity+1; i++){
            IdentityDistance[i][Status] = 0;
            IdentityDistance[i][SearchDistance] = 2147483647; //sets everything in row 1 to 0; row 2 and 3 to max int.
            IdentityDistance[i][TrueDistance] = 2147483647;
        }
        
        IdentityDistance[0][SearchDistance] = 0; IdentityDistance[0][TrueDistance] = 0; //except for the start
       
        int i=0; //Counter = 0
        //at this point we have an array full of 0s and max int, and a start distance 0. 
        
          while(Current != End){ //While we are not at the end
           while(i<=End.Identity){  
           
          int x=0;    
               while(Current.Connections[i]==null&&x==0){ //Goes all the connections of the current node until it finds one, or until it runs out of connections to search
               i++; if (i>Current.Connections.length){i=Current.Connections.length; x=1;}
          }
           
           if(x==0){ //if we have a connection
         
           if(IdentityDistance[Current.Identity][SearchDistance] + Current.Connections[i].distance<IdentityDistance[Current.Connections[i].Identity][TrueDistance]){ //if the Search Distance to the node is less than the True Distance of the node
                
                  IdentityDistance[Current.Connections[i].Identity][Status] = visited; //Set node Status to visited
                  IdentityDistance[Current.Connections[i].Identity][SearchDistance] = IdentityDistance[Current.Identity][SearchDistance] + Current.Connections[i].distance; //Update Search Distance
                  IdentityDistance[Current.Connections[i].Identity][TrueDistance] = IdentityDistance[Current.Connections[i].Identity][SearchDistance]; //Set True Distance to Search Distance
                  
                }

           if(CurrentDistance == IdentityDistance[Current.Identity][SearchDistance]){CurrentDistance = IdentityDistance[Current.Connections[i].Identity][1]; } //If this is the first branch from the node, set Current Distance to the new distance.
           else if (CurrentDistance > IdentityDistance[Current.Connections[i].Identity][SearchDistance]){CurrentDistance = IdentityDistance[Current.Connections[i].Identity][1];} //else if the distance travelled is larger that previously known largest distance, update distance.
          } i++;
          }
          
          //At this point, we have evaluated all connections of the current Node.
          IdentityDistance[Current.Identity][SearchDistance]=2147483647; //sets search distance to current node to max int to prevent it from being selected again unless a shorter path is found to it.
          IdentityDistance[Current.Identity][Status]=searched;
          int x=0;
          
          for(i=0; i<=End.Identity; i++){
              if(IdentityDistance[i][SearchDistance]<=CurrentDistance){
                  x=i; CurrentDistance=IdentityDistance[i][SearchDistance];}}
          
          if (x!=End.Identity) //if we are not at the end
          {Current = Algorithm.ArrayNodes[x];} 
          else {Current = End;} //Current = Node with that identity.
          
          i=0;
         }
        return CurrentDistance;
    }
}
