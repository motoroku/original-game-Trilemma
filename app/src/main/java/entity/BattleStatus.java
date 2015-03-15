package entity;

import android.app.Notification;

import entity.BattleStatus.SkillType;

/**
 * 戦闘関連で利用するenumクラスをまとめるクラス
 *
 * @author mori_yu
 */
public class BattleStatus {

    public static final String PLAYER = "player";
    public static final String ENEMY = "enemy";

    // ---------------------------------------------------
    // Enum
    public enum ActionStatus {
        攻撃("攻撃", 1), 防御("防御", 2), チャージ("チャージ", 3);

        private String type;
        private int id;

        private ActionStatus(String type, int id) {
            this.type = type;
            this.id = id;
        }

        public String getValue() {
            return type;
        }

        public int getId() {
            return id;
        }

        private String toValue() {
            return type;
        }

        public static ActionStatus fromValue(String type) {
            ActionStatus result = ActionStatus.攻撃;

            for (ActionStatus status : values()) {
                if (status.toValue().equals(type)) {
                    result = status;
                }
            }

            return result;
        }
    }

    // ---------------------------------------------------

    public enum BattleResult {
        win, lose, draw, clash
    }

    // ---------------------------------------------------

    public enum SkillType {
        NormalAttack("NormalAttack",1,ActionStatus.攻撃),
        Defense("Defense", 2,ActionStatus.防御),
        Charge("Charge",3,ActionStatus.チャージ),
        SpecialAttack("SpecialAttack",4,ActionStatus.攻撃);

        private String name;
        private int id;
        private ActionStatus action;

        private SkillType(String name, int id, ActionStatus action) {
            this.name = name;
            this.id = id;
            this.action = action;
        }

        public String getName(){return name;}
        public int getId(){return id;}
        public ActionStatus getActionStatus(){return action;}

        public static SkillType getType(String name) {
            if (name.equals(NormalAttack.getName())) {
                return NormalAttack;
            } else if (name.equals(SpecialAttack.getName())) {
                return SpecialAttack;
            } else if (name.equals(Defense.getName())) {
                return Defense;
            } else if (name.equals(Charge.getName())) {
                return Charge;
            } else {
                return Charge;
            }
        }
        }

    // ---------------------------------------------------

    public enum SelectedActionList {
        skill1 {
            public int no = 2;

            @Override
            public int getActionNo() {
                return no;
            }
        },
        skill2 {
            public int no = 3;

            @Override
            public int getActionNo() {
                return no;
            }

        },
        skill3 {
            public int no = 4;

            @Override
            public int getActionNo() {
                return no;
            }

        },
        skill4 {
            public int no = 5;

            @Override
            public int getActionNo() {
                return no;
            }

        },
        skill5 {
            public int no = 6;

            @Override
            public int getActionNo() {
                return no;
            }

        },
        defense {
            public int no = 0;

            @Override
            public int getActionNo() {
                return no;
            }

        },
        charge {
            public int no = 1;

            @Override
            public int getActionNo() {
                return no;
            }

        },;

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

    public enum AbilityType {
        statusUp, skillBoost, battleAssist
    }
}
