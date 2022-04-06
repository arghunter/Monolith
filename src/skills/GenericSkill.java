package skills;

import java.awt.Graphics;


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
		this.nextLevelXp=(int)Math.pow(2, tier+6);
		
	}
	public GenericSkill(StatType type,String name, boolean isActive) 
	{
		this.type=type;
		this.name=name;
		this.tier=-1;
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
	
	public abstract int apply(int value);
	public abstract void apply(StatType[] valueTypes, int[] values) ;
	public abstract void render(Graphics g,int x,int y);
	
	
	
	
	
	
	

	
	
	

}
