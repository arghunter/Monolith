package skills;

import java.awt.Graphics;

public class PowerSkill extends GenericSkill {

	private int power;
	private int modifiedPower;

	public PowerSkill(SkillType type,String name,int power,int tier) {
		super(type,name,tier);
		this.power=power;
		this.tier=tier;
		this.modifiedPower=power*(int)(tier/2.0+0.5);
	}

	@Override
	public int apply(int damage) {
		return (int)(damage*100*Math.log(1.0+power/12.0)*Math.log(1.0+power/12.0)+0.5);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
