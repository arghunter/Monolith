package skills;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Skill extends GenericSkill {

	private int percent;
	private int modifiedPercent;

	public Skill(StatType type, String name, int percent, int tier, boolean isActive) {
		super(type, name, tier, isActive);
		this.percent = percent;
		this.modifiedPercent = percent * tier;

	}

	@Override
	public int apply(int value) {
		this.modifiedPercent= percent * super.getTier();
		switch (super.getType()) {
			case ACCURACY:
				int newAccuracy = (int) (value * (modifiedPercent / 100.0 + 1.0) + 0.5);
				if (newAccuracy > 100) {
					newAccuracy = 100;
				}
				return newAccuracy;

				 
			case ARMOR:
				return (int)(value*(1+modifiedPercent/100.0)+0.5);
				 
			case ATTACKSPEED:
			int newAttackSpeed=(int)(value*(modifiedPercent/100.0+1)+0.5);
			if(newAttackSpeed>2.5*value) 
			{
				newAttackSpeed=(int)(2.5*value+0.5);
			}
			return newAttackSpeed;
			case HEALTH:
				return (int)(value*(1.0+modifiedPercent/100.0)+0.5);
				 
			case POWER:
				return (int)(value*100*Math.log(1.0+modifiedPercent/12.0)*Math.log(1.0+modifiedPercent/12.0)+0.5);
				 
			case REGEN:
			return (int)(value*(1+modifiedPercent/100.0)+0.5);
			 
			case SHIELD:
			return (int)(value*(1+modifiedPercent/100.0)+0.5);
			 
			case SPEED:
			int newMovementDelay=(int)(value*(1-modifiedPercent/100.0)+0.5);
			if(newMovementDelay<3) 
			{
				newMovementDelay=3;
			}
			return newMovementDelay;				 
			case STRENGTH:
			return (int)(value*0.88*Math.log(1.0+modifiedPercent/12.0)*Math.log(1.0+modifiedPercent/12.0)+0.5) ;
			 

			case MISC:
				return 0;

		}
		return -404;
	}

	@Override
	public void render(Graphics2D g, int x, int y) {

	}

	@Override
	public void apply(StatType[] valueTypes, int[] values) {
		// TODO Auto-generated method stub

	}

}
