package elevator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InDoor {
	//��׼POJO��
	//�����������ʶ
	private String ER;
	//����ǰ����Ŀ��¥���
	private int n;
	//����ʱ��
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


