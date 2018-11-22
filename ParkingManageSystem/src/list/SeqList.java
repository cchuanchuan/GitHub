package list;
public class SeqList<T>
{
	protected Object[]element;
	protected int n;//Ԫ�ظ���
	public SeqList(int length)//���쳤��Ϊlength�Ŀձ�
	{
		this.element=new Object[length];
		this.n=0;
	}
	
	//�޲ι���
	public SeqList()
	{
		this(64);//�������������Ĺ��캯��
	}
	
	//��value�����ṩԪ�ع����±�
	public SeqList(T[] value)
	{
		this(value.length);
		for(int j=0;j<value.length;j++)
		{
			this.element[j]=value[j];
		}
		this.n=element.length;
	}
	
	//�������죬���
	public SeqList(SeqList<T>list)
	{
		this(list.element.length);
		for(int i=0;i<n;i++)
		{
			this.element[i]=list.element[i];
		}
		this.n=list.n;
	}
	
	//�ж��Ƿ�Ϊ��
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
	//����˳���Ԫ�ظ���
	public int size()
	{
		return this.n;
	}
	
	//���ص�i��Ԫ��
	public T get(int i)
	{
		if(i>=0&&i<this.n)
			return (T)this.element[i];
		return null;
	}
	
	//���õ�i���ַ�Ϊx�����ݴ��ǲ��룩
	public void set(int i,T x)
	{
		if(x==null)
			throw new NullPointerException("x==null");
		if(i>=0&&i<this.n)
			this.element[i]=x;
		else throw new java.lang.IndexOutOfBoundsException(i+"");
	}
	
	//����x��Ϊ��i��Ԫ�أ����ݴ�
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
		if(this.n==element.length)//����������������
		{
			Object[]source=this.element;
			this.element=new Object[source.length*2];
			for(int j=0;j<i;j++)
			{
				this.element[j]=source[j];
			}
		}
		for(int j=this.n-1;j>=i;j--)//��i��ʼ��Ԫ������ƶ�һ��
		{
			this.element[j+1]=element[j];
		}
		this.element[i]=x;//��ֵ
		this.n++;
		return i;
	}
	//β����x
	public int insert(T x)
	{
		return this.insert(this.n,x);
	}
	
	//ɾ����i��Ԫ��
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
	
	//����keyԪ�أ�����key�����
	public int search(T key)
	{
		for(int i=0;i<this.n;i++)
		{
			if(key.equals(this.element[i]))
				return i;
		}
		return -1;
	}
	
	public String toString()//����ַ���
	{
		String str=this.getClass().getName()+"(";//��������
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
