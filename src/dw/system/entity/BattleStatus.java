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
				return "Attack";
			}
		},
		�h�� {
			public String value = "Deffence";

			@Override
			public String getActionStatusName() {
				return "Deffense";
			}
		},
		�`���[�W {
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
