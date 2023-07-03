package class28;

public class Problem_0019_RemoveNthNodeFromEndofList {
	
//    public ListNode removeNthFromEnd(ListNode head, int n) {
//        ListNode start = new ListNode(0);
//        start.next = head;
//        ListNode fast = start;
//        ListNode slow = start;
//        for(int i = 0; i <= n; i++){
//            fast = fast.next;
//        }
//        while(fast != null){
//            fast = fast.next;
//            slow = slow.next;
//        }
//        slow.next = slow.next.next;
//        return start.next;
//    }

	public static class ListNode {
		public int val;
		public ListNode next;
	}

	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode cur = head;
		ListNode pre = null;
		while (cur != null) {
			n--;
			if (n == -1) {
				pre = head;
			}
			if (n < -1) {
				pre = pre.next;
			}
			cur = cur.next;
		}
		if (n > 0) {
			return head;
		}
		if (pre == null) {
			return head.next;
		}
		pre.next = pre.next.next;
		return head;
	}

}