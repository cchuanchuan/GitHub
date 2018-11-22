package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainGraph extends JFrame implements ActionListener {
	Parking park = new Parking();
	JTextField textfield1;
	JTextField textfield2;
	JTextField textfield3;
	JTextField textfield4;
	JTextField textfield5;
	JTextField textfield6;
	
	JButton arrivebutton;
	JButton departbutton;
	JButton messagebutton;
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	public MainGraph() {
		super();
		this.setSize(600,200);
		this.setLocationRelativeTo(null);
		
		//界面初始化函数
		this.setGraph();
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void setGraph() {
		this.setLayout(new GridLayout(3,1));
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.arrivebutton = new JButton("车辆到达");
		this.departbutton = new JButton("车辆离开");
		this.messagebutton = new JButton("查看车辆信息");
		this.textfield1 = new JTextField(10);
		this.textfield2 = new JTextField(10);
		this.textfield3 = new JTextField(10);
		this.textfield4 = new JTextField(10);
		this.textfield5 = new JTextField(10);
		this.textfield6 = new JTextField(10);
		this.arrivebutton.addActionListener(this);
		this.departbutton.addActionListener(this);
		this.messagebutton.addActionListener(this);
		
		this.panel1.add(new JLabel("状态"));
		this.panel1.add(this.textfield1);
		this.panel1.add(new JLabel("车牌"));
		this.panel1.add(this.textfield2);
		this.panel1.add(new JLabel("时间"));
		this.panel1.add(this.textfield3);
		this.panel1.add(this.arrivebutton);
		
		this.panel2.add(new JLabel("状态"));
		this.panel2.add(this.textfield4);
		this.panel2.add(new JLabel("车牌"));
		this.panel2.add(this.textfield5);
		this.panel2.add(new JLabel("时间"));
		this.panel2.add(this.textfield6);
		this.panel2.add(this.departbutton);
		
		this.panel3.add(messagebutton);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.arrivebutton) {
			if((!this.textfield1.getText().equals(""))&&(!this.textfield1.getText().equals(""))
					&&(!this.textfield1.getText().equals(""))) {
				Car car = new Car(this.textfield1.getText(),this.textfield2.getText(),Integer.parseInt(this.textfield3.getText()));
				this.park.add(car);
			}else {
				JOptionPane.showMessageDialog(this, "请输入相关数据");
			}
		}
		if(e.getSource() == this.departbutton) {
			if((!this.textfield4.getText().equals(""))&&(!this.textfield5.getText().equals(""))
					&&(!this.textfield6.getText().equals(""))) {
				Car car = new Car(this.textfield4.getText(),this.textfield5.getText(),Integer.parseInt(this.textfield6.getText()));
				this.park.getCar(car);
			}else {
				JOptionPane.showMessageDialog(this, "请输入相关数据");
			}
			
		}
		if(e.getSource() == this.messagebutton) {
			this.dispose();
			new MessageJFrame(this.park.getStack(),this);
		}
	}

	public static void main(String args[]) {
		new MainGraph();
	}
}
