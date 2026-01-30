import java.util.Scanner;
import java.util.ArrayList;
public class HappyNumber{
    static int sumOfSquares(int num){
        int result = 0;
        while (num>0){
            result+= (int) Math.pow(num%10, 2);
            num = Math.floorDiv(num, 10);
        }
        return result;
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        ArrayList<Integer> array = new ArrayList<>();
        while ((! array.contains(input)) && (input != 1)){
            array.add(input);
            input = sumOfSquares(input);
            if (input == 1){break;}
        }
        array.add(input);
        if (input==1){System.out.println("Is a Happy Number.");}
        else {System.out.println("Not a Happy Number");}
        System.out.println(array.toString());
    }
}