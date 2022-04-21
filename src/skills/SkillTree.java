package skills;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JPanel;

import ui.Button;



public class SkillTree {
	private int[] baseStats;
	private int[] modifiedBaseStats;
	private StatType[] statTypes;
	private ArrayList<GenericSkill> skills=new ArrayList<GenericSkill>();
	public final String[] SKILL_NAMES= {"Accuracy","Armor","Attack Speed","Health","Power","Regen","Shield","Speed","Strength", "Tank","Marksman","Sword Master","Curse","Master","Sprinter","Weight Training","Eagle Eyes","Recovery","Shield Master","Armor Master", "Greased Lightning", "Sharpened Steel"};
	public final StatType[][] SKILL_TYPES=  { { StatType.ACCURACY}, {StatType.ARMOR },{StatType.ATTACKSPEED},{StatType.HEALTH},{StatType.POWER},{StatType.REGEN},{StatType.SHIELD},{StatType.SPEED},{StatType.STRENGTH},{StatType.HEALTH,StatType.SHIELD,StatType.REGEN,StatType.STRENGTH,StatType.SPEED},{StatType.ACCURACY,StatType.POWER},{StatType.STRENGTH},{StatType.ACCURACY,StatType.ARMOR,StatType.ATTACKSPEED,StatType.HEALTH,StatType.POWER,StatType.REGEN,StatType.SHIELD,StatType.SPEED,StatType.STRENGTH,StatType.XP},{StatType.ACCURACY,StatType.ARMOR,StatType.ATTACKSPEED,StatType.HEALTH,StatType.POWER,StatType.REGEN,StatType.SHIELD,StatType.SPEED,StatType.STRENGTH,StatType.XP},{StatType.SPEED},{StatType.STRENGTH, StatType.HEALTH},{StatType.ACCURACY},{StatType.REGEN},{StatType.SHIELD},{StatType.ARMOR},{StatType.ATTACKSPEED},{StatType.POWER,StatType.STRENGTH}};
	public final int[][][] SKILL_RANGE= {{{2,8}},{{5,15}},{{5,15}},{{8,16}},{{2,16}},{{5,15}},{{8,16}},{{2,8}},{{2,16}},{{25,35},{20,30},{10,20},{2,5},{-20,-10}},{{30,50},{15,30}},{{20,50}},{{-5,-5},{-5,-5},{-5,-5},{-5,-5},{-5,-5},{-5,-5},{-5,-5},{-5,-5},{-5,-5},{5,10}},{{5,5},{5,5},{5,5},{5,5},{5,5},{5,5},{5,5},{5,5},{5,5},{-5,-10}},{{12,24}},{{10,25},{5,10}},{{30,75}},{{30,40}},{{30,40}},{{30,40}},{{20,30}},{{20,25},{15,22}}};
	
	public SkillTree(String saveData,int[] baseStats,StatType[]statTypes) 
	{
		
		this.baseStats=Arrays.copyOf(baseStats, baseStats.length);
		this.modifiedBaseStats=baseStats;
		this.statTypes=statTypes;
		String[] skillStrings=saveData.split("{*}");
		for(int i=1;i<skillStrings.length;i++) 
		{
			
			if(skillStrings[i].contains("MULTIPLE")) 
			{
				
			}else 
			{
				this.addSkill(this.parseSkillData(skillStrings[i]));
				
			}
		}
		
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
		for(int i=0;i<statTypes.length;i++) 
		{
			if(statTypes[i]==StatType.XP) 
			{
				xp*=modifiedBaseStats[i]/100.0;
			}
		}
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
	public void applyLastAddedSkill() 
	{
		StatType type=skills.get(skills.size()-1).getType();
		if(type==StatType.MULTIPLE&&skills.get(skills.size()-1).getIsActive()) 
		{
			skills.get(skills.size()-1).apply(statTypes, modifiedBaseStats);
			return;
		}
		for(int i=0;i<baseStats.length;i++) 
		{
			if(statTypes[i]==type&&skills.get(skills.size()-1).getIsActive()) 
			{
				
				modifiedBaseStats[i]=skills.get(skills.size()-1).apply(modifiedBaseStats[i]);
			}
		}
		
		//System.out.println(Arrays.toString(modifiedBaseStats)+"   base");
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

	public void addSkill(GenericSkill skill) 
	{
		for(int i=0;i<skills.size();i++) 
		{
			if(skills.get(i).getName().equals(skill.getName())) 
			{
				skills.set(i, skill);
				return;
			}
		}
		skills.add(skill);
		applyLastAddedSkill();
	
	}

	public GenericSkill[] skillSelection(int skillCount) 
	{
		System.out.println(skillCount+" count");
		GenericSkill[] availableSkills=new GenericSkill[skillCount];
		Random rng=new Random();
		for(int j=0;j<skillCount;j++) 
		{
			int index=rng.nextInt(SKILL_NAMES.length);
			int[] values=new int[SKILL_TYPES[index].length];
			for(int i=0;i<SKILL_TYPES[index].length;i++) 
			{

				values[i]=rng.nextInt(Math.abs(SKILL_RANGE[index][i][1]-SKILL_RANGE[index][i][0]+1))+Math.abs(SKILL_RANGE[index][i][0]);
				if(SKILL_RANGE[index][i][1]<0) 
				{
					values[i]*=-1;
				}
			}
			int[] tiers=new int[values.length];
			
			StatType type=SKILL_TYPES[index][0];
			//System.out.println(SKILL_TYPES[index].length+" "+SKILL_NAMES[index]);
			if(SKILL_TYPES[index].length>1) 
			{
				
				availableSkills[j]=(new MultipleSkill(StatType.MULTIPLE,SKILL_NAMES[index],SKILL_TYPES[index],values,1,false));
			}else 
			{
				availableSkills[j]=(new Skill(type,SKILL_NAMES[index],values[0],1,false));
			}
			
		}
		return availableSkills;
	}
	
	private Skill parseSkillData(String skillData) 
	{
		String[] data=skillData.split("/");
		StatType type=null;
		switch(data[4]) 
		{
		case "ACCURACY":
			type=StatType.ACCURACY;
			break;
			 
		case "ARMOR":
			type=StatType.ARMOR;
			break;
			 
		case "ATTACKSPEED":
			type=StatType.ATTACKSPEED;
			break;
		case "HEALTH":
			type=StatType.HEALTH;
			break;
		case "POWER":
			type=StatType.POWER;
			break;
		case "REGEN":
			type=StatType.REGEN;
			break;
		case "SHIELD":
			type=StatType.SHIELD;
			break;
		case "SPEED":
			type=StatType.SPEED;
			break;
		case "STRENGTH":
			type=StatType.STRENGTH;
			break;
		case "XP":
			type=StatType.XP;
			break;
		case "MISC":
			type=StatType.MISC;
			break;
		}
		return new Skill(type,data[0].replace('_', ' '),Integer.parseInt(data[5]),Integer.parseInt(data[1]),Boolean.parseBoolean(data[2]));
	}


	
	@Override
	public String toString() 
	{
		String s="SkillTree{*}";
		for(int i=0;i<skills.size();i++) 
		{
			s+=skills.get(i).getName().replace(' ', '_')+"/"+skills.get(i).getTier()+"/"+skills.get(i).getIsActive()+"/"+skills.get(i).getXP()+"/"+skills.get(i).getType()+"/";
			if(skills.get(i).getType()!=StatType.MULTIPLE) 
			{
				Skill tempSkill=(Skill)skills.get(i);
				s+=tempSkill.getPercent()+"/END{*}";
			}else 
			{
				MultipleSkill tempSkill=(MultipleSkill) skills.get(i);
				Skill[] skillList=tempSkill.getSkills();
				s+="(";
				for(int j=0;j<skillList.length;j++) 
				{
					s+=skillList[j].getName()+"~"+skillList[j].getTier()+"~"+skillList[j].getIsActive()+"~"+skillList[j].getXP()+"~"+skillList[j].getType()+"~"+skillList[j].getPercent()+"~,~";
				}
				s+=")/END{*}";
			}
		}
		return s;
	}
	 
	
	
	public ArrayList<GenericSkill> getSkills()
	{
		return skills;
	}

	
	
	

}
