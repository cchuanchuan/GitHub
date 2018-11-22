package list;

import java.io.Serializable;

public class Node<T> implements Serializable
{
	public T data;
	public Node<T> next;
	public Node(T data,Node<T>next)
	{
		this.data=data;
		this.next=next;
	}
	public Node()
	{
		this(null,null);
	}
	public String toString()
	{
		return this.data.toString();
	}
}
