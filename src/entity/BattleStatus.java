package entity;

/**
 * 戦闘関連で利用するenumクラスをまとめるクラス
 * @author mori_yu
 * 
 */
public class BattleStatus {

	public static final String PLAYER = "player";
	public static final String ENEMY = "enemy";

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
		Defense {
			public int no = -1;

			@Override
			public int getNo() {
				return no;
			}
		},
		Charge {
			public int no = 0;

			@Override
			public int getNo() {
				return no;
			}
		},
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

	public enum SelectedActionList {
		skill1 {
			public int no = 0;

			@Override
			public int getActionNo() {
				return no;
			}
		},
		skill2 {
			public int no = 1;

			@Override
			public int getActionNo() {
				return no;
			}

		},
		skill3 {
			public int no = 2;

			@Override
			public int getActionNo() {
				return no;
			}

		},
		skill4 {
			public int no = 3;

			@Override
			public int getActionNo() {
				return no;
			}

		},
		skill5 {
			public int no = 4;

			@Override
			public int getActionNo() {
				return no;
			}

		},
		defense {
			public int no = 5;

			@Override
			public int getActionNo() {
				return no;
			}

		},
		charge {
			public int no = 6;

			@Override
			public int getActionNo() {
				return no;
			}

		},
		;
		public abstract int getActionNo();
	}

	public static SelectedActionList getSelectedAction(int num) {
		SelectedActionList result = null;

		SelectedActionList.skill1.getActionNo();

		switch (num) {
			case 0:
				result = SelectedActionList.skill1;
				break;
			case 1:
				result = SelectedActionList.skill2;
				break;
			case 2:
				result = SelectedActionList.skill3;
				break;
			case 3:
				result = SelectedActionList.skill4;
				break;
			case 4:
				result = SelectedActionList.skill5;
				break;
			case 5:
				result = SelectedActionList.defense;
				break;
			case 6:
				result = SelectedActionList.charge;
				break;
			default:
				break;
		}

		return result;
	}

	public enum TargetStatus {
		self, enemy
	}
}
