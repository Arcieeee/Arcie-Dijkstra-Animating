import java.util.Scanner;
public class FizzBuzz { 
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int Num=input.nextInt();
        for (int i=1; i<Num+1; i++){
            String j = "";
            if (i%3==0){j+="Fizz";}
            if (i%5==0){j+="Buzz";}
            if (j==""){j=i+"";}
            System.out.println(j);
        }
    }
}
