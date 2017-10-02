import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import java.applet.*;
import java.util.Properties;

import java.io.File;
/**
@File: DisplayTest.java
@Date: Friday, 24 February, 2017
@author: Xiao Shang
@For Display Test
*/
public class DisplayTest extends JFrame
		implements ActionListener,AdjustmentListener{
	//Define Frame
	//Main Panel
	JPanel jpDisplayTest = new JPanel();

	//Final srting
	final String strDisplayTest         = "Display Test @Xiao";// Main Frame Title
	//*Grayscale, *Pixel On/Off, GrayStep
	final String strGrayscale           = "Grayscale";
	final String strWhite               = "White";
	final String strRed                 = "Red";
	final String strGreen               = "Green";
	final String strBlue                = "Blue";
	final String strRGBW                = "RGBW";
	//
	final String strEnableVer           = "Ver.";
	final String strEnableReverse       = "Reverse";
	final String strEnableHalf          = "1/2Gray";
	final String strStep                = "Step";
	//
	final String strPixelOnOff          = "Pixel On/Off";
	final String strShade               = "Shade";
	//
	final String strHorLine             = "Hor. Line";
	final String strGrayStep            = "Gray Step";
	final String strEO                  = "E_O";
	
	//
	final String strAutoWhite           = "A. White";  // Auto White
	final String strAutoColor           = "A. Color";  // Auto Color
	final String strAutoOnOff           = "A. On/Off"; // Auto Pixel On/Off
	final String strAutoAll             = "A. All";    // Auto All
	final String strAutoShadow          = "A. Shadow"; // Auto Shadow
	//
	final String[] strOthers            = { "Others", "Others2", "Others3" };
	final String[] strShadow            = { "Shadow", "Shw.1", "Shw.2", "Shw.3", "Shw.4", "Shw.5" };
	final String strPosition            = "Position";
	final String strColorBar            = "Clr. Bar";
	final String strCircle              = "Circle";
	final String strFlash               = "Flash";
	final String strWord                = "W";
	final String strWordH               = "H";
	final String strEnableB             = "W/B.";
	final String strEnablePic           = "Pic.";
	//
	final String[] strRatioScreen       = { "16:9 Scr.", "4:3 Scr." };
	final String[] strGeneral           = { "General1", "General5"};
	final String strDotOnOff            = "Dot On/Off";
	final String strCrossTalk           = "CrossTalk";
	final String[] strMorie             = { "Morie1", "Moire2"};
	final String strPattern             = "Pattern2";
	final String strColorValue          = "Clr. Val.";
	//
	final String strScreenRes           = "Screen Resolution ";
	final String strWordX               = "X";
	//
	final String[] petStrings           = { "16", "32", "64", "128", "256" };
	//
	final String strNo255               = "255";
	//
	final String strSmallShow           = "Small Show";
	
	/*#01 Grayscale-------------------------------------------------------------------------------*/

	//#01 -- constant
	final int ENABLEVER                 = 0x0001; // Hor./Ver.
	final int ENABLEREVERSE             = 0x0002; // Reverse
	final int ENABLEHALF                = 0x0004; // 1/2Gray
	public static int ENABLERGBW        = 0x0008; // GRBW
	
    //#01 -- Main Panel
	JPanel  jpGrayscale                 = new JPanel();
	
	//#01 -- Button(White,Red,Green,Blue,RGBW)
	JButton jbGrayscaleWhite            = new JButton(strWhite);
	JButton jbGrayscaleRed              = new JButton(strRed);
	JButton jbGrayscaleGreen            = new JButton(strGreen);
	JButton jbGrayscaleBlue             = new JButton(strBlue);
	JButton jbGrayscaleRGBW             = new JButton(strRGBW);
	//#01-- CheckBox(Hor./Ver.),Reverse,(1/2Gray)
	JCheckBox jcbGrayscaleEnableVer     = new JCheckBox(strEnableVer);
	JCheckBox jcbGrayscaleEnableReverse = new JCheckBox(strEnableReverse);
	JCheckBox jcbGrayscaleEnableHalf    = new JCheckBox(strEnableHalf);
	//#01 -- Step(Label,ComboBox)
	int GrayscaleFlag = 0;
	JLabel jlGrayscaleStep              = new JLabel(strStep);
	//String[] petStrings                 = { "16", "32", "64", "128", "256" };
	JComboBox<String> jcbGrayscaleStep  = new JComboBox<String>(petStrings);
	
	/*#02 PixelOn/Off-----------------------------------------------------------------------------*/
	//#02 -- Main Panel
	JPanel jpPixelOnOff                 = new JPanel();
	//#02 -- Button(White,Red,Green,Blue)
	JButton jbPixelOnOffWhite           = new JButton(strWhite);
	JButton jbPixelOnOffRed             = new JButton(strRed);
	JButton jbPixelOnOffGreen           = new JButton(strGreen);
	JButton jbPixelOnOffBlue            = new JButton(strBlue);
	//#02 -- Shade(Label,Text,ScrollBar)
	int PixelOnOffShade                 = 255;
	JLabel jlPixelOnOffShade            = new JLabel(strShade,JLabel.CENTER);
	JTextField jtfPixelOnOffShade       = new JTextField(strNo255);
	JScrollBar jsbPixelOnOffShade       = new JScrollBar(JScrollBar.VERTICAL,255,0,0,255);
	
	/*#03 Hor.Line--------------------------------------------------------------------------------*/
	//#03 -- Main Hor.Line
	JPanel jpHorLine                    = new JPanel();
	//#03 -- Button(1B 1W, 2B 2W, 4B 4W, 8B 8W)
	JButton jbHorLine[]                 = new JButton[4];
	//#03 -- CheckBox(Ver.)
	boolean EnableVerFlag               = false;
	JCheckBox jcbHorLineEnableVer       = new JCheckBox(strEnableVer);
	
	/*#04 Gray Step-------------------------------------------------------------------------------*/
	//#04 -- Main Panel
	JPanel jpGrayStep                   = new JPanel();
	//#04 -- Button(White, Red, Green, Blue, E_O)
	JButton jbGrayStepWhite             = new JButton(strWhite);
	JButton jbGrayStepRed               = new JButton(strRed);
	JButton jbGrayStepGreen             = new JButton(strGreen);
	JButton jbGrayStepBlue              = new JButton(strBlue);
	JButton jbGrayStepEO                = new JButton(strEO);
	//#04 -- Shade(Label,Text,ScrollBar)
	int GrayStepShade = 255;
	JLabel jlGrayStepShade              = new JLabel(strShade,JLabel.CENTER);
	JTextField jtfGrayStepShade         = new JTextField(strNo255);
	JScrollBar jsbGrayStepShade         = new JScrollBar(JScrollBar.VERTICAL,255,0,0,255);
	//#04 -- Button(Auto -- <5>)	
	JButton jbGrayStepAWhite            = new JButton(strAutoWhite);  // Auto White
	JButton jbGrayStepAColor            = new JButton(strAutoColor);  // Auto Color
	JButton jbGrayStepAOnOff            = new JButton(strAutoOnOff); // Auto Pixel On/Off
	JButton jbGrayStepAAll              = new JButton(strAutoAll);    // Auto All
	JButton jbGrayStepAShadow           = new JButton(strAutoShadow); // Auto Shadow

	/*#05 others----------------------------------------------------------------------------------*/
	//#05 -- Main Panel
	JPanel jpOther1 = new JPanel();
	//#05 -- Button	
	JButton jbPosition                  = new JButton(strPosition);
	JButton jbColorBar                  = new JButton(strColorBar);
	JButton jbCircle                    = new JButton(strCircle);
	JButton jbFlash                     = new JButton(strFlash);
	JButton jbWord                      = new JButton(strWord);
	//#05 -- Text(Word)
	String Word                         = new String(strWordH);
	JTextField jtfWord                  = new JTextField(Word,3);//new JTextField("H",3);
	//#05 -- CheckBox(W/B.)
	boolean EnableBFlag                 = false;
	JCheckBox jcbWordEnableB            = new JCheckBox(strEnableB);
	
	/*#06 Shadow----------------------------------------------------------------------------------*/
	//#06 Main Panel
	JPanel jpShadow                     = new JPanel();
	//#06 -- Button(<4>)
	JButton jbShadow[]                  = new JButton[5];
	//#06 -- CheckBox(Pic)
	boolean EnablePicFlag               = false;
	JCheckBox jcbShadowEnablePic        = new JCheckBox(strEnablePic);
	
	/*#07 others2---------------------------------------------------------------------------------*/
	//#07 Main Panel
	JPanel jpOther2                     = new JPanel();
	//#07 Button(<5>)	
	JButton jbScreen1                   = new JButton(strRatioScreen[0]);
	JButton jbScreen2                   = new JButton(strRatioScreen[1]);
	JButton jbGeneral1                  = new JButton(strGeneral[0]);
	JButton jbGeneral5                  = new JButton(strGeneral[1]);
	JButton jbDotOnOff                  = new JButton(strDotOnOff);
	
	/*#08 others3---------------------------------------------------------------------------------*/
	//#08 Main Panel
	JPanel jpOther3                     = new JPanel();
	//#08 Button(<5>)
	JButton jbCrossTalk                 = new JButton(strCrossTalk);
	JButton jbMoire1                    = new JButton(strMorie[0]);
	JButton jbMoire2                    = new JButton(strMorie[1]);
	JButton jbPattern2                  = new JButton(strPattern);
	JButton jbColorValue                = new JButton(strColorValue);
	
	/*#09 Screen Resolution-----------------------------------------------------------------------*/
	int screenWidth                     = Toolkit.getDefaultToolkit().getScreenSize().width;
	int screenHeight                    = Toolkit.getDefaultToolkit().getScreenSize().height;
	String strSR                        = strScreenRes+ String.valueOf(screenWidth)
	                                      +strWordX+String.valueOf(screenHeight);
	JLabel jlScreenResolution           = new JLabel(strSR);
	//JLabel jlScreenResolution = new JLabel("Screen Resolution"+" "+
		//String.valueOf(screenWidth)+"x"+String.valueOf(screenHeight));
	/*#10 Small Show------------------------------------------------------------------------------*/
	JPanel  jpSmallShow                 = new JPanel();
	static boolean EnableSmallShowFlag  = false;
	JCheckBox jcbSmallShow              = new JCheckBox(strSmallShow);
	//JButton jbSmallShow                 = new JButton(strSmallShow);
	
	//Main Frame class
	public DisplayTest(){
		/*Main Frame title------------------------------------------------------------------------*/
		//setTitle("Display Test@Xiao");
		setTitle(strDisplayTest);
		jpDisplayTest.setLayout(null);
		
		//Rectangle rectDisplayTest = new Rectangle(0,0,640,480);
		/*#01 Grayscale---------------------------------------------------------------------------*/
		jpGrayscale.setBorder(BorderFactory.createTitledBorder(strGrayscale));		
		jpGrayscale.setBounds(10,10,195,220);
		jpDisplayTest.add(jpGrayscale);
		jpGrayscale.setLayout(null);
		//setBounds
		jbGrayscaleWhite.setBounds(10, 20,70,30);
		jbGrayscaleRed.setBounds  (10, 60,70,30);
		jbGrayscaleGreen.setBounds(10,100,70,30);
		jbGrayscaleBlue.setBounds (10,140,70,30);
		jbGrayscaleRGBW.setBounds (10,180,70,30);
		//
		jcbGrayscaleEnableVer.setBounds(100,20,80,30);
		jcbGrayscaleEnableReverse.setBounds(100,60,80,30);
		jcbGrayscaleEnableHalf.setBounds(100,100,80,30);
		//
		jlGrayscaleStep.setBounds(100,140,30,30);
		jcbGrayscaleStep.setBounds(130,140,50,30);
		//
		//add
		jpGrayscale.add(jbGrayscaleWhite);
		jpGrayscale.add(jbGrayscaleGreen);
		jpGrayscale.add(jbGrayscaleRed);
		jpGrayscale.add(jbGrayscaleBlue);
		jpGrayscale.add(jbGrayscaleRGBW);
		//
		jpGrayscale.add(jcbGrayscaleEnableVer);
		jpGrayscale.add(jcbGrayscaleEnableReverse);
		jpGrayscale.add(jcbGrayscaleEnableHalf);
		//
		jpGrayscale.add(jlGrayscaleStep);
		jpGrayscale.add(jcbGrayscaleStep);
		//		
		//addActionListener
		jbGrayscaleWhite.addActionListener(this);
		jbGrayscaleRed.addActionListener(this);
		jbGrayscaleGreen.addActionListener(this);
		jbGrayscaleBlue.addActionListener(this);
		jbGrayscaleRGBW.addActionListener(this);
		//
		jcbGrayscaleEnableVer.addActionListener(this);
		jcbGrayscaleEnableReverse.addActionListener(this);
		jcbGrayscaleEnableHalf.addActionListener(this);
		//
		jcbGrayscaleStep.addActionListener(this);

		/*#02 PixelOn/Off-------------------------------------------------------------------------*/
		jpPixelOnOff.setBorder(BorderFactory.createTitledBorder(strPixelOnOff));
		jpPixelOnOff.setBounds(210,10,120,220);
		jpDisplayTest.add(jpPixelOnOff);
		jpPixelOnOff.setLayout(null);
		
		jbPixelOnOffWhite.setBounds(10,20,70,30);
		jbPixelOnOffRed.setBounds(10,60,70,30);
		jbPixelOnOffGreen.setBounds(10,100,70,30);
		jbPixelOnOffBlue.setBounds(10,140,70,30);
		//
		jlPixelOnOffShade.setBounds(10,180,60,30);
		jtfPixelOnOffShade.setBounds(80,180,30,30);
		//
		jsbPixelOnOffShade.setBounds(90,20,20,150);
		//
		jpPixelOnOff.add(jbPixelOnOffWhite);
		jpPixelOnOff.add(jbPixelOnOffRed);
		jpPixelOnOff.add(jbPixelOnOffGreen);
		jpPixelOnOff.add(jbPixelOnOffBlue);
		jpPixelOnOff.add(jlPixelOnOffShade);
		jpPixelOnOff.add(jtfPixelOnOffShade);
		jpPixelOnOff.add(jsbPixelOnOffShade);
		//
		jbPixelOnOffWhite.addActionListener(this);
		jbPixelOnOffRed.addActionListener(this);
		jbPixelOnOffGreen.addActionListener(this);
		jbPixelOnOffBlue.addActionListener(this);
		jsbPixelOnOffShade.addAdjustmentListener(this);
		//
		//#03 Hor.Line----------------------------------------------------------------------------*/
		jpHorLine.setBorder(BorderFactory.createTitledBorder(strHorLine));
		jpHorLine.setBounds(335,10,115,220);
		jpDisplayTest.add(jpHorLine);
		jpHorLine.setLayout(null);
		for(int i=0;i<4;i++){
			String perString = String.valueOf(1<<i);
			jbHorLine[i] = new JButton(perString+"B"+" "+perString+strWord);
			jbHorLine[i].setBounds(10,20+40*i,95,30);
			jpHorLine.add(jbHorLine[i]);
			jbHorLine[i].addActionListener(this);
		}
		jcbHorLineEnableVer.setBounds(10,180,95,30);
		jpHorLine.add(jcbHorLineEnableVer);
		jcbHorLineEnableVer.addActionListener(this);
		
		//#04 Gray Step----------------------------------------------------------------------------*/
		jpGrayStep.setBorder(BorderFactory.createTitledBorder(strGrayStep));
		jpGrayStep.setBounds(455,10,120,450);
		jpDisplayTest.add(jpGrayStep);
		jpGrayStep.setLayout(null);
		//
		jbGrayStepWhite.setBounds(10,20,70,30);
		jbGrayStepRed.setBounds(10,60,70,30);
		jbGrayStepGreen.setBounds(10,100,70,30);
		jbGrayStepBlue.setBounds(10,140,70,30);
		jbGrayStepEO.setBounds(10,180,70,30);
		//
		jlGrayStepShade.setBounds(10,215,60,30);
		jtfGrayStepShade.setBounds(80,215,30,30);
		//
		jsbGrayStepShade.setBounds(90,20,20,150);
		//
		jbGrayStepAWhite.setBounds(10,250,100,30);
		jbGrayStepAColor.setBounds(10,290,100,30);
		jbGrayStepAOnOff.setBounds(10,330,100,30);
		jbGrayStepAAll.setBounds(10,370,100,30);
		jbGrayStepAShadow.setBounds(10,410,100,30);
		//add
		jpGrayStep.add(jbGrayStepWhite);
		jpGrayStep.add(jbGrayStepRed);
		jpGrayStep.add(jbGrayStepGreen);
		jpGrayStep.add(jbGrayStepBlue);
		jpGrayStep.add(jbGrayStepEO);
		jpGrayStep.add(jlGrayStepShade);
		jpGrayStep.add(jtfGrayStepShade);
		jpGrayStep.add(jsbGrayStepShade);
		jpGrayStep.add(jbGrayStepAWhite);
		jpGrayStep.add(jbGrayStepAColor);
		jpGrayStep.add(jbGrayStepAOnOff);
		jpGrayStep.add(jbGrayStepAAll);
		jpGrayStep.add(jbGrayStepAShadow);
		//addListener
		jbGrayStepWhite.addActionListener(this);
		jbGrayStepRed.addActionListener(this);
		jbGrayStepGreen.addActionListener(this);
		jbGrayStepBlue.addActionListener(this);
		jbGrayStepEO.addActionListener(this);
		jtfGrayStepShade.addActionListener(this);
		
		jsbGrayStepShade.addAdjustmentListener(this);
		
		jbGrayStepAWhite.addActionListener(this);
		jbGrayStepAColor.addActionListener(this);
		jbGrayStepAOnOff.addActionListener(this);
		jbGrayStepAAll.addActionListener(this);
		jbGrayStepAShadow.addActionListener(this);
		//#05 others------------------------------------------------------------------------------*/
		jpOther1.setBorder(BorderFactory.createTitledBorder(strOthers[0]));		
		jpOther1.setBounds(10,240,100,260);
		jpDisplayTest.add(jpOther1);
		jpOther1.setLayout(null);
		//setBounds
		jbPosition.setBounds(10,20,80,30);
		jbColorBar.setBounds(10,60,80,30);
		jbCircle.setBounds(10,100,80,30);
		jbFlash.setBounds(10,140,80,30);
		jbWord.setBounds(10,180,50,30);
		jtfWord.setBounds(65,180,25,30);
		jcbWordEnableB.setBounds(10,220,80,30);
		//add
		jpOther1.add(jbPosition);
		jpOther1.add(jbColorBar);
		jpOther1.add(jbCircle);
		jpOther1.add(jbFlash);
		jpOther1.add(jbWord);
		jpOther1.add(jtfWord);
		jpOther1.add(jcbWordEnableB);
		//addListener
		jbPosition.addActionListener(this);
		jbColorBar.addActionListener(this);
		jbCircle.addActionListener(this);
		jbFlash.addActionListener(this);
		jbWord.addActionListener(this);
		jtfWord.addActionListener(this);
		jcbWordEnableB.addActionListener(this);
		
		//#06 Shadow-------------------------------------------------------------------------------*/
		jpShadow.setBorder(BorderFactory.createTitledBorder(strShadow[0]));		
		jpShadow.setBounds(115,240,90,260);
		jpDisplayTest.add(jpShadow);
		jpShadow.setLayout(null);
		for(int i=0;i<5;i++){
			jbShadow[i] = new JButton("Shw."+String.valueOf(i+1));
			jbShadow[i].setBounds(10,20+40*i,70,30);
			jpShadow.add(jbShadow[i]);
			jbShadow[i].addActionListener(this);
		}
		jcbShadowEnablePic.setBounds(10,220,60,30);
		jpShadow.add(jcbShadowEnablePic);
		jcbShadowEnablePic.addActionListener(this);
		
		//#07 others2-----------------------------------------------------------------------------*/
		jpOther2.setBorder(BorderFactory.createTitledBorder(strOthers[1]));		
		jpOther2.setBounds(210,240,120,220);
		jpDisplayTest.add(jpOther2);
		jpOther2.setLayout(null);
		//setBounds
		jbScreen1.setBounds(10,20,100,30);
		jbScreen2.setBounds(10,60,100,30);
		jbGeneral1.setBounds(10,100,100,30);
		jbGeneral5.setBounds(10,140,100,30);
		jbDotOnOff.setBounds(10,180,100,30);
		//add
		jpOther2.add(jbScreen1);
		jpOther2.add(jbScreen2);
		jpOther2.add(jbGeneral1);
		jpOther2.add(jbGeneral5);
		jpOther2.add(jbDotOnOff);
		//addListener
		jbScreen1.addActionListener(this);
		jbScreen2.addActionListener(this);
		jbGeneral1.addActionListener(this);
		jbGeneral5.addActionListener(this);
		jbDotOnOff.addActionListener(this);
		
		//#08 others3-----------------------------------------------------------------------------*/
		jpOther3.setBorder(BorderFactory.createTitledBorder(strOthers[2]));		
		jpOther3.setBounds(335,240,115,220);
		jpDisplayTest.add(jpOther3);
		jpOther3.setLayout(null);
		//setBounds
		jbCrossTalk.setBounds(10,20,95,30);
		jbMoire1.setBounds(10,60,95,30);
		jbMoire2.setBounds(10,100,95,30);
		jbPattern2.setBounds(10,140,95,30);
		jbColorValue.setBounds(10,180,95,30);
		//add
		jpOther3.add(jbCrossTalk);
		jpOther3.add(jbMoire1);
		jpOther3.add(jbMoire2);
		jpOther3.add(jbPattern2);
		jpOther3.add(jbColorValue);
		//addListener
		jbCrossTalk.addActionListener(this);
		jbMoire1.addActionListener(this);
		jbMoire2.addActionListener(this);
		jbPattern2.addActionListener(this);
		jbColorValue.addActionListener(this);
		//#09 Screen Resolution------------------------------------------------------------------*/
		jlScreenResolution.setBounds(220,465,180,30);
		jpDisplayTest.add(jlScreenResolution);
		/*#10 Small Show---------------------------------------------------------------------------*/
		jpSmallShow.setBorder(BorderFactory.createLineBorder(Color.GREEN));//createTitledBorder(strSmallShow));		
		jpSmallShow.setBounds(460,465,102,32);
		jpDisplayTest.add(jpSmallShow);
		jpSmallShow.setLayout(null);
		//
		jcbSmallShow.setBounds(1,1,100,30);
		jpSmallShow.add(jcbSmallShow);
		jcbSmallShow.addActionListener(this);
		//
		add(jpDisplayTest);
		setBounds(300,250,600,540);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent ae){
		//Grayscale
		if(jbGrayscaleWhite == ae.getSource()){
			int stepValue = 16<<jcbGrayscaleStep.getSelectedIndex();
			new DrawPattern().drawGrayscale(new Color(255,255,255),stepValue,GrayscaleFlag);
		}
		else if(jbGrayscaleRed == ae.getSource()){
			int stepValue = 16<<jcbGrayscaleStep.getSelectedIndex();
			new DrawPattern().drawGrayscale(new Color(255,0,0),stepValue,GrayscaleFlag);
		}
		else if(jbGrayscaleGreen == ae.getSource()){
			int stepValue = 16<<jcbGrayscaleStep.getSelectedIndex();
			new DrawPattern().drawGrayscale(new Color(0,255,0),stepValue,GrayscaleFlag);
		}
		else if(jbGrayscaleBlue == ae.getSource()){
			int stepValue = 16<<jcbGrayscaleStep.getSelectedIndex();
			new DrawPattern().drawGrayscale(new Color(0,0,255),stepValue,GrayscaleFlag);
		}
		else if(jbGrayscaleRGBW == ae.getSource()){
			int stepValue = 16<<jcbGrayscaleStep.getSelectedIndex();
			new DrawPattern().drawGrayscale(new Color(255,255,255),stepValue,0x0008|GrayscaleFlag);
		}
		else if(jcbGrayscaleEnableVer == ae.getSource()){
			if(jcbGrayscaleEnableVer.isSelected())
				GrayscaleFlag |= ENABLEVER;
			else if(!jcbGrayscaleEnableVer.isSelected())
				GrayscaleFlag &= ~ENABLEVER;
		}
		else if(jcbGrayscaleEnableReverse == ae.getSource()){
			if(jcbGrayscaleEnableReverse.isSelected())
				GrayscaleFlag |= ENABLEREVERSE;
			else if(!jcbGrayscaleEnableReverse.isSelected())
				GrayscaleFlag &= ~ENABLEREVERSE;
		}
		else if(jcbGrayscaleEnableHalf == ae.getSource()){
			if(jcbGrayscaleEnableHalf.isSelected())
				GrayscaleFlag |= ENABLEHALF;
			else if(!jcbGrayscaleEnableHalf.isSelected())
				GrayscaleFlag &= ~ENABLEHALF;
		}
		//Pixel On/Off
		else if(jbPixelOnOffWhite == ae.getSource()){
			new DrawPattern().drawPixelOnOff(new Color(0,0,0),new Color(PixelOnOffShade,PixelOnOffShade,PixelOnOffShade));
		}
		else if(jbPixelOnOffRed == ae.getSource()){
			new DrawPattern().drawPixelOnOff(new Color(0,0,0),new Color(PixelOnOffShade,0,0));
		}
		else if(jbPixelOnOffGreen == ae.getSource()){
			new DrawPattern().drawPixelOnOff(new Color(0,0,0),new Color(0,PixelOnOffShade,0));
		}
		else if(jbPixelOnOffBlue == ae.getSource()){
			new DrawPattern().drawPixelOnOff(new Color(0,0,0),new Color(0,0,PixelOnOffShade));
		}
		//Hor.Line
		else if(jbHorLine[0] == ae.getSource()){
			new DrawPattern().drawHorLine(1,EnableVerFlag);
		}
		else if(jbHorLine[1] == ae.getSource()){
			new DrawPattern().drawHorLine(2,EnableVerFlag);
		}
		else if(jbHorLine[2] == ae.getSource()){
			new DrawPattern().drawHorLine(4,EnableVerFlag);
		}
		else if(jbHorLine[3] == ae.getSource()){
			new DrawPattern().drawHorLine(8,EnableVerFlag);
		}
		else if(jcbHorLineEnableVer == ae.getSource()){
			EnableVerFlag = jcbHorLineEnableVer.isSelected();
		}
		//Graystep
		else if(jbGrayStepWhite == ae.getSource()){
			new DrawPattern().drawGrayStep(new Color(GrayStepShade,GrayStepShade,GrayStepShade),false);
		}
		else if(jbGrayStepRed == ae.getSource()){
			new DrawPattern().drawGrayStep(new Color(GrayStepShade,0,0),false);
		}
		else if(jbGrayStepGreen == ae.getSource()){
			new DrawPattern().drawGrayStep(new Color(0,GrayStepShade,0),false);
		}
		else if(jbGrayStepBlue == ae.getSource()){
			new DrawPattern().drawGrayStep(new Color(0,0,GrayStepShade),false);
		}
		else if(jbGrayStepEO == ae.getSource()){
			new DrawPattern().drawGrayStep(new Color(GrayStepShade,GrayStepShade,GrayStepShade),true);
		}
		else if(jbGrayStepAWhite == ae.getSource()){
			new DrawPattern().drawAdjustPattern("Adjust White");
		}
		else if(jbGrayStepAColor == ae.getSource()){
			new DrawPattern().drawAdjustPattern("Adjust Color");
		}
		else if(jbGrayStepAOnOff == ae.getSource()){
			new DrawPattern().drawAdjustPattern("Adjust On/Off");
		}
		else if(jbGrayStepAAll == ae.getSource()){
		new DrawPattern().drawAdjustPattern("Adjust All");
		}
		else if(jbGrayStepAShadow == ae.getSource()){
			new DrawPattern().drawAdjustPattern("Adjust Shade");
		}
		//others1
		else if(jbPosition == ae.getSource()){
			new DrawPattern().drawPosition();
		}
		else if(jbColorBar== ae.getSource()){
			new DrawPattern().drawColorBar();
		}
		else if(jbCircle == ae.getSource()){
			new DrawPattern().drawCircle();
		}
		else if(jbFlash == ae.getSource()){
			new DrawPattern().drawFlash();
		}
		else if(jbWord == ae.getSource()){
			new DrawPattern().drawWord(Word,EnableBFlag);
		}
		else if(jtfWord == ae.getSource()){
			Word = jtfWord.getText();
		}
		else if(jcbWordEnableB == ae.getSource()){
			EnableBFlag = jcbWordEnableB.isSelected();
		}
		//Shadow
		else if(jbShadow[0] == ae.getSource()){
			if(EnablePicFlag)
				new DrawPattern().drawPic(0);
			else
				new DrawPattern().drawShadow(Color.WHITE,Color.BLACK);
		}
		else if(jbShadow[1] == ae.getSource()){
			if(EnablePicFlag)
				new DrawPattern().drawPic(1);
			else
				new DrawPattern().drawShadow(Color.BLACK,Color.WHITE);
		}
		else if(jbShadow[2] == ae.getSource()){
			if(EnablePicFlag)
				new DrawPattern().drawPic(2);
			else
				new DrawPattern().drawShadow(Color.GRAY,Color.BLACK);
		}
		else if(jbShadow[3] == ae.getSource()){
			if(EnablePicFlag)
				new DrawPattern().drawPic(3);
			else
				new DrawPattern().drawShadow(Color.GRAY,Color.WHITE);
		}
		else if(jbShadow[4] == ae.getSource()){
			if(EnablePicFlag)
				new DrawPattern().drawPic(4);
			else
				new DrawPattern().drawShadow(Color.GREEN,Color.RED);
		}
		else if(jcbShadowEnablePic == ae.getSource()){
			//new DrawPattern().drawPic();
			EnablePicFlag = jcbShadowEnablePic.isSelected();
		}
		//others2
		else if(jbScreen1 == ae.getSource()){
			new DrawPattern().drawScreen(16,9);
		}
		else if(jbScreen2 == ae.getSource()){
			new DrawPattern().drawScreen(4,3);
		}
		else if(jbGeneral1 == ae.getSource()){
			new DrawPattern().drawGeneral1();
		}
		else if(jbGeneral5 == ae.getSource()){
			new DrawPattern().drawGeneral5();
		}
		else if(jbDotOnOff == ae.getSource()){
			new DrawPattern().drawPixelOnOff(Color.MAGENTA,Color.GREEN);
		}
		//others3
		else if(jbCrossTalk == ae.getSource()){
			new DrawPattern().drawCrossTalk();
		}
		else if(jbMoire1 == ae.getSource()){
			new DrawPattern().drawMoire(1);
		}
		else if(jbMoire2 == ae.getSource()){
			new DrawPattern().drawMoire(2);
	
		}
		else if(jbPattern2 == ae.getSource()){
			new DrawPattern().drawPattern2();
		}
		else if(jbColorValue== ae.getSource()){
			new DrawPattern(0).drawColorValue();
		}
		//Small Show
		else if(jcbSmallShow == ae.getSource()){
			EnableSmallShowFlag = jcbSmallShow.isSelected();
		}
	}
	public void adjustmentValueChanged(AdjustmentEvent ae){
		repaint();
		if(jsbPixelOnOffShade == ae.getSource()){
			PixelOnOffShade = jsbPixelOnOffShade.getValue();
			jtfPixelOnOffShade.setText(String.valueOf(PixelOnOffShade));
		}
		else if(jsbGrayStepShade == ae.getSource()){
			GrayStepShade = jsbGrayStepShade.getValue();
			jtfGrayStepShade.setText(String.valueOf(GrayStepShade));
		}
	}
	public static void main(String[] arg){
		new DisplayTest();
	}
}