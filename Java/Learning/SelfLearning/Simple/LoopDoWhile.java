import java.util.Scanner;
class LoopDoWhile{
    public static void main(String[] args){
        byte c = 0;
        do{
            System.out.println("Yohu "+(c));
            c++;
        } while (c <= 2);
        Scanner scanner = new Scanner(System.in); String pwd;
        do{
            pwd = scanner.nextLine();
            

        } while (pwd.length() < 8);
    }
}