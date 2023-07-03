package class07;
//lc164
// 测试链接 : https://leetcode.com/problems/maximum-gap/
public class Code03_MaxGap {
//
//    public int maximumGap(int[] arr) {
//        int n = arr.length;
//        if(n == 1){
//            return 0;
//        }
//        int min = Integer.MAX_VALUE;
//        int max = Integer.MIN_VALUE;
//        for(int i = 0; i < n; i++){
//            max = Math.max(max, arr[i]);
//            min = Math.min(min, arr[i]);
//        }
//        if(max == min){
//            return 0;
//        }
//        boolean[] hasnum = new boolean[n + 1];
//        int[] maxnum = new int[n + 1];
//        int[] minnum = new int[n + 1];
//        for(int i = 0; i < n; i++){
//            int bid = bucket(arr[i], n, min, max);
//            minnum[bid] = hasnum[bid] ? Math.min(minnum[bid], arr[i]) : arr[i];
//            maxnum[bid] = hasnum[bid] ? Math.max(maxnum[bid], arr[i]) : arr[i];
//            hasnum[bid] = true;
//        }
//        int ans = 0;
//        int lastmax = maxnum[0];
//        for(int i = 1; i <= n; i++){
//            if(hasnum[i]){
//                ans = Math.max(ans, minnum[i] - lastmax);
//                lastmax = maxnum[i];
//            }
//        }
//        return ans;
//    }
//    
//    public int bucket(long num, long n, long min, long max){
//        if(num == max){
//            return (int)n;
//        }
//        return (int)((num - min) * (n + 1) / (max - min));
////         每个桶左闭右开 最后一个桶左闭右闭
////         (max-min)/(n+1) -> 每一份的大小
////         (num-min)/每一份的大小 -> num之前有几个桶 即为num在数组中对应的桶号
//    }
	public static int maximumGap(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		int len = nums.length;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < len; i++) {
			min = Math.min(min, nums[i]);
			max = Math.max(max, nums[i]);
		}
		if (min == max) {
			return 0;
		}
		boolean[] hasNum = new boolean[len + 1];
		int[] maxs = new int[len + 1];
		int[] mins = new int[len + 1];
		int bid = 0;
		for (int i = 0; i < len; i++) {
			bid = bucket(nums[i], len, min, max);
			mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
			maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
			hasNum[bid] = true;
		}
		int res = 0;
		int lastMax = maxs[0];
		int i = 1;
		for (; i <= len; i++) {
			if (hasNum[i]) {
				res = Math.max(res, mins[i] - lastMax);
				lastMax = maxs[i];
			}
		}
		return res;
	}

	public static int bucket(long num, long len, long min, long max) {
		return (int) ((num - min) * len / (max - min));
	}

}