import java.util.Arrays;
class Solution {
    public static boolean isRevSorted(int[] arr, int s){
        for (int i=s; s<arr.length-1; s++){
            if (arr[s]<arr[s+1]){
                return false;
            }
        }
        return true;
    }
    public static int[] sortPart(int[] arr, int s){
        for (int i=s;i<arr.length;i++){
            for (int j=i+1;j<arr.length;j++){
                if ((i!=j) && (arr[i]>arr[j])){
                    arr[i]+=arr[j];
                    arr[j]=arr[i]-arr[j];
                    arr[i] -= arr[j];
                }
            }
        }
        return arr;
    }
    public void nextPermutation(int[] nums) {
        for (int i=0; i<nums.length; i++){
            if (isRevSorted(nums, i)){

                if (i==0){
                    Arrays.sort(nums);
                } else {
                    int s= nums[i-1];
                    int m = i;
                    System.out.println(s+" "+m);
                    for (int k=i; k<nums.length; k++){
                        System.out.println(k+" "+Arrays.toString(nums));
                        
                        if (s<nums[k] && nums[k]<nums[m]){
                            m = k;
                        }
                    }
                    
                    nums[i-1]=nums[m];
                    nums[m]=s;
                    nums=sortPart(nums, i);
                }
                System.out.println(Arrays.toString(nums));
                break;
            }
        }
    }
}

class Solution2{
    public static void main(String[] args){
        Solution s = new Solution();
        int[] x = {7,8, 7};
        s.nextPermutation(x);
    }
}