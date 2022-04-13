package skills;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class MultipleSkill extends GenericSkill {
	GenericSkill[] skills;

	public MultipleSkill(StatType type,String name, StatType[] skillTypes,int[] values, int[] tiers,boolean isActive) {
		super(type,name,isActive);
		if(!(skillTypes.length==values.length&& values.length==tiers.length)) 
		{
			throw new IllegalArgumentException("skillTypes and values must be the same length");
		}
		this.skills=new Skill[skillTypes.length];
		for(int i=0;i<skills.length;i++) 
		{
			this.skills[i]=new Skill(skillTypes[i],""+i,values[i],tiers[i],isActive);
		}
		
	}

	@Override
	//Do not touch this method. Never call it.
	public int apply(int value) {
		// TODO Auto-generated method stub
		return 0;
	}
	public GenericSkill[] getSkills() 
	{
		return skills;
	}
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

	@Override
	public void render(Graphics2D g,int x,int y) {
		// TODO Auto-generated method stub
		
	}

}
