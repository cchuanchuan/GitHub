package main;

import java.util.Date;

public class Car {
	//����״̬,A��ʾ���D��ʾ�뿪
	private String condition;
	//���ƺ���
	private String number = "";
	//����ͣ����ʱ��
	private int time = 0;
	
	//���췽��������һ������
	public Car(String condition, String number, int time) {
		super();
		this.condition = condition;
		this.number = number;
		this.time = time;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

}
