// import java.util.Scanner;
// // import java.io.Thread;
// class Solution{
//     static long factorial(long n){
//         if (n<1 || n==0){return 1;}
//         for (int i=(int)n-1; i>0; i--){
//             n*=i;
//             try{Thread.sleep(500); System.out.println(i);} catch (Exception e){}
//             System.out.println(n);
//         }
//         return n;
//     }
//     public static void main(String[] args){
//         int r = 0;
//         byte n = (byte) new Scanner(System.in).nextInt();
//         if (n<(byte)0){System.out.println("Unable to find Factorial for Negative Number.\nResult: "+0);}
//         long f = factorial((long)n);
//         if (f!=0L){
//             while (f%10==0){
//                 r++;
//                 f/=10;
//             }
//             System.out.println("Result: "+r);
//         }
//     }
// }
import java.util.Scanner;
class Elimination{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = s.nextInt();
        s.close();
        int[] arr = new int[n];
        for (int i=1; i<=n; i++){
            arr[i-1] = i;
        }
    }

}

