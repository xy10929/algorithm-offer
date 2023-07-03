package class17;
//lc940
import java.util.HashMap;

// 本题测试链接 : https://leetcode.com/problems/distinct-subsequences-ii/
public class Code05_DistinctSubseqValue {
//	public int distinctSubseqII(String S) {
//		char[] s = S.toCharArray();
//		int n = s.length;
//		long[] lastTimePreChar = new long[26];//记录对应字符上次出现时 以出现位置的前一个位置为结尾的结果
//		long ans = 1;//未查看字符串时 只有一个空字符
//		int mod = 1000000007;
//		for (int i = 0; i < n; i++) {
//			long pre = ans;//到每个位置时 记录以前一个位置的结果 下次遍历到该字符产生重复时 减去对应的结果
//			ans = (ans * 2 - lastTimePreChar[s[i] - 'a'] + mod) % mod;//从数组中取数(字符上一次出现时 前一个位置的结果/字符没有出现过 为0)用于当前位置的计算 取余的数做被减数时 多加一个mod
//			lastTimePreChar[s[i] - 'a'] = pre;//前一个位置计算出的结果存入 用于以后该字符再次出现时的计算使用
//		}
//		return (int) ((ans - 1 + mod) % mod);
//	}

	public static int distinctSubseqII(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		long m = 1000000007;
		char[] str = s.toCharArray();
		long[] count = new long[26];
		long all = 1; // 算空集
		for (char x : str) {
			long add = (all - count[x - 'a'] + m) % m;
			all = (all + add) % m;
			count[x - 'a'] = (count[x - 'a'] + add) % m;
		}
		all = (all - 1 + m) % m;
		return (int) all;
	}

	public static int zuo(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int m = 1000000007;
		char[] str = s.toCharArray();
		HashMap<Character, Integer> map = new HashMap<>();
		int all = 1; // 一个字符也没遍历的时候，有空集
		for (char x : str) {
			int newAdd = all;
//			int curAll = all + newAdd - (map.containsKey(x) ? map.get(x) : 0);
			int curAll = all;
			curAll = (curAll + newAdd) % m;
			curAll = (curAll - (map.containsKey(x) ? map.get(x) : 0) + m) % m;
			all = curAll;
			map.put(x, newAdd);
		}
		return all;
	}

	public static void main(String[] args) {
		String s = "bccaccbaabbc";
		System.out.println(distinctSubseqII(s) + 1);
		System.out.println(zuo(s));
	}

}