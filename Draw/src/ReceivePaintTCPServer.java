import java.io.IOException;
import java.net.Socket;

public class ReceivePaintTCPServer 
{
	public ReceivePaintTCPServer(String host,int port1,int port2) throws IOException, ClassNotFoundException
	{
		Socket socket1=new Socket(host,port1);
		Socket socket2=new Socket(host,port2);
		//new Draw(socket,"�����Ļ�ͼ�����ͻ���");
		new DrawJFrame("�ͻ���1",socket1,socket2);
	}
	public static void main(String arg[]) throws IOException, ClassNotFoundException
	{
		new ReceivePaintTCPServer("127.0.0.1",2000,2004);
	}
}
