package activities.fragment;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import com.games.Trilemma.R;

import dao.DaoManager;
import service.PlayerService;
import system.battle.BattleSystem;
import utility.ImageSelector;
import utility.Utility;
import entity.BattleStatus;
import entity.CharacterEntity;
import entity.BattleStatus.SelectedActionList;
import entity.Enemy;
import entity.Player;
import Trilemma.MONSTER;
import Trilemma.LEARNED_SKILL;
import Trilemma.SKILL;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class BattleFragment extends Fragment implements OnClickListener, OnTouchListener {

	// ---------------------------------------------------
	// View
	private Button mButtonSkill;
	private Button mPopupButtonSkill1;
	private Button mPopupButtonSkill2;
	private Button mPopupButtonSkill3;
	private Button mPopupButtonSkill4;
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

	private ImageView mImageViewEnemy;
	private ImageView mImageViewPlayer;

	private ListView mListViewA;

	private LinearLayout mLinearLayoutEnemyBackGround;
	private LinearLayout mLinearLayoutAllBackGround;

	private PopupWindow popupWindowSkill1;
	private PopupWindow popupWindowSkill2;
	private PopupWindow popupWindowSkill3;
	private PopupWindow popupWindowSkill4;

	// ---------------------------------------------------
	// Adapter
	private ArrayAdapter<String> mAdapterA;
	// ---------------------------------------------------
	// List
	private List<String> mListA = new ArrayList<String>();
	private List<MONSTER> characterList = new ArrayList<MONSTER>();
	private List<LEARNED_SKILL> playerLearnedSkillList = new ArrayList<LEARNED_SKILL>();
	private List<LEARNED_SKILL> learnedSkillList = new ArrayList<LEARNED_SKILL>();
	private List<SKILL> enemySkillList = new ArrayList<SKILL>();
	private List<SKILL> playerSkillList = new ArrayList<SKILL>();
	// ---------------------------------------------------
	// Logic
	private BattleSystem battleSystem;
	// ---------------------------------------------------
	OnBattleEndListener mListener;

	// ---------------------------------------------------
	int x;
	int y;
	int skillButtonSizeX;
	int skillButtonSizeY;

	float lastTouchX;
	float lastTouchY;
	float currentX;
	float currentY;

	// ---------------------------------------------------
	Player player;
	Enemy enemy;

	// ---------------------------------------------------

	// ------------------------------------------------------------------------------------------------------
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_battle, container, false);
		Context context = getActivity();

		setDisplaySize(context);
		findViews(v, context);
		setBattleSetting(v, context, getArguments());
		setViewValues(getArguments());

		mTextViewA1.setText("PLAYER");
		mTextViewB1.setText("HP:" + battleSystem.battleElements.characterMap.get(BattleStatus.PLAYER).currentHp);
		mTextViewC1.setText("SP:" + battleSystem.battleElements.characterMap.get(BattleStatus.PLAYER).currentSp);

		mTextViewA2.setText("NPC");
		mTextViewB2.setText("HP:" + battleSystem.battleElements.characterMap.get(BattleStatus.ENEMY).currentHp);
		mTextViewC2.setText("SP:" + battleSystem.battleElements.characterMap.get(BattleStatus.ENEMY).currentSp);

		mAdapterA.add(battleSystem.battleElements.characterMap.get(BattleStatus.ENEMY).name + "が現れた！");

		setEnemyActionRate();
		mAdapterA.add(setEnemyActionRate());

		return v;
	}

	private void findViews(View v, Context context) {
		/*
		 * 画面に表示されるViewをバインドする
		 */
		mButtonSkill = (Button) v.findViewById(R.id.BattleFragment_button_Skill);
		mButtonDefense = (Button) v.findViewById(R.id.BattleFragment_button_Defense);
		mButtonCharge = (Button) v.findViewById(R.id.BattleFragment_button_Charge);
		mButtonxxx = (Button) v.findViewById(R.id.BattleFragment_button_xxx);
		mButtonyyy = (Button) v.findViewById(R.id.BattleFragment_button_yyy);

		mButtonSkill.setOnClickListener(this);
		mButtonSkill.setOnTouchListener(this);
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

		mImageViewEnemy = (ImageView) v.findViewById(R.id.BattleFragment_imageView_Enemy);
		mImageViewPlayer = (ImageView) v.findViewById(R.id.BattleFragment_imageView_Player);

		mLinearLayoutEnemyBackGround = (LinearLayout) v.findViewById(R.id.BattleFragment_EnemybackGround);
		mLinearLayoutAllBackGround = (LinearLayout) v.findViewById(R.id.BattleFragment_LinearLayout);

		/*
		 * 画面に表示されるListViewに セットするAdapterとList<String>を紐付けする
		 */
		mListViewA = (ListView) v.findViewById(R.id.BattleFragment_listViewA);
		mAdapterA = new ArrayAdapter<String>(context, R.layout.list_row_chara_white);
		mListViewA.setAdapter(mAdapterA);

		// PopupWindowを作成
		LinearLayout popupSkillLayout1 = (LinearLayout) getActivity().getLayoutInflater().inflate(
				R.layout.popupwindow_battle_skill, null);
		mPopupButtonSkill1 = (Button) popupSkillLayout1.findViewById(R.id.popupwindow_button_skill);
		LinearLayout popupSkillLayout2 = (LinearLayout) getActivity().getLayoutInflater().inflate(
				R.layout.popupwindow_battle_skill, null);
		mPopupButtonSkill2 = (Button) popupSkillLayout2.findViewById(R.id.popupwindow_button_skill);
		LinearLayout popupSkillLayout3 = (LinearLayout) getActivity().getLayoutInflater().inflate(
				R.layout.popupwindow_battle_skill, null);
		mPopupButtonSkill3 = (Button) popupSkillLayout3.findViewById(R.id.popupwindow_button_skill);
		LinearLayout popupSkillLayout4 = (LinearLayout) getActivity().getLayoutInflater().inflate(
				R.layout.popupwindow_battle_skill, null);
		mPopupButtonSkill4 = (Button) popupSkillLayout4.findViewById(R.id.popupwindow_button_skill);

		popupWindowSkill1 = new PopupWindow(getActivity());
		popupWindowSkill1.setWindowLayoutMode(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		popupWindowSkill1.setContentView(popupSkillLayout1);
		popupWindowSkill1.setOutsideTouchable(true);
		popupWindowSkill1.setFocusable(true);

		popupWindowSkill2 = new PopupWindow(getActivity());
		popupWindowSkill2.setWindowLayoutMode(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		popupWindowSkill2.setContentView(popupSkillLayout2);
		popupWindowSkill2.setOutsideTouchable(true);
		popupWindowSkill2.setFocusable(true);

		popupWindowSkill3 = new PopupWindow(getActivity());
		popupWindowSkill3.setWindowLayoutMode(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		popupWindowSkill3.setContentView(popupSkillLayout3);
		popupWindowSkill3.setOutsideTouchable(true);
		popupWindowSkill3.setFocusable(true);

		popupWindowSkill4 = new PopupWindow(getActivity());
		popupWindowSkill4.setWindowLayoutMode(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		popupWindowSkill4.setContentView(popupSkillLayout4);
		popupWindowSkill4.setOutsideTouchable(true);
		popupWindowSkill4.setFocusable(true);

	}

	private void setViewValues(Bundle bundle) {
		// SetField
		mLinearLayoutEnemyBackGround.setBackgroundResource(ImageSelector.getBackGround((int) bundle.getLong("id")));
		((MarginLayoutParams) mImageViewEnemy.getLayoutParams()).leftMargin = x / 20;
		((MarginLayoutParams) mImageViewPlayer.getLayoutParams()).rightMargin = x / 15;
		mLinearLayoutEnemyBackGround.getLayoutParams().height = y / 3;

		if (player.skillList[2] != null) {
			mPopupButtonSkill1.setText(player.skillList[2].skillName);
		}
		if (player.skillList[3] != null) {
			mPopupButtonSkill2.setText(player.skillList[3].skillName);
		}
		if (player.skillList[4] != null) {
			mPopupButtonSkill3.setText(player.skillList[4].skillName);
		}
		if (player.skillList[5] != null) {
			mPopupButtonSkill4.setText(player.skillList[5].skillName);
		}
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void setDisplaySize(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point point = new Point();

		if (Build.VERSION.SDK_INT >= 13) {
			display.getSize(point);
			x = point.x;
			y = point.y;
		} else {
			x = display.getWidth();
			y = display.getHeight();
		}
	}

	// ------------------------------------------------------------------------------------------------------
	private void setBattleSetting(View v, Context context, Bundle bundle) {
		DaoManager dao = new DaoManager(v.getContext());
        PlayerService playerService = new PlayerService(dao);

		// SetEnemy
		enemy = createEnemy(dao, bundle, context);
		// SetPlayer
		player = setPlayer(dao, context);

		battleSystem = new BattleSystem(player, enemy, context);
	}

	private Enemy createEnemy(DaoManager dao, Bundle bundle, Context context) {
		characterList = dao.getCharacterList(bundle.getLong("id"));
		int num = Utility.getRandomNum(characterList.size() - 1, true);
        MONSTER chara = characterList.get(num);

		int width;
		int height;

		Bitmap image = BitmapFactory.decodeResource(context.getResources(),
				ImageSelector.getEnemyImage(chara.getImage_no()));

		width = image.getWidth();
		height = image.getHeight();

		mImageViewEnemy.setBackgroundResource(ImageSelector.getEnemyImage(chara.getImage_no()));
		mImageViewEnemy.getLayoutParams().width = width * 3;
		mImageViewEnemy.getLayoutParams().height = height * 3;

		learnedSkillList = chara.getSkillList();

		for (int i = 0; i < learnedSkillList.size(); i++) {
			enemySkillList.add(learnedSkillList.get(i).getSKILL());
		}

		SKILL defense = dao.getDefaultDefenseSkill();
		SKILL charge = dao.getDefaultChargeSkill();
		Enemy enemy = new Enemy(chara, enemySkillList, defense, charge);
		return enemy;
	}

	private Player setPlayer(DaoManager dao, Context context) {
		int width;
		int height;

		Bitmap image = BitmapFactory.decodeResource(context.getResources(), R.drawable.player_01);

		width = image.getWidth();
		height = image.getHeight();

		mImageViewPlayer.getLayoutParams().width = width * 2;
		mImageViewPlayer.getLayoutParams().height = height * 2;

        PlayerService playerService = new PlayerService(dao);
		Player player = playerService.createPlayer();

		return player;
	}

	// ------------------------------------------------------------------------------------------------------

	private void startAction(SelectedActionList selectedAction) {
		CharacterEntity player = battleSystem.battleElements.characterMap.get(BattleStatus.PLAYER);
		if (battleSystem.isSetSkill(selectedAction, battleSystem.battleElements)) {
			if (battleSystem.isHaveNecessaryPoint(selectedAction, player)) {
				battleSystem.StartBattle(selectedAction);
				outputActionResult(battleSystem);
			} else {
				outPutInfoToA("SPが足りません");
			}
		} else {
			outPutInfoToA("スキルが設定されていません");
		}
	}

	private void outPutInfoToA(String message) {
		mAdapterA.add("Message:" + message);
	}

	private void outputActionResult(BattleSystem system) {
		// 画面出力用
		String playerAction = battleSystem.battleElements.playerTrunHistoryList
				.get(battleSystem.battleElements.playerTrunHistoryList.size() - 1).action.getValue();
		String playerSkill = battleSystem.battleElements.playerTrunHistoryList
				.get(battleSystem.battleElements.playerTrunHistoryList.size() - 1).skill.skillName;
		String enemyAction = battleSystem.battleElements.enemyTurnHistoryList
				.get(battleSystem.battleElements.enemyTurnHistoryList.size() - 1).action.getValue();
		String enemySkill = battleSystem.battleElements.enemyTurnHistoryList
				.get(battleSystem.battleElements.enemyTurnHistoryList.size() - 1).skill.skillName;

		mAdapterA.add("----------------------------");
		mAdapterA.add("Turn:" + battleSystem.battleElements.turnCount);
		mAdapterA.add("PlayerAction:" + playerAction + " Skill:" + playerSkill);
		mAdapterA.add("EnemyAction:" + enemyAction + " Skill:" + enemySkill);
		mAdapterA.add(setEnemyActionRate());
	}

	private String setEnemyActionRate() {
		String attackRate = String.valueOf(((Enemy) battleSystem.battleElements.getEnemy()).attackRate);
		String defenseRate = String.valueOf(((Enemy) battleSystem.battleElements.getEnemy()).defenseRate);
		String chargerate = String.valueOf(((Enemy) battleSystem.battleElements.getEnemy()).chargeRate);
		return "攻撃確率：" + attackRate + " 防御確率：" + defenseRate + " チャージ確率：" + chargerate;
	}

	private void endTurn() {
		if (battleSystem.isBattleEnd()) {
			outPutInfoToA("WIN");
			battleSystem.endBattle();
			// TODO 戦闘画面を抜ける方法を考える
		}
		mListViewA.setSelection(mAdapterA.getCount());
		mTextViewB1.setText("HP:" + battleSystem.battleElements.characterMap.get(BattleStatus.PLAYER).currentHp);
		mTextViewC1.setText("SP:" + battleSystem.battleElements.characterMap.get(BattleStatus.PLAYER).currentSp);
		mTextViewB2.setText("HP:" + battleSystem.battleElements.characterMap.get(BattleStatus.ENEMY).currentHp);
		mTextViewC2.setText("SP:" + battleSystem.battleElements.characterMap.get(BattleStatus.ENEMY).currentSp);
	}

	// ------------------------------------------------------------------------------------------------------

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.BattleFragment_button_Skill:
				break;
			case R.id.BattleFragment_button_Defense:
				startAction(SelectedActionList.defense);
				break;
			case R.id.BattleFragment_button_Charge:
				startAction(SelectedActionList.charge);
				break;
			case R.id.BattleFragment_button_xxx:
				mListener.onEndBattle();
				break;
			case R.id.BattleFragment_button_yyy:
				setBattleSetting(v, v.getContext(), getArguments());
				mAdapterA.clear();
				mTextViewA1.setText("PLAYER");
				mTextViewB1
						.setText("HP:" + battleSystem.battleElements.characterMap.get(BattleStatus.PLAYER).currentHp);
				mTextViewC1
						.setText("SP:" + battleSystem.battleElements.characterMap.get(BattleStatus.PLAYER).currentSp);
				mTextViewA2.setText("NPC");
				mTextViewB2.setText("HP:" + battleSystem.battleElements.characterMap.get(BattleStatus.ENEMY).currentHp);
				mTextViewC2.setText("SP:" + battleSystem.battleElements.characterMap.get(BattleStatus.ENEMY).currentSp);
				mAdapterA.add(battleSystem.battleElements.characterMap.get(BattleStatus.ENEMY).name + "が現れた！");
				mAdapterA.add(setEnemyActionRate());
				break;
			default:
				break;
		}
		endTurn();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		skillButtonSizeX = mButtonSkill.getWidth();
		skillButtonSizeY = mButtonSkill.getHeight();

		switch (event.getAction()) {
		// タッチ
			case MotionEvent.ACTION_DOWN:
				lastTouchX = x;
				lastTouchY = y;
				popupWindowSkill1.showAsDropDown(v, 0, -skillButtonSizeY * 2);
				popupWindowSkill2.showAsDropDown(v, skillButtonSizeX, -skillButtonSizeY);
				popupWindowSkill3.showAsDropDown(v, -skillButtonSizeX, -skillButtonSizeY);
				popupWindowSkill4.showAsDropDown(v, 0, 0);

				break;
			case MotionEvent.ACTION_UP:
				currentX = x;
				currentY = y;
				if (popupWindowSkill1.isShowing()) {
					popupWindowSkill1.dismiss();
				}
				if (popupWindowSkill2.isShowing()) {
					popupWindowSkill2.dismiss();
				}
				if (popupWindowSkill3.isShowing()) {
					popupWindowSkill3.dismiss();
				}
				if (popupWindowSkill4.isShowing()) {
					popupWindowSkill4.dismiss();
				}

				if (skillButtonSizeX > currentX && currentX > 0 && 0 > currentY && currentY > -skillButtonSizeY) {
					startAction(SelectedActionList.skill1);
				}
				if (skillButtonSizeX * 2 > currentX && currentX > skillButtonSizeX && skillButtonSizeY > currentY
						&& currentY > 0) {
					startAction(SelectedActionList.skill2);
				}
				if (0 > currentX && currentX > -skillButtonSizeX && skillButtonSizeY > currentY && currentY > 0) {
					startAction(SelectedActionList.skill3);
				}
				if (skillButtonSizeX > currentX && currentX > 0 && skillButtonSizeY * 2 > currentY
						&& currentY > skillButtonSizeY) {
					startAction(SelectedActionList.skill4);
				}
			default:
				break;
		}
		endTurn();
		return true;
	}

	// ------------------------------------------------------------------------------------------------------

	public void setOnBattleEndListener(OnBattleEndListener listener) {
		this.mListener = listener;
	}

	public interface OnBattleEndListener extends EventListener {
		void onEndBattle();
	}

}
