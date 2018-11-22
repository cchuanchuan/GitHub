import java.awt.*;
import java.awt.event.*;

public class Calculator extends Frame  implements ActionListener
{
	private TextField text; 
	private Button button_1,button_2,button_plus,button_cancel,button_equals;
	public Calculator()
	{
		super("Calculator");
		this.setSize(320,120);
		this.setBackground(java.awt.Color.lightGray);
		this.setLocation(300,240);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		//text.setEditable(false);
		text=new TextField(10);
		this.add(text);
		
		button_1=new Button("1");
		button_2=new Button("2");
		button_plus=new Button("+");
		button_cancel=new Button("C");
		button_equals=new Button("=");
		
		this.add(button_1);
		this.add(button_2);
		this.add(button_plus);
		this.add(button_equals);
		this.add(button_cancel);
		
		button_1.addActionListener(this);
		button_2.addActionListener(this);
		button_plus.addActionListener(this);
		button_cancel.addActionListener(this);
		button_cancel.addActionListener(this);
		
		this.addWindowListener(new WinClose());
		this.setVisible(true);	
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==button_cancel)
			text.setText("");
		else if(e.getSource()==button_equals)
		{
			int x=0,y=0,sum;
			String str=text.getText();
			for(int i=0;i<str.length();i++)
			{
				if(str.charAt(i)!='+')
					x=(int)str.charAt(i);
				else if(str.charAt(i)=='+')
				{
					y=(int)str.charAt(i+1);
					sum=x+y;
					text.setText(str+"="+sum);
				}
			}
			
			
		}
		else
			text.setText(text.getText()+e.getActionCommand());
	}
	class WinClose implements WindowListener
	{
		public void windowActivated(WindowEvent arg0) {}
		public void windowClosed(WindowEvent e) {}
		public void windowClosing(WindowEvent arg0) {
			System.exit(0);
		}
		public void windowDeactivated(WindowEvent arg0) {}
		public void windowDeiconified(WindowEvent arg0) {}
		public void windowIconified(WindowEvent arg0) {}
		public void windowOpened(WindowEvent arg0) {}
	}
	public static void main(String args[])
	{
		new Calculator();
		
	}

}
