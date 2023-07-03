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

	public static int removeDuplicates(int[] nums) {
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