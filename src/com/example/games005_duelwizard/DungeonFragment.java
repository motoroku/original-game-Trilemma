package com.example.games005_duelwizard;

import java.util.EventListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DungeonFragment extends Fragment implements OnClickListener {

	OnBattleStartListener mListener;

	Button mButtonEnemyA;
	Button mButtonEnemyB;
	Button mButtonExit;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_dungeon, container, false);

		setViews(v);

		return v;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.DungeonFragment_button1:
				// 敵Aボタン
				mListener.onBattleStart();
				break;
			case R.id.DungeonFragment_button2:
				// 敵Bボタン
				break;
			case R.id.DungeonFragment_button3:
				// 出口ボタン
				break;
			default:
				break;
		}
	}

	private void setViews(View v) {
		mButtonEnemyA = (Button) v.findViewById(R.id.DungeonFragment_button1);
		mButtonEnemyB = (Button) v.findViewById(R.id.DungeonFragment_button2);
		mButtonExit = (Button) v.findViewById(R.id.DungeonFragment_button3);

		mButtonEnemyA.setOnClickListener(this);
		mButtonEnemyB.setOnClickListener(this);
		mButtonExit.setOnClickListener(this);
	}

	public void setOnBattleStartListener(OnBattleStartListener listener) {
		this.mListener = listener;
	}

	public interface OnBattleStartListener extends EventListener {
		void onBattleStart();
	}

}
