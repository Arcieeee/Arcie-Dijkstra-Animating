import java.util.*;
/**
 * Write a description of class Arrays here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Arrays
{
    // instance variables - replace the example below with your own
    private int x;
    int[][] board = new int[6][4];
    private String[] roomName = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private String bob;
    private int y;
    /**
     * Constructor for objects of class Arrays
     */
    public Arrays()
    {
        System.out.println("Enter the Room Names for each room.");
        for (int rName=0; rName < 10; rName++){
            System.out.println("Enter the Room Name for room: "+(rName+1));
            Scanner in =new Scanner(System.in);
            String bob = in.nextLine();
            roomName[rName]=bob;
        }
        
        for (int x=0; x<6; x++)
           for (int y=0; y<4; y++)
               board[x][y]=(x+y);
               
        
    }
    public void print(){
        for (int i=9; i>=0; i--){
            System.out.println("Name for room: "+(i+1)+" is "+roomName[i]); 
        }
        
    }
    
    public void printTable(){
        for (int x=0; x<6; x++){
            for (int y=0; y<4; y++)
                System.out.print(board[x][y]+"  ");
            System.out.println();   
        }  
    }
}
