package class27;

import java.util.HashMap;

public class Problem_0001_TwoSum {

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

}