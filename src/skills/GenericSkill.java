package skills;

 enum SkillType
 {
	 SPEED,
	 STRENGTH,
	 ACCURACY,
	 HEALTH,
	 REGEN,
	 SHIELD,
	 ARMOR,
	 ATTACKSPEED,
	 POWER,
	 MULTIPLE,
	 MISC
 }
public abstract class GenericSkill {
	SkillType type;
	//Fields Percent increase/decrease
//	private int speed;
//	private int strength;//Affects melee weapons
//	private int accuracy;
//	private int health;
//	private int shield;
//	private int armor;
//	private int attackspeed;//In attacks per minute for my sanity
//	private int power;//Affects melee and ranged weapons
	
	
	public GenericSkill(SkillType type) 
	{
		this.type=type;
	}
	
	public abstract int apply(int value);
	
	
	
	
	
	
	

	
	
	

}
