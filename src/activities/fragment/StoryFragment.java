package activities.fragment;

import listener.OnSelectedHomeMenuListener;

import com.games.Trilemma.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class StoryFragment extends Fragment implements OnClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home_story, container, false);

		setViews(v);

		return v;
	}

	private void setViews(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
}
