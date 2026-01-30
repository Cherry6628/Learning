import java.util.Scanner;
import java.util.Arrays;
class Solution {
    public boolean isValid(String s) {
        if (s.equals("")){return true;}
        int l = s.length();
        if (l%2==1){return false;}
        char[] p = new char[l/2];
        int loc = -1;
        char last;
        char cur;
        for (int i=0; i<l; i++){
            last = loc>-1?p[loc]:'\u0000';
            cur = s.charAt(i);

            if (last=='\u0000'){
                p[++loc] = cur;
            } else {
                if (((last=='(') && (cur==')')) || ((last=='{') && (cur=='}')) || ((last=='[') && (cur==']'))){
                    p[loc--]='\u0000';
                } else {
                    if (loc+1==(l/2)){return false;}
                    p[++loc]=cur;
                }
            }
        }
        return loc==-1;
    }
}
public class Solution1{
    public static void main(String[] args){
        Solution s = new Solution();
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println(s.isValid(sc.nextLine()));
        }
    }
}