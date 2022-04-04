package skills;

public class Skill extends GenericSkill {
	GenericSkill skill;

	public Skill(SkillType type,int percent,int tier) {
		super(type);
		switch(type) 
		{
		case ACCURACY:
			skill=new AccuracySkill(type,percent,tier);
			break;
		case ARMOR:
			skill=new ArmorSkill(type,percent,tier);
			break;
		case ATTACKSPEED:
			skill=new AttackSpeedSkill(type,percent,tier);
			break;
		case HEALTH:
			skill=new HealthSkill(type,percent,tier);
			break;
		case POWER:
			skill=new PowerSkill(type,percent,tier);
			break;
		case REGEN:
			skill=new RegenSkill(type,percent,tier);
			break;
		case SHIELD:
			skill=new ShieldSkill(type,percent,tier);
			break;
		case SPEED:
			skill=new SpeedSkill(type,percent,tier);
			break;
		case STRENGTH:
			skill=new StrengthSkill(type,percent,tier);
			break;
		case MISC:
			break;
		

		}
		
		
	}

	@Override
	public int apply(int value) {
		
		return skill.apply(value);
	}
	
	

}
