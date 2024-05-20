
/**
 * Write a description of class FLoop here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FLoop
{
    // instance variables - replace the example below with your own
    private String x;
    private char Print='Z';
    private int y;

    /**
     * Constructor for objects of class FLoop
     */
    public FLoop()
    {
        // initialise instance variables
        
    }
    
    public void OnTime(){
        final int ROWS=10;
        for (int i=0; i<ROWS; i++){
            System.out.println("I must get to class on time");
        }
        }
        
    public void Lines(int NumOfLines, String Line){
        for (int i=0; i<NumOfLines; i++){
            System.out.println(i+": "+Line);
        }
    }
    
    public void Sevens(){
        for (int i=0; i<100; i+=7){
            if(i%5==0){x="***";}
            else{x="";}
            System.out.println(i+x); }
    }
    
    public void Letters(){
        for (int i=0; i<26; i++){
            if(i%3==0){System.out.println(Print);}
            Print--;
        }
        Print='Z';
    }
    
    public void AdditionTable(){
        y=0;
        for (int i=0; i<6; i++){
            for (int j=0; j<6; j++){
                System.out.print(y+" ");
                y++;
            }
            y-=5;
            System.out.println("");
        }
        
    }
}


