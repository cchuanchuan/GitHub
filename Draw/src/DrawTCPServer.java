
import java.awt.Point;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class DrawTCPServer implements Runnable
{
	//private ServerSocket servers1[];
	//private ServerSocket servers2[];
	private Socket sockets1[];
	private Socket sockets2[];
	private ObjectOutputStream oouts[];
	private ObjectInputStream oins[];
	private PrintWriter couts[];
	private BufferedReader bins[];
	DrawChatServerThread drawchatserverthreads[];
	private int number;
	Thread thread;
	TimerThread timer;
	public DrawTCPServer(int port1,int port2) throws IOException, ClassNotFoundException
	{
		int num=Integer.parseInt(JOptionPane.showInputDialog("请输入游戏人数"));
		this.number=num;
		this.sockets1=new Socket[number];
		this.sockets2=new Socket[number];
		this.oouts=new ObjectOutputStream[number];
		this.oins=new ObjectInputStream[number];
		this.couts=new PrintWriter[number];
		this.bins=new BufferedReader[number];
		this.drawchatserverthreads=new DrawChatServerThread[number];
		for(int i=0;i<number;i++)
		{
			sockets1[i]=new ServerSocket(port1).accept();
			sockets2[i]=new ServerSocket(port2).accept();
			this.oouts[i]=new ObjectOutputStream(sockets2[i].getOutputStream());
			this.oins[i]=new ObjectInputStream(sockets2[i].getInputStream());
			this.couts[i]=new PrintWriter(sockets1[i].getOutputStream(),true);
			this.bins[i]=new BufferedReader(new InputStreamReader(sockets1[i].getInputStream()));
			System.out.println(sockets1[i].getLocalPort()+"连接成功");
			port1++;port2++;
		}
		for(int i=0;i<this.number;i++)
		{
			this.drawchatserverthreads[i]=new DrawChatServerThread(this.couts,this.bins,i);
			this.drawchatserverthreads[i].start();
			this.couts[i].println("服务器");
		}
		this.thread=new Thread(this);
		this.thread.start();
		this.timer=new TimerThread(this.oouts);
		timer.start();
	}
	
	public void run() 
	{
		Object obj;
		while(true)
		{
			try {
				obj=this.oins[this.timer.n].readObject();
				if(obj!=null)
				{
					for(int i=0;i<this.number;i++)
						if(i!=this.timer.n)
							this.oouts[i].writeObject(obj);
				} else
					try {
						System.out.println("thread线程睡眠1秒");
						thread.sleep(1000);
					} catch (InterruptedException e) {}
			} catch (ClassNotFoundException | IOException e1) {System.out.println(e1.getClass().getName());break;}
			catch(NullPointerException e2){}//System.out.println(e2.getClass().getName());}
		}

		for(int i=0;i<this.number;i++)
		{
			System.out.println("Socket对象关闭");
			try {
				this.bins[i].close();
				this.couts[i].close();
				this.oins[i].close();
				this.oouts.clone();
				this.sockets1[i].close();
				this.sockets2[i].close();
			} catch (IOException e) {}	
		}
	}
	
	public static void main(String arg[]) throws IOException, ClassNotFoundException
	{
		DrawTCPServer server=new DrawTCPServer(2020,2025);
		//server.thread.start();
	}
	
	
}
