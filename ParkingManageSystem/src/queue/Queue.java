package queue;

public interface Queue<T>
{
	public abstract boolean isEmpty();
	public abstract boolean add(T x);//���
	public abstract T peek();//���ض�ͷԪ��
	public abstract T poll();//���ӣ����ض�ͷԪ��
}
