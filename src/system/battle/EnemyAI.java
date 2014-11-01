package system.battle;

import java.util.Random;

import entity.BattleStatus.SelectedActionList;

public class EnemyAI implements iEnemyAI {
	private Random random = new Random();

	@Override
	public int getEnemyAction() {
		int num = getRandomNum(3, false);
		if (num == 1) {
			num = getRandomNum(3, true);
		} else if (num == 2) {
			num = SelectedActionList.defense.getActionNo();
		} else if (num == 3) {
			num = SelectedActionList.charge.getActionNo();
		}

		return num;
	}

	private int getRandomNum(int max, boolean isZero) {
		int num;
		if (isZero) {
			num = random.nextInt(max);
		} else {
			num = random.nextInt(max) + 1;
		}
		return num;
	}

	@Override
	public int getEnemyAction(int attackRate, int defenseRate, int chargeRate) {
		attackRate = attackRate * getRandomNum(3, false);
		defenseRate = defenseRate * getRandomNum(3, false);
		chargeRate = chargeRate * getRandomNum(3, false);

		return 0;
	}

}
