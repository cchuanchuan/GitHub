package main;

import java.awt.GridLayout;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.*;

import bean.CargoBox;
import bean.CargoInfor;

public class StoreJFrame extends JFrame implements ActionListener{
	private Warehouse warehouse;
	JTextField textfield[];
	JButton buttoncon = new JButton("确认");
	JButton buttonclo = new JButton("退出");
	public StoreJFrame(Warehouse warehouse) {
		super();
		this.setName("存货界面");
		this.setSize(400,400);
		this.setLocationRelativeTo(null);
		this.warehouse = warehouse;
		//界面初始化函数
		this.setMenu();
		
		String textstr[] = {"编号","重量","存入时间","名称","种类","客户编号","预计存储时间"};
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
			if(!this.textfield[0].getText().equals("")&&!this.textfield[2].getText().equals("")
					&&!this.textfield[1].equals("")) {
				CargoInfor cargoinf = new CargoInfor(this.textfield[0].getText()
						,this.textfield[3].getText()
						,this.textfield[4].getText()
						,this.textfield[5].getText()
						,this.textfield[6].getText());
				CargoBox cargobox = new CargoBox(this.textfield[0].getText()
						,this.textfield[1].getText()
						,this.textfield[2].getText()
						,"-1"
						,cargoinf);
				this.warehouse.cargoIn(cargobox);
				
				//保存文件
				FileOutputStream fout;
				try {
					fout = new FileOutputStream("D:\\warehouse.txt");
					ObjectOutputStream oout=new ObjectOutputStream(fout);
					oout.writeObject(this.warehouse);
					System.out.println("文件保存成功");
					for(JTextField t:textfield) {
						t.setText("");
					}
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
			
		}
		
		if(e.getActionCommand().equals("取货")) {
			this.dispose();
			new GetJFrame(this.warehouse);
		}
		
		if(e.getActionCommand().equals("查询")) {
			this.dispose();
			new MainJFrame(this.warehouse);
			
		}
	}
}
