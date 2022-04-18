package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import skills.GenericSkill;
import skills.MultipleSkill;
import skills.Skill;
import skills.SkillTree;
import skills.StatType;

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
			Point[] points = { new Point(463 + offsetX, 360 + offsetY), new Point(388 + offsetX, 360 + offsetY),
					new Point(350 + offsetX, 425 + offsetY), new Point(388 + offsetX, 490 + offsetY),
					new Point(462 + offsetX, 490 + offsetY), new Point(500 + offsetX, 425 + offsetY) };
			Color color=Color.BLACK;
			switch (availableSkills[i].getType()) {
			case ACCURACY:
				color=new Color(43, 124, 255);
				break;
				 
			case ARMOR:
				color=new Color(168, 120, 8);
				break;
				 
			case ATTACKSPEED:
				color=new Color(210, 210, 0);
				break;
			case HEALTH:
				color=new Color(5, 99, 16);
				break;
			case POWER:
				color=new Color(75, 40, 181);
				break;
			case REGEN:
				color=new Color(40, 181, 155);
				break;
			case SHIELD:
				color=new Color(0, 219, 227);
				break;
			case SPEED:
				color=new Color(63, 163, 163);
				break;
			case STRENGTH:
				color=new Color(173, 10, 10);
				break;
			case MULTIPLE:
				color=new Color(194, 98, 14);
				break;

			case MISC:
				color=new Color(128, 37, 118);
				break;
		}
			System.out.println(availableSkills[i].getName()+" "+availableSkills[i].getType());
			skillButtons[i] = new Button(points, color,
					availableSkills[i].getName() + " " + availableSkills[i].getTier());
			skillButtons[i].setBounds(skillButtons[i].getX(), skillButtons[i].getY(),
					(int) skillButtons[i].getPreferredSize().getWidth(),
					(int) skillButtons[i].getPreferredSize().getHeight());
			panel.add(skillButtons[i]);
			skillButtons[i].addActionListener(this);
			Font text=null;
			try {
				text = Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\Exo_2\\static\\Exo2-Bold.ttf"));
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			skillButtons[i].setFont(text.deriveFont(12f));
		}

	}
	
	public void render(Graphics2D g,int JPanelX,int JPanelY) 
	{
		for(int i=0;i<skillButtons.length;i++) 
		{
			skillButtons[i].draw(g, JPanelX, JPanelY);
			if(skillButtons[i].isHovering()) 
			{	
				Font text=null;
				try {
					text = Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\Exo_2\\static\\Exo2-Bold.ttf"));
				} catch (FontFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				g.setColor(Color.BLACK);
				
				g.setFont(text.deriveFont(36f));
				String title=availableSkills[i].getName().toUpperCase()+" "+availableSkills[i].getTier();
				g.drawString(title, 800, 200);
				
				if(availableSkills[i].getType()!=StatType.MULTIPLE) 
				{
					
					g.setFont(text.deriveFont(30f));
					Skill tempSkill=(Skill)availableSkills[i];
					String sign="";
					if(tempSkill.getModifiedPercent()>0) 
					{
						sign+='+';
					}
					g.drawString(sign+tempSkill.getModifiedPercent()+"% "+tempSkill.getType(), 800,300);
				}else 
				{
					
					g.setFont(text.deriveFont(30f));
					MultipleSkill tempSkill=(MultipleSkill)availableSkills[i];
					Skill[] skills=tempSkill.getSkills();
					for(int j=0;j<skills.length;j++) 
					{
						String sign="";
						if(skills[j].getModifiedPercent()>0) 
						{
							sign+='+';
						}
						g.drawString(sign+skills[j].getModifiedPercent()+"% "+skills[j].getType(), 800,300+50*j);
					}
				}
				
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Button b=(Button)e.getSource();
		if(b.isClicked(e)) 
		{
			String name=b.getText();
			GenericSkill skill=null;
			for(int i=0;i<availableSkills.length;i++) 
			{
				//System.out.println(availableSkills[i].getName() + " " + availableSkills[i].getTier()+" "+name);
				if((availableSkills[i].getName() + " " + availableSkills[i].getTier()).equals(name)) 
				{
					
					skill=availableSkills[i];
					skill.setIsActive(true);
					tree.addSkill(skill);
					tree.applyLastAddedSkill();
					for(int j=0;j<skillButtons.length;j++) 
					{
						skillButtons[j].setVisible(false);
					}
					skillButtons=new Button[0];
					break;
				}
			}
			
		}
		
	}
}
