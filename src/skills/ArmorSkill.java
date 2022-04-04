package skills;

public class ArmorSkill extends GenericSkill {
	private int armor;
	private int tier;
	private int modifiedArmor;

	public ArmorSkill(SkillType type, int armor,int tier) {
		super(type);
		this.armor=armor;
		this.tier=tier;
		this.modifiedArmor=armor*(int)(tier/2+0.5);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int apply(int armor) {
		return (int)(armor*(1+modifiedArmor/100.0)+0.5);
		
	}

}
