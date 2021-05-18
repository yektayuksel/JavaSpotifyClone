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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class ArtistSettings extends JPanel implements MouseListener, ActionListener
{
	
	JComboBox<CBItem> artistNameBox;
	JLabel artistToDelete;
	JLabel addAnArtist;
	JButton deleteButton;
	JTextField enterArtistNameText;
	JButton addButton;
	JButton AlbumSettingsButton;
	static final int GENERAL_OBJ_LOC_Y = 360;
	static final int GENERAL_OBJ_LOC_X = 175;
	
	JComboBox<CBItem> countryCB;
	JButton addNewCountryButton;
	
	JButton updateExistingCountry;
	
	JLabel selectArtistNameToUpdate;
	JLabel selectArtistPropertyUpdateLabel;
	JLabel newValueLabel;
	JTextField newValueTxt;
	JButton updateButton;
	JComboBox<CBItem> selectArtistUpdateCB;
	JComboBox<String> selectArtistPropertyUpdateCB;
	JComboBox <CBItem> countryUpdateCB;
	ArtistSettings() throws SQLException
	{
		this.setBackground(new Color(33,33,33));
		initComboBoxes();
		initCreateArtist();
		addComponentsToPanel();
	    initUpdateSettings();
	    initCountryCB();
		try 
		{
			initArtistNameCB();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1280,720));
	}
	
	public void initCreateArtist()
	{
		addAnArtist = new JLabel();
		addAnArtist.setText("Add an artist:");
		addAnArtist.setBounds(GENERAL_OBJ_LOC_X,GENERAL_OBJ_LOC_Y, 500, 30);
		addAnArtist.setFont(new Font("Consolas", Font.BOLD, 30));
		addAnArtist.setForeground(Color.white);
		
		addButton = new JButton();
		addButton.setBounds(GENERAL_OBJ_LOC_X+250, GENERAL_OBJ_LOC_Y+150, 250, 40);
		addButton.setFont(new Font("Consolas", Font.BOLD, 20));
		addButton.setForeground(Color.white);
		addButton.setText("Add artist to db");
		addButton.setBackground(this.getBackground().brighter());
		addButton.setFocusable(false);
		addButton.addMouseListener(this);
		
		enterArtistNameText = new JTextField();
		enterArtistNameText.setBounds(GENERAL_OBJ_LOC_X, GENERAL_OBJ_LOC_Y + 50, 450,30);
		enterArtistNameText.setFont(new Font("Consolas", Font.BOLD, 20));
		enterArtistNameText.setText("Enter the Artist's name");
		
		AlbumSettingsButton = new JButton();
		AlbumSettingsButton.setBounds(825+175, 310+340,  250, 50);
		AlbumSettingsButton.setFont(new Font("Consolas", Font.BOLD, 25));
		AlbumSettingsButton.setForeground(Color.white);
		AlbumSettingsButton.setText("Album Settings");
		AlbumSettingsButton.setBackground(this.getBackground().brighter());
		AlbumSettingsButton.setFocusable(false);
		AlbumSettingsButton.addMouseListener(this);
		
		
	}
	public void initCountryCB() throws SQLException
	{
		countryCB = new JComboBox<CBItem>();
		initCountryCB2();
		countryCB.setBounds(GENERAL_OBJ_LOC_X, GENERAL_OBJ_LOC_Y + 100, 450,30);
		countryCB.setFont(new Font("Consolas", Font.BOLD, 25));
		this.add(countryCB);
		addNewCountryButton = new JButton();
		addNewCountryButton.setBounds(GENERAL_OBJ_LOC_X-50, GENERAL_OBJ_LOC_Y+150, 250,40);
		addNewCountryButton.setFont(new Font("Consolas", Font.BOLD, 20));
		addNewCountryButton.setForeground(Color.white);
		addNewCountryButton.setText("Add new country");
		addNewCountryButton.setBackground(this.getBackground().brighter());
		addNewCountryButton.setFocusable(false);
		addNewCountryButton.addMouseListener(this);
		this.add(addNewCountryButton);
		
		updateExistingCountry = new JButton();
		updateExistingCountry.setBounds(GENERAL_OBJ_LOC_X-50, GENERAL_OBJ_LOC_Y+200, 250,40);
		updateExistingCountry.setFont(new Font("Consolas", Font.BOLD, 20));
		updateExistingCountry.setForeground(Color.white);
		updateExistingCountry.setText("Update a country");
		updateExistingCountry.setBackground(this.getBackground().brighter());
		updateExistingCountry.setFocusable(false);
		updateExistingCountry.addMouseListener(this);
		this.add(updateExistingCountry);
	}
	public void initCountryCB2() throws SQLException
	{
		ResultSet rs = SpotifyDB.getAllCountires();
		countryCB.removeAllItems();
		while(rs.next())
		{
			countryCB.addItem(new CBItem(rs.getString("countryID"), rs.getString("country")));
		}
		repaint();
	}
	public void initUpdateSettings()
	{
		selectArtistNameToUpdate = new JLabel();
		selectArtistNameToUpdate.setText("Select an artist to update");
		selectArtistNameToUpdate.setBounds(GENERAL_OBJ_LOC_X+520 ,GENERAL_OBJ_LOC_Y - 200, 500, 30);
		selectArtistNameToUpdate.setFont(new Font("Consolas", Font.BOLD, 30));
		selectArtistNameToUpdate.setForeground(Color.white);
		this.add(selectArtistNameToUpdate);
		
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
		
		selectArtistUpdateCB.setEditable(true);
		selectArtistUpdateCB.setBounds(GENERAL_OBJ_LOC_X+520, GENERAL_OBJ_LOC_Y - 150, 450, 30);
		selectArtistUpdateCB.addActionListener(this);
		this.add(selectArtistUpdateCB);
		
		selectArtistPropertyUpdateLabel = new JLabel();
		selectArtistPropertyUpdateLabel.setText("Select a property to update ");
		selectArtistPropertyUpdateLabel.setBounds(GENERAL_OBJ_LOC_X+520,GENERAL_OBJ_LOC_Y - 100, 500, 30);
		selectArtistPropertyUpdateLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		selectArtistPropertyUpdateLabel.setForeground(Color.white);
		this.add(selectArtistPropertyUpdateLabel);
		
		selectArtistPropertyUpdateCB = new JComboBox<String>();
		selectArtistPropertyUpdateCB.addItem("Artist Name");
		selectArtistPropertyUpdateCB.addItem("Country");
		selectArtistPropertyUpdateCB.setEditable(true);
		selectArtistPropertyUpdateCB.setBounds(GENERAL_OBJ_LOC_X+520, GENERAL_OBJ_LOC_Y-50, 450, 30);
		selectArtistPropertyUpdateCB.addActionListener(this);
		this.add(selectArtistPropertyUpdateCB);
		
		newValueLabel = new JLabel();
		newValueLabel.setText("Enter the new value");
		newValueLabel.setBounds(GENERAL_OBJ_LOC_X+520,GENERAL_OBJ_LOC_Y, 500, 30);
		newValueLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		newValueLabel.setForeground(Color.white);
		this.add(newValueLabel);		
		
		newValueTxt = new JTextField();
		newValueTxt.setBounds(GENERAL_OBJ_LOC_X+520, GENERAL_OBJ_LOC_Y + 50, 450,30);
		newValueTxt.setFont(new Font("Consolas", Font.BOLD, 20));
		this.add(newValueTxt);
		newValueTxt.setVisible(false);
		newValueTxt.setEnabled(false);
		
		try {
			initCountryUpdateCB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		updateButton = new JButton();
		updateButton.setBounds(GENERAL_OBJ_LOC_X+870, GENERAL_OBJ_LOC_Y + 100, 100, 30);
		updateButton.setFont(new Font("Consolas", Font.BOLD, 20));
		updateButton.setForeground(Color.white);
		updateButton.setText("Update");
		updateButton.setBackground(this.getBackground().brighter());
		updateButton.setFocusable(false);
		updateButton.addMouseListener(this);
		this.add(updateButton);
	}
	public void initCountryUpdateCB() throws SQLException
	{
		countryUpdateCB = new JComboBox<CBItem>();
		countryUpdateCB.setBounds(GENERAL_OBJ_LOC_X+520, GENERAL_OBJ_LOC_Y + 50, 450,30);
		countryUpdateCB.setFont(new Font("Consolas", Font.BOLD, 20));
		countryUpdateCB.setVisible(false);
		countryUpdateCB.setEnabled(false);
		ResultSet rs = SpotifyDB.getAllCountires();
		while(rs.next())
		{
			countryUpdateCB.addItem(new CBItem(rs.getString("countryID"), rs.getString("country")));
		}
		this.add(countryUpdateCB);
	}
	public void initComboBoxes()
	{
		
		
		artistToDelete = new JLabel();
		artistToDelete.setText("Select an artist to delete:");
		artistToDelete.setBounds(GENERAL_OBJ_LOC_X,GENERAL_OBJ_LOC_Y - 200, 500, 30);
		artistToDelete.setFont(new Font("Consolas", Font.BOLD, 30));
		artistToDelete.setForeground(Color.white);
		
		deleteButton = new JButton();
		deleteButton.setBounds(GENERAL_OBJ_LOC_X+350, GENERAL_OBJ_LOC_Y - 100, 100, 30);
		deleteButton.setFont(new Font("Consolas", Font.BOLD, 20));
		deleteButton.setForeground(Color.white);
		deleteButton.setText("Delete");
		deleteButton.setBackground(this.getBackground().brighter());
		deleteButton.setFocusable(false);
		deleteButton.addMouseListener(this);
		
		artistNameBox = new JComboBox<CBItem>();
		artistNameBox.setEditable(true);
		artistNameBox.setBounds(GENERAL_OBJ_LOC_X, GENERAL_OBJ_LOC_Y - 150, 450, 30);
		artistNameBox.addActionListener(this);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void addComponentsToPanel()
	{
		this.add(artistNameBox);
		this.add(artistToDelete);
		this.add(deleteButton);
		this.add(addAnArtist);
		this.add(enterArtistNameText);
		this.add(addButton);
		this.add(AlbumSettingsButton);
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
	@Override
	public void mousePressed(MouseEvent e) 
	{
		
		if(e.getSource() == addNewCountryButton)
		{
			
			try 
			{
				SpotifyDB.addCountry();
				initCountryCB2();
			}
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			return;
		}
		
		else if(e.getSource() == addButton)
		{
			//artist tablosuna gerekli verilerle yeni eleman ekle
			String artistToAddName = enterArtistNameText.getText();
			String artistToAddCountry = ((CBItem)countryCB.getSelectedItem()).getID();
			try {
				SpotifyDB.addArtist(artistToAddName, artistToAddCountry);
				initArtistNameCB();
				repaint();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == deleteButton)
		{
			String artistToDelete =  ((CBItem) artistNameBox.getSelectedItem()).getID();
			try 
			{
				SpotifyDB.deleteArtist(artistToDelete);
				JOptionPane.showMessageDialog(null, "The artist has deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
				initArtistNameCB();
				repaint();
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == AlbumSettingsButton)
		{
			new Screen(new AlbumSettings());
			((Window) getRootPane().getParent()).dispose();
		}
		else if(e.getSource() == updateButton)
		{
			CBItem item = (CBItem)selectArtistUpdateCB.getSelectedItem();
			String str = selectArtistPropertyUpdateCB.getSelectedItem().toString();
			String newValue = newValueTxt.getText();
			
			try 
			{
				if(str.equals("Artist Name"))
				{
					SpotifyDB.updateArtistName(item.getID(), newValue);
				}
				else if (str.equals("Country"))
				{
					CBItem cntry = (CBItem)countryUpdateCB.getSelectedItem();
					SpotifyDB.updateArtistCountry(item.getID(), cntry.getID());
				}
				
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			repaint();
		}
		else if(e.getSource() == updateExistingCountry)
		{
			try {
				new Screen(new UpdateExistingCountry());
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
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == selectArtistPropertyUpdateCB)
		{
			String prp = selectArtistPropertyUpdateCB.getSelectedItem().toString();
			if(prp.equals("Artist Name"))
			{
				countryUpdateCB.setVisible(false);
				countryUpdateCB.setEnabled(false);
				newValueTxt.setVisible(true);
				newValueTxt.setEnabled(true);
			}
			else if(prp.equals("Country"))
			{
				newValueTxt.setVisible(false);
				newValueTxt.setEnabled(false);
				countryUpdateCB.setVisible(true);
				countryUpdateCB.setEnabled(true);
			}
		}
		
	}
	
	
	
}
