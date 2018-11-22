package main;

import java.io.Serializable;

import bean.CargoBox;
import list.SinglyList;
import queue.*;

public class Warehouse implements Serializable{
	//n表示仓库一层的容量
	private int n = 2;
	//m表示已经存放货物总数，货物默认先上后下存放
	private int m = 0;
	//两条单链表，分别储存上下货架货物信息
	private SinglyList<CargoBox> uplist = new SinglyList<CargoBox>(n);
	private SinglyList<CargoBox> downlist = new SinglyList<CargoBox>(n);
	//装箱队列
	private Queue<CargoBox> inqueue = new LinkedQueue<CargoBox>();
	//取货队列
	private Queue<CargoBox> outqueue = new LinkedQueue<CargoBox>();
	
	
	public void cargoIn(CargoBox cargo) {
		if(m < n &&!(cargo ==null)) {
			cargo.setPosition(""+m);
			//this.uplist = new SinglyList<CargoBox>(n);
			this.uplist.set(m, cargo);
			m=m+1;
			System.out.println("货物存入成功："+cargo.toString());
		}else if(m < 2*n) {
			cargo.setPosition(""+m);
			this.downlist.set(m-n, cargo);
			m=m+1;
			System.out.println("货物存入成功："+cargo.toString());
		}else {
			cargo.setPosition(""+"-1");
			this.inqueue.add(cargo);
			System.out.println("货物存入失败，放入待存队列");
		}
	}
	public void cargoOut(String boxid) {
		for(int i = 0 ; i < n ; i++) {
			if(this.uplist.get(i) != null && this.uplist.get(i).getBoxid()!=null&&this.uplist.get(i).getBoxid().equals(boxid)) {
				CargoBox cargo = this.uplist.get(i);
				this.uplist.set(i, null);
				System.out.println("提取成功："+cargo);
				m=m-1;
				if(this.inqueue.peek()!=null) {
					this.inqueue.peek().setPosition(""+i);
					CargoBox cargobox = this.inqueue.poll();
					this.uplist.set(i, cargobox);
					m=m+1;
					System.out.println("等待队列中货物存入："+cargobox);
				}
			}
			if(this.downlist.get(i) != null &&this.downlist.get(i).getBoxid()!=null&&this.downlist.get(i).getBoxid().equals(boxid)) {
				CargoBox cargo = this.downlist.get(i);
				this.downlist.set(i, null);
				System.out.println("提取成功:"+cargo);
				m=m-1;
				if(this.inqueue.peek()!=null) {
					this.inqueue.poll().setPosition(""+(i+n));
					CargoBox cargobox = this.inqueue.poll();
					this.uplist.set(i, cargobox);
					m=m+1;
					System.out.println("等待队列中货物存入："+cargobox);
				}
			}
		}
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
	public SinglyList<CargoBox> getUplist() {
		return uplist;
	}
	public void setUplist(SinglyList<CargoBox> uplist) {
		this.uplist = uplist;
	}
	public SinglyList<CargoBox> getDownlist() {
		return downlist;
	}
	public void setDownlist(SinglyList<CargoBox> downlist) {
		this.downlist = downlist;
	}
	public Queue<CargoBox> getInqueue() {
		return inqueue;
	}
	public void setInqueue(Queue<CargoBox> inqueue) {
		this.inqueue = inqueue;
	}
	public Queue<CargoBox> getOutqueue() {
		return outqueue;
	}
	public void setOutqueue(Queue<CargoBox> outqueue) {
		this.outqueue = outqueue;
	}
	
	
	
}
