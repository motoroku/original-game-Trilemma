package activities;

import listener.OnSelectedHomeMenuListener;

import com.games.Trilemma.R;

import activities.fragment.BattleFragment;
import activities.fragment.AdventureFragment;
import activities.fragment.CustomizeFragment;
import activities.fragment.HomeFragment;
import activities.fragment.SettingFragment;
import activities.fragment.ShopFragment;
import activities.fragment.StartFragment;
import activities.fragment.BattleFragment.OnBattleEndListener;
import activities.fragment.AdventureFragment.OnBattleStartListener;
import activities.fragment.StartFragment.OnGameStartListener;
import activities.fragment.StoryFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends FragmentActivity implements OnGameStartListener, OnSelectedHomeMenuListener, OnBattleStartListener, OnBattleEndListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		StartFragment mStartFragment = new StartFragment();
		// set listener
		mStartFragment.setOnGameStartListener(this);
		// set fragment
		ft.add(R.id.MainActivity_frame, mStartFragment);
		ft.commit();
	}

	@Override
	public void onGameStart() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		HomeFragment mHomeFragment = new HomeFragment();
		// set listener
		mHomeFragment.setOnHomeMenuListener(this);
		// set fragment
		ft.replace(R.id.MainActivity_frame, mHomeFragment);
		ft.commit();
	}

	@Override
	public void onSelectedStory() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		StoryFragment mStoryFragment = new StoryFragment();
		// remove view
		LinearLayout main = (LinearLayout) this.findViewById(R.id.HomeFragment_main);
		main.removeAllViews();
		// set fragment
		ft.add(R.id.HomeFragment_main, mStoryFragment);
		ft.commit();
	}

	@Override
	public void onSelectedAdventure() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		AdventureFragment mDungeonFragment = new AdventureFragment();
		// set listener
		mDungeonFragment.setOnBattleStartListener(this);
		// remove view
		LinearLayout main = (LinearLayout) this.findViewById(R.id.HomeFragment_main);
		main.removeAllViews();
		// set fragment
		ft.add(R.id.HomeFragment_main, mDungeonFragment);
		ft.commit();
	}

	@Override
	public void onSelectedCustomize() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		CustomizeFragment mCustomizeFragment = new CustomizeFragment();
		// remove view
		LinearLayout main = (LinearLayout) this.findViewById(R.id.HomeFragment_main);
		main.removeAllViews();
		// set fragment
		ft.add(R.id.HomeFragment_main, mCustomizeFragment);
		ft.commit();
	}

	@Override
	public void onSelectedShop() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ShopFragment mShopFragment = new ShopFragment();
		// remove view
		LinearLayout main = (LinearLayout) this.findViewById(R.id.HomeFragment_main);
		main.removeAllViews();
		// set fragment
		ft.add(R.id.HomeFragment_main, mShopFragment);
		ft.commit();
	}

	@Override
	public void onSelectedSetting() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		SettingFragment mSettingFragment = new SettingFragment();
		// remove view
		LinearLayout main = (LinearLayout) this.findViewById(R.id.HomeFragment_main);
		main.removeAllViews();
		// set fragment
		ft.add(R.id.HomeFragment_main, mSettingFragment);
		ft.commit();
	}

	@Override
	public void onBattleStart() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		BattleFragment mBattleFragment = new BattleFragment();
		mBattleFragment.setOnBattleEndListener(this);
		ft.replace(R.id.MainActivity_frame, mBattleFragment);
		ft.commit();
	}

	@Override
	public void onBattleEnd() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		HomeFragment mHomeFragment = new HomeFragment();
		mHomeFragment.setOnHomeMenuListener(this);
		ft.replace(R.id.MainActivity_frame, mHomeFragment);
		ft.commit();
	}

}
