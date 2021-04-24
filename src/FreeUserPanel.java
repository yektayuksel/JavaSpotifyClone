import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class FreeUserPanel extends JPanel implements MouseListener
{
	JPanel leftPanel;
	JPanel middlePanel;
	JPanel bottomPanel;
	JLabel albumCoverLabel;
	
	JButton playButton;
	int playButtonID;
	JButton forwardButton;
	JButton backwardButton;
	JButton fastforwardButton;
	JButton fastbackwardButton;
	final int BUTTON_SIZE = 50;
	
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
	
	
	
	FreeUserPanel()
	{
		this.setLayout(null);
		this.setBackground(new Color(33,33,33));
		initPanels();
		setAlbumCoverLabel();
		initButtonImgs();
		initButtons();

		
		progressBar = new JProgressBar();
		//progressBar.setBackground(Color.black);
		progressBar.setBounds(550+180-225,100,450,20);
		
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
		middlePanel.setBackground(Color.blue);
		middlePanel.setLayout(null);
		
		bottomPanel = new JPanel();
		bottomPanel.setBounds(0,550,1280,170);
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
		playButton = new JButton();
		playButton.setOpaque(false);
		playButton.setContentAreaFilled(false);
		playButton.setBorderPainted(false);
		playButton.setIcon((new ImageIcon(getScaledImage(playButtonImg1.getImage(), BUTTON_SIZE, BUTTON_SIZE))));
		playButton.setPressedIcon((new ImageIcon(getScaledImage(playButtonImg2.getImage(), BUTTON_SIZE, BUTTON_SIZE))));
		playButton.setFocusable(false);
		playButton.setBounds(705,20,BUTTON_SIZE,BUTTON_SIZE);
		playButtonID = 0;
		playButton.addMouseListener(this);
		bottomPanel.add(playButton);
		

		forwardButton = new JButton();
		forwardButton.setOpaque(false);
		forwardButton.setContentAreaFilled(false);
		forwardButton.setBorderPainted(false);
		forwardButton.setIcon((new ImageIcon(getScaledImage(forwardButtonImg1.getImage(), BUTTON_SIZE, BUTTON_SIZE))));
		forwardButton.setPressedIcon((new ImageIcon(getScaledImage(forwardButtonImg2.getImage(), BUTTON_SIZE, BUTTON_SIZE))));
		forwardButton.setFocusable(false);
		forwardButton.setBounds(835,20,BUTTON_SIZE,BUTTON_SIZE);
		forwardButton.addMouseListener(this);
		bottomPanel.add(forwardButton);
		
		backwardButton = new JButton();
		backwardButton.setOpaque(false);
		backwardButton.setContentAreaFilled(false);
		backwardButton.setBorderPainted(false);
		backwardButton.setIcon((new ImageIcon(getScaledImage(backwardButtonImg1.getImage(), BUTTON_SIZE, BUTTON_SIZE))));
		backwardButton.setPressedIcon((new ImageIcon(getScaledImage(backwardButtonImg2.getImage(), BUTTON_SIZE, BUTTON_SIZE))));
		backwardButton.setFocusable(false);
		backwardButton.setBounds(575,20,BUTTON_SIZE,BUTTON_SIZE);
		backwardButton.addMouseListener(this);
		bottomPanel.add(backwardButton);
		
		fastforwardButton = new JButton();
		fastforwardButton.setOpaque(false);
		fastforwardButton.setContentAreaFilled(false);
		fastforwardButton.setBorderPainted(false);
		fastforwardButton.setIcon((new ImageIcon(getScaledImage(fastforwardButtonImg.getImage(), 45, 45))));
		fastforwardButton.setFocusable(false);
		fastforwardButton.setBounds(770,23,45,45);
		fastforwardButton.addMouseListener(this);
		bottomPanel.add(fastforwardButton);
		
		
		fastbackwardButton = new JButton();
		fastbackwardButton.setOpaque(false);
		fastbackwardButton.setContentAreaFilled(false);
		fastbackwardButton.setBorderPainted(false);
		fastbackwardButton.setIcon((new ImageIcon(getScaledImage(fastbackwardButtonImg.getImage(), 45, 45))));
		fastbackwardButton.setFocusable(false);
		fastbackwardButton.setBounds(645,23,45,45);
		fastbackwardButton.addMouseListener(this);
		bottomPanel.add(fastbackwardButton);
		
		
		
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
	public void setAlbumCoverLabel() //DuzenlendiÃ°inde parametre olarak Image AlbumCover alacak
	{
		albumCoverLabel = new JLabel();
		albumCoverLabel.setBackground(Color.white);
		albumCoverLabel.setBounds(20,15,140,140);
		albumCoverLabel.setOpaque(true);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		if(e.getSource() == playButton)
		{
			if(playButtonID == 0)
			{
				//Ã�arkÃ½ oynamaya baÃ¾lar
				playButton.setIcon((new ImageIcon(getScaledImage(pauseButtonImg1.getImage(), BUTTON_SIZE, BUTTON_SIZE))));
				playButton.setPressedIcon((new ImageIcon(getScaledImage(pauseButtonImg2.getImage(), BUTTON_SIZE, BUTTON_SIZE))));
				playButtonID = 1;
			}
			else if(playButtonID == 1)
			{
				
				//Ã�arkÃ½ durdurulur
				playButton.setIcon((new ImageIcon(getScaledImage(playButtonImg1.getImage(), BUTTON_SIZE, BUTTON_SIZE))));
				playButton.setPressedIcon((new ImageIcon(getScaledImage(playButtonImg2.getImage(), BUTTON_SIZE, BUTTON_SIZE))));
				playButtonID = 0;
			}
		}
		
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
