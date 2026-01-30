public class School{

    // void display(){
    //     System.out.println("Hello World");
    // }

    // void display(int a){
    //     System.out.println("Hello Earth"+a);
    // }

    
    // public static void main(String[] args){
    //     School schl = new School();
    //     schl.display(345);
    // }

    void sum(int a, int b){
        System.out.println("Two Parameters passed... "+(a+b));
    }
    void sum(int a, int b, int c){
        System.out.println("Three Parameters passed... "+(a+b+c));
    }
    public static void main(String[] args){
        School s = new School();
        s.sum(1, 2);
        s.sum(1, 2, 3);
    }
}