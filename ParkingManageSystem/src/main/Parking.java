package main;

import stack.*;

import java.util.Date;

import queue.*;

public class Parking {
	//n��ʾͣ��������
	private int n = 2;
	//m��ʾͣ������ͣ����
	private int m = 0;
	//�շѱ�׼��ÿ�����Ǯ��������ԣ�
	private int secondcharge = 1;
	//ջ����ͣ���������
	private Stack<Car> stack = new SeqStack<Car>();
	//����û�г�λͣ�������qiche
	private Queue queue = new SeqQueue<Car>();
	
	public void add(Car car) {
		if(m<n) {
			//���ͣ��վ��û��
			stack.push(car);
			m=m+1;
			System.out.println("���ƺ�Ϊ��"+car.getNumber()+"�ĳ�����,����ʱ��Ϊ:"+car.getTime()+",ͣ��λ��Ϊ��"+m);
		}else {
			//��������ˣ���ͣ������
			queue.add(car);
			System.out.println("���ƺ�Ϊ��"+car.getNumber()+"����,û��ͣ��λ��δ�Ʒ�");
		}
	}
	
	public boolean getCar(Car car) {
		Car temp;
		//��ʱͣ������
		Queue tempqueue = new SeqQueue<Car>();
		while(!stack.isEmpty()) {
			temp = stack.pop();
			tempqueue.add(temp);
			if(temp.getNumber().equals(car.getNumber())) {
				//����ͣ��������
				double parkingtime = (car.getTime() - temp.getTime());
				System.out.println("���ƺ�Ϊ��"+temp.getNumber()+"�ĳ��뿪,�뿪ʱ��Ϊ:"+car.getTime()+"�շѣ�"+parkingtime*secondcharge);
				m--;
				//��������ĳ��Ż�ȥ
				tempqueue.poll();
				while(!tempqueue.isEmpty()) {
					Car car1 = (Car) tempqueue.poll();
					stack.push(car1);
				}
				
				if(m<n&&!queue.isEmpty()) {
					//��ԭ����λ���ģ�����һ���������ڳ�����һ����λ,��ԭ���г�ͣ�ڳ����������ڿ���ͣ��������
					Car tempcar = (Car) queue.poll();
					this.add(tempcar);
				}else {
					//���߳��⳵������һ��
					this.m--;
				}
				return true;
			}else {
				//������Ҫ�����ĳ�����ʱͣ������
				queue.add(temp);
			}
		}
		System.out.println("δ�ҵ��ó��Ƶĳ�");
		return false;
	}
	
	
	public Stack getStack() {
		return stack;
	}

	public void setStack(Stack stack) {
		this.stack = stack;
	}

	public Queue getQueue() {
		return queue;
	}

	public void setQueue(Queue queue) {
		this.queue = queue;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int getSecondcharge() {
		return secondcharge;
	}

	public void setSecondcharge(int secondcharge) {
		this.secondcharge = secondcharge;
	}
	
}
