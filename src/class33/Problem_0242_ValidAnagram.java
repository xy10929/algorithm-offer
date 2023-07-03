package class33;

public class Problem_0242_ValidAnagram {
//	public boolean isAnagram(String S, String T) {
//	if (S.length() != T.length()) {
//		return false;
//	}
//	int[] l = new int[26];
//	char[] s = S.toCharArray();
//	char[] t = T.toCharArray();
//	for (int i = 0; i < s.length; i++) {
//		l[s[i] - 'a']++;
//	}
//	for (int i = 0; i < t.length; i++) {
//		if (--l[t[i] - 'a'] < 0)
//			return false;
//	}
//	return true;
//}
	public static boolean isAnagram(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		char[] str1 = s.toCharArray();
		char[] str2 = t.toCharArray();
		int[] count = new int[256];
		for (char cha : str1) {
			count[cha]++;
		}
		for (char cha : str2) {
			if (--count[cha] < 0) {
				return false;
			}
		}
		return true;
	}

}