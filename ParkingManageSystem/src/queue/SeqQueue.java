package queue;

public final class SeqQueue<T> implements Queue<T>
{
	private Object element[];
	private int front,rear;
	public SeqQueue(int length)
	{
		if(length<64)
			length=64;
		this.element=new Object[length];
		this.front=this.rear=0;
	}
	public SeqQueue()
	{
		this(64);
	}
	public boolean isEmpty()
	{
		return this.front==this.rear;
	}
	public boolean add(T x)
	{
		if(x==null)
			return false;
		if(this.front==(this.rear+1)%this.element.length)//若队列满，则扩充数组
		{
			Object[]temp=this.element;
			this.element=new Object[this.element.length*2];
			int j=0;
			for(int i=this.front;i!=this.rear;i=(i+1)%temp.length)
			{
				this.element[j++]=temp[i];
			}
			this.front=0;
			this.rear=j;
		}
		this.element[rear]=x;
		this.rear=(this.rear+1)%this.element.length;
		return true;
	}
	public T peek()
	{
		return this.isEmpty()?null:(T)this.element[this.front];//???为什么要强制转换类型？
	}
	public T poll()
	{
		if(this.isEmpty())
			return null;
		T temp=(T)this.element[this.front];
		this.front=(this.front+1)%this.element.length;
		return temp;
	}
	
	public static void main(String argp[]) {
		Queue que = new SeqQueue<String>();
		que.add("1321");
		que.add("454");
		System.out.println(que.poll());
		System.out.println(que.poll());
		
	}
}
