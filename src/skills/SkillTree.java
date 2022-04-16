package skills;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JPanel;

import menu.Button;



public class SkillTree {
	private int[] baseStats;
	private int[] modifiedBaseStats;
	private StatType[] statTypes;
	private ArrayList<GenericSkill> skills=new ArrayList<GenericSkill>();
	public final String[] SKILL_NAMES= {"Acurracy","Armor","Attack Speed","Health","Power","Regen","Shield","Speed","Strength", "Tank"};//,"Marksman","Sword Master","Curse","Master","Sprinter","Weight Training","Eagle Eyes","Recovery","Shield Master","Armor Master", "Greased Lightning", "Sharpened Steel"};
	public final StatType[][] SKILL_TYPES=  { { StatType.ACCURACY}, {StatType.ARMOR },{StatType.ATTACKSPEED},{StatType.HEALTH},{StatType.POWER},{StatType.REGEN},{StatType.SHIELD},{StatType.SPEED},{StatType.STRENGTH},{StatType.HEALTH,StatType.SHIELD,StatType.REGEN,StatType.STRENGTH,StatType.SPEED}};
	public final int[][][] SKILL_RANGE= {{{2,8}},{{5,15}},{{5,15}},{{8,16}},{{2,16}},{{5,15}},{{8,16}},{{2,8}},{{2,16}},{{25,35},{20,30},{10,20},{2,5},{-20,-10}}} ;
	public SkillTree(String saveData,int[] baseStats,StatType[]statTypes) 
	{
		this.baseStats=Arrays.copyOf(baseStats, baseStats.length);
		this.modifiedBaseStats=baseStats;
		this.statTypes=statTypes;
	}
	public SkillTree(int[] baseStats,StatType[] statTypes) 
	{
		this.baseStats=Arrays.copyOf(baseStats, baseStats.length);
		this.modifiedBaseStats=baseStats;
		this.statTypes=statTypes;
	}
	public SkillTree(SkillTree tree) 
	{
		this.skills=tree.skills;
	}
	public void addXP(int xp) 
	{
		int activeSkills=0;
		for(int i=0;i<skills.size();i++) 
		{
			if(skills.get(i).getIsActive()) 
			{
				activeSkills++;
			}
		}
		int divXp=1+(int)((double)xp/activeSkills+0.5);
		for(int i=0;i<skills.size();i++) 
		{
			try {
				skills.get(i).addXP(divXp);
			} catch (SkillUpdateException e) {
				applyLastAddedSkill();

			}
		}
	}
	private void applyLastAddedSkill() 
	{
		StatType type=skills.get(skills.size()-1).getType();
		if(type==StatType.MULTIPLE) 
		{
			skills.get(skills.size()-1).apply(statTypes, modifiedBaseStats);
		}
		for(int i=0;i<baseStats.length;i++) 
		{
			if(statTypes[i]==type&skills.get(skills.size()-1).getIsActive()) 
			{
				modifiedBaseStats[i]=skills.get(skills.size()-1).apply(modifiedBaseStats[i]);
			}
		}
	}
	private void applyAllSkills() 
	{
		modifiedBaseStats=Arrays.copyOf(baseStats, baseStats.length);
		for(int i=0;i<skills.size();i++) 
		{
			if(!skills.get(i).getIsActive()) 
			{
				continue;
			}
			StatType type=skills.get(i).getType();
			for(int j=0;j<statTypes.length;j++) 
			{
				if(type==statTypes[i]) 
				{
					modifiedBaseStats[i]=skills.get(i).apply(modifiedBaseStats[i]);
				}else if(statTypes[i]==StatType.MULTIPLE) 
				{
					skills.get(i).apply(statTypes, modifiedBaseStats);
				}
			}
		}
	}
	private void addSkill(int index,int[] values,int[] tiers) 
	{
		StatType type=SKILL_TYPES[index][0];
		if(SKILL_TYPES[index].length!=1) 
		{
			
			skills.add(new MultipleSkill(StatType.MULTIPLE,SKILL_NAMES[index],SKILL_TYPES[index],values,tiers,true));
		}
		skills.add(new Skill(type,SKILL_NAMES[index],values[0],tiers[0],true));
	}
	private void generateSkill(int index,int tier) 
	{
		Random rng=new Random();
		int[] values=new int[SKILL_TYPES[index].length];
		for(int i=0;i<SKILL_TYPES[index].length;i++) 
		{
			values[i]=rng.nextInt(SKILL_RANGE[index][i][1]-SKILL_RANGE[index][i][0]+1)+SKILL_RANGE[index][i][0];
		}
		int[] tiers=new int[values.length];
		Arrays.fill(tiers, tier);
		addSkill(index,values,tiers);		
		
	}
	public GenericSkill[] skillSelection(int skillCount) 
	{
		GenericSkill[] availableSkills=new GenericSkill[skillCount];
		Random rng=new Random();
		for(int j=0;j<skillCount;j++) 
		{
			int index=rng.nextInt(skills.size()+1);
			int[] values=new int[SKILL_TYPES[index].length];
			for(int i=0;i<SKILL_TYPES[index].length;i++) 
			{
				values[i]=rng.nextInt(SKILL_RANGE[index][i][1]-SKILL_RANGE[index][i][0]+1)+SKILL_RANGE[index][i][0];
			}
			int[] tiers=new int[values.length];
			Arrays.fill(tiers, 1);
			StatType type=SKILL_TYPES[index][0];
			
			if(SKILL_TYPES[index].length!=1) 
			{
				
				availableSkills[j]=(new MultipleSkill(StatType.MULTIPLE,SKILL_NAMES[index],SKILL_TYPES[index],values,tiers,false));
			}
			availableSkills[j]=(new Skill(type,SKILL_NAMES[index],values[0],tiers[0],false));
		}
		return availableSkills;
	}
	

	public void render (Graphics2D g,SkillTreeRenderMode mode,int time,JPanel panel)
	{
		if(mode==SkillTreeRenderMode.SKILL_SELECTION) 
		{
			int numSkills=time/120;
			if(numSkills>7) 
			{
				numSkills=7;
			}
			GenericSkill[] availableSkills=skillSelection(time/120);
			Button[] skillButtons=new Button[availableSkills.length];
			for(int i=0;i<skillButtons.length;i++) 
			{
				int offsetX=0;
				int offsetY=0;
				switch(i) 
				{
					case 1:
						offsetX=0;
						offsetY=-150;
						break;
					case 2:
						offsetX=(int)(150*Math.cos(Math.PI/6));
						offsetY=(int)(150*Math.sin(Math.PI/6));
						break;
					case 3:
						offsetX=(int)(150*Math.cos(Math.PI/6));
						offsetY=-(int)(150*Math.sin(Math.PI/6));
						break;
					case 4:
						offsetX=0;
						offsetY=150;
						break;
					case 5:
						offsetX=-(int)(150*Math.cos(Math.PI/6));
						offsetY=-(int)(150*Math.sin(Math.PI/6));
						break;
					case 6:
						offsetX=-(int)(150*Math.cos(Math.PI/6));
						offsetY=(int)(150*Math.sin(Math.PI/6));
						break;
				}
				Point[] points={new Point(413+offsetX,310+offsetY),	new Point(338+offsetX,310+offsetY),new Point(300+offsetX,375+offsetY),new Point(338+offsetX,440+offsetY),new Point(412+offsetX,440+offsetY),new Point(450+offsetX,375+offsetY)};
				skillButtons[i]=new Button(points,Color.CYAN,availableSkills[i].getName()+" "+availableSkills[i].getTier());
				skillButtons[i].setBounds(skillButtons[i].getX(), skillButtons[i].getY(),(int) skillButtons[i].getPreferredSize().getWidth(),(int) skillButtons[i].getPreferredSize().getHeight());
				panel.add(skillButtons[i]);
				skillButtons[i].draw(g, offsetX, offsetY);
				
			}
		}
	}
	
	
	
	 
	
	
	public ArrayList<GenericSkill> getSkills()
	{
		return skills;
	}

	
	
	

}
