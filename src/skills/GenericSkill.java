package skills;

import java.awt.Graphics;
import java.awt.Graphics2D;


public abstract class GenericSkill {
	private StatType type;
	private String name;
	private int tier;
	private int xp;
	private int nextLevelXp;
	private boolean isActive;
	
	//Fields Percent increase/decrease
//	private int speed;
//	private int strength;//Affects melee weapons
//	private int accuracy;
//	private int health;
//	private int shield;
//	private int armor;
//	private int attackspeed;//In attacks per minute for my sanity
//	private int power;//Affects melee and ranged weapons
	
	
	public GenericSkill(StatType type,String name,int tier,boolean isActive) 
	{
		this.type=type;
		this.name=name;
		this.tier=tier;
		this.xp=0;
		this.nextLevelXp=(int)Math.pow(8, tier+6);
		
	}

	
	
	public StatType getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public int getTier() {
		return tier;
	}
	public boolean getIsActive() 
	{
		return isActive;
	}
	public void setIsActive(boolean isActive) 
	{
		this.isActive=isActive;
	}
	public void addXP(int xp) throws SkillUpdateException 
	{
		this.xp+=xp;
		if(this.xp>=this.nextLevelXp) 
		{
			this.xp-=this.nextLevelXp;
			if(tier<10) 
			{
				tier++;
				throw new SkillUpdateException("Skill: "+name+" has been updated");
			}
		}
	}
	public void setXP(int xp) 
	{
		this.xp=xp;
	}
	public int getXP() 
	{
		return xp;
	}
	public void setTier(int tier) 
	{
		this.tier=tier;
	}
	
	public abstract int apply(int value);
	public abstract void apply(StatType[] valueTypes, int[] values) ;
	public abstract void render(Graphics2D g,int x,int y);
	
	
	
	
	
	
	

	
	
	

}
