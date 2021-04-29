import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class ArtistSettings extends JPanel implements MouseListener
{
	
	JComboBox artistNameBox;
	JLabel artistToDelete;
	JLabel addAnArtist;
	JButton deleteButton;
	JTextField enterArtistNameText;
	JTextField enterArtistCountryText;
	JButton addButton;
	JButton AlbumSettingsButton;
	static final int GENERAL_OBJ_LOC_Y = 360;
	static final int GENERAL_OBJ_LOC_X = 420;
	
	ArtistSettings()
	{
		this.setBackground(new Color(33,33,33));
		initComboBoxes();
		initCreateArtist();
		
		
		
		this.add(artistNameBox);
		this.add(artistToDelete);
		this.add(deleteButton);
		this.add(addAnArtist);
		this.add(enterArtistNameText);
		this.add(enterArtistCountryText);
		this.add(addButton);
		this.add(AlbumSettingsButton);
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
		addButton.setBounds(GENERAL_OBJ_LOC_X+350, GENERAL_OBJ_LOC_Y+150, 100, 30);
		addButton.setFont(new Font("Consolas", Font.BOLD, 20));
		addButton.setForeground(Color.white);
		addButton.setText("Add");
		addButton.setBackground(this.getBackground().brighter());
		addButton.setFocusable(false);
		addButton.addMouseListener(this);
		
		enterArtistNameText = new JTextField();
		enterArtistNameText.setBounds(GENERAL_OBJ_LOC_X, GENERAL_OBJ_LOC_Y + 50, 450,30);
		enterArtistNameText.setFont(new Font("Consolas", Font.BOLD, 20));
		enterArtistNameText.setText("Enter the Artist's name");
		
		enterArtistCountryText = new JTextField();
		enterArtistCountryText.setBounds(GENERAL_OBJ_LOC_X, GENERAL_OBJ_LOC_Y + 100, 450,30);
		enterArtistCountryText.setFont(new Font("Consolas", Font.BOLD, 20));
		enterArtistCountryText.setText("Enter the Artist's Country");
		
		AlbumSettingsButton = new JButton();
		AlbumSettingsButton.setBounds(GENERAL_OBJ_LOC_X+550, GENERAL_OBJ_LOC_Y+250, 250, 50);
		AlbumSettingsButton.setFont(new Font("Consolas", Font.BOLD, 25));
		AlbumSettingsButton.setForeground(Color.white);
		AlbumSettingsButton.setText("Album Settings");
		AlbumSettingsButton.setBackground(this.getBackground().brighter());
		AlbumSettingsButton.setFocusable(false);
		AlbumSettingsButton.addMouseListener(this);
		
		
	}
	public void initComboBoxes()
	{
		artistNameBox = new JComboBox();
		artistNameBox.setEditable(true);
		artistNameBox.setBounds(GENERAL_OBJ_LOC_X, GENERAL_OBJ_LOC_Y - 150, 450, 30);
		
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
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		String artistToDelete = (String) artistNameBox.getSelectedItem();
		String artistToAddName = enterArtistNameText.getText();
		String artistToAddCountry = enterArtistCountryText.getText();
		if(e.getSource() == addButton)
		{
			//artist tablosuna gerekli verilerle yeni eleman ekle
		}
		else if(e.getSource() == deleteButton)
		{
			//Gerekli sorguyu yap ve isimler eslesen artisti sil
		}
		else if(e.getSource() == AlbumSettingsButton)
		{
			new Screen(new AlbumSettings());
			((Window) getRootPane().getParent()).dispose();
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
	
	
	
}
