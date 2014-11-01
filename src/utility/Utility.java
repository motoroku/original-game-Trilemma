package utility;

import java.util.Random;

public class Utility {

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
	 * ������Ԃ��܂��B
	 * @param max �������闐���̍ő�l
	 * @param isZero �[�����܂߂邩�ۂ��Btrue �܂߂� false �܂߂Ȃ�
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
