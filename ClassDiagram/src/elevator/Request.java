package elevator;

public class Request {
	//Requset描述一次请求的信息，将文件中的请求集体到一个类中
	//一次完整的请求由梯外请求和梯内请求构成，若确实梯外请求或缺失梯内请求，即请求不合法，直接跳过
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
