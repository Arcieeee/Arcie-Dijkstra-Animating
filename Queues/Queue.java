import java.lang.Math;
/**
 *
 * Archer Page
 * 12/03/2024
 */
public class Queue
{
    public Element Head; //Creates Head and Tail Elements, for keeping track of the queue
    public Element Tail;

    /**
     * Constructor for objects of class Queue
     */
    public Queue() //Constructor
    {
     Head = null;
     Tail = null;
    }

    public boolean queueEmpty(){
        if(Head == null){return true;}else{return false;}//If the Head is null, then the queue is empty
    }

    public Element enqueue(Element Element){
        if(queueEmpty()){Head = Element; Tail = Element; return Element;} //If the queue is empty, set the head and tail to be the new element
        else{Tail.addFollower(Element); Tail = Element; return Element;} // Otherwise, set the new element as the follower to the current tail, then change the tail to the new element
    }

    public Element Dequeue(){
        if(queueEmpty()){return null;} //If the queue is empty, there is noone to dequeue
        else{Element x = Head; //'x' keeps track of who is currently the head
            Head=Head.Follower; //Sets the Head to the next person in the queue
            if(queueEmpty()){Tail = null;}; //If the queue is now empty, set tail to null aswell
            return x;} //Returns the dequeued element
    }
    
    public double randonum(){
        double num=Math.random(); //num = random number
        if(num==0.5477962330249694){System.out.println("OMG I GUESSED IT");} // Fun flavour text that can occur
        if(num==0){System.out.println("Impossible... IT CAN'T BE");}
        return num; //return num
    }
    
    public int Length(){
        if(queueEmpty()){return 0;} //if the queue is empty, return 0
        Element current = Head.Follower; //Current functions as a counter, stating at the 2nd in line
        int i=1; //i = 1 since we are certain there is atleast 1 person in the queue
        while(current != null){ //if there is no follower, we have reached the end and the loop stops
            i++; //if there is a follower, increase i by 1
            current=current.Follower; //and move down the queue by 1
        }
        return i; //returns the length
    }
    
    public void TowersOfHanoi(int NumOfDisks){
        int i=1;
        int x=1;
        int j=1;
        
        for (i=1; i<=NumOfDisks; i++){
            
        }
        }
    }

