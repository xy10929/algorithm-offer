package class34;

// 有关这个游戏更有意思、更完整的内容：
// https://www.bilibili.com/video/BV1rJ411n7ri
// 也推荐这个up主
public class Problem_0289_GameOfLife {
//	public void gameOfLife(int[][] m) {
//	for (int i = 0; i < m.length; i++) {
//		for (int j = 0; j < m[0].length; j++) {
//			process(m, i, j);//记录下一步的变化的信息
//		}
//	}
//	//1-当前为1 要变成0
//	//3-当前为1 要保持为1
//	//0-当前为0 要保持为0
//	//2-当前为0 要变成1
//	for (int i = 0; i < m.length; i++) {
//		for (int j = 0; j < m[0].length; j++) {
//			m[i][j] >>= 1;
//		}
//	}
//}
//
//public void process(int[][] m, int i, int j) {
//	int count = live(m, i - 1, j - 1) +
//				live(m, i - 1, j) +
//				live(m, i - 1, j + 1) +
//				live(m, i, j - 1) +
//				live(m, i, j + 1) +
//				live(m, i + 1, j - 1) +
//				live(m, i + 1, j) +
//				live(m, i + 1, j + 1);//周围的存活个数
//	if (m[i][j] == 1) {//当前是存活
//		if (count == 2 || count == 3) {//存活条件
//			m[i][j] = 3;//3表示保持存活
//		}
//	} else {//当前是死亡
//		if (count == 3) {//复活条件
//			m[i][j] = 2;//2表示将要复活
//		}
//	}
//}
//
//public int live(int[][] m, int i, int j) {//当前位置存活则返回1
//	if (i < 0 || i == m.length || j < 0 || j == m[0].length) {//越界判断
//		return 0;
//	}
//	return (m[i][j] == 1 || m[i][j] == 3) ? 1 : 0;//8个方向的位置有可能已经被更新过  1或3表示当前是1
//}
	public static void gameOfLife(int[][] board) {
		int N = board.length;
		int M = board[0].length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int neighbors = neighbors(board, i, j);
				if (neighbors == 3 || (board[i][j] == 1 && neighbors == 2)) {
					board[i][j] |= 2;
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				board[i][j] >>= 1;
			}
		}
	}

	// b[i][j] 这个位置的数，周围有几个1
	public static int neighbors(int[][] b, int i, int j) {
		return f(b, i - 1, j - 1)
				+ f(b, i - 1, j)
				+ f(b, i - 1, j + 1)
				+ f(b, i, j - 1)
				+ f(b, i, j + 1)
				+ f(b, i + 1, j - 1)
				+ f(b, i + 1, j)
				+ f(b, i + 1, j + 1);
	}

	// b[i][j] 上面有1，就返回1，上面不是1，就返回0
	public static int f(int[][] b, int i, int j) {
		return (i >= 0 && i < b.length && j >= 0 && j < b[0].length && (b[i][j] & 1) == 1) ? 1 : 0;
	}

}