package class30;

public class Problem_0108_ConvertSortedArrayToBinarySearchTree {

	public TreeNode sortedArrayToBST(int[] arr) {
		return f(arr, 0, arr.length - 1);
	}

	public TreeNode f(int[] arr, int l, int r) {
		if (l > r) {
			return null;
		}
		if (l == r) {
			return new TreeNode(arr[l]);
		}
		int m = (l + r) / 2;
		TreeNode head = new TreeNode(arr[m]);
		head.left = f(arr, l, m - 1);
		head.right = f(arr, m + 1, r);
		return head;
	}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	public TreeNode sortedArrayToBST1(int[] nums) {
		return process(nums, 0, nums.length - 1);
	}

	public static TreeNode process(int[] nums, int L, int R) {
		if (L > R) {
			return null;
		}
		if (L == R) {
			return new TreeNode(nums[L]);
		}
		int M = (L + R) / 2;
		TreeNode head = new TreeNode(nums[M]);
		head.left = process(nums, L, M - 1);
		head.right = process(nums, M + 1, R);
		return head;
	}

}