package main;

import java.io.Serializable;

import bean.CargoBox;
import list.SinglyList;
import queue.*;

public class Warehouse implements Serializable{
	//n��ʾ�ֿ�һ�������
	private int n = 2;
	//m��ʾ�Ѿ���Ż�������������Ĭ�����Ϻ��´��
	private int m = 0;
	//�����������ֱ𴢴����»��ܻ�����Ϣ
	private SinglyList<CargoBox> uplist = new SinglyList<CargoBox>(n);
	private SinglyList<CargoBox> downlist = new SinglyList<CargoBox>(n);
	//װ�����
	private Queue<CargoBox> inqueue = new LinkedQueue<CargoBox>();
	//ȡ������
	private Queue<CargoBox> outqueue = new LinkedQueue<CargoBox>();
	
	
	public void cargoIn(CargoBox cargo) {
		if(m < n &&!(cargo ==null)) {
			cargo.setPosition(""+m);
			//this.uplist = new SinglyList<CargoBox>(n);
			this.uplist.set(m, cargo);
			m=m+1;
			System.out.println("�������ɹ���"+cargo.toString());
		}else if(m < 2*n) {
			cargo.setPosition(""+m);
			this.downlist.set(m-n, cargo);
			m=m+1;
			System.out.println("�������ɹ���"+cargo.toString());
		}else {
			cargo.setPosition(""+"-1");
			this.inqueue.add(cargo);
			System.out.println("�������ʧ�ܣ�����������");
		}
	}
	public void cargoOut(String boxid) {
		for(int i = 0 ; i < n ; i++) {
			if(this.uplist.get(i) != null && this.uplist.get(i).getBoxid()!=null&&this.uplist.get(i).getBoxid().equals(boxid)) {
				CargoBox cargo = this.uplist.get(i);
				this.uplist.set(i, null);
				System.out.println("��ȡ�ɹ���"+cargo);
				m=m-1;
				if(this.inqueue.peek()!=null) {
					this.inqueue.peek().setPosition(""+i);
					CargoBox cargobox = this.inqueue.poll();
					this.uplist.set(i, cargobox);
					m=m+1;
					System.out.println("�ȴ������л�����룺"+cargobox);
				}
			}
			if(this.downlist.get(i) != null &&this.downlist.get(i).getBoxid()!=null&&this.downlist.get(i).getBoxid().equals(boxid)) {
				CargoBox cargo = this.downlist.get(i);
				this.downlist.set(i, null);
				System.out.println("��ȡ�ɹ�:"+cargo);
				m=m-1;
				if(this.inqueue.peek()!=null) {
					this.inqueue.poll().setPosition(""+(i+n));
					CargoBox cargobox = this.inqueue.poll();
					this.uplist.set(i, cargobox);
					m=m+1;
					System.out.println("�ȴ������л�����룺"+cargobox);
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
