package elevator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//�÷���Ϊ���ݵ���Ҫʵ���㷨
public class Elevator {
	private double T = 0;//��ǰϵͳʱ��,��ʼʱ��Ϊ0
	private int floor = 1;//��ǰ����¥�㣬��ʼʱ��Ϊ1¥
	public double getT() {
		return T;
	}
	public void setT(double t) {
		T = t;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public Elevator(double t, int floor) {
		super();
		T = t;
		this.floor = floor;
	}
	

}
