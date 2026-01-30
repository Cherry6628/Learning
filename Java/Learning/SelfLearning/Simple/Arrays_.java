import java.util.Arrays;
class Arrays_{

    public static void main(String[] args){
        int[] array = new int[3];
        System.out.println(array);
        System.out.println(Arrays.toString(array));
        for (int i = 0; i < 3; i++){
            array[i] = i;
        }
        // System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(array));

        String[] array2 = {"a", "sdfa", "Sdfsdfsdfsdfs"};
        System.out.println(Arrays.toString(array2));
    }
}