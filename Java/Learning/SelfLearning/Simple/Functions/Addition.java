import java.util.Arrays;
public class Addition{
    byte a = 10;
    byte b = 20;
    byte add(){
        System.out.println(a+b); return (byte)(a+b);
    }

    static byte add(byte a, byte b){
        return (byte)(a+b);
    }
    public static void main(String[] args){
        System.out.println(Arrays.toString(args));
        Addition d = new Addition();
        System.out.println(d.add());
        System.out.println(add((byte)1, (byte)24));


    }
}