//Author: Armaan Gomes
//Date: 5/8/22
//Rev: 01
//Notes: Represents a skill that modifies multiple values
package skills;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class MultipleSkill extends GenericSkill {
	Skill[] skills;//Array of the skills that this multipleSkill has

	//Constructor
	public MultipleSkill(StatType type,String name, StatType[] skillTypes,int[] values, int tier,boolean isActive) {
		super(type,name,tier,isActive);
		if(!(skillTypes.length==values.length)) 
		{
			throw new IllegalArgumentException("skillTypes and values must be the same length");
		}
		this.skills=new Skill[skillTypes.length];
		for(int i=0;i<skills.length;i++) 
		{
			this.skills[i]=new Skill(skillTypes[i],""+i,values[i],tier,isActive);
			
		}
		
	}
	//Constructor
	public MultipleSkill(StatType type,String name,int tier, boolean isActive,Skill[] skills) 
	{
		super(type,name,tier,isActive);
		this.skills=skills;
	}
	
	@Override 
	//Overridden setisActive 
	public void setIsActive(boolean isActive) 
	{
		super.setIsActive(isActive);
		for(int i=0;i<skills.length;i++) 
		{
			skills[i].setIsActive(isActive);
		}
	}

	@Override
	//Dead apply
	public int apply(int value) {
		// TODO Auto-generated method stub
		return 0;
	}
	//Returns the skills that this multipleSkill object has
	public Skill[] getSkills() 
	{
		return skills;
	}
	//Applies this skill to an array of stats
	public void apply(StatType[] valueTypes,int[] modifiedStats) 
	{
		if(!(valueTypes.length==modifiedStats.length)) 
		{
			throw new IllegalArgumentException("valueTypes, stats, and modifiedStats must be the same length");
		}
		for(int i=0;i<skills.length;i++) 
		{
			skills[skills.length-1].setTier(this.getTier());
			for(int j=0;j<valueTypes.length;j++) 
			{
				if(valueTypes[j]==skills[i].getType()) 
				{
					modifiedStats[j]=skills[i].apply(modifiedStats[j]);
				}
			}
		}
	}



}
