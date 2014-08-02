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
				return value;
			}
		},
		防御 {
			public String value = "Deffence";

			@Override
			public String getActionStatusName() {
				return value;
			}
		},
		チャージ {
			public String value = "Charge";

			@Override
			public String getActionStatusName() {
				return value;
			}
		};
		public abstract String getActionStatusName();
	}

	// ---------------------------------------------------

	public enum BattleResult {
		win, lose, draw, clash
	}

	// ---------------------------------------------------

	public enum SkillType {
		NormalAttack {
			public int no = 1;

			@Override
			public int getNo() {
				return no;
			}
		},
		SpecialAttack {
			public int no = 2;

			@Override
			public int getNo() {
				return no;
			}
		};
		public abstract int getNo();
	}

	// ---------------------------------------------------
}
