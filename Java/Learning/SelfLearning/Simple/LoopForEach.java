public class LoopForEach{
    public static void main(String[] args){
        int num[] = {1,2,3,4,5,6,7,8,9,10};

        // For Loop
        for (int i=0; i<10; i++){
            System.out.println(num[i]);
        }


        // For Each Loop / Enhanced For Loop
        for (int i:num){
            System.out.println(i);
        }



    }
}