import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;

import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import java.util.Arrays;

import java.awt.Robot;
/**
@File: DrawPattern.java
@Date: Friday, 24 February, 2017
@author: Xiao Shang
@For Display Test
*/
public class DrawPattern extends JFrame
		implements MouseListener,MouseMotionListener{
	private int patternWidth;
	private int patternHeight;
	
	Graphics gPattern = null;
	Image iPattern = null;
	
	static Timer timerFlash = null;
	int flashi = 0;
	
	boolean enablePattern2 = false;
	JPanel jpDrawPattern = new JPanel();
	JPanel jpColorValue = new JPanel();
	JLabel jlColorValue[] = new JLabel[3];
	JTextField jtfColorValue[] = new JTextField[3];
	//private static Robot robot = null;
	Color colorValue;
	boolean colorValueFlag = false;
	
	public DrawPattern(){
		if(DisplayTest.EnableSmallShowFlag){
			patternWidth = 320;//Toolkit.getDefaultToolkit().getScreenSize().width;
			patternHeight = 240;//Toolkit.getDefaultToolkit().getScreenSize().height;
		}
		else{
			patternWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
			patternHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		}
		setBounds(0,0,patternWidth,patternHeight);
		setUndecorated(true);
		setVisible(true);
		addMouseListener(this);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);//EXIT_ON_CLOSE);
	}
	public DrawPattern(int Flag){
		if(DisplayTest.EnableSmallShowFlag){
			patternWidth = 320;//Toolkit.getDefaultToolkit().getScreenSize().width;
			patternHeight = 240;//Toolkit.getDefaultToolkit().getScreenSize().height;
		}
		else{
			patternWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
			patternHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		}
		//
		jpDrawPattern.setLayout(null);
		//jpDrawPattern.setBackground(null);
		//jpDrawPattern.setOpaque(false);
		jpColorValue.setLayout(null);
		jpColorValue.setBorder(BorderFactory.createTitledBorder("Color Value"));
		jpColorValue.setBounds(10,10,160,120);
		jpDrawPattern.add(jpColorValue);
		this.add(jpDrawPattern);
		jlColorValue[0] = new JLabel("RED");
		jlColorValue[1] = new JLabel("GREEN");
		jlColorValue[2] = new JLabel("BLUE");
		jtfColorValue[0] = new JTextField("0");
//		jtfColorValue[1] = new JTextField("0");
//		jtfColorValue[2] = new JTextField("0");
		for(int i =0;i<3;i++){
			jlColorValue[i].setBounds(20,20+30*i,70,30);
			jpColorValue.add(jlColorValue[i]);
			jtfColorValue[i] = new JTextField("0");
			jtfColorValue[i].setBounds(100,20+30*i,60,30);
			jtfColorValue[i].setFocusable(false);
			jpColorValue.add(jtfColorValue[i]);
		}
		//
		addMouseListener(this);
		addMouseMotionListener(this);
		setBounds(0,0,patternWidth,patternHeight);
		setUndecorated(true);
		setVisible(true);
		//patternEvent();
		setAlwaysOnTop(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);//EXIT_ON_CLOSE);
	}
	public DrawPattern(int perWidth,int perHeight){
		//patternWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		//patternHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		setBounds(0,0,patternWidth,patternHeight);
		setUndecorated(true);
		setVisible(true);
		addMouseListener(this);
		//patternEvent();
		setAlwaysOnTop(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);//EXIT_ON_CLOSE);
	}
	public void paint(Graphics g){
		if(iPattern != null){
			g.drawImage(iPattern,0,0,this);
		//jpDrawPattern.repaint();
		if(colorValueFlag)
			jpColorValue.repaint();}
	}
	public void update(Graphics g){
		paint(g);
	}
	public void frameInit(){
		//iPattern = createImage(patternWidth,patternHeight);
	}
	public void setPatternWidth(int patternWidth){
		this.patternWidth = patternWidth;
	}
	public void setPatternHeight(int patternHeight){
		this.patternHeight = patternHeight;
	}
	public int getPatternWidth(){
		return patternWidth;
	}
	public int getPatternHeight(){
		return patternHeight;
	}
	
	public void mouseClicked(MouseEvent me){
		if(MouseEvent.BUTTON1 == me.getButton()){
			if(enablePattern2){
				enablePattern2 = false;
				gPattern.setColor(Color.WHITE);
				gPattern.fillRect(0,0,patternWidth,patternHeight);

				repaint();
			}
		}
		if(MouseEvent.BUTTON3 == me.getButton()){//Right Button
			if(0 != flashi) timerFlash.cancel();//
			enablePattern2 = false;
			colorValueFlag = false;
			dispose();
		}
	}
	public void mouseEntered(MouseEvent me){
			//dispose();
			//System.out.println("mouseEntered!");
	}
	public void mouseExited(MouseEvent me){
			//System.out.println("mouseExited!");
	}
	public void mousePressed(MouseEvent me){
			//ystem.out.println("mousePressed!");
	}
	public void mouseReleased(MouseEvent me){
			//System.out.println("mouseReleased!");
	}

	public void mouseDragged(MouseEvent me){
	}
	public void mouseMoved(MouseEvent me){
		if(colorValueFlag){
		try {
            //Robot robot = new Robot();
			colorValue = new Robot().getPixelColor(me.getX(),me.getY());
			jtfColorValue[0].setText(String.valueOf(colorValue.getRed()));
			jtfColorValue[1].setText(String.valueOf(colorValue.getGreen()));
			jtfColorValue[2].setText(String.valueOf(colorValue.getBlue()));
			//repaint();
		} catch (Exception e) {
		}
		}
	}
	
	
	//Pattern
	//Grayscale
	public void drawGrayscale(Color perColor,int perStep,int perFlag){
		iPattern = createImage(patternWidth,patternHeight);
		gPattern = iPattern.getGraphics();
		//Color
		int perColorR = perColor.getRed();
		int perColorG = perColor.getGreen();
		int perColorB = perColor.getBlue();
		switch(perFlag){
			case 0x0000:{
				for(int i=0;i<perStep;i++){
					int stepPerColorR = perColorR*i/(perStep-1);
					int stepPerColorG = perColorG*i/(perStep-1);
					int stepPerColorB = perColorB*i/(perStep-1);
					gPattern.setColor(new Color(stepPerColorR,stepPerColorG,stepPerColorB));
					int stepX = 0;
					int stepY = patternHeight*i/perStep;
					int stepWidth = patternWidth;
					int stepHeight = patternHeight*(i+1)/perStep - patternHeight*i/perStep;
					gPattern.fillRect(stepX,stepY,stepWidth,stepHeight);
				}
				break;
			}
			case 0x0001:{
				for(int i=0;i<perStep;i++){
					int stepPerColorR = perColorR*i/(perStep-1);
					int stepPerColorG = perColorG*i/(perStep-1);
					int stepPerColorB = perColorB*i/(perStep-1);
					gPattern.setColor(new Color(stepPerColorR,stepPerColorG,stepPerColorB));
					int stepX = patternWidth*i/perStep;
					int stepY = 0;
					int stepWidth = patternWidth*(i+1)/perStep - patternWidth*i/perStep;;
					int stepHeight = patternHeight;
					gPattern.fillRect(stepX,stepY,stepWidth,stepHeight);
				}
				break;
			}
			case 0x0002:{
				for(int i=0;i<perStep;i++){
					int stepPerColorR = perColorR*i/(perStep-1);
					int stepPerColorG = perColorG*i/(perStep-1);
					int stepPerColorB = perColorB*i/(perStep-1);
					gPattern.setColor(new Color(stepPerColorR,stepPerColorG,stepPerColorB));
					int j = perStep - i -1;
					int stepX = 0;
					int stepY = patternHeight*j/perStep;
					int stepWidth = patternWidth;
					int stepHeight = patternHeight*(j+1)/perStep - patternHeight*j/perStep;
					gPattern.fillRect(stepX,stepY,stepWidth,stepHeight);
				}
				break;
			}
			case 0x0003:{
				for(int i=0;i<perStep;i++){
					int stepPerColorR = perColorR*i/(perStep-1);
					int stepPerColorG = perColorG*i/(perStep-1);
					int stepPerColorB = perColorB*i/(perStep-1);
					gPattern.setColor(new Color(stepPerColorR,stepPerColorG,stepPerColorB));
					int j = perStep - i -1;
					int stepX = patternWidth*j/perStep;
					int stepY = 0;
					int stepWidth = patternWidth*(j+1)/perStep - patternWidth*j/perStep;;
					int stepHeight = patternHeight;
					gPattern.fillRect(stepX,stepY,stepWidth,stepHeight);
				}
				break;
			}
			case 0x0004:{
				for(int i=0;i<perStep;i++){
					int stepPerColorR = perColorR*i/(perStep-1);
					int stepPerColorG = perColorG*i/(perStep-1);
					int stepPerColorB = perColorB*i/(perStep-1);
					gPattern.setColor(new Color(stepPerColorR,stepPerColorG,stepPerColorB));
					int stepX = 0;
					int stepY = patternHeight*i/perStep;
					int stepWidth = patternWidth/2;
					int stepHeight = patternHeight*(i+1)/perStep - patternHeight*i/perStep;
					gPattern.fillRect(stepX,stepY,stepWidth,stepHeight);
					//right
					int j = perStep - i-1;
					stepX = patternWidth/2;
					stepY = patternHeight*j/perStep;
					//stepWidth = patternWidth/2;
					stepHeight = patternHeight*(j+1)/perStep - patternHeight*j/perStep;
					gPattern.fillRect(stepX,stepY,stepWidth,stepHeight);
				}
				break;
			}
			case 0x0005:{
				for(int i=0;i<perStep;i++){
					int stepPerColorR = perColorR*i/(perStep-1);
					int stepPerColorG = perColorG*i/(perStep-1);
					int stepPerColorB = perColorB*i/(perStep-1);
					gPattern.setColor(new Color(stepPerColorR,stepPerColorG,stepPerColorB));
					int stepX = patternWidth*i/perStep;
					int stepY = 0;
					int stepWidth = patternWidth*(i+1)/perStep - patternWidth*i/perStep;;
					int stepHeight = patternHeight/2;
					gPattern.fillRect(stepX,stepY,stepWidth,stepHeight);
					//buttom
					int j = perStep - i -1;
					stepX = patternWidth*j/perStep;
					stepY = patternHeight/2;
					stepWidth = patternWidth*(j+1)/perStep - patternWidth*j/perStep;;
					//stepHeight = patternHeight/2;
					gPattern.fillRect(stepX,stepY,stepWidth,stepHeight);
				}
				break;
			}
			case 0x0006:{
				for(int i=0;i<perStep;i++){
					int stepPerColorR = perColorR*i/(perStep-1);
					int stepPerColorG = perColorG*i/(perStep-1);
					int stepPerColorB = perColorB*i/(perStep-1);
					gPattern.setColor(new Color(stepPerColorR,stepPerColorG,stepPerColorB));
					int j = perStep - i-1;
					int stepX = 0;
					int stepY = patternHeight*j/perStep;
					int stepWidth = patternWidth/2;
					int stepHeight = patternHeight*(j+1)/perStep - patternHeight*j/perStep;
					gPattern.fillRect(stepX,stepY,stepWidth,stepHeight);
					//right
					stepX = patternWidth/2;
					stepY = patternHeight*i/perStep;
					//stepWidth = patternWidth/2;
					stepHeight = patternHeight*(i+1)/perStep - patternHeight*i/perStep;
					gPattern.fillRect(stepX,stepY,stepWidth,stepHeight);
				}
				break;
			}
			case 0x0007:{
				for(int i=0;i<perStep;i++){
					int stepPerColorR = perColorR*i/(perStep-1);
					int stepPerColorG = perColorG*i/(perStep-1);
					int stepPerColorB = perColorB*i/(perStep-1);
					gPattern.setColor(new Color(stepPerColorR,stepPerColorG,stepPerColorB));
					int j = perStep - i -1;
					int stepX = patternWidth*j/perStep;
					int stepY = 0;
					int stepWidth = patternWidth*(j+1)/perStep - patternWidth*j/perStep;;
					int stepHeight = patternHeight/2;
					gPattern.fillRect(stepX,stepY,stepWidth,stepHeight);
					//buttom
					stepX = patternWidth*i/perStep;
					stepY = patternHeight/2;
					stepWidth = patternWidth*(i+1)/perStep - patternWidth*i/perStep;;
					stepHeight = patternHeight/2;
					gPattern.fillRect(stepX,stepY,stepWidth,stepHeight);
				}
				break;
			}
			case 0x0008:{
				for(int i=0;i<perStep;i++){
					int stepPerColorR = perColorR*i/(perStep-1);
					int stepPerColorG = perColorG*i/(perStep-1);
					int stepPerColorB = perColorB*i/(perStep-1);
					//P1
					gPattern.setColor(new Color(stepPerColorR,0,0));
					int stepX = 0;
					int stepY = patternHeight*i/perStep;
					int stepWidth = patternWidth/4;
					int stepHeight = patternHeight*(i+1)/perStep - patternHeight*i/perStep;
					for(int j = 0; j<4;j++){
						if(0 == j)//P1
								gPattern.setColor(new Color(stepPerColorR,0,0));
							else if(1 == j)//P2
								gPattern.setColor(new Color(0,stepPerColorG,0));
							else if(2 == j)//P3
								gPattern.setColor(new Color(0,0,stepPerColorB));
							else if(3 == j)//P4
								gPattern.setColor(new Color(stepPerColorR,stepPerColorG,stepPerColorB));
						stepX = patternWidth*j/4;
						//stepY = patternHeight*i/perStep;
						stepWidth = patternWidth*(j+1)/4 - patternWidth*j/4;
						//stepHeight = patternHeight*(i+1)/perStep - patternHeight*i/perStep;
						gPattern.fillRect(stepX,stepY,stepWidth,stepHeight);
					}
				}
				break;
			}
			case 0x0009:{
				for(int i=0;i<perStep;i++){
					int stepPerColorR = perColorR*i/(perStep-1);
					int stepPerColorG = perColorG*i/(perStep-1);
					int stepPerColorB = perColorB*i/(perStep-1);
					//P1
					gPattern.setColor(new Color(stepPerColorR,0,0));
					int stepX = patternWidth*i/perStep;
					int stepY = 0;
					int stepWidth = patternWidth*(i+1)/perStep - patternWidth*i/perStep;;
					int stepHeight = patternHeight/4;
					for(int j = 0;j<4;j++){
						if(0 == j)//P1
							gPattern.setColor(new Color(stepPerColorR,0,0));
						else if(1 == j)//P2
							gPattern.setColor(new Color(0,stepPerColorG,0));
						else if(2 == j)//P3
							gPattern.setColor(new Color(0,0,stepPerColorB));
						else if(3 == j)//P4
							gPattern.setColor(new Color(stepPerColorR,stepPerColorG,stepPerColorB));
						//stepX = patternWidth*i/perStep;
						stepY = patternHeight*j/4;
						//stepWidth = patternWidth*(i+1)/perStep - patternWidth*i/perStep;;
						stepHeight = patternHeight*(j+1)/4-patternHeight*j/4;
						gPattern.fillRect(stepX,stepY,stepWidth,stepHeight);
					}
				}
				break;
			}
			case 0x000A:{
				for(int i=0;i<perStep;i++){
					int k = perStep - i -1;
					int stepPerColorR = perColorR*k/(perStep-1);
					int stepPerColorG = perColorG*k/(perStep-1);
					int stepPerColorB = perColorB*k/(perStep-1);
					//P1
					int stepX = 0;
					int stepY = patternHeight*i/perStep;
					int stepWidth = patternWidth/4;
					int stepHeight = patternHeight*(i+1)/perStep - patternHeight*i/perStep;
					
					for(int j = 0; j<4;j++){
						if(0 == j)//P1
								gPattern.setColor(new Color(stepPerColorR,0,0));
							else if(1 == j)//P2
								gPattern.setColor(new Color(0,stepPerColorG,0));
							else if(2 == j)//P3
								gPattern.setColor(new Color(0,0,stepPerColorB));
							else if(3 == j)//P4
								gPattern.setColor(new Color(stepPerColorR,stepPerColorG,stepPerColorB));
						stepX = patternWidth*j/4;
						//stepY = patternHeight*i/perStep;
						stepWidth = patternWidth*(j+1)/4 - patternWidth*j/4;
						//stepHeight = patternHeight*(i+1)/perStep - patternHeight*i/perStep;
						gPattern.fillRect(stepX,stepY,stepWidth,stepHeight);
					}
				}
				break;
			}
			case 0x000B:{
				for(int i=0;i<perStep;i++){
					int k = perStep - i -1;
					int stepPerColorR = perColorR*k/(perStep-1);
					int stepPerColorG = perColorG*k/(perStep-1);
					int stepPerColorB = perColorB*k/(perStep-1);
					//P1
					int stepX = patternWidth*i/perStep;
					int stepY = 0;
					int stepWidth = patternWidth*(i+1)/perStep - patternWidth*i/perStep;;
					int stepHeight = patternHeight/4;
					for(int j = 0;j<4;j++){
						if(0 == j)//P1
							gPattern.setColor(new Color(stepPerColorR,0,0));
						else if(1 == j)//P2
							gPattern.setColor(new Color(0,stepPerColorG,0));
						else if(2 == j)//P3
							gPattern.setColor(new Color(0,0,stepPerColorB));
						else if(3 == j)//P4
							gPattern.setColor(new Color(stepPerColorR,stepPerColorG,stepPerColorB));
						//stepX = patternWidth*i/perStep;
						stepY = patternHeight*j/4;
						//stepWidth = patternWidth*(i+1)/perStep - patternWidth*i/perStep;;
						stepHeight = patternHeight*(j+1)/4-patternHeight*j/4;
						gPattern.fillRect(stepX,stepY,stepWidth,stepHeight);
					}
				}
				break;
			}
			case 0x000C:{
				for(int i=0;i<perStep;i++){
					int stepPerColorR = perColorR*i/(perStep-1);
					int stepPerColorG = perColorG*i/(perStep-1);
					int stepPerColorB = perColorB*i/(perStep-1);
					//P1
					gPattern.setColor(new Color(stepPerColorR,0,0));
					int stepX = 0;
					int stepY = patternHeight*i/perStep;
					int stepWidth = patternWidth/8;
					int stepHeight = patternHeight*(i+1)/perStep - patternHeight*i/perStep;
					for(int j = 0; j<4;j++){
						if(0 == j)//P1
								gPattern.setColor(new Color(stepPerColorR,0,0));
							else if(1 == j)//P2
								gPattern.setColor(new Color(0,stepPerColorG,0));
							else if(2 == j)//P3
								gPattern.setColor(new Color(0,0,stepPerColorB));
							else if(3 == j)//P4
								gPattern.setColor(new Color(stepPerColorR,stepPerColorG,stepPerColorB));
						//left
						stepX = patternWidth*j*2/8;
						stepY = patternHeight*i/perStep;
						stepWidth = patternWidth*(j*2+1)/8 - patternWidth*(j*2)/8;
						stepHeight = patternHeight*(i+1)/perStep - patternHeight*i/perStep;
						gPattern.fillRect(stepX,stepY,stepWidth,stepHeight);
						//right
						int k = perStep - i-1;
						stepX = patternWidth*(j*2+1)/8;
						stepY = patternHeight*k/perStep;
						stepWidth = patternWidth*(j*2+2)/8 - patternWidth*(j*2+1)/8;
						stepHeight = patternHeight*(k+1)/perStep - patternHeight*k/perStep;
						gPattern.fillRect(stepX,stepY,stepWidth,stepHeight);
					}
				}
				break;
			}
			case 0x000D:{
				for(int i=0;i<perStep;i++){
					int stepPerColorR = perColorR*i/(perStep-1);
					int stepPerColorG = perColorG*i/(perStep-1);
					int stepPerColorB = perColorB*i/(perStep-1);
					//P1
					gPattern.setColor(new Color(stepPerColorR,0,0));
					int stepX = patternWidth*i/perStep;
					int stepY = 0;
					int stepWidth = patternWidth*(i+1)/perStep - patternWidth*i/perStep;;
					int stepHeight = patternHeight/4;
					for(int j = 0;j<4;j++){
						if(0 == j)//P1
							gPattern.setColor(new Color(stepPerColorR,0,0));
						else if(1 == j)//P2
							gPattern.setColor(new Color(0,stepPerColorG,0));
						else if(2 == j)//P3
							gPattern.setColor(new Color(0,0,stepPerColorB));
						else if(3 == j)//P4
							gPattern.setColor(new Color(stepPerColorR,stepPerColorG,stepPerColorB));
						//left
						stepX = patternWidth*i/perStep;
						stepY = patternHeight*j*2/8;
						stepWidth = patternWidth*(i+1)/perStep - patternWidth*i/perStep;;
						stepHeight = patternHeight*(j*2+1)/8-patternHeight*j*2/8;
						gPattern.fillRect(stepX,stepY,stepWidth,stepHeight);
						//right
						int k = perStep - i-1;
						stepX = patternWidth*k/perStep;
						stepY = patternHeight*(j*2+1)/8;
						stepWidth = patternWidth*(k+1)/perStep - patternWidth*k/perStep;;
						stepHeight = patternHeight*(j*2+2)/8-patternHeight*(j*2+1)/8;
						gPattern.fillRect(stepX,stepY,stepWidth,stepHeight);
					}
				}
				break;
			}
			case 0x000E:{
				for(int i=0;i<perStep;i++){
					int stepPerColorR = perColorR*(perStep - i-1)/(perStep-1);
					int stepPerColorG = perColorG*(perStep - i-1)/(perStep-1);
					int stepPerColorB = perColorB*(perStep - i-1)/(perStep-1);
					//P1
					gPattern.setColor(new Color(stepPerColorR,0,0));
					int stepX = 0;
					int stepY = patternHeight*i/perStep;
					int stepWidth = patternWidth/8;
					int stepHeight = patternHeight*(i+1)/perStep - patternHeight*i/perStep;
					for(int j = 0; j<4;j++){
						if(0 == j)//P1
								gPattern.setColor(new Color(stepPerColorR,0,0));
							else if(1 == j)//P2
								gPattern.setColor(new Color(0,stepPerColorG,0));
							else if(2 == j)//P3
								gPattern.setColor(new Color(0,0,stepPerColorB));
							else if(3 == j)//P4
								gPattern.setColor(new Color(stepPerColorR,stepPerColorG,stepPerColorB));
						//left
						stepX = patternWidth*j*2/8;
						stepY = patternHeight*i/perStep;
						stepWidth = patternWidth*(j*2+1)/8 - patternWidth*(j*2)/8;
						stepHeight = patternHeight*(i+1)/perStep - patternHeight*i/perStep;
						gPattern.fillRect(stepX,stepY,stepWidth,stepHeight);
						//right
						int k = perStep - i-1;
						stepX = patternWidth*(j*2+1)/8;
						stepY = patternHeight*k/perStep;
						stepWidth = patternWidth*(j*2+2)/8 - patternWidth*(j*2+1)/8;
						stepHeight = patternHeight*(k+1)/perStep - patternHeight*k/perStep;
						gPattern.fillRect(stepX,stepY,stepWidth,stepHeight);
					}
				}
				break;
			}
			case 0x000F:{
				for(int i=0;i<perStep;i++){
					int stepPerColorR = perColorR*(perStep - i-1)/(perStep-1);
					int stepPerColorG = perColorG*(perStep - i-1)/(perStep-1);
					int stepPerColorB = perColorB*(perStep - i-1)/(perStep-1);
					//P1
					gPattern.setColor(new Color(stepPerColorR,0,0));
					int stepX = patternWidth*i/perStep;
					int stepY = 0;
					int stepWidth = patternWidth*(i+1)/perStep - patternWidth*i/perStep;;
					int stepHeight = patternHeight/4;
					for(int j = 0;j<4;j++){
						if(0 == j)//P1
							gPattern.setColor(new Color(stepPerColorR,0,0));
						else if(1 == j)//P2
							gPattern.setColor(new Color(0,stepPerColorG,0));
						else if(2 == j)//P3
							gPattern.setColor(new Color(0,0,stepPerColorB));
						else if(3 == j)//P4
							gPattern.setColor(new Color(stepPerColorR,stepPerColorG,stepPerColorB));
						//left
						stepX = patternWidth*i/perStep;
						stepY = patternHeight*j*2/8;
						stepWidth = patternWidth*(i+1)/perStep - patternWidth*i/perStep;;
						stepHeight = patternHeight*(j*2+1)/8-patternHeight*j*2/8;
						gPattern.fillRect(stepX,stepY,stepWidth,stepHeight);
						//right
						int k = perStep - i-1;
						stepX = patternWidth*k/perStep;
						stepY = patternHeight*(j*2+1)/8;
						stepWidth = patternWidth*(k+1)/perStep - patternWidth*k/perStep;;
						stepHeight = patternHeight*(j*2+2)/8-patternHeight*(j*2+1)/8;
						gPattern.fillRect(stepX,stepY,stepWidth,stepHeight);
					}
				}
				break;
			}
		}
	}
	//PixelOn/Off
	public void drawPixelOnOff(Color perColor1,Color perColor2){
		iPattern = createImage(patternWidth,patternHeight);
		gPattern = iPattern.getGraphics();
		gPattern.setColor(perColor1);
		gPattern.fillRect(0,0,patternWidth,patternHeight);
		gPattern.setColor(perColor2);
		for(int i = 1;i < patternWidth+patternHeight; i += 2){
			gPattern.drawLine(i,0,0,i);
		}
	}
	//Hor.Line
	public void drawHorLine(int lineWidth,boolean enableVer){
		iPattern = createImage(patternWidth,patternHeight);
		gPattern = iPattern.getGraphics();
		//
		gPattern.setColor(Color.BLACK);
		gPattern.fillRect(0,0,patternWidth,patternHeight);
		gPattern.setColor(Color.WHITE);
		//gPattern.setStroke(new BasicStroke((float) lineWidth));
		if(enableVer){
			for(int i = lineWidth;i < patternWidth; i += 2*lineWidth){
				gPattern.fillRect(i,0,lineWidth,patternHeight);
				//gPattern.fillOval(i,0,lineWidth,patternHeight);
				//gPattern.drawLine(i,0,i,patternHeight);
			}
		}
		else{
			for(int i = lineWidth;i < patternHeight; i += 2*lineWidth){
				gPattern.fillRect(0,i,patternWidth,lineWidth);
				//gPattern.fillOval(0,i,patternWidth,lineWidth);
				//gPattern.drawLine(0,i,patternWidth,i);
			}
		}
	}
	//Gray Step
	public void drawGrayStep(Color perColor,boolean enableEO){
		iPattern = createImage(patternWidth,patternHeight);
		gPattern = iPattern.getGraphics();
		//
		gPattern.setColor(perColor);
		gPattern.fillRect(0,0,patternWidth,patternHeight);
		if(enableEO){
			gPattern.setColor(Color.BLACK);
			if(1 == patternWidth%2){//
				gPattern.drawLine(patternWidth/2,0,patternWidth/2,patternHeight);
				for(int i=1;i< patternWidth/2;i+=2){
					gPattern.drawLine(patternWidth/2-i,0,patternWidth/2-i,patternHeight);
					gPattern.drawLine(patternWidth/2+i,0,patternWidth/2+i,patternHeight);
				}
			}
			else if(0 == patternWidth%2){
				for(int i=1;i< patternWidth/2;i+=2){
					gPattern.drawLine(patternWidth/2-i,0,patternWidth/2-i,patternHeight);
					gPattern.drawLine(patternWidth/2+i-1,0,patternWidth/2+i-1,patternHeight);
				}
			}
		}
	}
	//others1
	public void drawPosition(){
		iPattern = createImage(patternWidth,patternHeight);
		gPattern = iPattern.getGraphics();
		//
		gPattern.setColor(Color.BLACK);
		gPattern.fillRect(0,0,patternWidth,patternHeight);
		gPattern.setColor(Color.WHITE);
		gPattern.drawRect(0,0,patternWidth-1,patternHeight-1);
		gPattern.drawLine(0,0,patternWidth,patternHeight);
		gPattern.drawLine(0,patternHeight,patternWidth,0);
	}
	public void drawColorBar(){
		iPattern = createImage(patternWidth,patternHeight);
		gPattern = iPattern.getGraphics();
		//
		Color perColor[] = new Color[16];
		perColor[0] = Color.WHITE;
		perColor[1] = Color.BLUE;
		perColor[2] = Color.CYAN;
		perColor[3] = Color.BLUE;
		perColor[4] = Color.GREEN;
		perColor[5] = Color.YELLOW;
		perColor[6] = Color.GREEN;
		perColor[7] = Color.YELLOW;
		perColor[8] = Color.GREEN;
		perColor[9] = Color.RED;
		perColor[10] = Color.WHITE;
		perColor[11] = Color.CYAN;
		perColor[12] = Color.MAGENTA;//0xFF00FF
		perColor[13] = Color.YELLOW;
		perColor[14] = Color.GRAY;
		perColor[15] = Color.BLACK;
		for(int i = 0;i <16;i++){
			gPattern.setColor(perColor[i]);
			gPattern.fillRect(patternWidth*i/16,0,patternWidth*(i+1)/16-patternWidth*i/16,patternHeight);
		}
	}
	public void drawCircle(){
		iPattern = createImage(patternWidth,patternHeight);
		gPattern = iPattern.getGraphics();
		//
		gPattern.setColor(Color.BLACK);
		gPattern.fillRect(0,0,patternWidth,patternHeight);
		gPattern.setColor(Color.WHITE);
		gPattern.drawRect(0,0,patternWidth-1,patternHeight-1);
		gPattern.drawLine(0,patternHeight/2,patternWidth,patternHeight/2);
		gPattern.drawLine(patternWidth/2,0,patternWidth/2,patternHeight);
		//
		if(patternHeight<patternWidth)
			gPattern.drawOval(patternWidth/2 - patternHeight/2,0,patternHeight-1,patternHeight-1);
		else
			gPattern.drawOval(0,patternHeight/2 - patternWidth/2,patternWidth-1,patternWidth-1);
	}
	public void drawFlash(){
		iPattern = createImage(patternWidth,patternHeight);
		gPattern = iPattern.getGraphics();
		//
		final Color clr[] = new Color[8];
		clr[0] = Color.WHITE;
		clr[1] = Color.RED;
		clr[2] = Color.YELLOW;
		clr[3] = Color.GREEN;
		clr[4] = Color.CYAN;
		clr[5] = Color.BLUE;
		clr[6] = Color.MAGENTA;
		clr[7] = Color.BLACK;
		timerFlash = new Timer();
		timerFlash.schedule(new TimerTask(){
			@Override
			public void run(){
				if(flashi>7){
					flashi %= 8;
				}
				gPattern.setColor(clr[flashi++]);
				gPattern.fillRect(0,0,patternWidth,patternHeight);
				repaint();
			}
		},0,1*1000);
	}
	public void drawWord(String perWord,boolean enableB){
		iPattern = createImage(patternWidth,patternHeight);
		gPattern = iPattern.getGraphics();
		//
		if(enableB){
			gPattern.setColor(Color.BLACK);
			gPattern.fillRect(0,0,patternWidth,patternHeight);
			gPattern.setColor(Color.WHITE);
		}
		else{
			gPattern.setColor(Color.WHITE);
			gPattern.fillRect(0,0,patternWidth,patternHeight);
			gPattern.setColor(Color.BLACK);
		}
		int delta1 = 10+5*perWord.length();
		int delta2 = 20;
		for(int i = 0;i < patternWidth;i += delta1){
			for(int j = 0;j<patternHeight;j += delta2){
				gPattern.drawString(perWord,i,j);
			}
		}
	}
	//Shadow
	public void drawShadow(Color perColor1,Color perColor2){
		iPattern = createImage(patternWidth,patternHeight);
		gPattern = iPattern.getGraphics();
		//
		gPattern.setColor(perColor1);
		gPattern.fillRect(0,0,patternWidth,patternHeight);
		gPattern.setColor(perColor2);
		gPattern.fillRect(patternWidth/4,patternHeight/4,patternWidth/2,patternHeight/2);
	}
	public void drawPic(int imageN){
		iPattern = createImage(patternWidth,patternHeight);
		gPattern = iPattern.getGraphics();
		//
		imageN = imageN%6;
		final String iStr[] = {"OP1.bmp","OP2.bmp","OP3.bmp","OP4.bmp","OP5.bmp"};
		Image iPic=null;
		 try{
            iPic=ImageIO.read(new File(System.getProperty("user.dir")+"\\images\\"+iStr[imageN]));
        }catch(IOException ex){
        }
		//gPattern.drawImage(iPic,0,0,this);
		gPattern.drawImage(iPic,0,0,patternWidth,patternHeight,0,0,iPic.getWidth(this),iPic.getHeight(this),this);
		//System.out.println(iPic);
		//System.out.println(System.getProperty("user.dir"));  
		//System.out.println("支持写的图片格式:" +Arrays.toString(ImageIO.getWriterFormatNames()));
	}
	//others2
	public void drawScreen(int perXn,int perYn){
		iPattern = createImage(patternWidth,patternHeight);
		gPattern = iPattern.getGraphics();
		//
		gPattern.setColor(Color.BLACK);
		gPattern.fillRect(0,0,patternWidth,patternHeight);
		gPattern.setColor(Color.WHITE);
		//revise
		if(perXn>perYn){
			perYn = (perXn+9)/perXn*perYn;
			perXn = (perXn+9)/perXn*perXn;
		}
		else{
			perXn = (perYn+9)/perYn*perXn;
			perYn = (perYn+9)/perYn*perYn;
		}
		int x = 0,y=0,cx=0,cy=0;
		for(int i = 0;i < perXn;i += 2){
			for(int j = 0;j < perYn;j++){
				x = (i+(j%2))*patternWidth/perXn;
				y =j*patternHeight/perYn;
				cx = (i+(j%2)+1)*patternWidth/perXn-(i+(j%2))*patternWidth/perXn;
				cy = (j+1)*patternHeight/perYn-j*patternHeight/perYn;
				gPattern.fillRect(x,y,cx,cy);
			}
		}
	}
	//
	public void drawGeneral1(){
		iPattern = createImage(patternWidth,patternHeight);
		gPattern = iPattern.getGraphics();
		//
		int m_Width = patternWidth;
		int m_Height = patternHeight;
		gPattern.setColor(Color.BLACK);
		gPattern.fillRect(0,0,m_Width,m_Height);
	//
	int Lw=8;
	int Lh=8;
	int w1 = (m_Width-Lw)/16;
	int h1 = (m_Height-Lh)/12;
	gPattern.setColor(Color.WHITE);
	drawPixelOnOff(Color.WHITE,Color.BLACK,w1+8,h1+8,2*w1-16,2*h1-16);
	drawPixelOnOff(Color.WHITE,Color.BLACK,w1+8,m_Height-3*h1+8,2*w1-16,2*h1-16);
	drawPixelOnOff(Color.WHITE,Color.BLACK,m_Width-3*w1+8,h1+8,2*w1-16,2*h1-16);
	drawPixelOnOff(Color.WHITE,Color.BLACK,m_Width-3*w1+8,m_Height-3*h1+8,2*w1-16,2*h1-16);
	drawPixelOnOff(Color.WHITE,Color.BLACK,7*w1+8,5*h1+8,m_Width-14*w1-16,m_Height-10*h1-16);

	//linewidth = 2
	gPattern.drawLine(0,m_Height/2-1,m_Width,m_Height/2-1);
	gPattern.drawLine(0,m_Height/2,m_Width,m_Height/2);
	gPattern.drawLine(m_Width/2,0,m_Width/2,m_Height);
	gPattern.drawLine(m_Width/2-1,0,m_Width/2-1,m_Height);
	
	//
	
 	drawGriddingRect(0,0,8*w1,6*h1,9,7);
	drawGriddingRect(0,m_Height - 6*h1,8*w1,6*h1,9,7);
	drawGriddingRect(m_Width - 8*w1,0,8*w1,6*h1,9,7);
	drawGriddingRect(m_Width - 8*w1,m_Height - 6*h1,8*w1,6*h1,9,7);
	
	//Draw circle
 	int a = m_Width/2-2*w1;
 	int b = m_Height/2;
	gPattern.drawOval(2*w1,0,m_Width-4*w1,m_Height);
	gPattern.drawOval(0,0,4*w1,4*h1);
	gPattern.drawOval(0,m_Height-4*h1,4*w1,4*h1);
	gPattern.drawOval(m_Width-4*w1,0,4*w1,4*h1);
	gPattern.drawOval(m_Width-4*w1,m_Height-4*h1,4*w1,4*h1);
	gPattern.drawOval(6*w1,4*h1,m_Width-12*w1,m_Height-8*h1);
	//Draw Color Rect
	Color clr[] =new Color[8];
	clr[0] = Color.GRAY;
	clr[1] = Color.BLUE;
	clr[2] = Color.RED;
	clr[3] = Color.MAGENTA;
	clr[4] = Color.GREEN;
	clr[5] = Color.CYAN;
	clr[6] = Color.YELLOW;
	clr[7] = Color.WHITE;
	for(int i = 0;i <8;i++)
	{
		gPattern.setColor(clr[i]);
		gPattern.fillRect(m_Width/2 - w1*(-i+4),m_Height - h1*3+Lw/2,w1+1,h1-Lh+1);
	}	
	}
	public void drawGriddingRect(int x,int y,int cx,int cy,int m,int n){
		
		int x1,y1,x2,y2,i,j;
		if(m<2) m = 2;
		if(n<2) n = 2;
		gPattern.setColor(Color.WHITE);
		for(i = 0; i < n; i++){
			if(i < n-1)	{
				x1 = x;
				y1 = y + i * cy / (n - 1);
				x2 = x+cx-1;
				y2 = y1;
			}
			else{
				x1 = x;
				y1 = y+cy-1;
				x2 = x+cx-1;
				y2 = y1;
			}
			gPattern.drawLine(x1,y1,x2,y2);
		}
		//
		for(j = 0; j < m; j++){
			if(j < m-1){
				x1 = x + j * cx / (m - 1);
				y1 = y ;
				x2 = x1;
				y2 = y1+cy-1;
			}
			else{
				x1 = x+cx-1;
				y1 = y;
				x2 = x1;
				y2 = y+cy-1;
			}
			gPattern.drawLine(x1,y1,x2,y2);
		}
	}
	public void drawPixelOnOff(Color clr1,Color clr2,int x,int y,int cx,int cy){
		//iPattern = createImage(patternWidth,patternHeight);
		//gPattern = iPattern.getGraphics();
		Image tempImage = createImage(cx,cy);
		Graphics tempGraphics = tempImage.getGraphics();
		
		tempGraphics.setColor(clr1);
		tempGraphics.fillRect(0,0,cx,cy);
		tempGraphics.setColor(clr2);
		for(int i = 1;i < cx+cy; i += 2){
			tempGraphics.drawLine(i,0,0,i);
		}
		//
		//if(iPattern != null)
			//tempGraphics.drawImage(tempImage,0,0,this);
		gPattern.drawImage(tempImage,x,y,this);
	}
	//
	public void drawGeneral5(){
		iPattern = createImage(patternWidth,patternHeight);
		gPattern = iPattern.getGraphics();
		//
		int w,h;
		w=patternWidth;
		h=patternHeight;
	
	//Draw background
	gPattern.setColor(Color.GRAY);
	gPattern.fillRect(0,0,patternWidth,patternHeight);
	
	gPattern.setColor(Color.WHITE);
	for(int i = 0;i<5;i++){
		int x = patternWidth*1/100+i;
		int y = patternHeight*1/100+i;
		int cx = patternWidth - x*2;
		int cy = patternHeight - y*2;
		gPattern.drawRect(x,y,cx,cy);
	}
	gPattern.drawLine(-1,patternHeight/64,patternWidth,patternHeight/64);
	gPattern.drawLine(-1,patternHeight/64+1,patternWidth,patternHeight/64+1);
	//
	gPattern.drawLine(-1,patternHeight-patternHeight/64,patternWidth,patternHeight-patternHeight/64);
	gPattern.drawLine(-1,patternHeight-patternHeight/64+1,patternWidth,patternHeight-patternHeight/64+1);
	//
	for(int i = 1; i<10; i++){
		int y = (patternHeight*1/64)+i*(patternHeight-((patternHeight*1/64)*2))/10;
		gPattern.drawLine(-1,y,patternWidth,y);
		gPattern.drawLine(-1,y+1,patternWidth,y+1);
	}
	//
	for(int i = 1; i<14; i++){
		int x = i*patternWidth/14;
		gPattern.drawLine(x,0,x,patternHeight);
		gPattern.drawLine(x+1,0,x+1,patternHeight);
	}
	//
	//Big Black Rectangle
	for(int i=0;i<4;i++){
	        int x=0,y=0,cx=0,cy=0;
			switch(i)
	        {case 0:
	                x=0+2*(w/14)+4;
	                y=(h*1/64)+2*(h-((h*1/64)*2))/10+h*1/64;
	                cx=w-2*(w/14)-4 - x+1;
	                cy=(h*1/64)+3*(h-((h*1/64)*2))/10-h*1/64- y+1;
					gPattern.setColor(Color.BLACK);
					gPattern.fillRect(x,y,cx,cy);
	                break;
	        case 1:
	                x=0+2*(w/14)+4;
	                y=h-3*(h-((h*1/64)*2))/10;
	                cx=w-2*(w/14)-4 - x+1;
	                cy=h-2*(h-((h*1/64)*2))/10-(h*1/64)*2-y+1;
					gPattern.setColor(Color.WHITE);
					gPattern.fillRect(x,y,cx,cy);
	                break;
	        case 2:
	                x=0+4*(w/14)+(w/14)/4;
	                y=(h*1/64)+2*(h-((h*1/64)*2))/10+2*(h*1/64);
	                cx=w-4*(w/14)-(w/14/4)-x+1;
	                cy=(h*1/64)+3*(h-((h*1/64)*2))/10-2*(h*1/64)-y+1;
	                gPattern.setColor(Color.WHITE);
					gPattern.fillRect(x,y,cx,cy);
	                break;
	        case 3:
	                x=0+4*(w/14)+w/14/4;
	                y=h-3*(h-((h*1/64)*2))/10+(h*1/64);
	                cx=w-4*(w/14)-(w/14/4)-x+1;
	                cy=h-2*(h-((h*1/64)*2))/10-(h*1/64)*3-y+1;
	                gPattern.setColor(Color.BLACK);
					gPattern.fillRect(x,y,cx,cy);
	        break;
	        }
	}

	//Top Color
	for(int i=0;i<6;i++){
                //GetClientRect();   
                int x=4*(w/14)+5+i*(w/14);
				int y=(h*1/64)+1*(h-((h*1/64)*2))/10+2;
                int cx=5*(w/14)+5+i*(w/14)-x+1;
                int cy=(h*1/64)+2*(h-((h*1/64)*2))/10-2-y+1;
                switch(i){
				case 0:
					gPattern.setColor(new Color(0,0,160));
					break;
                case 1:
                    gPattern.setColor(new Color(160,0,0));
                    break;
                case 2:
                    gPattern.setColor(new Color(160,0,160));
                    break;
                case 3:
                    gPattern.setColor(new Color(0,160,0));
                    break;
                case 4:
                    gPattern.setColor(new Color(0,160,160));
                    break;
                case 5:
                    gPattern.setColor(new Color(160,160,0));
					break;
                }
				gPattern.fillRect(x,y,cx,cy);
         }
	//Bottom Color
	for(int i=0;i<6;i++){                               
                int x=4*(w/14)+5+i*(w/14);
				int y=h-2*(h-((h*1/64)*2))/10-(h*1/64);
                int cx=5*(w/14)+5+i*(w/14)-x+1;
                int cy=h-1*(h-((h*1/64)*2))/10-(h*1/64)-2-y+1;
                switch(i)
                {case 0:
                        gPattern.setColor(new Color(0,0,255));
                        break;
                case 1:
                        gPattern.setColor(new Color(255,0,0));
                        break;
                case 2:
                        gPattern.setColor(new Color(255,0,255));
                        break;
                case 3:
                        gPattern.setColor(new Color(0,255,0));
                        break;
                case 4:
                        gPattern.setColor(new Color(0,255,255));
                        break;
                case 5:
                        gPattern.setColor(new Color(255,255,0));
                break;
                }
				gPattern.fillRect(x,y,cx,cy);
         }
	//Center Color
	for(int i=0;i<2;i++){
                int y=h-4*(h-((h*1/64)*2))/10-(h*1/64);
                int cy=h-3*(h-((h*1/64)*2))/10-(h*1/64)-2-y+1;
				int x=0,cx=0;
                switch(i)
                {case 0:
	                x=0+4*(w/14)+10+i*4*(w/14);
	                cx=0+6*(w/14)+5+i*4*(w/14)-x+1;
					gPattern.setColor(new Color(0,0,0));
                    break;
                case 1:
	                x=0+4*(w/14)+5+i*4*(w/14);
	                cx=0+6*(w/14)-5+i*4*(w/14)-x+1;
                    gPattern.setColor(new Color(255,255,255));
                    break;
                }
				gPattern.fillRect(x,y,cx,cy);
        }
	//left-Dark Gray
	for(int i=0;i<3;i++){ 
	        int x=0+4*(w/14)+10;
			int y=(h*1/64)+3*(h-((h*1/64)*2))/10+1+i*(h-((h*1/64)*2))/10;
	        int cx=0+5*(w/14)+5-x+1;
            int cy=(h*1/64)+4*(h-((h*1/64)*2))/10+i*(h-((h*1/64)*2))/10-y+1;
                switch(i)
                {
                 case 0:
                        gPattern.setColor(new Color(76,76,76));
                        break;
                 case 1:
                        gPattern.setColor(new Color(51,51,51));
                         break;
                 case 2:
                        gPattern.setColor(new Color(25,25,25));
                        break;
                }
			gPattern.fillRect(x,y,cx,cy);
          }
	//right-Light Gray
	for(int i=0;i<3;i++){
	        int x=w-5*(w/14)-5;
			int y=(h*1/64)+3*(h-((h*1/64)*2))/10+1+i*(h-((h*1/64)*2))/10;
	        int cx=0+10*(w/14)-5-x+1;
	        int cy=(h*1/64)+4*(h-((h*1/64)*2))/10+i*(h-((h*1/64)*2))/10-y+1;
                switch(i)
                {
                 case 0:
                        gPattern.setColor(new Color(179,179,179));
                         break;
                 case 1:
                       gPattern.setColor(new Color(204,204,204));
                        break;
                 case 2:
                        gPattern.setColor(new Color(230,230,230));
                        break;
                }
				gPattern.fillRect(x,y,cx,cy);
          }
	//center top color
         for(int i=0;i<4;i++){               
                int x=0+5*(w/14)+5+i*(w/14);
				int y=(h*1/64)+3*(h-((h*1/64)*2))/10+1;
				int cx=0;
                if(i==3)
					cx=0+6*(w/14)+1+i*(w/14)-x+1;
                else
                {
					cx=0+6*(w/14)+5+i*(w/14)-x+1;
                }
				int cy=(h*1/64)+4*(h-((h*1/64)*2))/10-1-y+1;
                switch(i)
                {
                 case 0:
                 gPattern.setColor(new Color(102,102,102));
                 break;
                 case 1:
                 gPattern.setColor(new Color(128,128,128));
                 break;
                 case 2:
                 gPattern.setColor(new Color(128,128,128));
                 break;
                 case 3:
                 gPattern.setColor(new Color(153,153,153));
                 break;
                }
				gPattern.fillRect(x,y,cx,cy);
         }
	//center small color
        for(int i=0;i<2;i++){
	        int x=0+5*(w/14)+w/14/5+i*3*(w/14);
			int y=h-4*(h-((h*1/64)*2))/10;
	        int cx=0+6*(w/14)-w/14/5+i*3*(w/14)-x+1;
	        int cy=h-3*(h-((h*1/64)*2))/10-(h*1/64)*3-y+1;
                switch(i)
                {
                case 0:
                        gPattern.setColor(new Color(13,13,13));
                        break;
                case 1:
                        gPattern.setColor(new Color(243,243,243));
                        break;
                }
				gPattern.fillRect(x,y,cx,cy);
        }
	
	////////////////////////////// line   ///////////////////////////////////

        int flag1=1;
        int nx = w/14+3;
        int n = (((h-(h*1/32))/5)-8)/3;
        int ny_buffer1=0,ny_buffer2=0,ny1=0,ny2=0;
        int second_line=h*67/320;

        for(int i=0;i<n;i++)
	{
            gPattern.setColor(new Color(255*flag1,255*flag1,255*flag1));
			int x1 =nx;
			int y1 =(h*1/64)+i*1+3;
			int x2= 2*nx-(w/14/2)+8;
			int y2 = (h*1/64)+i*1+3;
	        gPattern.drawLine(x1,y1,x2,y2); //left-top
			
			x1 = nx;
			y1 = (h*63/64)-i*1-3;
			x2 = (2*nx-(w/14/2)+8);
			y2 = (h*63/64)-i*1-3;
            gPattern.drawLine(x1,y1,x2,y2); //left-bottom
			
			x1 = w-nx;
			y1 = (h*1/64)+i*1+3;
			x2 = w-(2*nx-(w/14/2)+8);
			y2 = (h*1/64)+i*1+3;
            gPattern.drawLine(x1,y1,x2,y2);//right-top
			
			x1 = w-nx;
			y1 = (h*63/64)-i*1-3;
			x2 = w-(2*nx-(w/14/2)+8);
			y2 = (h*63/64)-i*1-3;
            gPattern.drawLine(x1,y1,x2,y2);//right-bottom

                if(1 == flag1)
					flag1 = 0;
				else if(0 == flag1)
					flag1 = 1;				
                ny_buffer1=(h*1/64)+i+3;
                ny_buffer2=(h*63/64)-i-2;
	}
                ny1=ny_buffer1+1;
                ny2=ny_buffer2-1;

        for(int i=0;i<=w/11/2;i++)
	{
                gPattern.setColor(new Color(255*flag1,255*flag1,255*flag1));
				int x1 = 2*nx-(w/14/2)+8+i;
				int y1 = h*1/64+3;
				int x2 = 2*nx-(w/14/2)+8+i;
				int y2 = ny1;
               gPattern.drawLine(x1,y1,x2,y2);//left-top
				
				x1 = w-2*nx+(w/14/2)-8-i;
				y1 = h*1/64+3;
				x2 = w-2*nx+(w/14/2)-8-i;
				y2 = ny1;
                gPattern.drawLine(x1,y1,x2,y2); //right-top             
				
				x1 = 2*nx-(w/14/2)+8+i;
				y1 = h*63/64-3;
				x2 = 2*nx-(w/14/2)+8+i;
				y2 = ny2;
                gPattern.drawLine(x1,y1,x2,y2);//left-bottom
				
				x1 = w-2*nx+(w/14/2)-8-i;
				y1 = h*63/64-3;
				x2 = w-2*nx+(w/14/2)-8-i;
				y2 = ny2;
				gPattern.drawLine(x1,y1,x2,y2);//right-bottom

                if(1 == flag1)
					flag1 = 0;
				else if(0 == flag1)
					flag1 = 1;	
	}

/////////////////////////////////////////////////////////
        for(int i=0;i<n/2;i++)
	{
			//2 linewidth =2;
			gPattern.setColor(new Color(255*flag1,255*flag1,255*flag1));
			
                gPattern.drawLine(nx,ny1+i*2,(2*nx-(w/14/2)+7),ny1+i*2); //left-top
				gPattern.drawLine(nx+1,ny1+i*2,(2*nx-(w/14/2)+7)+1,ny1+i*2);

                gPattern.drawLine(nx,ny2-i*2,(2*nx-(w/14/2)+7),ny2-i*2);  //left-bottom
				gPattern.drawLine(nx+1,ny2-i*2,(2*nx-(w/14/2)+7)+1,ny2-i*2); 

                 gPattern.drawLine(w-nx,ny1+i*2,w-(2*nx-(w/14/2)+7),ny1+i*2);  //right-top
				 gPattern.drawLine(w-nx+1,ny1+i*2,w-(2*nx-(w/14/2)+7)+1,ny1+i*2);

                 gPattern.drawLine(w-nx,ny2-i*2,w-(2*nx-(w/14/2)+7),ny2-i*2); //right-bottom
				 gPattern.drawLine(w-nx+1,ny2-i*2,w-(2*nx-(w/14/2)+7)+1,ny2-i*2);

                ny_buffer1=ny1+i*2+2;
                ny_buffer2=ny2-i*2-3;
                if(1 == flag1)
					flag1 = 0;
				else if(0 == flag1)
					flag1 = 1;	
	}

          for(int i=0;i<(w/11/4+1);i++)
 	{
               //2 linewidth =2;
			gPattern.setColor(new Color(255*flag1,255*flag1,255*flag1));

               gPattern.drawLine(2*nx-(w/28)+8+i*2,ny1,2*nx-(w/28)+8+i*2,ny_buffer1); //left-top
			   gPattern.drawLine(2*nx-(w/28)+8+i*2+1,ny1,2*nx-(w/28)+8+i*2+1,ny_buffer1);

                gPattern.drawLine(w-2*nx+(w/28)-7-i*2,ny1,w-2*nx+(w/28)-7-i*2,ny_buffer1); //right-top
				gPattern.drawLine(w-2*nx+(w/28)-7-i*2+1,ny1,w-2*nx+(w/28)-7-i*2+1,ny_buffer1);
             

               gPattern.drawLine(2*nx-(w/28)+8+i*2,ny2,2*nx-(w/28)+8+i*2,ny_buffer2);  //left-bottom
			   gPattern.drawLine(2*nx-(w/28)+8+i*2+1,ny2,2*nx-(w/28)+8+i*2+1,ny_buffer2); 

                gPattern.drawLine(w-2*nx+(w/28)-7-i*2,ny2,w-2*nx+(w/28)-7-i*2,ny_buffer2);  //right-bottom
				gPattern.drawLine(w-2*nx+(w/28)-7-i*2+1,ny2,w-2*nx+(w/28)-7-i*2+1,ny_buffer2);

                if(1 == flag1)
					flag1 = 0;
				else if(0 == flag1)
					flag1 = 1;	
	}
                ny1=ny_buffer1;
                ny2=ny_buffer2;

/////////////////////////////////////////////////////////////
        for(int i=0;i<n;i++)
	{
			//linewidth =3

                gPattern.setColor(new Color(255*flag1,255*flag1,255*flag1));

                if (  (second_line+ny_buffer2-h)<3 )      break;
               gPattern.drawLine(nx,ny2-i*3,(2*nx-(w/28)+6),ny2-i*3);// left-bottom
			   gPattern.drawLine(nx,ny2-i*3+1,(2*nx-(w/28)+6),ny2-i*3+1);
			   gPattern.drawLine(nx,ny2-i*3+2,(2*nx-(w/28)+6),ny2-i*3+2);

                gPattern.drawLine(w-nx,ny2-i*3,w-(2*nx-(w/28)+6),ny2-i*3); // right-bottom
				gPattern.drawLine(w-nx,ny2-i*3+1,w-(2*nx-(w/28)+6),ny2-i*3+1);
				gPattern.drawLine(w-nx,ny2-i*3+2,w-(2*nx-(w/28)+6),ny2-i*3+2);

                if (  (second_line-ny_buffer1)<3 )      break;
				gPattern.drawLine(nx,ny1+i*3,(2*nx-(w/28)+6),ny1+i*3);  //left-top      w/28=w/14/2
				gPattern.drawLine(nx,ny1+i*3+1,(2*nx-(w/28)+6),ny1+i*3+2); 
				gPattern.drawLine(nx,ny1+i*3+2,(2*nx-(w/28)+6),ny1+i*3+2); 

                gPattern.drawLine(w-nx,ny1+i*3,w-(2*nx-(w/28)+6),ny1+i*3);  //right-top      w/28=w/14/2
				gPattern.drawLine(w-nx,ny1+i*3+1,w-(2*nx-(w/28)+6),ny1+i*3+1);
				gPattern.drawLine(w-nx,ny1+i*3+2,w-(2*nx-(w/28)+6),ny1+i*3+2);

                ny_buffer1=ny1+i*3;
                ny_buffer2=ny2-i*3;
                if(1 == flag1)
					flag1 = 0;
				else if(0 == flag1)
					flag1 = 1;
	}
        for(int i=0;i<(w/11/6+1);i++)
	{
                gPattern.setColor(new Color(255*flag1,255*flag1,255*flag1));

	        gPattern.drawLine(2*nx-(w/14/2)+8+i*3,ny1,2*nx-(w/14/2)+8+i*3,ny_buffer1);//left-top
			gPattern.drawLine(2*nx-(w/14/2)+8+i*3+1,ny1,2*nx-(w/14/2)+8+i*3+1,ny_buffer1);
			gPattern.drawLine(2*nx-(w/14/2)+8+i*3+2,ny1,2*nx-(w/14/2)+8+i*3+2,ny_buffer1);

             gPattern.drawLine(w-2*nx+(w/14/2)-8-i*3,ny1,w-2*nx+(w/14/2)-8-i*3,ny_buffer1); //right-top
			 gPattern.drawLine(w-2*nx+(w/14/2)-8-i*3+1,ny1,w-2*nx+(w/14/2)-8-i*3+1,ny_buffer1);
			 gPattern.drawLine(w-2*nx+(w/14/2)-8-i*3+2,ny1,w-2*nx+(w/14/2)-8-i*3+2,ny_buffer1);

            gPattern.drawLine(2*nx-(w/14/2)+8+i*3,ny2,2*nx-(w/14/2)+8+i*3,ny_buffer2);   //left-bottom
			gPattern.drawLine(2*nx-(w/14/2)+8+i*3+1,ny2,2*nx-(w/14/2)+8+i*3+1,ny_buffer2);
			gPattern.drawLine(2*nx-(w/14/2)+8+i*3+2,ny2,2*nx-(w/14/2)+8+i*3+2,ny_buffer2);

            gPattern.drawLine(w-2*nx+(w/14/2)-8-i*3,ny2,w-2*nx+(w/14/2)-8-i*3,ny_buffer2);  //right-bottom
			gPattern.drawLine(w-2*nx+(w/14/2)-8-i*3+1,ny2,w-2*nx+(w/14/2)-8-i*3+1,ny_buffer2);
			gPattern.drawLine(w-2*nx+(w/14/2)-8-i*3+1,ny2,w-2*nx+(w/14/2)-8-i*3+2,ny_buffer2);
            if(1 == flag1)
					flag1 = 0;
			else if(0 == flag1)
					flag1 = 1;
	}

/////////////////////////////  line end  /////////////////////////////////

///////////////////////////////////////////////////center line
        int everyw=h*31/320;
        flag1=1;
        int n1=w/28;
       // int sixline=h/64+6*everyw;
        for(int i=0;i<n;i++)
        {
                gPattern.setColor(new Color(255*flag1,255*flag1,255*flag1));
                gPattern.drawLine(7*w/14+4,h/64+4*everyw+3+i,7*w/14+n1+13,h/64+4*everyw+3+i);
                ny1=h/64+4*everyw+3+i;
	        if(1 == flag1)
					flag1 = 0;
			else if(0 == flag1)
					flag1 = 1;
        }
          ny_buffer1=ny1+2;
          flag1=1;
        for(int i=0;i<n1+6;i++)
        {
                gPattern.setColor(new Color(255*flag1,255*flag1,255*flag1));
				gPattern.drawLine(6*w/14+n1+i,h/64+4*everyw+3,6*w/14+n1+i,ny_buffer1);
                 if(1 == flag1)
					flag1 = 0;
			else if(0 == flag1)
					flag1 = 1;
        }
        flag1=0;
        for(int i=0;i<n/2;i++)
        {
                //linewidth =2
				gPattern.setColor(new Color(255*flag1,255*flag1,255*flag1));
	       gPattern.drawLine(7*w/14+4,ny_buffer1+i*2,7*w/14+n1+12,ny_buffer1+i*2);
		   gPattern.drawLine(7*w/14+4,ny_buffer1+i*2+1,7*w/14+n1+12,ny_buffer1+i*2+1);
                ny1=ny_buffer1+i*2;
	        if(1 == flag1)
					flag1 = 0;
			else if(0 == flag1)
					flag1 = 1;
        }
//
         for(int i=0;i<(n1/2+4);i++)
        {
	        //linewidth =2
			gPattern.setColor(new Color(255*flag1,255*flag1,255*flag1));
                gPattern.drawLine(6*w/14+n1+i*2,ny_buffer1,6*w/14+n1+i*2,ny1);
				gPattern.drawLine(6*w/14+n1+i*2+1,ny_buffer1,6*w/14+n1+i*2+1,ny1);
				gPattern.drawLine(6*w/14+n1+i*2+2,ny_buffer1,6*w/14+n1+i*2+2,ny1);
                if(1 == flag1)
					flag1 = 0;
			else if(0 == flag1)
					flag1 = 1;
        }
                ny_buffer1=ny1+2;
        for(int i=0;i<n/3+2;i++)
        {
            //linewidth =3
			gPattern.setColor(new Color(255*flag1,255*flag1,255*flag1));
               // if((sixline-ny_buffer1)<3) break;
	        gPattern.drawLine(7*w/14+4,ny_buffer1+i*3,7*w/14+n1+11,ny_buffer1+i*3);
			gPattern.drawLine(7*w/14+4,ny_buffer1+i*3+1,7*w/14+n1+11,ny_buffer1+i*3+1);
			gPattern.drawLine(7*w/14+4,ny_buffer1+i*3+2,7*w/14+n1+11,ny_buffer1+i*3+2);
                ny1=ny_buffer1+i*3 ;
                if(1 == flag1)
					flag1 = 0;
			else if(0 == flag1)
					flag1 = 1;
        }
          flag1=1;
         for(int i=0;i<n1/3+2;i++)
        {
	        //linewidth =3
			gPattern.setColor(new Color(255*flag1,255*flag1,255*flag1));
	        gPattern.drawLine(6*w/14+n1+1+i*3,ny_buffer1-2,6*w/14+n1+1+i*3,ny1);
			gPattern.drawLine(6*w/14+n1+1+i*3+1,ny_buffer1-2,6*w/14+n1+1+i*3+1,ny1);
			gPattern.drawLine(6*w/14+n1+1+i*3+2,ny_buffer1-2,6*w/14+n1+1+i*3+2,ny1);
                if(1 == flag1)
					flag1 = 0;
			else if(0 == flag1)
					flag1 = 1;
        }
	}
	//others3
	public void drawCrossTalk(){
		iPattern = createImage(patternWidth,patternHeight);
		gPattern = iPattern.getGraphics();
		//
		gPattern.setColor(Color.BLACK);
		gPattern.fillRect(0,0,patternWidth,patternHeight);
		gPattern.setColor(Color.WHITE);
		gPattern.drawRect(0,0,patternWidth-1,patternHeight-1);
		
		int m_Width = patternWidth;
		int m_Height = patternHeight;
		int a = m_Width/12;
		int b = m_Height/12;
		int delta = Math.abs(a-b);
		double theta =3.14/4;

		drawEllipseCenter(m_Width/2,delta+b,a,b);
		drawCrossTalkCenter(m_Width/2,delta+b,a,b,theta);
		drawLineCenter(m_Width/2,delta+b,a,b);

		drawEllipseCenter(delta+a,m_Height/2,a,b);
		drawCrossTalkCenter(delta+a,m_Height/2,a,b,theta);
		drawLineCenter(delta+a,m_Height/2,a,b);

		drawEllipseCenter(m_Width/2,m_Height-(delta+b),a,b);
		drawCrossTalkCenter(m_Width/2,m_Height-(delta+b),a,b,theta);
		drawLineCenter(m_Width/2,m_Height-(delta+b),a,b);

		drawEllipseCenter(m_Width-(delta+a),m_Height/2,a,b);
		drawCrossTalkCenter(m_Width-(delta+a),m_Height/2,a,b,theta);
		drawLineCenter(m_Width-(delta+a),m_Height/2,a,b);
	}
	public void drawEllipseCenter(int x,int y,int a,int b){
		gPattern.setColor(Color.WHITE);
		gPattern.drawOval(x-a,y-b,2*a,2*b);
	}
	public void drawCrossTalkCenter(int x,int y,int a,int b,double theta){
		 int cx = (int) Math.floor(2*a*Math.cos(theta));
		 int cy = (int) Math.floor(2*b*Math.sin(theta));
		 x = x - cx/2;
		 y = y - cy/2;
		gPattern.setColor(Color.BLACK);
		gPattern.fillRect(x,y,cx+1,cy+1);
		gPattern.setColor(Color.WHITE);
		int x1=0,y1=0;
		for(int i = 0;i < cx ;i+=2){	
			for(int j = 0;j < cy;j+=2){
				x1 = x+i;
				if(0 == j%4){
					x1 += 1;
				}
				y1 =y+j;
				gPattern.drawLine(x1,y1,x1,y1);
			}
		}
	}
	public void drawLineCenter(int x,int y,int a,int b){
		gPattern.setColor(Color.WHITE);
		gPattern.drawLine(x-a,y,x+a+1,y);
		gPattern.drawLine(x,y-b,x,y+b+1);
	}
	
	public void drawMoire(int type){
		type = type%3;
		iPattern = createImage(patternWidth,patternHeight);
		gPattern = iPattern.getGraphics();
		gPattern.setColor(Color.BLACK);
		gPattern.fillRect(0,0,patternWidth,patternHeight);
		gPattern.setColor(Color.WHITE);
		int i =0,j=0,x=0,y=0;
		switch(type){
			case 1:{
				for(i = 0;i < patternWidth ;i+=2){	
					for(j = 0;j < patternHeight;j+=2){
						x = i;
						if(0 == j%4){
							x += 1;
						}
						y =j;
						gPattern.drawLine(x,y,x,y);
					}
				}break;
			}
			case 2:{
				for(i = 0;i < patternWidth ;i+=2){	
					for(j = 0;j < patternHeight;j+=2 ){
						x = i;
						if(2 == j%4){
							x += 1;
						}
						y = j;
						gPattern.drawLine(x,y,x,y);
					}
				}
			}
			default:{
			}
		}
	}
	public void drawPattern2(){
		iPattern = createImage(patternWidth,patternHeight);
		gPattern = iPattern.getGraphics();
		//
		enablePattern2 = true;
		//
		gPattern.setColor(Color.WHITE);
		gPattern.fillRect(0,0,patternWidth,patternHeight);
		gPattern.setColor(Color.BLACK);
		for(int i = 0;i<4;i++){
			gPattern.drawLine(patternWidth/2-2+i,0,patternWidth/2-2+i,patternHeight-1);
			gPattern.drawLine(0,patternHeight/2-2+i,patternWidth-1,patternHeight/2-2+i);
		}
		
	}
	public void drawColorValue(){
		colorValueFlag = true;
		iPattern = createImage(patternWidth,patternHeight);
		gPattern = iPattern.getGraphics();
		Image tempImage = null;
		try{
			tempImage = new Robot().createScreenCapture(new Rectangle(patternWidth,patternHeight));
		}catch (Exception e) {
		}
		if(tempImage != null){
			gPattern.drawImage(tempImage,0,0,patternWidth,patternHeight,0,0,tempImage.getWidth(this),tempImage.getHeight(this),this);
		}
	}
	public void drawAdjustPattern(String str){
		iPattern = createImage(patternWidth,patternHeight);
		gPattern = iPattern.getGraphics();
		gPattern.drawString(str,patternWidth/2,patternHeight/2);
	}
}