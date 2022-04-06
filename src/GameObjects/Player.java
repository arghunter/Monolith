package GameObjects;
import java.awt.Graphics;

import skills.*;

public class Player extends MovingObject {
	private SkillTree skills;
	private static StatType[] statTypes= {StatType.ACCURACY,StatType.ARMOR,StatType.ATTACKSPEED,StatType.HEALTH,StatType.POWER,StatType.REGEN,StatType.SHIELD,StatType.SPEED,StatType.STRENGTH};
	private static int[] baseStats= {10,25,60,100,10,1,100,15,10};
	//Note the speed will come from skill tree
	public Player(int x, int y, int id,int width,int height) {
		super(x, y,baseStats[7],id,width,height);
		skills=new SkillTree(baseStats,statTypes);
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	} 

	


	

}
