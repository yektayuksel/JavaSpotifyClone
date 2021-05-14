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

public class AlbumSettings extends JPanel implements MouseListener,ActionListener
{
	static final int GENERAL_OBJ_LOC_Y = 310;
	static final int GENERAL_OBJ_LOC_X = 175;
	
	JComboBox<CBItem> albumNameBox;
	JLabel albumToDelete;
	JLabel artistNameToDelete;
	JComboBox<CBItem> artistToDeleteNameBox;
	JButton deleteButton;
	
	JLabel albumToAdd;
	JLabel selectAnArtistLabel;
	JComboBox<CBItem> artistNameBox;
	JTextField albumNameTxt;
	JTextField relaseDateTxt;
	JComboBox<String> genreCB;
	JButton addButton;
	
	JButton artistSettingsButton;
	JButton songSettingsButton;
	
	JLabel selectAnAlbumUpdateLabel;
	JLabel artistNameUpdate;
	JLabel selectAlbumPropertyUpdateLabel;
	JLabel newValueLabel;
	JLabel selectTheAlbumUpdateLabel;
	JComboBox<CBItem> selectTheAlbumUpdateCB;
	JTextField newValueTxt;
	JButton updateButton;
	JComboBox<CBItem> selectArtistUpdateCB;
	JComboBox<String> selectAlbumUpdatePropCB;
	
	AlbumSettings()
	{
		
		this.setBackground(new Color(33,33,33));
		initComboBoxes();
		initAddingSettings();
		initMenuButtons();
		initArtistComboBoxes(artistToDeleteNameBox);
		initArtistComboBoxes(artistNameBox);
		initUpdateSetting();
		try 
		{
			initArtistNameCB();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		this.add(albumNameBox);
		this.add(albumToDelete);
		this.add(deleteButton);
		this.add(albumToAdd);
		this.add(selectAnArtistLabel);
		this.add(artistNameBox);
		this.add(albumNameTxt);
		this.add(relaseDateTxt);
		this.add(genreCB);
		this.add(addButton);
		this.add(artistSettingsButton);
		this.add(songSettingsButton);
		this.add(artistNameToDelete);
		this.add(artistToDeleteNameBox);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1280,720));
	}
	public void initComboBoxes()
	{
		albumNameBox = new JComboBox<CBItem>();
		albumNameBox.setEditable(true);
		albumNameBox.setBounds(GENERAL_OBJ_LOC_X, GENERAL_OBJ_LOC_Y - 100, 450, 30);
		
		albumToDelete = new JLabel();
		albumToDelete.setText("Select an album to delete");
		albumToDelete.setBounds(GENERAL_OBJ_LOC_X,GENERAL_OBJ_LOC_Y  - 150, 500, 30);
		albumToDelete.setFont(new Font("Consolas", Font.BOLD, 30));
		albumToDelete.setForeground(Color.white);
		
		
		artistToDeleteNameBox = new JComboBox();
		artistToDeleteNameBox.setEditable(true);
		artistToDeleteNameBox.setBounds(GENERAL_OBJ_LOC_X, GENERAL_OBJ_LOC_Y - 200, 450, 30);
		artistToDeleteNameBox.addActionListener(this);
		
		artistNameToDelete = new JLabel();
		artistNameToDelete.setText("Select the artist");
		artistNameToDelete.setBounds(GENERAL_OBJ_LOC_X,GENERAL_OBJ_LOC_Y - 250, 500, 30);
		artistNameToDelete.setFont(new Font("Consolas", Font.BOLD, 30));
		artistNameToDelete.setForeground(Color.white);
		
		deleteButton = new JButton();
		deleteButton.setBounds(GENERAL_OBJ_LOC_X+350, GENERAL_OBJ_LOC_Y - 50, 100, 30);
		deleteButton.setFont(new Font("Consolas", Font.BOLD, 20));
		deleteButton.setForeground(Color.white);
		deleteButton.setText("Delete");
		deleteButton.setBackground(this.getBackground().brighter());
		deleteButton.setFocusable(false);
		deleteButton.addMouseListener(this);
	}
	
	public void initArtistNameCB() throws SQLException
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
	public void initAddingSettings()
	{
		albumToAdd = new JLabel();
		selectAnArtistLabel = new JLabel();
		artistNameBox = new JComboBox();
		albumNameTxt = new JTextField();
		relaseDateTxt = new JTextField();
		genreCB = new JComboBox<String>();
		
		
		albumToAdd.setText("Add an album");
		albumToAdd.setBounds(GENERAL_OBJ_LOC_X,GENERAL_OBJ_LOC_Y, 500, 30);
		albumToAdd.setFont(new Font("Consolas", Font.BOLD, 30));
		albumToAdd.setForeground(Color.white);
		
		selectAnArtistLabel.setText("Select the artist");
		selectAnArtistLabel.setBounds(GENERAL_OBJ_LOC_X,GENERAL_OBJ_LOC_Y+50, 500, 30);
		selectAnArtistLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		selectAnArtistLabel.setForeground(Color.white);
		

		artistNameBox.setEditable(true);
		artistNameBox.setBounds(GENERAL_OBJ_LOC_X, GENERAL_OBJ_LOC_Y +90, 450, 30);
		
		albumNameTxt.setBounds(GENERAL_OBJ_LOC_X, GENERAL_OBJ_LOC_Y + 140, 450,30);
		albumNameTxt.setFont(new Font("Consolas", Font.BOLD, 20));
		albumNameTxt.setText("Enter Album Name");
		
		relaseDateTxt.setBounds(GENERAL_OBJ_LOC_X, GENERAL_OBJ_LOC_Y + 190, 450,30);
		relaseDateTxt.setFont(new Font("Consolas", Font.BOLD, 20));
		relaseDateTxt.setText("Release date of the album");
		
		genreCB.setBounds(GENERAL_OBJ_LOC_X, GENERAL_OBJ_LOC_Y + 240, 450,30);
		genreCB.setFont(new Font("Consolas", Font.BOLD, 20));
		genreCB.addItem("Jazz");
		genreCB.addItem("Clasiccal");
		genreCB.addItem("Pop");
		
		addButton = new JButton();
		addButton.setBounds(GENERAL_OBJ_LOC_X+350, GENERAL_OBJ_LOC_Y +290, 100, 30);
		addButton.setFont(new Font("Consolas", Font.BOLD, 20));
		addButton.setForeground(Color.white);
		addButton.setText("Add");
		addButton.setBackground(this.getBackground().brighter());
		addButton.setFocusable(false);
		addButton.addMouseListener(this);
		
	}
	
	public void initMenuButtons()
	{
		
		
		artistSettingsButton = new JButton();
		artistSettingsButton.setBounds(30, GENERAL_OBJ_LOC_Y+340,  250, 50);
		artistSettingsButton.setFont(new Font("Consolas", Font.BOLD, 25));
		artistSettingsButton.setForeground(Color.white);
		artistSettingsButton.setText("Artist Settings");
		artistSettingsButton.setBackground(this.getBackground().brighter());
		artistSettingsButton.setFocusable(false);
		artistSettingsButton.addMouseListener(this);
		
		songSettingsButton = new JButton();
		songSettingsButton.setBounds(GENERAL_OBJ_LOC_X+825, GENERAL_OBJ_LOC_Y+340,  250, 50);
		songSettingsButton.setFont(new Font("Consolas", Font.BOLD, 25));
		songSettingsButton.setForeground(Color.white);
		songSettingsButton.setText("Song Settings");
		songSettingsButton.setBackground(this.getBackground().brighter());
		songSettingsButton.setFocusable(false);
		songSettingsButton.addMouseListener(this);
	}
	public void initUpdateSetting()
	{
		selectAnAlbumUpdateLabel = new JLabel();
		selectAnAlbumUpdateLabel.setText("Select an album to update");
		selectAnAlbumUpdateLabel.setBounds(GENERAL_OBJ_LOC_X+520 ,GENERAL_OBJ_LOC_Y - 260, 500, 30);
		selectAnAlbumUpdateLabel.setFont(new Font("Consolas", Font.BOLD, 30));
		selectAnAlbumUpdateLabel.setForeground(Color.white);
		this.add(selectAnAlbumUpdateLabel);
		
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
		artistNameUpdate.setBounds(GENERAL_OBJ_LOC_X+520,GENERAL_OBJ_LOC_Y - 210, 500, 30);
		artistNameUpdate.setFont(new Font("Consolas", Font.BOLD, 20));
		artistNameUpdate.setForeground(Color.white);
		this.add(artistNameUpdate);
		
		selectArtistUpdateCB.setEditable(true);
		selectArtistUpdateCB.setBounds(GENERAL_OBJ_LOC_X+520, GENERAL_OBJ_LOC_Y - 160, 450, 30);
		selectArtistUpdateCB.addActionListener(this);
		this.add(selectArtistUpdateCB);
		
		selectTheAlbumUpdateLabel = new JLabel();
		selectTheAlbumUpdateLabel.setText("Select the album");
		selectTheAlbumUpdateLabel.setBounds(GENERAL_OBJ_LOC_X+520,GENERAL_OBJ_LOC_Y - 110, 500, 30);
		selectTheAlbumUpdateLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		selectTheAlbumUpdateLabel.setForeground(Color.white);
		this.add(selectTheAlbumUpdateLabel);
		
		selectTheAlbumUpdateCB = new JComboBox<CBItem>();
		selectTheAlbumUpdateCB.setEditable(true);
		selectTheAlbumUpdateCB.setBounds(GENERAL_OBJ_LOC_X+520, GENERAL_OBJ_LOC_Y-60, 450, 30);
		selectTheAlbumUpdateCB.addActionListener(this);
		this.add(selectTheAlbumUpdateCB);
		
		selectAlbumPropertyUpdateLabel = new JLabel();
		selectAlbumPropertyUpdateLabel.setText("Select a property to update ");
		selectAlbumPropertyUpdateLabel.setBounds(GENERAL_OBJ_LOC_X+520,GENERAL_OBJ_LOC_Y + 90, 500, 30);
		selectAlbumPropertyUpdateLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		selectAlbumPropertyUpdateLabel.setForeground(Color.white);
		this.add(selectAlbumPropertyUpdateLabel);
		
		selectAlbumUpdatePropCB = new JComboBox<String>();
		selectAlbumUpdatePropCB.addItem("Album Name");
		selectAlbumUpdatePropCB.addItem("Genre");
		selectAlbumUpdatePropCB.addItem("Release Date");
		selectAlbumUpdatePropCB.setEditable(true);
		selectAlbumUpdatePropCB.setBounds(GENERAL_OBJ_LOC_X+520, GENERAL_OBJ_LOC_Y+140, 450, 30);
		selectAlbumUpdatePropCB.addActionListener(this);
		this.add(selectAlbumUpdatePropCB);
		
		
		newValueLabel = new JLabel();
		newValueLabel.setText("Enter the new value");
		newValueLabel.setBounds(GENERAL_OBJ_LOC_X+520,GENERAL_OBJ_LOC_Y + 190, 500, 30);
		newValueLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		newValueLabel.setForeground(Color.white);
		this.add(newValueLabel);		
		
		newValueTxt = new JTextField();
		newValueTxt.setBounds(GENERAL_OBJ_LOC_X+520, GENERAL_OBJ_LOC_Y + 240, 450,30);
		newValueTxt.setFont(new Font("Consolas", Font.BOLD, 20));
		this.add(newValueTxt);
		
		updateButton = new JButton();
		updateButton.setBounds(GENERAL_OBJ_LOC_X+870, GENERAL_OBJ_LOC_Y + 290, 100, 30);
		updateButton.setFont(new Font("Consolas", Font.BOLD, 20));
		updateButton.setForeground(Color.white);
		updateButton.setText("Update");
		updateButton.setBackground(this.getBackground().brighter());
		updateButton.setFocusable(false);
		updateButton.addMouseListener(this);
		this.add(updateButton);
	}
	public void initAlbumComboBoxes()
	{
		/*artistNameBox
		albumNameBox*/
		try {
			albumNameBox.removeAllItems();
			
			CBItem item = (CBItem)artistToDeleteNameBox.getSelectedItem();
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
	public void initArtistComboBoxes(JComboBox cb)
	{
		try {
			cb.removeAllItems();

	        ResultSet rs = SpotifyDB.getAllArtists();

	        while(rs.next())
	        {                
				CBItem comItem = new CBItem(rs.getString("ArtistID"), rs.getString("ArtistName"));
				cb.addItem(comItem);
				

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
		
		if(e.getSource() == addButton)
		{
			CBItem id = (CBItem)artistNameBox.getSelectedItem();
			String artistID = id.getID();
			String albumName = albumNameTxt.getText();
			String releaseDate  = relaseDateTxt.getText();
			String genre = genreCB.getSelectedItem().toString();
			try 
			{
				SpotifyDB.addAlbum(albumName, artistID,releaseDate,genre);
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			initAlbumComboBoxes();
		}
		else if(e.getSource() == deleteButton)
		{
			CBItem album = (CBItem) albumNameBox.getSelectedItem();
			String albumID = album.getID();
			try 
			{
				SpotifyDB.deleteAlbum(albumID);
				JOptionPane.showMessageDialog(null, "The album has deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
				initAlbumComboBoxes();
				repaint();
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == artistSettingsButton)
		{
			new Screen(new ArtistSettings());
			((Window) getRootPane().getParent()).dispose();
		}
		else if(e.getSource() == songSettingsButton)
		{
			try {
				new Screen(new SongSettings());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			((Window) getRootPane().getParent()).dispose();
		}
		else if(e.getSource() == updateButton)
		{
			CBItem item = (CBItem)selectTheAlbumUpdateCB.getSelectedItem();
			String str = selectAlbumUpdatePropCB.getSelectedItem().toString();
			String newValue = newValueTxt.getText();
			
			try 
			{
				if(str.equals("Album Name"))
				{
					SpotifyDB.updateAlbumName(item.getID(), newValue);
				}
				else if (str.equals("Genre"))
				{
					SpotifyDB.updateAlbumGenre(item.getID(), newValue);
				}
				else if(str.equals("Release Date"))
				{
					SpotifyDB.updateAlbumReleaseDate(item.getID(), newValue);
				}
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			repaint();
			
			
				
				
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
		if(e.getSource() == artistToDeleteNameBox)
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
		
	}

}
