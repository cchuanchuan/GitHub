import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class DrawChatServerThread extends Thread
{
	private int n;
	private int number;
	private BufferedReader bins[];
	private PrintWriter couts[];
	public DrawChatServerThread(PrintWriter couts[],BufferedReader bins[],int n)
	{
		this.n=n;
		this.number=couts.length;
		this.bins=bins;
		this.couts=couts;
	}
	
	public void run()
	{
		String str="";
		while(true)
		{
			try {
				str=this.bins[n].readLine();
			} catch (IOException e) {break;}
			for(int i=0;i<this.number;i++)
				if(i!=n)
					this.couts[i].println(str);
		}
		System.out.println("chatÏß³Ì½áÊø");
	}
	

}
