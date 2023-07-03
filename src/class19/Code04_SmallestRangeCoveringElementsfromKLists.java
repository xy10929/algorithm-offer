package class19;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

// 本题测试链接 : https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
public class Code04_SmallestRangeCoveringElementsfromKLists {
//	public int[] smallestRange(List<List<Integer>> nums) {
//	int n = nums.size();
//	TreeSet<node> order = new TreeSet<>(new nodecomparator());
//	for (int i = 0; i < n; i++) {
//		order.add(new node(nums.get(i).get(0), i, 0));
//	}
//	int a = 0;
//	int b = 0;
//	boolean setpresent = false;
//	while (order.size() == n) {
//		node min = order.first();
//		node max = order.last();
//		if (!setpresent || max.val - min.val < b - a) {
//			setpresent = true;
//			a = min.val;
//			b = max.val;
//		}
//		min = order.pollFirst();
//		int arr = min.arr;
//		int i = min.i + 1;
//		if (i < nums.get(arr).size()) {
//			order.add(new node(nums.get(arr).get(i), arr, i));
//		}
//	}
//	return new int[] { a, b };
//}
//
//public class node {
//	public int val;
//	public int arr;
//	public int i;
//
//	public node(int val, int arr, int i) {
//		this.val = val;
//		this.arr = arr;
//		this.i = i;
//	}
//}
//
//public class nodecomparator implements Comparator<node> {
//	public int compare(node n1, node n2) {
//		return n1.val != n2.val ? n1.val - n2.val : n1.arr - n2.arr;
//	}
//}
	public static class Node {
		public int value;
		public int arrid;
		public int index;

		public Node(int v, int ai, int i) {
			value = v;
			arrid = ai;
			index = i;
		}
	}

	public static class NodeComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o1.value != o2.value ? o1.value - o2.value : o1.arrid - o2.arrid;
		}

	}

	public static int[] smallestRange(List<List<Integer>> nums) {
		int N = nums.size();
		TreeSet<Node> orderSet = new TreeSet<>(new NodeComparator());
		for (int i = 0; i < N; i++) {
			orderSet.add(new Node(nums.get(i).get(0), i, 0));
		}
		boolean set = false;
		int a = 0;
		int b = 0;
		while (orderSet.size() == N) {
			Node min = orderSet.first();
			Node max = orderSet.last();
			if (!set || (max.value - min.value < b - a)) {
				set = true;
				a = min.value;
				b = max.value;
			}
			min = orderSet.pollFirst();
			int arrid = min.arrid;
			int index = min.index + 1;
			if (index != nums.get(arrid).size()) {
				orderSet.add(new Node(nums.get(arrid).get(index), arrid, index));
			}
		}
		return new int[] { a, b };
	}

}