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
	/*---------------------------------------------------------*/
	
	//---Middle Panel---
	/*Orta panelde bulunacak olan listelerin oluturulmasi*/
	JPanel middlePanel;
	final int MIDDLE_LABEL_WIDTH = 1100;
	final int MIDDLE_LABEL_HEIGHT = 55;
	ArrayList<Button> followerList = new ArrayList<Button>();
	ArrayList<Button> artistAlbumsList = new ArrayList<Button>();
	ArrayList<Button> songsList = new ArrayList<Button>();
	/*--------------------------------------------------------*/
	
	//---BottomPanel---
	/*Alt panelde bulunan butonlar icin gerekli degiskenler*/
	JPanel bottomPanel;
	JProgressBar progressBar;
	JButton playButton;
	int playButtonID;
	JButton forwardButton;
	JButton backwardButton;
	JButton fastforwardButton;
	JButton fastbackwardButton;
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
		//initFollowerListButtons();
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
	
	/*Gerekli butonlari alta itecek ve gerekli comboBox'i gosterecek olan metoddur*/
	public void addPlayclistCB(ArrayList<Button> buttons, Button button, JPanel panel) throws SQLException
	{
		
		clearPanel(leftPanel);
		playlistsCB.removeAllItems();
		playlistsCB.addItem(new CBItem(button.ID, "Jazz"));
		playlistsCB.addItem(new CBItem(button.ID, "Pop"));
		playlistsCB.addItem(new CBItem(button.ID, "Classical"));
		playlistsCB.setEditable(true);
		playlistsCB.setBounds((int)button.getLocation().getX(),  (int)button.getLocation().getY()+50, 150, 25);
		panel.add(playlistsCB);
		initUserButtons();
		for(int i = 0; i < buttons.size(); i++)
		{
			leftPanel.add(buttons.get(i));
		}
		repaint();
		
		
	}
	/*-----------------------------------------------------------------------------------*/
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
				addPlayclistCB(userButtons, btn2, leftPanel);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		else if(e.getSource() == myPlaylistsButton)
		{
			clearPanel(leftPanel);
			
			for(int i = 0; i < userPlaylistButtons.size(); i++)
			{
				leftPanel.add(userPlaylistButtons.get(i));
			}
			
		}
		else if(e.getSource() == usersButton)
		{
			clearPanel(leftPanel);
			
			for(int i = 0; i < userButtons.size(); i++)
			{
				leftPanel.add(userButtons.get(i));
			}
		}
		else if(e.getSource() == artistsButton)
		{
			clearPanel(leftPanel);
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
					artistAlbumsList.get(i).setBounds(0, 50, 150, 50);
				}
				else
				{
					artistAlbumsList.get(i).setBounds(0, (int)artistAlbumsList.get(i-1).getLocation().getY()+70, 150, 50);
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
			try 
			{
				ResultSet rs = SpotifyDB.getAlbumSongs(button.getID());
				//SongID SongName
				while(rs.next())
				{
					songsList.add(new Button(rs.getString("SongID"), rs.getString("SongName")));
					songsList.get(songsList.size()-1).setGenre(rs.getString("genre"));
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
					songsList.get(i).setBounds(0, 50, 150, 50);
				}
				else
				{
					songsList.get(i).setBounds(0, (int)songsList.get(i-1).getLocation().getY()+70, 150, 50);
				}
				songsList.get(i).setFont(new Font("Consolas", Font.BOLD, 20));
				songsList.get(i).setForeground(Color.white);
				songsList.get(i).setBackground(Color.black);
				songsList.get(i).setFocusable(false);
				songsList.get(i).addMouseListener(this);
				middlePanel.add(songsList.get(i));
				
			}
			
		}
		else if(songsList.contains(btn))
		{
			Button button = (Button)btn;
			int a = JOptionPane.showOptionDialog(null, button.getText() + " going to be added to your playlist.", "Warning", JOptionPane.YES_NO_OPTION , JOptionPane.INFORMATION_MESSAGE, null,null, 0);
			if(a == JOptionPane.YES_OPTION)
			{
				try {
					SpotifyDB.addToPlaylist(this.userID, button.getID(), button.getGenre());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else
			{
				return;
			}
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
	public void initFollowerListButtons() throws SQLException
	{
		//followerList
		ResultSet rs = SpotifyDB.getFollowers(userID);
		while(rs.next())
		{
			followerList.add(new Button(rs.getString("f.FollowerID"), rs.getString("u.userID")));
		}
		
		for(int i = 0; i < followerList.size(); i++)
		{
			if(i == 0)
			{
				followerList.get(i).setBounds(0, 50, 150, 50);
			}
			else
			{
				followerList.get(i).setBounds(0, (int)followerList.get(i-1).getLocation().getY()+70, 150, 50);
			}
			followerList.get(i).setFont(new Font("Consolas", Font.BOLD, 20));
			followerList.get(i).setForeground(Color.white);
			followerList.get(i).setBackground(Color.black);
			followerList.get(i).setFocusable(false);
			followerList.get(i).addMouseListener(this);
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
