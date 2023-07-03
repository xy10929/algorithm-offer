package class26;
//lc282
import java.util.LinkedList;
import java.util.List;

// 本题测试链接 : https://leetcode.com/problems/expression-add-operators/
public class Code03_ExpressionAddOperators {
//	public List<String> addOperators(String num, int target) {
//	List<String> ans = new ArrayList<>();
//	char[] path = new char[num.length() * 2 - 1];//如果每个可能的位置都插入符合 长度为2n-1
//	char[] nums = num.toCharArray();
//	long first = 0;//第一个数的大小
//	for (int i = 0; i < nums.length; i++) {//尝试第一个数长度从1到n时 后续的所有情况
//		first = first * 10 + nums[i] - '0';//更新计算第一个数的大小
//		path[i] = nums[i];//把第一个数的所有字符从nums抄至path
//		process(nums, i + 1, target, path, i + 1, ans, 0, first);//nums从下一个字符开始检查 从0到i共i+1长度的path已经决定好(里面只有第一个数)  仅存的第一个数后面的符号未知 属于未知的部分
//		if (first == 0) {//0之后不能跟任何的数以组成一个数字
//			break;
//		}
//	}
//	return ans;
//}
//
//public void process(char[] num, int index, int aim, char[] path, int len, List<String> ans, long pre, long cur) {//从num的index位置开始遍历字符 path存放之前已经做好决定的字符的结果 长度为len  之前可以确定的结果为pre  不能确定的部分结果为cur  满足条件的结果存入ans
//	if (index == num.length) {//base case:index到中止位置
//		if (pre + cur == aim) {
//			ans.add(new String(path, 0, len));
//		}
//		return;
//	}
//	long c = 0;//已经可以确定的结果
//	int j = len + 1;//把第一个数前面的符号固定地放在len位置 当前要填的数的字符放在j位置  j随着第一个数的长度向右移动
//	for (int i = index; i < num.length; i++) {//num的index到i位置的所有字符作为第一个数 每次向右多填一个字符数以增加第一个数的长度 再分别在前面尝试三种不同的符号 尝试完成后 继续增大第一个数的位数 把下一个字符放在j位置
//		c = c * 10 + num[i] - '0';//第一个数的大小为已经确定的部分的结果
//		path[j++] = num[i];
//		path[len] = '+';
//		process(num, i + 1, aim, path, j, ans, pre + cur, c);//num从第一个数的结尾位置i的下一个位置查看  path要填的下一个字符在j位置 即已填的长度为j  pre和cur的变化随符号而定
//		path[len] = '-';
//		process(num, i + 1, aim, path, j, ans, pre + cur, -c);
//		path[len] = '*';
//		process(num, i + 1, aim, path, j, ans, pre, cur * c);
//		if (num[index] == '0') {//如果当前位置字符是0则其不能作为一个数的开头 只能单独出现 第一个数的长度只能是1  循环仅执行一次后结束
//			break;
//		}
//	}
//}
	public static List<String> addOperators(String num, int target) {
		List<String> ret = new LinkedList<>();
		if (num.length() == 0) {
			return ret;
		}
		// 沿途的数字拷贝和+ - * 的决定，放在path里
		char[] path = new char[num.length() * 2 - 1];
		// num -> char[]
		char[] digits = num.toCharArray();
		long n = 0;
		for (int i = 0; i < digits.length; i++) { // 尝试0~i前缀作为第一部分
			n = n * 10 + digits[i] - '0';
			path[i] = digits[i];
			dfs(ret, path, i + 1, 0, n, digits, i + 1, target); // 后续过程
			if (n == 0) {
				break;
			}
		}
		return ret;
	}

	// char[] digits 固定参数，字符类型数组，等同于num
	// int target 目标
	// char[] path 之前做的决定，已经从左往右依次填写的字符在其中，可能含有'0'~'9' 与 * - +
	// int len path[0..len-1]已经填写好，len是终止
	// int pos 字符类型数组num, 使用到了哪
	// left -> 前面固定的部分 cur -> 前一块
	// 默认 left + cur ...
	public static void dfs(List<String> res, char[] path, int len, 
			long left, long cur, 
			char[] num, int index, int aim) {
		if (index == num.length) {
			if (left + cur == aim) {
				res.add(new String(path, 0, len));
			}
			return;
		}
		long n = 0;
		int j = len + 1;
		for (int i = index; i < num.length; i++) { // pos ~ i
			// 试每一个可能的前缀，作为第一个数字！
			// num[index...i] 作为第一个数字！
			n = n * 10 + num[i] - '0';
			path[j++] = num[i];
			path[len] = '+';
			dfs(res, path, j, left + cur, n, num, i + 1, aim);
			path[len] = '-';
			dfs(res, path, j, left + cur, -n, num, i + 1, aim);
			path[len] = '*';
			dfs(res, path, j, left, cur * n, num, i + 1, aim);
			if (num[index] == '0') {
				break;
			}
		}
	}

}