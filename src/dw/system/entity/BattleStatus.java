package dw.system.entity;

/**
 * �퓬�֘A�ŗ��p����enum�N���X���܂Ƃ߂�N���X
 * @author mori_yu
 * 
 */
public class BattleStatus {

	public static String PLAYER = "player";
	public static String NPC = "npc";

	// ---------------------------------------------------
	// Enum
	public enum ActionStatus {
		�U�� {
			public String value = "Attack";

			@Override
			public String getActionStatusName() {
				return value;
			}
		},
		�h�� {
			public String value = "Deffence";

			@Override
			public String getActionStatusName() {
				return value;
			}
		},
		�`���[�W {
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
