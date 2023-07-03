package class22;
//lc956
import java.util.HashMap;

// 本题测试链接 : https://leetcode.com/problems/tallest-billboard/
public class Code05_TallestBillboard {
//	public int tallestBillboard(int[] m) {
//	HashMap<Integer, Integer> map = new HashMap<>();//记录结果的map  k为差  v为累加和最大的一对的较小的值
//	HashMap<Integer, Integer> cur = new HashMap<>();//复制上一步的map的信息 用于对map的更新
//	map.put(0, 0);
//	for (int num : m) {//遍历数组
//		cur = new HashMap<>(map);
//		for (int dif : cur.keySet()) {//遍历所有已有的记录
//			int v = cur.get(dif);//记录中的数为v和v+dif
//			map.put(dif + num, Math.max(v, map.getOrDefault(num + dif, 0)));//把num放入较大的 差变为dif+num  较小的仍是v  如果已经有以dif+num为k的记录 比较并存入较大的v
//			int oldv = map.getOrDefault(Math.abs(num - dif), 0);//把num放入较小的 差值k缩小为abs(num - dif)  该k如果原来就存在 对应的v记为oldv
//			if (dif > num) {//放入后v+num未超过较大值v+dif
//				map.put(dif - num, Math.max(oldv, v + num));//新的较小值v+num与原较小值取较大的
//			} else {//放入后v+num超过较大值v+dif
//				map.put(num - dif, Math.max(oldv, v + dif));//新的较小值v+dif与原较小值取较大的
//			}
//		}
//	}
//	return map.get(0);
//}
	public int tallestBillboard(int[] rods) {
		// key 集合对的某个差
		// value 满足差值为key的集合对中，最好的一对，较小集合的累加和
		// 较大 -> value + key
		HashMap<Integer, Integer> dp = new HashMap<>(), cur;
		dp.put(0, 0);// 空集 和 空集
		for (int num : rods) {
			if (num != 0) {
				// cur 内部数据完全和dp一样
				cur = new HashMap<>(dp); // 考虑x之前的集合差值状况拷贝下来
				for (int d : cur.keySet()) {
					int diffMore = cur.get(d); // 最好的一对，较小集合的累加和
					// x决定放入，比较大的那个
					dp.put(d + num, Math.max(diffMore, dp.getOrDefault(num + d, 0)));
					// x决定放入，比较小的那个
					// 新的差值 Math.abs(x - d)
					// 之前差值为Math.abs(x - d)，的那一对，就要和这一对，决策一下
					// 之前那一对，较小集合的累加和diffXD
					int diffXD = dp.getOrDefault(Math.abs(num - d), 0);
					if (d >= num) { // x决定放入比较小的那个, 但是放入之后，没有超过这一对较大的那个
						dp.put(d - num, Math.max(diffMore + num, diffXD));
					} else { // x决定放入比较小的那个, 但是放入之后，没有超过这一对较大的那个
						dp.put(num - d, Math.max(diffMore + d, diffXD));
					}
				}
			}
		}
		return dp.get(0);
	}

}