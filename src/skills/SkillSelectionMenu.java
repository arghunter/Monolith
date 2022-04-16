package skills;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import menu.Button;

public class SkillSelectionMenu implements ActionListener {
	int numSkills;
	SkillTree tree;
	GenericSkill[] availableSkills;
	Button[] skillButtons;

	public SkillSelectionMenu(SkillTree tree, int timeSeconds, JPanel panel) {

		this.tree = tree;
		this.numSkills = timeSeconds / 120;
		this.availableSkills = tree.skillSelection(numSkills);
		if (numSkills > 7) {
			numSkills = 7;
		}

		skillButtons = new Button[availableSkills.length];
		for (int i = 0; i < skillButtons.length; i++) {
			int offsetX = 0;
			int offsetY = 0;
			switch (i) {
			case 1:
				offsetX = 0;
				offsetY = -150;
				break;
			case 2:
				offsetX = (int) (150 * Math.cos(Math.PI / 6));
				offsetY = (int) (150 * Math.sin(Math.PI / 6));
				break;
			case 3:
				offsetX = (int) (150 * Math.cos(Math.PI / 6));
				offsetY = -(int) (150 * Math.sin(Math.PI / 6));
				break;
			case 4:
				offsetX = 0;
				offsetY = 150;
				break;
			case 5:
				offsetX = -(int) (150 * Math.cos(Math.PI / 6));
				offsetY = -(int) (150 * Math.sin(Math.PI / 6));
				break;
			case 6:
				offsetX = -(int) (150 * Math.cos(Math.PI / 6));
				offsetY = (int) (150 * Math.sin(Math.PI / 6));
				break;
			}
			Point[] points = { new Point(413 + offsetX, 310 + offsetY), new Point(338 + offsetX, 310 + offsetY),
					new Point(300 + offsetX, 375 + offsetY), new Point(338 + offsetX, 440 + offsetY),
					new Point(412 + offsetX, 440 + offsetY), new Point(450 + offsetX, 375 + offsetY) };
			skillButtons[i] = new Button(points, Color.CYAN,
					availableSkills[i].getName() + " " + availableSkills[i].getTier());
			skillButtons[i].setBounds(skillButtons[i].getX(), skillButtons[i].getY(),
					(int) skillButtons[i].getPreferredSize().getWidth(),
					(int) skillButtons[i].getPreferredSize().getHeight());
			panel.add(skillButtons[i]);
			skillButtons[i].addActionListener(this);
		}

	}
	
	public void render(Graphics2D g,int JPanelX,int JPanelY) 
	{
		for(int i=0;i<numSkills;i++) 
		{
			skillButtons[i].draw(g, JPanelX, JPanelY);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Button b=(Button)e.getSource();
		if(b.isClicked(e)) 
		{
			System.out.println(b);
		}
		
	}
}
