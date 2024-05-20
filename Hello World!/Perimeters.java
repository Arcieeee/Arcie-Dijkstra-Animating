import java.util.Scanner;
public class Perimeters {
    
    public static void main(String[] args){
      Scanner input = new Scanner(System.in);
      int ax=input.nextInt();
      int ay=input.nextInt();
      int bx=input.nextInt();
      int by=input.nextInt();
      System.out.println((by-ay)*2+(bx-ax)*2);
    }
}