package utility;

import entity.CharacterEntity;
import entity.Skill;

public class Cal {

	public static int calSkillMight(CharacterEntity chara, Skill skill) {
		int result = 0;

		int level = chara.level;
		int attack = chara.attack;
		double sAttack = skill.effectPoint;

		result = (int) ((level + attack) * sAttack);

		return result;
	}

	public static int calEffectPoint(int attack, int defense) {
		int result = 0;

		result = attack - defense;
		if (result < 0) {
			result = 0;
		}

		return result;
	}
}
