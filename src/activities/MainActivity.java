package activities;

import com.games.Trilemma.R;

import activities.fragment.BattleFragment;
import activities.fragment.DungeonFragment;
import activities.fragment.HomeFragment;
import activities.fragment.StartFragment;
import activities.fragment.BattleFragment.OnBattleEndListener;
import activities.fragment.DungeonFragment.OnBattleStartListener;
import activities.fragment.HomeFragment.OnQuestStartListener;
import activities.fragment.StartFragment.OnGameStartListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends FragmentActivity implements OnGameStartListener, OnQuestStartListener, OnBattleStartListener, OnBattleEndListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();

		StartFragment mStartFragment = new StartFragment();
		mStartFragment.setOnGameStartListener(this);
		ft.add(R.id.MainActivity_frame, mStartFragment);
		ft.commit();
	}

	@Override
	public void onGameStart() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		HomeFragment mHomeFragment = new HomeFragment();
		mHomeFragment.setOnQuestStartListener(this);
		ft.replace(R.id.MainActivity_frame, mHomeFragment);
		ft.commit();
	}

	@Override
	public void onQuestStart() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		DungeonFragment mDungeonFragment = new DungeonFragment();
		mDungeonFragment.setOnBattleStartListener(this);
		LinearLayout main = (LinearLayout) this.findViewById(R.id.HomeFragment_main);
		LinearLayout content = (LinearLayout) this.findViewById(R.id.HomeFragment_content);
		main.removeView(content);
		ft.add(R.id.HomeFragment_main, mDungeonFragment);
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
		mHomeFragment.setOnQuestStartListener(this);
		ft.replace(R.id.MainActivity_frame, mHomeFragment);
		ft.commit();
	}

}
