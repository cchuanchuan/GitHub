package MainJFrame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddDeptJFrame extends JFrame implements ActionListener
{
	PersonJTree personjtree;
	DatabaseConnect dc;
	JTextField textfields[];
	JButton button_confirm;
	public AddDeptJFrame(PersonJTree personjtree,DatabaseConnect dc)
	{
		super("���Ӳ���");
		this.personjtree=personjtree;
		this.dc=dc;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(300,260);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new GridLayout(4,1));
		this.textfields=new JTextField[3];
		for(int i=0;i<this.textfields.length;i++)
			this.textfields[i]=new JTextField();
		JPanel panels[]=new JPanel[3];
		for(int i=0;i<panels.length;i++)
		{
			panels[i]=new JPanel(new GridLayout(1,2));
			this.add(panels[i]);
		}
		panels[0].add(new JLabel("���ű�ţ�"));
		panels[0].add(this.textfields[0]);
		panels[1].add(new JLabel("�������֣�"));
		panels[1].add(this.textfields[1]);
		panels[2].add(new JLabel("�����ţ�"));
		panels[2].add(this.textfields[2]);
		this.add(this.button_confirm=new JButton("ȷ�����"));
		this.button_confirm.addActionListener(this);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==this.button_confirm)
		{
			if(this.textfields[0].getText().equals("")||this.textfields[1].getText().equals("")
					||this.textfields[2].getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "��������Ϣ");
				return;
			}
			String sql1="use EnterprisePersonnel  select no from Person where no='"+this.textfields[2].getText()+"'";
			String sql2="use EnterprisePersonnel  select DeptNo from dept where DeptNo='"+this.textfields[0].getText()+"'";
			String sql3="use EnterprisePersonnel  select deptname from dept where DeptName='"+this.textfields[1].getText()+"'";
			try {
				ResultSet rs1=dc.executeQuery(sql1);
				ResultSet rs2=dc.executeQuery(sql2);
				ResultSet rs3=dc.executeQuery(sql3);
				if(rs1.next())
				{
					if(!(rs2.next()||rs3.next()))
					{	
						System.out.println(rs1.getString(1));
						String sql4="use EnterprisePersonnel insert into dept values('"+
								this.textfields[0].getText()+"','"+this.textfields[1].getText()+"','"+this.textfields[2].getText()+"')";
						dc.executeUpdate(sql4);
						JOptionPane.showMessageDialog(null, "��Ӳ��ųɹ�");
						this.personjtree.addDept(this.textfields[1].getText());
						this.dispose();
					}
					else
						JOptionPane.showMessageDialog(null, "���ű�Ż��������Ѵ���");
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Ա�������޹��ţ�"+this.textfields[2].getText());
				}
				
				
			} catch (SQLException e1) {JOptionPane.showMessageDialog(null, e1.getMessage());}
			
		}
		
	}

}
