//Author: Adithya Giri 
//Date: 5/11/22
//Rev: 01
//Notes: An exception that is thrown when the arsenal is full.
package GameObjects.Player;

public class ArsenalFullException extends Exception {
	
	public ArsenalFullException(String errorMessage) 
	{
		super(errorMessage);
	}

}
