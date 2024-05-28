
/**
 * Write a description of class DistanceChecker here.
 *
 * @author (your name)
 * @version (a version End.Identity or a date)
 */
public class DistanceChecker
{
    public DistanceChecker(){
        
    }
    
    public int Path(Nodes Start, Nodes End){
        Nodes Current = Start;
        int CurrentDistance = 0;
        int[][] IdentityDistance = new int[End.Identity+1][2];
        for(int h=0; h<End.Identity; h++){
            IdentityDistance[h][0] = 0;
            IdentityDistance[h][1] = 2147483647;
        }
        IdentityDistance[0][1] = 0;
        int i=0;
        
        //at this point we have an array full of 0s and max int, and A start. 
        
          while(Current != End){
           while(i<End.Identity){   
               while(Current.Connections[i]==null){ //Goes through until finds valid connection.
               i++;
           }
           if(IdentityDistance[Current.Connections[i].Identity][0]==0){
               IdentityDistance[Current.Connections[i].Identity][0] = 1;
               IdentityDistance[Current.Connections[i].Identity][1] = IdentityDistance[Current.Identity][1] + Current.Connections[i].distance;
           } else if (IdentityDistance[Current.Connections[i].Identity][1]>IdentityDistance[Current.Identity][1]+Current.Connections[i].distance){
               IdentityDistance[Current.Connections[i].Identity][0] = 1;
               IdentityDistance[Current.Connections[i].Identity][1] = IdentityDistance[Current.Identity][1] + Current.Connections[i].distance;
           }
           if(CurrentDistance == IdentityDistance[Current.Identity][1]){CurrentDistance = IdentityDistance[Current.Connections[i].Identity][1]; }
           else if (CurrentDistance > IdentityDistance[Current.Connections[i].Identity][1]){CurrentDistance = IdentityDistance[Current.Connections[i].Identity][1];}
           i++;
          }
          
          //At this point, we have evaluated all connections of the current Node.
          
          IdentityDistance[Current.Identity][1]=2147483647;
          i=0;
          while(IdentityDistance[i][1]>CurrentDistance){i++;}
          if (i!=End.Identity)
          {Current = Start.Connections[i];} else {Current = End;}
          
         }
        return CurrentDistance;
    }
}
