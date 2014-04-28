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
			public String ActionStatusName() {
				return "Attack";
			}
		},
		ñhå‰ {
			public String value = "Deffence";

			@Override
			public String ActionStatusName() {
				return "Deffense";
			}
		},
		É`ÉÉÅ[ÉW {
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
