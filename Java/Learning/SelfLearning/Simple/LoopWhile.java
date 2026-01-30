import java.util.Random;

class LoopWhile{
    public static void main(String[] args){
        Random random = new Random();
        // random.setSeed(234);
        float c  = random.nextFloat();
        while (c < 0.9){
            System.out.println(c);
            c = random.nextFloat();
        }

        System.out.println(c);
        byte v = (byte) random.nextInt(11);
        while (v != 5){
            System.out.println(v);
            v = (byte) random.nextInt(11);
        }
        System.out.println(v);

    }
}