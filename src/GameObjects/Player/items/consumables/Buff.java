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
	Timer timer;

	public Buff(StatType[] types, int[] statBuffs, int duration, StatType[] playerTypes, int[] playerStats) {
		this.types = types;
		this.buffs = statBuffs;
		this.playerStats = playerStats;
		this.playerStatTypes = playerTypes;
		timer = new Timer(duration * 1000, this);
		timer.start();
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

}
