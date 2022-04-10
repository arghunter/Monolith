import javax.swing.JFrame;

import GameObjects.Direction;
import GameObjects.Player.Player;

public class KeyboardInputParser {
	KeyboardInput input;
	public KeyboardInputParser(JFrame frame) 
	{
		this.input=new KeyboardInput(frame);
	}
	
	public void updatePlayerPos(Player player) 
	{
		int leftComp=(input.isLeftPressed()||input.isaPressed()?1:0);
		int rightComp=(input.isRightPressed()||input.isdPressed()?1:0);
		int upComp=(input.isUpPressed()||input.iswPressed()?1:0);
		int downComp=(input.isDownPressed()||input.issPressed()?1:0);
		if(leftComp-rightComp==0) {
			if(upComp-downComp==1) {
				player.move(Direction.NORTH);
			}else if(upComp-downComp==-1) {
				player.move(Direction.SOUTH);
			}else {
				//Don't move
			}
		}else if(leftComp-rightComp==1) {
			if(upComp-downComp==1) {
				player.move(Direction.NORTHWEST);
			}else if(upComp-downComp==-1) {
				player.move(Direction.SOUTHWEST);
			}else {
				player.move(Direction.WEST);
			}
		}else if(leftComp-rightComp==-1) {
			if(upComp-downComp==1) {
				player.move(Direction.NORTHEAST);
			}else if(upComp-downComp==-1) {
				player.move(Direction.SOUTHEAST);
			}else {
				player.move(Direction.EAST);
			}
		}
	}

}
