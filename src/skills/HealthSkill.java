package skills;

import java.awt.Graphics;

public class HealthSkill extends GenericSkill{
	private int health;
	private int modifiedHealth;

	public HealthSkill(SkillType type,String name,int health,int tier) {
		super(type, name, tier);
		this.health=health;
		this.tier=tier;
		this.modifiedHealth=health*(int)(tier/2.0+0.5);
	}

	@Override
	public int apply(int health) {
		return (int)(health*(1.0+modifiedHealth/100)+0.5);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
