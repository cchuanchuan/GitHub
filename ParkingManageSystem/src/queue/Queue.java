package queue;

public interface Queue<T>
{
	public abstract boolean isEmpty();
	public abstract boolean add(T x);//入队
	public abstract T peek();//返回队头元素
	public abstract T poll();//出队，返回队头元素
}
