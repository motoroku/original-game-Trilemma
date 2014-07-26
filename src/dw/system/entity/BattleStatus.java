package dw.system.entity;

/**
 * 戦闘関連で利用するenumクラスをまとめるクラス
 * @author mori_yu
 * 
 */
public class BattleStatus {

	public static String PLAYER = "player";
	public static String NPC = "npc";

	// ---------------------------------------------------
	// Enum
	public enum ActionStatus {
		攻撃 {
			public String value = "Attack";

			@Override
			public String getActionStatusName() {
				return "Attack";
			}
		},
		防御 {
			public String value = "Deffence";

			@Override
			public String getActionStatusName() {
				return "Deffense";
			}
		},
		チャージ {
			public String value = "Charge";

			@Override
			public String getActionStatusName() {
				return "Charge";
			}
		};
		public abstract String getActionStatusName();
	}

	public enum BattleResult {
		win, lose, draw, clash
	}

	public enum AttackSkillType {
		Normal, Special
	}

	// ---------------------------------------------------

}
