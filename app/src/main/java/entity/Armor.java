package entity;

import Trilemma.ARMOR;

public class Armor {
	public int armorId;
	public String armorName;
	public int defensePower;

	public Armor(ARMOR armor) {
		armorId = armor.getId().intValue();
		armorName = armor.getArmor_name();
		defensePower = armor.getDefense_point();
	}

}
