package skills;

import java.awt.Graphics;

public class Skill extends GenericSkill {
	GenericSkill skill;

	public Skill(StatType type,String name,int percent,int tier) {
		super(type,name,tier);
		switch(type) 
		{
		case ACCURACY:
			skill=new AccuracySkill(type,name, percent,tier);
			break;
		case ARMOR:
			skill=new ArmorSkill(type,name, percent,tier);
			break;
		case ATTACKSPEED:
			skill=new AttackSpeedSkill(type,name, percent,tier);
			break;
		case HEALTH:
			skill=new HealthSkill(type,name, percent,tier);
			break;
		case POWER:
			skill=new PowerSkill(type,name, percent,tier);
			break;
		case REGEN:
			skill=new RegenSkill(type,name, percent,tier);
			break;
		case SHIELD:
			skill=new ShieldSkill(type,name, percent,tier);
			break;
		case SPEED:
			skill=new SpeedSkill(type,name, percent,tier);
			break;
		case STRENGTH:
			skill=new StrengthSkill(type,name, percent,tier);
			break;
		case MISC:
			break;
		

		}
		
		
	}

	@Override
	public int apply(int value) {
		
		return skill.apply(value);
	}

	@Override
	public void render(Graphics g) {
		skill.render(g);
		
	}

	@Override
	public void apply(StatType[] valueTypes, int[] values) {
		// TODO Auto-generated method stub
		
	}
	
	

}
