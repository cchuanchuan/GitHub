package list;
public class SeqList<T>
{
	protected Object[]element;
	protected int n;//元素个数
	public SeqList(int length)//构造长度为length的空表
	{
		this.element=new Object[length];
		this.n=0;
	}
	
	//无参构造
	public SeqList()
	{
		this(64);//调用上面申明的构造函数
	}
	
	//由value数组提供元素构造新表
	public SeqList(T[] value)
	{
		this(value.length);
		for(int j=0;j<value.length;j++)
		{
			this.element[j]=value[j];
		}
		this.n=element.length;
	}
	
	//拷贝构造，深拷贝
	public SeqList(SeqList<T>list)
	{
		this(list.element.length);
		for(int i=0;i<n;i++)
		{
			this.element[i]=list.element[i];
		}
		this.n=list.n;
	}
	
	//判断是否为空
	public boolean isEmpty()
	{
		return this.n==0;
	}
	
	public boolean equals(Object obj)
	{
		if(this==obj)
			return true;
		if(!(obj instanceof SeqList))
			return false;
		SeqList<T> list=(SeqList)obj;
		if(this.n!=list.n)
			return false;
		for(int i=0;i<this.n;i++)
			if(!this.element[i].equals(list.element[i]))
				return false;
		return true;
	}
	//返回顺序表元素个数
	public int size()
	{
		return this.n;
	}
	
	//返回第i个元素
	public T get(int i)
	{
		if(i>=0&&i<this.n)
			return (T)this.element[i];
		return null;
	}
	
	//设置第i个字符为x，并容错（非插入）
	public void set(int i,T x)
	{
		if(x==null)
			throw new NullPointerException("x==null");
		if(i>=0&&i<this.n)
			this.element[i]=x;
		else throw new java.lang.IndexOutOfBoundsException(i+"");
	}
	
	//插入x作为第i个元素，并容错
	public int insert(int i,T x)
	{
		if(x==null)
		{
			throw new NullPointerException("x==null");
		}
		if(i<0)
			i=0;
		if(i>this.n)
			i=this.n;
		if(this.n==element.length)//若数组满，则扩容
		{
			Object[]source=this.element;
			this.element=new Object[source.length*2];
			for(int j=0;j<i;j++)
			{
				this.element[j]=source[j];
			}
		}
		for(int j=this.n-1;j>=i;j--)//从i开始的元素向后移动一格
		{
			this.element[j+1]=element[j];
		}
		this.element[i]=x;//赋值
		this.n++;
		return i;
	}
	//尾插入x
	public int insert(T x)
	{
		return this.insert(this.n,x);
	}
	
	//删除第i个元素
	public T remove(int i)
	{
		if(this.n>0&&i>=0&&i<this.n)
		{
			T old=(T)this.element[i];
			for(int j=i;j<this.n-1;j++)
			{
				this.element[j]=this.element[j+1];
			}
			this.element[this.n-1]=null;
			this.n--;
			return old;
		}
		return null;
	}
	
	//搜索key元素，返回key的序号
	public int search(T key)
	{
		for(int i=0;i<this.n;i++)
		{
			if(key.equals(this.element[i]))
				return i;
		}
		return -1;
	}
	
	public String toString()//输出字符串
	{
		String str=this.getClass().getName()+"(";//返回类名
		if(this.n>0)
			str+=this.element[0].toString();
		for(int i=1;i<this.n;i++)
		{
			str+=","+this.element[i].toString();
		}
		return str+")";
	}
	
	public static void main(String args[])
	{	
		Integer[] c={1,2,3,4,5,6,7,8,9};
		SeqList<Integer> cc=new SeqList<Integer>(c);
		SeqList<Integer> ll=new SeqList<Integer>(c);
		System.out.println(cc.equals(ll));
	}
}
