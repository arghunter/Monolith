package skills;

public class MultipleSkill extends GenericSkill {
	GenericSkill[] skills;

	public MultipleSkill(SkillType type, SkillType[] skillTypes,int[] values, int[] tiers) {
		super(type);
		if(!(skillTypes.length==values.length&& values.length==tiers.length)) 
		{
			throw new IllegalArgumentException("skillTypes and values must be the same length");
		}
		this.skills=new GenericSkill[skillTypes.length];
		for(int i=0;i<skills.length;i++) 
		{
			this.skills[i]=new Skill(skillTypes[i],values[i],tiers[i]);
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
	}

}
