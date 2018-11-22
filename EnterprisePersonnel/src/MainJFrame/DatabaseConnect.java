package MainJFrame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import MainJFrame.*;
public class DatabaseConnect
{
	//SQL Server 2005(������)JDBC����
	private static String DRIVERNAME="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	private Connection dbConn;
	private java.sql.Statement statement;
	private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	public DatabaseConnect(String name,String password)
	{
		//���ݿ�SQL server 2005(������)URL
		String dbURL="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=EnterprisePersonnel";
		try{
			//ָ����������������
			Class.forName(DRIVERNAME);
			
			//ͨ��DriverManager���getConnection()��������һ�����ݿ�����
			dbConn=DriverManager.getConnection(dbURL,name,password);
			
			//������ӳɹ�������̨���Connection Successful��
			System.out.println("Connection Successful!");
		}catch(Exception e){System.out.println(e.getMessage());}
	}
	//�ǲ�ѯ���
	public int executeUpdate(String sql) throws SQLException
	{
		//ͨ��Connection�����createStatement()��������һ��Statement����
		statement=dbConn.createStatement();
		return statement.executeUpdate(sql);
	}
	//��ѯ���
	public ResultSet executeQuery(String sql) throws SQLException
	{
		statement=dbConn.createStatement();
		return statement.executeQuery(sql);
	}
	public void closeConn() throws SQLException
	{
		if(statement!=null)
			statement.close();
		System.out.println("Disconnection Successful!");
		dbConn.close();
	}
	public PreparedStatement PreparedStatement(String sql) throws SQLException
	{
		// TODO Auto-generated method stub
		return dbConn.prepareStatement(sql);
	}
}
