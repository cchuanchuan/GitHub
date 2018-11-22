package stack;

import list.SeqList;

public interface Stack<T> //模板
{
	public abstract boolean isEmpty();//判断栈是否为空
	public abstract void push(T x);//元素x入栈
	public abstract T peek();//返回栈顶元素
	public abstract T pop();//出栈
}
