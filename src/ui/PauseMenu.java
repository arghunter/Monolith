package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import GameObjects.Player.Player;
import general.Constants;
import input.MouseInputParser;
import input.PlayerInputParser;
import render.GameStatus;
import render.Main;
import render.Tester;

public class PauseMenu implements ActionListener {

	private Button resume;
	private boolean hidden = false;
	Player player;
	PlayerInputParser input;
	GameStatus status = GameStatus.RUNNING;

	public PauseMenu(Player player, JPanel panel, PlayerInputParser input) {
		Point[] menuPoints = { new Point(0, 66), new Point(0, 266), new Point(400, 266), new Point(400, 66) };
		resume = new Button(menuPoints, new Color((212) / 2, (175) / 2, (55) / 2, 0), "Resume");
		resume.setFontColor(Constants.TEXTCOLOR);
		panel.add(resume);
		resume.addActionListener(this);
		this.player = player;
		this.input = input;

	}

	public void draw(Graphics2D g, int JPanelX, int JPanelY) {
		if (!hidden) {
			g.drawImage(createGradient(),0,0,null);
			resume.draw(g, JPanelX, JPanelY);

			if (input.isEscapePressed()) {
				this.hidden = true;
				status = GameStatus.RUNNING;
			}

		}
	}

	// Creates a nice looking gradient centered around the mouse
	private static BufferedImage createGradient() {
		int width = (int) Tester.WIDTH;
		int height = (int) Tester.HEIGHT;

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();

		Color[] colors = { new Color((212) / 3, (175) / 3, (55) / 3), new Color(212 / 6, 175 / 6, 55 / 6) };
		float[] ratio = { 0.0f, 0.6f };
		Point center = new Point((int) MouseInputParser.getX(), (int) MouseInputParser.getY());
		RadialGradientPaint gradient = new RadialGradientPaint(center, 0.25f * width, ratio, colors);
		g.setPaint(gradient);
		g.fillRect(0, 0, width, height);
		g.dispose();

		return img;
	}

	public GameStatus getStatus() {
		return status;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
		if (!hidden) {
			status = GameStatus.PAUSED;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		status = GameStatus.RUNNING;
	}

}
