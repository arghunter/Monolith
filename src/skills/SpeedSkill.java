package skills;

import java.awt.Graphics;

public class SpeedSkill extends GenericSkill {

	private int speed;
	private int modifiedSpeed;

	public SpeedSkill(SkillType type,String name, int speed, int tier) {
		super(type, name, tier);
		this.speed=speed;
		this.tier=tier;
		this.modifiedSpeed=speed*(tier);
	}

	@Override
	public int apply(int movementDelay) {
		int newMovementDelay=(int)(movementDelay*(1-modifiedSpeed/100.0)+0.5);
		if(newMovementDelay<3) 
		{
			newMovementDelay=3;
		}
		return newMovementDelay;
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void apply(SkillType[] valueTypes, int[] values) {
		// TODO Auto-generated method stub
		
	}

}
