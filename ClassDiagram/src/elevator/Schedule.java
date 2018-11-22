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
	private List<String>executes = new LinkedList();//记录系统运行日志，写入文件
	private Queue<Request> queueFloor;//请求队列
	private Elevator elevator;
	
	//构造方法创建创建读取文件类，在读取文件类中初始化将请求队列初始化
	public Schedule() {
		ReadFile file = new ReadFile();
		this.queueFloor = file.getQueueFloor();
		this.elevator = new Elevator(0,1);
	}
	
	//事实上，本程序直接筛选符合要求的请求，按顺序为其服务
	public void FAFS() {
		//程序开始，当前电梯所在楼层为：1，当前系统时间为：0
		for(Request request:queueFloor) {
			//以下五条为电梯外操作过程
			//得到电梯的运行方向
			String UPorDown = this.elevator.getFloor()>request.getOutdoor().getM()
					?"DOWN":this.elevator.getFloor()==request.getOutdoor().getM()
					?"STILL":"UP";
			//得到电梯开始运行的时间
			double time = this.elevator.getT()>=request.getOutdoor().getT()
					?this.elevator.getT():request.getOutdoor().getT();
			//将电梯的执行数据存入链表
			this.executes.add("("+this.elevator.getFloor()+","+UPorDown+","+time+")");
			
			//更新执行结束后的系统时间和电梯楼层
			this.elevator.setT(time+calculateTime(this.elevator.getFloor(),request.getOutdoor().getM()));
			this.elevator.setFloor(request.getOutdoor().getM());
			
			//以下五条为电梯内操作的过程
			UPorDown = request.getOutdoor().getUPorDOWN();
			time = this.elevator.getT()+1;
			this.executes.add("("+this.elevator.getFloor()+","+UPorDown+","+time+")");
			//更新系统时间和电梯楼层
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
			System.out.println("文件找不到");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("字节流，请检查文件是否合法");
			e.printStackTrace();
		}
		System.out.println("日志保存失败");
		return false;
	}
	
	public static void main(String args[]) throws IOException {
        Schedule schedule = new Schedule();
    	schedule.FAFS();
    	schedule.writeFile();
	}
}
