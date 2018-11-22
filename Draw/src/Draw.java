import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Draw extends Canvas implements MouseListener,MouseMotionListener,WindowListener, ActionListener
{
	JFrame frame;
	ArrayList<Point> list_one;
	ArrayList<ArrayList<Point>>list_all;
	Point start,end;
	private Color color;
	private JButton button_color;
	JSplitPane split1,split2;
	JPanel panel_left,panel_right,panel_down;
	Graphics g;
	
	Socket socket;
	ObjectOutputStream oout;
	ObjectInputStream oin;
	public Draw(Socket socket,String name) throws ClassNotFoundException, IOException//
	{
		this.frame=new JFrame(name);
		//Dimension dim=this.frame.getToolkit().getScreenSize();
		//this.frame.setBounds(dim.width/4,dim.height/4,dim.width/2,dim.height/2);
		this.frame.setSize(1000, 600);
		this.frame.addWindowListener(this);
		
		
		this.setBackground(Color.white);
		this.start=new Point();
		this.end=new Point();
		this.list_one=new ArrayList<Point>();
		this.list_all=new ArrayList<ArrayList<Point>>();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		panel_left=new JPanel();
		panel_right=new JPanel(new GridLayout(1,1));
		button_color=new JButton("选择颜色");
		button_color.addActionListener(this);
		panel_left.add(button_color);
		split1=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		split1.setDividerLocation(250);
		split1.setLeftComponent(panel_left);
		split1.setRightComponent(panel_right);
		this.frame.add(split1);
		
		this.panel_down=new JPanel(new GridLayout(1,5));
		panel_down.add(new JLabel("选择图形:"));
		this.split2=new JSplitPane(JSplitPane.VERTICAL_SPLIT,this,panel_down);
		split2.setDividerLocation(450);
		panel_right.add(split2);
		
		String graphstr[]={"直线","三角形","长方形","椭圆"};
		for(int i=0;i<graphstr.length;i++)
		{
			JButton button=new JButton(graphstr[i]);
			panel_down.add(button);
			button.addActionListener(this);
		}
		
		
		
		this.frame.setVisible(true);
		
		this.socket=socket;
		try {
			oout=new ObjectOutputStream(this.socket.getOutputStream());
			oin=new ObjectInputStream(this.socket.getInputStream());
		} catch (Exception e1) {System.out.println(e1.getClass().getName());}
		this.g=this.getGraphics();
		while(true)
		{
			Object obj=oin.readObject();
			Point p0;
			Point p1;
			if(obj instanceof Color)
			{
				this.setColor((Color)obj);
				p0=(Point) oin.readObject();
			}
			else
				p0=(Point)obj;
			
			while(true)
			{
				obj=oin.readObject();
				if(obj instanceof Color)
				{
					this.setColor((Color)obj);
				}
				else
				{
					p1=(Point)obj;
					if(p1!=null){
						this.list_one.add(new Point(p0));
						g.drawLine(p0.x,p0.y,p1.x,p1.y);
						p0=p1;
					}
					else{
						list_all.add(new ArrayList<Point>(list_one));
						list_one.clear();
						obj=oin.readObject();
						if(obj instanceof Color)
						{
							this.setColor((Color)obj);
							p0=(Point)oin.readObject();
						}
						else
							p0=(Point)obj;
					}
				}
			}
		}
		
	}
	
	public void paint(Graphics g)
	{
		for(int i=0;i<list_all.size();i++)
		{	
			Point p0=list_all.get(i).get(0);
			for(int j=0;j<list_all.get(i).size();j++)
			{	
				Point p1=list_all.get(i).get(j);
				g.drawLine(p0.x, p0.y, p1.x, p1.y);
				p0=p1;
			}
		}
	}
	
	//按下鼠标
	public void mousePressed(MouseEvent e) 
	{
		start.x=e.getX();
		start.y=e.getY();
	}
	
	//释放鼠标
	public void mouseReleased(MouseEvent e) 
	{
		start.x=end.x;
		start.y=end.y;
		list_one.add(new Point(start));//尾插入
		try {
			this.oout.writeObject(new Point(start));
			this.oout.writeObject(null);
		} catch (IOException e1) {System.out.println(e1.getClass().getName());}
		list_all.add(new ArrayList<Point>(list_one));
		list_one.clear();
	}
	//鼠标拖动
	public void mouseDragged(MouseEvent e) 
	{
		end.x=e.getX();
		end.y=e.getY();
		list_one.add(new Point(end));
		g.drawLine(start.x, start.y, end.x, end.y);
		try {
			this.oout.writeObject(new Point(start));
		} catch (Exception e1) {System.out.println(e1.getClass().getName());}
		start.x=end.x;
		start.y=end.y;
	}
	
	public void mouseMoved(MouseEvent e){}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public void windowOpened(WindowEvent e) {}
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
		try {
			oout.close();
			oin.close();
			} catch (IOException e1) {System.out.println(e1.getClass().getName());}
		try {
			this.socket.close();
		} catch (IOException e1) {}
	}
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {
		this.repaint();
		}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
	
	public static void main(String arg[])
	{
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		this.g.setColor(this.color);
	}

	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getActionCommand()=="选择颜色")
		{
			Color c=JColorChooser.showDialog(this,"选择颜色",Color.blue);
			this.setColor(c);
			try {
				this.oout.writeObject(c);
			} catch (IOException e) {}
		}
		
	}
}

