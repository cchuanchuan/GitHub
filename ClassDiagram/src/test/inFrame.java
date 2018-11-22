package test;

	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	public class inFrame extends JFrame implements ActionListener{ //建立一个Frame窗体
	 
	 private JButton button;
	 private JPanel desktop; //用来存放internalFrame的容器
	 
	 public inFrame(){ //用构造函数，初始化
	  //有些人在做INTERNALFRAME时，internalFrame会无法显示，其主要原因是窗口界面重叠覆盖所造成的啊
	  //我现在在contentpane上面 加了两个 容器，一个是desktop用来存放JInternalFrame的
	           // 另一个是panel用来存放button的，
	  //然后把他们的位置错开，这个就不会覆盖了啊
	  super("InternalFrame的一个例子");
	  this.setSize(800,600);
	  this.setLocationRelativeTo(null);
	  this.setLayout(null);
	  
	  desktop=new JPanel();
	  desktop.setLayout(null);
	  desktop.setBounds(0,100,800,500);
	  this.add(desktop);
	  init();
	  
	  this.setVisible(true);
	 }
	 
	 private void init(){ //添加按钮，并且给按钮增加 监听
	  button=new JButton("打开InternalFrame");
	  button.setBounds(300,25,200,40);
	  button.addActionListener(this);
	  
	  JPanel panel = new JPanel();
	  panel.setLayout(null);
	  panel.setBounds(0,0,800,100);
	  panel.add(button);
	  panel.setBackground(Color.BLUE);
	  this.add(panel);
	   
	 }
	 
	 public void actionPerformed(ActionEvent e){ //事件处理
	  JInternalFrame inframe =new JInternalFrame("我是internalFrame",true,true,true,true);//定义一个inframe
	  inframe.setSize(600,400);
	  inframe.setLocation(50,50);
	  inframe.add(new JLabel("131243khjbkj"));
	  
	  desktop.add(inframe);//将inframe添加到desktop中
	  inframe.setVisible(true);//使他显示
	 }
	 
	 public static void main (String args[]){
	  new inFrame(); 
	 }
	} 
