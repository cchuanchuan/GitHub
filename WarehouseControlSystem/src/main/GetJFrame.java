package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JTextField;

import bean.CargoBox;
import bean.CargoInfor;

public class GetJFrame extends JFrame implements ActionListener{
	private Warehouse warehouse;
	JTextField textfield[];
	JButton buttoncon = new JButton("确认");
	JButton buttonclo = new JButton("退出");

	public GetJFrame(Warehouse warehouse) {
		super();
		this.setName("取货界面");
		this.setSize(300,200);
		this.setLocationRelativeTo(null);
		this.warehouse = warehouse;
		//界面初始化函数
		this.setMenu();
		
		String textstr[] = {"货物编号"};
		this.setLayout(new GridLayout(textstr.length+1,2));
		this.textfield = new JTextField[textstr.length];
		for(int i=0;i<textstr.length;i++) {
			JLabel label = new JLabel(textstr[i]);
			this.textfield[i] = new JTextField(10);
			this.add(label);
			this.add(textfield[i]);
		}
		
		this.add(buttonclo);
		this.add(buttoncon);
		this.buttonclo.addActionListener(this);
		this.buttoncon.addActionListener(this);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	

	private void setMenu() {
		JMenuBar menubar=new JMenuBar();
		String strmenuitem[]={"存货","取货","查询"};
		for(int i=0;i<strmenuitem.length;i++) {
			JButton menuitem = new JButton(strmenuitem[i]);
			menubar.add(menuitem);
			menuitem.addActionListener(this);
		}
		this.setJMenuBar(menubar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("确认")) {
			String cargoid = this.textfield[0].getText();
			if(!cargoid.equals("")) {
				this.warehouse.cargoOut(cargoid);
				//保存文件
				FileOutputStream fout;
				try {
					fout = new FileOutputStream("D:\\warehouse.txt");
					ObjectOutputStream oout=new ObjectOutputStream(fout);
					oout.writeObject(this.warehouse);
					System.out.println("文件保存成功");
					this.textfield[0].setText("");
				} catch (IOException e1) {
					System.out.println("文件输出错误"+e1.getMessage());
					e1.printStackTrace();
				}
			}
		}
		
		if(e.getActionCommand().equals("退出")) {
			System.exit(0);
		}
		
		if(e.getActionCommand().equals("存货")) {
			this.dispose();
			new StoreJFrame(this.warehouse);
		}
		
		if(e.getActionCommand().equals("取货")) {
		}
		
		if(e.getActionCommand().equals("查询")) {
			this.dispose();
			new MainJFrame(this.warehouse);
		}
	}
	

}
