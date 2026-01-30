class NonPrimitiveDataTypes{
    public static void printType(char ch){
        System.out.println("Is a Character.");
    }
    public static void printType(String str){
        System.out.println("Is a String.");
    }

    public static void main(String args[]){
        String name = "ZerilPHA";
        printType(name.charAt(0));
        System.out.println(name.charAt(0));
    }
}