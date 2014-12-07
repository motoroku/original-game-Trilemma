package listener;

import java.util.EventListener;

public interface OnSelectedHomeMenuListener extends EventListener {
	void onSelectedStoryList();

	void onSelectedAdventure();

	void onSelectedCustomize();

	void onSelectedShop();

	void onSelectedSetting();
}
