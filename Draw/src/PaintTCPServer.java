import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PaintTCPServer implements WindowListener
{
	private ServerSocket server1;
	private ServerSocket server2;
	DrawJFrame drawjframe;
	public PaintTCPServer(int port1,int port2,String name) throws IOException, ClassNotFoundException
	{
		server1 = new ServerSocket(port1,2);
		server2 = new ServerSocket(port2,2);
		Socket client1=server1.accept();
		//System.out.println(client1.getInetAddress());
		Socket client2=server2.accept();
		//new Draw(client,"川川的画图——服务器");
		this.drawjframe=new DrawJFrame("服务器",client1,client2);
		this.drawjframe.addWindowListener(this);
	}
	public static void main(String arg[]) throws IOException, ClassNotFoundException
	{
		new PaintTCPServer(2000,2004,"ccc");
	}
	@Override
	public void windowOpened(WindowEvent e) {}
	@Override
	public void windowClosing(WindowEvent e) {
		try {
			this.server1.close();
			this.server2.close();
		} catch (IOException e1) {}
	}
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e) {}
}
