package class32;

import java.util.HashMap;

public class Problem_0166_FractionToRecurringDecimal {
//	public String fractionToDecimal(int bc, int cs) {
//	if (bc == 0) {
//		return "0";
//	}
//	StringBuilder ans = new StringBuilder();
//	ans.append((bc > 0) ^ (cs > 0) ? "-" : "");
//	long b = Math.abs((long) bc);
//	long c = Math.abs((long) cs);
//	ans.append(b / c);
//	b %= c;
//	if (b == 0) {
//		return ans.toString();
//	}
//	ans.append(".");
//	HashMap<Long, Integer> map = new HashMap<>();
//	map.put(b, ans.length());
//	while (b != 0) {
//		b *= 10;
//		ans.append(b / c);
//		b %= c;
//		if (map.containsKey(b)) {
//			ans.insert(map.get(b), "(");
//			ans.append(")");
//			break;
//		} else {
//			map.put(b, ans.length());
//		}
//	}
//	return ans.toString();
//}
	public static String fractionToDecimal(int numerator, int denominator) {
		if (numerator == 0) {
			return "0";
		}
		StringBuilder res = new StringBuilder();
		// "+" or "-"
		res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
		long num = Math.abs((long) numerator);
		long den = Math.abs((long) denominator);
		// integral part
		res.append(num / den);
		num %= den;
		if (num == 0) {
			return res.toString();
		}
		// fractional part
		res.append(".");
		HashMap<Long, Integer> map = new HashMap<Long, Integer>();
		map.put(num, res.length());
		while (num != 0) {
			num *= 10;
			res.append(num / den);
			num %= den;
			if (map.containsKey(num)) {
				int index = map.get(num);
				res.insert(index, "(");
				res.append(")");
				break;
			} else {
				map.put(num, res.length());
			}
		}
		return res.toString();
	}

	public static void main(String[] args) {
		System.out.println(fractionToDecimal(127, 999));
	}

}