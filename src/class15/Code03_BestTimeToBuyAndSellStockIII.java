package class15;
//lc123
public class Code03_BestTimeToBuyAndSellStockIII {
//	public int maxProfit(int[] prices) {
//		if (prices.length == 1) {
//			return 0;
//		}
//		int ans = 0;//总利润
//		int afterfirstsell = 0;//第一次卖出后的利润
//		int aftersecondbuy = -prices[0];//第二次买入后的相对钱数
//		int min = prices[0];//第一次买入时之前时刻的的最小值
//		for (int i = 1; i < prices.length; i++) {//i既是第一次卖出的时机 又是第二次买入的时机 还是第二次卖出的时机
//			afterfirstsell = Math.max(afterfirstsell, prices[i] - min);//第一次卖出后的利润
//			min = Math.min(min, prices[i]);//第一次买入时之前时刻的新的最小值
//			aftersecondbuy = Math.max(aftersecondbuy, afterfirstsell - prices[i]);//第二次买入后相对钱数
//			ans = Math.max(ans, aftersecondbuy + prices[i]);//第二次卖出后的利润
//		}
//		return ans;
//	}
	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length < 2) {
			return 0;
		}
		int ans = 0;
		int doneOnceMinusBuyMax = -prices[0];
		int doneOnceMax = 0;
		int min = prices[0];
		for (int i = 1; i < prices.length; i++) {
			min = Math.min(min, prices[i]);
			ans = Math.max(ans, doneOnceMinusBuyMax + prices[i]);
			doneOnceMax = Math.max(doneOnceMax, prices[i] - min);
			doneOnceMinusBuyMax = Math.max(doneOnceMinusBuyMax, doneOnceMax - prices[i]);
		}
		return ans;
	}

}