package assignments.assignment002;

import java.util.Arrays;

class Student implements Cloneable{
	String name;
	int id;
	Student(String name, int id){
		this.name=name;
		this.id=id;
	}
	public Student clone() throws CloneNotSupportedException {
        return (Student)super.clone();
    }
	public String toString() {
		return this.name+"@"+this.id;
	}
}

public class ArraysMethod{
    public static void main(String[] args) {

        int[] a = {5, 2, 9, 1, 7};
        Arrays.sort(a);
        System.out.println(a[0] + " " + a[a.length-1]);

        int[] b = {1, 2, 3};
        int[] c = {1, 2, 3};
        System.out.println(Arrays.equals(b, c));

        int[] bigger = Arrays.copyOf(a, 10);
        System.out.println(Arrays.toString(bigger));

        int[] d = new int[5];
        Arrays.fill(d, 7);
        System.out.println(Arrays.toString(d));

        int[] e = new int[6];
        Arrays.fill(e, 0, e.length/2, 1);
        Arrays.fill(e, e.length/2, e.length, 2);
        System.out.println(Arrays.toString(e));

        int i = Arrays.binarySearch(a, 7);
        System.out.println(i);

        System.out.println(Arrays.toString(a));

        int[] part = Arrays.copyOfRange(a, 1, 4);
        System.out.println(Arrays.toString(part));

        System.out.println(isSorted(a));

        int[][] twoD = {{1,2},{3,4}};
        System.out.println(Arrays.deepToString(twoD));

        System.out.println(Arrays.hashCode(b) == Arrays.hashCode(c));

        int[] f = {3,5,3,5};
        int mid = f.length/2;
        System.out.println(
            Arrays.hashCode(Arrays.copyOfRange(f,0,mid)) ==
            Arrays.hashCode(Arrays.copyOfRange(f,mid,f.length))
        );
        try {
	        Student s1 = new Student("Ragul", 1234);
	        Student s2 = s1.clone();
	        System.out.println(s1+" "+s2);
        } catch(Exception err) {
        	System.out.println("Error Occurred: "+err);
        }
    }

    static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++)
            if (arr[i] < arr[i-1]) return false;
        return true;
    }
}