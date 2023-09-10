# algorithm-offer

Leetcode problems in data stucture & algorithm course by [Chengyun Zuo](https://github.com/algorithmzuo/coding-for-great-offer)

- [class27](#class27)
  - [lc1 求数组中和为 target 两数的位置](#lc1)
  - [lc7](#lc7)
- [class28](#class28)
  - [lc14 求所有字符串的最长公共前缀](#lc14)
  - [lc19 删去链表倒数第 n 个节点](#lc19)

## class27

### lc1

@求数组中和为 target 两数的位置

遍历数组 用 hashmap 记录 k 为值 v 为位置  
每次记录之前 检查 target-v 是否作为 map 的 key 已存在

```java
public int[] twoSum(int[] arr, int target) {
  HashMap<Integer, Integer> map = new HashMap<>();
  for (int i = 0; i < arr.length; i++) {
    if (map.containsKey(target - arr[i])) {// 记录之前检查target-当前值是否已经作为key存在 是则找到了一组结果
      return new int[] { i, map.get(target - arr[i]) };
    }
    map.put(arr[i], i);// 记录值和位置
  }
  return new int[] {};
}
```

## class28

### lc14

@求所有字符串的最长公共前缀

每个字符串依次与首个字符串相比 求最长公共前缀长度 对每次的结果取 min

```java
public String longestCommonPrefix(String[] strs) {
  char[] chs = strs[0].toCharArray();// 首个字符串
  int ans = chs.length;
  for (String str : strs) {
    char[] cur = str.toCharArray();// 当前字符串
    int index = 0;
    while (index < chs.length && index < cur.length) {
      if (chs[index] != cur[index]) {
        break;
      }
      index++;
    }
    ans = Math.min(ans, index);
  }
  return strs[0].substring(0, ans);
}
```

### lc19

@删去链表倒数第 n 个节点

fast 先从 head 开始走 n 步 如果 fast 为 null 说明需删 head  
否则 slow 指向 head, 两指针同时移动直至 fast 指向末尾节点, 删去 slow 的下一个节点

```java
public ListNode removeNthFromEnd(ListNode head, int n) {
  ListNode fast = head;
  for (int i = 0; i < n; i++) {// fast走n步
    fast = fast.next;
  }
  if (fast == null) {// 需要删去head
    return head.next;
  }
  ListNode slow = head;
  while (fast.next != null) {// fast继续走到末尾节点
    slow = slow.next;
    fast = fast.next;
  }
  slow.next = slow.next.next;// 删去slow的下一个节点
  return head;
}
```
