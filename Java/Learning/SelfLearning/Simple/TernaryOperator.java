import java.util.Scanner; import java.lang.System;
class Ternary{
    public static void print(Object buffer){System.out.print(buffer);}
    public static void println(Object buffer){System.out.println(buffer);}
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        print("Are you my friend (Y/n) ? "); String inp = scan.nextLine();
        String variable = (inp.equals("y") || inp.equals("Y")) ? "Hello Buddy": "Who are you? Man !";
        println(variable);
        scan.close();
    }
}