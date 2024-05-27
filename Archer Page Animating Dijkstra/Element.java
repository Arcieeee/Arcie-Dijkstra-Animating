

    /**
 * 
 * Archer Page
 * 12/03/2024
 */
public class Element<E>
{
    // instance variables - replace the example below with your own
    public Element Follower; //Creates a Follower Element
    public E Name; //Identity of element

    /**
     * Constructor for objects of class Element
     */
    public Element() //constructor
    {
        
        
    }
    
    public Element(E Name){ //other constructor
        this.Name=Name; //Identity of Element = Input
    }
    
    public Element addFollower(Element toFollower){
        if (toFollower.Follower!=null){return this;} //if the follower has a follower, then it is in the queue, hence it can't be added
        if (toFollower == this){return this;} //the follower can't be the same as the element it is following
        this.Follower=toFollower; //Element Follower = Input Follower
        return toFollower; //returns the follower
    }
    
    public Element getFollower(){
        return Follower;//Returns the Follower Element
    }
    
    
    
}

