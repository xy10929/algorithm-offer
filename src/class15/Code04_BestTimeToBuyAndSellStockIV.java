package class15;
//lc188
public class Code04_BestTimeToBuyAndSellStockIV {
//	public int maxProfit(int k, int[] prices) {
//		int n = prices.length;
//		if (k >= n / 2) {
//			return all(prices);
//		}
//		int[][] dp = new int[n][k + 1];
//		for (int end = 1; end < n; end++) {
//			int min = prices[0];
//			for (int i = 1; i <= end; i++) {
//				dp[end][1] = Math.max(dp[end][1], prices[i] - min);
//				min = Math.min(min, prices[i]);
//			}
//		}
//		for (int j = 2; j <= k; j++) {
//			dp[1][j] = Math.max(prices[1] - prices[0], 0);
//			int best = dp[1][j - 1] - prices[1];
//			for (int i = 2; i < n; i++) {
//				int p1 = dp[i - 1][j];
//				int newi = dp[i][j - 1] - prices[i];
//				best = Math.max(newi, best);
//				dp[i][j] = Math.max(p1, best + prices[i]);
//			}
//		}
//		return dp[n - 1][k];
//	}
//
//	public int all(int[] prices) {
//		int ans = 0;
//		for (int i = 1; i < prices.length; i++) {
//			ans += Math.max(prices[i] - prices[i - 1], 0);
//		}
//		return ans;
//	}	public int maxProfit(int k, int[] prices) {
//		int n = prices.length;
//		if (k >= n / 2) {
//			return all(prices);
//		}
//		int[][] dp = new int[n][k + 1];
//		for (int end = 1; end < n; end++) {
//			int min = prices[0];
//			for (int i = 1; i <= end; i++) {
//				dp[end][1] = Math.max(dp[end][1], prices[i] - min);
//				min = Math.min(min, prices[i]);
//			}
//		}
//		for (int j = 2; j <= k; j++) {
//			dp[1][j] = Math.max(prices[1] - prices[0], 0);
//			int best = dp[1][j - 1] - prices[1];
//			for (int i = 2; i < n; i++) {
//				int p1 = dp[i - 1][j];
//				int newi = dp[i][j - 1] - prices[i];
//				best = Math.max(newi, best);
//				dp[i][j] = Math.max(p1, best + prices[i]);
//			}
//		}
//		return dp[n - 1][k];
//	}
//
//	public int all(int[] prices) {
//		int ans = 0;
//		for (int i = 1; i < prices.length; i++) {
//			ans += Math.max(prices[i] - prices[i - 1], 0);
//		}
//		return ans;
//	}
	public static int maxProfit(int K, int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int N = prices.length;
		if (K >= N / 2) {
			return allTrans(prices);
		}
		int[][] dp = new int[K + 1][N];
		int ans = 0;
		for (int tran = 1; tran <= K; tran++) {
			int pre = dp[tran][0];
			int best = pre - prices[0];
			for (int index = 1; index < N; index++) {
				pre = dp[tran - 1][index];
				dp[tran][index] = Math.max(dp[tran][index - 1], prices[index] + best);
				best = Math.max(best, pre - prices[index]);
				ans = Math.max(dp[tran][index], ans);
			}
		}
		return ans;
	}

	public static int allTrans(int[] prices) {
		int ans = 0;
		for (int i = 1; i < prices.length; i++) {
			ans += Math.max(prices[i] - prices[i - 1], 0);
		}
		return ans;
	}

	// 课上写的版本，对了
	public static int maxProfit2(int K, int[] arr) {
		if (arr == null || arr.length == 0 || K < 1) {
			return 0;
		}
		int N = arr.length;
		if (K >= N / 2) {
			return allTrans(arr);
		}
		int[][] dp = new int[N][K + 1];
		// dp[...][0] = 0
		// dp[0][...] = arr[0.0] 0
		for (int j = 1; j <= K; j++) {
			// dp[1][j]
			int p1 = dp[0][j];
			int best = Math.max(dp[1][j - 1] - arr[1], dp[0][j - 1] - arr[0]);
			dp[1][j] = Math.max(p1, best + arr[1]);
			// dp[1][j] 准备好一些枚举，接下来准备好的枚举
			for (int i = 2; i < N; i++) {
				p1 = dp[i - 1][j];
				int newP = dp[i][j - 1] - arr[i];
				best = Math.max(newP, best);
				dp[i][j] = Math.max(p1, best + arr[i]);
			}
		}
		return dp[N - 1][K];
	}

}