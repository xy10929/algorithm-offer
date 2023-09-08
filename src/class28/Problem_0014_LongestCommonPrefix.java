package class28;

public class Problem_0014_LongestCommonPrefix {

	public String longestCommonPrefix(String[] strs) {
		char[] chs = strs[0].toCharArray();// 首个字符串
		int ans = chs.length;
		for (String str : strs) {
			char[] cur = str.toCharArray();// 当前字符串
			int index = 0;
			while (index < chs.length && index < cur.length) {
				if (chs[index] != cur[index]) {
					break;
				}
				index++;
			}
			ans = Math.min(ans, index);
		}
		return strs[0].substring(0, ans);
	}

	public static String longestCommonPrefix1(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		char[] chs = strs[0].toCharArray();
		int min = Integer.MAX_VALUE;
		for (String str : strs) {
			char[] tmp = str.toCharArray();
			int index = 0;
			while (index < tmp.length && index < chs.length) {
				if (chs[index] != tmp[index]) {
					break;
				}
				index++;
			}
			min = Math.min(index, min);
			if (min == 0) {
				return "";
			}
		}
		return strs[0].substring(0, min);
	}

}