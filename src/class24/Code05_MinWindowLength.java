package class24;
//lc76
public class Code05_MinWindowLength {
//	public String minWindow(String s, String t) {
//	char[] str1 = s.toCharArray();
//	char[] str2 = t.toCharArray();
//	int[] map = new int[256];//欠账表
//	for (char c : str2) {
//		map[c]++;//生成记录
//	}
//	int all = str2.length;//欠账总数
//	int n = str1.length;
//	int left = 0;
//	int right = 0;//[left,right)为有效区域
//	int min = Integer.MAX_VALUE;//最短子串长度
//	int ansl = -1;
//	int ansr = -1;//最短子串的起止位置
//	for (; right < n; right++) {//窗口右边界右滑并更新还款直至all为0  找到首个符合要求的子串 对于每个找到的子串 右滑左边界直至首次出现再增就超过0的记录(已经不能在缩短) 更新并记录此时的结果 右边界右滑一个字符重复操作
//		map[str1[right]]--;//更新右边界右滑时的欠账表
//		if (map[str1[right]] >= 0) {//还款后对应字符的数量不小于0则是有效还款 更新all
//			all--;
//		}
//		if (all == 0) {//找到了右边界最少需要滑动到的位置 尝试左边界右滑来缩小窗口
//			while (map[str1[left]] < 0) {//原欠账如果为负数则即使当前字符滑出窗口 仍能保证欠账all为0
//				map[str1[left++]]++;
//			}//找到了当前右边界的最小窗口
//			if (right - left + 1 < min) {//更新最小窗口的信息
//				min = right - left + 1;
//				ansl = left;
//				ansr = right;
//			}
//			map[str1[left++]]++;//左边界右滑到下一个位置(变得出现欠账)
//			all++;//更新all  右边界准备继续右滑
//		}
//	}
//	return min == Integer.MAX_VALUE ? "" : s.substring(ansl, ansr + 1);
//}
	public static int minLength(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() < s2.length()) {
			return Integer.MAX_VALUE;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int[] map = new int[256]; // map[37] = 4 37 4次
		for (int i = 0; i != str2.length; i++) {
			map[str2[i]]++;
		}
		int all = str2.length;

		// [L,R-1] R
		// [L,R) -> [0,0)
		int L = 0;
		int R = 0;
		int minLen = Integer.MAX_VALUE;
		while (R != str1.length) {
			map[str1[R]]--;
			if (map[str1[R]] >= 0) {
				all--;
			}
			if (all == 0) { // 还完了
				while (map[str1[L]] < 0) {
					map[str1[L++]]++;
				}
				// [L..R]
				minLen = Math.min(minLen, R - L + 1);
				all++;
				map[str1[L++]]++;
			}
			R++;
		}
		return minLen == Integer.MAX_VALUE ? 0 : minLen;
	}

	// 测试链接 : https://leetcode.com/problems/minimum-window-substring/
	public static String minWindow(String s, String t) {
		if (s.length() < t.length()) {
			return "";
		}
		char[] str = s.toCharArray();
		char[] target = t.toCharArray();
		int[] map = new int[256];
		for (char cha : target) {
			map[cha]++;
		}
		int all = target.length;
		int L = 0;
		int R = 0;
		int minLen = Integer.MAX_VALUE;
		int ansl = -1;
		int ansr = -1;
		while (R != str.length) {
			map[str[R]]--;
			if (map[str[R]] >= 0) {
				all--;
			}
			if (all == 0) {
				while (map[str[L]] < 0) {
					map[str[L++]]++;
				}
				if (minLen > R - L + 1) {
					minLen = R - L + 1;
					ansl = L;
					ansr = R;
				}
				all++;
				map[str[L++]]++;
			}
			R++;
		}
		return minLen == Integer.MAX_VALUE ? "" : s.substring(ansl, ansr + 1);
	}

}