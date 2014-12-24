package activities.fragment;

import utility.ImageSelector;

import com.games.Trilemma.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PeopleFragment extends Fragment {
	Long id;
	int image_no;
	String name;
	String serif;

	ImageView mImage;
	TextView mName;
	TextView mSerif;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home_storylist_peoplelist_people, container, false);

		id = getArguments().getLong("id");
		image_no = getArguments().getInt("image_no");
		name = getArguments().getString("name");
		serif = getArguments().getString("serif");

		setView(v);

		return v;
	}

	private void setView(View v) {
		mImage = (ImageView) v.findViewById(R.id.PeopleFragment_peopleImage);
		mName = (TextView) v.findViewById(R.id.PeopleFragment_peopleName);
		mSerif = (TextView) v.findViewById(R.id.PeopleFragment_peopleSerif);

		mImage.setImageResource(ImageSelector.getPeopleImage(image_no));
		mName.setText(name);
		mSerif.setText(serif);
	}
}
