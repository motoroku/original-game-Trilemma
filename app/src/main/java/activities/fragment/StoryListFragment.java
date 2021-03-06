package activities.fragment;

import java.util.ArrayList;
import java.util.List;

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

public class StoryListFragment extends Fragment implements OnItemClickListener {
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
		// ここでlistviewの中身を詰める。
		DaoManager dao = new DaoManager(v.getContext());
		townList = dao.session.getTOWNDao().loadAll();
		for (int i = 0; i < townList.size(); i++) {
			storyAdapter.add(townList.get(i).getTown_name());
		}
	}

	private void setViews(View v) {
		mStoryList = (ListView) v.findViewById(R.id.StoryListFragment_List);
		storyAdapter = new ArrayAdapter<>(v.getContext(), R.layout.list_row_chara_black);
		mStoryList.setAdapter(storyAdapter);
		mStoryList.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		TOWN selectedTown = townList.get(position);
		mSelectedStorylistener.OnSelectedTown(selectedTown);
	}

	public void setOnStoryListener(OnSelectedStoryListener listener) {
		this.mSelectedStorylistener = listener;
	}

	public interface OnSelectedStoryListener {
		void OnSelectedTown(TOWN town);
	}
}
