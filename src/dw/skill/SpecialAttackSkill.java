package dw.skill;

import dw.system.entity.BattleStatus.ActionStatus;
import dw.system.entity.BattleStatus.AttackSkillType;
import dw.system.entity.BattleStatus.BattleResult;
import dw.system.entity.CharacterEntity;

public class SpecialAttackSkill extends AttackSkill {
	public SpecialAttackSkill() {
		super();
		actionStatus = ActionStatus.çUåÇ;
		type = AttackSkillType.Special;
	}

	public SpecialAttackSkill(String actor, int damege, int point) {
		super(actor, damege, point);
		type = AttackSkillType.Special;
	}
}
