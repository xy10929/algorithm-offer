package class31;

import java.util.List;



// lintcode也有测试，数据量比leetcode大很多 : https://www.lintcode.com/problem/107/
public class Problem_0139_WordBreak {
//	public boolean wordBreak(String s, List<String> wordDict) {
//		node root = new node();
//		for (String ss : wordDict) {//把所有字符串加入前缀树
//			char[] str = ss.toCharArray();
//			node n = root;//当前所在的前缀树节点
//			int index = 0;
//			for (int i = 0; i < str.length; i++) {//把当前字符串加入前缀树
//				index = str[i] - 'a';//找到当前字符对应的路径
//				if (n.nexts[index] == null) {//如果没有节点则创建
//					n.nexts[index] = new node();
//				}
//				n = n.nexts[index];//跳转到下一个对应节点
//			}
//			n.end = true;//结尾节点的end即为true
//		}
//		char[] str = s.toCharArray();
//		int n = str.length;
//		boolean[] dp = new boolean[n + 1];//dp[i]表示i及后面的所有字符能否被wordDict拆分出来
//		dp[n] = true;//任何wordDict都可以分解出空字符串
//		for (int i = n - 1; i >= 0; i--) {
//			node cur = root;
//			for (int end = i; end < n; end++) {//end作为拆分出的第一部分的结尾 尝试从i到末尾位置的全部情况
//				cur = cur.nexts[str[end] - 'a'];//跳转到前缀树的下一个节点
//				if (cur == null) {//节点不存在 
//					break;//当前i到end作为第一部分的尝试失败 以下一个位置作为end进行尝试
//				}
//				if (cur.end) {//是有效的结尾 则i到end是一个有效的前缀串 继续查看end+1位置开始向后的部分能否被有效拆分
//					dp[i] |= dp[end + 1];//得到当前i到end作为第一部分的尝试是否成功
//				}
//				if (dp[i]) {//当前i到end作为第一部分的尝试成功 代表当前的i及后面的所有字符能否被wordDict拆分出来 继续计算向左一个位置的i的情况
//					break;
//				}
//			}
//		}
//		return dp[0];
//	}
//
//	public class node {//前缀树节点
//		public boolean end;//是否作为结尾
//		public node[] nexts;//路径记录字符
//
//		public node() {
//			end = false;
//			nexts = new node[26];//对应下标是否存在节点 代表 字符对应的路径是否存在
//		}
	public static class Node {
		public boolean end;
		public Node[] nexts;

		public Node() {
			end = false;
			nexts = new Node[26];
		}
	}

	public static boolean wordBreak1(String s, List<String> wordDict) {
		Node root = new Node();
		for (String str : wordDict) {
			char[] chs = str.toCharArray();
			Node node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					node.nexts[index] = new Node();
				}
				node = node.nexts[index];
			}
			node.end = true;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		boolean[] dp = new boolean[N + 1];
		dp[N] = true; // dp[i]  word[i.....] 能不能被分解
		// dp[N] word[N...]  -> ""  能不能够被分解 
		// dp[i] ... dp[i+1....]
		for (int i = N - 1; i >= 0; i--) {
			// i
			// word[i....] 能不能够被分解
			// i..i    i+1....
			// i..i+1  i+2...
			Node cur = root;
			for (int end = i; end < N; end++) {
				cur = cur.nexts[str[end] - 'a'];
				if (cur == null) {
					break;
				}
				// 有路！
				if (cur.end) {
					// i...end 真的是一个有效的前缀串  end+1....  能不能被分解
					dp[i] |= dp[end + 1];
				}
				if (dp[i]) {
					break;
				}
			}
		}
		return dp[0];
	}

	public static int wordBreak2(String s, List<String> wordDict) {
		Node root = new Node();
		for (String str : wordDict) {
			char[] chs = str.toCharArray();
			Node node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					node.nexts[index] = new Node();
				}
				node = node.nexts[index];
			}
			node.end = true;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		int[] dp = new int[N + 1];
		dp[N] = 1;
		for (int i = N - 1; i >= 0; i--) {
			Node cur = root;
			for (int end = i; end < N; end++) {
				cur = cur.nexts[str[end] - 'a'];
				if (cur == null) {
					break;
				}
				if (cur.end) {
					dp[i] += dp[end + 1];
				}
			}
		}
		return dp[0];
	}

}