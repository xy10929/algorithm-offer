package class28;

public class Problem_0036_ValidSudoku {

	public boolean isValidSudoku(char[][] board) {
		boolean[][] row = new boolean[9][10];// 某行中数字1-9是否已存在
		boolean[][] col = new boolean[9][10];
		boolean[][] box = new boolean[9][10];// 某子数独中数字1-9是否已存在
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.') {// 该位置有数字
					int num = board[i][j] - '0';
					int sub = (i / 3) * 3 + (j / 3);// 子数独编号
					if (row[i][num] || col[j][num] || box[sub][num]) {// 无效判断
						return false;
					}
					row[i][num] = true;
					col[j][num] = true;
					box[sub][num] = true;
				}
			}
		}
		return true;
	}

	public static boolean isValidSudoku1(char[][] board) {
		boolean[][] row = new boolean[9][10];
		boolean[][] col = new boolean[9][10];
		boolean[][] bucket = new boolean[9][10];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int bid = 3 * (i / 3) + (j / 3);
				if (board[i][j] != '.') {
					int num = board[i][j] - '0';
					if (row[i][num] || col[j][num] || bucket[bid][num]) {
						return false;
					}
					row[i][num] = true;
					col[j][num] = true;
					bucket[bid][num] = true;
				}
			}
		}
		return true;
	}

}