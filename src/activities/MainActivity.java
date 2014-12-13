package activities;

import com.games.Trilemma.R;

import dao.DaoManager;

import Trilemma.DUNGEON;
import Trilemma.PEOPLE;
import Trilemma.TOWN;
import activities.fragment.BattleFragment;
import activities.fragment.DungeonFragment;
import activities.fragment.CustomizeFragment;
import activities.fragment.HomeFragment;
import activities.fragment.HomeFragment.OnSelectedHomeMenuListener;
import activities.fragment.PeopleListFragment;
import activities.fragment.PeopleListFragment.OnSelectedPeopleListener;
import activities.fragment.SettingFragment;
import activities.fragment.ShopFragment;
import activities.fragment.StartFragment;
import activities.fragment.PeopleFragment;
import activities.fragment.BattleFragment.OnBattleEndListener;
import activities.fragment.DungeonFragment.OnBattleStartListener;
import activities.fragment.StartFragment.OnGameStartListener;
import activities.fragment.StoryListFragment;
import activities.fragment.StoryListFragment.OnSelectedStoryListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends FragmentActivity implements OnGameStartListener, OnSelectedHomeMenuListener, OnBattleStartListener,
		OnBattleEndListener, OnSelectedStoryListener, OnSelectedPeopleListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		DaoManager daoManager = new DaoManager(this);

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
	public void onStartGame() {
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
	public void onSelectedStoryList() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		StoryListFragment mStoryListFragment = new StoryListFragment();
		// set listener
		mStoryListFragment.setOnStoryListener(this);
		// remove view
		LinearLayout main = (LinearLayout) this.findViewById(R.id.HomeFragment_main);
		main.removeAllViews();
		// set fragment
		ft.add(R.id.HomeFragment_main, mStoryListFragment);
		ft.commit();
	}

	@Override
	public void onSelectedAdventure() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		DungeonFragment mDungeonFragment = new DungeonFragment();
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
	public void onStartBattle(DUNGEON dungeon) {
		// TODO Auto-generated method stub
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		BattleFragment mBattleFragment = new BattleFragment();
		// set bundles
		Bundle bundle = new Bundle();
		bundle.putLong("id", dungeon.getId());
		bundle.putString("name", dungeon.getDungeon_name());
		mBattleFragment.setArguments(bundle);
		// set listener
		mBattleFragment.setOnBattleEndListener(this);
		ft.replace(R.id.HomeFragment_main, mBattleFragment);
		ft.commit();
	}

	@Override
	public void onEndBattle() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		HomeFragment mHomeFragment = new HomeFragment();
		mHomeFragment.setOnHomeMenuListener(this);
		ft.replace(R.id.MainActivity_frame, mHomeFragment);
		ft.commit();
	}

	@Override
	public void OnSelectedTown(TOWN town) {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		PeopleListFragment mPeopleListFragment = new PeopleListFragment();
		// set bundles
		Bundle bundle = new Bundle();
		bundle.putLong("id", town.getId());
		bundle.putString("name", town.getTown_name());
		mPeopleListFragment.setArguments(bundle);
		// set listener
		mPeopleListFragment.setOnSelectedPeopleListener(this);
		// remove view
		LinearLayout main = (LinearLayout) this.findViewById(R.id.HomeFragment_main);
		main.removeAllViews();
		// set fragment
		ft.add(R.id.HomeFragment_main, mPeopleListFragment);
		ft.commit();
	}

	@Override
	public void OnSelectedPeople(PEOPLE people) {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		PeopleFragment mPeopleFragment = new PeopleFragment();
		// set bundles
		Bundle bundle = new Bundle();
		bundle.putLong("id", people.getId());
		bundle.putInt("image_no", people.getImgae_no());
		bundle.putString("name", people.getPeople_name());
		bundle.putString("serif", people.getSerif());
		mPeopleFragment.setArguments(bundle);
		// remove view
		LinearLayout main = (LinearLayout) this.findViewById(R.id.HomeFragment_main);
		main.removeAllViews();
		// set fragment
		ft.add(R.id.HomeFragment_main, mPeopleFragment);
		ft.commit();
	}

}
