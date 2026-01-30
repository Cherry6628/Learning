import java.util.Scanner;
import java.math.BigInteger;

public class Main{
    public static BigInteger factorial(int num){
        // System.out.println(num);
        if ((num%1>0) || (num<0)){
            return BigInteger.ZERO;
        }
        BigInteger num_ = BigInteger.ONE;
        for(int i=2; i<=num; i++){
            num_ = num_.multiply(BigInteger.valueOf(i));
        }

        return num_;
    }
    public static int result(BigInteger num){
        
        int res = 0;
        while (num.mod(BigInteger.valueOf(10))>BigInteger.ZERO){
            num = num.mod(BigInteger.valueOf(10));
            res+=1;
        }
        return res;

    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println(result(factorial(scanner.nextInt())));
    }
}