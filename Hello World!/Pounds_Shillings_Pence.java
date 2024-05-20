import java.util.Scanner;
public class Pounds_Shillings_Pence {
    
    public static void main(String[] args){
      Scanner input = new Scanner(System.in);
      int N = input.nextInt();
      for(int i=0; i<N; i++){
          int S = input.nextInt();
          int x=0;
          for (int j=0; j<S; j++){
              String str = input.nextLine();
              if(j==0){str = input.nextLine();}
              str=str.substring(1, str.length());
              String[] arrOfStr = str.split("-", 7);
              int Weight=240;
              for (String a : arrOfStr) {
               x=x+(Weight*Integer.parseInt(a)); if (Weight == 240){Weight = 12;} else if (Weight == 12){Weight = 1;}}
          }
          int Pence=x/240; x=x-(Pence*240);
          int Shillings=x/12; x=x-(Shillings*12);
          System.out.println("#"+Pence+"-"+Shillings+"-"+x);
      }
    }
}
