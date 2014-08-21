package com.example.games005_duelwizard;

import com.example.games005_duelwizard.StartFragment.OnBattleStartListener;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends FragmentActivity implements OnBattleStartListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();

		StartFragment mStartFragment = new StartFragment();
		mStartFragment.setOnBattleStartListener(this);
		ft.add(R.id.MainActivity_LinearLayout, mStartFragment);
		ft.commit();
	}

	@Override
	public void onGameStart() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		BattleFragment mBattleFragment = new BattleFragment();
		ft.replace(R.id.MainActivity_LinearLayout, mBattleFragment);
		ft.commit();
	}

}
