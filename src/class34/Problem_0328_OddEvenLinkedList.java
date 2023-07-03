package class34;

public class Problem_0328_OddEvenLinkedList {
//	public ListNode oddEvenList(ListNode head) {
//	if (head == null) {
//		return null;
//	}
//	int n = 1;
//	ListNode cur = head;
//	if (head.next == null) {
//		return head;
//	}
//	ListNode second = head.next;
//	while (cur.next != null) {
//		cur = cur.next;
//		n++;
//	}
//	n = n % 2 == 0 ? n / 2 : n / 2 + 1;
//	int i = 1;
//	cur = head;
//	ListNode next = cur.next;
//	while (i < n) {
//		cur.next = next.next;
//		cur = cur.next;
//		next.next = cur.next;
//		next = cur.next;
//		i++;
//	}
//	cur.next = second;
//	return head;
//}
	
	// 提交时不要提交这个类
	public static class ListNode {
		int val;
		ListNode next;
	}

	public ListNode oddEvenList(ListNode head) {
		ListNode firstOdd = null;
		ListNode firstEven = null;
		ListNode odd = null;
		ListNode even = null;
		ListNode next = null;
		int count = 1;
		while (head != null) {
			next = head.next;
			head.next = null;
			if ((count & 1) == 1) {
				firstOdd = firstOdd == null ? head : firstOdd;
				if (odd != null) {
					odd.next = head;
				}
				odd = head;
			} else {
				firstEven = firstEven == null ? head : firstEven;
				if (even != null) {
					even.next = head;
				}
				even = head;
			}
			count++;
			head = next;
		}
		if (odd != null) {
			odd.next = firstEven;
		}
		return firstOdd != null ? firstOdd : firstEven;
	}

}