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
	public void push(T x)//元素x入栈
	{
		this.list.insert(x);
	}
	public T peek()//返回栈顶元素
	{
		return this.list.get(list.size()-1);
	}
	public T pop()//出栈
	{
		return list.remove(list.size()-1);
	}
}
