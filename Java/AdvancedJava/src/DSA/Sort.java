package DSA;

import java.util.Arrays;

public class Sort {
	public static void main(String[]args) {
		int[]a= {1, 3, 6, 4, 3, 0, 20, -3, 5};
//		insertionSort(a);
//		System.out.println(Arrays.toString(a));
		
		
		System.out.println(Arrays.toString(merge(new int[] {1,4,6,8,9}, new int[] {0,2,3,5,7})));
	}
	
	public static void insertionSort(int[] arr) {
		for (int i=1;i<arr.length;i++) {
			for (int j=i;j>0;j--) {
				if(arr[j]<arr[j-1]) {
					int t=arr[j-1];
					arr[j-1]=arr[j];
					arr[j]=t;
				}
				else break;
			}
		}
		
//		for (int i=0;i<arr.length-1;i++) {
//			for (int j=arr.length-1;j>0;j--) {
//				if (arr[j]<arr[j-1]) {
//					int t=arr[j-1];
//					arr[j-1]=arr[j];
//					arr[j]=t;
//				}
//			}
//		}
	}
	
	public static int[] merge(int[]a1, int[]a2) {
		int[]a=new int[a1.length+a2.length];
		int p0=0;
		int p1=0;
		int p2=0;
		while(p0<a.length) {
			if(p1<a1.length&&p2<a2.length&&a1[p1]<a2[p2]) {
				a[p0]=a1[p1];
				p1++;p0++;
			}
			else if (p2<a2.length){
				a[p0]=a2[p2];
				p2++;p0++;
			}
			System.out.println(Arrays.toString(a));
		}
		System.out.println("1");
		System.out.println("\u00A0");
		System.out.println("1");
		return a;
	}
	
	
	
}
