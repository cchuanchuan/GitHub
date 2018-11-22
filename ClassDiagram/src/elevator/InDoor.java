package elevator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InDoor {
	//标准POJO类
	//电梯内请求标识
	private String ER;
	//请求前往的目标楼层号
	private int n;
	//发出时刻
	private double T;
	public InDoor(String eR, int n, double t) {
		super();
		ER = eR;
		this.n = n;
		T = t;
	}
	public String getER() {
		return ER;
	}
	public void setER(String eR) {
		ER = eR;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public double getT() {
		return T;
	}
	public void setT(double t) {
		T = t;
	}
	@Override
	public String toString() {
		return "InDoor [ER=" + ER + ", n=" + n + ", T=" + T + "]";
	}
	
	
	
}


