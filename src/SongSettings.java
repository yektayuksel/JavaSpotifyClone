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
	JComboBox<String> genreCB;
	JTextField durationText;
	JButton addButton;
	JButton albumSettingsButton;
	
	JLabel selectASongUpdateLabel;
	JLabel artistNameUpdate;
	JLabel songNameUpdate;
	JLabel selectSongPropertyUpdateLabel;
	JLabel newValueLabel;
	JLabel selectTheAlbumUpdateLabel;
	JComboBox<CBItem> selectTheAlbumUpdateCB;
	JTextField newValueTxt;
	JButton updateButton;
	JComboBox<CBItem> selectArtistUpdateCB;
	JComboBox<CBItem> selectSongUpdateCB;
	JComboBox<String> selectSongPropertyUpdateCB;
	
	static final int GENERAL_OBJ_LOC_Y = 250;
	static final int GENERAL_OBJ_LOC_X = 175;
	SongSettings() throws SQLException
	{
		this.setBackground(new Color(33,33,33));
		initAll();
		this.add(songToDeleteBox);
		this.add(songToDeleteLabel);
		this.add(deleteButton);
		this.add(addSongLabel);
		this.add(selectArtistLabel);
		this.add(artistNameBox);
		this.add(selectAlbumLabel);
		this.add(albumNameBox);
		this.add(songNameText);
		this.add(genreCB);
		this.add(durationText);
		this.add(addButton);
		this.add(albumSettingsButton);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1280,720));
	}
	
	public void initAll() throws SQLException
	{
		initDeleteSettings();
		initAddingSettings();
		initMenuButtons();
		initArtistComboBoxes();
		initUpdateSettings();
		initSongToDeleteCB();
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
		
		genreCB = new JComboBox<String>();
		genreCB.setBounds(GENERAL_OBJ_LOC_X, GENERAL_OBJ_LOC_Y + 290, 450,30);
		genreCB.setFont(new Font("Consolas", Font.BOLD, 20));
		genreCB.addItem("Jazz");
		genreCB.addItem("Clasiccal");
		genreCB.addItem("Pop");
		

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
	
	public void initUpdateSettings()
	{
		selectASongUpdateLabel = new JLabel();
		selectASongUpdateLabel.setText("Select a song to update");
		selectASongUpdateLabel.setBounds(GENERAL_OBJ_LOC_X+520 ,GENERAL_OBJ_LOC_Y - 200, 500, 30);
		selectASongUpdateLabel.setFont(new Font("Consolas", Font.BOLD, 30));
		selectASongUpdateLabel.setForeground(Color.white);
		this.add(selectASongUpdateLabel);
		
		selectArtistUpdateCB = new JComboBox<CBItem>();
		try 
		{
			selectArtistUpdateCB.removeAllItems();
	        ResultSet rs = SpotifyDB.getAllArtists();

	        while(rs.next())
	        {                
				CBItem comItem = new CBItem(rs.getString("ArtistID"), rs.getString("ArtistName"));
				selectArtistUpdateCB.addItem(comItem);
				

	        }
	    }
		catch (Exception e) 
		{
	        JOptionPane.showMessageDialog(this, "Error" + e  );
	    }
		
		artistNameUpdate = new JLabel();
		artistNameUpdate.setText("Select the artist");
		artistNameUpdate.setBounds(GENERAL_OBJ_LOC_X+520,GENERAL_OBJ_LOC_Y - 150, 500, 30);
		artistNameUpdate.setFont(new Font("Consolas", Font.BOLD, 20));
		artistNameUpdate.setForeground(Color.white);
		this.add(artistNameUpdate);
		
		selectArtistUpdateCB.setEditable(true);
		selectArtistUpdateCB.setBounds(GENERAL_OBJ_LOC_X+520, GENERAL_OBJ_LOC_Y - 100, 450, 30);
		selectArtistUpdateCB.addActionListener(this);
		this.add(selectArtistUpdateCB);
		
		selectTheAlbumUpdateLabel = new JLabel();
		selectTheAlbumUpdateLabel.setText("Select the album");
		selectTheAlbumUpdateLabel.setBounds(GENERAL_OBJ_LOC_X+520,GENERAL_OBJ_LOC_Y - 50, 500, 30);
		selectTheAlbumUpdateLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		selectTheAlbumUpdateLabel.setForeground(Color.white);
		this.add(selectTheAlbumUpdateLabel);
		
		selectTheAlbumUpdateCB = new JComboBox<CBItem>();
		selectTheAlbumUpdateCB.setEditable(true);
		selectTheAlbumUpdateCB.setBounds(GENERAL_OBJ_LOC_X+520, GENERAL_OBJ_LOC_Y, 450, 30);
		selectTheAlbumUpdateCB.addActionListener(this);
		this.add(selectTheAlbumUpdateCB);
		
		songNameUpdate = new JLabel();
		songNameUpdate.setText("Select the song");
		songNameUpdate.setBounds(GENERAL_OBJ_LOC_X+520,GENERAL_OBJ_LOC_Y +50, 500, 30);
		songNameUpdate.setFont(new Font("Consolas", Font.BOLD, 20));
		songNameUpdate.setForeground(Color.white);
		this.add(songNameUpdate);
		
		selectSongUpdateCB = new JComboBox<CBItem>();
		selectSongUpdateCB.setEditable(true);
		selectSongUpdateCB.setBounds(GENERAL_OBJ_LOC_X+520, GENERAL_OBJ_LOC_Y+100, 450, 30);
		selectSongUpdateCB.addActionListener(this);
		this.add(selectSongUpdateCB);
		
		selectSongPropertyUpdateLabel = new JLabel();
		selectSongPropertyUpdateLabel.setText("Select a property to update ");
		selectSongPropertyUpdateLabel.setBounds(GENERAL_OBJ_LOC_X+520,GENERAL_OBJ_LOC_Y + 150, 500, 30);
		selectSongPropertyUpdateLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		selectSongPropertyUpdateLabel.setForeground(Color.white);
		this.add(selectSongPropertyUpdateLabel);
		
		selectSongPropertyUpdateCB = new JComboBox<String>();
		selectSongPropertyUpdateCB.addItem("Song name");
		selectSongPropertyUpdateCB.addItem("Genre");
		selectSongPropertyUpdateCB.addItem("Duration");
		selectSongPropertyUpdateCB.setEditable(true);
		selectSongPropertyUpdateCB.setBounds(GENERAL_OBJ_LOC_X+520, GENERAL_OBJ_LOC_Y+200, 450, 30);
		selectSongPropertyUpdateCB.addActionListener(this);
		this.add(selectSongPropertyUpdateCB);
		
		
		newValueLabel = new JLabel();
		newValueLabel.setText("Enter the new value");
		newValueLabel.setBounds(GENERAL_OBJ_LOC_X+520,GENERAL_OBJ_LOC_Y + 250, 500, 30);
		newValueLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		newValueLabel.setForeground(Color.white);
		this.add(newValueLabel);		
		
		newValueTxt = new JTextField();
		newValueTxt.setBounds(GENERAL_OBJ_LOC_X+520, GENERAL_OBJ_LOC_Y + 300, 450,30);
		newValueTxt.setFont(new Font("Consolas", Font.BOLD, 20));
		this.add(newValueTxt);
		
		updateButton = new JButton();
		updateButton.setBounds(GENERAL_OBJ_LOC_X+870, GENERAL_OBJ_LOC_Y + 350, 100, 30);
		updateButton.setFont(new Font("Consolas", Font.BOLD, 20));
		updateButton.setForeground(Color.white);
		updateButton.setText("Update");
		updateButton.setBackground(this.getBackground().brighter());
		updateButton.setFocusable(false);
		updateButton.addMouseListener(this);
		this.add(updateButton);
		
	}
	public void initMenuButtons()
	{
		
		
		albumSettingsButton = new JButton();
		albumSettingsButton.setBounds(30, GENERAL_OBJ_LOC_Y+400,  250, 50);
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
	public void initSongToDeleteCB() throws SQLException
	{
		//songToDeleteBox
		ResultSet rs = SpotifyDB.getAllSongs();
		while(rs.next())
		{
			songToDeleteBox.addItem(new CBItem(rs.getString("SongID"), rs.getString("SongName")));
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
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
			String genre = genreCB.getSelectedItem().toString();
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
		else if(e.getSource() == deleteButton)
		{
			CBItem song = (CBItem)songToDeleteBox.getSelectedItem();
			String songID = song.getID();
			try 
			{
				SpotifyDB.deleteSong(songID);
				JOptionPane.showMessageDialog(null, "The song has deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
			} 
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == updateButton)
		{
			String str = selectSongPropertyUpdateCB.getSelectedItem().toString();
			String newValue = newValueTxt.getText(); 
			CBItem item = (CBItem) selectSongUpdateCB.getSelectedItem();
			
			try 
			{
				if(str.equals("Song name"))
				{
					SpotifyDB.updateSongName(item.getID(), newValue);
				}
				else if(str.equals("Genre"))
				{
					SpotifyDB.updateSongGenre(item.getID(), newValue);
				}
				else if(str.equals("Duration"))
				{
					SpotifyDB.updateSongDuration(item.getID(), newValue);
				}
					
			} 
			catch (SQLException e1) 
			{
					e1.printStackTrace();
			}
			try 
			{
				initAll();
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			repaint();
			
		}
		else if(e.getSource() == deleteButton)
		{
			CBItem item = (CBItem)songToDeleteBox.getSelectedItem();
			try 
			{
				SpotifyDB.deleteSong(item.getID());
			} 
			catch (SQLException e1) 
			{
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
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == artistNameBox);
		{
			initAlbumComboBoxes();
		}
		if(e.getSource() == selectArtistUpdateCB)
		{
			CBItem item = (CBItem) selectArtistUpdateCB.getSelectedItem(); 
			
			try 
			{
				selectTheAlbumUpdateCB.removeAllItems();
				ResultSet rs = SpotifyDB.getAllAlbums(item.getID());
                while(rs.next())
                {
                	selectTheAlbumUpdateCB.addItem(new CBItem(rs.getString("AlbumID"), rs.getString("AlbumName")));
                }
                repaint();
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		if(e.getSource() == selectTheAlbumUpdateCB)
		{
			CBItem item = (CBItem) selectTheAlbumUpdateCB.getSelectedItem(); 
			selectSongUpdateCB.removeAll();
			try 
			{
				ResultSet rs = SpotifyDB.getAlbumSongs(item.getID());
				selectSongUpdateCB.removeAllItems();
                while(rs.next())
                {
                	selectSongUpdateCB.addItem(new CBItem(rs.getString("SongID"), rs.getString("SongName")));
                }
               
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			repaint();
		}
		
		
	}

}
