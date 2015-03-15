package activities.fragment;

import java.util.EventListener;

import com.games.Trilemma.R;

import dao.DaoManager;
import data.DataManager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartFragment extends Fragment implements OnClickListener {

	OnGameStartListener mListener;
	Button mButtonGameStart;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home_start, container, false);
		Context context = getActivity();

		setViews(v);

		return v;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.StartFragment_button_start:
				dataLoad();
                mListener.onStartGame();
				break;

			default:
				break;
		}

	}

    private void dataLoad() {
        DataManager dataManager = new DataManager(getActivity());
        dataManager.setFirstSkill();
    }

    private void setViews(View v) {
		mButtonGameStart = (Button) v.findViewById(R.id.StartFragment_button_start);

		mButtonGameStart.setOnClickListener(this);
	}

	private void initDb(DaoManager dao) {

	}

	public void setOnGameStartListener(OnGameStartListener listener) {
		this.mListener = listener;
	}

	public interface OnGameStartListener extends EventListener {
		void onStartGame();
	}
}
