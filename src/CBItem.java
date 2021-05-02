import javax.swing.JComboBox;

public class CBItem
{
	String ID;
	String ArtistName;
	
	CBItem(String ID, String ArtistName)
	{
		this.ID = ID;
		this.ArtistName = ArtistName;
	}
	public String getArtistName() {
		return ArtistName;
	}

	public void setArtistName(String artistName) {
		ArtistName = artistName;
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
	    return ArtistName;
	 }
	
}
