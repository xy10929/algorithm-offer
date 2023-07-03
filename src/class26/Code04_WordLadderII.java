package class26;
//lc126
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 本题测试链接 : https://leetcode.com/problems/word-ladder-ii/
public class Code04_WordLadderII {
//	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
//	wordList.add(beginWord);
//	HashMap<String, List<String>> nexts = getnexts(wordList);
//	HashMap<String, Integer> begindistances = getdistances(beginWord, nexts);
//	List<List<String>> ans = new ArrayList<>();
//	if (!begindistances.containsKey(endWord)) {
//		return ans;
//	}
//	HashMap<String, Integer> enddistances = getdistances(endWord, nexts);
//	LinkedList<String> path = new LinkedList<>();
//	getshortestpaths(beginWord, endWord, nexts, begindistances, enddistances, path, ans);
//	return ans;
//
//}
//
//public HashMap<String, List<String>> getnexts(List<String> wordlist) {//生成记录邻接字符串的map k-字符串 v-邻接字符串列表
//	HashSet<String> words = new HashSet<>(wordlist);
//	HashMap<String, List<String>> nexts = new HashMap<>();
//	for (int i = 0; i < wordlist.size(); i++) {
//		nexts.put(wordlist.get(i), getnext(wordlist.get(i), words));
//	}
//	return nexts;
//}
//
//public List<String> getnext(String word, HashSet<String> words) {//当前字符串word 找到单词列表中它的所有邻接单词 放入列表返回
//	List<String> ans = new ArrayList<>();
//	char[] str = word.toCharArray();
//	for (int i = 0; i < str.length; i++) {
//		for (char c = 'a'; c <= 'z'; c++) {
//			if (str[i] != c) {
//				char last = str[i];
//				str[i] = c;
//				if (words.contains(String.valueOf(str))) {
//					ans.add(String.valueOf(str));
//				}
//				str[i] = last;
//			}
//		}
//	}
//	return ans;
//}
//
//public HashMap<String, Integer> getdistances(String start, HashMap<String, List<String>> nexts) {// 根据邻居表生成各字符串到start的距离表
//	HashMap<String, Integer> distances = new HashMap<>();
//	distances.put(start, 0);
//	Queue<String> queue = new LinkedList<>();
//	queue.add(start);
//	HashSet<String> set = new HashSet<>();
//	set.add(start);
//	while (!queue.isEmpty()) {
//		String cur = queue.poll();
//		for (String next : nexts.get(cur)) {
//			if (!set.contains(next)) {
//				distances.put(next, distances.get(cur) + 1);
//				queue.add(next);
//				set.add(next);
//			}
//		}
//	}
//	return distances;
//}
//
//public void getshortestpaths(String cur, String end, HashMap<String, List<String>> nexts,
//		HashMap<String, Integer> begindistances, HashMap<String, Integer> enddistances, LinkedList<String> path,
//		List<List<String>> ans) {// 已经走过了path之后 继续走向end 当前即将加入字符串cur 通过nexts获取下一步可能走向的字符串 通过2个distances判断下一步是否需要走向某字符串 如果成功走到end 结果记入ans
//	path.add(cur);
//	if (end.equals(cur)) {
//		ans.add(new LinkedList<String>(path));
//	} else {
//		for (String next : nexts.get(cur)) {
//			if (begindistances.get(next) == begindistances.get(cur) + 1
//					&& enddistances.get(next) == enddistances.get(cur) - 1) {
//				getshortestpaths(next, end, nexts, begindistances, enddistances, path, ans);
//			}
//		}
//	}
//	path.pollLast();
//}
	public static List<List<String>> findLadders(String start, String end, List<String> list) {
		list.add(start);
		HashMap<String, List<String>> nexts = getNexts(list);
		HashMap<String, Integer> fromDistances = getDistances(start, nexts);
		List<List<String>> res = new ArrayList<>();
		if (!fromDistances.containsKey(end)) {
			return res;
		}
		HashMap<String, Integer> toDistances = getDistances(end, nexts);
		LinkedList<String> pathList = new LinkedList<>();
		getShortestPaths(start, end, nexts, fromDistances, toDistances, pathList, res);
		return res;
	}

	public static HashMap<String, List<String>> getNexts(List<String> words) {
		HashSet<String> dict = new HashSet<>(words);
		HashMap<String, List<String>> nexts = new HashMap<>();
		for (int i = 0; i < words.size(); i++) {
			nexts.put(words.get(i), getNext(words.get(i), dict));
		}
		return nexts;
	}

	// word, 在表中，有哪些邻居，把邻居们，生成list返回
	public static List<String> getNext(String word, HashSet<String> dict) {
		ArrayList<String> res = new ArrayList<String>();
		char[] chs = word.toCharArray();
		for (char cur = 'a'; cur <= 'z'; cur++) {
			for (int i = 0; i < chs.length; i++) {
				if (chs[i] != cur) {
					char tmp = chs[i];
					chs[i] = cur;
					if (dict.contains(String.valueOf(chs))) {
						res.add(String.valueOf(chs));
					}
					chs[i] = tmp;
				}
			}
		}
		return res;
	}

	// 生成距离表，从start开始，根据邻居表，宽度优先遍历，对于能够遇到的所有字符串，生成(字符串，距离)这条记录，放入距离表中
	public static HashMap<String, Integer> getDistances(String start, HashMap<String, List<String>> nexts) {
		HashMap<String, Integer> distances = new HashMap<>();
		distances.put(start, 0);
		Queue<String> queue = new LinkedList<>();
		queue.add(start);
		HashSet<String> set = new HashSet<>();
		set.add(start);
		while (!queue.isEmpty()) {
			String cur = queue.poll();
			for (String next : nexts.get(cur)) {
				if (!set.contains(next)) {
					distances.put(next, distances.get(cur) + 1);
					queue.add(next);
					set.add(next);
				}
			}
		}
		return distances;
	}

	// cur 当前来到的字符串 可变
	// to 目标，固定参数
	// nexts 每一个字符串的邻居表
	// cur 到开头距离5 -> 到开头距离是6的支路 fromDistances距离表
	// cur 到结尾距离5 -> 到开头距离是4的支路 toDistances距离表
	// path : 来到cur之前，深度优先遍历之前的历史是什么
	// res : 当遇到cur，把历史，放入res，作为一个结果
	public static void getShortestPaths(String cur, String to, HashMap<String, List<String>> nexts,
			HashMap<String, Integer> fromDistances, HashMap<String, Integer> toDistances, LinkedList<String> path,
			List<List<String>> res) {
		path.add(cur);
		if (to.equals(cur)) {
			res.add(new LinkedList<String>(path));
		} else {
			for (String next : nexts.get(cur)) {
				if (fromDistances.get(next) == fromDistances.get(cur) + 1
						&& toDistances.get(next) == toDistances.get(cur) - 1) {
					getShortestPaths(next, to, nexts, fromDistances, toDistances, path, res);
				}
			}
		}
		path.pollLast();
	}

}