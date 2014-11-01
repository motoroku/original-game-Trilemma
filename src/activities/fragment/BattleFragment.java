package activities.fragment;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import system.battle.BattleSystem;

import com.example.games005_duelwizard.R;

import entity.BattleStatus;
import entity.CharacterEntity;
import entity.BattleStatus.SelectedActionList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class BattleFragment extends Fragment implements OnClickListener {

	// ---------------------------------------------------
	// View
	private Button mButtonSkill1;
	private Button mButtonSkill2;
	private Button mButtonSkill3;
	private Button mButtonSkill4;
	private Button mButtonSkill5;
	private Button mButtonDefense;
	private Button mButtonCharge;
	private Button mButtonxxx;
	private Button mButtonyyy;

	private TextView mTextViewA1;
	private TextView mTextViewA2;
	private TextView mTextViewB1;
	private TextView mTextViewB2;
	private TextView mTextViewC1;
	private TextView mTextViewC2;

	private ListView mListViewA;

	// ---------------------------------------------------
	// Adapter
	private ArrayAdapter<String> mAdapterA;
	// ---------------------------------------------------
	// List
	private List<String> mListA = new ArrayList<String>();
	// ---------------------------------------------------
	// Logic
	private BattleSystem battleSystem;
	// ---------------------------------------------------
	OnBattleEndListener mListener;

	// ---------------------------------------------------

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_battle, container, false);
		Context context = getActivity();

		setViews(v, context);

		battleSystem = new BattleSystem();

		mTextViewA1.setText("PLAYER");
		mTextViewB1.setText("HP:" + battleSystem.battleElements.characterMap.get(BattleStatus.PLAYER).hp);
		mTextViewC1.setText("SP:" + battleSystem.battleElements.characterMap.get(BattleStatus.PLAYER).sp);

		mTextViewA2.setText("NPC");
		mTextViewB2.setText("HP:" + battleSystem.battleElements.characterMap.get(BattleStatus.ENEMY).hp);
		mTextViewC2.setText("SP:" + battleSystem.battleElements.characterMap.get(BattleStatus.ENEMY).sp);

		mAdapterA.add("敵が現れた！");

		return v;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.BattleFragment_button_Skill1:
				startAction(SelectedActionList.skill1);
				break;
			case R.id.BattleFragment_button_Skill2:
				startAction(SelectedActionList.skill2);
				break;
			case R.id.BattleFragment_button_Skill3:
				startAction(SelectedActionList.skill3);
				break;
			case R.id.BattleFragment_button_Skill4:
				startAction(SelectedActionList.skill4);
				break;
			case R.id.BattleFragment_button_Skill5:
				startAction(SelectedActionList.skill5);
				break;
			case R.id.BattleFragment_button_Defense:
				startAction(SelectedActionList.defense);
				break;
			case R.id.BattleFragment_button_Charge:
				startAction(SelectedActionList.charge);
				break;
			case R.id.BattleFragment_button_xxx:
				mListener.onBattleEnd();
				break;
			case R.id.BattleFragment_button_yyy:
				resetBattleSystem();
				mAdapterA.clear();
				break;
			default:
				break;
		}

		if (isBattleEnd()) {
			outPutInfoToA("WIN");
			resetBattleSystem();
		}

		mListViewA.setSelection(mAdapterA.getCount());
		mTextViewB1.setText("HP:" + battleSystem.battleElements.characterMap.get(BattleStatus.PLAYER).hp);
		mTextViewC1.setText("SP:" + battleSystem.battleElements.characterMap.get(BattleStatus.PLAYER).sp);
		mTextViewB2.setText("HP:" + battleSystem.battleElements.characterMap.get(BattleStatus.ENEMY).hp);
		mTextViewC2.setText("SP:" + battleSystem.battleElements.characterMap.get(BattleStatus.ENEMY).sp);
	}

	private void startAction(SelectedActionList selectedAction) {
		CharacterEntity player = battleSystem.battleElements.characterMap.get(BattleStatus.PLAYER);
		if (battleSystem.isHaveNecessaryPoint(selectedAction, player)) {
			startBattle(selectedAction);
			outputActionResult(battleSystem);
		} else {
			outPutInfoToA("SPが足りません");
		}
	}

	private void startBattle(SelectedActionList selectedAction) {
		battleSystem.StartBattle(selectedAction);
		if (battleSystem.isBattleEnd(true)) {
			battleSystem.EndTurn();
		}
	}

	private boolean isBattleEnd() {
		return battleSystem.battleElements.getEnemy().hp <= 0;
	}

	private void resetBattleSystem() {
		battleSystem = new BattleSystem();
	}

	private void outPutInfoToA(String message) {
		mAdapterA.add("Message:" + message);
	}

	private void outputActionResult(BattleSystem system) {
		mAdapterA.add("----------------------------");
		mAdapterA.add("Turn:" + battleSystem.battleElements.turnCount);
		mAdapterA.add("PlayerAction:" + system.playerAction);
		mAdapterA.add("EnemyAction:" + system.enemyAction);
	}

	private void setViews(View v, Context context) {
		/*
		 * 画面に表示されるViewをバインドする
		 */
		mButtonSkill1 = (Button) v.findViewById(R.id.BattleFragment_button_Skill1);
		mButtonSkill2 = (Button) v.findViewById(R.id.BattleFragment_button_Skill2);
		mButtonSkill3 = (Button) v.findViewById(R.id.BattleFragment_button_Skill3);
		mButtonSkill4 = (Button) v.findViewById(R.id.BattleFragment_button_Skill4);
		mButtonSkill5 = (Button) v.findViewById(R.id.BattleFragment_button_Skill5);
		mButtonDefense = (Button) v.findViewById(R.id.BattleFragment_button_Defense);
		mButtonCharge = (Button) v.findViewById(R.id.BattleFragment_button_Charge);
		mButtonxxx = (Button) v.findViewById(R.id.BattleFragment_button_xxx);
		mButtonyyy = (Button) v.findViewById(R.id.BattleFragment_button_yyy);

		mButtonSkill1.setOnClickListener(this);
		mButtonSkill2.setOnClickListener(this);
		mButtonSkill3.setOnClickListener(this);
		mButtonSkill4.setOnClickListener(this);
		mButtonSkill5.setOnClickListener(this);
		mButtonDefense.setOnClickListener(this);
		mButtonCharge.setOnClickListener(this);
		mButtonxxx.setOnClickListener(this);
		mButtonyyy.setOnClickListener(this);

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
		mAdapterA = new ArrayAdapter<String>(context, R.layout.list_row, mListA);
		mListViewA.setAdapter(mAdapterA);
	}

	public void setOnBattleEndListener(OnBattleEndListener listener) {
		this.mListener = listener;
	}

	public interface OnBattleEndListener extends EventListener {
		void onBattleEnd();
	}

}
