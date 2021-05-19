import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

public class FreeUserPanel extends JPanel implements MouseListener,ActionListener
{
	/**/
	
	
	boolean premium;
	String userID;
	
	//TODO addAllSongToPlaylistButton
	Button addAllSongToPlaylistButton;
	
	//---Top panel---
	/*Ust panelde bulunacak olan gerekli degiskenler*/
	JPanel topPanel;
	JButton myPlaylistsButton;
	JButton usersButton;
	JButton artistsButton;
	JButton followers;
	JButton following;
	JButton topTenButton;
	Color topPanelButtonColor = new Color(29,195,84).darker();
	Color topPanelBGColor = new Color(33,33,33);
	/*---------------------------------------------------------*/	
	
	//---Left Panel---
	/*Sol panelde bulunacak gerekli listelerin olusturulmasi*/
	JPanel leftPanel;
	ArrayList<Button> userButtons;
	JComboBox<CBItem> playlistsCB = new JComboBox<CBItem>();
	ArrayList<Button> userPlaylistButtons = new ArrayList<Button>();
	ArrayList<Button> artistButtonsList = new ArrayList<Button>();
	ArrayList<Button> followingButtonList = new ArrayList<Button>();
	JScrollPane leftScroll; 
	Color leftPanelButtonColor = new Color(29,195,84).darker();
	Color leftPanelBGColor = new Color(33,33,33);
	JComboBox<CBItem> top10CB = new JComboBox<CBItem>();
	//Dimensin left
	
	/*---------------------------------------------------------*/
	
	//---Middle Panel---
	/*Orta panelde bulunacak olan listelerin oluturulmasi*/
	//TODO orta panelde tutulacak butonların boyutları vsvs
	JPanel middlePanel;
	JScrollPane middleScroll;
	final int MID_PNL_BTN_W = 1130;
	final int MID_PNL_BTN_H = 57;
	ArrayList<Button> artistAlbumsList = new ArrayList<Button>();
	ArrayList<Button> songsList = new ArrayList<Button>();
	ArrayList<Button> songAddButtons = new ArrayList<Button>();
	ArrayList<Button> removeFromMyPlaylist = new ArrayList<Button>();
	final Dimension middPanButtonSize = new Dimension(1130,57);
	Color middlePanelBGColor = new Color(18,18,18);
	Color middlePanelButtonColor = new Color(179,179,179);
	/*--------------------------------------------------------*/
	
	//---BottomPanel---
	/*Alt panelde bulunan componentler icin gerekli degiskenler*/
	JPanel bottomPanel;
	JProgressBar progressBar;
	JButton playButton;
	int playButtonID;
	JButton forwardButton;
	JButton backwardButton;
	JButton fastforwardButton;
	JButton fastbackwardButton;
	JButton userSettingButton;
	JLabel playingSongLabel;
	Color bottomPanelBGColor = new Color(83,83,83).darker();
	/*--------------------------------------------------------*/
	//Alt panelde bulunan butonların boyutlarinin ve lokasyonlarinin ayarlanmasi
	final int BUTTON_SIZE = 30;
	final int BUTTON_LOCATION_Y = 20;
	final int BUTTON_GAP = BUTTON_SIZE*2-5;
	final int BUTTON_LOCATION_X = 645-70;
	
	ImageIcon playButtonImg1;
	ImageIcon playButtonImg2;
	
	ImageIcon pauseButtonImg1;
	ImageIcon pauseButtonImg2;
	
	ImageIcon forwardButtonImg1;
	ImageIcon forwardButtonImg2;
	
	ImageIcon backwardButtonImg1;
	ImageIcon backwardButtonImg2;
	
	ImageIcon fastforwardButtonImg;
	ImageIcon fastbackwardButtonImg;
	/*--------------------------------------------------------*/
	
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
		initLeftPanel();
		initTopPanel();
		initMiddlePanel();
		initBottomPanel();
		initUserPlaylistButtons();
		initButtonImgs();
		initButtons();
		initUserButtons();
		initArtistButtons();
		playlistsCB.setEditable(true);
		playlistsCB.setSize(new Dimension(150, 25));
		playlistsCB.addActionListener(this);
		this.setPreferredSize(new Dimension(1280,720));
	}
	
	
	
	/*Yukardaki panelin ayarlamalarinin yapildigi ve uzerinde bulunacak butonlarin eklendigi metod */
	public void initTopPanel()
	{
		topPanel = new JPanel();
		topPanel.setBounds(150,0,1130,50);
		topPanel.setBackground(topPanelBGColor);
		topPanel.setLayout(null);
		myPlaylistsButton = new JButton();
		myPlaylistsButton.setBounds(30, 0, 150, 50);
		myPlaylistsButton.setFont(new Font("Consolas", Font.BOLD, 17));
		myPlaylistsButton.setForeground(Color.white);
		myPlaylistsButton.setText("My Playlists");
		myPlaylistsButton.setBackground(topPanelButtonColor);
		myPlaylistsButton.setFocusable(false);
		myPlaylistsButton.addMouseListener(this);
		
		
		usersButton = new JButton();
		usersButton.setBounds((int)myPlaylistsButton.getLocation().getX()+232-25-25, 0, 150, 50);
		usersButton.setFont(new Font("Consolas", Font.BOLD, 20));
		usersButton.setForeground(Color.white);
		usersButton.setText("Users");
		usersButton.setBackground(topPanelButtonColor);
		usersButton.setFocusable(false);
		usersButton.addMouseListener(this);
		 
		artistsButton = new JButton();
		artistsButton.setBounds((int)usersButton.getLocation().getX()+232-25-25, 0, 150, 50);
		artistsButton.setFont(new Font("Consolas", Font.BOLD, 20));
		artistsButton.setForeground(Color.white);
		artistsButton.setText("Artists");
		artistsButton.setBackground(topPanelButtonColor);
		artistsButton.setFocusable(false);
		artistsButton.addMouseListener(this);
		
		followers = new JButton();
		followers.setBounds((int)artistsButton.getLocation().getX()+232-25-25, 0, 150, 50);
		followers.setFont(new Font("Consolas", Font.BOLD, 20));
		followers.setForeground(Color.white);
		followers.setText("Followers");
		followers.setBackground(topPanelButtonColor);
		followers.setFocusable(false);
		followers.addMouseListener(this);
		
		following = new JButton();
		following.setBounds((int)followers.getLocation().getX()+232-25-25, 0, 150, 50);
		following.setFont(new Font("Consolas", Font.BOLD, 20));
		following.setForeground(Color.white);
		following.setText("Following");
		following.setBackground(topPanelButtonColor);
		following.setFocusable(false);
		following.addMouseListener(this);
		
		
		topTenButton = new JButton();
		topTenButton.setBounds((int)following.getLocation().getX()+232-25-25, 0, 150, 50);
		topTenButton.setFont(new Font("Consolas", Font.BOLD, 20));
		topTenButton.setForeground(Color.white);
		topTenButton.setText("Top 10");
		topTenButton.setBackground(topPanelButtonColor);
		topTenButton.setFocusable(false);
		topTenButton.addMouseListener(this);
		
		topPanel.add(topTenButton);
		topPanel.add(myPlaylistsButton);
		topPanel.add(usersButton);
		topPanel.add(artistsButton);
		topPanel.add(followers);
		topPanel.add(following);
		this.add(topPanel);
	}
	/*-----------------------------------------------------------------------------------*/
	
	/*---Sol panel icin gerekli ayarlamalarin yapildigi metod---*/
	public void initLeftPanel()
	{
		leftPanel = new JPanel();
		leftPanel.setBackground(leftPanelBGColor);
		leftPanel.setPreferredSize(new Dimension(150,620));
		top10CB.setEditable(true);
		top10CB.setSize(new Dimension(150, 25));
		top10CB.addActionListener(this);
		leftScroll = new JScrollPane(leftPanel);
		leftScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    leftScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		leftScroll.setBounds(0,0,150,620);
		
		this.add(leftScroll);
		
		
	}
	/*-----------------------------------------------------------------------------------*/
	
	/*Ortada bulunan panelin ayarlamalarinin yapildigi metod*/
	public void initMiddlePanel()
	{
		middlePanel = new JPanel();
		middlePanel.setPreferredSize(new Dimension(1130,570));
		middlePanel.setBackground(middlePanelBGColor);
		middleScroll = new JScrollPane(middlePanel);
		middleScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		middleScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		middleScroll.setBounds(150,50,1130,570);
		this.add(middleScroll);
	}
	/*-------------------------------------------------------------------------------------*/
	
	/*Alt panelin ayarlamalarinin yapildigi metod*/
	public void initBottomPanel()
	{
		bottomPanel = new JPanel();
		bottomPanel.setBounds(0,620,1280,100);
		bottomPanel.setBackground(bottomPanelBGColor);
		bottomPanel.setLayout(null);
		progressBar = new JProgressBar();
		progressBar.setBounds(BUTTON_LOCATION_X-30,BUTTON_LOCATION_Y+50,350,15);
		bottomPanel.add(progressBar);
		this.add(bottomPanel);
		playingSongLabel = new JLabel();
		playingSongLabel.setBounds(25,25,400,50);
		playingSongLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		playingSongLabel.setForeground(Color.black);
		playingSongLabel.setVisible(true);
		bottomPanel.add(playingSongLabel);
		
		addAllSongToPlaylistButton = new Button();
		addAllSongToPlaylistButton.setText("<html>Add all songs<br />to playlist</html>");
		addAllSongToPlaylistButton.setFont(new Font("Consolas", Font.BOLD, 18));
		addAllSongToPlaylistButton.setForeground(Color.white);
		addAllSongToPlaylistButton.setBackground(leftPanelButtonColor);
		addAllSongToPlaylistButton.setFocusable(false);
		addAllSongToPlaylistButton.addMouseListener(this);
		addAllSongToPlaylistButton.setBounds(1000, 15, 180, 70);
		addAllSongToPlaylistButton.setVisible(false);
		addAllSongToPlaylistButton.setEnabled(false);
		bottomPanel.add(addAllSongToPlaylistButton);
		
		userSettingButton = new Button();
		ImageIcon userSettingButtonImg = new ImageIcon("images\\settings.png");
		userSettingButton.setIcon((new ImageIcon(getScaledImage(userSettingButtonImg.getImage(), 75, 75))));
		userSettingButton.setFont(new Font("Consolas", Font.BOLD, 18));
		userSettingButton.setForeground(Color.white);
		userSettingButton.setBackground(leftPanelButtonColor);
		userSettingButton.setFocusable(false);
		userSettingButton.addMouseListener(this);
		userSettingButton.setBounds(1200, 15, 70, 70);
		bottomPanel.add(userSettingButton);
		
	}
	//Alt panelde bulunan play/pause/skip butonlarinin resimlerinin ayarlandigi metod
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
	//Alt panelde bulunan butonlarin konumlarinin ayarlandigi metod
	
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
	/*-----------------------------------------------------------------------------------*/
	
	/*Ust panelde bulunan "Users" butonuna islev kazandirmak icin gerekli metod
	 * database'den sadece premium kullanicilarin bilgilerini getirir ve bir ArrayList icine atar.
	 * Daha sonra bu ArrayList icinde bulunan bir butona basildiginde, o kullanici ile alakali
	 * bilgilere erisim saglanabilir.*/
	public void initUserButtons() throws SQLException
	{
		
		userButtons.clear();
		ResultSet rs = SpotifyDB.getPremiumUsers(this.userID);
		while(rs.next())
		{
			userButtons.add(new Button(rs.getString("userID"), rs.getString("userName")));
		}
		
		for(int i = 0; i < userButtons.size(); i++)
		{
			userButtons.get(i).setPreferredSize(new Dimension(150,50));
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
			userButtons.get(i).setBackground(leftPanelButtonColor);
			userButtons.get(i).setFocusable(false);
			userButtons.get(i).addMouseListener(this);
		}
	}
	/*-----------------------------------------------------------------------------------*/
	
	/*Boyutu gereginden buyuk veya kucuk olan resmi tekrar istenilen boyutlarda ayarlanmasi icin kullanilan
	 * metoddur.*/
	private Image getScaledImage(Image srcImg, int w, int h)
	{
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();
	    return resizedImg;
	}
	/*-----------------------------------------------------------------------------------*/
	
	/*Herhangi bir panel uzerindeki elemanlar temizlenmek istendiginde kullancilan metod*/
	public void clearPanel(JPanel panel)
	{
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
	}
	/*-----------------------------------------------------------------------------------*/
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) 
	{
		JButton btn = (JButton)e.getSource();
		changePressedBackground((JComponent)e.getSource());
		disableAddAllSongsButton();
		
		if(userButtons.contains(btn))
		{
		/*"Users" butonuna tiklandiktan sonra eger sol panelden bir kullanici secilirse aktif olacak
		 * if blogudur. Uzerine tiklanilan kullanici butonunun hemen altinda bir ComboBox olusturur. ComboBox
		 * icerisinde ilgili kullanicinin calma listeleri bulunmaktadir.*/
			Button btn2 = (Button) btn;
			try {
				if(!SpotifyDB.isFollowing(this.userID, btn2.getID()))
				{
					int a = JOptionPane.showOptionDialog(null, "Are you sure you want to follow " + btn2.getText() + "?", "Warning", JOptionPane.YES_NO_OPTION , JOptionPane.INFORMATION_MESSAGE, null,null, 0);
					if(a == JOptionPane.YES_OPTION)
					{
						SpotifyDB.follow(this.userID, btn2.getID());
						leftPanel.repaint();
						
					}
					else
					{
						leftPanel.repaint();
						return;
					}
					
				}
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			try {
				clearPanel(leftPanel);
				playlistsCB.removeAllItems();
				playlistsCB.addItem(new CBItem(btn2.getID(), "Jazz","1"));
				playlistsCB.addItem(new CBItem(btn2.getID(), "Pop" , "2"));
				playlistsCB.addItem(new CBItem(btn2.getID(), "Classical", "3"));
				playlistsCB.setLocation((int)btn2.getLocation().getX(),  (int)btn2.getLocation().getY()+50);
				leftPanel.add(playlistsCB);
				initUserButtons();
				for(int i = 0; i < userButtons.size(); i++)
				{
					leftPanel.add(userButtons.get(i));
				}
				
				repaint();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			
		}
		else if(e.getSource() == myPlaylistsButton)
		{
			clearPanel(leftPanel);
			clearPanel(middlePanel);
			for(int i = 0; i < userPlaylistButtons.size(); i++)
			{
				leftPanel.add(userPlaylistButtons.get(i));
			}
			
		}
		else if(e.getSource() == usersButton)
		{
			clearPanel(leftPanel);
			clearPanel(middlePanel);
			for(int i = 0; i < userButtons.size(); i++)
			{
				leftPanel.add(userButtons.get(i));
			}
		}
		else if(e.getSource() == artistsButton)
		{
			clearPanel(leftPanel);
			clearPanel(middlePanel);
			for(int i = 0; i < artistButtonsList.size(); i++)
			{
				leftPanel.add(artistButtonsList.get(i));
			}
			leftPanel.revalidate();
			leftPanel.repaint();
		}
		else if(artistButtonsList.contains(btn))
		{
			
			clearPanel(middlePanel);
			artistAlbumsList.clear();
			Button button = (Button)e.getSource();
			try 
			{
				ResultSet rs = SpotifyDB.getAllAlbums(button.getID());
				while(rs.next())
				{
					artistAlbumsList.add(new Button(rs.getString("AlbumID"), rs.getString("AlbumName")));
				}
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			for(int i = 0; i < artistAlbumsList.size(); i++)
			{
				artistAlbumsList.get(i).setPreferredSize(middPanButtonSize);
				artistAlbumsList.get(i).setFont(new Font("Consolas", Font.BOLD, 20));
				artistAlbumsList.get(i).setForeground(Color.white);
				artistAlbumsList.get(i).setBackground(Color.black);
				artistAlbumsList.get(i).setFocusable(false);
				artistAlbumsList.get(i).addMouseListener(this);
				middlePanel.add(artistAlbumsList.get(i));
			}
			
		}
		else if(artistAlbumsList.contains(btn))
		{
			Button button = (Button)e.getSource();
			clearPanel(middlePanel);
			
			songsList.clear();
			songAddButtons.clear();
			try 
			{
				ResultSet rs = SpotifyDB.getAlbumSongs(button.getID());
				while(rs.next())
				{
					String ID = rs.getString("SongID");
					String songName =  rs.getString("SongName");
					songsList.add(new Button(ID,songName));
					songsList.get(songsList.size()-1).setGenre(rs.getString("genreID"));
					songAddButtons.add(new Button(ID, "+"));
					songAddButtons.get(songAddButtons.size()-1).setGenre(rs.getString("genreID"));
				}
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			for(int i = 0; i < songsList.size(); i++)
			{
				songsList.get(i).setPreferredSize(new Dimension(1000,57));
				songAddButtons.get(i).setPreferredSize(new Dimension(50,50));
				
				songsList.get(i).setFont(new Font("Consolas", Font.BOLD, 20));
				songsList.get(i).setForeground(Color.white);
				songsList.get(i).setBackground(Color.black);
				songsList.get(i).setFocusable(false);
				songsList.get(i).addMouseListener(this);
				middlePanel.add(songsList.get(i));
				
				songAddButtons.get(i).setFont(new Font("Consolas", Font.BOLD, 25));
				songAddButtons.get(i).setForeground(Color.white);
				songAddButtons.get(i).setBackground(Color.black);
				songAddButtons.get(i).setFocusable(false);
				songAddButtons.get(i).addMouseListener(this);
				middlePanel.add(songAddButtons.get(i));
				
			}
			
			
		}
		else if(songsList.contains(btn))
		{
			enableAddAllSongsButton();
			Button button = (Button)btn;
			try 
			{
				String tp = SpotifyDB.getTimesPlayed(button.getID());
				Integer i = Integer.parseInt(tp);
				i++;
				String tp2 = i.toString();
				SpotifyDB.updateSongTimesPlayed(button.getID(), tp2);
				ResultSet rs = SpotifyDB.getSong(button.getID());
				while(rs.next())
				{
					playingSongLabel.setText(rs.getString("SongName") + "   " +  rs.getString("duration") + "   " + rs.getString("timesPlayed"));
					repaint();
				}
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			
		}
		else if(songAddButtons.contains(btn))
		{
			Button button = (Button)btn;
			int a = JOptionPane.showOptionDialog(null, "This song going to be added to your playlist.", "Warning", JOptionPane.YES_NO_OPTION , JOptionPane.INFORMATION_MESSAGE, null,null, 0);
			if(a == JOptionPane.YES_OPTION)
			{
				SpotifyDB.addToPlaylist(this.userID, button.getID(),1);
			}
			else
			{
				return;
			}
		}
		else if(userPlaylistButtons.contains(btn))
		{
			Button button = (Button)btn;
			String playlistName = button.getText();
			String genre = null;
			clearPanel(middlePanel);
			if(playlistName.equals("Jazz"))
			{
				genre = "1";
			}
			else if(playlistName.equals("Pop"))
			{
				genre = "2";
			}
			else if(playlistName.equals("Classical"))
			{
				genre = "3";
			}
			try 
			{
				ResultSet rs = SpotifyDB.getPlaylist(this.userID, genre);
				songsList.clear();
				removeFromMyPlaylist.clear();
				while(rs.next())
				{	
					String ID = rs.getString("s.SongID");
					String buttonText = rs.getString("s.SongName") + "  " + rs.getString("ar.ArtistName") + "   "
							+ rs.getString("g.genre") + "   " + rs.getString("s.duration"); 
					songsList.add(new Button(ID, buttonText));
					removeFromMyPlaylist.add(new Button(ID, "-"));
				}
				for(int i = 0; i < songsList.size(); i++)
				{
					songsList.get(i).setPreferredSize(new Dimension(1000,57));
					songsList.get(i).setFont(new Font("Consolas", Font.BOLD, 20));
					songsList.get(i).setForeground(Color.white);
					songsList.get(i).setBackground(Color.black);
					songsList.get(i).setFocusable(false);
					songsList.get(i).addMouseListener(this);
					middlePanel.add(songsList.get(i));
					
					removeFromMyPlaylist.get(i).setPreferredSize(new Dimension(50,57));
					removeFromMyPlaylist.get(i).setFont(new Font("Consolas", Font.BOLD, 25));
					removeFromMyPlaylist.get(i).setForeground(Color.white);
					removeFromMyPlaylist.get(i).setBackground(Color.black);
					removeFromMyPlaylist.get(i).setFocusable(false);
					removeFromMyPlaylist.get(i).addMouseListener(this);
					middlePanel.add(removeFromMyPlaylist.get(i));
					
					
				}
				
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		else if(removeFromMyPlaylist.contains(btn))
		{
			int a = JOptionPane.showOptionDialog(null, "Are you sure you want to remove this song from your playlist?", "Warning", JOptionPane.YES_NO_OPTION , JOptionPane.INFORMATION_MESSAGE, null,null, 0);
			if(a == JOptionPane.NO_OPTION)
			{
				return;
			}
			
			Button button = (Button)btn;
			try {
				SpotifyDB.removeFromMyPlaylist(this.userID, button.getID());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == followers)
		{
			int y = 0;
			clearPanel(middlePanel);
			clearPanel(leftPanel);
			try 
			{
				ResultSet rs = SpotifyDB.getFollowers(this.userID);
				while(rs.next())
				{
					JButton button = new JButton(rs.getString("u.userName"));
					button.setFont(new Font("Consolas", Font.BOLD, 25));
					button.setForeground(Color.white);
					button.setBackground(Color.black);
					button.setFocusable(false);
					button.addMouseListener(this);
					button.setPreferredSize(middPanButtonSize);
					y+=MID_PNL_BTN_H;
					middlePanel.add(button);
				}
			} 
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
		else if(e.getSource() == following)
		{
			clearPanel(middlePanel);
			try 
			{
				initFollowingButtonsList();
			} catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		else if(followingButtonList.contains(btn))
		{
			Button btn2 = (Button)btn;
			clearPanel(leftPanel);
			playlistsCB.removeAllItems();
			playlistsCB.addItem(new CBItem(btn2.getID(), "Jazz","1"));
			playlistsCB.addItem(new CBItem(btn2.getID(), "Pop" , "2"));
			playlistsCB.addItem(new CBItem(btn2.getID(), "Classical", "3"));
			playlistsCB.addItem(new CBItem(btn2.getID(), "Unfollow"));
			playlistsCB.setLocation((int)btn2.getLocation().getX(),  (int)btn2.getLocation().getY()+50);
			leftPanel.add(playlistsCB);
			for(int i = 0; i < followingButtonList.size(); i++)
			{
				leftPanel.add(followingButtonList.get(i));
			}
			repaint();
		}
		else if(e.getSource() == addAllSongToPlaylistButton)
		{
			enableAddAllSongsButton();
			System.out.print("yekta");
			try {
				addMyPlaylistAllSongs();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == topTenButton)
		{
			//TODO TOPTEN
			clearPanel(leftPanel);
			clearPanel(middlePanel);
			try {
				initTop10CB();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			leftPanel.add(top10CB);
			
		}
		else if(e.getSource() == userSettingButton)
		{
			try {
				new Screen(new UserSettingsPanel(this.userID));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			
			leftPanel.revalidate();
			middlePanel.revalidate();
	}
	
	
	public void initTop10CB() throws SQLException
	{
		top10CB.addItem(new CBItem("-1","Global"));
		top10CB.addItem(new CBItem("1","Jazz"));
		top10CB.addItem(new CBItem("2","Pop"));
		top10CB.addItem(new CBItem("3","Classical"));
		ResultSet rs = SpotifyDB.getAllCountires();
		while(rs.next())
		{
			top10CB.addItem(new CBItem(rs.getString("countryID"), rs.getString("country")));
		}
	}
	public void addMyPlaylistAllSongs() throws SQLException
	{
		int a = JOptionPane.showOptionDialog(null, "Are you sure you want to add all these song to your playlist?", "Warning", JOptionPane.YES_NO_OPTION , JOptionPane.INFORMATION_MESSAGE, null,null, 0);
		if(a == JOptionPane.NO_OPTION)
		{
			return;
		}
		ResultSet rs = SpotifyDB.getPlaylist(addAllSongToPlaylistButton.getID(), addAllSongToPlaylistButton.getGenre());
		while(rs.next())
		{
			SpotifyDB.addToPlaylist(userID, rs.getString("s.SongID"), 0);
		}
		 
	}
	public void enableAddAllSongsButton()
	{
		addAllSongToPlaylistButton.setVisible(true);
		addAllSongToPlaylistButton.setEnabled(true);
	}
	public void disableAddAllSongsButton()
	{
		addAllSongToPlaylistButton.setVisible(false);
		addAllSongToPlaylistButton.setEnabled(false);
	}
	public void initFollowingButtonsList() throws SQLException
	{
		ResultSet rs = SpotifyDB.getFollowings(userID);
		followingButtonList.clear();
		clearPanel(leftPanel);
		while(rs.next())
		{
			followingButtonList.add(new Button(rs.getString("f.FollowingID"), rs.getString("u.userName")));
		}
		
		for(int i = 0; i < followingButtonList.size(); i++)
		{
			followingButtonList.get(i).setPreferredSize(new Dimension(150,50));
			
			followingButtonList.get(i).setFont(new Font("Consolas", Font.BOLD, 20));
			followingButtonList.get(i).setForeground(Color.white);
			followingButtonList.get(i).setBackground(leftPanelButtonColor);
			followingButtonList.get(i).setFocusable(false);
			followingButtonList.get(i).addMouseListener(this);
			leftPanel.add(followingButtonList.get(i));
		}
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
			userPlaylistButtons.get(i).setPreferredSize(new Dimension(150,50));
			userPlaylistButtons.get(i).setFont(new Font("Consolas", Font.BOLD, 20));
			userPlaylistButtons.get(i).setForeground(Color.white);
			userPlaylistButtons.get(i).setBackground(leftPanelButtonColor);
			userPlaylistButtons.get(i).setFocusable(false);
			userPlaylistButtons.get(i).addMouseListener(this);
		}
	}
	
	public void initArtistButtons() throws SQLException
	{
		ResultSet rs = SpotifyDB.getAllArtists();
		artistButtonsList.clear();
		while(rs.next())
		{
			artistButtonsList.add(new Button(rs.getString("ArtistID"), rs.getString("ArtistName")));
		}
		for(int i = 0; i < artistButtonsList.size(); i++)
		{
			artistButtonsList.get(i).setPreferredSize(new Dimension(150,50));
			artistButtonsList.get(i).setFont(new Font("Consolas", Font.BOLD, 17));
			artistButtonsList.get(i).setForeground(Color.white);
			artistButtonsList.get(i).setBackground(leftPanelButtonColor);
			artistButtonsList.get(i).setFocusable(false);
			artistButtonsList.get(i).addMouseListener(this);
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == playlistsCB)
		{
			CBItem item = (CBItem)playlistsCB.getSelectedItem();
			
			if(item != null && item.getText().equals("Unfollow"))
			{
				int a = JOptionPane.showOptionDialog(null, "Are you sure you want to unfollow this user?", "Warning", JOptionPane.YES_NO_OPTION , JOptionPane.INFORMATION_MESSAGE, null,null, 0);
				if(a == JOptionPane.NO_OPTION)
				{
					return;
				}
				try {
					SpotifyDB.unfollow(userID, item.getID());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			if(item != null)
			{
				clearPanel(middlePanel);
				enableAddAllSongsButton();
				addAllSongToPlaylistButton.setGenre(item.getGenreID());
				addAllSongToPlaylistButton.setID(item.getID());
				songsList.clear();
				songAddButtons.clear();
				try 
				{
					ResultSet rs = SpotifyDB.getPlaylist(item.getID(), item.getGenreID());
					while(rs.next())
					{	
						String ID = rs.getString("s.SongID");
						String buttonText = rs.getString("s.SongName") + "  " + rs.getString("ar.ArtistName") + "   "
								+ rs.getString("g.genre") + "   " + rs.getString("s.duration"); 
						songsList.add(new Button(ID, buttonText));
						songAddButtons.add(new Button(ID, "+"));
						songAddButtons.get(songAddButtons.size()-1).setGenre(item.getGenreID());
						
					}
					
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
				for(int i = 0; i < songsList.size(); i++)
				{
					songsList.get(i).setPreferredSize(new Dimension(1000,57));
					songAddButtons.get(i).setPreferredSize(new Dimension(50,57));
					
					songsList.get(i).setFont(new Font("Consolas", Font.BOLD, 20));
					songsList.get(i).setForeground(Color.white);
					songsList.get(i).setBackground(Color.black);
					songsList.get(i).setFocusable(false);
					songsList.get(i).addMouseListener(this);
					middlePanel.add(songsList.get(i));
					
					songAddButtons.get(i).setFont(new Font("Consolas", Font.BOLD, 25));
					songAddButtons.get(i).setForeground(Color.white);
					songAddButtons.get(i).setBackground(Color.black);
					songAddButtons.get(i).setFocusable(false);
					songAddButtons.get(i).addMouseListener(this);
					middlePanel.add(songAddButtons.get(i));
					
				}
			}
			
		}
		if(e.getSource() == top10CB)
		{
			//TODO TOP10CB HALLET
			songsList.clear();
			songAddButtons.clear();
			clearPanel(middlePanel);
			String text = top10CB.getSelectedItem().toString();	
			ResultSet rs;
			try 
			{
				
				if(text.equals("Global"))
				{
					rs = SpotifyDB.getMostPlayedWorld();
				}
				else if(text.equals("Jazz"))
				{
					rs = SpotifyDB.getMostPlayedByGenre("1");
				}
				else if(text.equals("Pop"))
				{
					rs = SpotifyDB.getMostPlayedByGenre("2");
				}
				else if(text.equals("Classical"))
				{
					rs = SpotifyDB.getMostPlayedByGenre("3");
				}
				else
				{
					CBItem item = (CBItem)top10CB.getSelectedItem();
					rs = SpotifyDB.getMostPlayedByCountry(item.getID());
				}
				
				int i = 1;
				while(rs.next() && i <= 10)
				{	
					String ID = rs.getString("s.SongID");
					String buttonText = i + ".    " +rs.getString("s.SongName") + "    " + rs.getString("a.AlbumName") + "    "
							 + rs.getString("ar.ArtistName") + "    " + rs.getString("s.timesPlayed") + "    " 
							 + rs.getString("g.genre") +"   " +  rs.getString("c.country"); 
					songsList.add(new Button(ID, buttonText));
					songAddButtons.add(new Button(ID, "+"));
					songAddButtons.get(songAddButtons.size()-1).setGenre(rs.getString("genreID"));
					i++;
				}
				
				
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			for(int i = 0; i < songsList.size(); i++)
			{
				songsList.get(i).setPreferredSize(new Dimension(1000,50));
				songAddButtons.get(i).setPreferredSize(new Dimension(50,50));
				
				songsList.get(i).setFont(new Font("Consolas", Font.BOLD, 17));
				songsList.get(i).setForeground(Color.white);
				songsList.get(i).setBackground(Color.black);
				songsList.get(i).setFocusable(false);
				songsList.get(i).addMouseListener(this);
				middlePanel.add(songsList.get(i));
				
				songAddButtons.get(i).setFont(new Font("Consolas", Font.BOLD, 25));
				songAddButtons.get(i).setForeground(Color.white);
				songAddButtons.get(i).setBackground(Color.black);
				songAddButtons.get(i).setFocusable(false);
				songAddButtons.get(i).addMouseListener(this);
				middlePanel.add(songAddButtons.get(i));
				
			}
			
			
		}
		
	}
	


}
