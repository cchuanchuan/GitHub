package elevator;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
//�������ڶ�ȡ�ļ���ɸѡ��Ч���ݣ�����Ч���ݷ������
public class ReadFile {
	//������У�����ɸѡ�����Ч��������
	private Queue<Request> queueFloor = new LinkedList<>();
	//�ļ��е����ݣ�ÿ������һ��
		private List<String>orders = new LinkedList();
	
	public Queue<Request> getQueueFloor() {
		return queueFloor;
	}


	public void setQueueFloor(Queue<Request> queueFloor) {
		this.queueFloor = queueFloor;
	}
	//���캯��Ĭ�Ͻ��ļ���ȡ��������
	public ReadFile() {
			try {
				File directory = new File("");
				File file = new java.io.File(directory.getAbsolutePath()+"\\src\\input.txt");
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String line = "";
				while((line=br.readLine())!=null) {
					//����һ������(ȥ���ո�)
					if(line.toUpperCase().equals("RUN")) {
						break;
					}
					orders.add(line.replaceAll(" ", ""));
				}
				if(line == null || !line.toUpperCase().equals("RUN")) {
					System.out.println("δ��⵽RUN,����δִ��");
					this.orders = new LinkedList();
				}
			} catch (FileNotFoundException e) {
				System.out.println("�ļ��Ҳ���");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("��ȡ�쳣�������ļ��Ƿ�Ϸ�");
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
					System.out.println("�ѱ�����");
				}*/
				
				//����Ҫ������󣬴����������У��ȴ�ִ��
				if(indoor != null && outdoor != null
						&& indoor.getT() == outdoor.getT()) {
					if(this.queueFloor.peek() == null) {
						if(indoor.getT() == 0) {
							//��һ�������T����Ϊ0�������쳣�˳�����
							this.queueFloor.add(new Request(indoor,outdoor));
						}else {
							System.out.println("��һ�������ʱ�䲻Ϊ0,������ֹ");
							break;
						}
					}else if(indoor.getT()>this.queueFloor.peek().getIndoor().getT()){
						this.queueFloor.add(new Request(indoor,outdoor));
					}
					
				}
			}
		
	}
	
	//�˺���Ϊ�����ַ���������ͨ���˺����ܷ������ַ�����������ָ�������ʵ�����������ָ��
	public Object analyseData(String data) {
		String str = getTextCenter(data,"(",",");//�õ���ʾ�ַ���
		if(str.toUpperCase().equals("FR")) {
			return analyseOutDoor(data);
		}else if(str.toUpperCase().equals("ER")) {
			return analyseInDoor(data);
		}else {
			System.out.println(data+":�����ʽ���ԣ��ѱ�����");
			return null;
		}
	}
	//ʵ����������ָ��
	public InDoor analyseInDoor(String data) {
		try {
			String ER = "ER";
			int n = Integer.parseInt(getTextCenter(data,",",","));
			double T = Double.parseDouble(getTextCenter(data,n+",",")"));
			
			if(n>=0&&n<=10) {
				return new InDoor(ER,n,T);
			}else {
				System.out.println(data+":¥�㳬����Χ���ѱ�����");
				return null;
			}
		}catch(Exception e) {
			System.out.println(data+":�����ʽ���ԣ��ѱ�����");
		}
		return null;
	}
	//ʵ����������ָ��
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
				System.out.println(data+":¥�㲻�ڷ�Χ�����������ַ�����ȷ���ѱ�����");
				return null;
			}
		}catch(Exception e) {
			System.out.println(data+":�����ʽ���ԣ��ѱ�����");
		}
		return null;
	}
	
	//��ȡ�����ַ��м���ַ���
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
