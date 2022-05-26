//Author: Adithya Giri
//Date: 5/11/22
//Rev: 01
//Notes: A Player buff that improves player stats
package GameObjects.Player.items.consumables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.Timer;

import GameObjects.Player.items.ItemType;
import skills.StatType;

public class Buff implements ActionListener {

	// Fields
	private StatType[] types;
	private int[] buffs;
	private int[] playerStats;
	private StatType[] playerStatTypes;
	private int duration;
	private Timer timer;

	// Constructor
	public Buff(StatType[] types, int[] statBuffs, int duration, StatType[] playerTypes, int[] playerStats) {
		this.types = types;
		this.buffs = statBuffs;
		this.playerStats = playerStats;
		this.playerStatTypes = playerTypes;
		this.duration = duration;
		timer = new Timer(duration * 1000, this);

	}

	// Duplicate Constructor
	public Buff(Buff buff) {
		this.types = buff.types;
		this.buffs = buff.buffs;
		this.playerStats = buff.playerStats;
		this.playerStatTypes = buff.playerStatTypes;
		this.duration = buff.duration;
		timer = new Timer(buff.duration * 1000, this);

	}

	// Starts this buff
	public void start() {

		for (int i = 0; i < types.length; i++) {
			for (int j = 0; j < playerStatTypes.length; j++) {
				if (!(types[i] == playerStatTypes[j])) {
					continue;
				}
				playerStats[j] += buffs[i];
			}
		}

		timer.start();

	}

	@Override
	// Ends this buff
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {
			for (int i = 0; i < types.length; i++) {
				for (int j = 0; j < playerStatTypes.length; j++) {
					if (!(types[i] == playerStatTypes[j])) {
						continue;
					}
					playerStats[j] -= buffs[i];
				}
			}
			timer.stop();
		}

	}

	// Gets the stat Types that this buff improves
	public StatType[] getTypes() {
		return types;
	}

	// Returns the buff vales for each stat type
	public int[] getBuffs() {
		return buffs;
	}

	// Gets the duration of this buff in seconds
	public int getDuration() {
		return duration;
	}

	// Sets the duration of this buff
	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	// String token for this buff for save data parsing
	public String toString() {
		return ("Buff[types=" + Arrays.toString(types) + ",buffs=" + Arrays.toString(buffs) + ",duration=" + duration
				+ "]").replace(",", ";~;");
	}

}
