package ui;

import java.awt.Color;  
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import GameObjects.Player.Player;
import general.Constants;
import input.MouseInputParser;
import mapGeneration.MapGenerator;
import render.GameStatus;
import render.Main;

public class PauseMenu implements ActionListener {

	private Button resume;
	private MapVisual visual;
	private Player player;
	private Button mapButton;


	public PauseMenu(Player player, JPanel panel,Long initTime,MapGenerator map) {
		Point[] resumePoints = { new Point(0, 66), new Point(0, 266), new Point(400, 266), new Point(400, 66) };
		resume = new Button(resumePoints, new Color((212) / 2, (175) / 2, (55) / 2, 0), "Resume");
		resume.setFontColor(Constants.TEXTCOLOR);
		panel.add(resume);
		resume.addActionListener(this);
		Point[] mapPoints = { new Point((int)Main.WIDTH-400, 66), new Point((int)Main.WIDTH-400, 266), new Point((int)Main.WIDTH, 266), new Point((int)Main.WIDTH, 66) };
		mapButton=new Button(mapPoints, new Color((212) / 2, (175) / 2, (55) / 2, 0), "Map");
		mapButton.setFontColor(Constants.TEXTCOLOR);
		panel.add(mapButton);
		mapButton.addActionListener(this);
		visual=new MapVisual(map,initTime,panel);
		this.player = player;


	}

	public void draw(Graphics2D g, int JPanelX, int JPanelY) {
		if (Main.status==GameStatus.PAUSED) {
			g.drawImage(createGradient(),0,0,null);
			Font text = null;
			try {
				text = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Exo_2/static/Exo2-Medium.ttf"));
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			
			g.setFont(text.deriveFont(240f));
			FontMetrics metrics = g.getFontMetrics();
			g.setColor(Constants.TEXTCOLOR);
			g.drawString("Monolith", ((int)Main.WIDTH - metrics.stringWidth("Monolith")) / 2, ((int)Main.HEIGHT)/2);
			g.setFont(text.deriveFont(40f));
			metrics = g.getFontMetrics();
			g.drawString("Player Stats", ((int)Main.WIDTH - metrics.stringWidth("playerStats")) / 2, ((int)Main.HEIGHT)/2+80);
			for(int i=0;i<player.getStatTypes().length;i++) 
			{
				g.drawString(""+player.getStatTypes()[i]+":    "+player.getStats()[i]+" + "+player.getBuffs()[i]+" = "+(player.getStats()[i]+player.getBuffs()[i]),((int)Main.WIDTH - metrics.stringWidth("playerStats")) / 2-100, ((int)Main.HEIGHT)/2+(i+3)*50);
			}
			if(!visual.getHidden()) 
			{
				visual.draw(g);

			}
			mapButton.draw(g, JPanelX, JPanelY);
			resume.draw(g, JPanelX, JPanelY);


			
			

		}
	}

	// Creates a nice looking gradient centered around the mouse
	private static BufferedImage createGradient() {
		int width = (int) Main.WIDTH;
		int height = (int) Main.HEIGHT;

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

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==resume)
			Main.status = GameStatus.RUNNING;
		else if(e.getSource()==mapButton) 
		{
			if(visual.getHidden())
				visual.setHidden(false);
			else
				visual.setHidden(true);

		}
	}

}
