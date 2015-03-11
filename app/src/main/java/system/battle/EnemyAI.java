package system.battle;

import utility.Utility;

import entity.BattleStatus;
import entity.BattleStatus.SelectedActionList;
import entity.Enemy;

public class EnemyAI implements iEnemyAI {

	@Override
	public int getEnemyAction(int attackRate, int defenseRate, int chargeRate, BattleElements elements) {
		int result = SelectedActionList.charge.getActionNo();
		int size = elements.getEnemy().skillList.length - 2;

		int temp = 0;
		for (int i = 0; i < 10; i++) {
			temp = Utility.getRandomNum(attackRate + defenseRate + chargeRate, false);
		}

		if (temp < attackRate) {
			result = BattleStatus.getSelectedAction(Utility.getRandomNum(size - 1, true)).getActionNo();
		} else if (temp < attackRate + defenseRate) {
			result = SelectedActionList.defense.getActionNo();
		} else if (temp < attackRate + defenseRate + chargeRate) {
			result = SelectedActionList.charge.getActionNo();
		}
		return result;
	}
}
