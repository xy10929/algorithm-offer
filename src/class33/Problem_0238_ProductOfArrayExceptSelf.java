package class33;

public class Problem_0238_ProductOfArrayExceptSelf {
	public int[] productExceptSelf(int[] arr) {
		int n = arr.length;
		int zeronum = 0;
		int all = 1;
		for (int i = 0; i < n; i++) {
			if (arr[i] == 0) {
				zeronum++;
			} else {
				all *= arr[i];
			}
		}
		for (int i = 0; i < n; i++) {
			if (zeronum >= 2) {
				arr[i] = 0;
			} else if (zeronum == 1) {
				if (arr[i] == 0) {
					arr[i] = all;
				} else {
					arr[i] = 0;
				}
			} else {
				arr[i] = div(all, arr[i]);
			}
		}
		return arr;
	}

	public int div(int a, int b) {
		int x = Math.abs(a);
		int y = Math.abs(b);
		boolean pos = (a > 0 && b > 0) || (a < 0 && b < 0);
		int ans = 0;
		for (int i = 30; i >= 0; i--) {
			if ((x >> i) >= y) {
				ans |= (1 << i);
				x -= (y << i);
			}
		}
		return pos ? ans : -ans;
	}
}
