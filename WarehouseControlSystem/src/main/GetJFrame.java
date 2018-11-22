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
	JButton buttoncon = new JButton("ȷ��");
	JButton buttonclo = new JButton("�˳�");

	public GetJFrame(Warehouse warehouse) {
		super();
		this.setName("ȡ������");
		this.setSize(300,200);
		this.setLocationRelativeTo(null);
		this.warehouse = warehouse;
		//�����ʼ������
		this.setMenu();
		
		String textstr[] = {"������"};
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
			String cargoid = this.textfield[0].getText();
			if(!cargoid.equals("")) {
				this.warehouse.cargoOut(cargoid);
				//�����ļ�
				FileOutputStream fout;
				try {
					fout = new FileOutputStream("D:\\warehouse.txt");
					ObjectOutputStream oout=new ObjectOutputStream(fout);
					oout.writeObject(this.warehouse);
					System.out.println("�ļ�����ɹ�");
					this.textfield[0].setText("");
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
			this.dispose();
			new StoreJFrame(this.warehouse);
		}
		
		if(e.getActionCommand().equals("ȡ��")) {
		}
		
		if(e.getActionCommand().equals("��ѯ")) {
			this.dispose();
			new MainJFrame(this.warehouse);
		}
	}
	

}
