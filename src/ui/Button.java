package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.awt.Graphics2D;

import javax.swing.JButton;

import general.AudioPlayer;
import input.PlayerInputParser;
import input.MouseInputParser;

public class Button extends Component implements MouseListener {
	private int x;// top left x coordinate on the panel
	private int y;// top left y coordinate on the panel
	private Polygon polygon;// polygon that is the shape of the button
	private Graphics2D g;// the graphics object used to draw the button
	private int JPanelX = -1;// the top left x coordinate of the JPanel on screen
	private int JPanelY = -1;// the top left y coordinate of the JPanel on screen
	private Color color;// the button's color
	private boolean listening = false;// is this button is being listened to by a mouse listener
	private boolean isHovering = false;// if this button is being hovered over
	private boolean isPressed = false;// if this button is being pressed
	private Component parent;// the parent component of this button
	private String text;
	private Font font;
	private Color fontColor = Color.GRAY;
	private float fontSize;
	private int stringX;
	private int stringY;
	private ActionListener[] actionListeners;
	private boolean calibrationRequired;
	private boolean fontSizeSet = false;
	private boolean hoverEffectsOn = true;
	private Color outLineColor = color;

	// Constructors

	// Creates a button with a shape with vertices points, color black and no text
	public Button(Point[] points) {
		this(points, Color.BLACK, "");
	}

	// Creates a button with a shape with vertices points, color color and no text
	public Button(Point[] points, Color color) {
		this(points, color, "");
	}

	// Creates a button with a shape with vertices points, color color and text text
	public Button(Point[] points, Color color, String text) {
		this.text = (text);
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Exo_2/static/Exo2-Medium.ttf"));
		} catch (FontFormatException | IOException e) {

			e.printStackTrace();
		}
		actionListeners = new ActionListener[0];
		this.color = color;

		int xmin = Integer.MAX_VALUE;
		int xmax = 0;
		int ymin = Integer.MAX_VALUE;
		int ymax = 0;
		this.polygon = new Polygon();
		for (int i = 0; i < points.length; i++) {
			polygon.addPoint((int) points[i].getX(), (int) points[i].getY());
			if (points[i].getX() < xmin) {
				xmin = (int) points[i].getX();
			}
			if (points[i].getX() > xmax) {
				xmax = (int) points[i].getX();
			}
			if (points[i].getY() < ymin) {
				ymin = (int) points[i].getY();
			}
			if (points[i].getY() > ymax) {
				ymax = (int) points[i].getY();
			}
		}

		super.setPreferredSize(new Dimension(((xmax - xmin)), ((ymax - ymin))));
		calibrationRequired = true;
		this.x = (int) (xmin);
		this.y = (int) (ymin);

		this.setFocusable(false);

	}

	// Draws the button on the screen and updates certain values
	public void draw(Graphics2D g, int JPanelX, int JPanelY) {

		this.g = g;
		if (!listening) {
			try {
				super.getParent().addMouseListener(this);
				parent = super.getParent();
				listening = true;
			} catch (Exception e) {
				listening = false;
				System.err.println("Don't forget to add the button to a component");
			}

		}

		if (buttonContainsMouse()) {
			if (isPressed) {
				if (hoverEffectsOn) {
					g.setColor(new Color((int) (color.getRed() * 0.5), (int) (color.getGreen() * 0.5),
							(int) (color.getBlue() * 0.5)));

				} else {
					g.setColor(color);

				}
			} else {
				if (this.hoverEffectsOn) {

					g.setColor(new Color((int) (color.getRed() * 0.75), (int) (color.getGreen() * 0.75),
							(int) (color.getBlue() * 0.75)));
				} else {
					g.setColor(color);

				}
				isHovering = true;
			}
		} else {
			g.setColor(color);
			isHovering = false;
		}
		g.fill(polygon);
		g.setColor(outLineColor);
		g.setStroke(new BasicStroke(4f));
		g.draw(polygon);
		if (calibrationRequired) {
			calibrateText(g);
		}
		g.setColor(fontColor);
		g.setFont(font.deriveFont(fontSize));
		g.drawString(text, stringX, stringY);
		this.JPanelX = JPanelX;
		this.JPanelY = JPanelY;

	}

	// Sets the hover Effects to on or off
	public void setHoverEffectsOn(boolean isOn) {
		this.hoverEffectsOn = isOn;
	}

	// Gets hover effects
	public boolean isHoverEffectsOn() {
		return this.hoverEffectsOn;
	}

	// auto Sizes the font
	public void autoSizeFont() {
		this.fontSizeSet = false;
		this.calibrationRequired = true;
	}

	// sets the fontSize
	public void setFontSize(float size) {
		this.fontSizeSet = true;
		this.fontSize = size;
	}

	// Sets the outline color
	public void setOutlineColor(Color color) {
		this.outLineColor = color;
	}

	// Calibrates the font color and position of the text
	private void calibrateText(Graphics2D g) {
		if (fontSizeSet) {
			return;
		}
		int fontSize = 4;

		while (true) {
			FontMetrics metrics = g.getFontMetrics(font.deriveFont((float) fontSize));

			int x = this.x + (polygon.getBounds().width - metrics.stringWidth(text)) / 2;
			int y = this.y + polygon.getBounds().height / 2 - metrics.getHeight() / 2;
			if (polygon.contains(new Point(x, y))
					&& polygon.contains(new Point(x + metrics.stringWidth(text), y + metrics.getHeight()))
					&& polygon.contains(new Point(x + metrics.stringWidth(text), y))
					&& polygon.contains(new Point(x, y + metrics.getHeight()))) {
				fontSize++;
			} else {
				this.fontSize = fontSize - 4;
				metrics = g.getFontMetrics(font.deriveFont((float) (this.fontSize)));

				x = this.x + (polygon.getBounds().width - metrics.stringWidth(text)) / 2;
				y = this.y + polygon.getBounds().height / 2 - metrics.getHeight() / 2;

				this.stringX = x;
				this.stringY = y + (int) (metrics.getHeight() / 1.28);
				calibrationRequired = false;
				return;
			}
		}

	}

	// Translates this button
	public void translate(int x, int y) {
		polygon.translate(x, y);
		this.x += x;
		this.y += y;
		calibrationRequired = true;

	}

	// Returns the text that this button displays
	public String getText() {
		return text;
	}

	// Sets the text this button displays
	public void setText(String text) {
		this.text = text;
	}

	// Sets the font color
	public void setFontColor(Color color) {
		this.fontColor = color;
	}

	// Returns true if the mouse is currently inside the button
	private boolean buttonContainsMouse() {
		return polygon.contains((int) ((MouseInputParser.getX() - JPanelX / MouseInputParser.getRatioX())),
				(int) ((MouseInputParser.getY() - JPanelY / MouseInputParser.getRatioY())));

	}

	// Takes in an action event and returns true if it was this button that got
	// clicked
	public boolean isClicked(ActionEvent e) {
		return e.getSource() == this && e.getID() == 88888;
	}

	// Returns the x coordinate of this button
	public int getX() {
		return x;
	}

	// Returns the y coordinate of this button
	public int getY() {
		return y;
	}

	// Returns if this button is being hovered over
	public boolean isHovering() {
		return isHovering;
	}

	// Returns if this button is depressed
	public boolean isPressed() {
		return isPressed;
	}

	// Disposes of this button. Makes it inaccessible and impossible to use
	public void dispose() {
		this.setVisible(false);
		this.getParent().removeMouseListener(this);
		this.getParent().remove(this);

	}

	@Override
	// Sets the font
	public void setFont(Font font) {
		this.font = font;

	}

	@Override
	// Returns the font
	public Font getFont() {
		return this.font.deriveFont(fontSize);
	}

	// Returns the action listenrs in this object
	public ActionListener[] getActionListeners() {
		return actionListeners;
	}

	// Adds an action listener to this object
	public void addActionListener(ActionListener listener) {
		ActionListener[] temp = new ActionListener[actionListeners.length + 1];
		for (int i = 0; i < actionListeners.length; i++) {
			temp[i] = actionListeners[i];
		}
		temp[actionListeners.length] = listener;
		actionListeners = temp;
	}

	@Override
	// Fires action events to all listeners if this button has been clicked
	public void mouseClicked(MouseEvent e) {

		if (super.getParent() != null && e.getButton() == MouseEvent.BUTTON1 && buttonContainsMouse()) {
			for (ActionListener i : this.getActionListeners()) {
				if (i == this) {
					continue;
				}
				i.actionPerformed(new ButtonClickedEvent(this, "click"));
				new AudioPlayer("button_0008", AudioPlayer.ONE_TIME);
			}

		}

	}

	@Override
	// Decides if the button is depressed currently
	public void mousePressed(MouseEvent e) {

		if (e.getButton() == MouseEvent.BUTTON1 && buttonContainsMouse())
			isPressed = true;
		else
			isPressed = false;
	}

	@Override
	// Sets isPressed to false
	public void mouseReleased(MouseEvent e) {

		isPressed = false;

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}
