package com.example.games005_duelwizard;

import java.util.ArrayList;
import java.util.List;

import dw.system.battle.BattleSystem;
import dw.system.entity.BattleStatus;
import dw.system.entity.BattleStatus.ActionStatus;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class BattleFragment extends Fragment implements OnClickListener {

	// ---------------------------------------------------
	// View
	private Button mButtonA;
	private Button mButtonB;
	private Button mButtonC;
	private Button mButtonD;
	private Button mButtonE;
	private Button mButtonF;

	private TextView mTextViewA1;
	private TextView mTextViewA2;
	private TextView mTextViewB1;
	private TextView mTextViewB2;
	private TextView mTextViewC1;
	private TextView mTextViewC2;

	private ListView mListViewA;
	private ListView mListViewB;

	// ---------------------------------------------------
	// Adapter
	private ArrayAdapter<String> mAdapterA;
	private ArrayAdapter<String> mAdapterB;

	// ---------------------------------------------------
	// List
	private List<String> mListA = new ArrayList<String>();
	private List<String> mListB = new ArrayList<String>();

	// ---------------------------------------------------
	// Logic
	private BattleSystem mBattleSystem;

	// ---------------------------------------------------

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_battle, container, false);
		Context context = getActivity();

		setViews(v, context);

		mBattleSystem = new BattleSystem();

		mTextViewA1.setText("PLAYER");
		mTextViewB1.setText("HP:" + mBattleSystem.mBattleElements.characterMap.get(BattleStatus.PLAYER).mHp);
		mTextViewC1.setText("SP:" + mBattleSystem.mBattleElements.characterMap.get(BattleStatus.PLAYER).mSp);

		mTextViewA2.setText("NPC");
		mTextViewB2.setText("HP:" + mBattleSystem.mBattleElements.characterMap.get(BattleStatus.NPC).mHp);
		mTextViewC2.setText("SP:" + mBattleSystem.mBattleElements.characterMap.get(BattleStatus.NPC).mSp);

		return v;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.BattleFragment_buttonA:
				StartAction(0);
				break;
			case R.id.BattleFragment_buttonB:
				StartAction(10);
				break;
			case R.id.BattleFragment_buttonC:
				StartAction(11);
				break;
			case R.id.BattleFragment_buttonD:

				break;
			case R.id.BattleFragment_buttonE:

				break;
			case R.id.BattleFragment_buttonF:
				break;
			default:
				break;
		}

		outputInfo(mBattleSystem);

		mTextViewB1.setText("HP:" + mBattleSystem.mBattleElements.characterMap.get(BattleStatus.PLAYER).mHp);
		mTextViewC1.setText("SP:" + mBattleSystem.mBattleElements.characterMap.get(BattleStatus.PLAYER).mSp);
		mTextViewB2.setText("HP:" + mBattleSystem.mBattleElements.characterMap.get(BattleStatus.NPC).mHp);
		mTextViewC2.setText("SP:" + mBattleSystem.mBattleElements.characterMap.get(BattleStatus.NPC).mSp);
	}

	private void outputInfo(BattleStatus.ActionStatus actionStatus) {
		mAdapterA.add(actionStatus.getActionStatusName());
		mListViewA.setAdapter(mAdapterA);
	}

	private void outputInfo(BattleSystem system) {
		mAdapterA.add("PlayerAction:" + system.playerAction);
		mAdapterA.add("EnemyAction:" + system.enemyAction);
	}

	private void StartAction(int action) {
		mBattleSystem.StartBattle(action);
		if (mBattleSystem.isBattleEnd(true)) {
			mBattleSystem.EndTurn();
		}
	}

	private void setViews(View v, Context context) {
		/*
		 * 画面に表示されるViewをバインドする
		 */
		mButtonA = (Button) v.findViewById(R.id.BattleFragment_buttonA);
		mButtonB = (Button) v.findViewById(R.id.BattleFragment_buttonB);
		mButtonC = (Button) v.findViewById(R.id.BattleFragment_buttonC);
		mButtonD = (Button) v.findViewById(R.id.BattleFragment_buttonD);
		mButtonE = (Button) v.findViewById(R.id.BattleFragment_buttonE);
		mButtonF = (Button) v.findViewById(R.id.BattleFragment_buttonF);

		mButtonA.setOnClickListener(this);
		mButtonB.setOnClickListener(this);
		mButtonC.setOnClickListener(this);
		mButtonD.setOnClickListener(this);
		mButtonE.setOnClickListener(this);
		mButtonF.setOnClickListener(this);

		mTextViewA1 = (TextView) v.findViewById(R.id.BattleFragment_textViewA_1);
		mTextViewA2 = (TextView) v.findViewById(R.id.BattleFragment_textViewA_2);
		mTextViewB1 = (TextView) v.findViewById(R.id.BattleFragment_textViewB_1);
		mTextViewB2 = (TextView) v.findViewById(R.id.BattleFragment_textViewB_2);
		mTextViewC1 = (TextView) v.findViewById(R.id.BattleFragment_textViewC_1);
		mTextViewC2 = (TextView) v.findViewById(R.id.BattleFragment_textViewC_2);

		/*
		 * 画面に表示されるListViewに セットするAdapterとList<String>を紐付けする
		 */
		mListViewA = (ListView) v.findViewById(R.id.BattleFragment_listViewA);
		mListViewB = (ListView) v.findViewById(R.id.BattleFragment_listViewB);

		mAdapterA = new ArrayAdapter<String>(context, R.layout.list_row, mListA);
		mAdapterB = new ArrayAdapter<String>(context, R.layout.list_row, mListB);

		mListViewA.setAdapter(mAdapterA);
		mListViewB.setAdapter(mAdapterB);

	}

}
