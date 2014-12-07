package activities.fragment;

import java.util.ArrayList;
import java.util.List;

import listener.OnSelectedHomeMenuListener;
import listener.OnSelectedStoryListener;

import com.games.Trilemma.R;

import dao.DaoManager;

import Trilemma.TOWN;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class StoryListFragment extends Fragment implements OnClickListener, OnItemClickListener {
	OnSelectedStoryListener mSelectedStorylistener;

	ListView mStoryList;
	ArrayAdapter<String> storyAdapter;
	List<TOWN> townList = new ArrayList<TOWN>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home_storylist, container, false);

		setViews(v);
		setStory(v);

		return v;
	}

	private void setStory(View v) {
		// Ç±Ç±Ç≈listviewÇÃíÜêgÇãlÇﬂÇÈÅB
		DaoManager dao = new DaoManager(v.getContext());
		townList = dao.session.getTOWNDao().getSession().loadAll(TOWN.class);
		for (int i = 0; i < townList.size(); i++) {
			storyAdapter.add(townList.get(i).getTown_name());
		}
	}

	private void setViews(View v) {
		mStoryList = (ListView) v.findViewById(R.id.StoryListFragment_List);
		storyAdapter = new ArrayAdapter<>(v.getContext(), R.layout.list_row);
		mStoryList.setAdapter(storyAdapter);
		mStoryList.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		TOWN selectedTown = townList.get(position);
		mSelectedStorylistener.OnSelectedTown(selectedTown);
	}

	public void setOnStoryListener(OnSelectedStoryListener listener) {
		this.mSelectedStorylistener = listener;
	}
}
