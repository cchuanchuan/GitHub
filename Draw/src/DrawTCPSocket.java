import java.io.IOException;
import java.net.Socket;

public class DrawTCPSocket 
{
	public DrawTCPSocket(String name,String host,int port1,int port2) throws IOException, ClassNotFoundException
	{
		Socket socket1=new Socket(host,port1);
		Socket socket2=new Socket(host,port2);
		//new Draw(socket,"�����Ļ�ͼ�����ͻ���");
		new DrawJFrame(name,socket1,socket2);
	}
	public static void main(String arg[]) throws IOException, ClassNotFoundException
	{
		new DrawTCPSocket("�ͻ���1","127.0.0.1",2020,2025);
		new DrawTCPSocket("�ͻ���2","127.0.0.1",2021,2026);
		new DrawTCPSocket("�ͻ���3","127.0.0.1",2022,2027);
		new DrawTCPSocket("�ͻ���4","127.0.0.1",2023,2028);
	}
}
