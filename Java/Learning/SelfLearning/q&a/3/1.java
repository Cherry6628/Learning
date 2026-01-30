import java.util.Scanner;
import java.util.Arrays;
class one{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.print("Total Number of Subjects ? ");
        byte n = (byte) scan.nextInt();
        byte[] marks = new byte[n];
        for (byte i = 0; i < n; i++){
            marks[i] = (byte) scan.nextInt();
        }
        System.out.println(Arrays.toString(marks));
        int t = 2;
        for (int i = 1; i < 11; i++){
            System.out.println(t+"*"+i+"="+i*t);
        }
    }
}