
/**
 *
 * @author (Archer Page)
 * @version (12/02/2024)
 */
public class Strings
{
    private int x;
    private char first;
    private char last;
    private int n1;
    private int n2;

    public void FirstAndLast(String Message)
    {
        first = Message.charAt(0); //Variable "first" = first character of string
        last = Message.charAt(Message.length() - 1); //Variable "last" = last character of string
        System.out.println(first); 
        System.out.println(last); //First and last charcters printed
        System.out.println("Done!");
    }
    
    public int StringAddition(String First, String Second)
    {
        n1=Integer.parseInt(First); 
        n2=Integer.parseInt(Second); //Converts Strings to inegers and sets n1 and n2 equal to the respective values
        return n1+n2; //Returns Sum
    }
    
    public void Equivelence(String s1, String s2)
    {
        if(s1.equals(s2)){System.out.println("Equals! (case sensitive)");} //If Strings exactly match, Prints
        else {s1=s1.toLowerCase(); s2=s2.toLowerCase(); //Sets each string to Full Lowercase
        if(s1.equals(s2)){System.out.println("Equals (case insensitive)");} //if new strings match, Prints
        else{System.out.println("Not equals...");}} //else strings do not match regardless of case
    }
    
    public void StringSwitcharoo(String string){
        x=string.length()/2; //sets x to be the number of the placement of the middle character of the string (rounded down)
        n1=0; //resets n1 value, n1 functions as an offset
        while(n1<x){ //ensures printing ends once offset from middle equates to middle
            System.out.print(string.charAt(x+n1)); //Prints Middle and Positive values from middle
            n1++; //increments offset
            System.out.print(string.charAt(x-n1)); //Prints negative values from middle, repeats until charAt(0) is printed
        }
        if(string.length()%2==1){System.out.print(string.charAt(string.length()-1));} //if String contains an odd number of characters, Last character is not printed due to being futher away from the middle than the first character is thus it gets printed here
        System.out.println(""); //empty space to ensure Done message appears on new line
        System.out.println("Done!");
    }
}
