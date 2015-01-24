package utility;

import Trilemma.ARMOR;
import Trilemma.WEAPON;

public class Const_Item {
	public static WEAPON getNoWeapon() {
		return new WEAPON((long) 0, "素手", 1);
	}

	public static ARMOR getNoArmor() {
		return new ARMOR((long) 0, "寝間着", 1);
	}

}
