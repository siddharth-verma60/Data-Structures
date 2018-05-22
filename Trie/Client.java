package Trie;

import java.util.HashMap;

public class Client {

	public static void main(String[] args) {
		// System.out.println(BruteForcePatternSearch("startsde", "arts"));
		System.out.println(boyerMoorePatternSearch("startsde", "arts"));
	}

	public static int BruteForcePatternSearch(String source, String pattern) {
		int retval = -1;
		for (int i = 0; i <= source.length() - pattern.length(); i++) {
			int j;
			for (j = 0; j < pattern.length(); j++) {
				if (source.charAt(i + j) != pattern.charAt(j)) {
					break;
				}
			}
			if (j == pattern.length()) {
				retval = i;
				break;
			}
		}
		return retval;
	}

	public static int boyerMoorePatternSearch(String source, String pattern) {
		int retval = -1;
		HashMap<Character, Integer> patternMap = new HashMap<>();

		for (int i = 0; i < pattern.length(); i++) {
			patternMap.put(pattern.charAt(i), i);
		}

		for (int i = 0; i <= source.length() - pattern.length();) {
			int j;
			for (j = pattern.length() - 1; j >= 0; --j) {
				if (source.charAt(i + j) != pattern.charAt(j)) {
					char Mismatch = source.charAt(i + j);

					if (!patternMap.containsKey(Mismatch)) {
						i += j + 1;
					} else {
						int psi = patternMap.get(Mismatch);
						if (psi < j) {
							i += j - psi;

						} else {
							i += 1;

						}
						break;
					}
				}

			}
			if (j == -1) {
				retval = i;
				break;
			}
		}
		return retval;
	}
}
