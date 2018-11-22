package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import stack.Stack;

public class MessageJFrame extends JFrame implements ActionListener {
	JButton button = new JButton("¹Ø±Õ");
	MainGraph maingraph;
	public MessageJFrame(Stack stack,MainGraph maingraph) {
		super();
		this.setSize(300,400);
		this.setLocationRelativeTo(null);
		this.maingraph = maingraph;
		
		JTable table=new JTable(new TableModels(stack));
		DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table.setDefaultRenderer(Object.class, renderer); 
		table.setOpaque(true);
		table.setBackground(Color.white);
		this.add(new JScrollPane(table));
		this.button.addActionListener(this);
		this.add(button,BorderLayout.SOUTH) ;
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.dispose();
	}
	

}
