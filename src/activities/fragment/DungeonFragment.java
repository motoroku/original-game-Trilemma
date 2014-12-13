package activities.fragment;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import com.games.Trilemma.R;

import dao.DaoManager;

import Trilemma.DUNGEON;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class DungeonFragment extends Fragment implements OnClickListener, OnItemClickListener {

	OnBattleStartListener mBattleStartListener;

	Button mButtonEnemyA;
	Button mButtonEnemyB;
	Button mButtonExit;
	ListView mDungeonList;
	ArrayAdapter<String> dungeonAdapter;
	List<DUNGEON> dungeonList = new ArrayList<DUNGEON>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home_dungeon, container, false);

		setViews(v);
		setDungeon(v);

		return v;
	}

	private void setDungeon(View v) {
		DaoManager dao = new DaoManager(v.getContext());
		dungeonList = dao.session.getDUNGEONDao().getSession().loadAll(DUNGEON.class);
		for (int i = 0; i < dungeonList.size(); i++) {
			dungeonAdapter.add(dungeonList.get(i).getDungeon_name());
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.DungeonFragment_button1:
				// �GA�{�^��
				mBattleStartListener.onStartBattle(new DUNGEON((long) 1, "test"));
				break;
			case R.id.DungeonFragment_button2:
				// �GB�{�^��
				break;
			default:
				break;
		}
	}

	private void setViews(View v) {
		mButtonEnemyA = (Button) v.findViewById(R.id.DungeonFragment_button1);
		mButtonEnemyB = (Button) v.findViewById(R.id.DungeonFragment_button2);

		mButtonEnemyA.setOnClickListener(this);
		mButtonEnemyB.setOnClickListener(this);

		mDungeonList = (ListView) v.findViewById(R.id.DungeonFragment_dungeonList);
		dungeonAdapter = new ArrayAdapter<>(v.getContext(), R.layout.list_row);
		mDungeonList.setAdapter(dungeonAdapter);
		mDungeonList.setOnItemClickListener(this);
	}

	public void setOnBattleStartListener(OnBattleStartListener listener) {
		this.mBattleStartListener = listener;
	}

	public interface OnBattleStartListener extends EventListener {
		void onStartBattle(DUNGEON dungeon);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		DUNGEON selectedDungeon = dungeonList.get(position);
		mBattleStartListener.onStartBattle(selectedDungeon);
	}
}
