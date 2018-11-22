import java.awt.AWTException;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class DrawJFrame extends JFrame implements ActionListener
{
	JPanel panel_all,panel_left,panel_right,panel_down,panel_tools;
	JSplitPane split1,split2;
	private JButton button_color,button_rubber,button_pen,button_clear,button_save;
	public Socket socket1,socket2;
	public DrawCanvas drawcanvas;
	public DrawChat drawchat;
	JMenuBar menubar=new JMenuBar();
	public DrawJFrame(String name,Socket sock1,Socket sock2) throws ClassNotFoundException, IOException
	{
		super(name);
		this.setSize(1000, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setJMenuBar(menubar);
		this.socket1=sock1;
		this.socket2=sock2;
		
		//this.panel_all=new JPanel();
		//this.add(panel_all);
		this.split1=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		this.add(split1);
		split1.setDividerLocation(250);
		this.panel_left=new JPanel(new GridLayout(2,1));
		this.panel_right=new JPanel(new GridLayout(1,1));
		split1.setLeftComponent(panel_left);
		split1.setRightComponent(panel_right);
		
		this.split2=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		panel_right.add(split2);
		split2.setDividerLocation(450);
		panel_down=new JPanel(new GridLayout(1,5));
		split2.setRightComponent(panel_down);
		
		panel_down.add(new JLabel("选择图形"));
		String graphstr[]={"直线","三角形","长方形","椭圆"};
		for(int i=0;i<graphstr.length;i++)
		{
			JButton button=new JButton(graphstr[i]);
			panel_down.add(button);
			button.addActionListener(this);
		}
		
		this.drawcanvas=new DrawCanvas(socket2);
		split2.setLeftComponent(drawcanvas);
		
		this.panel_tools=new JPanel(new GridLayout(5,1));
		String toolsstr[]={"选择画笔颜色","橡皮檫","画笔","全部清除","截图保存"};
		for(int i=0;i<toolsstr.length;i++)
		{
			JButton button=new JButton(toolsstr[i]);
			panel_tools.add(button);
			button.addActionListener(this);
		}
		this.panel_left.add(panel_tools);

		
		this.drawchat=new DrawChat(name,socket1);
		panel_left.add(drawchat);
		this.addMenu();
		this.setVisible(true);
		this.drawchat.thread.start();
		this.drawcanvas.thread.start();
	}
	
	private void addMenu()
	{
		JMenuBar menubar=new JMenuBar();
		this.setJMenuBar(menubar);
		String menustr[]={"打开","保存"};
		JMenu menu1=new JMenu("文件");
		for(int i=0;i<menustr.length;i++)
		{
			JMenuItem menuitem=new JMenuItem(menustr[i]);
			menu1.add(menuitem);
			menuitem.addActionListener(this);
		}
		menubar.add(menu1);
	}
	
	public static void main(String arg[])
	{
		
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand()=="全部清除")
		{
			this.drawcanvas.list.clear();
			this.drawcanvas.repaint();
			this.drawcanvas.list.add(6);
			this.drawcanvas.list.add(this.drawcanvas.getColor());
			this.drawcanvas.list.add(this.drawcanvas.draw);
			try {
				this.drawcanvas.oout.writeObject(DrawCanvas.CLEAR);
				this.drawcanvas.oout.writeObject(new Point(0,0));
			} catch (IOException e1) {}
		}
		if(e.getActionCommand()=="选择画笔颜色")
		{
			Color c=JColorChooser.showDialog(this,"选择颜色",Color.blue);
			this.drawcanvas.setColor(c);
			try {
				this.drawcanvas.oout.writeObject(DrawCanvas.CHANGECOLOR);
				this.drawcanvas.list.add(DrawCanvas.CHANGECOLOR);
				this.drawcanvas.oout.writeObject(c);
				this.drawcanvas.list.add(c);
			} catch (IOException e1) {}
		}
		if(e.getActionCommand()=="橡皮檫")
		{
			try {
				this.drawcanvas.draw=DrawCanvas.RUBBER;
				this.drawcanvas.list.add(DrawCanvas.RUBBER);
				this.drawcanvas.oout.writeObject(DrawCanvas.RUBBER);
			} catch (IOException e1) {}
		}
		if(e.getActionCommand()=="长方形")
		{
			try {
				this.drawcanvas.draw=DrawCanvas.RECTANGLE;
				this.drawcanvas.list.add(DrawCanvas.RECTANGLE);
				this.drawcanvas.oout.writeObject(DrawCanvas.RECTANGLE);
			} catch (IOException e1) {}
		}
		if(e.getActionCommand()=="画笔")
		{
			try {
				this.drawcanvas.draw=DrawCanvas.POINT;
				this.drawcanvas.list.add(DrawCanvas.POINT);
				this.drawcanvas.oout.writeObject(DrawCanvas.POINT);
			} catch (IOException e1) {}
		}
		if(e.getActionCommand()=="椭圆")
		{
			try {
				this.drawcanvas.draw=DrawCanvas.OVAL;
				this.drawcanvas.list.add(DrawCanvas.OVAL);
				this.drawcanvas.oout.writeObject(DrawCanvas.OVAL);
			} catch (IOException e1) {}
		}
		if(e.getActionCommand()=="直线")
		{
			try {
				this.drawcanvas.draw=DrawCanvas.LINE;
				this.drawcanvas.list.add(DrawCanvas.LINE);
				this.drawcanvas.oout.writeObject(DrawCanvas.LINE);
			} catch (IOException e1) {}
		}
		if(e.getActionCommand()=="三角形")
		{
			try {
				this.drawcanvas.draw=DrawCanvas.TRIANGLE;
				this.drawcanvas.list.add(DrawCanvas.TRIANGLE);
				this.drawcanvas.oout.writeObject(DrawCanvas.TRIANGLE);
			} catch (IOException e1) {}
		}
		if(e.getActionCommand()=="保存")
		{
			JFileChooser jfc=new JFileChooser("D://123");  
			//jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
	        jfc.showSaveDialog(this);    
	        if(jfc.getSelectedFile().isDirectory()){  
	            System.out.println("文件夹:"+jfc.getSelectedFile().getAbsolutePath());  
	        }else if(jfc.getSelectedFile().isFile()){  
	            try {
	            	FileOutputStream fout=new FileOutputStream(jfc.getSelectedFile().getPath());
	            	ObjectOutputStream oout2=new ObjectOutputStream(fout);
					//FileInputStream fin=new FileInputStream(jfc.getSelectedFile().getPath());
					//ObjectInputStream oin2=new ObjectInputStream(fin);
					for(int i=0;i<this.drawcanvas.list.size();i++)
					{
						oout2.writeObject(this.drawcanvas.list.get(i));
					}
					oout2.close();
					fout.close();
				} catch (IOException e1) {}
	            
	        }  
	        System.out.println(jfc.getSelectedFile().getName());  
		}
		if(e.getActionCommand()=="打开")
		{
			JFileChooser jfc=new JFileChooser("D://123");  
			//jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
	        jfc.showOpenDialog(this);    
	        if(jfc.getSelectedFile().isFile())
	        {  
				try {
					FileInputStream fin = new FileInputStream(jfc.getSelectedFile().getPath());
					ObjectInputStream oin2=new ObjectInputStream(fin);
					ArrayList<Object>al=new ArrayList<Object>();
					while(true)
					{
						try{
							al.add(oin2.readObject());
						}catch (IOException e1) {this.drawcanvas.list=al;this.drawcanvas.repaint();break;}
					}
					oin2.close();
					fin.close();
				} catch (Exception e1) {}
	        }
		}
		if(e.getActionCommand()=="截图保存")
		{	
	        try {
				Robot robot=new Robot();
				BufferedImage screenshot=robot.createScreenCapture(
						new Rectangle(this.getX(),this.getY(),this.getWidth(),this.getHeight()));
				String str=JOptionPane.showInputDialog(this, "请输入文件名");
				File f=new File("D:\\123\\"+str+".jpg");
				FileOutputStream fos=new FileOutputStream(f);
				ImageIO.write(screenshot, "JPG",fos); 
				JOptionPane.showMessageDialog(this,"文件已保存至："+f.getPath());
			}  catch (Exception e1) {}
	       
		}
	}

}
