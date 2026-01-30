class forLoop{
    
    public static void main(String args[]){
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