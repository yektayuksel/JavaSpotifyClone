import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AddExistingAlbumArtist extends JPanel implements MouseListener,ActionListener
{
	JLabel selectAristLabel;
	JComboBox<CBItem> selectArtistCB;
	JLabel selectAlbumLabel;
	JComboBox<CBItem> selectAlbumCB;
	JLabel selectOtherArtistLabel;
	JComboBox<CBItem> selectOtherArtistCB;
	
	JButton addButton;
	AddExistingAlbumArtist() throws SQLException
	{
		this.setBackground(new Color(33,33,33));
		initArtistCB();
		initAlbumCB();
		initComboBoxes();
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(500,400));
	}
	public void initArtistCB() 
	{
		
		selectAristLabel = new JLabel();
		selectAristLabel.setText("Select the artist");
		selectAristLabel.setBounds(50,50, 250, 30);
		selectAristLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		selectAristLabel.setForeground(Color.white);
		this.add(selectAristLabel);
		
		selectArtistCB = new JComboBox<CBItem>();
		selectArtistCB.setEditable(true);
		selectArtistCB.setBounds(50, 90, 400, 30);
		selectArtistCB.addActionListener(this);
		this.add(selectArtistCB);
	}
	public void initAlbumCB()
	{
		selectAlbumLabel = new JLabel();
		selectAlbumLabel.setText("Select the Album");
		selectAlbumLabel.setBounds(50,140, 250, 30);
		selectAlbumLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		selectAlbumLabel.setForeground(Color.white);
		this.add(selectAlbumLabel);
		selectAlbumCB = new JComboBox<CBItem>();
		selectAlbumCB.setEditable(true);
		selectAlbumCB.setBounds(50, 180, 400, 30);
		selectAlbumCB.addActionListener(this);
		this.add(selectAlbumCB);
		
		selectOtherArtistLabel = new JLabel();
		selectOtherArtistLabel.setText("Select an artist to add");
		selectOtherArtistLabel.setBounds(50,230, 300, 30);
		selectOtherArtistLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		selectOtherArtistLabel.setForeground(Color.white);
		this.add(selectOtherArtistLabel);
		selectOtherArtistCB = new JComboBox<CBItem>();
		selectOtherArtistCB.setEditable(true);
		selectOtherArtistCB.setBounds(50, 270, 400, 30);
		selectOtherArtistCB.addActionListener(this);
		this.add(selectOtherArtistCB);
		
		addButton = new JButton();
		addButton.setBounds(350, 330, 100, 30);
		addButton.setFont(new Font("Consolas", Font.BOLD, 20));
		addButton.setForeground(Color.white);
		addButton.setText("Add");
		addButton.setBackground(this.getBackground().brighter());
		addButton.setFocusable(false);
		addButton.addMouseListener(this);
		this.add(addButton);
		
	}
	
	public void initComboBoxes() throws SQLException
	{
		//selectArtistCB
		ResultSet rs = SpotifyDB.getAllArtists();
		while(rs.next())
		{
			CBItem item = new CBItem(rs.getString("ArtistID"), rs.getString("ArtistName"));
			selectArtistCB.addItem(item);
			selectOtherArtistCB.addItem(item);
		}
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == selectArtistCB)
		{
			try 
			{
				ResultSet rs = SpotifyDB.getAllAlbums(((CBItem)selectArtistCB.getSelectedItem()).getID());
				selectAlbumCB.removeAllItems();
				while(rs.next())
				{
					selectAlbumCB.addItem(new CBItem(rs.getString("AlbumID"), rs.getString("AlbumName")));
				}
				repaint();
			} 
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() == addButton)
		{
			String albumID = ((CBItem)selectAlbumCB.getSelectedItem()).getID();
			String artistID = ((CBItem)selectOtherArtistCB.getSelectedItem()).getID();
			
			try {
				SpotifyDB.addAnArtistToAnExistingAlbum(artistID, albumID);
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
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
