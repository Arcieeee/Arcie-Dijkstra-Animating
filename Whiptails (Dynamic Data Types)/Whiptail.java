
/**
 * Write a description of class Whiptail here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Whiptail
{
    // instance variables - replace the example below with your own
    String name;
    int length;
    private Whiptail mother;

    /**
     * Constructor for objects of class Whiptail
     */
    public Whiptail()
    {
        this.name="anonymous";    
    }

    public Whiptail(String name){
        this.name=name;
    }
    
    public void setName(String name){
        this.name=name;
    }
    
    public void setLength(int len){
        this.length=len;
    }
    
    public void createMother(){
        this.mother=new Whiptail();
    }
    
    public void setMother(Whiptail mother){
        this.mother=mother;
    }
    
    public String getName(){
        return this.name;
    }
    
    public Whiptail getMother(){
        return this.mother;
    }
    
    public String getMotherName(){
        return this.mother.getName();
    }
}
