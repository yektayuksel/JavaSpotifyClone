import javax.swing.JButton;

public class Button extends JButton
{
	public String ID;
	public String displayText;
	public String genre;
	
	public void setID(String iD) {
		ID = iD;
	}

	public Button()
	{
		
	}
	
	public Button(String ID, String displayText)
	{
		this.ID = ID;
		this.displayText = displayText;
		this.setText(displayText);
	}
	//Sadece songta
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getID() {
		return ID;
	}
	
	public String getDisplayText() {
		return displayText;
	}
	
	
}
