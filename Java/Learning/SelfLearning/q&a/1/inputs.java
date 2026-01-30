import java.util.Scanner;
class inputs{
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        String age = input.nextInt();
        System.out.println(name+" is "+age+" years old.")
    }
}