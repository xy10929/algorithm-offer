package class38;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Problem_0739_DailyTemperatures {
//	public int[] dailyTemperatures(int[] arr) {
//	int n = arr.length;
//	Stack<ArrayList<Integer>> stack = new Stack<>();//存放相同val的位置
//	int[] ans = new int[n];
//	for (int i = 0; i < n; i++) {
//		while (!stack.isEmpty() && arr[stack.peek().get(0)] < arr[i]) {//不能直接进入栈时 不断弹出栈顶并生成相应信息 直至可以进入栈
//			ArrayList<Integer> indexes = stack.pop();
//			for (int popi : indexes) {//所有popi的右边最近的更大的位置都是i
//				ans[popi] = i - popi;
//			}
//		}
//		if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {//相等则放入栈顶列表的末尾
//			stack.peek().add(i);
//		} else {//复活栈由大到小 可以直接进入栈
//			ArrayList<Integer> list = new ArrayList<>();
//			list.add(i);
//			stack.add(list);
//		}
//	}
//	return ans;
//}
	public static int[] dailyTemperatures(int[] arr) {
		if (arr == null || arr.length == 0) {
			return new int[0];
		}
		int N = arr.length;
		int[] ans = new int[N];
		Stack<List<Integer>> stack = new Stack<>();
		for (int i = 0; i < N; i++) {
			while (!stack.isEmpty() && arr[stack.peek().get(0)] < arr[i]) {
				List<Integer> popIs = stack.pop();
				for (Integer popi : popIs) {
					ans[popi] = i - popi;
				}
			}
			if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
				stack.peek().add(Integer.valueOf(i));
			} else {
				ArrayList<Integer> list = new ArrayList<>();
				list.add(i);
				stack.push(list);
			}
		}
		return ans;
	}

}