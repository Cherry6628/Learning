// import java.lang.reflect.Array;
// class SpecialArray{
//     int l;
//     Object arr;
//     SpecialArray(int length, Class<?> type){
//         // this.arr = new type[length];
//         this.arr = Array.newInstance(type, length);
//     }
//     void showarr(){
//         System.out.println(this.arr);
//     }
// }

// public class Main{
//     public static void main(String[] args){
//         SpecialArray newarr = new SpecialArray(10, Byte.class);
//     }
// }



// import java.lang.reflect.Array;
// class SpecialArray{
//     int l;
//     Object arr;
//     SpecialArray(int length, Object type){
//         this.arr = new type[length];
//     }
//     void showarr(){
//         System.out.println(this.arr);
//     }
// }

// public class Main{
//     public static void main(String[] args){
//         SpecialArray newarr = new SpecialArray(10, Byte);
//     }
// }


// import java.lang.reflect.Array;
// import java.util.Arrays;
// class SpecialArray{
//     int filled_length;
//     int current_length;
//     int initial_length;
//     Object arr;
//     Class<?> type;

//     SpecialArray(int length, Class<?> type){
//         this.current_length = length;
//         this.initial_length = length;
//         this.type = type;
//         this.arr = Array.newInstance(this.type, this.current_length);
//     }

//     void add(Object obj){
//         if (this.filled_length < this.current_length){
//             // this.arr[this.filled_length] = type.cast(obj);
//             Array.set(this.arr, this.filled_length, type.cast(obj));
//             this.filled_length++;
//         } else{
//             this.current_length += this.initial_length;
//             Array temp = Array.newInstance()
//         }
//     }
// }

import java.util.Arrays;
class IntegerArray{
    int length;
    int filled_length;
    int[] array;
    IntegerArray(int length){
        array = new int[length];
        this.length = length;
    }
    void add(int n){
        if (this.filled_length >= this.length){
            int temp_len = this.length;
            this.length += (this.length>>1);
            int[] temp = new int[this.length];
            for (int i = 0; i < temp_len; i++){
                temp[i] = this.array[i];
            }
            this.array = temp;
        }
        this.array[this.filled_length] = n;
        this.filled_length++;
    }
    void show(){
        System.out.println(Arrays.toString(this.array));
    }
    int[] real_array(){
        int[] temp = new int[this.filled_length];
        for (int i = 0; i<this.filled_length; i++){
            temp[i] = this.array[i];
        }
        return temp;
    }
}


public class Main{
    public static void main(String[] args){
        IntegerArray arr = new IntegerArray(10);

        // TEsting...
        arr.show();
        for (int i = 0; i<20; i++){
            arr.add(i); arr.show();
        }
        System.out.println(Arrays.toString(arr.real_array()));


    }
}
