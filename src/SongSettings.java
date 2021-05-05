import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SongSettings extends JPanel implements MouseListener,ActionListener
{
	
	JComboBox<CBItem> songToDeleteBox;
	JLabel songToDeleteLabel;
	JButton deleteButton;
	
	JLabel addSongLabel;
	JLabel selectArtistLabel;
	JLabel selectAlbumLabel;
	JComboBox<CBItem> albumNameBox;
	JComboBox<CBItem> artistNameBox;
	JTextField songNameText;
	JTextField genreText;
	JTextField durationText;
	JButton addButton;
	JButton albumSettingsButton;
	
	static final int GENERAL_OBJ_LOC_Y = 250;
	static final int GENERAL_OBJ_LOC_X = 420;
	SongSettings()
	{
		this.setBackground(new Color(33,33,33));
		initDeleteSettings();
		initAddingSettings();
		initMenuButtons();
		initArtistComboBoxes();
		this.add(songToDeleteBox);
		this.add(songToDeleteLabel);
		this.add(deleteButton);
		this.add(addSongLabel);
		this.add(selectArtistLabel);
		this.add(artistNameBox);
		this.add(selectAlbumLabel);
		this.add(albumNameBox);
		this.add(songNameText);
		this.add(genreText);
		this.add(durationText);
		this.add(addButton);
		this.add(albumSettingsButton);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1280,720));
	}
	
	
	public void initAddingSettings()
	{
		addSongLabel = new JLabel();
		addSongLabel.setText("Add a song");
		addSongLabel.setBounds(GENERAL_OBJ_LOC_X,GENERAL_OBJ_LOC_Y, 500, 30);
		addSongLabel.setFont(new Font("Consolas", Font.BOLD, 30));
		addSongLabel.setForeground(Color.white);
		
		selectArtistLabel = new JLabel();
		selectArtistLabel.setText("Select the artist");
		selectArtistLabel.setBounds(GENERAL_OBJ_LOC_X,GENERAL_OBJ_LOC_Y+50, 500, 30);
		selectArtistLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		selectArtistLabel.setForeground(Color.white);
		
		artistNameBox = new JComboBox();
		artistNameBox.setEditable(true);
		artistNameBox.setBounds(GENERAL_OBJ_LOC_X, GENERAL_OBJ_LOC_Y +90, 450, 30);
		artistNameBox.addActionListener(this);
		
		selectAlbumLabel = new JLabel();
		selectAlbumLabel.setText("Select the album");
		selectAlbumLabel.setBounds(GENERAL_OBJ_LOC_X,GENERAL_OBJ_LOC_Y+140, 500, 30);
		selectAlbumLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		selectAlbumLabel.setForeground(Color.white);
		
		albumNameBox = new JComboBox();
		albumNameBox.setEditable(true);
		albumNameBox.setBounds(GENERAL_OBJ_LOC_X, GENERAL_OBJ_LOC_Y +190, 450, 30);
		
		songNameText = new JTextField();
		songNameText.setBounds(GENERAL_OBJ_LOC_X, GENERAL_OBJ_LOC_Y + 240, 450,30);
		songNameText.setFont(new Font("Consolas", Font.BOLD, 20));
		songNameText.setText("Enter Song Name");
		
		genreText = new JTextField();
		genreText.setBounds(GENERAL_OBJ_LOC_X, GENERAL_OBJ_LOC_Y + 290, 450,30);
		genreText.setFont(new Font("Consolas", Font.BOLD, 20));
		genreText.setText("Enter Genre");
		

		durationText = new JTextField();
		durationText.setBounds(GENERAL_OBJ_LOC_X, GENERAL_OBJ_LOC_Y + 340, 450,30);
		durationText.setFont(new Font("Consolas", Font.BOLD, 20));
		durationText.setText("Duration of the song");
		
		addButton = new JButton();
		addButton.setBounds(GENERAL_OBJ_LOC_X+350, GENERAL_OBJ_LOC_Y + 390, 100, 30);
		addButton.setFont(new Font("Consolas", Font.BOLD, 20));
		addButton.setForeground(Color.white);
		addButton.setText("Add");
		addButton.setBackground(this.getBackground().brighter());
		addButton.setFocusable(false);
		addButton.addMouseListener(this);
		
		
		
	}
	public void initDeleteSettings()
	{
		songToDeleteBox = new JComboBox();
		songToDeleteBox.setEditable(true);
		songToDeleteBox.setBounds(GENERAL_OBJ_LOC_X, GENERAL_OBJ_LOC_Y - 150, 450, 30);
		
		songToDeleteLabel = new JLabel();
		songToDeleteLabel.setText("Select a song to delete");
		songToDeleteLabel.setBounds(GENERAL_OBJ_LOC_X,GENERAL_OBJ_LOC_Y - 200, 500, 30);
		songToDeleteLabel.setFont(new Font("Consolas", Font.BOLD, 30));
		songToDeleteLabel.setForeground(Color.white);
		
		deleteButton = new JButton();
		deleteButton.setBounds(GENERAL_OBJ_LOC_X+350, GENERAL_OBJ_LOC_Y - 100, 100, 30);
		deleteButton.setFont(new Font("Consolas", Font.BOLD, 20));
		deleteButton.setForeground(Color.white);
		deleteButton.setText("Delete");
		deleteButton.setBackground(this.getBackground().brighter());
		deleteButton.setFocusable(false);
		deleteButton.addMouseListener(this);
	}
	
	public void initMenuButtons()
	{
		
		
		albumSettingsButton = new JButton();
		albumSettingsButton.setBounds(60, GENERAL_OBJ_LOC_Y+360,  250, 50);
		albumSettingsButton.setFont(new Font("Consolas", Font.BOLD, 25));
		albumSettingsButton.setForeground(Color.white);
		albumSettingsButton.setText("Album Settings");
		albumSettingsButton.setBackground(this.getBackground().brighter());
		albumSettingsButton.setFocusable(false);
		albumSettingsButton.addMouseListener(this);
		
	
	}
	
	public void initArtistComboBoxes()
	{
		try {
			artistNameBox.removeAllItems();

	        ResultSet rs = SpotifyDB.getAllArtists();

	        while(rs.next())
	        {                
				CBItem comItem = new CBItem(rs.getString("ArtistID"), rs.getString("ArtistName"));
				artistNameBox.addItem(comItem);
				

	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Error" + e  );
	    }
	}
	
	public void initAlbumComboBoxes()
	{
		/*artistNameBox
		albumNameBox*/
		try {
			albumNameBox.removeAllItems();
			
			CBItem item = (CBItem)artistNameBox.getSelectedItem();
	        ResultSet rs = SpotifyDB.getAllAlbums(item.getID());

	        while(rs.next())
	        {                
				CBItem comItem = new CBItem(rs.getString("AlbumID"), rs.getString("AlbumName"));
				albumNameBox.addItem(comItem);
				

	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Error" + e  );
	    }
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		if(e.getSource() == albumSettingsButton)
		{
			new Screen(new AlbumSettings());
			((Window) getRootPane().getParent()).dispose();
		}
		else if(e.getSource() == addButton)
		{
			
			CBItem cb = (CBItem)albumNameBox.getSelectedItem();
			String albumID = cb.getID(); 
			CBItem cb2 = (CBItem) artistNameBox.getSelectedItem();
			String artistID = cb2.getID();
			String songName = songNameText.getText();
			String genre = genreText.getText();
			String duration = durationText.getText();
			String releaseDate;
			try {
				releaseDate = SpotifyDB.getAlbumRelaseDate(albumID);
				SpotifyDB.addSong( songName,  artistID,  albumID,  genre,  duration,  releaseDate);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		JButton button = (JButton) e.getSource();
		changeEnteredBackground(button);
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		JButton button = (JButton) e.getSource();
		changeExitedBackground(button);	
	}
	public void changeEnteredBackground(JButton button)
	{
		button.setBackground(button.getBackground().darker());
	}
	
	
	public void changeExitedBackground(JButton button)
	{
		button.setBackground(this.getBackground().brighter());
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == artistNameBox);
		{
			initAlbumComboBoxes();
		}
		
	}

}
