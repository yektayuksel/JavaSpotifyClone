import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AlbumSettings extends JPanel implements MouseListener
{
	static final int GENERAL_OBJ_LOC_Y = 310;
	static final int GENERAL_OBJ_LOC_X = 420;
	
	JComboBox albumNameBox;
	JLabel albumToDelete;
	JButton deleteButton;
	
	JLabel albumToAdd;
	JLabel selectAnArtistLabel;
	JComboBox artistNameBox;
	JTextField albumNameTxt;
	JTextField relaseDateTxt;
	JTextField genreTxt;
	JButton addButton;
	
	JButton artistSettingsButton;
	JButton songSettingsButton;
	AlbumSettings()
	{
		
		this.setBackground(new Color(33,33,33));
		initComboBoxes();
		initAddingSettings();
		initMenuButtons();
		this.add(albumNameBox);
		this.add(albumToDelete);
		this.add(deleteButton);
		this.add(albumToAdd);
		this.add(selectAnArtistLabel);
		this.add(artistNameBox);
		this.add(albumNameTxt);
		this.add(relaseDateTxt);
		this.add(genreTxt);
		this.add(addButton);
		this.add(artistSettingsButton);
		this.add(songSettingsButton);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1280,720));
	}
	public void initComboBoxes()
	{
		albumNameBox = new JComboBox();
		albumNameBox.setEditable(true);
		albumNameBox.setBounds(GENERAL_OBJ_LOC_X, GENERAL_OBJ_LOC_Y - 150, 450, 30);
		
		albumToDelete = new JLabel();
		albumToDelete.setText("Select an album to delete:");
		albumToDelete.setBounds(GENERAL_OBJ_LOC_X,GENERAL_OBJ_LOC_Y - 200, 500, 30);
		albumToDelete.setFont(new Font("Consolas", Font.BOLD, 30));
		albumToDelete.setForeground(Color.white);
		
		deleteButton = new JButton();
		deleteButton.setBounds(GENERAL_OBJ_LOC_X+350, GENERAL_OBJ_LOC_Y - 100, 100, 30);
		deleteButton.setFont(new Font("Consolas", Font.BOLD, 20));
		deleteButton.setForeground(Color.white);
		deleteButton.setText("Delete");
		deleteButton.setBackground(this.getBackground().brighter());
		deleteButton.setFocusable(false);
		deleteButton.addMouseListener(this);
	}
	
	public void initAddingSettings()
	{
		albumToAdd = new JLabel();
		selectAnArtistLabel = new JLabel();
		artistNameBox = new JComboBox();
		albumNameTxt = new JTextField();
		relaseDateTxt = new JTextField();
		genreTxt = new JTextField();
		
		
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
		
		genreTxt.setBounds(GENERAL_OBJ_LOC_X, GENERAL_OBJ_LOC_Y + 240, 450,30);
		genreTxt.setFont(new Font("Consolas", Font.BOLD, 20));
		genreTxt.setText("Genre");
		
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
		artistSettingsButton.setBounds(60, GENERAL_OBJ_LOC_Y+300,  250, 50);
		artistSettingsButton.setFont(new Font("Consolas", Font.BOLD, 25));
		artistSettingsButton.setForeground(Color.white);
		artistSettingsButton.setText("Artist Settings");
		artistSettingsButton.setBackground(this.getBackground().brighter());
		artistSettingsButton.setFocusable(false);
		artistSettingsButton.addMouseListener(this);
		
		songSettingsButton = new JButton();
		songSettingsButton.setBounds(GENERAL_OBJ_LOC_X+550, GENERAL_OBJ_LOC_Y+300,  250, 50);
		songSettingsButton.setFont(new Font("Consolas", Font.BOLD, 25));
		songSettingsButton.setForeground(Color.white);
		songSettingsButton.setText("Song Settings");
		songSettingsButton.setBackground(this.getBackground().brighter());
		songSettingsButton.setFocusable(false);
		songSettingsButton.addMouseListener(this);
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
			String artistName = (String) artistNameBox.getSelectedItem();
			String albumName = albumNameTxt.getText();
			String relaseDate  = relaseDateTxt.getText();
			String genre = genreTxt.getText();
		}
		else if(e.getSource() == deleteButton)
		{
			String albumName = (String) albumNameBox.getSelectedItem();
		}
		else if(e.getSource() == artistSettingsButton)
		{
			new Screen(new ArtistSettings());
			((Window) getRootPane().getParent()).dispose();
		}
		else if(e.getSource() == songSettingsButton)
		{
			new Screen(new SongSettings());
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