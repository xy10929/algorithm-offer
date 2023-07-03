package class42;

public class Problem_0265_PaintHouseII {
//	public int minCostII(int[][] costs) {
//	int n = costs.length;//房子数
//	int k = costs[0].length;//颜色数
//	int prefirstv = 0;//上一个位置的最优代价
//	int prefirstc = -1;//上一个位置是最优代价时的颜色
//	int presecondv = 0;//上一个位置的次优代价
//	int presecondc = -1;//上一个位置是次优代价时的颜色
//	for (int i = 0; i < n; i++) {//从0到n-1位置
//		int curfirstv = Integer.MAX_VALUE;//更新当前位置的最优代价
//		int curfirstc = -1;//更新当前位置是最优代价时的颜色
//		int cursecondv = Integer.MAX_VALUE;//更新当前位置的最次代价
//		int cursecondc = -1;//更新当前位置是次优代价时的颜色
//		for (int j = 0; j < k; j++) {//尝试所有颜色 但不能和上一个位置(最优&次优的情况都检查并更新)的颜色相同
//			if (j != prefirstc) {//上一个位置假如涂了最优代价对应的颜色
//				if (prefirstv + costs[i][j] < curfirstv) {//涂当前颜色后 可以更新当前的最优代价
//					cursecondv = curfirstv;
//					cursecondc = curfirstc;//原最优变次优
//					curfirstv = prefirstv + costs[i][j];
//					curfirstc = j;//当前颜色为最优
//				} else if (prefirstv + costs[i][j] < cursecondv) {//涂当前颜色后 可以更新当前的最次代价
//					cursecondv = prefirstv + costs[i][j];
//					cursecondc = j;//当前颜色为次优
//				}
//			} else if (j != presecondc) {//上一个位置假如涂了最次代价对应的颜色
//				if (presecondv + costs[i][j] < curfirstv) {//涂当前颜色后 可以更新当前的最优代价
//					cursecondv = curfirstv;
//					cursecondc = curfirstc;//原最优变次优
//					curfirstv = presecondv + costs[i][j];
//					curfirstc = j;//当前颜色为最优
//				} else if (presecondv + costs[i][j] < cursecondv) {//涂当前颜色后 可以更新当前的最次代价
//					cursecondv = presecondv + costs[i][j];
//					cursecondc = j;//当前颜色为次优
//				}
//			}
//		}
//		prefirstv = curfirstv;
//		prefirstc = curfirstc;
//		presecondv = cursecondv;
//		presecondc = cursecondc;//即将到下一个位置  cur作为新的pre
//	}
//	return prefirstv;
//}
	// costs[i][k] i号房子用k颜色刷的花费
	// 要让0...N-1的房子相邻不同色
	// 返回最小花费
	public static int minCostII(int[][] costs) {
		int N = costs.length;
		if (N == 0) {
			return 0;
		}
		int K = costs[0].length;
		// 之前取得的最小代价、取得最小代价时的颜色
		int preMin1 = 0;
		int preEnd1 = -1;
		// 之前取得的次小代价、取得次小代价时的颜色
		int preMin2 = 0;
		int preEnd2 = -1;
		for (int i = 0; i < N; i++) { // i房子
			int curMin1 = Integer.MAX_VALUE;
			int curEnd1 = -1;
			int curMin2 = Integer.MAX_VALUE;
			int curEnd2 = -1;
			for (int j = 0; j < K; j++) { // j颜色！
				if (j != preEnd1) {
					if (preMin1 + costs[i][j] < curMin1) {
						curMin2 = curMin1;
						curEnd2 = curEnd1;
						curMin1 = preMin1 + costs[i][j];
						curEnd1 = j;
					} else if (preMin1 + costs[i][j] < curMin2) {
						curMin2 = preMin1 + costs[i][j];
						curEnd2 = j;
					}
				} else if (j != preEnd2) {
					if (preMin2 + costs[i][j] < curMin1) {
						curMin2 = curMin1;
						curEnd2 = curEnd1;
						curMin1 = preMin2 + costs[i][j];
						curEnd1 = j;
					} else if (preMin2 + costs[i][j] < curMin2) {
						curMin2 = preMin2 + costs[i][j];
						curEnd2 = j;
					}
				}
			}
			preMin1 = curMin1;
			preEnd1 = curEnd1;
			preMin2 = curMin2;
			preEnd2 = curEnd2;
		}
		return preMin1;
	}

}