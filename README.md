# algorithm-offer

Leetcode problems in data stucture & algorithm course by [Chengyun Zuo](https://github.com/algorithmzuo/coding-for-great-offer)

- [class27](#class27)
  - [lc1 求数组中和为 target 两数的位置](#lc1)
  - [lc7](#lc7)
- [class28](#class28)
  - [lc14 求所有字符串的最长公共前缀](#lc14)
  - [lc19 删去链表倒数第 n 个节点](#lc19)
  - [lc26 把升序数组去重 并返回数字种类数](#lc26)
  - [lc36 9\*9 的矩阵表示 9 个 3\*3 数独 判断已填的数是否使整体还有效](#lc36)
- [class29](#class29)
  - [lc66 数组表示一个数 返回其+1 后的对应数组](#lc66)
  - [lc69 求一个数开方后的结果的整数部分](#lc69)
  - [lc73 把矩阵中 0 所在的行和列都归零](#lc73)
- [class30](#class30)
  - [lc79 求从矩阵任意位置开始上下左右不重复地走 能否形成目标字符串](#lc79)
  - [lc88 把一个有序数组 merge 进另一个](#lc88)
  - [lc108 根据有序数组建立 BST](#lc108)

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

### lc26

@把升序数组去重 并返回数字种类数

用 cur 指针指向要查看的位置 用 f 指针指向有效区的末尾  
每次通过比较两指针内容决定是否扩充有效区 直至 cur 到末尾

```java
public int removeDuplicates(int[] arr) {
  int f = 0;// 有效区末尾
  int cur = 0;// 当前位置
  while (cur < arr.length) {
    if (arr[f] == arr[cur]) {// 当前数已在有效区存在 有效区不扩充
      cur++;
    } else {// 扩充有效区
      f++;
      arr[f] = arr[cur];
      cur++;
    }
  }
  return f + 1;// 有效区大小
}
```

### lc36

@9\*9 的矩阵表示 9 个 3\*3 数独 判断已填的数是否使整体还有效

遍历整个矩阵 对于每个已经填数的位置:  
用 3 个 boolean 二维数组分别记录 9 个行/列/子数独中各个数字是否已经存在 如果存在则返回无效 否则把该存在的信息记入三个数组

```java
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
```

## class29

### lc66

@数组表示一个数 返回其+1 后的对应数组

从低位开始把数+1, 如果未进位则直接返回 如果最后也未返回说明原数每一位都是 9

```java
public int[] plusOne(int[] arr) {
  int n = arr.length;
  for (int i = n - 1; i >= 0; i--) {
    if (arr[i] < 9) {
      arr[i]++;
      return arr;
    }
    arr[i] = 0;
  }
  // 仍未return说明原数每一位都是9
  int[] ans = new int[n + 1];
  ans[0] = 1;
  return ans;
}
```

### lc69

@求一个数 x 开方后的结果的整数部分

在 1 到 x 间用二分法求结果  
计算平方时可能会对于 int 越界 所以使用 long

```java
public int mySqrt(int x) {
  if (x == 0) {
    return 0;
  }
  long ans = 0;
  long start = 1;
  long end = x;
  long m = 0;
  while (start <= end) {// 二分范围上有数
    m = (start + end) / 2;
    if (m * m <= x) {// m^2不超过x, 先设为ans, 尝试更大的结果
      ans = m;
      start = m + 1;
    } else {
      end = m - 1;
    }
  }
  return (int) ans;
}
```

### lc73

@把矩阵中 0 所在的行和列都归零

准备两个 boolean 数组 记录各行/列是否应该归零  
遍历矩阵 遇到 0 时在两个数组中记录对应的行&列应该归零  
再次遍历矩阵 根据两个数组的记录进行归零

```java
public void setZeroes(int[][] matrix) {
  int n = matrix.length;
  int m = matrix[0].length;
  boolean[] row = new boolean[n];
  boolean[] col = new boolean[m];
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      if (matrix[i][j] == 0) {// 遇到0, 记录对应的行列应该归零
        row[i] = true;
        col[j] = true;
      }
    }
  }
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      if (row[i] || col[j]) {// 记录中的行列归零
        matrix[i][j] = 0;
      }
    }
  }
}
```

## class30

### lc79

@求从矩阵任意位置开始上下左右不重复地走 能否形成目标字符串

创建递归函数 返回从矩阵某位置开始 能否走出目标字符串某位置开始的内容  
遍历矩阵所以位置 代入递归函数验证

```java
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
```

### lc88

@把一个有序数组 merge 进另一个 其已经有它们的总长度 后面的空位为 0

准备两个指针指向有效数字的结尾 从后往前填入长数组

```java
public void merge(int[] arr1, int m, int[] arr2, int n) {
  int p1 = m - 1;
  int p2 = n - 1;
  int i = m + n - 1;
  while (p1 >= 0 && p2 >= 0) {// 两数组的数都未用尽
    if (arr1[p1] >= arr2[p2]) {
      arr1[i--] = arr1[p1--];
    } else {
      arr1[i--] = arr2[p2--];
    }
  }
  // 其中一个数组的数已用尽
  while (p1 >= 0) {
    arr1[i--] = arr1[p1--];
  }
  while (p2 >= 0) {
    arr1[i--] = arr2[p2--];
  }
}
```

### lc108

@根据有序数组建立 BST

建立递归函数生成 BST, 数组坐标范围作为参数

```java
public TreeNode sortedArrayToBST(int[] arr) {
  return f(arr, 0, arr.length - 1);
}

public TreeNode f(int[] arr, int l, int r) {
  if (l > r) {
    return null;
  }
  if (l == r) {
    return new TreeNode(arr[l]);
  }
  int m = (l + r) / 2;
  TreeNode head = new TreeNode(arr[m]);
  head.left = f(arr, l, m - 1);
  head.right = f(arr, m + 1, r);
  return head;
}
```
