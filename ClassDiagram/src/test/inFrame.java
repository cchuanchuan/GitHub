package test;

	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	public class inFrame extends JFrame implements ActionListener{ //����һ��Frame����
	 
	 private JButton button;
	 private JPanel desktop; //�������internalFrame������
	 
	 public inFrame(){ //�ù��캯������ʼ��
	  //��Щ������INTERNALFRAMEʱ��internalFrame���޷���ʾ������Ҫԭ���Ǵ��ڽ����ص���������ɵİ�
	  //��������contentpane���� �������� ������һ����desktop�������JInternalFrame��
	           // ��һ����panel�������button�ģ�
	  //Ȼ������ǵ�λ�ô�������Ͳ��Ḳ���˰�
	  super("InternalFrame��һ������");
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
	 
	 private void init(){ //��Ӱ�ť�����Ҹ���ť���� ����
	  button=new JButton("��InternalFrame");
	  button.setBounds(300,25,200,40);
	  button.addActionListener(this);
	  
	  JPanel panel = new JPanel();
	  panel.setLayout(null);
	  panel.setBounds(0,0,800,100);
	  panel.add(button);
	  panel.setBackground(Color.BLUE);
	  this.add(panel);
	   
	 }
	 
	 public void actionPerformed(ActionEvent e){ //�¼�����
	  JInternalFrame inframe =new JInternalFrame("����internalFrame",true,true,true,true);//����һ��inframe
	  inframe.setSize(600,400);
	  inframe.setLocation(50,50);
	  inframe.add(new JLabel("131243khjbkj"));
	  
	  desktop.add(inframe);//��inframe��ӵ�desktop��
	  inframe.setVisible(true);//ʹ����ʾ
	 }
	 
	 public static void main (String args[]){
	  new inFrame(); 
	 }
	} 
