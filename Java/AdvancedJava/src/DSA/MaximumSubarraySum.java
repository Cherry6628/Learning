package DSA;
class MaximumSubarraySum {
	public static void main(String[] args) {
		int[] arr = { 1, 1, 3, 5, 6, 3, 4, -234, 234, 453, -2342, 34, 1 };
		{
			int max = arr[0];
			for (int i = 0; i < arr.length; i++) {
				int cur = 0;
				for (int j = i + 1; j < arr.length; j++) {
					cur += arr[j];
					max = Math.max(max, cur);
				}
			}
		}

		{
			int max = arr[0], cur = arr[0];
			for (int i = 1; i < arr.length; i++) {
				cur = Math.max(arr[i], cur + arr[i]);
				max = Math.max(max, cur);
			}
			System.out.println(max);
		}

	}
}
