package elevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OutDoor {
	//��׼POJO��
	//¥�������ʶ
	private String FR;
	//���������¥���
	private int m;
	//UPΪ��������DOWNΪ��������
	private  String UPorDOWN;
	//����ʱ��
	private double T;
	public OutDoor(String fR, int m, String uPorDOWN, double t) {
		super();
		FR = fR;
		this.m = m;
		UPorDOWN = uPorDOWN;
		T = t;
	}
	public String getFR() {
		return FR;
	}
	public void setFR(String fR) {
		FR = fR;
	}
	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
	}
	public String getUPorDOWN() {
		return UPorDOWN;
	}
	public void setUPorDOWN(String uPorDOWN) {
		UPorDOWN = uPorDOWN;
	}
	public double getT() {
		return T;
	}
	public void setT(double t) {
		T = t;
	}
	@Override
	public String toString() {
		return "OutDoor [FR=" + FR + ", m=" + m + ", UPorDOWN=" + UPorDOWN + ", T=" + T + "]";
	}
	
	
	
}
