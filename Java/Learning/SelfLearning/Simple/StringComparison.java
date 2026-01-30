class StringComparison{
    public static void hashCode(Object buffer){System.out.println(System.identityHashCode(buffer));}
    public static void println(Object buffer){System.out.println(buffer);}
    public static void print(Object buffer){System.out.print(buffer);}
    public static void main(String args[]){
        // System.out.println("Hello World");
        String name = "Hello";
        String name2 = "Hello";
        println(name==name2);

        String name3 = new String("Hello");
        String name4 = new String("Hello");
        println((name3==name4) + " " + (name3==name2));
        println(name.equals(name3)+" "+name3.equals(name4));


        hashCode(name);
        hashCode(name2);
        hashCode(name3);
        hashCode(name4);
        

        String temp = name3;
        println(name3==temp);


        // String[] a = {""};
        // println(a);
        // hashCode(a);
    }
}