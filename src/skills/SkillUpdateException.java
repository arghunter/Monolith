//Author:Armaan Gomes
//5/8/22
//Rev: 01
//Notes: Represents an exception that tells the skillTree to update
package skills;

public class SkillUpdateException extends Exception {
	public SkillUpdateException(String errorMessage) 
	{
		super(errorMessage);
	}
}
