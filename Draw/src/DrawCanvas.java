import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DrawCanvas extends Canvas implements MouseListener, MouseMotionListener,Runnable
{
	public Integer draw=0;
	public Integer temp=0;
	public static final Integer POINT=0;//画笔
	public static final Integer RECTANGLE =1;//长方形
	public static final Integer TRIANGLE=2;//三角形
	public static final Integer OVAL=3;//椭圆
	public static final Integer LINE=4;//直线
	public static final Integer RUBBER=5;//橡皮檫
	public static final Integer CHANGECOLOR=6;//颜色
	public static final Integer CLEAR=7;//清空
	public static final Integer G_NULL=8;
	
	public Point start,end;
	public ArrayList<Object>list;
	public Graphics g;
	private Color color=Color.BLACK;
	Thread thread;
	boolean isAccomplish=true;
	boolean could =false;
	Socket socket;
	ObjectOutputStream oout;
	ObjectInputStream oin;
	
	public DrawCanvas(Socket sock) throws ClassNotFoundException, IOException
	{
		super();
		this.setBackground(Color.white);
		this.list=new ArrayList<Object>();
		this.list.add(DrawCanvas.POINT);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.start=new Point();
		this.end=new Point();
		this.socket=sock;
		try {
			oout=new ObjectOutputStream(this.socket.getOutputStream());
			oin=new ObjectInputStream(this.socket.getInputStream());
		} catch (Exception e1) {System.out.println(e1.getClass().getName());}
		
		this.thread=new Thread(this);
	}
	public void run()
	{	
		this.g=this.getGraphics();
		while(true)
		{
			Object obj;
			try {
				obj = oin.readObject();
				if(obj instanceof Integer)
				{
					this.temp=this.draw;
					this.draw=(int)obj;
					this.list.add((int)obj);
				}
				else
				{
					this.DrawRun(obj);
				}

			}catch (IOException e) {break;} catch (ClassNotFoundException e) {}
		}
		try {
			this.socket.close();
		} catch (IOException e) {}
	}
	
	public void paint(Graphics g)
	{
		for(int i=0;i<this.list.size();i++)
		{
			Object obj;
			obj = list.get(i);
			if(obj instanceof Integer)
			{
				this.temp=this.draw;
				this.draw=(int)obj;
			}
			else
			{
				switch(this.draw)
				{
				case 0:{//曲线
					Point p2=(Point)obj;
					while(true)
					{
						Object obj1;
						obj1 = list.get(++i);
						if(obj1 instanceof Point){
							Point p1=(Point)obj1;
							g.drawLine(p1.x,p1.y,p2.x,p2.y);
							p2=p1;
						}
						else{
							this.temp=this.draw;
							this.draw=(Integer) obj1;
							break;
						}
					}
					break;
				}
				case 1:{//画长方形
					Point p=(Point)obj;
					g.drawRect(p.x-20, p.y-12, 40, 24);
					break;
				}
				case 2:{//三角形
					Point p=(Point)obj;
					g.drawLine(p.x, p.y-22, p.x-20, p.y+12);
					g.drawLine(p.x+20, p.y+12, p.x-20, p.y+12);
					g.drawLine(p.x, p.y-22, p.x+20, p.y+12);
					break;
				}
				case 3:{//画椭圆
					Point p=(Point)obj;
					g.drawOval(p.x-20, p.y-12, 40, 24);
					break;
				}
				case 4:{//直线
					Point p1=(Point)obj;
					Object obj1=list.get(++i);
					if(obj1 instanceof Point)
					{
						Point p2=(Point)obj1;
						g.drawLine(p1.x, p1.y, p2.x, p2.y);
					}
					else
					{
						this.temp=this.draw;
						this.draw=(Integer) obj1;
					}
					break;
				}
				case 5:{//橡皮檫
					Point p=(Point)obj;
					g.setColor(this.getBackground());
					g.fillRect(p.x-20, p.y-12,40,24);
					this.setColor(this.color);
					break;
				}
				case 6:{//选择颜色
					g.setColor((Color)obj);
					this.draw=this.temp;
					break;
				}
				case 8:{
					this.draw=temp;
					break;
				}
				default:break;
				}
			}
		}
	}
	public void DrawRun(Object obj)
	{
		switch(this.draw)
		{
		case 0:{//曲线
			Point p2=(Point)obj;
			while(true)
			{
				Object obj1;
				try {
					obj1 = oin.readObject();
					if(obj1 instanceof Point){
					Point p1=(Point)obj1;
					this.list.add(new Point(p2));
					g.drawLine(p1.x,p1.y,p2.x,p2.y);
					p2=p1;
				}
				else{
					this.temp=this.draw;
					this.draw=(Integer) obj1;
					list.add(obj1);
					break;
				}
				} catch (ClassNotFoundException | IOException e) {}	
			}
			break;
		}
		case 1:{//画长方形
			Point p=(Point)obj;
			g.drawRect(p.x-20, p.y-12, 40, 24);
			list.add(new Point(p));
			break;
		}
		case 2:{//三角形
			Point p=(Point)obj;
			g.drawLine(p.x, p.y-22, p.x-20, p.y+12);
			g.drawLine(p.x+20, p.y+12, p.x-20, p.y+12);
			g.drawLine(p.x, p.y-22, p.x+20, p.y+12);
			list.add(new Point(p));
			break;
		}
		case 3:{//画椭圆
			Point p=(Point)obj;
			g.drawOval(p.x-20, p.y-12, 40, 24);
			list.add(new Point(p));
			break;
		}
		case 4:{//直线
			if(this.isAccomplish==true)
			{
				start=(Point)obj;
				list.add(new Point(start));
				this.isAccomplish=false;
			}
			else
			{
				end=(Point)obj;
				g.drawLine(start.x, start.y, end.x, end.y);
				list.add(end);
				this.isAccomplish=true;
			}
			break;
		}
		case 5:{//橡皮檫
			Point p=(Point)obj;
			g.setColor(this.getBackground());
			g.fillRect(p.x-20, p.y-12,40,24);
			this.setColor(this.color);
			list.add(new Point(p));
			break;
		}
		case 6:{//选择颜色
			this.setColor((Color)obj);
			this.list.add((Color)obj);
			this.draw=this.temp;
			break;
		}
		case 7:{
			this.list.clear();
			this.repaint();
			list.add(6);
			list.add(this.color);
			this.draw=this.temp;
			list.add(this.draw);
			break;
		}
		case 8:{
			if(this.could==false)
			{
				this.could=true;
				this.draw=temp;
				JOptionPane.showMessageDialog(this, "轮到你画了");
			}
			else
			{
				this.could=false;
				this.draw=temp;
				try {
					this.oout.writeObject(null);
				} catch (IOException e) {}
			}
			break;
		}
		
		default:break;
		}//switch语句
	}
	
	public void DrawPressed(MouseEvent e)
	{
		switch(this.draw)
		{
		case 0:{
			start.x=e.getX();
			start.y=e.getY();
			break;
		}
		case 1:{
			Point p=new Point(e.getX(),e.getY());
			g.drawRect(p.x-20, p.y-12, 40, 24);
			try {
				this.oout.writeObject(new Point(p));
			} catch (IOException e1) {}
			list.add(new Point(p));
			break;
		}
		case 2:{
			Point p=new Point(e.getX(),e.getY());
			g.drawLine(p.x, p.y-22, p.x-20, p.y+12);
			g.drawLine(p.x+20, p.y+12, p.x-20, p.y+12);
			g.drawLine(p.x, p.y-22, p.x+20, p.y+12);
			try {
				this.oout.writeObject(new Point(p));
			} catch (IOException e1) {}
			list.add(new Point(p));
			break;
		}
		case 3:{
			Point p=new Point(e.getX(),e.getY());
			g.drawOval(e.getX()-20, e.getY()-12, 40, 24);
			try {
				this.oout.writeObject(new Point(p));
			} catch (IOException e1) {}
			list.add(new Point(p));
			break;
		}
		case 4:{
			start=new Point(e.getX(),e.getY());
			list.add(new Point(start));
			try {
				oout.writeObject(new Point(start));
			} catch (IOException e1) {}
			break;
		}
		case 5:{
			Point p=new Point(e.getX(),e.getY());
			g.setColor(this.getBackground());
			g.fillRect(p.x-20, p.y-12,40,24);
			this.setColor(this.color);
			try {
				this.oout.writeObject(new Point(p));
			} catch (IOException e1) {}
			list.add(new Point(p));
			break;
		}
		case 8:{
			break;
		}
		default:break;
		}
	}
	
	//鼠标拖动
	public void DrawDragged(MouseEvent e)
	{
		switch(this.draw)
		{
		case 0:{
			end.x=e.getX();
			end.y=e.getY();
			g.drawLine(start.x, start.y, end.x, end.y);
			list.add(new Point(end));
			try {
				this.oout.writeObject(new Point(start));
			} catch (Exception e1) {System.out.println(e1.getClass().getName());}
			start.x=end.x;
			start.y=end.y;
			break;
		}
		case 1:{
			Point p=new Point(e.getX(),e.getY());
			g.drawRect(e.getX()-20, e.getY()-12, 40, 24);
			try {
				this.oout.writeObject(new Point(p));
			} catch (IOException e1) {}
			list.add(new Point(p));
			break;
		}
		case 2:{
			Point p=new Point(e.getX(),e.getY());
			g.drawLine(p.x, p.y-22, p.x-20, p.y+12);
			g.drawLine(p.x+20, p.y+12, p.x-20, p.y+12);
			g.drawLine(p.x, p.y-22, p.x+20, p.y+12);
			try {
				this.oout.writeObject(new Point(p));
			} catch (IOException e1) {}
			list.add(new Point(p));
			break;
		}
		case 3:{
			Point p=new Point(e.getX(),e.getY());
			g.drawOval(e.getX()-20, e.getY()-12, 40, 24);
			try {
				this.oout.writeObject(new Point(p));
			} catch (IOException e1) {}
			list.add(new Point(p));
			break;
		}
		case 4:{
			break;
		}
		case 5:{
			Point p=new Point(e.getX(),e.getY());
			g.setColor(this.getBackground());
			g.fillRect(p.x-20, p.y-12,40,24);
			this.setColor(this.color);
			try {
				this.oout.writeObject(new Point(p));
			} catch (IOException e1) {}
			list.add(new Point(p));
			break;
		}
		default:break;
		}
	}
	
	//鼠标释放
	public void DrawReleased(MouseEvent e)
	{
		switch(this.draw)
		{
		case 0:{
			start.x=end.x;
			start.y=end.y;
			//list.add(new Point(start));//尾插入
			list.add(DrawCanvas.POINT);//记录换行
			try {
				//this.oout.writeObject(new Point(start));
				this.oout.writeObject(DrawCanvas.POINT);//传输数据，换行。
			} catch (IOException e1) {System.out.println(e1.getClass().getName());}
			break;
		}
		case 1:{
			break;
		}
		case 2:{
			break;
		}
		case 3:{
			break;
		}
		case 4:{
			end=new Point(e.getX(),e.getY());
			g.drawLine(start.x, start.y, end.x, end.y);
			list.add(new Point(end));
			try {
				oout.writeObject(new Point(end));
				//oout.writeObject(DrawCanvas.LINE);
			} catch (IOException e1) {
				System.out.println(e1.getClass().getName());}
			break;
		}
		case 5:{
			break;
		}
		default:break;
		
		}
	}
	
	//按下鼠标
	public void mousePressed(MouseEvent e) 
	{
		if(this.could==true)
			this.DrawPressed(e);
	}
	//释放鼠标
	public void mouseReleased(MouseEvent e)
	{
		if(this.could==true)
			this.DrawReleased(e);
	}
	//鼠标拖动
	public void mouseDragged(MouseEvent e) 
	{
		if(this.could==true)
			this.DrawDragged(e);
	}
	
	
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
		this.g.setColor(this.color);
	}

}
