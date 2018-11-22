package list;
import java.util.ArrayList;

public class SinglyList<T>
{
	public Node<T>head;
	
	public SinglyList()
	{
		this.head=new Node<T>();
	}
	
	/*由value数组构造的非递归算法
	public SinglyList(T[] values)
	{
		this();
		Node<T> rear=this.head;
		for(int i=0;i<values.length;i++)
		{
			rear.next=new Node<T>(values[i],null);
			rear=rear.next;
		}
	}
	*/
	
	//由values数组构造的递归算法
	public SinglyList(T[]values)
	{
		this();
		this.head.next=create(values,0);
	}
	private Node<T>create(T[]values,int i)
	{
		Node<T>p=null;
		if(i<values.length)
		{
			p=new Node<T>(values[i],null);
			p.next=create(values,i+1);
		}
		return p;
	}
	
	//拷贝构造，深拷贝
	public SinglyList(SinglyList<T>list)
	{
		this();
		Node<T>q=this.head;
		for(Node<T>p=list.head.next;p!=null;p=p.next)
		{
			q.next=new Node<T>(p.data,null);
			q=q.next;
		}
	}
	
	public boolean isEmpty()
	{
		return this.head.next==null;
	}
	
	//判断两条链表是否相等
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(!(obj instanceof SinglyList))
			return false;
		SinglyList<T> list = (SinglyList)obj;
		Node<T>p = this.head.next;
		Node<T>q = list.head.next;
		while(p!=null){
			if(p.data.equals(q.data)){
				p=p.next;
				q=q.next;
			}
			else
				return false;
		}
		String s;
		if(q.next==null)
			return true;
		return false;
	}
	
	//返回第i个元素，1<=i<表的长度，若i越界，则返回null
	public T get(int i)
	{
		Node<T>p=this.head.next;
		for(int j=0;j<i&&p!=null;j++)
		{
			p=p.next;
		}
		return (i>=0&&p!=null)?p.data:null;
	}
	
	//设置第i个元素，0<=i<链表长度，x！=null，若i<0，则头插入，若i>n，则尾插入
	public void set(int i,T x)
	{
		if(x==null)
		{
			throw new NullPointerException("x==null");
		}	
		Node<T>p=this.head.next;
		for(int j=0;j<i&&p.next!=null;j++)
		{
			p=p.next;
		}
			p.data=x;
	}
	
	//输出字符串的非递归算法
	/*public String toString()
	{
		String str=this.getClass().getName()+"(";
		for(Node<T>p=this.head.next;p!=null;p=p.next)
		{
			str+=p.data.toString();
			if(p.next!=null)
				str+=",";
		}
		return str+")";
	}*/
	
	//输出字符串的递归算法
	public String toString()
	{
		return this.getClass().getName()+"("+this.toString(this.head.next)+")";
	}
	private String toString(Node<T>p)
	{
		if(p==null)
			return "";
		String str=p.data.toString();
		if(p.next!=null)
			str+=",";
		return str+this.toString(p.next);
		//return p.next==null?p.data.toString()+toString(p.next):p.data.toString()+","+toString(p.next);
	}
	
	//插入x作为第i个元素，x！=null，返回插入结点
	//对i采取容错，若i<0,则头插入，i>n,则尾插入
	public Node<T>insert(int i,T x)
	{
		if(x==null)
			throw new NullPointerException("x==null");
		Node<T>front=this.head;
		for(int j=0;front.next!=null&&j<i;j++)
		{
			front=front.next;
		}
		front.next=new Node<T>(x,front.next);
		return front.next;
	}
	
	//返回首个于key相等的元素结点，查找不成功则返回null
	public Node<T>search(T key)
	{
		Node<T>p=this.head;
		while(p.next!=null)
		{
			p=p.next;
			if(p.data.equals(key))
				return p;
		}
		return null;
	}
	
	//删除第i个元素，0<=i<n,返回被删除的元素，若i越界，则返回null
	public T remove(int i)
	{
		Node<T>front=this.head;
		for(int j=0;front.next!=null&&j<i;j++)
		{
			front=front.next;
		}
		if(i>=0&&front.next!=null)
		{
			T old =front.next.data;
			front.next=front.next.next;
			return old;
		}
		return null;
	}
	
	public static void main(String args[])
	{
		String[] a={"5","nn","4","55","d"};
		String[] aa={"5","nn","4","55","a"};
		SinglyList<String>cc=new SinglyList<String>(a);
		SinglyList<String>ccc=new SinglyList<String>(aa);
		System.out.println(cc.equals(ccc));
		/*cc.set(5,"3");
		cc.insert(-100, "5");
		//Node<String>p=cc.search("");
		System.out.print(cc);*/
	}
}
