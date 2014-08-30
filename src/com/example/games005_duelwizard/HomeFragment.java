package com.example.games005_duelwizard;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home, container, false);

		setViews(v);

		mAdapter.add("äCÇâjÇ¢Ç≈Ç¢ÇΩÇÁÉ^ÉRÇ…èPÇÌÇÍÇΩÅI");

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
}
