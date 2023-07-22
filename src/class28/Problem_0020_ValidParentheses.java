package class28;

public class Problem_0020_ValidParentheses {

	public boolean isValid(String s) {
		char[] str = s.toCharArray();
		int n = str.length;
		char[] stack = new char[n];// 数组表示栈
		int size = 0;// size既记录栈中元素的个数 又指向栈顶上面一个位置
		for (int i = 0; i < n; i++) {
			char ch = str[i];// 遍历字符串
			if (ch == '(' || ch == '[' || ch == '{') {// 当前字符是左括号
				stack[size++] = ch == '(' ? ')' : (ch == '[' ? ']' : '}');// 将对应的右括号压入栈
			} else {// 当前字符是右括号
				if (size == 0) {// 栈中已没有可以匹配的括号
					return false;
				}
				char top = stack[--size];// size-1为栈顶元素 将其弹出 和当前字符进行对比
				if (top != ch) {
					return false;
				}
			}
		}
		return size == 0;// 判断是否还有剩余的左括号没有匹配
	}

}