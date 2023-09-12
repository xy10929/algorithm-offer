package class29;

public class Problem_0066_PlusOne {

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

	public static int[] plusOne1(int[] digits) {
		int n = digits.length;
		for (int i = n - 1; i >= 0; i--) {
			if (digits[i] < 9) {
				digits[i]++;
				return digits;
			}
			digits[i] = 0;
		}
		int[] ans = new int[n + 1];
		ans[0] = 1;
		return ans;
	}

}