package elevator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Schedule {
	private List<String>executes = new LinkedList();//��¼ϵͳ������־��д���ļ�
	private Queue<Request> queueFloor;//�������
	private Elevator elevator;
	
	//���췽������������ȡ�ļ��࣬�ڶ�ȡ�ļ����г�ʼ����������г�ʼ��
	public Schedule() {
		ReadFile file = new ReadFile();
		this.queueFloor = file.getQueueFloor();
		this.elevator = new Elevator(0,1);
	}
	
	//��ʵ�ϣ�������ֱ��ɸѡ����Ҫ������󣬰�˳��Ϊ�����
	public void FAFS() {
		//����ʼ����ǰ��������¥��Ϊ��1����ǰϵͳʱ��Ϊ��0
		for(Request request:queueFloor) {
			//��������Ϊ�������������
			//�õ����ݵ����з���
			String UPorDown = this.elevator.getFloor()>request.getOutdoor().getM()
					?"DOWN":this.elevator.getFloor()==request.getOutdoor().getM()
					?"STILL":"UP";
			//�õ����ݿ�ʼ���е�ʱ��
			double time = this.elevator.getT()>=request.getOutdoor().getT()
					?this.elevator.getT():request.getOutdoor().getT();
			//�����ݵ�ִ�����ݴ�������
			this.executes.add("("+this.elevator.getFloor()+","+UPorDown+","+time+")");
			
			//����ִ�н������ϵͳʱ��͵���¥��
			this.elevator.setT(time+calculateTime(this.elevator.getFloor(),request.getOutdoor().getM()));
			this.elevator.setFloor(request.getOutdoor().getM());
			
			//��������Ϊ�����ڲ����Ĺ���
			UPorDown = request.getOutdoor().getUPorDOWN();
			time = this.elevator.getT()+1;
			this.executes.add("("+this.elevator.getFloor()+","+UPorDown+","+time+")");
			//����ϵͳʱ��͵���¥��
			this.elevator.setT(time+calculateTime(this.elevator.getFloor(),request.getIndoor().getN()));
			this.elevator.setFloor(request.getIndoor().getN());
		}
		this.executes.add("("+this.elevator.getFloor()+","+"END"+","+this.elevator.getT()+")");
	}
	
	public double calculateTime(int floor1,int floor2) {
		return 0.5*Math.abs(floor1-floor2);
	}
	
	public boolean writeFile() {
		PrintWriter pw;
		FileWriter fr;
		try {
			File directory = new File("");
			File file = new java.io.File(directory.getAbsolutePath()+"\\src\\output.txt");
			fr = new FileWriter(file);
			pw = new PrintWriter(fr,true);
			for(String str:this.executes) {
				pw.println(str);	
				System.out.println(str);
			}
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("�ļ��Ҳ���");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("�ֽ����������ļ��Ƿ�Ϸ�");
			e.printStackTrace();
		}
		System.out.println("��־����ʧ��");
		return false;
	}
	
	public static void main(String args[]) throws IOException {
        Schedule schedule = new Schedule();
    	schedule.FAFS();
    	schedule.writeFile();
	}
}
