
/**
 * Write a description of class Loops here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Loops
{
    // instance variables - replace the example below with your own
    private int x=0;
    private boolean Lower=true;
    private int y=0;

    /**
     * Constructor for objects of class Loops
     */
    public Loops()
    {
        // initialise instance variables
        x = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void Counting(int number)
    {
      Lower=true;
      if(number>-1){
         while(Lower){
             System.out.println(x);
             x++;
             if(x>number){Lower=false; x=0;}
         }
     }
    }
    public void CountingFives(int number)
    {
      Lower=true;
      if(number>-1){
         while(Lower){
             System.out.println(x);
             x+=5;
             if(x>number){Lower=false; x=0;}
         }
     }
    }
    public void CountingFivesAndTwentySeven(int number)
    {
      Lower=true; x=0; y=0;
      if(number>-1){
         while(Lower){
             if(x%27==0){y++;}
             System.out.println(x);
             x+=5;
             if(x>number || y==3){Lower=false; x=0; y=0;}
         }
     }
    }
    public void gnirtS(String string)
    {
        x=string.length()-1;
        while(x>=0){
            System.out.print(string.charAt(x));
            x--;
        }
        System.out.println("");
    }
    public void QueenofHearts(int NumberofGuests)
    {
        x=(NumberofGuests*(NumberofGuests+1))/2;
        System.out.println("Her cooks cooked " + 13*x + " tarts");
        System.out.println(13*x*7+"g of flour, "+13*x*3+"g of butter and "+11*x*13+"g of jam was required.");
    }
}
