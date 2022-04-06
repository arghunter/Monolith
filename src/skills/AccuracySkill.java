package skills;

import java.awt.Graphics;

public class AccuracySkill extends GenericSkill {
	private int accuracy;
	private int modifiedAccuracy;
	public AccuracySkill(StatType type,String name, int accuracy, int tier,boolean isActive) {
		super(type, name, tier, isActive);
		this.accuracy=accuracy;
		this.modifiedAccuracy=accuracy*tier;
		
	}
	public int getAccuracy() {
		return accuracy;
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
	@Override
	public void render(Graphics g,int x,int y) {
		
		
	}
	@Override
	public void apply(StatType[] valueTypes, int[] values) {
		// TODO Auto-generated method stub
		
	}

}
