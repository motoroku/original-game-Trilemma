package system.battle;

public interface iEnemyAI {

	public int getEnemyAction();

	public int getEnemyAction(int attackRate, int defenseRate, int chargeRate);
}
