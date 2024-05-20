import java.lang.Math;
/**
 * Write a description of class QueueManager here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class QueueManager
{
    // instance variables - replace the example below with your own
    private int x;
    private Queue HighPriority;
    private Queue LowPriority;

    /**
     * Constructor for objects of class QueueManager
     */
    public QueueManager()
    {
        // initialise instance variables
        HighPriority = new Queue();
        LowPriority = new Queue();
    }
    
    public boolean QueueEmpty(){
        return (HighPriority.queueEmpty() && LowPriority.queueEmpty());
    }
        
    public Element deQueue(){
        if (QueueEmpty()){return null;};
        if(!HighPriority.queueEmpty()){return HighPriority.Dequeue();}
        else{return LowPriority.Dequeue();}
    }
    
    public Element enqueue(Element Element, boolean HighPriority){
        if(HighPriority){return this.HighPriority.enqueue(Element);}
        return LowPriority.enqueue(Element);
    }
    
    public int length(){
        return (HighPriority.Length()+LowPriority.Length());
    }
    
    public void SimulateCars(){
        QueueManager arrivals = new QueueManager();
        System.out.println("Customers are arriving...");
        for(int i=1; i<26; i++)
           if (Math.random() < 0.101){
               System.out.println("Person "+i+" is important!");
               Element y = new Element(""+i);
               arrivals.enqueue(y, true);
           } else {
               Element y = new Element(""+i);
               arrivals.enqueue(y, false);
           }
        Element y;   
        System.out.println("Serving Customers...");
        while (!arrivals.QueueEmpty()){
            y = arrivals.deQueue(); System.out.println(y.Name);}
            
    }
}
