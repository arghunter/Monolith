package ui;

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
	private int numSkills;
	private SkillTree tree;
	private GenericSkill[] availableSkills;
	private GenericSkill[] currentSkills;
	private Button[] currentSkillButtons;
	private Button[] skillButtons;
	private boolean isActive=true;

	public SkillSelectionMenu(SkillTree tree, int timeSeconds, JPanel panel) {

		this.tree = tree;
		this.numSkills = timeSeconds / 120;
		if (numSkills > 7) {
			numSkills = 7;
		}
		this.availableSkills = tree.skillSelection(numSkills);
		this.currentSkills=new GenericSkill[tree.getSkills().size()];
		for(int i=0;i<currentSkills.length;i++) 
		{
			currentSkills[i]=tree.getSkills().get(i);
		}
		
		currentSkillButtons=new Button[currentSkills.length];

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
			Color color=skillColor(availableSkills[i]);

			//System.out.println(availableSkills[i].getName()+" "+availableSkills[i].getType());
			skillButtons[i] = new Button(points, color,
					availableSkills[i].getName() + " " + availableSkills[i].getTier());
			skillButtons[i].setBounds(skillButtons[i].getX(), skillButtons[i].getY(),
					(int) skillButtons[i].getPreferredSize().getWidth(),
					(int) skillButtons[i].getPreferredSize().getHeight());
			panel.add(skillButtons[i]);
			skillButtons[i].addActionListener(this);
			Font text=null;
			try {
				text = Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\Exo_2\\static\\Exo2-Black.ttf"));
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			skillButtons[i].setFont(text.deriveFont(12f));
			skillButtons[i].setForeground(new Color(148, 148, 148));
		}
		for (int i = 0; i < currentSkillButtons.length; i++) {
			int offsetX = 0;
			int offsetY = 0;
			int modI=i%7;
			int shiftX=i/7*390-75;
			int shiftY=(int)(600+72*((Math.floor(i/7.0))%2));
			switch (modI) {
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
			Point[] points = { new Point(463 + offsetX+ shiftX, 360 + offsetY+shiftY), new Point(388 + offsetX+ shiftX, 360 + offsetY+shiftY),
					new Point(350 + offsetX+ shiftX, 425 + offsetY+shiftY), new Point(388 + offsetX+ shiftX, 490 + offsetY+shiftY),
					new Point(462 + offsetX+ shiftX, 490 + offsetY+shiftY), new Point(500 + offsetX+ shiftX, 425 + offsetY+shiftY) };
			Color color=skillColor(currentSkills[i]);

			//System.out.println(availableSkills[i].getName()+" "+availableSkills[i].getType());
			currentSkillButtons[i] = new Button(points, color,
					currentSkills[i].getName() + " " + currentSkills[i].getTier());
			currentSkillButtons[i].setBounds(currentSkillButtons[i].getX(), currentSkillButtons[i].getY(),
					(int) currentSkillButtons[i].getPreferredSize().getWidth(),
					(int) currentSkillButtons[i].getPreferredSize().getHeight());
			panel.add(currentSkillButtons[i]);
			currentSkillButtons[i].addActionListener(this);
			Font text=null;
			try {
				text = Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\Exo_2\\static\\Exo2-Bold.ttf"));
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			currentSkillButtons[i].setFont(text.deriveFont(12f));
			currentSkillButtons[i].setForeground(new Color(148, 148, 148));
		}


	}
	public Color skillColor(GenericSkill skill) 
	{
		Color color=null;
		switch (skill.getType()) {
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
			MultipleSkill tempSkill=(MultipleSkill)skill;
			for(int i=0;i<tempSkill.getSkills().length;i++) 
			{
				if(tempSkill.getSkills()[i].getType()==StatType.XP) 
				{
					color=skillColor(tempSkill.getSkills()[i]);
					return color;
				}
			}
			color=skillColor(tempSkill.getSkills()[0]);
			break;
		case XP:
			color=new Color(88,132,44);
			break;
		case MISC:
			color=new Color(128, 37, 118);
			break;
		}
		return color;
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
		for(int i=0;i<currentSkillButtons.length;i++) 
		{
			currentSkillButtons[i].draw(g, JPanelX, JPanelY);
			if(currentSkillButtons[i].isHovering()) 
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
				String title=currentSkills[i].getName().toUpperCase()+" "+currentSkills[i].getTier();
				g.drawString(title, 800, 200);
				
				if(currentSkills[i].getType()!=StatType.MULTIPLE) 
				{
					
					g.setFont(text.deriveFont(30f));
					Skill tempSkill=(Skill)currentSkills[i];
					String sign="";
					if(tempSkill.getModifiedPercent()>0) 
					{
						sign+='+';
					}
					g.drawString(sign+tempSkill.getModifiedPercent()+"% "+tempSkill.getType(), 800,300);
				}else 
				{
					
					g.setFont(text.deriveFont(30f));
					MultipleSkill tempSkill=(MultipleSkill)currentSkills[i];
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
				System.out.println(tree);
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
						
						for(int j=0;j<skillButtons.length;j++) 
						{
							skillButtons[j].setVisible(false);
							skillButtons[j].setText("");
							skillButtons[j].getParent().remove(skillButtons[j]);
							skillButtons[j].setEnabled(false);
							
							
						}
						for(int j=0;j<currentSkillButtons.length;j++) 
						{
							
							currentSkillButtons[j].setVisible(false);
							currentSkillButtons[j].setText("");
							currentSkillButtons[j].getParent().remove(currentSkillButtons[j]);
							currentSkillButtons[j].setEnabled(false);
						}
						skillButtons=new Button[0];
						currentSkillButtons=new Button[0];
						this.isActive=false;
						break;
					}
				}
				
			}
		
		
		
	}
	
	public boolean isActive() 
	{
		return isActive;
	}
}
