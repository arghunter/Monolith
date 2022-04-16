package menu;
import java.awt.event.ActionEvent;

public class ButtonClickedEvent extends ActionEvent {

	public ButtonClickedEvent(Object source, String command) {
		super(source, 88888, command);
	}

}
