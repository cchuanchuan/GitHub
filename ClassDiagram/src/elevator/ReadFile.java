package elevator;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
//此类用于读取文件，筛选有效数据，将有效数据放入队列
public class ReadFile {
	//请求队列，经过筛选后的有效请求数据
	private Queue<Request> queueFloor = new LinkedList<>();
	//文件中的数据，每条数据一行
		private List<String>orders = new LinkedList();
	
	public Queue<Request> getQueueFloor() {
		return queueFloor;
	}


	public void setQueueFloor(Queue<Request> queueFloor) {
		this.queueFloor = queueFloor;
	}
	//构造函数默认将文件读取到队列中
	public ReadFile() {
			try {
				File directory = new File("");
				File file = new java.io.File(directory.getAbsolutePath()+"\\src\\input.txt");
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String line = "";
				while((line=br.readLine())!=null) {
					//存入一行数据(去除空格)
					if(line.toUpperCase().equals("RUN")) {
						break;
					}
					orders.add(line.replaceAll(" ", ""));
				}
				if(line == null || !line.toUpperCase().equals("RUN")) {
					System.out.println("未检测到RUN,程序未执行");
					this.orders = new LinkedList();
				}
			} catch (FileNotFoundException e) {
				System.out.println("文件找不到");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("读取异常，请检查文件是否合法");
				e.printStackTrace();
			}
			
			InDoor indoor = null;
			OutDoor outdoor = null;
			
			for(String str:orders) {
				Object obj = analyseData(str);
				if(obj instanceof InDoor) {
					indoor = (InDoor)obj;
				}else if(obj instanceof OutDoor){
					outdoor = (OutDoor)obj;
				}/*else {
					System.out.println("已被忽略");
				}*/
				
				//符合要求的请求，打包放入队列中，等待执行
				if(indoor != null && outdoor != null
						&& indoor.getT() == outdoor.getT()) {
					if(this.queueFloor.peek() == null) {
						if(indoor.getT() == 0) {
							//第一个请求的T必须为0，否则报异常退出程序
							this.queueFloor.add(new Request(indoor,outdoor));
						}else {
							System.out.println("第一个请求的时间不为0,程序终止");
							break;
						}
					}else if(indoor.getT()>this.queueFloor.peek().getIndoor().getT()){
						this.queueFloor.add(new Request(indoor,outdoor));
					}
					
				}
			}
		
	}
	
	//此函数为分析字符串函数，通过此函数能分析出字符串属于哪种指令，并将其实例化方便调用指令
	public Object analyseData(String data) {
		String str = getTextCenter(data,"(",",");//得到表示字符串
		if(str.toUpperCase().equals("FR")) {
			return analyseOutDoor(data);
		}else if(str.toUpperCase().equals("ER")) {
			return analyseInDoor(data);
		}else {
			System.out.println(data+":命令格式不对，已被忽略");
			return null;
		}
	}
	//实例化电梯内指令
	public InDoor analyseInDoor(String data) {
		try {
			String ER = "ER";
			int n = Integer.parseInt(getTextCenter(data,",",","));
			double T = Double.parseDouble(getTextCenter(data,n+",",")"));
			
			if(n>=0&&n<=10) {
				return new InDoor(ER,n,T);
			}else {
				System.out.println(data+":楼层超出范围，已被忽略");
				return null;
			}
		}catch(Exception e) {
			System.out.println(data+":命令格式不对，已被忽略");
		}
		return null;
	}
	//实例化电梯外指令
	public OutDoor analyseOutDoor(String data) {
		try {
			String FR = "FR";
			int m = Integer.parseInt(getTextCenter(getTextCenter(data,",",")"),"",","));
			String UPorDOWN = getTextCenter(getTextCenter(data,",",")"),",",",");
			double T = Double.parseDouble(getTextCenter(data,UPorDOWN+",",")"));
			
			if(m>=0&&m<=10
					&&(UPorDOWN.toUpperCase().equals("UP")||UPorDOWN.toUpperCase().equals("DOWN"))) {
				return new OutDoor(FR,m,UPorDOWN,T);
			}else {
				System.out.println(data+":楼层不在范围或上下请求字符不正确，已被忽略");
				return null;
			}
		}catch(Exception e) {
			System.out.println(data+":命令格式不对，已被忽略");
		}
		return null;
	}
	
	//获取两个字符中间的字符串
	public  String getTextCenter(String text,String beginStr,String endStr){
		String textCenter="";
	    try {
	    	int startPosition=text.indexOf(beginStr)+beginStr.length();
	    	int endPosition=text.indexOf(endStr,startPosition);
	    	textCenter=text.substring(startPosition,endPosition);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return textCenter;
	}

}
