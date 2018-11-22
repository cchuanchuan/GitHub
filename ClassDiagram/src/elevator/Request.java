package elevator;

public class Request {
	//Requset����һ���������Ϣ�����ļ��е������嵽һ������
	//һ������������������������������󹹳ɣ���ȷʵ���������ȱʧ�������󣬼����󲻺Ϸ���ֱ������
	private InDoor indoor;
	private OutDoor outdoor;
	public Request(InDoor indoor, OutDoor outdoor) {
		super();
		this.indoor = indoor;
		this.outdoor = outdoor;
	}
	public InDoor getIndoor() {
		return indoor;
	}
	public void setIndoor(InDoor indoor) {
		this.indoor = indoor;
	}
	public OutDoor getOutdoor() {
		return outdoor;
	}
	public void setOutdoor(OutDoor outdoor) {
		this.outdoor = outdoor;
	}
	@Override
	public String toString() {
		return "Request [indoor=" + indoor + ", outdoor=" + outdoor + "]";
	}
	
}
