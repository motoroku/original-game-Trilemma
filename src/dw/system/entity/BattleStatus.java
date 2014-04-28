package dw.system.entity;

public class BattleStatus {

	public static String PLAYER = "player";
	public static String NPC = "npc";

	// ---------------------------------------------------
	// Enum
	public enum ActionStatus {
		�U�� {
			public String value = "Attack";

			@Override
			public String ActionStatusName() {
				return "Attack";
			}
		},
		�h�� {
			public String value = "Deffence";

			@Override
			public String ActionStatusName() {
				return "Deffense";
			}
		},
		�`���[�W {
			public String value = "Charge";

			@Override
			public String ActionStatusName() {
				return "Charge";
			}
		};
		public abstract String ActionStatusName();
	}

	public enum BattleResult {
		win, lose, draw, clash
	}

	// ---------------------------------------------------

}
