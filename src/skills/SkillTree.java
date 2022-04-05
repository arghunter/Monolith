package skills;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SkillTree {
	private ArrayList<GenericSkill> skills=new ArrayList<GenericSkill>();
	public final String[] SKILL_NAMES= {"Acurracy","Armor","Attack Speed","Health","Power","Regen","Shield","Speed","Strength", "Tank"};//,"Marksman","Sword Master","Curse","Master","Sprinter","Weight Training","Eagle Eyes","Recovery","Shield Master","Armor Master", "Greased Lightning", "Sharpened Steel"};
	public final SkillType[][] SKILL_TYPES=  { { SkillType.ACCURACY}, {SkillType.ARMOR },{SkillType.ATTACKSPEED},{SkillType.HEALTH},{SkillType.POWER},{SkillType.REGEN},{SkillType.SHIELD},{SkillType.SPEED},{SkillType.STRENGTH},{SkillType.HEALTH,SkillType.SHIELD,SkillType.REGEN,SkillType.STRENGTH,SkillType.SPEED}};
	public final int[][][] SKILL_RANGE= {{{2,8}},{{5,15}},{{5,15}},{{8,16}},{{2,16}},{{5,15}},{{8,16}},{{2,8}},{{2,16}},{{25,35},{20,30},{10,20},{2,5},{-20,-10}}} ;
	public SkillTree(String saveData) 
	{
		
	}
	public SkillTree() 
	{
		
	}
	public SkillTree(SkillTree tree) 
	{
		this.skills=tree.skills;
	}
	
	private void addSkill(int index,int[] values,int[] tiers) 
	{
		SkillType type=SKILL_TYPES[index][0];
		if(SKILL_TYPES[index].length!=1) 
		{
			
			skills.add(new MultipleSkill(SkillType.MULTIPLE,SKILL_NAMES[index],SKILL_TYPES[index],values,tiers));
		}
		skills.add(new Skill(type,SKILL_NAMES[index],values[0],tiers[0]));
	}
	public void generateSkill(int index,int tier) 
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
	
	 
	
	
	public ArrayList<GenericSkill> getSkills()
	{
		return skills;
	}
	
	public static void main(String[] args) {
		

		
	}
	
	
	

}
