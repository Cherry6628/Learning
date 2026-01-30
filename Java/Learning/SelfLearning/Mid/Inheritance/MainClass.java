class Dad{
    byte money = 100;
}
class Son extends Dad{

}

public class MainClass{
    public static void main(String[] args){
        Son s1 = new Son();
        System.out.println(s1.money);
    }
}