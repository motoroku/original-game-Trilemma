package activities.fragment;

import java.util.EventListener;

import utility.ImageSelector;

import com.games.Trilemma.R;

import dao.DaoManager;

import Trilemma.PEOPLE;
import Trilemma.TOWN;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PeopleFragment extends Fragment implements OnClickListener {
	Long id;
	int image_no;
	String name;
	String serif;

	TOWN town;

	ImageView mImage;
	TextView mName;
	TextView mSerif;
	Button mButtonTalk;
	Button mButtonLeave;

	OnMeetPeopleListener mMeetPeopleListener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home_storylist_peoplelist_people, container, false);

		id = getArguments().getLong("id");
		image_no = getArguments().getInt("image_no");
		name = getArguments().getString("name");
		serif = getArguments().getString("serif");

		setView(v);

		DaoManager dao = new DaoManager(v.getContext());
		PEOPLE people = new PEOPLE(id);
		town = dao.getTownFromPeople(people);

		return v;
	}

	private void setView(View v) {
		mImage = (ImageView) v.findViewById(R.id.PeopleFragment_peopleImage);
		mName = (TextView) v.findViewById(R.id.PeopleFragment_peopleName);
		mSerif = (TextView) v.findViewById(R.id.PeopleFragment_peopleSerif);
		mButtonTalk = (Button) v.findViewById(R.id.PeopleFragment_actionButton_talk);
		mButtonLeave = (Button) v.findViewById(R.id.PeopleFragment__actionButton_leave);

		mImage.setImageResource(ImageSelector.getPeopleImage(image_no));
		mName.setText(name);
		mSerif.setText(serif);
		mButtonTalk.setOnClickListener(this);
		mButtonLeave.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.PeopleFragment_actionButton_talk:

				break;
			case R.id.PeopleFragment__actionButton_leave:
				mMeetPeopleListener.onLeave(town);
				break;
		}

	}

	public void setOnMeetPeopleListener(OnMeetPeopleListener listener) {
		this.mMeetPeopleListener = listener;
	}

	public interface OnMeetPeopleListener extends EventListener {
		void onLeave(TOWN town);
	}
}
