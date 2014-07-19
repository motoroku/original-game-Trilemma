package dw.system.entity;

public class BattleStatus {

	public static String PLAYER = "player";
	public static String NPC = "npc";

	// ---------------------------------------------------
	// Enum
	public enum ActionStatus {
		çUåÇ {
			public String value = "Attack";

			@Override
			public String getActionStatusName() {
				return "Attack";
			}
		},
		ñhå‰ {
			public String value = "Deffence";

			@Override
			public String getActionStatusName() {
				return "Deffense";
			}
		},
		É`ÉÉÅ[ÉW {
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
