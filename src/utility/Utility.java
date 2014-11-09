package utility;

import java.util.Random;

public class Utility {

	/**
	 * 最も大きい数字を返します
	 * @param a 数値
	 * @param b 数値
	 * @param c 数値
	 * @return 引数の数値の中で最も大きい数値
	 */
	public static int getHighestNum(int a, int b, int c) {
		int result = 0;

		if (a > b && a > c) {
			result = a;
		} else if (b > a && b > c) {
			result = b;
		} else {
			result = c;
		}
		return result;
	}

	/**
	 * 乱数を返します。
	 * @param max 生成する乱数の最大値
	 * @param isZero ゼロを含めるか否か。true 含める false 含めない
	 * @return
	 */
	public static int getRandomNum(int max, boolean isZero) {
		Random random = new Random();
		int num;
		if (isZero) {
			max = max + 1;
			num = random.nextInt(max);
		} else {
			num = random.nextInt(max) + 1;
		}
		return num;
	}
}
