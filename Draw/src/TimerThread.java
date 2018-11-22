import java.awt.Point;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TimerThread extends Thread
{
	private DrawTCPServer drawtcpserver;
	public int n=0;
	public int number;
	private ObjectOutputStream oouts[];
	public TimerThread(ObjectOutputStream oouts[])
	{
		this.oouts=oouts;
		this.number=this.oouts.length;
	}
	public void run()
	{
		/*for(int i=0;i<this.number;i++)
		{
			try {
				this.oouts[i].writeObject(8);
				this.oouts[i].writeObject(new Point(-1,-1));
			} catch (IOException e) {}
		}*/
		while(true)
		{
			try {
				for(int i=0;i<this.number;i++)
				{
					n=i;
					this.oouts[n].writeObject(8);
					this.oouts[n].writeObject(new Point(-1,-1));
					for(int j=0;j<this.number;j++)
						this.oouts[j].writeObject(0);
					Thread.sleep(30000);
					this.oouts[n].writeObject(8);
					this.oouts[n].writeObject(new Point(-1,-1));
				}
				}catch (InterruptedException e) {}
				catch (IOException e) {System.out.println("timerÏß³Ì½áÊø");break;}
		}
	}
	
	
}
