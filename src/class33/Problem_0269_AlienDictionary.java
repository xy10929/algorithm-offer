package class33;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Problem_0269_AlienDictionary {
//	public String alienOrder(String[] words) {
//	int n = words.length;
//	char[][] info = new char[n - 1][];//存放相邻的两个单词得到的拓扑排序信息-前置信息
//	boolean[] letters = new boolean[26];//记录出现过哪些字符
//	for (int i = 0; i < n - 1; i++) {
//		char[] w1 = words[i].toCharArray();
//		char[] w2 = words[i + 1].toCharArray();
//		info[i] = get(w1, w2);//相邻的两个单词得到的拓扑排序信息/代表无信息的空数组存入info
//	}
//	HashMap<Character, letter> map = new HashMap<>();//k-字符  v-含有入度&出度信息的字符节点
//	for (int i = 0; i < n - 1; i++) {
//		if (info[i].length != 0) {//有拓扑排序信息 则建立相应节点
//			char from = info[i][0];
//			char to = info[i][1];
//			if (!map.containsKey(from)) {
//				map.put(from, new letter(from));
//			}
//			if (!map.containsKey(to)) {
//				map.put(to, new letter(to));
//			}
//			letter f = map.get(from);
//			letter t = map.get(to);
//			t.in++;
//			f.nexts.add(t);
//		} else {//如果无拓扑排序信息 则必须保证长度没有变短  words才是有效的字典序结果
//			if (words[i].length() > words[i + 1].length()) {
//				return "";
//			}
//		}
//	}
//	int count = map.size();
//	StringBuilder ans = new StringBuilder();
//	for (int i = 0; i < n; i++) {//收集出现的所有字符
//		char[] w = words[i].toCharArray();
//		allletters(w, letters);
//	}
//	if (count == 0) {//没有拓扑排序信息 且words有效 则直接输出所有出现过的字符即使一种结果
//		for (int i = 0; i < 26; i++) {
//			if (letters[i]) {
//				char k = (char) ('a' + i);
//				ans.append(k);
//				letters[i] = false;
//			}
//		}
//		return ans.toString();
//	}
//	//有拓扑排序信息(箭头)
//	Queue<letter> zero = new LinkedList<>();//存放节点的队列
//	for (letter l : map.values()) {
//		if (l.in == 0) {
//			zero.add(l);//收集入度为0的节点进队列
//		}
//	}
//	while (!zero.isEmpty()) {//弹出节点 消除影响 将指向的节点加入队列
//		letter cur = zero.poll();
//		count--;//箭头数量--
//		ans.append(cur.c);//将字符记入结果字符串
//		letters[cur.c - 'a'] = false;//用true记录还未放入结果字符串的字符
//		for (letter next : cur.nexts) {
//			if (--next.in == 0) {
//				zero.add(next);
//			}
//		}
//	}
//	if (count != 0) {//存在闭环 无法进行拓扑排序
//		return "";
//	}
//	for (int i = 0; i < 26; i++) {//把还未放入结果字符串的字符加入ans
//		if (letters[i]) {
//			char k = (char) ('a' + i);
//			ans.append(k);
//		}
//	}
//	return ans.toString();
//}
//
//public void allletters(char[] w, boolean[] letters) {//收集字符串中的字符
//	for (int i = 0; i < w.length; i++) {
//		letters[w[i] - 'a'] = true;
//	}
//}
//
//public class letter {
//	public char c;
//	public int in;//入度
//	public ArrayList<letter> nexts;//出度
//
//	public letter(char c) {
//		this.c = c;
//		in = 0;
//		nexts = new ArrayList<>();
//	}
//}
//
//public char[] get(char[] s1, char[] s2) {
//	for (int i = 0; i < s1.length && i < s2.length; i++) {
//		if (s1[i] != s2[i]) {//两个单词中找到首个同位置不同字符的一对结果
//			return new char[] { s1[i], s2[i] };
//		}
//	}
//	return new char[] {};
//}
	public static String alienOrder(String[] words) {
		if (words == null || words.length == 0) {
			return "";
		}
		int N = words.length;
		HashMap<Character, Integer> indegree = new HashMap<>();
		for (int i = 0; i < N; i++) {
			for (char c : words[i].toCharArray()) {
				indegree.put(c, 0);
			}
		}
		HashMap<Character, HashSet<Character>> graph = new HashMap<>();
		for (int i = 0; i < N - 1; i++) {
			char[] cur = words[i].toCharArray();
			char[] nex = words[i + 1].toCharArray();
			int len = Math.min(cur.length, nex.length);
			int j = 0;
			for (; j < len; j++) {
				if (cur[j] != nex[j]) {
					if (!graph.containsKey(cur[j])) {
						graph.put(cur[j], new HashSet<>());
					}
					if (!graph.get(cur[j]).contains(nex[j])) {
						graph.get(cur[j]).add(nex[j]);
						indegree.put(nex[j], indegree.get(nex[j]) + 1);
					}
					break;
				}
			}
			if (j < cur.length && j == nex.length) {
				return "";
			}
		}
		StringBuilder ans = new StringBuilder();
		Queue<Character> q = new LinkedList<>();
		for (Character key : indegree.keySet()) {
			if (indegree.get(key) == 0) {
				q.offer(key);
			}
		}
		while (!q.isEmpty()) {
			char cur = q.poll();
			ans.append(cur);
			if (graph.containsKey(cur)) {
				for (char next : graph.get(cur)) {
					indegree.put(next, indegree.get(next) - 1);
					if (indegree.get(next) == 0) {
						q.offer(next);
					}
				}
			}
		}
		return ans.length() == indegree.size() ? ans.toString() : "";
	}

}