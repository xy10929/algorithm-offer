package class26;
//lc212
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

// 本题测试链接 : https://leetcode.com/problems/word-search-ii/
public class Code02_WordSearchII {
//	public List<String> findWords(char[][] board, String[] words) {
//	node head = new node();
//	for (String word : words) {
//		trie(head, word);//把单词放入前缀树
//	}
//	List<String> ans = new ArrayList<>();
//	LinkedList<Character> path = new LinkedList<>();//存放已走过的路径
//	for (int i = 0; i < board.length; i++) {
//		for (int j = 0; j < board[0].length; j++) {
//			process(board, i, j, path, head, ans);
//		}
//	}
//	return ans;
//}
//
//public class node {//前缀树节点
//	public node[] nexts;//路径对应的数组的下标记录字符
//	public boolean isend;//到达当前节点的路径对应的字符是否为单词结尾
//	public int pass;//节点被经过的次数 当减为0时 说明经过该节点的所有可能的情况都已经被检查过了 用于加速
//
//	public node() {
//		nexts = new node[26];
//		isend = false;
//		pass = 0;
//	}
//}
//
//public void trie(node head, String word) {
//	head.pass++;//从头结点开始
//	char[] str = word.toCharArray();
//	int index = 0;//表示字符在nexts数组中的坐标
//	node cur = head;
//	for (int i = 0; i < str.length; i++) {
//		index = str[i] - 'a';
//		if (cur.nexts[index] == null) {//如果对应的路径不存在则创建
//			cur.nexts[index] = new node();
//		}
//		cur = cur.nexts[index];//到达下一个对应节点
//		cur.pass++;//当前节点到达次数+1
//	}
//	cur.isend = true;//到达单词结尾时标记节点
//}
//
//public int process(char[][] board, int i, int j, LinkedList<Character> path, node cur, List<String> ans) {//将要尝试从(i,j)位置开始走 用以cur为起始节点的前缀树加速过程 已走过的路径存入path  path有效时将其存入ans  返回有效的path个数
//	char c = board[i][j];//记录当前位置的字符
//	if (c == '*') {//如果到了已经走过的字符则无效 
//		return 0;
//	}
//	int index = c - 'a';
//	if (cur.nexts[index] == null || cur.nexts[index].pass == 0) {//如果路径不存在或经过该节点的情况都已经检查过了
//		return 0;
//	}//可以以(i,j)位置为开头进行尝试
//	cur = cur.nexts[index];//沿路径到达下一个节点
//	path.addLast(c);//(i,j)位置字符加入path
//	int num = 0;//有效结果个数
//	if (cur.isend) {//到当前节点的路径对应的字符如果是单词的结尾
//		ans.add(generate(path));//path转换成字符串 加入结果
//		cur.isend = false;//当前位置作为结尾的结果对应的字符串已经记入了结果 把isend变为false 便不再重复记录
//		num++;
//	}
//	board[i][j] = '*';//把当前位置的字符改为*  表示当前已经经过了该位置 避免路径无效
//	if (i > 0) {
//		num += process(board, i - 1, j, path, cur, ans);
//	}
//	if (i < board.length - 1) {
//		num += process(board, i + 1, j, path, cur, ans);
//	}
//	if (j > 0) {
//		num += process(board, i, j - 1, path, cur, ans);
//	}
//	if (j < board[0].length - 1) {
//		num += process(board, i, j + 1, path, cur, ans);
//	}//向上下左右四个方向尝试移动 把结果记入ans  结果个数累加入num
//	board[i][j] = c;//恢复现场 把board改为原始的样子 以便对 以 下一个board中的位置的字符 开头 的情况 进行计算
//	path.pollLast();//恢复现场 消除 (i,j)位置字符加入path的尝试 的影响
//	cur.pass -= num;//共得到num个经过cur节点的有效的结果 从pass中减去
//	return num;
//}
//
//public String generate(LinkedList<Character> path) {//把path转换为char数组再转化为字符串返回
//	char[] str = new char[path.size()];
//	int index = 0;
//	for (Character c : path) {
//		str[index] = c;
//		index++;
//	}
//	return String.valueOf(str);
//}
	public static class TrieNode {
		public TrieNode[] nexts;
		public int pass;
		public boolean end;

		public TrieNode() {
			nexts = new TrieNode[26];
			pass = 0;
			end = false;
		}

	}

	public static void fillWord(TrieNode head, String word) {
		head.pass++;
		char[] chs = word.toCharArray();
		int index = 0;
		TrieNode node = head;
		for (int i = 0; i < chs.length; i++) {
			index = chs[i] - 'a';
			if (node.nexts[index] == null) {
				node.nexts[index] = new TrieNode();
			}
			node = node.nexts[index];
			node.pass++;
		}
		node.end = true;
	}

	public static String generatePath(LinkedList<Character> path) {
		char[] str = new char[path.size()];
		int index = 0;
		for (Character cha : path) {
			str[index++] = cha;
		}
		return String.valueOf(str);
	}

	public static List<String> findWords(char[][] board, String[] words) {
		TrieNode head = new TrieNode(); // 前缀树最顶端的头
		HashSet<String> set = new HashSet<>();
		for (String word : words) {
			if (!set.contains(word)) {
				fillWord(head, word);
				set.add(word);
			}
		}
		// 答案
		List<String> ans = new ArrayList<>();
		// 沿途走过的字符，收集起来，存在path里
		LinkedList<Character> path = new LinkedList<>();
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				// 枚举在board中的所有位置
				// 每一个位置出发的情况下，答案都收集
				process(board, row, col, path, head, ans);
			}
		}
		return ans;
	}

	// 从board[row][col]位置的字符出发，
	// 之前的路径上，走过的字符，记录在path里
	// cur还没有登上，有待检查能不能登上去的前缀树的节点
	// 如果找到words中的某个str，就记录在 res里
	// 返回值，从row,col 出发，一共找到了多少个str
	public static int process(
			char[][] board, int row, int col, 
			LinkedList<Character> path, TrieNode cur,
			List<String> res) {
		char cha = board[row][col];
		if (cha == 0) { // 这个row col位置是之前走过的位置
			return 0;
		}
		// (row,col) 不是回头路 cha 有效

		int index = cha - 'a';
		// 如果没路，或者这条路上最终的字符串之前加入过结果里
		if (cur.nexts[index] == null || cur.nexts[index].pass == 0) {
			return 0;
		}
		// 没有走回头路且能登上去
		cur = cur.nexts[index];
		path.addLast(cha);// 当前位置的字符加到路径里去
		int fix = 0; // 从row和col位置出发，后续一共搞定了多少答案
		// 当我来到row col位置，如果决定不往后走了。是不是已经搞定了某个字符串了
		if (cur.end) {
			res.add(generatePath(path));
			cur.end = false;
			fix++;
		}
		// 往上、下、左、右，四个方向尝试
		board[row][col] = 0;
		if (row > 0) {
			fix += process(board, row - 1, col, path, cur, res);
		}
		if (row < board.length - 1) {
			fix += process(board, row + 1, col, path, cur, res);
		}
		if (col > 0) {
			fix += process(board, row, col - 1, path, cur, res);
		}
		if (col < board[0].length - 1) {
			fix += process(board, row, col + 1, path, cur, res);
		}
		board[row][col] = cha;
		path.pollLast();
		cur.pass -= fix;
		return fix;
	}

}