package class32;

public class Problem_0171_ExcelSheetColumnNumber {
//	public int titleToNumber(String columnTitle) {
//		char[] str = columnTitle.toCharArray();
//		int ans = 0;
//		int k = 1;
//		for (int i = str.length - 1; i >= 0; i--) {
//			ans += (str[i] - 'A' + 1) * k;
//			k *= 26;
//		}
//		return ans;
//	}
	// 这道题反过来也要会写
	public static int titleToNumber(String s) {
		char[] str = s.toCharArray();
		int ans = 0;
		for (int i = 0; i < str.length; i++) {
			ans = ans * 26 + (str[i] - 'A') + 1;
		}
		return ans;
	}

}