package MainJFrame;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class PersonJTree extends JTree
{
	private DefaultTreeModel treeModel;
	private DefaultMutableTreeNode root;
	DefaultMutableTreeNode NodeDept=new DefaultMutableTreeNode("������Ϣ");
	DefaultMutableTreeNode NodePosition=new DefaultMutableTreeNode("ְλ��Ϣ");
	public PersonJTree(DatabaseConnect dc)
	{
		super();
		try {
			String sql1="use EnterprisePersonnel select DeptName from Dept";
			String sql2="use EnterprisePersonnel select PositionName from Position";
			ResultSet rs1=dc.executeQuery(sql1);
			ResultSet rs2=dc.executeQuery(sql2);
			this.treeModel=(DefaultTreeModel)this.getModel();
			this.root=new DefaultMutableTreeNode("��������ҵ");
	
			this.root.add(new DefaultMutableTreeNode("����Ա��"));
			
			while(rs1.next())
			{	
				NodeDept.add(new DefaultMutableTreeNode(rs1.getString("DeptName")));
			}
			this.root.add(NodeDept);
			while(rs2.next())
			{
				NodePosition.add(new DefaultMutableTreeNode(rs2.getString("PositionName")));
			}
			this.root.add(NodePosition);
	
			this.root.add(new DefaultMutableTreeNode("������Ϣ"));
	
			this.root.add(new DefaultMutableTreeNode("������Ϣ"));
			this.root.add(new DefaultMutableTreeNode("�����춯"));
			this.treeModel.setRoot(root);
		} catch (SQLException e) {JOptionPane.showMessageDialog(null, e.getMessage());}
	}
	public void addDept(String name)
	{
		this.NodeDept.add(new DefaultMutableTreeNode(name));
	}
	
	public void addPosition(String name)
	{
		this.NodePosition.add(new DefaultMutableTreeNode(name));
	}
}
