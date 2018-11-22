package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MainJFrame extends JFrame implements ActionListener{
	private Warehouse warehouse;
    ObjectInputStream ois = null;
	public MainJFrame(){
		super();
		this.setName("货物信息界面");
		this.setSize(800,400);
		this.setLocationRelativeTo(null);
		this.setMenu();
		
		try {
			FileInputStream fin = new FileInputStream("D:\\warehouse.txt");
			this.ois = new ObjectInputStream(fin);
			this.warehouse = (Warehouse) ois.readObject();
			//System.out.println(this.warehouse.getUplist().get(0));
			System.out.println("读取仓库对象成功");
		} catch (ClassNotFoundException | IOException e) {
			this.warehouse = new Warehouse();
			System.out.println("读取仓库对象失败，创建了新的仓库对象");
		}
		
		JTable table=new JTable(new TableModels(this.warehouse));
		DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table.setDefaultRenderer(Object.class, renderer); 
		table.setOpaque(true);
		table.setBackground(Color.white);
		this.add(new JScrollPane(table));
		
        this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public MainJFrame(Warehouse warehouse){
		super();
		this.setName("货物信息界面");
		this.setSize(800,400);
		this.setLocationRelativeTo(null);
		this.setMenu();
		
		
		this.warehouse = warehouse;
		JTable table=new JTable(new TableModels(warehouse));
		DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table.setDefaultRenderer(Object.class, renderer); 
		table.setOpaque(true);
		table.setBackground(Color.white);
		this.add(new JScrollPane(table));
		
        this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void setMenu() {
		JMenuBar menubar=new JMenuBar();
		String strmenuitem[]={"存货","取货"};
		for(int i=0;i<strmenuitem.length;i++) {
			JButton menuitem = new JButton(strmenuitem[i]);
			menubar.add(menuitem);
			menuitem.addActionListener(this);
		}
		this.setJMenuBar(menubar);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("存货")) {
			this.dispose();
			new StoreJFrame(this.warehouse);
		}
		
		if(e.getActionCommand().equals("取货")) {
			this.dispose();
			new GetJFrame(this.warehouse);
		}
	}

	public static void main(String args[]) throws FileNotFoundException, IOException {
		new MainJFrame();
	}
}
