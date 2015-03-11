package entity;

import Trilemma.WEAPON;

public class Weapon {
	public int weaponId;
	public String weaponName;
	public int attackPower;

	public Weapon(WEAPON weapon) {
		weaponId = weapon.getId().intValue();
		weaponName = weapon.getWeapon_name();
		attackPower = weapon.getAttack_point();
	}

}
