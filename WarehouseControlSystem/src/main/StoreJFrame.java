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
	JButton buttoncon = new JButton("ȷ��");
	JButton buttonclo = new JButton("�˳�");
	public StoreJFrame(Warehouse warehouse) {
		super();
		this.setName("�������");
		this.setSize(400,400);
		this.setLocationRelativeTo(null);
		this.warehouse = warehouse;
		//�����ʼ������
		this.setMenu();
		
		String textstr[] = {"���","����","����ʱ��","����","����","�ͻ����","Ԥ�ƴ洢ʱ��"};
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
		String strmenuitem[]={"���","ȡ��","��ѯ"};
		for(int i=0;i<strmenuitem.length;i++) {
			JButton menuitem = new JButton(strmenuitem[i]);
			menubar.add(menuitem);
			menuitem.addActionListener(this);
		}
		this.setJMenuBar(menubar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("ȷ��")) {
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
				
				//�����ļ�
				FileOutputStream fout;
				try {
					fout = new FileOutputStream("D:\\warehouse.txt");
					ObjectOutputStream oout=new ObjectOutputStream(fout);
					oout.writeObject(this.warehouse);
					System.out.println("�ļ�����ɹ�");
					for(JTextField t:textfield) {
						t.setText("");
					}
				} catch (IOException e1) {
					System.out.println("�ļ��������"+e1.getMessage());
					e1.printStackTrace();
				}
				
			}
		}
		
		if(e.getActionCommand().equals("�˳�")) {
			System.exit(0);
        	
		}
		
		if(e.getActionCommand().equals("���")) {
			
		}
		
		if(e.getActionCommand().equals("ȡ��")) {
			this.dispose();
			new GetJFrame(this.warehouse);
		}
		
		if(e.getActionCommand().equals("��ѯ")) {
			this.dispose();
			new MainJFrame(this.warehouse);
			
		}
	}
}
