import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.*;

public class DrawChat extends JPanel implements ActionListener,Runnable
{
	private JTextArea text_receiver;
	private JTextField text_sender;
	public PrintWriter cout;
	private BufferedReader bin;
	private String name;
	
	Thread thread;
	Socket socket;
	public DrawChat(String name,Socket sock) throws IOException
	{
		super();
		this.socket=sock;
		this.name=name;
		
		JToolBar toolbar=new JToolBar();
		
		toolbar.add(this.text_sender=new JTextField(15));
		JButton button_sender=new JButton("发送");
		button_sender.addActionListener(this);
		toolbar.add(button_sender);
		toolbar.setRollover(false);
		
		this.text_receiver=new JTextArea(12,20);
		this.text_receiver.setEditable(false);
		this.add(new JScrollPane(text_receiver));
		this.add(toolbar);
		this.cout=new PrintWriter(socket.getOutputStream(),true);
		this.bin=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.thread=new Thread(this);
	}
	
	public void run() 
	{
		try {
			String na=this.bin.readLine();
			text_receiver.append("连接 "+na+" 成功"+"\r\n");
		} catch (IOException e1) {System.out.println(e1.getClass().getName());}
		
		String line;
		while(true)
		{
			try {
				line=bin.readLine();
				//System.out.println(line);
				text_receiver.append(line+"\r\n");
			} catch (IOException e) {break;}
		}
		try {
			this.socket.close();
		} catch (IOException e) {}
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand()=="发送")
		{
			this.cout.println(this.name+" 说："+text_sender.getText());
			text_receiver.append("我说: "+text_sender.getText()+"\r\n");
			text_sender.setText("");
		}
	}
}
