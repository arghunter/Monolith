//Author: Armaan Gomes 
//Date: 5/12/22
//Rev: 01
// Notes: Displays all current skills of the player
package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import general.Constants;
import input.MouseInputParser;
import render.Main;
import skills.GenericSkill;
import skills.MultipleSkill;
import skills.Skill;
import skills.SkillTree;
import skills.StatType;

public class SkillDisplayMenu implements ActionListener {
	// Fields
	private GenericSkill[] currentSkills;
	private Button[] currentSkillButtons;
	private boolean isActive = true;

	// Constructor
	public SkillDisplayMenu(SkillTree tree, JPanel panel) {

		this.currentSkills = new GenericSkill[tree.getSkills().size()];
		for (int i = 0; i < currentSkills.length; i++) {
			currentSkills[i] = tree.getSkills().get(i);
		}

		currentSkillButtons = new Button[currentSkills.length];

		for (int i = 0; i < currentSkillButtons.length; i++) {
			int offsetX = 0;
			int offsetY = 0;
			int modI = i % 7;
			int shiftX = i / 7 * 390 - 75;
			int shiftY = (int) (300 + 72 * ((Math.floor(i / 7.0)) % 2));
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
			Point[] points = { new Point(463 + offsetX + shiftX, 360 + offsetY + shiftY),
					new Point(388 + offsetX + shiftX, 360 + offsetY + shiftY),
					new Point(350 + offsetX + shiftX, 425 + offsetY + shiftY),
					new Point(388 + offsetX + shiftX, 490 + offsetY + shiftY),
					new Point(462 + offsetX + shiftX, 490 + offsetY + shiftY),
					new Point(500 + offsetX + shiftX, 425 + offsetY + shiftY) };
			Color color = skillColor(currentSkills[i]);

			currentSkillButtons[i] = new Button(points, color,
					currentSkills[i].getName() + " " + currentSkills[i].getTier());
			panel.add(currentSkillButtons[i]);
			currentSkillButtons[i].addActionListener(this);

			currentSkillButtons[i].setFontColor(Constants.TEXTCOLOR);
		}

	}

	// Color decision based on skill type
	public Color skillColor(GenericSkill skill) {
		Color color = null;
		switch (skill.getType()) {
		case ACCURACY:
			color = new Color(43, 124, 255);
			break;

		case ARMOR:
			color = new Color(168, 168, 168);
			break;

		case ATTACKSPEED:
			color = new Color(210, 210, 0);
			break;
		case HEALTH:
			color = new Color(5, 99, 16);
			break;
		case POWER:
			color = new Color(75, 40, 181);
			break;
		case REGEN:
			color = new Color(40, 181, 155);
			break;
		case SHIELD:
			color = new Color(0, 219, 227);
			break;
		case SPEED:
			color = new Color(63, 163, 163);
			break;
		case STRENGTH:
			color = new Color(173, 10, 10);
			break;
		case MULTIPLE:
			MultipleSkill tempSkill = (MultipleSkill) skill;
			for (int i = 0; i < tempSkill.getSkills().length; i++) {
				if (tempSkill.getSkills()[i].getType() == StatType.XP) {
					color = skillColor(tempSkill.getSkills()[i]);
					return color;
				}
			}
			color = skillColor(tempSkill.getSkills()[0]);
			break;
		case XP:
			color = new Color(88, 132, 44);
			break;

		}
		return color;
	}

	// Renders this menu
	public void render(Graphics2D g, int JPanelX, int JPanelY) {
		if (isActive) {
			g.drawImage(createGradient(JPanelX, JPanelY), JPanelX, JPanelY, null);
			g.setColor(Constants.TEXTCOLOR);
			Font text = null;
			try {
				text = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Exo_2/static/Exo2-Medium.ttf"));
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			g.setFont(text.deriveFont(120f));
			g.drawString("Skills", 275, 175);
			for (int i = 0; i < currentSkillButtons.length; i++) {
				currentSkillButtons[i].draw(g, JPanelX, JPanelY);
				if (currentSkillButtons[i].isHovering()) {
					text = null;
					try {
						text = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Exo_2/static/Exo2-Bold.ttf"));
					} catch (FontFormatException e) {
						e.printStackTrace();
					} catch (IOException e) {

						e.printStackTrace();
					}
					g.setColor(Constants.TEXTCOLOR);

					g.setFont(text.deriveFont(36f));
					String title = currentSkills[i].getName().toUpperCase() + " " + currentSkills[i].getTier();
					g.drawString(title, 1600, 200);
					if (currentSkills[i].getIsActive() == true) {
						g.drawString("Active", 1600, 250);
					} else {
						g.drawString("Inactive", 1600, 250);

					}
					if (currentSkills[i].getType() != StatType.MULTIPLE) {

						g.setFont(text.deriveFont(30f));
						Skill tempSkill = (Skill) currentSkills[i];
						String sign = "";
						if (tempSkill.getModifiedPercent() > 0) {
							sign += '+';
						}
						g.drawString(sign + tempSkill.getModifiedPercent() + "% " + tempSkill.getType(), 1600, 300);
					} else {

						g.setFont(text.deriveFont(30f));
						MultipleSkill tempSkill = (MultipleSkill) currentSkills[i];
						Skill[] skills = tempSkill.getSkills();
						for (int j = 0; j < skills.length; j++) {
							String sign = "";
							if (skills[j].getModifiedPercent() > 0) {
								sign += '+';
							}
							g.drawString(sign + skills[j].getModifiedPercent() + "% " + skills[j].getType(), 1600,
									300 + 50 * j);
						}
					}

				}
			}
		}

	}

	// Creates a gradient
	private static BufferedImage createGradient(int JPanelX, int JPanelY) {
		int width = (int) Main.WIDTH;
		int height = (int) Main.HEIGHT;

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();

		Color[] colors = { new Color((212) / 3, (175) / 3, (55) / 3), new Color(212 / 6, 175 / 6, 55 / 6) };
		float[] ratio = { 0.0f, 0.6f };
		Point center = new Point((int) (MouseInputParser.getX() - JPanelX / MouseInputParser.getRatioX()),
				(int) (MouseInputParser.getY() - JPanelY / MouseInputParser.getRatioX()));

		RadialGradientPaint gradient = new RadialGradientPaint(center, 0.25f * width, ratio, colors);
		g.setPaint(gradient);
		g.fillRect(0, 0, width, height);
		g.dispose();

		return img;
	}

	@Override
	// Disables or enables clicked skills
	public void actionPerformed(ActionEvent e) {
		if (this.isActive) {
			Button b = (Button) e.getSource();
			if (b.isClicked(e)) {

				String name = b.getText();
				GenericSkill skill = null;
				for (int i = 0; i < currentSkills.length; i++) {
					if ((currentSkills[i].getName() + " " + currentSkills[i].getTier()).equals(name)) {

						skill = currentSkills[i];
						skill.setIsActive(!skill.getIsActive());
						break;
					}
				}

			}

		}

	}

	// Sets isActive
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isActive() {
		return isActive;
	}

	public void dispose() {
		for (int j = 0; j < currentSkillButtons.length; j++) {

			currentSkillButtons[j].dispose();
		}

		currentSkillButtons = new Button[0];
		this.isActive = false;
	}
}
