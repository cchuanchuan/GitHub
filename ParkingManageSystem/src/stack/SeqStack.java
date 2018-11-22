package stack;

import list.SeqList;

public final  class SeqStack <T>implements Stack<T>
{
	private SeqList<T>list;
	public SeqStack(int length)
	{
		this.list=new SeqList<T>(length);
	}
	public SeqStack()
	{
		this(64);
	}
	public boolean isEmpty()
	{
		return this.list.isEmpty();
	}
	public void push(T x)//Ԫ��x��ջ
	{
		this.list.insert(x);
	}
	public T peek()//����ջ��Ԫ��
	{
		return this.list.get(list.size()-1);
	}
	public T pop()//��ջ
	{
		return list.remove(list.size()-1);
	}
}
