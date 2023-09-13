package class30;

public class Problem_0088_MergeSortedArray {

	public void merge(int[] arr1, int m, int[] arr2, int n) {
		int p1 = m - 1;
		int p2 = n - 1;
		int i = m + n - 1;
		while (p1 >= 0 && p2 >= 0) {// 两数组的数都未用尽
			if (arr1[p1] >= arr2[p2]) {
				arr1[i--] = arr1[p1--];
			} else {
				arr1[i--] = arr2[p2--];
			}
		}
		// 其中一个数组的数已用尽
		while (p1 >= 0) {
			arr1[i--] = arr1[p1--];
		}
		while (p2 >= 0) {
			arr1[i--] = arr2[p2--];
		}
	}

	public static void merge1(int[] nums1, int m, int[] nums2, int n) {
		int index = nums1.length;
		while (m > 0 && n > 0) {
			if (nums1[m - 1] >= nums2[n - 1]) {
				nums1[--index] = nums1[--m];
			} else {
				nums1[--index] = nums2[--n];
			}
		}
		while (m > 0) {
			nums1[--index] = nums1[--m];
		}
		while (n > 0) {
			nums1[--index] = nums2[--n];
		}
	}

}