package class38;

public class Problem_0647_PalindromicSubstrings {
//	public int countSubstrings(String s) {
//	char[] str = s.toCharArray();
//	int n = str.length;
//	boolean[][] dp = new boolean[n][n];
//	int ans = 0;
//	for (int i = 0; i < n - 1; i++) {
//		dp[i][i] = true;
//		dp[i][i + 1] = str[i] == str[i + 1];
//		ans += dp[i][i + 1] ? 2 : 1;
//	}
//	// dp[n - 1][n - 1] = true;
//	ans++;
//	for (int i = n - 3; i >= 0; i--) {
//		for (int j = n - 1; j > i + 1; j--) {
//			if (dp[i + 1][j - 1] && (str[i] == str[j])) {
//				dp[i][j] = true;
//				ans++;
//			}
//		}
//	}
//	return ans;
//}
	public static int countSubstrings(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int[] dp = getManacherDP(s);
		int ans = 0;
		for (int i = 0; i < dp.length; i++) {
			ans += dp[i] >> 1;
		}
		return ans;
	}

	public static int[] getManacherDP(String s) {
		char[] str = manacherString(s);
		int[] pArr = new int[str.length];
		int C = -1;
		int R = -1;
		for (int i = 0; i < str.length; i++) {
			pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
			while (i + pArr[i] < str.length && i - pArr[i] > -1) {
				if (str[i + pArr[i]] == str[i - pArr[i]])
					pArr[i]++;
				else {
					break;
				}
			}
			if (i + pArr[i] > R) {
				R = i + pArr[i];
				C = i;
			}
		}
		return pArr;
	}

	public static char[] manacherString(String str) {
		char[] charArr = str.toCharArray();
		char[] res = new char[str.length() * 2 + 1];
		int index = 0;
		for (int i = 0; i != res.length; i++) {
			res[i] = (i & 1) == 0 ? '#' : charArr[index++];
		}
		return res;
	}

}