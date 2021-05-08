import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

public class FreeUserPanel extends JPanel implements MouseListener
{
	JPanel leftPanel;
	JPanel middlePanel;
	JPanel bottomPanel;
	JLabel albumCoverLabel;
	
	final int MIDDLE_LABEL_WIDTH = 1100;
	final int MIDDLE_LABEL_HEIGHT = 55;
	
	JButton playButton;
	int playButtonID;
	JButton forwardButton;
	JButton backwardButton;
	JButton fastforwardButton;
	JButton fastbackwardButton;
	final int BUTTON_SIZE = 30;
	final int BUTTON_LOCATION_Y = 20;
	final int BUTTON_GAP = BUTTON_SIZE*2-5;
	final int BUTTON_LOCATION_X = 645-70;
	ImageIcon playButtonImg1;
	ImageIcon pauseButtonImg1;
	ImageIcon forwardButtonImg1;
	ImageIcon backwardButtonImg1;
	ImageIcon fastforwardButtonImg;
	ImageIcon fastbackwardButtonImg;
	
	
	ImageIcon playButtonImg2;
	ImageIcon pauseButtonImg2;
	ImageIcon forwardButtonImg2;
	ImageIcon backwardButtonImg2;
	
	JProgressBar progressBar;
	Color defaultBackground = new Color(33,33,33).brighter();
	Color pressedColor = defaultBackground.darker();
	Color eneteredColor = defaultBackground.brighter();
	boolean premium;
	String userID;
	
	//---Top panel---
	JPanel topPanel;
	JButton myPlaylistsButton;
	JButton usersButton;
	JButton artistsButton;
	JButton followers;
	JButton following;
	//---Left Panel---
	ArrayList<Button> userButtons;
	JComboBox<CBItem> playlistsCB = new JComboBox<CBItem>();
	ArrayList<Button> userPlaylistButtons = new ArrayList<Button>();
	ArrayList<Button> artistButtons = new ArrayList<Button>();
	
	FreeUserPanel(String ID) throws SQLException
	{
		userID = ID;
		userButtons = new ArrayList<Button>();
	    if(SpotifyDB.checkPremium(ID))
	    {
	    	premium = true;
	    }
	    else
	    {
	    	premium = false;
		}
		
		this.setLayout(null);
		this.setBackground(new Color(33,33,33));
		setAlbumCoverLabel();
		initLeftPanel();
		initTopPanel();
		initMiddlePanel();
		initBottomPanel();
		initUserPlaylistButtons();
		initButtonImgs();
		initButtons();
		initUserButtons();
		initArtistButtons();
	    addMiddleLabels();
	
		
		
		
		
		
		
		this.setPreferredSize(new Dimension(1280,720));
	}

	
	public void initUserButtons() throws SQLException
	{
		ResultSet rs = SpotifyDB.getPremiumUsers(this.userID);
		while(rs.next())
		{
			userButtons.add(new Button(rs.getString("userID"), rs.getString("userName")));
		}
		
		for(int i = 0; i < userButtons.size(); i++)
		{
			if(i == 0)
			{
				userButtons.get(i).setBounds(0, 50, 150, 50);
			}
			else
			{
				userButtons.get(i).setBounds(0, (int)userButtons.get(i-1).getLocation().getY()+70, 150, 50);
			}
			userButtons.get(i).setFont(new Font("Consolas", Font.BOLD, 20));
			userButtons.get(i).setForeground(Color.white);
			userButtons.get(i).setBackground(Color.black);
			userButtons.get(i).setFocusable(false);
			userButtons.get(i).addMouseListener(this);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void initLeftPanel()
	{
		leftPanel = new JPanel();
		leftPanel.setBounds(0,0,150,620);
		leftPanel.setBackground(Color.red);
		leftPanel.setLayout(null);
		this.add(leftPanel);
	}
	
	
	public void initTopPanel()
	{
		topPanel = new JPanel();
		topPanel.setBounds(150,0,1130,50);
		topPanel.setBackground(Color.blue);
		topPanel.setLayout(null);
		myPlaylistsButton = new JButton();
		myPlaylistsButton.setBounds(1, 0, 200, 50);
		myPlaylistsButton.setFont(new Font("Consolas", Font.BOLD, 20));
		myPlaylistsButton.setForeground(Color.white);
		myPlaylistsButton.setText("My Playlists");
		myPlaylistsButton.setBackground(Color.black);
		myPlaylistsButton.setFocusable(false);
		myPlaylistsButton.addMouseListener(this);
		
		
		usersButton = new JButton();
		usersButton.setBounds((int)myPlaylistsButton.getLocation().getX()+232, 0, 200, 50);
		usersButton.setFont(new Font("Consolas", Font.BOLD, 20));
		usersButton.setForeground(Color.white);
		usersButton.setText("Users");
		usersButton.setBackground(Color.black);
		usersButton.setFocusable(false);
		usersButton.addMouseListener(this);
		 
		artistsButton = new JButton();
		artistsButton.setBounds((int)usersButton.getLocation().getX()+232, 0, 200, 50);
		artistsButton.setFont(new Font("Consolas", Font.BOLD, 20));
		artistsButton.setForeground(Color.white);
		artistsButton.setText("Artists");
		artistsButton.setBackground(Color.black);
		artistsButton.setFocusable(false);
		artistsButton.addMouseListener(this);
		
		followers = new JButton();
		followers.setBounds((int)artistsButton.getLocation().getX()+232, 0, 200, 50);
		followers.setFont(new Font("Consolas", Font.BOLD, 20));
		followers.setForeground(Color.white);
		followers.setText("Followers");
		followers.setBackground(Color.black);
		followers.setFocusable(false);
		followers.addMouseListener(this);
		/*following;*/
		following = new JButton();
		following.setBounds((int)followers.getLocation().getX()+232, 0, 200, 50);
		following.setFont(new Font("Consolas", Font.BOLD, 20));
		following.setForeground(Color.white);
		following.setText("Following");
		following.setBackground(Color.black);
		following.setFocusable(false);
		following.addMouseListener(this);
		
		topPanel.add(myPlaylistsButton);
		topPanel.add(usersButton);
		topPanel.add(artistsButton);
		topPanel.add(followers);
		topPanel.add(following);
		this.add(topPanel);
	}
	
	public void initMiddlePanel()
	{
		middlePanel = new JPanel();
		middlePanel.setBounds(150,50,1130,570);
		middlePanel.setBackground(Color.white);
		//middlePanel.setBackground(new Color(33,33,33).brighter());
		middlePanel.setLayout(null);
		this.add(middlePanel);
	}
	public void initBottomPanel()
	{

		bottomPanel = new JPanel();
		bottomPanel.setBounds(0,620,1280,100);
		bottomPanel.setBackground(Color.green);
		bottomPanel.setLayout(null);
		progressBar = new JProgressBar();
		progressBar.setBounds(BUTTON_LOCATION_X-30,BUTTON_LOCATION_Y+50,350,15);
		bottomPanel.add(albumCoverLabel);
		bottomPanel.add(progressBar);
		
		this.add(bottomPanel);
	}
	public void initButtonImgs()
	{
		playButtonImg1 = new ImageIcon("images\\playButton.png");
		pauseButtonImg1 = new ImageIcon("images\\pauseButton.png");
		forwardButtonImg1 = new ImageIcon("images\\forwardButton.png");
		backwardButtonImg1 = new ImageIcon("images\\backwardButton.png");
		
		playButtonImg2 = new ImageIcon("images\\playButton2.png");
		pauseButtonImg2 = new ImageIcon("images\\pauseButton2.png");
		forwardButtonImg2 = new ImageIcon("images\\forwardButton2.png");
		backwardButtonImg2 = new ImageIcon("images\\backwardButton2.png");
		
		fastforwardButtonImg = new ImageIcon("images\\fastForwardButton.png");
		fastbackwardButtonImg = new ImageIcon("images\\fastBackwardButton.png");
	}
	
	public void initButtons()
	{
		
		backwardButton = new JButton();
		backwardButton.setOpaque(false);
		backwardButton.setContentAreaFilled(false);
		backwardButton.setBorderPainted(false);
		backwardButton.setIcon((new ImageIcon(getScaledImage(backwardButtonImg1.getImage(), BUTTON_SIZE, BUTTON_SIZE))));
		backwardButton.setPressedIcon((new ImageIcon(getScaledImage(backwardButtonImg2.getImage(), BUTTON_SIZE, BUTTON_SIZE))));
		backwardButton.setFocusable(false);
		backwardButton.setBounds(BUTTON_LOCATION_X,BUTTON_LOCATION_Y,BUTTON_SIZE,BUTTON_SIZE);
		backwardButton.addMouseListener(this);
		bottomPanel.add(backwardButton);
		

		fastbackwardButton = new JButton();
		fastbackwardButton.setOpaque(false);
		fastbackwardButton.setContentAreaFilled(false);
		fastbackwardButton.setBorderPainted(false);
		fastbackwardButton.setIcon((new ImageIcon(getScaledImage(fastbackwardButtonImg.getImage(), BUTTON_SIZE-5, BUTTON_SIZE-5))));
		fastbackwardButton.setFocusable(false);
		fastbackwardButton.setBounds((int)backwardButton.getLocation().getX() + BUTTON_GAP,BUTTON_LOCATION_Y+3,BUTTON_SIZE-5,BUTTON_SIZE-5);
		fastbackwardButton.addMouseListener(this);
		bottomPanel.add(fastbackwardButton);
		
		playButton = new JButton();
		playButton.setOpaque(false);
		playButton.setContentAreaFilled(false);
		playButton.setBorderPainted(false);
		playButton.setIcon((new ImageIcon(getScaledImage(playButtonImg1.getImage(), BUTTON_SIZE, BUTTON_SIZE))));
		playButton.setPressedIcon((new ImageIcon(getScaledImage(playButtonImg2.getImage(), BUTTON_SIZE, BUTTON_SIZE))));
		playButton.setFocusable(false);
		playButton.setBounds((int)fastbackwardButton.getLocation().getX() + BUTTON_GAP,BUTTON_LOCATION_Y,BUTTON_SIZE,BUTTON_SIZE);
		playButtonID = 0;
		playButton.addMouseListener(this);
		bottomPanel.add(playButton);
		
		fastforwardButton = new JButton();
		fastforwardButton.setOpaque(false);
		fastforwardButton.setContentAreaFilled(false);
		fastforwardButton.setBorderPainted(false);
		fastforwardButton.setIcon((new ImageIcon(getScaledImage(fastforwardButtonImg.getImage(), BUTTON_SIZE-5, BUTTON_SIZE-5))));
		fastforwardButton.setFocusable(false);
		fastforwardButton.setBounds((int)playButton.getLocation().getX() + BUTTON_GAP,BUTTON_LOCATION_Y+3,BUTTON_SIZE-5,BUTTON_SIZE-5);
		fastforwardButton.addMouseListener(this);
		bottomPanel.add(fastforwardButton);
			
		forwardButton = new JButton();
		forwardButton.setOpaque(false);
		forwardButton.setContentAreaFilled(false);
		forwardButton.setBorderPainted(false);
		forwardButton.setIcon((new ImageIcon(getScaledImage(forwardButtonImg1.getImage(), BUTTON_SIZE, BUTTON_SIZE))));
		forwardButton.setPressedIcon((new ImageIcon(getScaledImage(forwardButtonImg2.getImage(), BUTTON_SIZE, BUTTON_SIZE))));
		forwardButton.setFocusable(false);
		forwardButton.setBounds((int)fastforwardButton.getLocation().getX() + BUTTON_GAP,BUTTON_LOCATION_Y,BUTTON_SIZE,BUTTON_SIZE);
		forwardButton.addMouseListener(this);
		bottomPanel.add(forwardButton);
	}
	
	private Image getScaledImage(Image srcImg, int w, int h)
	{
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();
	    return resizedImg;
	}
	public void setAlbumCoverLabel() //Duzenlendiginde parametre olarak Image AlbumCover alacak
	{
		albumCoverLabel = new JLabel();
		albumCoverLabel.setBackground(Color.white);
		albumCoverLabel.setBounds(20,10,80,80);
		albumCoverLabel.setOpaque(true);
	}
	
	public JLabel createLabel(int x, int y, int w, int h)
	{
		JLabel label = new JLabel();
		label.setBounds(x, y, w, h);
		label.setBackground(defaultBackground);
		label.addMouseListener(this);
		label.setFont(new Font("Consolas", Font.BOLD, 30));
		label.setForeground(Color.white);
		label.setOpaque(true);
		label.setBorder(BorderFactory.createLineBorder(Color.white));
		//Label text'i database'den cekilecek ve label uzerine yazılacak.
		
		return label;
	}
	
	public void addMiddleLabels() throws SQLException
	{
			ResultSet rs = SpotifyDB.getPlaylist("1", "jazz");
			
			for(int i = 0;rs.next(); i++)
			{
				JLabel label = createLabel(0,i*55,MIDDLE_LABEL_WIDTH,MIDDLE_LABEL_HEIGHT);
				label.setText( rs.getString("SongName") + " " + rs.getString("ArtistName") + " " + rs.getString("duration") );
				middlePanel.add(label);
			}
	}
	
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		JButton btn = (JButton)e.getSource();
		changePressedBackground((JComponent)e.getSource());
		if(userButtons.contains(btn))
		{/*Sadece premium kullanicilarin gosterildigi panelde ustune tiklanilan kullanicilarin IDleri ile
		  beraber combobox'a playlistler item olarak eklenir. Daha sonra bu comboboxta secilen item'a gore
		  ortadaki panelde kullanicinin o playlistindeli sarkilar gosterilir.*/
			
			Button btn2 = (Button) btn;
			
			playlistsCB.removeAllItems();
			playlistsCB.addItem(new CBItem(btn2.ID, "Jazz"));
			playlistsCB.addItem(new CBItem(btn2.ID, "Pop"));
			playlistsCB.addItem(new CBItem(btn2.ID, "Classical"));
		}
		else if(e.getSource() == myPlaylistsButton)
		{
			clearLeftPanel();
			
			for(int i = 0; i < userPlaylistButtons.size(); i++)
			{
				leftPanel.add(userPlaylistButtons.get(i));
			}
			
		}
		else if(e.getSource() == usersButton)
		{
			clearLeftPanel();
			
			for(int i = 0; i < userButtons.size(); i++)
			{
				leftPanel.add(userButtons.get(i));
			}
		}
		else if(e.getSource() == artistsButton)
		{
			clearLeftPanel();
			for(int i = 0; i < artistButtons.size(); i++)
			{
				leftPanel.add(artistButtons.get(i));
			}
		}
			
	}
	public void clearLeftPanel()
	{
		leftPanel.removeAll();
		leftPanel.revalidate();
		leftPanel.repaint();
	}
	public void initUserPlaylistButtons()
	{
		userPlaylistButtons.clear();
		Button jazzButton = new Button(userID, "Jazz");
		Button popButton = new Button(userID, "Pop");
		Button classicalButton = new Button(userID, "Classical");
		userPlaylistButtons.add(jazzButton);
		userPlaylistButtons.add(popButton);
		userPlaylistButtons.add(classicalButton);
		for(int i = 0; i < userPlaylistButtons.size(); i++)
		{
			if(i == 0)
			{
				userPlaylistButtons.get(i).setBounds(0, 50, 150, 50);
			}
			else
			{
				userPlaylistButtons.get(i).setBounds(0, (int)userPlaylistButtons.get(i-1).getLocation().getY()+70, 150, 50);
			}
			userPlaylistButtons.get(i).setFont(new Font("Consolas", Font.BOLD, 20));
			userPlaylistButtons.get(i).setForeground(Color.white);
			userPlaylistButtons.get(i).setBackground(Color.black);
			userPlaylistButtons.get(i).setFocusable(false);
			userPlaylistButtons.get(i).addMouseListener(this);
		}
	}
	
	public void initArtistButtons() throws SQLException
	{
		//artistButtons
		ResultSet rs = SpotifyDB.getAllArtists();
		artistButtons.clear();
		while(rs.next())
		{
			artistButtons.add(new Button(rs.getString("ArtistID"), rs.getString("ArtistName")));
		}
		
		for(int i = 0; i < artistButtons.size(); i++)
		{
			if(i == 0)
			{
				artistButtons.get(i).setBounds(0, 50, 150, 50);
			}
			else
			{
				artistButtons.get(i).setBounds(0, (int)artistButtons.get(i-1).getLocation().getY()+70, 150, 50);
			}
			artistButtons.get(i).setFont(new Font("Consolas", Font.BOLD, 20));
			artistButtons.get(i).setForeground(Color.white);
			artistButtons.get(i).setBackground(Color.black);
			artistButtons.get(i).setFocusable(false);
			artistButtons.get(i).addMouseListener(this);
		}
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) 
	{
		if(e.getSource() == playButton)
		{
			if(playButtonID == 0)
			{
				playButton.setIcon((new ImageIcon(getScaledImage(pauseButtonImg1.getImage(), BUTTON_SIZE, BUTTON_SIZE))));
				playButton.setPressedIcon((new ImageIcon(getScaledImage(pauseButtonImg2.getImage(), BUTTON_SIZE, BUTTON_SIZE))));
				playButtonID = 1;
			}
			else if(playButtonID == 1)
			{
				playButton.setIcon((new ImageIcon(getScaledImage(playButtonImg1.getImage(), BUTTON_SIZE, BUTTON_SIZE))));
				playButton.setPressedIcon((new ImageIcon(getScaledImage(playButtonImg2.getImage(), BUTTON_SIZE, BUTTON_SIZE))));
				playButtonID = 0;
			}
			return;
		}
		changeReleasedBackground((JComponent)e.getSource());
		
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		
		changeEnteredBackground((JComponent)e.getSource());
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		changeExitedBackground((JComponent)e.getSource());
	}
	public void changeEnteredBackground(JComponent obj)
	{
		obj.setBackground(obj.getBackground().brighter());
	}
	public void changeExitedBackground(JComponent obj)
	{
		obj.setBackground(obj.getBackground().darker());
	}
	public void changeReleasedBackground(JComponent obj)
	{
		obj.setBackground(obj.getBackground().brighter().brighter());
	}
	public void changePressedBackground(JComponent obj)
	{
		obj.setBackground(obj.getBackground().darker().darker());
	}
	


}
