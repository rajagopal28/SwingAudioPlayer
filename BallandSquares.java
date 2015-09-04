import java.io.*;
import java.awt.event.*;
import java.applet.*;
import java.awt.*;
/*<applet code=BallandSquares.class width=500 height=500>
</applet>*/
class Square
{
	private int side;
	private int xPosition,yPosition;
	private boolean isColorable;

	public Square(int s, int x,int y)
	{
		side = s;
		xPosition = x;
		yPosition = y;
		isColorable = true;
	}
	public Square()
	{
		side = Applet.WIDTH;
		xPosition = 0;
		yPosition = 0;
		isColorable = false;
	}
	public void setX(int sX)
	{
		xPosition = sX;
	}
	public void setY(int sY)
	{
		yPosition = sY;
	}
	public int getX()
	{
		return xPosition;
	}
	public int getY()
	{
		return yPosition;
	}
	public int getSide()
	{
		return side;
	}
	public void draw(Graphics g)
	{
		g.drawRect(xPosition, yPosition, side,side);
		if (isColorable)
		{
			g.fillRect(xPosition,yPosition, side, side);
		}
	}
}
public class BallandSquares extends Applet implements KeyListener, MouseListener
{
	private Square bigSquare;
	private Square smallSquares[];
	private int ballX, ballY;
	private int count;
	private int HEIGHT;
	private int WIDTH;
	public void init()
	{
		setForeground(Color.BLUE);
		setBackground(Color.WHITE);
		ballX = 500 / 2;
		ballY = 500 / 2;
		HEIGHT = 500;
		WIDTH = 500;
		bigSquare = new Square();
		count = (int)(Math.random() * 10);
		smallSquares = new Square[count];
		smallSquares[0] = new Square(50,50,50);
		for (int i = 1; i < count; i++)
		{
			int tx, ty;
			tx = (int)((Math.random()+1)*500);
			while (tx > WIDTH)
			{
				tx = (int)((Math.random() + 1) * 500);
			}
			ty = (int)((Math.random()+1) * 500);
			while (ty > HEIGHT)
			{
				ty = (int)((Math.random() + 1) * 500);
			}
			smallSquares[i] = new Square(50,tx,ty);
		}
	}
	public void start()
	{
		addMouseListener(this);
		addKeyListener(this);
	}
	public void stop()
	{
	}
	public void paint(Graphics g)
	{
		bigSquare.draw(g);
		for (int i = 0; i < count;i++ )
		{
			smallSquares[i].draw(g);
			showStatus("Inside For Loop");
			System.out.println("InsideFor");
		}
		g.drawOval(ballX, ballY, 50, 50);
		g.fillOval(ballX, ballY, 50, 50);
	}
	public void destroy()
	{
	}
	private int getCollidingSquareIndex(int bX,int bY,Square[] sqrs)
	{
		int target = -1;
		for (int i = 0; i < sqrs.length; i++)
		{
			if (((sqrs[i].getX() + sqrs[i].getSide()) > bX) || ((sqrs[i].getX() < bX)))
			{
				System.out.println("HelloWorld");
				target = i;
				break;
			}
			if ((sqrs[i].getY() + sqrs[i].getSide() > bY) || (sqrs[i].getY() < bY))
			{
				target = i;
				break;
			}
		}
		return target;
	}

	private boolean isColiding(int bX, int bY, Square[] sqrs)
	{
		boolean coliding = false;
		for (int i = 0; i < sqrs.length ;i++ )
		{
			if (((sqrs[i].getX() + sqrs[i].getSide()) > bX) && ((sqrs[i].getX() > bX)))
			{
				coliding = true;
				break;
			}
			if ((sqrs[i].getY() + sqrs[i].getSide() > bY) && (sqrs[i].getY() < bY))
			{
				coliding = true;
				break;
			}
		}
		return coliding;
	}
	public void keyPressed(KeyEvent ke)
	{
		int key = ke.getKeyCode();
		switch(key)
		{
			case KeyEvent.VK_UP:
				if (isColiding(ballX, ballY - 50, smallSquares))
				{
					// move the ball with colliding square
					System.out.println("UP Colliding");
					int i = getCollidingSquareIndex(ballX, ballY - 50, smallSquares);
					if (smallSquares[i].getY() - 50 >= 0 && i >= 0)
					{
						smallSquares[i].setY(smallSquares[i].getY() - 50);
					}
					
				}
					if (ballY-50 >= 0)
					{
						ballY -= 50;
					}
					System.out.println("UP not Colliding");
				repaint();
				break;
			case KeyEvent.VK_DOWN:
				if (isColiding(ballX, ballY + 50, smallSquares))
				{
					// move the ball with colliding square
					int i = getCollidingSquareIndex(ballX, ballY + 50, smallSquares);
					if (smallSquares[i].getY() + 50 <= HEIGHT && i >= 0)
					{
						smallSquares[i].setY(smallSquares[i].getY() + 50);
					}
				}
					if(ballY+50<=HEIGHT)
					{
						ballY += 50;
					}
				repaint();
				break;
			case KeyEvent.VK_RIGHT:
				if(isColiding(ballX + 50,ballY,smallSquares))
				{
					// move the ball with colliding square
					int i = getCollidingSquareIndex(ballX + 50, ballY, smallSquares);
					if (smallSquares[i].getX() + 50 <= WIDTH && i >= 0)
					{
						smallSquares[i].setX(smallSquares[i].getX() + 50);
					}
				}
					if (ballX + 50 <= WIDTH)
					{
						ballX += 50;
					}
				repaint();
				break;
			case KeyEvent.VK_LEFT:
				if (isColiding(ballX - 50, ballY, smallSquares))
				{
					//move the ball with colliding square
					int i = getCollidingSquareIndex(ballX - 50, ballY, smallSquares);
					if (smallSquares[i].getX() - 50 >= 0 && i >= 0)
					{
						smallSquares[i].setX(smallSquares[i].getX() - 50);
					}
				}
					if (ballX - 50 >= 0)
					{
						ballX -= 50;
					}
				repaint();
				break;
		}
	}
	public void keyTyped(KeyEvent ke)
	{
		showStatus("Key Typed");
	}
	public void keyReleased(KeyEvent ke)
	{
		showStatus("Key Released");
	}
	public void mouseClicked(MouseEvent me)
	{
		showStatus("Mouse Clicked");
	}
	public void mousePressed(MouseEvent me)
	{
		showStatus("Mouse Pressed");
	}
	public void mouseReleased(MouseEvent me)
	{
		showStatus("Mouse Released");
	}
	public void mouseEntered(MouseEvent me)
	{
		showStatus("Mouse Entered");
	}
	public void mouseExited(MouseEvent me)
	{
		showStatus("Mouse Exited");
	}
	public void mouseDragged(MouseEvent me)
	{
		showStatus("Mouse Dragged");
	}

}