package class02;
//lc581
// 本题测试链接 : https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
public class Code06_MinLengthForSort {
//	public int findUnsortedSubarray(int[] arr) {
//	int n = arr.length;
//	if (n == 1) {
//		return 0;
//	}
//	int leftmax = Integer.MIN_VALUE;
//	int leftend = -1;
//	for (int i = 0; i < n; i++) {
//		if (arr[i] < leftmax) {
//			leftend = Math.max(leftend, i);
//		}
//		leftmax = Math.max(leftmax, arr[i]);
//	}
//	int rightmin = Integer.MAX_VALUE;
//	int rightend = n;
//	for (int i = n - 1; i >= 0; i--) {
//		if (arr[i] > rightmin) {
//			rightend = Math.min(rightend, i);
//		}
//		rightmin = Math.min(rightmin, arr[i]);
//	}
//	return Math.max(leftend - rightend + 1, 0);
//}
	public static int findUnsortedSubarray(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		int N = nums.length;
		int right = -1;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			if (max > nums[i]) {
				right = i;
			}
			max = Math.max(max, nums[i]);
		}
		int min = Integer.MAX_VALUE;
		int left = N;
		for (int i = N - 1; i >= 0; i--) {
			if (min < nums[i]) {
				left = i;
			}
			min = Math.min(min, nums[i]);
		}
		return Math.max(0, right - left + 1);
	}

}