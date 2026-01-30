import java.util.Scanner;
import java.util.Random;

class BirthdayParadox{

    static Random random = new Random();

    public static float helper(int n, float i){
        if (i == 1){
            return 1;
        } else {
            return ((366-i)/365)*helper(n, i-1);
        }
    }

    public static float probability(int n){
        return 1-helper(n, n);
    }

    public static int[] generate_people(int n){
        int[] dates = new int[n];
        for (int i = 0; i<n; i++){
            dates[i] = random.nextInt(365)+1;
        }
        return dates;
    }

    public static boolean simulation(int people){
        int[] arr = generate_people(people);
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (arr[i] == arr[j]){
                    return true;}
            }
        }
        return false;
    }
    // public static 
    public static float runMultipleSimulations(int n, int times){
        float passed_cases = 0;
        float output;
        for (int i = 0; i<times; i++){if (simulation(n)){passed_cases++;}}
        return passed_cases/times;
    }





    public static void initiateAndCompare(int people_count, int iterations){
        float chance = probability(people_count)*100;
        float result = runMultipleSimulations(people_count, iterations)*100;
        System.out.println("For Infinite Iterations : "+chance+" %\nFor "+iterations+" Iterations : "+result+" %\nDifference :"+Math.abs(chance - result)+" %");
    }

    public static void main(String args[]){

        Scanner input = new Scanner(System.in);
        System.out.print("Total Number of People               : ");
        int people_count = input.nextInt();
        System.out.print("Total Number of Simulations to check : ");
        int iterations = input.nextInt();


        initiateAndCompare(people_count, iterations);
    }
}