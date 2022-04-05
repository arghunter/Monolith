package skills;

import java.awt.Graphics;

public class MultipleSkill extends GenericSkill {
	Skill[] skills;

	public MultipleSkill(SkillType type,String name, SkillType[] skillTypes,int[] values, int[] tiers) {
		super(type,name);
		if(!(skillTypes.length==values.length&& values.length==tiers.length)) 
		{
			throw new IllegalArgumentException("skillTypes and values must be the same length");
		}
		this.skills=new Skill[skillTypes.length];
		for(int i=0;i<skills.length;i++) 
		{
			this.skills[i]=new Skill(skillTypes[i],""+i,values[i],tiers[i]);
		}
		
	}

	@Override
	//Do not touch this method. Never call it.
	public int apply(int value) {
		// TODO Auto-generated method stub
		return 0;
	}
	public void apply(SkillType[] valueTypes, int[] values) 
	{
		if(valueTypes.length!=values.length) 
		{
			throw new IllegalArgumentException("valueTypes and values must be the same length");
		}
		for(int i=0;i<skills.length;i++) 
		{
			for(int j=0;j<valueTypes.length;j++) 
			{
				if(valueTypes[j]==skills[i].type) 
				{
					values[j]=skills[i].apply(values[j]);
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
