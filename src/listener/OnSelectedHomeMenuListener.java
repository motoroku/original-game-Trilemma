package listener;

import java.util.EventListener;

public interface OnSelectedHomeMenuListener extends EventListener {
	void onSelectedStory();

	void onSelectedAdventure();

	void onSelectedCustomize();

	void onSelectedShop();

	void onSelectedSetting();
}
