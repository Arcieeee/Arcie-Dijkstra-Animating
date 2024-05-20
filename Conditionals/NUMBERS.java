
/**
 * Write a description of class NUMBERS here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NUMBERS
{
    // instance variables - replace the example below with your own
    private int x;
    private boolean even;

    /**
     * Constructor for objects of class NUMBERS
     */
    public NUMBERS()
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
    public void NumberReader(int number)
    {
        // put your code here
        even = number%2 == 0;
        if(number%5==0){
        System.out.println(number + "* odd");
        } else {
        System.out.print(number);    
        if(even){System.out.println(" even");}
        if(!even){System.out.println(" odd");}
        }
    }
    
    public void Hello(int NumofPeople){
      switch (NumofPeople){
          case 1: System.out.println("Tena koe");
          break;
          case 2: System.out.println("Tena korua");
          break;
          default:  if (NumofPeople>2){System.out.println("Tena kotou"); }
                  else{System.out.println("Pick a positive number");}
          break;
    }
    }
}
