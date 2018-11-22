package MainJFrame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputWorktime extends JFrame implements ActionListener
{
	public JButton button_confirm,button_cancel;
	public JTextField textfields[];
	DatabaseConnect dc;
	public InputWorktime(DatabaseConnect dc)
	{
		super("�������ϰ�ʱ��");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(300,260);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(4,1));
		this.dc=dc;
		
		this.add(new JLabel("�������ϰ�ʱ��",JLabel.CENTER));
		this.textfields=new JTextField[2];
		JPanel panel1=new JPanel(new GridLayout(1,2));
		this.add(panel1);
		panel1.add(new JLabel("ʱ(hour):"));
		panel1.add(this.textfields[0]=new JTextField());
		
		JPanel panel2=new JPanel(new GridLayout(1,2));
		this.add(panel2);
		panel2.add(new JLabel("��(minute):"));
		panel2.add(this.textfields[1]=new JTextField());
		JPanel panel3=new JPanel(new GridLayout(1,2));
		this.add(panel3);
		panel3.add(this.button_confirm=new JButton("ȷ��"));
		panel3.add(this.button_cancel=new JButton("ȡ��"));
		this.button_cancel.addActionListener(this);
		this.button_confirm.addActionListener(this);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		if(ev.getActionCommand().equals("ȷ��"))
		{
			if(!this.textfields[0].getText().equals(""))
			{
				int h=Integer.parseInt(this.textfields[0].getText());
				int m;
				if (this.textfields[1].getText().equals(""))
					m=0;
				else
					m=Integer.parseInt(this.textfields[1].getText());
				String sql="use EnterprisePersonnel update WorkTime set hour='"+h+"',minute='"+m+"'";
				try {
					dc.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "ʱ�����óɹ���"+h+"��"+m+"�ϰ�");
					this.dispose();
				} catch (SQLException e) {JOptionPane.showMessageDialog(null, e.getMessage());}
			}
			else
				JOptionPane.showMessageDialog(null, "������ʱ���ٵ�ȷ��");
		}
		if(ev.getActionCommand().equals("ȡ��"))
		{
			this.dispose();
		}
	}
}
