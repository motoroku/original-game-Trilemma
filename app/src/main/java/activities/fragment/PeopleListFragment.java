package activities.fragment;

import java.util.ArrayList;
import java.util.List;

import com.games.Trilemma.R;

import dao.DaoManager;

import Trilemma.PEOPLE;
import Trilemma.PEOPLEDao.Properties;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PeopleListFragment extends Fragment implements OnItemClickListener {
	ListView mPeopleList;
	ArrayAdapter<String> peopleAdapter;
	List<PEOPLE> peopleList = new ArrayList<PEOPLE>();
	Long townId;
	String townName;

	OnSelectedPeopleListener mSelectedPeopleListener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home_storylist_peoplelist, container, false);

		townId = getArguments().getLong("id");
		townName = getArguments().getString("name");

		setViews(v);
		setPeople(v);

		return v;
	}

	private void setViews(View v) {
		mPeopleList = (ListView) v.findViewById(R.id.PeopleListFragment_list);
		peopleAdapter = new ArrayAdapter<String>(v.getContext(), R.layout.list_row_chara_black);
		mPeopleList.setAdapter(peopleAdapter);
		mPeopleList.setOnItemClickListener(this);
	}

	private void setPeople(View v) {
		DaoManager dao = new DaoManager(v.getContext());
		peopleList = dao.getPeopleList(townId);

		for (int i = 0; i < peopleList.size(); i++) {
			peopleAdapter.add(peopleList.get(i).getPeople_name());
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		PEOPLE people = peopleList.get(position);
		mSelectedPeopleListener.OnSelectedPeople(people);
	}

	public void setOnSelectedPeopleListener(OnSelectedPeopleListener listener) {
		this.mSelectedPeopleListener = listener;
	}

	public interface OnSelectedPeopleListener {
		void OnSelectedPeople(PEOPLE people);
	}

}
