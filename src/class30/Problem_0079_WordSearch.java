package class30;

//lc79
public class Problem_0079_WordSearch {

	public boolean exist(char[][] board, String word) {
		char[] w = word.toCharArray();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (f(board, i, j, w, 0)) {// 对每个位置开始的情况进行验证
					return true;
				}
			}
		}
		return false;
	}

	public boolean f(char[][] b, int i, int j, char[] w, int k) {// 从(i,j)开始能否形成w从k位置开始的内容
		if (k == w.length) {// 匹配成功
			return true;
		}
		if (i < 0 || i == b.length || j < 0 || j == b[0].length) {// 走出界
			return false;
		}
		if (b[i][j] != w[k]) {// 当前位置不匹配
			return false;
		}
		char cur = b[i][j];// 每个位置不能重复使用 先记录其内容 改为无法匹配的结果 函数return前还原
		b[i][j] = 0;
		boolean ans = f(b, i, j + 1, w, k + 1) || f(b, i, j - 1, w, k + 1) || f(b, i + 1, j, w, k + 1)
				|| f(b, i - 1, j, w, k + 1);
		b[i][j] = cur;
		return ans;
	}

//	public static boolean exist(char[][] board, String word) {
//		char[] w = word.toCharArray();
//		for (int i = 0; i < board.length; i++) {
//			for (int j = 0; j < board[0].length; j++) {
//				if (f(board, i, j, w, 0)) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//
//	// 目前到达了b[i][j]，word[k....]
//	// 从b[i][j]出发，能不能搞定word[k....] true false
//	public static boolean f(char[][] b, int i, int j, char[] w, int k) {
//		if (k == w.length) {
//			return true;
//		}
//		// word[k.....] 有字符
//		// 如果(i,j)越界，返回false
//		if (i < 0 || i == b.length || j < 0 || j == b[0].length) {
//			return false;
//		}
//		if (b[i][j] != w[k]) {
//			return false;
//		}
//		char tmp = b[i][j];
//		b[i][j] = 0;
//		boolean ans = f(b, i - 1, j, w, k + 1) 
//				|| f(b, i + 1, j, w, k + 1) 
//				|| f(b, i, j - 1, w, k + 1)
//				|| f(b, i, j + 1, w, k + 1);
//		b[i][j] = tmp;
//		return ans;
//	}

}