package stack;

import list.SeqList;

public interface Stack<T> //ģ��
{
	public abstract boolean isEmpty();//�ж�ջ�Ƿ�Ϊ��
	public abstract void push(T x);//Ԫ��x��ջ
	public abstract T peek();//����ջ��Ԫ��
	public abstract T pop();//��ջ
}
