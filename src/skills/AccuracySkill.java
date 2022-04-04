package skills;

public class AccuracySkill extends GenericSkill {
	private int accuracy;
	private int tier;
	private int modifiedAccuracy;
	public AccuracySkill(SkillType type, int accuracy, int tier) {
		super(type);
		this.accuracy=accuracy;
		this.tier=tier;
		this.modifiedAccuracy=accuracy*(int)(tier/2+0.5);
		
	}
	public int getAccuracy() {
		return accuracy;
	}
	public int getTier() {
		return tier;
	}
	public int getModifiedAccuracy() {
		return modifiedAccuracy;
	}
	@Override
	public int apply(int accuracy) {
		int newAccuracy=(int)(accuracy*(modifiedAccuracy/100.0+1.0)+0.5);
		if(newAccuracy>100) 
		{
			newAccuracy=100;
		}
		return newAccuracy;
	
	}

}
