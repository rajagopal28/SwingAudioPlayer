import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.nio.*;
import java.nio.charset.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.*;
import javax.sound.sampled.*;
class Node
{
 public String name,url;
 public Node next;
 public Node(String nam,String link)
 {
	name=nam;
	url=link;
	next=null;
 }
 public void finalize()
 {
	next=null;
 }
};
class SinglyLinkedList
{
 private Node head;
 public SinglyLinkedList()
 {
	head=null;
 }
 public void flushList()
 {
	if(head!=null)
	{
	 Node p=head;
		while(p!=null)
		{
		 Node temp=p;
		 p=p.next;
		 temp.finalize();
		}
	}
 }
 public void printList()
 {
	Node p=head;
	while(p!=null)
	{
	 System.out.println("value="+p.url);
	 p=p.next;
	}
 }
 public void append(String nam,String path)
 {
	if(head==null)
	{
		head=new Node(nam,path);
	}
	else
	{
	 Node p=head;
		while(p.next!=null)
		{
			p=p.next;
		}
		if(p.next==null)
		{
			p.next=new Node(nam,path);
		}
	}
 }
public boolean isEmpty()
{
 return head==null;
}
public Node getHead()
{
 return this.head;
}
 public boolean isPresent(String path)
 {
	boolean present=false;
	Node p=head;
	while(p!=null && !present)
	{
		if(path.equalsIgnoreCase(p.url))
		{
			present=true;
			break;
		}
	 p=p.next;
	}
	return present;
 }// present

 public void remove(String path)
 {
	Node p=head,prev=null;
	while(p!=null)
	{
		if(p.url.equalsIgnoreCase(path))
		{
			Node temp=p;
			if(prev!=null)
			{
			 prev.next=p.next;
			}
			//free(temp);
			if(p==head)
			{
			 head=p.next;
			}
			temp.finalize();
			break;
		}
		prev=p;
		p=p.next;
	}
 }// remove
 public boolean isAnyPrev(String path)
 {
	Node p=head,prev=null;
	boolean present=false;
	while(p!=null)
	{
	 if(path.equalsIgnoreCase(p.url))
	 {
		present=(prev!=null);
	 }
	 prev=p;
	 p=p.next;
	}
 return present;
 }
 public String getPrev(String path)
 {
	Node p=head,prev=null;
	String myPrev=new String();
	boolean present=false;
	while(p!=null)
	{
	 if(path.equalsIgnoreCase(p.url))
	 {
		myPrev=prev.url;
		break;
	 }
	 prev=p;
	 p=p.next;
	}
 return myPrev;
 }
 public boolean isAnyNext(String path)
 {
	Node p=head,next=p.next;
	boolean present=false;
	while(next!=null)
	{
	 if(path.equalsIgnoreCase(p.url))
	 {
		present=(next!=null);
	 }
	 p=next;
	 next=p.next;
	}
 return present;
 }
 public String getNext(String path)
 {
	Node p=head,next=p.next;
	String myNext=new String();
	boolean present=false;
	while(next!=null)
	{
	 if(path.equalsIgnoreCase(p.url))
	 {
		myNext=(next.url);
		break;
	 }
	 p=next;
	 next=p.next;
	}
 return myNext;
 }
 public void swapUp(String path)
 {
	Node p=head;
	Node prev=null,next=p.next;
	if(p.url.equalsIgnoreCase(path))
	{
		System.out.println("cannot move up, its head");
		return;
	}
	while(next!=null)
	{
		if(next.url.equalsIgnoreCase(path))
		{
			if(prev!=null) // means p!=head
			{
				prev.next=next;
				p.next=next.next;
				next.next=p;
				System.out.println("Hello World!!!");
			}
			else if(p==head || prev==null)
			{
				System.out.println("The Problem area");
				p.next=next.next;
				next.next=p;
				head=next;
			}
			break;
		}
	 prev=p;
	 p=p.next;
	 next=p.next;
	}
 }// up
 public void swapDown(String path)
 {
	Node p=head;
	while(p!=null)
	{
		if(p.url.equalsIgnoreCase(path))
		{
			if(p.next!=null)
			{
				swapUp(p.next.url);
			}
		 break;
		}
	 p=p.next;
	}
 }//down
};// class dll
class SaveDialog extends JFrame implements ActionListener
{
 private JLabel label=new JLabel("Give Your File Name:",JLabel.LEFT);
 private JTextField name=new JTextField();
 private JButton save=new JButton("Save");
 private SinglyLinkedList list;
 Container c;
 public SaveDialog(SinglyLinkedList dll)
 {
	super("Save PlayList");
	list=dll;
	save.addActionListener(this);
	c=getContentPane();
	c.setLayout(new GridLayout(3,1));
	c.add(label,BorderLayout.NORTH);
	c.add(name);
	c.add(save,BorderLayout.SOUTH);
	pack();
	setSize(200,100);
	setVisible(true);
 }
 public void actionPerformed(ActionEvent ae)
 {
	if(ae.getSource()==save)
	{
		Node p=list.getHead();
		try
		{
		 FileOutputStream fout=new FileOutputStream(name.getText()+".spl");
		 DataOutputStream out=new DataOutputStream(fout);
		 while(p!=null)
		 {
			String line="file name:"+p.name+" url:"+p.url+"\n";
			out.write(line.getBytes());
		  p=p.next;
		 }// while
		 out.close();
		 fout.close();
		 JOptionPane jop=new JOptionPane();
		 jop.showMessageDialog(null,"Playlist Created in the Current Directory","Success",JOptionPane.INFORMATION_MESSAGE);
		}
		 catch(Exception e)
		 {
			System.out.println("Caught an exception in save class " + e.toString());
		 	JOptionPane jop=new JOptionPane();
		 	jop.showMessageDialog(null,"Playlist Creation Error","ERROR",JOptionPane.ERROR_MESSAGE);
		 }
	}
 }
};// class saved
class SongsListTable extends JFrame implements ActionListener
{
	DefaultTableModel dtm;
	JTableHeader header;
	JScrollPane pane;
	JTable t;
	SwingAudioPlayer myParent;
	static int rowCount;
	Container c;
	JPanel panel,panel2;

	public SinglyLinkedList myList;
	JButton add, delete,play,up,down,open,save;

	public SongsListTable(SwingAudioPlayer obj,SinglyLinkedList list)
	{
		super("PlayList");
		myParent=obj;
		myList=list;
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/playlistIcon.png")));
		rowCount=0;
		c = getContentPane();
		c.setLayout(new FlowLayout());
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		add = new JButton("Add");
		add.addActionListener(this);
		delete= new JButton("Delete");
		delete.addActionListener(this);
		up=new JButton("Move Up");
		up.addActionListener(this);
		down=new JButton("Down");
		down.addActionListener(this);
		play=new JButton("Play Selected");
		play.addActionListener(this);
		open=new JButton("Open PlayList");
		open.addActionListener(this);
		save=new JButton("Save PlayList");
		save.addActionListener(this);

		panel = new JPanel();
		panel.setLayout(new GridLayout());
		dtm = new DefaultTableModel(15,0);
		t = new JTable(dtm);
		t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		

		dtm.addColumn("Name");
		dtm.addColumn("Path");

		//add column at third position

		header = t.getTableHeader();

		//t.setLocation(5,5);
		header.setBackground(new Color(0, 75, 200));
		header.setForeground(new Color(255, 255, 255));

		pane = new JScrollPane(t);
		//panel.add(pane, BorderLayout.CENTER);
		panel.add(pane);
		//getContentPane().add(panel);

		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.add(add);
		panel2.add(delete);
		panel2.add(up);
		panel2.add(down);
		panel2.add(play);
		panel2.add(open);
		panel2.add(save);


		c.add(panel, BorderLayout.NORTH);
		c.add(panel2,BorderLayout.SOUTH);
		pack();
		setVisible(true);
		setSize(800, 500);
		if(!myList.isEmpty())
		{
			Node p=myList.getHead();
			while(p!=null)
			{
			 	String turl=p.url;
				String fName=p.name;
				System.out.println("file name="+fName+" row count ="+ rowCount);
				dtm.insertRow(rowCount++,new Object[]{fName,turl});
			    	p=p.next;
			}
		}
	}

	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == add)
		{
			String turl=new String();
			//add a song
			JFileChooser c = new JFileChooser();
			// Demonstrate "Open" dialog:

			c.setFileFilter(new FileFilter()
			{
				public boolean accept(File f)
				{
					return f.getName().toLowerCase().endsWith(".au") || f.getName().toLowerCase().endsWith(".wav") || f.getName().toLowerCase().endsWith(".mid") || f.isDirectory();
				}

				public String getDescription()
				{
					return "Music Files";
				}
			});

			int rVal = c.showOpenDialog(this);
			if (rVal == JFileChooser.APPROVE_OPTION)
			{
				turl=c.getSelectedFile().toString();
				System.out.println("File Selectde="+turl);
			}
			if (rVal == JFileChooser.CANCEL_OPTION)
			{
				turl="";
			}


			if (turl.length() != 0)
			{
				// good work
				String dirPath=c.getCurrentDirectory().toString();
				int ind=turl.indexOf(dirPath);
				if(ind>=0 && !myList.isPresent(turl))
				{
					//String fName=turl.substring(ind+dirPath.length()+1,turl.length()-1);
					String fName=c.getSelectedFile().getName();
					System.out.println("file name="+fName+" row count ="+ rowCount);
					dtm.insertRow(rowCount++,new Object[]{fName,turl});
					myList.append(fName,turl);
				}
				else
				{
					JOptionPane jop=new JOptionPane();
					jop.showMessageDialog(null,"File Already Exists","Cannot Add",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		if (ae.getSource() == delete)
		{
			//delete a song
			int index=t.getSelectedRow();
			System.out.println("SelectedRowIndex="+index);
			if(index>=0)
			{
			 String turl=t.getValueAt(index,1).toString();
			 dtm.removeRow(index);
			 myList.remove(turl);
			 rowCount--;
			}
			
		}// delete
		if(ae.getSource() == up)
		{
			int index =t.getSelectedRow();
			String turl=t.getValueAt(index,1).toString();
			if(index>0)
			{
				dtm.moveRow(index-1,index-1 , index);
				myList.swapUp(turl);
				myList.printList();
			}
		}//up
		if(ae.getSource() == down)
		{
			int index =t.getSelectedRow();
			String turl=t.getValueAt(index,1).toString();
			if(index+1<rowCount)
			{
				dtm.moveRow(index+1,index+1 , index);
				myList.swapDown(turl);
				myList.printList();
			}
		}//down
		if(ae.getSource()==play)
		{
		  if((t.getValueAt(t.getSelectedRow(),1).toString()).length()==0)
		  {
			System.out.println("problem here");
		  }
		  else
		  {
			myParent.url.setText(t.getValueAt(t.getSelectedRow(),1).toString());
			myParent.playAudio();
		  }
		}// play
		if(ae.getSource()==save)
		{
			SaveDialog sd=new SaveDialog(myList);
		}//save
		if(ae.getSource()==open)
		{
			String turl=new String();
			//open a playlist
			JFileChooser c = new JFileChooser();

			c.setFileFilter(new FileFilter()
			{
				public boolean accept(File f)
				{
					return f.getName().toLowerCase().endsWith(".spl") || f.isDirectory();
				}

				public String getDescription()
				{
					return "Swing Playlist File";
				}
			});

			int rVal = c.showOpenDialog(this);
			if (rVal == JFileChooser.APPROVE_OPTION)
			{
				turl=c.getSelectedFile().toString();
				System.out.println("File Selected="+turl);
			}
			if (rVal == JFileChooser.CANCEL_OPTION)
			{
				turl="";
			}


			if (turl.length() != 0)
			{
				// open the selected file as input stream
				try
				{
				 FileInputStream fin=new FileInputStream(turl);
				 DataInputStream in=new DataInputStream(fin);
				 byte[] input = new byte[100000];
				 myList.flushList();
				 String line;
				 while((line=in.readLine())!=null)
				 {
					System.out.println("InsideFile While,read line="+line);
					//line=line.trim();
					//file name:<file_name> url:<file_url>
					int ind1=line.indexOf("file name:");
					int ind2=line.indexOf("url:");
					System.out.println("ind1="+ ind1 + " ind2="+ind2);
					if(ind1>=0 && ind2>0)
					{
					 String fName=line.substring(ind1+"file name:".length(),ind2);
					 String path=line.substring(ind2+"url:".length(),line.length());
					 myList.append(fName,path);
					 dtm.insertRow(rowCount++,new Object[]{fName,path});
					 System.out.println("Inside if index");
					}
				 }//while
				 in.close();
				 fin.close();
				}
				catch(Exception e)
				{
					System.out.println("Got an Exception:"+e.toString());
				}
				
			}
		}
	}
	public void positionColumn(JTable table, int col_Index)
	{

		table.moveColumn(table.getColumnCount() - 1, col_Index);
	}
}
public class SwingAudioPlayer extends JFrame implements ActionListener
{
	public JTextField url = new JTextField();
	private JLabel label = new JLabel("File Selected::", JLabel.LEFT);
	private JPanel p1, p2,p3;

	private JComboBox txt=new JComboBox();

	private JMenuItem openItem;
	private JMenuItem exitItem;
	private JMenuItem help,about;

	private JButton browse = new JButton("Browse");

	AudioFormat audioFormat;
	AudioInputStream audioInputStream;
	SourceDataLine sourceDataLine;
	private PlayThread pt;

	static boolean stopPlayback = false, pausePlayback = false;

	private JButton stop = new JButton("Stop");
	private JButton pause = new JButton("Pause");
	private JButton play = new JButton("Play");
	private JButton playList = new JButton("PlayList");
	private JButton next = new JButton("Next");
	private JButton prev = new JButton("Previous");
	private JButton increaseVol=new JButton("+");
	private JButton decreaseVol=new JButton("-");
	private JLabel volume=new JLabel("Volume:",JLabel.CENTER);
	private DataLine.Info dataLineInfo;

	private SinglyLinkedList myPlayList;

	public SwingAudioPlayer()
	{
		super("Swing Audio Player");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/myIcon.png")));
		JMenuBar mbar = new JMenuBar();
		JMenu m = new JMenu("File");
		openItem = new JMenuItem("Open");
		openItem.addActionListener(this);
		m.add(openItem);
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(this);		
		addWindowListener(new WindowAdapter()
		{
			public void windowOpened(WindowEvent e)
			{
				//field1.requestFocus();
			}
			public void windowClosing(WindowEvent e)
			{
			
				setVisible(false);
				dispose();
			
			}
		}); 
		m.add(exitItem);
		mbar.add(m);

		JMenu h = new JMenu("Help");
		help=new JMenuItem("Help Text");
		about=new JMenuItem("About Application");
		h.add(help);
		h.add(about);
		help.addActionListener(this);
		about.addActionListener(this);
		mbar.add(h);

		stop.setEnabled(false);
		play.setEnabled(true);
		next.setEnabled(false);
		prev.setEnabled(false);

		play.addActionListener(this);
		stop.addActionListener(this);
		pause.addActionListener(this);
		next.addActionListener(this);
		prev.addActionListener(this);
		playList.addActionListener(this);
		increaseVol.addActionListener(this);
		decreaseVol.addActionListener(this);

		p1 = new JPanel();
		p2 = new JPanel();

		myPlayList=new SinglyLinkedList();

		Container cp = getContentPane();
		browse.addActionListener(this);
		cp.add(mbar, "North");
		cp.add(p1);
		cp.add(p2, "South");
		p1.setLayout(new GridLayout(2, 1));
		p2.setLayout(new GridLayout(2,5));
		p1.add(label);
		p1.add(url);


		p2.add(play);
		p2.add(pause);
		p2.add(stop);
		p2.add(next);
		p2.add(prev);
		p2.add(browse);
		p2.add(playList);
		p2.add(volume);
		p2.add(increaseVol);
		p2.add(decreaseVol);
		//cp.add(p, BorderLayout.CENTER);
	}
	public void setVolume(Line line,double volume)
	{

		FloatControl ctrl=null;

		try
		{
			ctrl=(FloatControl)(line.getControl(FloatControl.Type.MASTER_GAIN));
		}

		catch(IllegalArgumentException ex)
		{

			System.out.println("Both the control type not supported");
		}

		if(ctrl!=null)
		{
				float currentVol=ctrl.getValue();
				currentVol+=volume;
				if(currentVol>100.0f)
				{

					currentVol=100.0f;

				}

				else if(currentVol<0)

				{

					currentVol=0.0f;

				}

				ctrl.setValue(currentVol);

		}

	}
	public void playAudio()
	{
		if(!myPlayList.isEmpty())
		{
			next.setEnabled(myPlayList.isAnyNext(url.getText()));
			prev.setEnabled(myPlayList.isAnyPrev(url.getText()));
		}
		try
		{
			if(url.getText().length()==0 || url.getText().equalsIgnoreCase("You pressed cancel"))
			{
				JOptionPane jop=new JOptionPane();
				jop.showMessageDialog(null,"No Files Selected","Erro Playing",JOptionPane.ERROR_MESSAGE);
				play.setEnabled(true);
				stop.setEnabled(false);
				return;
			}
			stop.setEnabled(true);
			File soundFile = new File(url.getText());
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			audioFormat = audioInputStream.getFormat();
			System.out.println(audioFormat);

			dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);

			sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLineInfo);

			pt=new PlayThread();
			pt.start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}

	}//end playAudio
	private void stopAudio()
	{
		pt.stopper();
		sourceDataLine.stop();
		sourceDataLine.drain();
		sourceDataLine.close();
		pt.stop();
		System.out.println("Audio Stopped");

		//Prepare to playback another file
		stop.setEnabled(false);
		play.setEnabled(true);
		stopPlayback = false;
		pausePlayback = false;
	}
	private void pauseAudio()
	{
		if (pausePlayback)
		{
			if (pt!=null)
			{
				pt.suspend();
				System.out.println("Suspended");
			}
		}
		else
		{
			pt.resume();
			System.out.println("Suspended");
		}
	}
	class PlayThread extends Thread
	{
		byte tempBuffer[] = new byte[1000000];
		private boolean stopPlayback=false;
		public void stopper()
		{
			this.stopPlayback=true;
		}

		public void run()
		{
			try
			{
				sourceDataLine.open(audioFormat);
				sourceDataLine.start();

				int cnt = audioInputStream.read(tempBuffer, 0, tempBuffer.length);
				while ((cnt != -1) && !(this.stopPlayback))
				{
					//System.out.println("stopPlayback=" + this.stopPlayback);
					if (!(pausePlayback))
					{
						if (cnt > 0)
						{
							sourceDataLine.write(tempBuffer, 0, cnt);
							//System.out.println("stopPlayback inside=" + stopPlayback);
						}
						cnt = audioInputStream.read(tempBuffer, 0, tempBuffer.length);
					}
					else
					{
						System.out.println("Paused???");
						continue;
					}
				}

				sourceDataLine.drain();
				sourceDataLine.close();

				//Prepare to playback another file
				stop.setEnabled(false);
				play.setEnabled(true);
				stopPlayback = false;
				pausePlayback = false;
				playNext();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.exit(0);
			}
		}

	}//end inner class PlayThread
	public void playNext()
	{
		if(myPlayList.isAnyNext(url.getText()))
		{
			stop.setEnabled(true);
			play.setEnabled(false);
			pausePlayback = false;
			url.setText(myPlayList.getNext(url.getText()));
			playAudio();
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if ((e.getSource() == browse) || (e.getSource() == openItem))
		{
			JFileChooser c = new JFileChooser();
			// Demonstrate "Open" dialog:

			c.setFileFilter(new FileFilter()
			{
				public boolean accept(File f)
				{
					return f.getName().toLowerCase().endsWith(".au") || f.getName().toLowerCase().endsWith(".wav") || f.getName().toLowerCase().endsWith(".mid") || f.isDirectory();
				}

				public String getDescription()
				{
					return "Music Files";
				}
			});

			int rVal = c.showOpenDialog(SwingAudioPlayer.this);
			if (rVal == JFileChooser.APPROVE_OPTION)
			{
				String temp;
				//temp = c.getCurrentDirectory().toString();
				url.setText((c.getSelectedFile()).toString());
				//temp = temp + "\\" + c.getSelectedFile().getName();
				//url.setText(temp);
			}
			if (rVal == JFileChooser.CANCEL_OPTION)
			{
				url.setText("You pressed cancel");
			}
		}

		if (e.getSource() == play)
		{
			stop.setEnabled(true);
			play.setEnabled(false);
			pausePlayback = false;
			playAudio();//Play the file
		}
		if (e.getSource() == stop)
		{
			System.out.println("Stop listened");
			stopPlayback = true;
			stopAudio();
		}
		if (e.getSource() == pause)
		{
			System.out.println("Pause listened");
			pausePlayback = !(pausePlayback);
			pauseAudio();
		}
		if ( e.getSource() == exitItem )
		{
			//set close commands
			setVisible(false);
			dispose();

		}
		if (e.getSource() == playList)
		{
			SongsListTable slt = new SongsListTable(this,myPlayList);
		}
		if(e.getSource() == next)
		{
			if(myPlayList.isAnyNext(url.getText()))
			{
			 stopPlayback = true;
			 stopAudio();
			 url.setText(myPlayList.getNext(url.getText()));
			 playAudio();
			}
		}
		if(e.getSource() == prev)
		{
			if(myPlayList.isAnyPrev(url.getText()))
			{
			 stopPlayback = true;
			 stopAudio();
			 url.setText(myPlayList.getPrev(url.getText()));
			 playAudio();
			}
		}

		if(e.getSource() == decreaseVol)

		{
			System.out.println("Decreasing volume by 10");
	
		 	try		
			{
				setVolume(AudioSystem.getLine(dataLineInfo),-10);

			}
			catch(LineUnavailableException ex)
			{
				System.out.println("Line Unavailable");

			}
		}

		if(e.getSource() == increaseVol)

		{

			System.out.println("Increasing volume by 10");

			try		
			{
				setVolume(AudioSystem.getLine(dataLineInfo),10);

			}
			catch(LineUnavailableException ex)
			{
				System.out.println("Line Unavailable");

			}
		}
		if (e.getSource() == about)
		{
			JOptionPane jop=new JOptionPane();
			System.out.println("About.....");
			jop.showMessageDialog(null,"This Application has been Developed\n     by M.Rajagopal","About",JOptionPane.INFORMATION_MESSAGE);
		}
		if (e.getSource() == help)
		{
			JOptionPane jop=new JOptionPane();
			System.out.println("About.....");
			jop.showMessageDialog(null,"This Swing Player can Support\n AudioFormats Like WAV,AU,MIDI","Help Text",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public static void main(String[] args)
	{
		run(new SwingAudioPlayer(), 600, 150);
	}

	public static void run(JFrame frame, int width, int height)
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
