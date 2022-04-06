package skills;

import java.awt.Graphics;

public class PowerSkill extends GenericSkill {

	private int power;
	private int modifiedPower;

	public PowerSkill(StatType type,String name,int power,int tier,boolean isActive) {
		super(type,name,tier,isActive);
		this.power=power;
		
		this.modifiedPower=power*(tier);
	}

	@Override
	public int apply(int damage) {
		return (int)(damage*100*Math.log(1.0+modifiedPower/12.0)*Math.log(1.0+modifiedPower/12.0)+0.5);
	}

	@Override
	public void render(Graphics g,int x,int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void apply(StatType[] valueTypes, int[] valuess) {
		// TODO Auto-generated method stub
		
	}

}
