//Author: Armaan Gomes
//Date: 5/8/22
//Rev:01
//Notes: Represents a generic skill that boosts play stats
package skills;

import java.awt.Graphics;
import java.awt.Graphics2D;


public abstract class GenericSkill {
	//Fields
	private StatType type;//What type of skill it boosts 
	private String name;//Skill name
	private int tier;//Skill tier
	private int xp;//current xp
	private int nextLevelXp;// xp till next level
	private boolean isActive;// if this skill is active
	

	
	//Construcotr
	public GenericSkill(StatType type,String name,int tier,boolean isActive) 
	{
		this.type=type;
		this.name=name;
		this.tier=tier;
		this.isActive=isActive;
		this.xp=0;
		this.nextLevelXp=(int)Math.pow(4, tier+6);
		
	}
 
	
	//Returns stat type
	public StatType getType() {
		return type;
	}
	//Returns this skill's name
	public String getName() {
		return name;
	}
	//Returns this skill's tier
	public int getTier() {
		return tier;
	}
    //Returns if this skill is Active
	public boolean getIsActive() 
	{
		return isActive;
	}
	//Sets isActive
	public void setIsActive(boolean isActive) 
	{
		this.isActive=isActive;
	}
	//Add XP to this skill. Throws an exception that tells the skill tree to update stats if it levels up
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
	//Sets this skill's Xp
	public void setXP(int xp) 
	{
		this.xp=xp;
	}
	//Returns this Skills xp
	public int getXP() 
	{
		return xp;
	}
	//Sets this skills tier
	public void setTier(int tier) 
	{
		this.tier=tier;
	}
	//Applies this skill 
	public abstract int apply(int value);
	//Alternative apply
	public abstract void apply(StatType[] valueTypes, int[] values) ;
	

}
