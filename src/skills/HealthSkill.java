package skills;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class HealthSkill extends GenericSkill{
	private int health;
	private int modifiedHealth;

	public HealthSkill(StatType type,String name,int health,int tier,boolean isActive) {
		super(type, name, tier,isActive);
		this.health=health;
		
		this.modifiedHealth=health*(tier);
	}

	@Override
	public int apply(int health) {
		System.out.println((1.0+modifiedHealth/100.0));
		return (int)(health*(1.0+modifiedHealth/100.0)+0.5);
	}

	@Override
	public void render(Graphics2D g,int x,int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void apply(StatType[] valueTypes, int[] values) {
		// TODO Auto-generated method stub
		
	}

	

}
