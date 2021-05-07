import javax.swing.JButton;

public class Button extends JButton
{
	String ID;
	String displayText;
	Button(String ID, String displayText)
	{
		this.ID = ID;
		this.displayText = displayText;
	}
	public String getID() {
		return ID;
	}
	
	public String getDisplayText() {
		return displayText;
	}
	
	
}
