package activities.fragment;

import java.util.ArrayList;
import java.util.List;

import listener.OnSelectedHomeMenuListener;

import com.games.Trilemma.R;

import dao.DaoManager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class HomeFragment extends Fragment implements OnClickListener, OnItemClickListener {

	OnSelectedHomeMenuListener mSelectedHomeMenuListener;

	private Button mButtonStory;
	private Button mButtonAdventure;
	private Button mButtonCustomize;
	private Button mButtonShop;
	private Button mButtonSetting;
	private ListView mMessageWindow;
	private ArrayAdapter<String> mAdapter;

	private List<String> list = new ArrayList<String>();

	private int count = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home, container, false);
		Context context = v.getContext();

		setViews(v);
		DaoManager daoManager = new DaoManager(context);

		mAdapter.add("äCÇâjÇ¢Ç≈Ç¢ÇΩÇÁÉ^ÉRÇ…èPÇÌÇÍÇΩÅI\n");

		return v;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.HomeFragment_radio0_story:
				mSelectedHomeMenuListener.onSelectedStoryList();
				break;
			case R.id.HomeFragment_radio1_adventure:
				mSelectedHomeMenuListener.onSelectedAdventure();
				break;
			case R.id.HomeFragment_radio2_customize:
				mSelectedHomeMenuListener.onSelectedCustomize();
				break;
			case R.id.HomeFragment_radio3_shop:
				mSelectedHomeMenuListener.onSelectedShop();
				break;
			case R.id.HomeFragment_radio4_setting:
				mSelectedHomeMenuListener.onSelectedSetting();
				break;
			default:
				break;
		}
	}

	private void setViews(View v) {
		mButtonStory = (Button) v.findViewById(R.id.HomeFragment_radio0_story);
		mButtonStory.setOnClickListener(this);
		mButtonAdventure = (Button) v.findViewById(R.id.HomeFragment_radio1_adventure);
		mButtonAdventure.setOnClickListener(this);
		mButtonCustomize = (Button) v.findViewById(R.id.HomeFragment_radio2_customize);
		mButtonCustomize.setOnClickListener(this);
		mButtonShop = (Button) v.findViewById(R.id.HomeFragment_radio3_shop);
		mButtonShop.setOnClickListener(this);
		mButtonSetting = (Button) v.findViewById(R.id.HomeFragment_radio4_setting);
		mButtonSetting.setOnClickListener(this);

		mMessageWindow = (ListView) v.findViewById(R.id.HomeFragment_listView1);
		mAdapter = new ArrayAdapter<String>(v.getContext(), R.layout.list_row, list);
		mMessageWindow.setAdapter(mAdapter);
		mMessageWindow.setOnItemClickListener(this);

	}

	public void setOnHomeMenuListener(OnSelectedHomeMenuListener listener) {
		this.mSelectedHomeMenuListener = listener;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		ListView list = (ListView) parent;

		String selectedItem = (String) list.getItemAtPosition(position);
		count++;
		mAdapter.add(selectedItem + Integer.toString(count));
	}
}
