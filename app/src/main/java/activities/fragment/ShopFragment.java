package activities.fragment;

import java.util.List;

import org.w3c.dom.Text;

import com.games.Trilemma.R;

import dao.DaoManager;

import Trilemma.ARMOR;
import Trilemma.ARMOR_INVENTORY;
import Trilemma.WEAPON;
import Trilemma.WEAPON_INVENTORY;
import android.app.Dialog;
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
import android.widget.TextView;

public class ShopFragment extends Fragment implements OnClickListener, OnItemClickListener {
	ListView mListShopWeapon;
	ListView mListShopArmor;
	ListView mListInventory;
	ArrayAdapter<String> mAdapterShopWeapon;
	ArrayAdapter<String> mAdapterShopArmor;
	ArrayAdapter<String> mAdapterInventory;
	List<WEAPON> weaponList;
	List<ARMOR> armorList;
	List<WEAPON_INVENTORY> weaponInventoryList;
	List<ARMOR_INVENTORY> armorInventoryList;
	DaoManager dao;
	int inventoryStatus;
	Button mButtonWeapon;
	Button mButtonArmor;
	TextView mTextMessage;
	String firstMessage = "何か欲しい？";
	String errorMessage = "売却に失敗しました";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home_shop, container, false);
		dao = new DaoManager(v.getContext());

		setViews(v);
		setViewItems(v);
		return v;
	}

	private void setViews(View v) {
		mListShopWeapon = (ListView) v.findViewById(R.id.ShopFragment_listView_Weapon);
		mListShopArmor = (ListView) v.findViewById(R.id.ShopFragment_listView_Armor);
		mListInventory = (ListView) v.findViewById(R.id.ShopFragment_listView_inventory);

		mAdapterShopWeapon = new ArrayAdapter<>(v.getContext(), R.layout.list_row_chara_black);
		mAdapterShopArmor = new ArrayAdapter<>(v.getContext(), R.layout.list_row_chara_black);
		mAdapterInventory = new ArrayAdapter<>(v.getContext(), R.layout.list_row_chara_black);

		mListShopWeapon.setAdapter(mAdapterShopWeapon);
		mListShopArmor.setAdapter(mAdapterShopArmor);
		mListInventory.setAdapter(mAdapterInventory);

		mListShopWeapon.setOnItemClickListener(this);
		mListShopArmor.setOnItemClickListener(this);
		mListInventory.setOnItemClickListener(this);

		mButtonWeapon = (Button) v.findViewById(R.id.ShopFragment_button_set_inventory_weapon);
		mButtonArmor = (Button) v.findViewById(R.id.ShopFragment_button_set_inventory_armor);

		mButtonWeapon.setOnClickListener(this);
		mButtonArmor.setOnClickListener(this);

		mTextMessage = (TextView) v.findViewById(R.id.ShopFragment_message);
		resetMessage();
	}

	private void setViewItems(View v) {
		weaponList = dao.session.getWEAPONDao().loadAll();
		armorList = dao.session.getARMORDao().loadAll();

		for (int i = 0; i < weaponList.size(); i++) {
			mAdapterShopWeapon.add(weaponList.get(i).getWeapon_name());
		}
		for (int i = 0; i < armorList.size(); i++) {
			mAdapterShopArmor.add(armorList.get(i).getArmor_name());
		}

		setInventory();
	}

	private void setInventory() {
		weaponInventoryList = dao.session.getWEAPON_INVENTORYDao().loadAll();
		armorInventoryList = dao.session.getARMOR_INVENTORYDao().loadAll();

		mAdapterInventory.clear();
		if (inventoryStatus == 0) {
			for (int i = 0; i < weaponInventoryList.size(); i++) {
				mAdapterInventory.add(weaponInventoryList.get(i).getWEAPON().getWeapon_name());
			}
		} else {
			for (int i = 0; i < armorInventoryList.size(); i++) {
				mAdapterInventory.add(armorInventoryList.get(i).getARMOR().getArmor_name());
			}
		}
	}

	@Override
	public void onClick(View v) {
		mAdapterInventory.clear();
		switch (v.getId()) {
			case R.id.ShopFragment_button_set_inventory_weapon:
				inventoryStatus = 0;
				break;
			case R.id.ShopFragment_button_set_inventory_armor:
				inventoryStatus = 1;
				break;
			default:
				break;
		}
		setInventory();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		int viewId = parent.getId();

		switch (viewId) {
			case R.id.ShopFragment_listView_Weapon:
				WEAPON selectedWeapon = weaponList.get(position);
				buyWeapon(selectedWeapon);
				break;
			case R.id.ShopFragment_listView_Armor:
				ARMOR selectedArmor = armorList.get(position);
				buyArmor(selectedArmor);
				break;
			case R.id.ShopFragment_listView_inventory:
				sellInventory(position);
				break;
		}
		setInventory();
	}

	public void buyWeapon(WEAPON selectedWeapon) {
		dao.addWeapon(selectedWeapon.getId());
	}

	private void buyArmor(ARMOR selectedArmor) {
		// TODO 防具購入の処理を追加
		dao.addArmor(selectedArmor.getId());
	}

	private void sellInventory(int position) {
		// TODO 持ち物売却の処理を追加
		// TODO　売るものの種類を判定する必要あり？
		if (inventoryStatus == 0) {
			WEAPON_INVENTORY sellingWeapon = weaponInventoryList.get(position);
			if (dao.reduceWeapon(sellingWeapon.getWeapon_id())) {
				// TODO 武器のお金を取得するように変更する！
				dao.addGold(sellingWeapon.getWEAPON().getAttack_point());
				resetMessage();
			} else {
				feiledSell();
			}
		} else if (inventoryStatus == 1) {
			ARMOR_INVENTORY sellingArmor = armorInventoryList.get(position);
			if (dao.reduceArmor(sellingArmor.getArmor_id())) {
				// TODO 防具のお金を取得するように変更する！
				dao.addGold(sellingArmor.getARMOR().getDefense_point());
				resetMessage();
			} else {
				feiledSell();
			}
		} else {
			// TODO 他の種類のアイテムがあればここで売る処理を実装？
		}
		setInventory();
	}

	private void feiledSell() {
		// TODO 所持品売却に失敗した場合の処理
		mTextMessage.setText(errorMessage);
	}

	private void resetMessage() {
		mTextMessage.setText(firstMessage);
	}
}
