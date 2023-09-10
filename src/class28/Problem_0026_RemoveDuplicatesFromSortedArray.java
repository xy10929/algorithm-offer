package class28;

//class Solution {
//    public int removeDuplicates(int[] arr) {
//        int f = 0;
//        int c = 0;
//        while(c < arr.length){
//            if(arr[f] == arr[c]){
//                c++;
//            }else{
//                arr[++f] = arr[c];
//                c++;
//            }
//        }
//        return f + 1;
//    }
//}

public class Problem_0026_RemoveDuplicatesFromSortedArray {

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

	public static int removeDuplicates1(int[] nums) {
		if (nums == null) {
			return 0;
		}
		if (nums.length < 2) {
			return nums.length;
		}
		int done = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[done]) {
				nums[++done] = nums[i];
			}
		}
		return done + 1;
	}

}