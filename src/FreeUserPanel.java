import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	String userID;
	FreeUserPanel(String ID)
	{
		userID = ID;
		this.setLayout(null);
		this.setBackground(new Color(33,33,33));
		initPanels();
		setAlbumCoverLabel();
		initButtonImgs();
		initButtons();
		try {
			addMiddleLabels();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		progressBar = new JProgressBar();
		progressBar.setBounds(BUTTON_LOCATION_X-30,BUTTON_LOCATION_Y+50,350,15);
		
		bottomPanel.add(albumCoverLabel);
		bottomPanel.add(progressBar);
		this.add(leftPanel);
		this.add(middlePanel);
		this.add(bottomPanel);
		
		
		
		this.setPreferredSize(new Dimension(1280,720));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void initPanels()
	{
		leftPanel = new JPanel();
		leftPanel.setBounds(0,0,180,550);
		leftPanel.setBackground(Color.red);
		leftPanel.setLayout(null);
		
		middlePanel = new JPanel();
		middlePanel.setBounds(180,0,1100,550);
		//middlePanel.setBackground(Color.blue);
		middlePanel.setBackground(new Color(33,33,33).brighter());
		middlePanel.setLayout(null);
		
		bottomPanel = new JPanel();
		bottomPanel.setBounds(0,550,1280,100);
		bottomPanel.setBackground(Color.green);
		bottomPanel.setLayout(null);
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
	
	public void addLeftLabels() throws SQLException
	{
		
		
	}
	@Override
	public void mousePressed(MouseEvent e) 
	{
		changePressedBackground((JComponent)e.getSource());
		
		
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
