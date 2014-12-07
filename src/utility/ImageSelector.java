package utility;

import com.games.Trilemma.R;

public class ImageSelector {

	public static int getImage(int imageId) {
		int result = R.drawable.ch_message_03_noimage;

		switch (imageId) {
			case 1:
				result = R.drawable.ch_boy_02;
				break;
			case 2:
				result = R.drawable.ch_people_01;
				break;
			case 3:
				result = R.drawable.ch_hero_01;
				break;
			case 4:
				result = R.drawable.ch_future_01;
				break;
			case 5:
				result = R.drawable.ch_magician_01;
				break;
			case 6:
				result = R.drawable.ch_mafia_01;
				break;
			case 7:
				result = R.drawable.ch_muslim_01;
				break;
			case 8:
				result = R.drawable.ch_maid_01;
				break;
			case 9:
				result = R.drawable.ch_samurai_01;
				break;
			case 10:
				result = R.drawable.ch_people_04;
				break;
		}

		return result;
	}
}
