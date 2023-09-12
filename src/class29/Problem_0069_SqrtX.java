package class29;

public class Problem_0069_SqrtX {

	public int mySqrt(int x) {
		if (x == 0) {
			return 0;
		}
		long ans = 0;
		long start = 1;
		long end = x;
		long m = 0;
		while (start <= end) {// 二分范围上有数
			m = (start + end) / 2;
			if (m * m <= x) {// m^2不超过x, 先设为ans, 尝试更大的结果
				ans = m;
				start = m + 1;
			} else {
				end = m - 1;
			}
		}
		return (int) ans;
	}

	// x一定非负，输入可以保证
	public static int mySqrt1(int x) {
		if (x == 0) {
			return 0;
		}
		if (x < 3) {
			return 1;
		}
		// x >= 3
		long ans = 1;
		long L = 1;
		long R = x;
		long M = 0;
		while (L <= R) {
			M = (L + R) / 2;
			if (M * M <= x) {
				ans = M;
				L = M + 1;
			} else {
				R = M - 1;
			}
		}
		return (int) ans;
	}

}