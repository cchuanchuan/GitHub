package main;

import stack.*;

import java.util.Date;

import queue.*;

public class Parking {
	//n表示停车场容量
	private int n = 2;
	//m表示停车场已停汽车
	private int m = 0;
	//收费标准，每秒多少钱（方便测试）
	private int secondcharge = 1;
	//栈储存停车库的汽车
	private Stack<Car> stack = new SeqStack<Car>();
	//由于没有车位停在外面的qiche
	private Queue queue = new SeqQueue<Car>();
	
	public void add(Car car) {
		if(m<n) {
			//如果停车站车没满
			stack.push(car);
			m=m+1;
			System.out.println("车牌号为："+car.getNumber()+"的车到达,到达时间为:"+car.getTime()+",停车位置为："+m);
		}else {
			//如果车满了，则停到外面
			queue.add(car);
			System.out.println("车牌号为："+car.getNumber()+"到达,没有停车位，未计费");
		}
	}
	
	public boolean getCar(Car car) {
		Car temp;
		//临时停车队列
		Queue tempqueue = new SeqQueue<Car>();
		while(!stack.isEmpty()) {
			temp = stack.pop();
			tempqueue.add(temp);
			if(temp.getNumber().equals(car.getNumber())) {
				//计算停车多少秒
				double parkingtime = (car.getTime() - temp.getTime());
				System.out.println("车牌号为："+temp.getNumber()+"的车离开,离开时间为:"+car.getTime()+"收费："+parkingtime*secondcharge);
				m--;
				//将队列里的车放回去
				tempqueue.poll();
				while(!tempqueue.isEmpty()) {
					Car car1 = (Car) tempqueue.poll();
					stack.push(car1);
				}
				
				if(m<n&&!queue.isEmpty()) {
					//若原来车位满的，开出一辆车则现在车库有一个空位,且原来有车停在车库外面现在可以停到车库了
					Car tempcar = (Car) queue.poll();
					this.add(tempcar);
				}else {
					//否者车库车子少了一辆
					this.m--;
				}
				return true;
			}else {
				//若不是要开出的车则临时停到队列
				queue.add(temp);
			}
		}
		System.out.println("未找到该车牌的车");
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
