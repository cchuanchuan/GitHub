package main;

import java.util.Date;

public class Car {
	//车辆状态,A表示到达，D表示离开
	private String condition;
	//车牌号码
	private String number = "";
	//进入停车场时刻
	private int time = 0;
	
	//构造方法，创建一个汽车
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
