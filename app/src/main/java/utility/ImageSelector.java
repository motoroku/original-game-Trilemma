package utility;

import com.games.Trilemma.R;

public class ImageSelector {

	public static int getPeopleImage(int imageId) {
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

	public static int getEnemyImage(int imageId) {
		int result = R.drawable.ch_message_03_noimage;

		switch (imageId) {
			case 1:
				result = R.drawable.mon_001;
				break;
			case 2:
				result = R.drawable.mon_050;
				break;
			case 3:
				result = R.drawable.mon_022;
				break;
			case 4:
				result = R.drawable.mon_275;
				break;
			case 5:
				result = R.drawable.mon_046;
				break;
			case 6:
				result = R.drawable.mon_013;
				break;
		}

		return result;
	}

	public static int getBackGround(int imageId) {
		int result = R.drawable.bk_forest_01;

		switch (imageId) {
			case 1:
				result = R.drawable.bk_underground_01;
				break;
			case 2:
				result = R.drawable.bk_field_01;
				break;
			case 3:
				result = R.drawable.bk_dungeon_01;
				break;
			case 4:
				result = R.drawable.bk_mountain_01;
				break;
		}

		return result;
	}

}
