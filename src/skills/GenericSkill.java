package skills;

import java.awt.Graphics;

enum SkillType
 {
	 SPEED,
	 STRENGTH,
	 ACCURACY,
	 HEALTH,
	 REGEN,
	 SHIELD,
	 ARMOR,
	 ATTACKSPEED,
	 POWER,
	 MULTIPLE,
	 MISC
 }
public abstract class GenericSkill {
	SkillType type;
	String name;
	int tier;
	//Fields Percent increase/decrease
//	private int speed;
//	private int strength;//Affects melee weapons
//	private int accuracy;
//	private int health;
//	private int shield;
//	private int armor;
//	private int attackspeed;//In attacks per minute for my sanity
//	private int power;//Affects melee and ranged weapons
	
	
	public GenericSkill(SkillType type,String name,int tier) 
	{
		this.type=type;
		this.name=name;
		this.tier=tier;
	}
	public GenericSkill(SkillType type,String name) 
	{
		this.type=type;
		this.name=name;
		this.tier=-1;
	}
	
	
	
	public SkillType getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public int getTier() {
		return tier;
	}
	
	public abstract int apply(int value);
	public abstract void render(Graphics g);
	
	
	
	
	
	
	

	
	
	

}
