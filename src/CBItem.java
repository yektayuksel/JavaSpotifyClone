import javax.swing.JComboBox;

public class CBItem
{
	String ID;
	String text;
	
	CBItem(String ID, String ArtistName)
	{
		this.ID = ID;
		this.text = ArtistName;
	}
	public String getText() {
		return text;
	}




	
	

	
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	
	 @Override
	 public String toString() 
	 {
	    return text;
	 }
	
}
