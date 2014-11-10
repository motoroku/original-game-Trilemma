package system.battle;

import entity.Enemy;

public interface iEnemyAI {

	public int getEnemyAction(int attackRate, int defenseRate, int chargeRate, BattleElements elements);

	public Enemy resetRate(Enemy enemy, BattleElements elements);
}
