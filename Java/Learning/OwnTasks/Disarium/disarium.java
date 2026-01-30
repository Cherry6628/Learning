import java.util.ArrayList;
import java.util.Scanner;


public class disarium{
    static boolean isDisarium(int num, boolean debug){
        ArrayList<Byte> digits = new ArrayList<>();
        int new_num = 0;
        int copy = num;
        int l = 0;
        while (copy>0){
            digits.add((byte)(copy%10));
            copy = Math.floorDiv(copy, 10); l++;
        }
        for (byte digit:digits){
            new_num = new_num+((int) Math.pow(digit, l)); l--;
        }
        if((new_num == num) && debug){System.out.println(num+" is a Disarium Number");} else if(debug){System.out.println("Expected: "+num+"\nGot     : "+new_num);}
        return (new_num == num);

    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Number: ");
        System.out.println(isDisarium(scanner.nextInt(), true));
    }
}
