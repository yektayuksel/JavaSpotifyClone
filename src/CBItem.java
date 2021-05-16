import javax.swing.JComboBox;

public class CBItem
{
	String ID;
	String text;
	String genreID;
	CBItem(String ID, String ArtistName)
	{
		this.ID = ID;
		this.text = ArtistName;
	}
	CBItem(String ID, String ArtistName, String genreID)
	{
		this.ID = ID;
		this.text = ArtistName;
		this.genreID = genreID;
	}
	public String getText() {
		return text;
	}



	public String getGenreID() {
		return genreID;
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
