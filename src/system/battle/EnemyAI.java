package system.battle;

import utility.Utility;

import entity.BattleStatus.SelectedActionList;

public class EnemyAI implements iEnemyAI {

	@Override
	public int getEnemyAction() {
		int num = Utility.getRandomNum(3, false);
		if (num == 1) {
			num = Utility.getRandomNum(2, true);
		} else if (num == 2) {
			num = SelectedActionList.defense.getActionNo();
		} else if (num == 3) {
			num = SelectedActionList.charge.getActionNo();
		}

		return num;
	}

	@Override
	public int getEnemyAction(int attackRate, int defenseRate, int chargeRate) {
		int result = SelectedActionList.charge.getActionNo();

		attackRate = attackRate * Utility.getRandomNum(attackRate, false);
		defenseRate = defenseRate * Utility.getRandomNum(defenseRate, false);
		chargeRate = chargeRate * Utility.getRandomNum(chargeRate, false);

		int temp = Utility.getHighestNum(attackRate, defenseRate, chargeRate);

		if (temp == attackRate) {
			result = Utility.getRandomNum(2, true);
		} else if (temp == defenseRate) {
			result = SelectedActionList.defense.getActionNo();
		} else if (temp == chargeRate) {
			result = SelectedActionList.charge.getActionNo();
		}

		return result;
	}

}
