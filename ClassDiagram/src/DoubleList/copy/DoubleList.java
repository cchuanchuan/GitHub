package DoubleList.copy;

public class DoubleList<T>
{
	
	public DoubleNode<T>head;
	public DoubleList()
	{
		this.head=new DoubleNode<T>();
	}
	
	public DoubleList(T[] values)
	{
		this();
		DoubleNode<T>rear=this.head;
		for(int j=0;j<values.length;j++)
		{
			rear.next=new DoubleNode<T>(values[j],rear,null);
			rear=rear.next;
		}
		
	}
	public boolean isEmpty()
	{
		return this.head.next==null;
	}
	public DoubleNode<T> insert(T x)//尾插入
	{
		if(x==null)
		{
			throw new NullPointerException("x==null");
		}
		DoubleNode<T>q=new DoubleNode<T>(x);
		DoubleNode<T>p=this.head;
		while(p.next!=null)
		{
			p=p.next;
		}
		p.next=q;
		q.prev=p;
		return q;
	}
	public String toString()
	{
		String str=this.getClass().getName()+"{";
		for(DoubleNode<T>p=this.head.next;p!=null;p=p.next)
		{
			str+=p.data.toString();
			if(p.next!=null)
			{
				str+=",";
			}
		}
		return str+"}";
		
	}
	//在第i个结点前插入list链表
	//若i<0,则头插入
	//若i大于链表长度，则尾插入,返回false
	public boolean addAll(int i,DoubleList<T> list)
	{
		if(i<0)
			i=1;
		DoubleNode<T>front=this.head;
		int j=0;
		while(j<i)
		{
			if(front.next==null)
				break;
			front=front.next;
			j++;
		}
		if(front.next==null&&j<i)
		{
			front.next=list.head.next;
			head.next.prev=front;
			return false;
		}
			
		DoubleNode<T>p=list.head;//指向list尾结点
		while(p.next!=null)
		{
			p=p.next;
		}
		p.next=front;
		list.head.next.prev=front.prev;
		front.prev.next=list.head.next;
		front.prev=p;
		return true;
	}

	public static void main(String args[])
	{
		Integer[] sz={1,2,6,7,8,9};
		Integer[] sz2={3,4,5};
		DoubleList<Integer> a=new DoubleList<Integer>(sz);
		DoubleList<Integer> b=new DoubleList<Integer>(sz2);
		System.out.println("a:"+a.toString()+"\nb:"+b.toString());
		a.addAll(3, b);
		System.out.println("a:"+a.toString());		
	}
}
