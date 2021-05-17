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
	
	
	
	
	//---Top panel---
	/*Ust panelde bulunacak olan gerekli degiskenler*/
	JPanel topPanel;
	JButton myPlaylistsButton;
	JButton usersButton;
	JButton artistsButton;
	JButton followers;
	JButton following;
	/*---------------------------------------------------------*/	
	
	//---Left Panel---
	/*Sol panelde bulunacak gerekli listelerin olusturulmasi*/
	JPanel leftPanel;
	ArrayList<Button> userButtons;
	JComboBox<CBItem> playlistsCB = new JComboBox<CBItem>();
	ArrayList<Button> userPlaylistButtons = new ArrayList<Button>();
	ArrayList<Button> artistButtonsList = new ArrayList<Button>();
	ArrayList<Button> followingButtonList = new ArrayList<Button>();
	
	/*---------------------------------------------------------*/
	
	//---Middle Panel---
	/*Orta panelde bulunacak olan listelerin oluturulmasi*/
	JPanel middlePanel;
	final int MID_PNL_BTN_W = 1130;
	final int MID_PNL_BTN_H = 57;
	ArrayList<Button> artistAlbumsList = new ArrayList<Button>();
	ArrayList<Button> songsList = new ArrayList<Button>();
	ArrayList<Button> songAddButtons = new ArrayList<Button>();
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
	JLabel playingSongLabel;
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
	
	/*---Sol panel icin gerekli ayarlamalarin yapildigi metod---*/
	public void initLeftPanel()
	{
		leftPanel = new JPanel();
		leftPanel.setBounds(0,0,150,620);
		leftPanel.setBackground(Color.red);
		leftPanel.setLayout(null);
		this.add(leftPanel);
	}
	/*-----------------------------------------------------------------------------------*/
	
	/*Yukardaki panelin ayarlamalarinin yapildigi ve uzerinde bulunacak butonlarin eklendigi metod */
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
	/*-----------------------------------------------------------------------------------*/
	
	/*Ortada bulunan panelin ayarlamalarinin yapildigi metod*/
	public void initMiddlePanel()
	{
		middlePanel = new JPanel();
		middlePanel.setBounds(150,50,1130,570);
		middlePanel.setBackground(Color.white);
		//middlePanel.setBackground(new Color(33,33,33).brighter());
		middlePanel.setLayout(null);
		this.add(middlePanel);
	}
	/*-------------------------------------------------------------------------------------*/
	
	/*Alt panelin ayarlamalarinin yapildigi metod*/
	public void initBottomPanel()
	{

		bottomPanel = new JPanel();
		bottomPanel.setBounds(0,620,1280,100);
		bottomPanel.setBackground(Color.green);
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
					}
					else
					{
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
				if(i == 0)
				{
					artistAlbumsList.get(i).setBounds(0, 50, MID_PNL_BTN_W, MID_PNL_BTN_H);
				}
				else
				{
					artistAlbumsList.get(i).setBounds(0, (int)artistAlbumsList.get(i-1).getLocation().getY()+50, MID_PNL_BTN_W, MID_PNL_BTN_H);
				}
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
			//TODO
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
				if(i == 0)
				{
					songsList.get(i).setBounds(0, 0, MID_PNL_BTN_W-50, MID_PNL_BTN_H);
					songAddButtons.get(i).setBounds(MID_PNL_BTN_W-50, 0, 50,MID_PNL_BTN_H);
				}
				else
				{
					songsList.get(i).setBounds(0, (int)songsList.get(i-1).getLocation().getY()+70, MID_PNL_BTN_W-50, MID_PNL_BTN_H);
					songAddButtons.get(i).setBounds(MID_PNL_BTN_W-50, (int)songAddButtons.get(i-1).getLocation().getY()+70, 50,MID_PNL_BTN_H);
				}
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
				while(rs.next())
				{	
					String ID = rs.getString("s.SongID");
					String buttonText = rs.getString("s.SongName") + "  " + rs.getString("ar.ArtistName") + "   "
							+ rs.getString("g.genre") + "   " + rs.getString("s.duration"); 
					songsList.add(new Button(ID, buttonText));
					
				}
				for(int i = 0; i < songsList.size(); i++)
				{
					if(i == 0)
					{
						songsList.get(i).setBounds(0, 0, MID_PNL_BTN_W-50, MID_PNL_BTN_H);
					}
					else
					{
						songsList.get(i).setBounds(0, (int)songsList.get(i-1).getLocation().getY()+70, MID_PNL_BTN_W-50, MID_PNL_BTN_H);
					}
					songsList.get(i).setFont(new Font("Consolas", Font.BOLD, 20));
					songsList.get(i).setForeground(Color.white);
					songsList.get(i).setBackground(Color.black);
					songsList.get(i).setFocusable(false);
					songsList.get(i).addMouseListener(this);
					middlePanel.add(songsList.get(i));
					
					
					
				}
			} 
			catch (SQLException e1) 
			{
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
					button.setBounds(0, y, MID_PNL_BTN_W, MID_PNL_BTN_H);
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
			playlistsCB.setLocation((int)btn2.getLocation().getX(),  (int)btn2.getLocation().getY()+50);
			leftPanel.add(playlistsCB);
			for(int i = 0; i < followingButtonList.size(); i++)
			{
				leftPanel.add(followingButtonList.get(i));
			}
			repaint();
		}
			
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
			if(i == 0)
			{
				followingButtonList.get(i).setBounds(0, 50, 150, 50);
			}
			else
			{
				followingButtonList.get(i).setBounds(0, (int)followingButtonList.get(i-1).getLocation().getY()+70, 150, 50);
			}
			followingButtonList.get(i).setFont(new Font("Consolas", Font.BOLD, 20));
			followingButtonList.get(i).setForeground(Color.white);
			followingButtonList.get(i).setBackground(Color.black);
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
		ResultSet rs = SpotifyDB.getAllArtists();
		artistButtonsList.clear();
		while(rs.next())
		{
			artistButtonsList.add(new Button(rs.getString("ArtistID"), rs.getString("ArtistName")));
		}
		for(int i = 0; i < artistButtonsList.size(); i++)
		{
			if(i == 0)
			{
				artistButtonsList.get(i).setBounds(0, 50, 150, 50);
			}
			else
			{
				artistButtonsList.get(i).setBounds(0, (int)artistButtonsList.get(i-1).getLocation().getY()+70, 150, 50);
			}
			artistButtonsList.get(i).setFont(new Font("Consolas", Font.BOLD, 20));
			artistButtonsList.get(i).setForeground(Color.white);
			artistButtonsList.get(i).setBackground(Color.black);
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
			clearPanel(middlePanel);
			//TODO
			songsList.clear();
			songAddButtons.clear();
			if(item != null)
			{
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
					if(i == 0)
					{
						songsList.get(i).setBounds(0, 50, MID_PNL_BTN_W-50, MID_PNL_BTN_H);
						songAddButtons.get(i).setBounds(MID_PNL_BTN_W-50, 50, 50,MID_PNL_BTN_H);
					}
					else
					{
						songsList.get(i).setBounds(0, (int)songsList.get(i-1).getLocation().getY()+70, MID_PNL_BTN_W-50, MID_PNL_BTN_H);
						songAddButtons.get(i).setBounds(MID_PNL_BTN_W-50, (int)songAddButtons.get(i-1).getLocation().getY()+70, 50,MID_PNL_BTN_H);
					}
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
		
	}
	


}
