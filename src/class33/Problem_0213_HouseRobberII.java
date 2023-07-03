package class33;

public class Problem_0213_HouseRobberII {
//	public int rob(int[] arr) {
//	int n = arr.length;
//	if (n == 1) {
//		return arr[0];
//	}
//	if (n == 2) {
//		return Math.max(arr[0], arr[1]);
//	}
//	int[] dp1 = new int[n];
//	dp1[0] = arr[0];
//	dp1[1] = Math.max(arr[0], arr[1]);
//	for (int i = 2; i <= n - 2; i++) {
//		dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + arr[i]);
//	}
//	int[] dp2 = new int[n];
//	dp2[1] = arr[1];
//	dp2[2] = Math.max(arr[1], arr[2]);
//	for (int i = 3; i <= n - 1; i++) {
//		dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + arr[i]);
//	}
//	return Math.max(dp1[n - 2], dp2[n - 1]);
//}
	// arr 长度大于等于1
	public static int pickMaxSum(int[] arr) {
		int n = arr.length;
		// dp[i] : arr[0..i]范围上，随意选择，但是，任何两数不能相邻。得到的最大累加和是多少？
		int[] dp = new int[n];
		dp[0] = arr[0];
		dp[1] = Math.max(arr[0], arr[1]);
		for (int i = 2; i < n; i++) {
			int p1 = arr[i];
			int p2 = dp[i - 1];
			int p3 = arr[i] + dp[i - 2];
			dp[i] = Math.max(p1, Math.max(p2, p3));
		}
		return dp[n - 1];
	}

	public static int rob(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}
		if (nums.length == 2) {
			return Math.max(nums[0], nums[1]);
		}
		int pre2 = nums[0];
		int pre1 = Math.max(nums[0], nums[1]);
		for (int i = 2; i < nums.length - 1; i++) {
			int tmp = Math.max(pre1, nums[i] + pre2);
			pre2 = pre1;
			pre1 = tmp;
		}
		int ans1 = pre1;
		pre2 = nums[1];
		pre1 = Math.max(nums[1], nums[2]);
		for (int i = 3; i < nums.length; i++) {
			int tmp = Math.max(pre1, nums[i] + pre2);
			pre2 = pre1;
			pre1 = tmp;
		}
		int ans2 = pre1;
		return Math.max(ans1, ans2);
	}

}