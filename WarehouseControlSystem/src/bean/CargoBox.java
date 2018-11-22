package bean;

import java.io.Serializable;

public class CargoBox implements Serializable{
	//±ê×¼POJOÀà
	private String boxid;
	private String weight;
	private String time;
	private String position;
	private CargoInfor cargoinfor;
	public String getBoxid() {
		return boxid;
	}
	public void setBoxid(String boxid) {
		this.boxid = boxid;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public CargoInfor getCargoinfor() {
		return cargoinfor;
	}
	public void setCargoinfor(CargoInfor cargoinfor) {
		this.cargoinfor = cargoinfor;
	}
	@Override
	public String toString() {
		return "CargoBox [boxid=" + boxid + ", weight=" + weight + ", time=" + time + ", position=" + position
				+ ", cargoinfor=" + cargoinfor + "]";
	}
	public CargoBox(String boxid, String weight, String time, String position, CargoInfor cargoinfor) {
		super();
		this.boxid = boxid;
		this.weight = weight;
		this.time = time;
		this.position = position;
		this.cargoinfor = cargoinfor;
	}
	public CargoBox() {
	}
	

	
	
	
	

}
