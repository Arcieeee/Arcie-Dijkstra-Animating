
/**
 * Write a description of class Stack here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Stack
{
    // instance variables - replace the example below with your own
    
    private Element top;

    /**
     * Constructor for objects of class Stack
     */
    public Stack()
    {
        // initialise instance variables
        
    }

    public Element Pop(){
     Element x = top; top = top.Follower; return x; 
    }
    
    public Element Push(Element Element){
     if (stackEmpty()){top = Element; return Element;}
     else{Element.addFollower(top); top = Element; return Element;}
    }
    
    public boolean stackEmpty(){if(top == null) {return true;} else {return false;}}
}
