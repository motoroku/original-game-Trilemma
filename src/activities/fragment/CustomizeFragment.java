package activities.fragment;

import java.util.List;

import com.games.Trilemma.R;

import dao.DaoManager;
import dao.PlayerDto;

import entity.Player;

import Trilemma.LEARNED_SKILL;
import Trilemma.SKILL;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CustomizeFragment extends Fragment implements OnClickListener {
	public Player player;
	private DaoManager dao;

	private Context mContext;

	public TextView mTextPlayerName;
	public TextView mTextLevel;
	public TextView mTextHp;
	public TextView mTextSp;
	public TextView mTextAttack;
	public TextView mTextDefense;
	public TextView mTextWeapon;
	public TextView mTextArmor;

	public Button mWeaponButton;
	public Button mArmorButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home_customize, container, false);
		mContext = v.getContext();

		setViews(v);
		dao = new DaoManager(mContext);
		createPlayer(dao, mContext);
		setPlayerValue();

		return v;
	}

	private void setViews(View v) {
		mTextPlayerName = (TextView) v.findViewById(R.id.CustomizeFragment_textView_PlayerName);
		mTextLevel = (TextView) v.findViewById(R.id.CustomizeFragment_textView_Level);
		mTextHp = (TextView) v.findViewById(R.id.CustomizeFragment_textView_Hp);
		mTextSp = (TextView) v.findViewById(R.id.CustomizeFragment_textView_Sp);
		mTextAttack = (TextView) v.findViewById(R.id.CustomizeFragment_textView_Attack);
		mTextDefense = (TextView) v.findViewById(R.id.CustomizeFragment_textView_Defense);
		mTextWeapon = (TextView) v.findViewById(R.id.CustomizeFragment_textView_Weapon);
		mTextArmor = (TextView) v.findViewById(R.id.CustomizeFragment_textView_Armor);

		mWeaponButton = (Button) v.findViewById(R.id.CustomizeFragment_button_weaponChange);
		mArmorButton = (Button) v.findViewById(R.id.CustomizeFragment_button_armorChange);

		mWeaponButton.setOnClickListener(this);
		mArmorButton.setOnClickListener(this);
	}

	private void createPlayer(DaoManager dao, Context context) {
		int width;
		int height;

		Bitmap image = BitmapFactory.decodeResource(context.getResources(), R.drawable.player_01);

		width = image.getWidth();
		height = image.getHeight();

		// mImageViewPlayer.getLayoutParams().width = width * 2;
		// mImageViewPlayer.getLayoutParams().height = height * 2;

		SKILL defense = dao.getDefaultDefenseSkill();
		SKILL charge = dao.getDefaultChargeSkill();

		List<LEARNED_SKILL> playerLearnedSkillList = dao.getPlayerLearnedSkill();
		List<SKILL> playerSkillList = dao.getPlayerSkillList(playerLearnedSkillList);

		PlayerDto playerDto = dao.getPlayerDto();

		Player player = new Player(playerDto, playerLearnedSkillList, playerSkillList, defense, charge);

		this.player = player;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.CustomizeFragment_button_weaponChange:
				dao.changeNextWeapon();
				break;
			case R.id.CustomizeFragment_button_armorChange:
				dao.changeNextArmor();
				break;
		}
		createPlayer(dao, mContext);
		setPlayerValue();
	}

	public void setPlayerValue() {
		mTextPlayerName.setText("NAME:" + player.name);
		mTextLevel.setText("LEVEL:" + String.valueOf(player.level));
		mTextHp.setText("HP:" + String.valueOf(player.hp));
		mTextSp.setText("SP:" + String.valueOf(player.currentSp) + "/" + String.valueOf(player.maxSp));
		mTextAttack.setText("ATTACK:" + String.valueOf(player.attack));
		mTextDefense.setText("DEFENSE:" + String.valueOf(player.defense));
		mTextWeapon.setText("WEAPON:" + player.weapon.weaponName);
		mTextArmor.setText("DEFENSE:" + player.armor.armorName);
	}
}
