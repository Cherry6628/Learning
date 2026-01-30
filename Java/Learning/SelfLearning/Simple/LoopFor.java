class LoopFor{
    public static void println(Object buffer){System.out.println(buffer);}
    public static void main(String args[]){
        for (int i = 1; i <= 5; ++i){
            println("Hello " + i);
        }

        for (int i = 0; i <= 20; i++){
            if (i%5 == 0 && i%3 == 0) {
                println("FizzBuzz");
            } else if (i%3==0) {
                println("Fizz");
            } else if (i%5==0) {
                println("Buzz");
            } else {
                println(i);
            }
        }
        int l = 10;
        for (int i = 1; i <= l; i++){
            for (int j = 1; j <= i; j++){
                System.out.print("*");
            }System.out.println();
        }
        for (int i = l - 1; i > 0; i--){
            for (int j = 1; j <= i; j++){
                System.out.print("*");
            }System.out.println();
        }
    }

    
}