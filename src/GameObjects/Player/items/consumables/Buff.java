package GameObjects.Player.items.consumables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import GameObjects.Player.items.ItemType;
import skills.StatType;

public class Buff implements ActionListener {


	private StatType[] types;
	private int[] buffs;
	private int[] playerStats;
	private StatType[] playerStatTypes;
	private int duration;
	Timer timer;

	public Buff(StatType[] types, int[] statBuffs, int duration, StatType[] playerTypes, int[] playerStats) {
		this.types = types;
		this.buffs = statBuffs;
		this.playerStats = playerStats;
		this.playerStatTypes = playerTypes;
		this.duration=duration;
		timer = new Timer(duration * 1000, this);
		
	}
	public Buff(Buff buff) {
		this.types = buff.types;
		this.buffs = buff.buffs;
		this.playerStats = buff.playerStats;
		this.playerStatTypes = buff.playerStatTypes;
		this.duration=buff.duration;
		timer = new Timer(buff.duration * 1000, this);
		
	}

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
	public StatType[] getTypes() {
		return types;
	}
	public void setTypes(StatType[] types) {
		this.types = types;
	}
	public int[] getBuffs() {
		return buffs;
	}
	public void setBuffs(int[] buffs) {
		this.buffs = buffs;
	}
	public int[] getPlayerStats() {
		return playerStats;
	}
	public void setPlayerStats(int[] playerStats) {
		this.playerStats = playerStats;
	}
	public StatType[] getPlayerStatTypes() {
		return playerStatTypes;
	}
	public void setPlayerStatTypes(StatType[] playerStatTypes) {
		this.playerStatTypes = playerStatTypes;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

}
