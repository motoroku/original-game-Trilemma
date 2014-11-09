package activities.fragment;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import com.games.Trilemma.R;

import Trilemma.DUNGEON;
import Trilemma.DUNGEONDao;
import Trilemma.DaoMaster;
import Trilemma.DaoSession;
import Trilemma.EXP_TABLE;
import Trilemma.EXP_TABLEDao;
import Trilemma.MONSTER;
import Trilemma.MONSTERDao;
import Trilemma.PLAYER;
import Trilemma.PLAYERDao;
import Trilemma.TOWN;
import Trilemma.TOWNDao;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class HomeFragment extends Fragment implements OnClickListener {

	OnQuestStartListener mListener;

	private Button mButtonQuest;
	private ListView mMessageWindow;
	private ArrayAdapter<String> mAdapter;
	private List<String> list = new ArrayList<String>();

	private List<DUNGEON> dungeonList = new ArrayList<DUNGEON>();
	private List<EXP_TABLE> expTableList = new ArrayList<EXP_TABLE>();
	private List<MONSTER> monsterList = new ArrayList<MONSTER>();
	private List<PLAYER> playerList = new ArrayList<PLAYER>();
	private List<TOWN> townList = new ArrayList<TOWN>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home, container, false);
		Context context = v.getContext();

		SQLiteDatabase db = new DaoMaster.DevOpenHelper(context, "Trilemma", null).getWritableDatabase();
		DaoSession daoSession = new DaoMaster(db).newSession();

		setViews(v);
		initDatabase(context, daoSession);

		mAdapter.add("海を泳いでいたらタコに襲われた！");

		mAdapter.add("dungeon_name@1st:" + dungeonList.get(0).getDungeon_name());
		mAdapter.add("necessary_exp@level10:" + expTableList.get(10).getNecessary_exp().toString());
		mAdapter.add("monster_name:" + monsterList.get(0).getMonster_name());
		mAdapter.add("player:" + playerList.get(0).getPlayer_name());
		mAdapter.add("town:" + townList.get(0).getTown_name());

		return v;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.HomeFragment_button2:
				mListener.onQuestStart();
				break;
			default:
				break;
		}
	}

	private void setViews(View v) {
		mButtonQuest = (Button) v.findViewById(R.id.HomeFragment_button2);
		mButtonQuest.setOnClickListener(this);

		mMessageWindow = (ListView) v.findViewById(R.id.HomeFragment_listView1);
		mAdapter = new ArrayAdapter<String>(v.getContext(), R.layout.list_row, list);
		mMessageWindow.setAdapter(mAdapter);
	}

	public void setOnQuestStartListener(OnQuestStartListener listener) {
		this.mListener = listener;
	}

	public interface OnQuestStartListener extends EventListener {
		void onQuestStart();
	}

	public void initDatabase(Context context, DaoSession daoSession) {
		DUNGEONDao dungeonDao = daoSession.getDUNGEONDao();
		EXP_TABLEDao expTableDao = daoSession.getEXP_TABLEDao();
		MONSTERDao monsterDao = daoSession.getMONSTERDao();
		PLAYERDao playerDao = daoSession.getPLAYERDao();
		TOWNDao townDao = daoSession.getTOWNDao();

		DUNGEON dungeonEntity = new DUNGEON();
		// dungeonEntity.setId((long) 1);
		dungeonEntity.setDungeon_name("初めの洞窟");

		int exp = -1;
		EXP_TABLE expTableEntity_0 = new EXP_TABLE();
		expTableEntity_0.setLevel(0);
		expTableEntity_0.setNecessary_exp(exp);
		expTableDao.insert(expTableEntity_0);
		for (int i = 1; i < 100; i++) {
			long x = (long) i;
			EXP_TABLE expTableEntity = new EXP_TABLE();
			expTableEntity.setLevel(i);
			expTableEntity.setNecessary_exp((exp + i) * 10);
			expTableDao.insert(expTableEntity);
		}

		MONSTER monsterEntity_1 = new MONSTER();
		// monsterEntity_1.setId((long) 1);
		monsterEntity_1.setMonster_name("スライム（茶）");
		monsterEntity_1.setMax_hp(50);
		monsterEntity_1.setMax_skill_point(3);
		monsterEntity_1.setAttack_power(3);
		monsterEntity_1.setDefense_power(2);
		monsterEntity_1.setProfit_exp(3);
		monsterEntity_1.setProfit_gold(4);
		MONSTER monsterEntity_2 = new MONSTER();
		// monsterEntity_2.setId((long) 2);
		monsterEntity_2.setMonster_name("洞窟の奥にいた大きなカニ");
		monsterEntity_2.setMax_hp(230);
		monsterEntity_2.setMax_skill_point(6);
		monsterEntity_2.setAttack_power(9);
		monsterEntity_2.setDefense_power(5);
		monsterEntity_2.setProfit_exp(60);
		monsterEntity_2.setProfit_gold(110);
		MONSTER monsterEntity_3 = new MONSTER();
		// monsterEntity_3.setId((long) 3);
		monsterEntity_3.setMonster_name("空の上にいる悪魔");
		monsterEntity_3.setMax_hp(300);
		monsterEntity_3.setMax_skill_point(8);
		monsterEntity_3.setAttack_power(16);
		monsterEntity_3.setDefense_power(14);
		monsterEntity_3.setProfit_exp(300);
		monsterEntity_3.setProfit_gold(300);

		PLAYER playerEntity = new PLAYER();
		// playerEntity.setId((long) 1);
		playerEntity.setPlayer_name("トリレンマ");
		playerEntity.setLevel(1);
		playerEntity.setMax_hp(100);
		playerEntity.setMax_skill_point(10);
		playerEntity.setAttack_power(8);
		playerEntity.setDefense_power(5);
		playerEntity.setCurrent_exp(0);
		playerEntity.setCurrent_gold(100);

		TOWN townEntity = new TOWN();
		// townEntity.setId((long) 1);
		townEntity.setTown_name("ビオフェルミン");

		dungeonDao.insert(dungeonEntity);
		monsterDao.insert(monsterEntity_1);
		monsterDao.insert(monsterEntity_2);
		monsterDao.insert(monsterEntity_3);
		playerDao.insert(playerEntity);
		townDao.insert(townEntity);

		this.dungeonList = dungeonDao.loadAll();
		this.expTableList = expTableDao.loadAll();
		this.monsterList = monsterDao.loadAll();
		this.playerList = playerDao.loadAll();
		this.townList = townDao.loadAll();
	}
}
