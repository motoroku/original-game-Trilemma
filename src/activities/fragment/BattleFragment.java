package activities.fragment;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import com.games.Trilemma.R;

import dao.DaoManager;
import dao.PlayerDto;

import system.battle.BattleSystem;
import utility.ImageSelector;
import utility.Utility;

import entity.BattleStatus;
import entity.CharacterEntity;
import entity.BattleStatus.SelectedActionList;
import entity.Enemy;
import entity.Player;

import Trilemma.CHARACTER;
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
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

	private ImageView mImageViewEnemy;
	private ImageView mImageViewPlayer;

	private ListView mListViewA;

	private LinearLayout mLinearLayoutEnemyBackGround;
	private LinearLayout mLinearLayoutAllBackGround;

	// ---------------------------------------------------
	// Adapter
	private ArrayAdapter<String> mAdapterA;
	// ---------------------------------------------------
	// List
	private List<String> mListA = new ArrayList<String>();
	private List<CHARACTER> characterList = new ArrayList<CHARACTER>();
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

		mAdapterA.add(battleSystem.enemyActionRate);

		return v;
	}

	private void setBattleSetting(View v, Context context, Bundle bundle) {
		DaoManager dao = new DaoManager(v.getContext());

		// SetEnemy
		enemy = createEnemy(dao, bundle, context);
		// SetPlayer
		player = createPlayer(dao, context);

		battleSystem = new BattleSystem(player, enemy);
	}

	private Enemy createEnemy(DaoManager dao, Bundle bundle, Context context) {
		characterList = dao.getCharacterList(bundle.getLong("id"));
		int num = Utility.getRandomNum(characterList.size() - 1, true);
		CHARACTER chara = characterList.get(num);

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

	private Player createPlayer(DaoManager dao, Context context) {
		int width;
		int height;

		Bitmap image = BitmapFactory.decodeResource(context.getResources(), R.drawable.player_01);

		width = image.getWidth();
		height = image.getHeight();

		mImageViewPlayer.getLayoutParams().width = width * 2;
		mImageViewPlayer.getLayoutParams().height = height * 2;

		SKILL defense = dao.getDefaultDefenseSkill();
		SKILL charge = dao.getDefaultChargeSkill();

		playerLearnedSkillList = dao.getPlayerLearnedSkill();
		playerSkillList = dao.getPlayerSkillList(playerLearnedSkillList);

		PlayerDto playerDto = dao.getPlayerDto();

		Player player = new Player(playerDto, playerLearnedSkillList, playerSkillList, defense, charge);

		return player;
	}

	private void findViews(View v, Context context) {
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

		mImageViewEnemy = (ImageView) v.findViewById(R.id.BattleFragment_imageView_Enemy);
		mImageViewPlayer = (ImageView) v.findViewById(R.id.BattleFragment_imageView_Player);

		mLinearLayoutEnemyBackGround = (LinearLayout) v.findViewById(R.id.BattleFragment_EnemybackGround);
		mLinearLayoutAllBackGround = (LinearLayout) v.findViewById(R.id.BattleFragment_LinearLayout);

		/*
		 * 画面に表示されるListViewに セットするAdapterとList<String>を紐付けする
		 */
		mListViewA = (ListView) v.findViewById(R.id.BattleFragment_listViewA);
		mAdapterA = new ArrayAdapter<String>(context, R.layout.list_row);
		mListViewA.setAdapter(mAdapterA);
	}

	private void setViewValues(Bundle bundle) {
		// SetField
		mLinearLayoutEnemyBackGround.setBackgroundResource(ImageSelector.getBackGround((int) bundle.getLong("id")));
		((MarginLayoutParams) mImageViewEnemy.getLayoutParams()).leftMargin = x / 20;
		((MarginLayoutParams) mImageViewPlayer.getLayoutParams()).rightMargin = x / 15;
		mLinearLayoutEnemyBackGround.getLayoutParams().height = y / 3;

		if (player.skillList.length > 2) {
			mButtonSkill1.setText(player.skillList[2].skillName);
		}
		if (player.skillList.length > 3) {
			mButtonSkill2.setText(player.skillList[3].skillName);
		}
		if (player.skillList.length > 4) {
			mButtonSkill3.setText(player.skillList[4].skillName);
		}
		if (player.skillList.length > 5) {
			mButtonSkill4.setText(player.skillList[5].skillName);
		}
		if (player.skillList.length > 6) {
			mButtonSkill5.setText(player.skillList[6].skillName);
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

	private void startAction(SelectedActionList selectedAction) {
		CharacterEntity player = battleSystem.battleElements.characterMap.get(BattleStatus.PLAYER);
		if (battleSystem.isSetSkill(selectedAction, battleSystem.battleElements)) {
			if (battleSystem.isHaveNecessaryPoint(selectedAction, player)) {
				startBattle(selectedAction);
				outputActionResult(battleSystem);
			} else {
				outPutInfoToA("SPが足りません");
			}
		} else {
			outPutInfoToA("スキルが設定されていません");
		}
	}

	private void startBattle(SelectedActionList selectedAction) {
		battleSystem.StartBattle(selectedAction);
		if (battleSystem.isBattleEnd(true)) {
			battleSystem.EndTurn();
		}
	}

	private boolean isBattleEnd() {
		return battleSystem.battleElements.getEnemy().currentHp <= 0;
	}

	private void outPutInfoToA(String message) {
		mAdapterA.add("Message:" + message);
	}

	private void outputActionResult(BattleSystem system) {
		mAdapterA.add("----------------------------");
		mAdapterA.add("Turn:" + battleSystem.battleElements.turnCount);
		mAdapterA.add("PlayerAction:" + system.playerAction + " Skill:" + system.playerSkill);
		mAdapterA.add("EnemyAction:" + system.enemyAction + " Skill:" + system.enemySkill);
		mAdapterA.add(system.enemyActionRate);
	}

	// ------------------------------------------------------------------------------------------------------

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
				mAdapterA.add(battleSystem.enemyActionRate);
				break;
			default:
				break;
		}

		if (isBattleEnd()) {
			outPutInfoToA("WIN");
			// resetBattleSystem();
		}

		mListViewA.setSelection(mAdapterA.getCount());
		mTextViewB1.setText("HP:" + battleSystem.battleElements.characterMap.get(BattleStatus.PLAYER).currentHp);
		mTextViewC1.setText("SP:" + battleSystem.battleElements.characterMap.get(BattleStatus.PLAYER).currentSp);
		mTextViewB2.setText("HP:" + battleSystem.battleElements.characterMap.get(BattleStatus.ENEMY).currentHp);
		mTextViewC2.setText("SP:" + battleSystem.battleElements.characterMap.get(BattleStatus.ENEMY).currentSp);
	}

	// ------------------------------------------------------------------------------------------------------

	public void setOnBattleEndListener(OnBattleEndListener listener) {
		this.mListener = listener;
	}

	public interface OnBattleEndListener extends EventListener {
		void onEndBattle();
	}

}
