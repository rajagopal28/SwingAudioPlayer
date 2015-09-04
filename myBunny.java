/*<applet code=myBunny.java width="100" height="100">
</applet>*/
import java.io.*;
import java.awt.*;
import java.applet.*;
import java.awt.event.*;
class Bunny
{
 private int headX,headY,headLength,headBreadth;
 private int SIZE=1,defaultX=10,defaultY=20;
 private int ear1X,ear1Y,ear2X,ear2Y,earLength,earBreadth;
 private int eye1X,eye1Y,eye2X,eye2Y,eyeDia;
 private int hand1X,hand1Y,hand2X,hand2Y,handLength,handBreadth;
 private int mouthX,mouthY,mouthDia;
 private int bodyX,bodyY,bodyLength,bodyBreadth;
 private int leg1X,leg1Y,leg2X,leg2Y,legLength,legBreadth;

 public Bunny(int down)
 {
	ear1X=10;
	ear1Y=ear2Y=0;
	ear2X=17;
	earLength=3;
	earBreadth=8;

	headX=10;
	headY=8;
	headLength=10;
	headBreadth=10;

	eye1X=0;
	eye1Y=0;
	eye2X=0;
	eye2Y=0;
	eyeDia=0;

	mouthX=0;
	mouthY=0;
	mouthDia=0;

	bodyX=10;
	bodyY=16;
	bodyLength=10;
	bodyBreadth=14;

	hand1X=2;
	hand1Y=20;
	hand2X=18;
	hand2Y=20;
	handLength=8;
	handBreadth=3;

	leg1X=10;
	leg1Y=24;
	leg2X=17;
	leg2Y=24;
	legLength=3;
	legBreadth=8;

 }
 public Bunny(int right,int right1)
 {
	ear1X=10;
	ear1Y=ear2Y=0;
	ear2X=10;
	earLength=3;
	earBreadth=8;

	headX=10;
	headY=8;
	headLength=6;
	headBreadth=10;

	eye1X=12;
	eye1Y=10;
	eye2X=12;
	eye2Y=10;
	eyeDia=0;

	mouthX=13;
	mouthY=12;
	mouthDia=0;

	bodyX=12;
	bodyY=16;
	bodyLength=6;
	bodyBreadth=14;

	hand1X=14;
	hand1Y=20;
	hand2X=14;
	hand2Y=20;
	handLength=8;
	handBreadth=3;

	leg1X=12;
	leg1Y=24;
	leg2X=12;
	leg2Y=24;
	legLength=3;
	legBreadth=8;
 }
 public Bunny(String left)
 {
	ear1X=12;
	ear1Y=ear2Y=0;
	ear2X=12;
	earLength=3;
	earBreadth=8;

	headX=10;
	headY=8;
	headLength=6;
	headBreadth=10;

	eye1X=12;
	eye1Y=10;
	eye2X=12;
	eye2Y=10;
	eyeDia=0;

	mouthX=13;
	mouthY=12;
	mouthDia=0;

	bodyX=10;
	bodyY=16;
	bodyLength=6;
	bodyBreadth=14;

	hand1X=2;
	hand1Y=20;
	hand2X=4;
	hand2Y=20;
	handLength=8;
	handBreadth=3;

	leg1X=12;
	leg1Y=25;
	leg2X=12;
	leg2Y=25;
	legLength=3;
	legBreadth=8;
 }
 public Bunny()
 {
	ear1X=10;
	ear1Y=ear2Y=0;
	ear2X=17;
	earLength=3;
	earBreadth=8;

	headX=10;
	headY=8;
	headLength=10;
	headBreadth=10;

	eye1X=12;
	eye1Y=10;
	eye2X=16;
	eye2Y=10;
	eyeDia=2;

	mouthX=13;
	mouthY=12;
	mouthDia=4;

	bodyX=10;
	bodyY=16;
	bodyLength=10;
	bodyBreadth=14;

	hand1X=2;
	hand1Y=20;
	hand2X=18;
	hand2Y=20;
	handLength=8;
	handBreadth=3;

	leg1X=10;
	leg1Y=24;
	leg2X=17;
	leg2Y=24;
	legLength=3;
	legBreadth=8;

 }
 public void drawBunny(Graphics g)
 {
	g.drawOval(ear1X,ear1Y,earLength,earBreadth);
	g.drawOval(ear2X,ear2Y,earLength,earBreadth);

	g.drawOval(headX,headY,headLength,headBreadth);

	g.drawOval(eye1X,eye1Y,eyeDia,eyeDia);
	g.drawOval(eye2X,eye2Y,eyeDia,eyeDia);

	g.drawOval(mouthX,mouthY,mouthDia,mouthDia);

	g.drawOval(hand1X,hand1Y,handLength,handBreadth);
	g.drawOval(hand2X,hand2Y,handLength,handBreadth);

	g.drawOval(bodyX,bodyY,bodyLength,bodyBreadth);

	g.drawOval(leg1X,leg1Y,legLength,legBreadth);
	g.drawOval(leg2X,leg2Y,legLength,legBreadth);
 }
}
public class myBunny extends Applet implements KeyListener
{

 private Bunny curBunny,rightBunny,leftBunny,frontBunny,backBunny;
 public void init()
 {
	frontBunny=new Bunny();//front
	backBunny=new Bunny(1);//back
	leftBunny=new Bunny("left");
	rightBunny=new Bunny(1,1);//right

	curBunny=frontBunny;
	
	setForeground(Color.blue);
 }
 public void paint(Graphics g)
 {
	curBunny.drawBunny(g);
 }
 public void start()
 {
	addKeyListener(this);
 }
 public void keyPressed(KeyEvent ke)
 {
	int key=ke.getKeyCode();
	switch(key)
	{
	 case KeyEvent.VK_UP:
	 {
		curBunny=frontBunny;
		break;
	 }
	 case KeyEvent.VK_DOWN:
	 {
		curBunny=backBunny;
		break;
	 }
	 case KeyEvent.VK_RIGHT:
	 {
		curBunny=rightBunny;
		break;
	 }
	 case KeyEvent.VK_LEFT:
	 {
		curBunny=leftBunny;
		break;
	 }
	 default:
	 {
		showStatus("Some other Key Pressed");
		break;
	 }
	}// switch
 	 repaint();
 }
 public void keyReleased(KeyEvent ke)
 {
	curBunny=frontBunny;
	repaint();
 }
 public void keyTyped(KeyEvent ke)
 {
	showStatus("Key Typing Not Allowed");
 }

}