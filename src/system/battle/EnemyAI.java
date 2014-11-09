package system.battle;

import utility.Utility;

import entity.BattleStatus;
import entity.BattleStatus.SelectedActionList;

public class EnemyAI implements iEnemyAI {

	@Override
	public int getEnemyAction() {
		int num = Utility.getRandomNum(3, false);
		if (num == 1) {
			num = BattleStatus.getSelectedAction(Utility.getRandomNum(2, true)).getActionNo();
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

		int temp = 0;
		for (int i = 0; i < 10; i++) {
			temp = Utility.getRandomNum(attackRate + defenseRate + chargeRate, false);
		}

		if (temp < attackRate) {
			result = BattleStatus.getSelectedAction(Utility.getRandomNum(2, true)).getActionNo();
		} else if (temp < attackRate + defenseRate) {
			result = SelectedActionList.defense.getActionNo();
		} else if (temp < attackRate + defenseRate + chargeRate) {
			result = SelectedActionList.charge.getActionNo();
		}
		return result;
	}
}
