//Author: Adithya Giri
//Date: 5/11/22
//Rev: 01
//Notes: An exception that is thrown when there is not enough resources to construct a blueprint
package GameObjects.Player.items.blueprints;

public class MissingResourcesException extends Exception {
	public MissingResourcesException(String message) 
	{
		super(message);
	}
}
